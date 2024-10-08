package it.polimi.ingsw.controller;

import it.polimi.ingsw.network.Client.*;
import it.polimi.ingsw.network.Client.RMI.RMI_Client;
import it.polimi.ingsw.network.Client.Socket.Socket_Client;
import it.polimi.ingsw.network.Messages.*;
import it.polimi.ingsw.network.Messages.ChatMessage;
import it.polimi.ingsw.network.ObserverImplementation.Observer;
import it.polimi.ingsw.tasks.NumberRequestTask;
import it.polimi.ingsw.view.*;
import it.polimi.ingsw.network.Client.ClientModel;
import it.polimi.ingsw.view.Cli.Cli;
//import it.polimi.ingsw.View.Gui.guiControllers.ViewFactory;
import it.polimi.ingsw.view.Gui.guiControllers.ViewFactory;
import it.polimi.ingsw.view.OBSMessages.*;
import it.polimi.ingsw.utility.Couple;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;

/**
 * The logical controller of a client
 */
public class ClientController implements Observer<View, OBS_Message> {
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_RESET = "\u001B[0m";
    private View view;
    private ClientModel clientModel;
    private CommunicationQueueHandler communicationQueueHandler;
    private PingHandler pingHandler;
    private Client client;


    public ClientController(String viewMode)
    {
        if(viewMode.equals("CLI"))
        {
            view = new Cli();
        }
        else
        {
            this.view = ViewFactory.getInstance();
        }
        clientModel = view.getClientModel();
        view.addObserver(this);
    }



    /** This method runs generic view as it was an independent component
     * @author Francesco Lo Mastro
     */
    public void turnOnView(){
        view.startView();
    }


    /** This method handles the creation of a client contacting a remote server
     * @author Francesco Lo Mastro
     * @author RIccardo Figini
     * @author Alberto Aniballi
     * @author Andrea Ferrini
     * @param chosenUsername the username of the client chosen by the user
     * @param chosenTechnology the technology chosen by the user
     * @param chosenAddress the remote server ip address
     * @param chosenPort the remote server port number
     */
    public void createClient(String chosenUsername, int chosenTechnology, String chosenAddress, int chosenPort) {
        try {
            switch (chosenTechnology) {
                case 0 -> {
                    client = new RMI_Client(chosenUsername, chosenAddress, chosenPort);
                }
                case 1 -> {
                    client = new Socket_Client(chosenUsername, chosenAddress, chosenPort);
                }
            }
            communicationQueueHandler = new CommunicationQueueHandler(client, this);
            pingHandler = new PingHandler(client, this);

            client.connect();
            new Thread(communicationQueueHandler).start();
            new Thread(pingHandler).start();
        }
        catch (Exception e){
            view.errorCreatingClient(chosenAddress,chosenPort);
        }
    }




    /**
     * This method handles all the message coming from the network
     * @param message the message coming from the network
     * @author Francesco Lo Mastro
     * @author Alberto Aniballi
     * @author Riccardo Figni
     * @author Andrea Ferrini
     */
    public void onMessage(Message message)  {
        switch (message.getType())
        {
            case ACCEPTED_LOGIN_ANSWER -> {
                AcceptedLoginMessage msg = (AcceptedLoginMessage)message;
                view.acceptedLogin();
                clientModel.setMyName(msg.getAcceptedName());
            }
            case PLAYER_NUMBER_REQUEST -> {
                PlayerNumberRequest msg = (PlayerNumberRequest)message;
                view.askNumberOfPlayers(msg.getMinimumPlayers(), msg.getMaximumPlayers());
            }
            case INVALID_USERNAME_ANSWER -> {
                pingHandler.shutDown();
                view.onInvalidUsername();
            }
            case LOBBY_UPDATE_MESSAGE -> {
                LobbyUpdateMessage msg = (LobbyUpdateMessage) message;
                view.lobbyUpdate("Currently in lobby: " + msg.getLobbyUsernames().size() + "/" + msg.getLobbySize() + " players. "
                        +"Members: " + msg.getLobbyUsernames());
            }
            case NEW_GAME_SERVER_MESSAGE -> {
                NewGameServerMessage msg = (NewGameServerMessage) message;
                if(client instanceof RMI_Client c) {
                    c.changeServer(msg);
                }
            }
            case START_GAME_MESSAGE -> {
                view.startGame();
                view.startChat();
            }
            case CHAT_MESSAGE -> {
                ChatMessage msg = (ChatMessage) message;
                String text = msg.getText();
                String username = msg.getSenderName();
                view.chatMessage(username,text);
            }
            case PLAYER_MOVE_REQUEST -> {
                view.printAll();
                view.askMove();
            }
            case BAD_MOVE_ANSWER -> {
                BadMoveMessage msg = (BadMoveMessage) message;
                view.onBadMoveAnswer(msg);
                view.askMove();
            }
            case WINNER_MESSAGE -> {
                pingHandler.shutDown();
                WinnerMessage msg = (WinnerMessage) message;
                view.printFinalRank(msg);
                view.printMessage("The game is ended\nYour points: " + msg.getMyPoints() + "\nWinner: " + msg.getFinalRanking().get(0).getFirst());
            }
            case ALMOST_OVER_MESSAGE -> {
                clientModel.onAlmostOver((AlmostOverMessage) message);
            }
            case ERROR_MESSAGE -> {
                ErrorMessage msg = (ErrorMessage) message;
                pingHandler.shutDown();
                view.closeGame(msg.getErrorText());
            }
            case COMMON_GOAL_REACHED_MESSAGE -> {
                clientModel.addPoint((CommonGoalMessage) message);
            }
            case GOOD_MOVE_ANSWER -> {
                if(view instanceof Cli)
                    view.printMessage("Move performed successfully\n" +
                        ">> \033[34mYou can use the chat while waiting for your turn, try type something!\033[0m");
                if(((GoodMoveMessage) message).getGainedPointsFirstCard()>0){
                    view.printMessage("Points gained from first common goal: " + ((GoodMoveMessage) message).getGainedPointsFirstCard());
                }
                if(((GoodMoveMessage) message).getGainedPointsSecondCard()>0){
                    view.printMessage("Points gained from second common goal: " + ((GoodMoveMessage) message).getGainedPointsSecondCard());
                }

            }
            case INITIAL_SETUP_MESSAGE -> {
                SetupMessage msg = (SetupMessage) message;
                clientModel.setup(msg);
            }
            case UPDATE_GRID_MESSAGE -> {
                clientModel.setGrid(((GridMessage) message).getGrid(), ((GridMessage) message).getTypeOfGridMessage() );
            }
            case UPDATE_LIBRARY_MESSAGE -> {
                LibraryMessage msg = (LibraryMessage) message;
                clientModel.setLibrary(msg.getLibraryOwner(), msg.getLibrary(), msg.getGridCardRemoved(), msg.getLibraryCardAdded());
            }
            case RETURN_TO_OLD_GAME_MESSAGE -> {
                view.printMessage("You are joining in your old game");
            }
            case FINAL_POINTS_MESSAGE -> {
                ArrayList<Couple<String, Integer>> list = ((ScoreMessage) message).getList();
                for (Couple<String, Integer> stringIntegerCouple : list) {
                    clientModel.setPointToPlayer(stringIntegerCouple.getFirst(), stringIntegerCouple.getSecond());
                }
            }
            case GAME_OVER_MESSAGE -> {
                view.printMessage("Game is over");
            }
        }

    }





    /** Collects all the messages coming from the view
     * @author Francesco Lo Mastro
     * @param o the observable view
     * @param arg the OBS_Message coming from the view
     */
    @Override
    public void update(View o, OBS_Message arg) {
        switch (arg.getType()) {
            case START ->
            {
                view.askInitialInfo();
            }
            case INITIAL_INFO ->
            {
                OBS_InitialInfoMessage message = (OBS_InitialInfoMessage) arg;
                createClient(message.getChosenUsername(), message.getChosenTechnology(),
                        message.getChosenAddress(), message.getChosenPort());
            }
            case NUMBER_OF_PLAYERS -> {
                OBS_NumberOfPlayerMessage message = (OBS_NumberOfPlayerMessage) arg;
                try {
                    client.sendMessage(new PlayerNumberAnswer(client.getUsername(), message.getNumberOfPlayers()));
                } catch (IOException e) {
                    throw new RuntimeException("Couldn't contact the server" + e);
                }
            }
            case CHANGED_USERNAME -> {
                OBS_ChangedUsernameMessage message = (OBS_ChangedUsernameMessage) arg;
                client.changeUsername(message.getChangedUsername());
            }
            case MOVE -> {
                OBS_MoveMessage message = (OBS_MoveMessage) arg;
                try {
                    client.sendMessage(new MoveMessage(message.getMove(), message.getColumn()));
                } catch (IOException e) {
                    System.out.println("Impossible send move to server, " + e);
                }
            }
            case CHAT ->
            {
                OBS_ChatMessage message = (OBS_ChatMessage) arg;
                try {
                    client.sendMessage(new ChatMessage(client.getUsername(),message.getChatMessage()));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

}
