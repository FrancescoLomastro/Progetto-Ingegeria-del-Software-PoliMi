package it.polimi.ingsw.View;


import it.polimi.ingsw.Network.Client.*;
import it.polimi.ingsw.Network.Client.RMI.RMI_Client;
import it.polimi.ingsw.Network.Client.Socket.Socket_Client;
import it.polimi.ingsw.Network.Messages.*;
import it.polimi.ingsw.model.Utility.ConnectionMode;
import it.polimi.ingsw.model.Utility.Position;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Scanner;

public class CLI implements View,Runnable {
    private Client client;
    ClientObject clientObject;
    ThreadOutputClient threadOutputClient;
    Scanner scanner;
    Thread threadChat;
    public CLI(){
        threadOutputClient = new ThreadOutputClient(this);
        clientObject = new ClientObject();
        scanner = new Scanner(System.in);
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
                    askMove();
            case UPDATE_GRID_MESSAGE ->
                clientObject.setGrid(((MessageGrid) message).getGrid());
            case UPDATE_LIBRARY_MESSAGE ->
                clientObject.setLibrary(((MessageLibrary) message).getPlayer(), ((MessageLibrary) message).getLibrary());
            case ACCEPTEDLOGIN_MESSAGE ->
                threadOutputClient.printAString("Connection accepted, waiting for other players");
            case INVALIDUSERNAME_MESSAGE ->
                    askName();
            case PLAYERNUMBER_REQUEST ->
                    askNumberOfPlayer(message);
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
                threadChat = new Thread(new ThreadChat(client,scanner));
                threadChat.start();
            }
            case CHAT_MESSAGE -> {
                String text = ((ChatMessage) message).getText();
                System.out.println(text);
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
    private void askNumberOfPlayer(Message message) {
        PlayerNumberRequest msg = (PlayerNumberRequest) message;
        threadOutputClient.printAString("You are the first player, please insert the number of players: ");
        threadOutputClient.printAString("The number must be between " + msg.getMinimumPlayers() + " and " + msg.getMaximumPlayers());
        String input;
        int value;
        while(true)
        {
            input = scanner.nextLine().trim();
            try {
                value = Integer.parseInt(input);
                if(value<msg.getMinimumPlayers() || value> msg.getMaximumPlayers())
                {
                    System.out.println("You entered an invalid number of players. Please retry.");
                }
                break;
            }
            catch (NumberFormatException e)
            {
                System.out.println("You didn't insert a number. Please retry.");
            }
        }
        try {
            client.sendMessage(new PlayerNumberAnswer(client.getUsername(),value));
        } catch (IOException e) {
            throw new RuntimeException("Couldn't contact the server"+e);
        }
    }
    private void askName(){
        String input = scanner.nextLine().trim();
        threadOutputClient.printAString("The username is already used, try later or change username: ");
        try {
            client.sendMessage(new RMILoginMessage(input));
        }
        catch (IOException e){
            System.out.println("Something goes wrong, " + e);
        }
    }
    private Position[] manageTurn(){
        int n;
        Scanner scanner = new Scanner(System.in);
        //messageFromCLI = scanner.nextLine().trim();
        System.out.println("How many card do you want? (minimum 1, max 3)");
        n=goodFormat(3);
        Position[] positions = new Position[n];
        System.out.println("Now chose object card. ");
        for(int i=0; i<n; i++){
            System.out.println("Card number: " + i);
            System.out.print("Row: ");
            n=goodFormat(10);
            positions[i].setRow(n);
            System.out.print("Column: ");
            n=goodFormat(10);
            positions[i].setColumn(n);
        }
        return positions;
    }
    private int goodFormat(int limit){
        int number;
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().trim();
        while (true) {
            try {
                number = Integer.parseInt(input);
                if(number<=0 || number>limit)
                    throw new Exception();
            } catch (Exception e) {
                System.out.println("That is not a good number! Try again...");
            }
        }
    }
    private void askMove()  {
        int n;
        //Interrompi threadh chat
        threadChat.interrupt();

        System.out.println("It's your turn, next input has to be your move.");
        Position[] position = manageTurn();
        MessageMove reMessage = new MessageMove();
        reMessage.setMove(position);
        System.out.println("In which column do you want insert new cards?");
        n=goodFormat(5);
        reMessage.setColumn(n);
        try {
            client.sendMessage(reMessage);
        }
        catch (Exception e){
            System.out.println("Impossible to send message! " + e);
        }
        //Attiva thread chat
        threadChat.start();
    }
}
