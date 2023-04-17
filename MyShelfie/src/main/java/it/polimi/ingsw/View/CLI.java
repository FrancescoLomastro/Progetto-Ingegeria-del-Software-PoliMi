package it.polimi.ingsw.View;


import it.polimi.ingsw.Network.Client.Client;
import it.polimi.ingsw.Network.Client.ClientObject;
import it.polimi.ingsw.Network.Client.RMI.RMI_Client;
import it.polimi.ingsw.Network.Client.Socket.Socket_Client;
import it.polimi.ingsw.Network.Client.ThreadInputClient;
import it.polimi.ingsw.Network.Client.ThreadOutputClient;
import it.polimi.ingsw.Network.Messages.*;
import it.polimi.ingsw.model.Utility.ConnectionMode;

import java.rmi.RemoteException;
import java.util.Scanner;

public class CLI implements View,Runnable {
    private Client client;
    ClientObject clientObject;
    ThreadOutputClient threadOutputClient;
    public CLI(){
        threadOutputClient = new ThreadOutputClient(this);
        clientObject = new ClientObject();
    }
    @Override
    public void update(Message message) {
        switch (message.getType()) {
            case INIT_PLAYER_MESSAGE ->
                clientObject.addPlayer(((MessageInitPlayer) message).getPlayer());
            case START_GAME_MESSAGE -> {
                threadOutputClient.initGame();
               // client.setStatus(Status.IN_GAME);
            }
            case MY_MOVE_REQUEST ->
                new Thread(new ThreadInputClient(client, MessageType.MY_MOVE_REQUEST, message)).start();
            case UPDATE_GRID_MESSAGE ->
                clientObject.setGrid(((MessageGrid) message).getGrid());
            case UPDATE_LIBRARY_MESSAGE ->
                clientObject.setLibrary(((MessageLibrary) message).getPlayer(), ((MessageLibrary) message).getLibrary());
            case ACCEPTEDLOGIN_MESSAGE ->
                threadOutputClient.printAString("Connection accepted, waiting for other players");
            case INVALIDUSERNAME_MESSAGE ->
            {
                threadOutputClient.printAString("The username is already used, try later or change username: ");
                new Thread(new ThreadInputClient(client, MessageType.INVALIDUSERNAME_MESSAGE, message)).start();
            }
            case PLAYERNUMBER_REQUEST -> {
                PlayerNumberRequest msg = (PlayerNumberRequest) message;
                threadOutputClient.printAString("You are the first player, please insert the number of players: ");
                threadOutputClient.printAString("The number must be between " + msg.getMinimumPlayers() + " and " + msg.getMaximumPlayers());
                new Thread(new ThreadInputClient(client, MessageType.PLAYERNUMBER_REQUEST, message)).start();
            }
            case LOBBYUPDATE_MESSAGE -> {
                LobbyUpdateMessage msg = (LobbyUpdateMessage) message;
                threadOutputClient.printAString("Currently in lobby: " + msg.getUsernames().size() + "/" + msg.getLimitOfPlayers() + " players.");
                threadOutputClient.printAString("Members: " + msg.getUsernames());
            }
            case NEW_GAME_SERVER_MESSAGE -> {
                NewGameServerMessage msg = (NewGameServerMessage) message;
                if(client instanceof RMI_Client c) {
                    c.changeServer(msg);
                }
                threadOutputClient.printAString("Server is changed, now you are connected to the game...Game will start soon");
            }
            case ERROR -> {
                ErrorMessage msg = (ErrorMessage) message;
                threadOutputClient.printAString(msg.getString());
            }
        }
    }

    public static void main(String[] args) {
        new CLI().run();
    }

    @Override
    public void run() {
        ConnectionMode cm;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to MyShelfie!!");
        System.out.println("Select the communication protocol you want to use\n[SOCKET,RMI]");
        String input;
        while(true) {
            input = scanner.nextLine();
            try {
                cm = ConnectionMode.valueOf(input);
                break;
            } catch (IllegalArgumentException e) {
                System.err.println("Unknown sign: " + input);
                System.err.println("Try again...");
            }
        }
        System.out.println("Insert a username");
        input = scanner.nextLine().trim();
        switch (cm)
        {
            case SOCKET ->
            {
                try {
                    client = new Socket_Client(input, "localhost", 8000, this);
                } catch (RemoteException e) {
                    throw new RuntimeException("Error creating Socket client"+e);
                }
            }
            case RMI ->
            {
                try {
                    client = new RMI_Client(input, "localhost", 9000,this);
                } catch (RemoteException e) {
                    throw new RuntimeException("Error creating RMI client"+e);
                }
            }
        }
        client.connect();

    }
    public ClientObject getClientObject() {
        return clientObject;
    }
}
