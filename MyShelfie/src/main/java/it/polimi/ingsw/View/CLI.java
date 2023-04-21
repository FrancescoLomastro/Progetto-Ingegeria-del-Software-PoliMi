package it.polimi.ingsw.View;


import it.polimi.ingsw.Network.Client.*;
import it.polimi.ingsw.Network.Client.RMI.RMI_Client;
import it.polimi.ingsw.Network.Client.Socket.Socket_Client;
import it.polimi.ingsw.Network.Messages.*;
import it.polimi.ingsw.model.Cards.ObjectCard;
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
    String input;
    Thread threadChat;
    private final String defaultAddress = "localhost";
    private final int defaultRMIPort = 9000;
    private final int defaultSocketPort = 8000;

    public CLI(){
        threadOutputClient = new ThreadOutputClient(this);
        clientObject = new ClientObject();
        scanner = new Scanner(System.in);
    }


    public static void main(String[] args) {
        new CLI().run();
    }


    @Override
    public void run() {
        System.out.println("Welcome to MyShelfie!!\n");
        createClient();
        client.connect();
    }


    private void createClient()
    {
        ConnectionMode connectionMode;
        String username, address;
        int port=0;
        System.out.println("Insert a username");
        username = scanner.nextLine().trim();

        connectionMode=askConnectionMode();
        System.out.println("Insert the server address [Default: '"+defaultAddress+"']");
        address = scanner.nextLine().trim();
        if(address.equals("")) address="localhost";


        while(true) {
            System.out.println("Insert a port number [Default-RMI: '"+defaultRMIPort+"'][Default-SOCKET: '"+defaultSocketPort+"']");
            input = scanner.nextLine().trim();
            if(!input.equals("")) {
                try {
                    port = Integer.valueOf(input);
                    break;
                } catch (NumberFormatException e) {
                    System.err.println("Please select enter a valid port number");
                }
            }
            else break;
        }
        switch (connectionMode)
        {
            case SOCKET ->
            {
                if(input.equals(""))
                {
                    port=defaultSocketPort;
                }
                try {
                    client = new Socket_Client(username, address, port, this);
                } catch (RemoteException e) {
                    throw new RuntimeException("Error creating Socket client"+e);
                }
            }
            case RMI ->
            {
                if(input.equals(""))
                {
                    port=defaultRMIPort;
                }
                try
                {
                    client = new RMI_Client(username, address, port,this);
                } catch (RemoteException e) {
                    throw new RuntimeException("Error creating RMI client"+e);
                }
            }
        }
    }


    private ConnectionMode askConnectionMode()
    {
        ConnectionMode connectionMode;
        System.out.println("Select the communication protocol you want to use\n[SOCKET,RMI]");
        while(true) {
            input = scanner.nextLine();
            try {
                connectionMode = ConnectionMode.valueOf(input);
                break;
            } catch (IllegalArgumentException e) {
                System.err.println("Unknown sign: '" + input+ "' you have to select an option between SOCKET and RMI");
            }
        }
        return connectionMode;
    }


    @Override
    public void update(Message message) {
        switch (message.getType())
        {
            case ACCEPTEDLOGIN_MESSAGE ->
            {
                threadOutputClient.printAString("Connection accepted, waiting for other players");
            }
            case PLAYERNUMBER_REQUEST ->
            {
                PlayerNumberRequest msg = (PlayerNumberRequest)message;
                askNumberOfPlayer(msg.getMinimumPlayers(), msg.getMaximumPlayers());
            }
            case LOBBYUPDATE_MESSAGE ->
            {
                LobbyUpdateMessage msg = (LobbyUpdateMessage) message;
                threadOutputClient.printAString("Currently in lobby: " + msg.getUsernames().size() + "/" + msg.getLimitOfPlayers() + " players.");
                threadOutputClient.printAString("Members: " + msg.getUsernames());
            }
            case INVALIDUSERNAME_MESSAGE ->
            {
                askNewName();
            }
            case NEW_GAME_SERVER_MESSAGE ->
            {
                NewGameServerMessage msg = (NewGameServerMessage) message;
                if(client instanceof RMI_Client c) {
                    c.changeServer(msg);
                }
                threadOutputClient.printAString("Server moved to a game");
                threadChat = new Thread(new ThreadChat(client,scanner));
                threadChat.start();
            }
            case START_GAME_MESSAGE ->
            {
                threadOutputClient.printAString("Game started");
            }
            case CHAT_MESSAGE ->
            {
                String text = ((ChatMessage) message).getText();
                threadOutputClient.printAString(text);
            }
            case MY_MOVE_REQUEST ->
            {
                askMove();
            }
            case AFTER_MOVE_NEGATIVE ->
            {
                MessageAfterMoveNegative msg = (MessageAfterMoveNegative) message;
                threadOutputClient.printAString(msg.getInvelidmessage());
                askMove();
            }
            case WINNER ->
            {
                MessageWinner msg = (MessageWinner) message;
                threadOutputClient.printAString("The game is ended\nYour points: "+msg.getMyPoints()+"\nWinner: "+msg.getWinner());
            }
            case ALMOST_OVER ->
            {
                threadOutputClient.printAString("A player has completed his library, last turn concluding");
            }
            case ERROR ->
            {
                ErrorMessage msg = (ErrorMessage) message;
                threadOutputClient.printAString(msg.getString());
            }
            case COMMON_GOAL ->
            {
                //bruttissimo
                MessageCommonGoal msg = (MessageCommonGoal) message;
                threadOutputClient.printAString(msg.getPlayer()+" completed common goal card number ");
                if(msg.getGainedPointsFirstCard()!=0)
                    threadOutputClient.printAString("1 gaining "+msg.getGainedPointsFirstCard()+" points");
                else
                    threadOutputClient.printAString("2 gaining "+msg.getGainedPointsSecondCard()+" points");
            }
            case AFTER_MOVE_POSITIVE ->
            {
                threadOutputClient.printAString("Move performed successfully");
            }
            case INIT_PLAYER_MESSAGE ->
            {
                clientObject.addPlayer(((MessageInitPlayer) message).getPlayer());
            }
            case UPDATE_GRID_MESSAGE ->
                    clientObject.setGrid(((MessageGrid) message).getGrid());
            case UPDATE_LIBRARY_MESSAGE ->
                    clientObject.setLibrary(((MessageLibrary) message).getPlayer(), ((MessageLibrary) message).getLibrary());
        }
    }

    private void printGrid(ObjectCard[][] grid) {
        for(int i = 0; i<grid.length;i++)
        {
            for (int j = 0; j<grid[j].length;j++)
            {
                System.out.println(grid[i][j]);
            }
        }
    }

    private void printlibrary(ObjectCard[][] library) {
        for(int i = 0; i<library.length;i++)
        {
            for (int j = 0; j<library[j].length;j++)
            {
                System.out.println(library[i][j]);
            }
        }
    }
    public ClientObject getClientObject() {
        return clientObject;
    }
    private void askNumberOfPlayer(int min, int max) {
        threadOutputClient.printAString("You are the first player, please insert the number of players: ");
        threadOutputClient.printAString("The number must be between " + min + " and " + max);
        int value;
        while(true)
        {
            input = scanner.nextLine().trim();
            try {
                value = Integer.parseInt(input);
                if(value<min || value> max)
                {
                    threadOutputClient.printAString("You entered an invalid number of players. Please retry.");
                }
                break;
            }
            catch (NumberFormatException e)
            {
                threadOutputClient.printAString("You didn't insert a number. Please retry.");
            }
        }
        try {
            client.sendMessage(new PlayerNumberAnswer(client.getUsername(),value));
        } catch (IOException e) {
            throw new RuntimeException("Couldn't contact the server"+e);
        }
    }
    private void askNewName()
    {
        threadOutputClient.printAString("The username is already used, try later or change username: ");
        client.changeUsername(scanner.nextLine());
    }
    private Position[] askPositions()
    {
        int numberOfCards;
        threadOutputClient.printAString("How many card do you want? (minimum 1, max 3)");
        numberOfCards = getNumberOfCards(3);
        Position[] positions = new Position[numberOfCards];
        threadOutputClient.printAString("Now draw");
        for(int i=0; i<numberOfCards; i++){
            threadOutputClient.printAString("Card number " + i);
            threadOutputClient.printAString("Row: ");
            numberOfCards= getNumberOfCards(10)-1;
            positions[i].setRow(numberOfCards);
            threadOutputClient.printAString("Column: ");
            numberOfCards= getNumberOfCards(10)-1;
            positions[i].setColumn(numberOfCards);
        }
        return positions;
    }
    private int getNumberOfCards(int limit)
    {
        int number;
        input = scanner.nextLine().trim();
        while (true) {
            try {
                number = Integer.parseInt(input);
                if(number<=0 || number>limit)
                    throw new NumberFormatException();
                return number;
            } catch (NumberFormatException e) {
                System.out.println("That is not a good number! Try again...");
            }
        }
    }

    private void askMove()  {
        int column;
        threadChat.interrupt();

        threadOutputClient.printAString("It's your turn, please make your move");
        Position[] position = askPositions();
        MessageMove reMessage = new MessageMove();
        reMessage.setMove(position);
        threadOutputClient.printAString("In which column do you want insert this cards?");
        column= getNumberOfCards(5)-1;
        reMessage.setColumn(column);
        try {
            client.sendMessage(reMessage);
        }
        catch (Exception e){
            throw new RuntimeException("Unable to send message");
        }
        threadChat.start();
    }
}
