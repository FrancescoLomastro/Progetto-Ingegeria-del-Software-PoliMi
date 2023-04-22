package it.polimi.ingsw.controller;

import it.polimi.ingsw.Network.Client.*;
import it.polimi.ingsw.Network.Client.RMI.RMI_Client;
import it.polimi.ingsw.Network.Client.Socket.Socket_Client;
import it.polimi.ingsw.Network.Messages.*;
import it.polimi.ingsw.Network.ObserverImplementation.Observer;
import it.polimi.ingsw.View.*;
import it.polimi.ingsw.Network.Client.ClientModel;

import java.io.IOException;
import java.rmi.RemoteException;


public class ClientController implements Observer<View,Message> {

    private View view;
    private ClientModel clientModel;
    private MessageQueueHandler messageReceiver;
    private Client client;
    private String chosenUsername;
    private int chosenTechnology;
    private int chosenPort;
    private String chosenAddress;
    private final int rmiPort = 9000;
    private final int socketPort = 8000;

    public ClientController()
    {
        this.clientModel = new ClientModel();
        this.view = new Cli(clientModel);
        view.addObserver(this);
    }


    public void startView()
    {
        int defaultPort;
        chosenUsername=view.askUsername();
        chosenTechnology=view.askTechnology();
        chosenAddress=view.askAddress();
        if(chosenTechnology==0)
            defaultPort=rmiPort;
        else
            defaultPort=socketPort;
        chosenPort=view.askPort(chosenTechnology,defaultPort);
        createClient(chosenUsername,chosenTechnology,chosenAddress,chosenPort);
    }

    private void createClient(String chosenUsername, int chosenTechnology, String chosenAddress, int chosenPort) {
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
            new Thread(messageReceiver).start();
            client.connect();
        }catch (RuntimeException | RemoteException e) {
            throw new RuntimeException("It was impossible to create a client and contact " +
                    "the server at [" + chosenAddress + "," + this.chosenPort + "]\n" + e);
        }
    }

    //messaggi ricevuti dalla rete
    public void onMessage(Message message) {
        switch (message.getType())
        {
            case ACCEPTED_LOGIN_MESSAGE ->
            {
                view.printAString("Connection accepted, waiting for other players");
            }
            case PLAYER_NUMBER_REQUEST ->
            {
                int numberOfPlayers;
                PlayerNumberRequest msg = (PlayerNumberRequest)message;
                numberOfPlayers=view.askNumberOfPlayers(msg.getMinimumPlayers(), msg.getMaximumPlayers());
                try {
                    client.sendMessage(new PlayerNumberAnswer(client.getUsername(),numberOfPlayers));
                } catch (IOException e) {
                    throw new RuntimeException("Couldn't contact the server"+e);
                }
            }
            case LOBBY_UPDATE_MESSAGE ->
            {
                LobbyUpdateMessage msg = (LobbyUpdateMessage) message;
                view.printAString("Currently in lobby: " + msg.getUsernames().size() + "/" + msg.getLimitOfPlayers() + " players.");
                view.printAString("Members: " + msg.getUsernames());
            }
            case INVALID_USERNAME_MESSAGE ->
            {
                //Da finire
                //bisogna distruggere il client di prima perchè potrebbe aver creato dei thread di ascolto
                chosenUsername=view.onInvalidUsername();
                //createClient(chosenUsername,chosenTechnology,chosenAddress,chosenPort);
            }
            case NEW_GAME_SERVER_MESSAGE ->
            {
                NewGameServerMessage msg = (NewGameServerMessage) message;
                if(client instanceof RMI_Client c) {
                    c.changeServer(msg);
                }
                view.printAString("Server moved to a game");
                // threadChat = new Thread(new ThreadChat(client,scanner));
                //threadChat.start();
            }
            /*case START_GAME_MESSAGE ->
            {
                view.printAString("Game started");
                threadOutputClient.printAll(clientObject);
            }
            case CHAT_MESSAGE ->
            {
                String text = ((ChatMessage) message).getText();
                view.printAString("CHAT >> "+text);
            }
            case MY_MOVE_REQUEST ->
            {
                threadOutputClient.printAll(clientObject);
                askMove();
            }
            case AFTER_MOVE_NEGATIVE ->
            {
                MessageAfterMoveNegative msg = (MessageAfterMoveNegative) message;
                view.printAString(msg.getInvalidMessage());
                askMove();
            }
            case WINNER ->
            {
                MessageWinner msg = (MessageWinner) message;
                view.printAString("The game is ended\nYour points: "+msg.getMyPoints()+"\nWinner: "+msg.getWinner());
            }
            case ALMOST_OVER ->
            {
                view.printAString("A player has completed his library, last turn concluding");
            }
            case ERROR ->
            {
                ErrorMessage msg = (ErrorMessage) message;
                view.printAString(msg.getString());
            }
            case COMMON_GOAL ->
            {
                clientObject.addPoint((MessageCommonGoal) message);
            }
            case AFTER_MOVE_POSITIVE ->
            {
                view.printAString("Move performed successfully");
                if(((MessageAfterMovePositive) message).getGainedPointsFirstCard()>0){
                    view.printAString("Points gained from first common goal: " + ((MessageAfterMovePositive) message).getGainedPointsFirstCard());
                }
                if(((MessageAfterMovePositive) message).getGainedPointsSecondCard()>0){
                    view.printAString("Points gained from second common goal: " + ((MessageAfterMovePositive) message).getGainedPointsSecondCard());
                }
            }
            case INIT_PLAYER_MESSAGE -> {
                clientObject.addPlayer(((MessageInitPlayer) message).getPlayer());
            }*/
            case UPDATE_GRID_MESSAGE ->
                    clientModel.setGrid(((MessageGrid) message).getGrid());
            case UPDATE_LIBRARY_MESSAGE ->
                    clientModel.setLibrary(((MessageLibrary) message).getPlayer(), ((MessageLibrary) message).getLibrary());
            /*case INIT_COMMON_GOAL -> {
                MessaggeInitCommondGoal msg = (MessaggeInitCommondGoal) message;
                clientObject.setDescriptionFirstCommonGoal(msg.getDescription1());
                clientObject.setDescriptionSecondCommonGoal(msg.getDescription2());
            }
            case INIT_PERSONAL_GOAL -> {
                MessagePersonalGoal msg = (MessagePersonalGoal) message;
                clientObject.setPersonalGoalCard(msg.getGoalVector());
            }*/
        }
    }


    @Override
    public void update(View o, Message arg) {
        //servirà per la chat
    }
}
