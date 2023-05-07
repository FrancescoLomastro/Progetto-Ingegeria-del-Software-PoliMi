package it.polimi.ingsw.controller;

import it.polimi.ingsw.Network.Client.*;
import it.polimi.ingsw.Network.Client.RMI.RMI_Client;
import it.polimi.ingsw.Network.Client.Socket.Socket_Client;
import it.polimi.ingsw.Network.Messages.*;
import it.polimi.ingsw.Network.Messages.ChatMessage;
import it.polimi.ingsw.Network.ObserverImplementation.Observer;
import it.polimi.ingsw.View.*;
import it.polimi.ingsw.Network.Client.ClientModel;
import it.polimi.ingsw.View.Cli.Cli;
//import it.polimi.ingsw.View.Gui.guiControllers.ViewFactory;
import it.polimi.ingsw.View.Gui.guiControllers.ViewFactory;
import it.polimi.ingsw.View.OBSMessages.*;
import it.polimi.ingsw.model.Utility.Couple;

import java.io.IOException;
import java.util.ArrayList;


public class ClientController implements Observer<View, OBS_Message> {
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_RESET = "\u001B[0m";
    private View view;
    private ClientModel clientModel;
    private MessageQueueHandler messageReceiver;
    private Client client;

    public ClientController(String viewMode)
    {
        this.clientModel = new ClientModel();
        if(viewMode.equals("CLI"))
        {
            view = new Cli(clientModel);
        }
        else
        {
            this.view = ViewFactory.getInstance();
        }
        view.addObserver(this);

        new Thread(view).start();
    }

    public void turnOnView(){
        view.startView();
    }
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
            messageReceiver = new MessageQueueHandler(client, this);
            client.connect();
            new Thread(messageReceiver).start();
        }
        catch (Exception e){
            view.errorCreatingClient(chosenAddress,chosenPort);
            throw new RuntimeException("It was impossible to create a client and contact " +
                    "the server at [" + chosenAddress + "," + chosenPort + "]\n" + e);
        }
    }

    //messaggi ricevuti dalla rete
    public void onMessage(Message message) {
        System.out.println(ANSI_YELLOW + "Message has arrived: " + message.getType() + ANSI_RESET);
        switch (message.getType())
        {
            case ACCEPTED_LOGIN_MESSAGE ->
            {
                view.printMessage("Connection accepted, waiting for other players");
            }
            case PLAYER_NUMBER_REQUEST ->
            {
                PlayerNumberRequest msg = (PlayerNumberRequest)message;
                view.askNumberOfPlayers(msg.getMinimumPlayers(), msg.getMaximumPlayers());
            }
            case LOBBY_UPDATE_MESSAGE ->
            {
                LobbyUpdateMessage msg = (LobbyUpdateMessage) message;
                view.printMessage("Currently in lobby: " + msg.getUsernames().size() + "/" + msg.getLimitOfPlayers() + " players. "
                        +"Members: " + msg.getUsernames());
            }
            case INVALID_USERNAME_MESSAGE ->
            {
                view.onInvalidUsername();
            }
            case NEW_GAME_SERVER_MESSAGE ->
            {
                NewGameServerMessage msg = (NewGameServerMessage) message;
                if(client instanceof RMI_Client c) {
                    c.changeServer(msg);
                }
                view.printMessage("Server moved to a game");
            }
            case START_GAME_MESSAGE ->
            {
                view.printMessage("Game started");
                view.startChat();
            }
            case CHAT_MESSAGE ->
            {
                String text = ((ChatMessage) message).getText();
                view.printMessage("CHAT >> "+ text);
            }
            case MY_MOVE_REQUEST ->
            {
                view.printAll(clientModel);
                view.askMove();
            }
            case AFTER_MOVE_NEGATIVE ->
            {
                MessageAfterMoveNegative msg = (MessageAfterMoveNegative) message;
                view.printMessage(msg.getInvalidMessage());
                view.askMove();
            }
            case WINNER ->
            {
                MessageWinner msg = (MessageWinner) message;
                view.printPoints(clientModel);
                view.printMessage("The game is ended\nYour points: "+msg.getMyPoints()+"\nWinner: "+msg.getWinner());
            }
            case ALMOST_OVER ->
            {
                view.printMessage("A player has completed his library, last turn concluding");
            }
            case ERROR ->
            {
                ErrorMessage msg = (ErrorMessage) message;
                view.printMessage(msg.getString());
                System.exit(0);
            }
            case COMMON_GOAL -> {
                clientModel.addPoint((MessageCommonGoal) message);
            }
            case AFTER_MOVE_POSITIVE ->
            {
                view.printMessage("Move performed successfully");
                if(((MessageAfterMovePositive) message).getGainedPointsFirstCard()>0){
                    view.printMessage("Points gained from first common goal: " + ((MessageAfterMovePositive) message).getGainedPointsFirstCard());
                }
                if(((MessageAfterMovePositive) message).getGainedPointsSecondCard()>0){
                    view.printMessage("Points gained from second common goal: " + ((MessageAfterMovePositive) message).getGainedPointsSecondCard());
                }
            }
            case INIT_PLAYER_MESSAGE -> {
                clientModel.addPlayer(((MessageInitPlayer) message).getPlayer());
            }
            case UPDATE_GRID_MESSAGE ->
                    clientModel.setGrid(((MessageGrid) message).getGrid());
            case UPDATE_LIBRARY_MESSAGE -> {
                MessageLibrary msg = (MessageLibrary) message;
                clientModel.setLibrary(msg.getOwnerOfLibrary(), msg.getLibrary());
            }case INIT_COMMON_GOAL -> {
            MessaggeInitCommondGoal msg = (MessaggeInitCommondGoal) message;
            clientModel.setDescriptionFirstCommonGoal(msg.getDescription1());
            clientModel.setDescriptionSecondCommonGoal(msg.getDescription2());
        }
            case INIT_PERSONAL_GOAL -> {
                MessagePersonalGoal msg = (MessagePersonalGoal) message;
                clientModel.setPersonalGoalCard(msg.getGoalVector());
            }
            case RETURN_TO_OLD_GAME_MESSAGE -> {
                view.printMessage("You are joining in your old game");
            }
            case POINTS_MESSAGE -> {
                ArrayList<Couple<String, Integer>> list = ((MessagePoints) message).getList();
                for (Couple<String, Integer> stringIntegerCouple : list) {
                    clientModel.setPointToPlayer(stringIntegerCouple.getFirst(), stringIntegerCouple.getSecond());
                }
            }
            case GAME_IS_OVER -> {
                view.printMessage("Game is over");
            }
        }

    }


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
                    client.sendMessage(new MessageMove(message.getMove(), message.getColumn()));
                } catch (IOException e) {
                    //TODO capire cosa fare
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
