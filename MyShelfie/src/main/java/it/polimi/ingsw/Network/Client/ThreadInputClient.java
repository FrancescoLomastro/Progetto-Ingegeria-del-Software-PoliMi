package it.polimi.ingsw.Network.Client;

import it.polimi.ingsw.Network.Messages.*;
import it.polimi.ingsw.model.Utility.Position;
import java.io.IOException;
import java.util.Scanner;
/**
 * This class is used to catch input from client/user. In this way class "client" can handle other message
 * */
public class ThreadInputClient implements Runnable{
    private String messageFromCLI;
    private final Client client;
    private final MessageType command;
    private final Message message;
    /**Constructor
     * @author: Riccardo Figini
     * @param message message
     * @param client Class client (used to send message)
     * @param command*/
    public ThreadInputClient(Client client, MessageType command, Message message){
        messageFromCLI=null;
        this.client=client;
        this.command=command;
        this.message=message;
    }
    /**Switch case commad
     * @author: Riccardo Figini*/
    @Override
    public void run() {
        switch(command){
            case INVALID_USERNAME_MESSAGE -> askName();
            case MY_MOVE_REQUEST -> askMove();
            case PLAYER_NUMBER_REQUEST -> askNumberOfPlayer();
        }
    }
    /**Ask Player to insert number of player
    * @author: Riccardo Figini*/
    private void askNumberOfPlayer() {
        PlayerNumberRequest msg = (PlayerNumberRequest) message;
        Scanner scanner = new Scanner(System.in);
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
    /**Ask player a move
     * @author: Riccardo Figini*/
    private void askMove()  {
        int n;
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
    }
    /**
     * Ask name
     * @author: Riccardo Figini*/
    private void askName(){
        Scanner scanner = new Scanner(System.in);
        messageFromCLI = scanner.nextLine().trim();
        try {
            client.sendMessage(new RMILoginMessage(messageFromCLI));
        }
        catch (IOException e){
            System.out.println("Something goes wrong, " + e);
        }
    }
    /**
     * It allows good input for a move
     * @author: Riccardo Figini
     * @return {@code Position[]} Return an array of position*/
    private Position[] manageTurn(){
        int n;
        Scanner scanner = new Scanner(System.in);
        messageFromCLI = scanner.nextLine().trim();
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
    /**It controls the input from user, type e limit of number inserted
     * @author: Riccardo Figini
     * @param limit Limit of integer inserted
     * @return {@code Integer} Inserted number*/
    private int goodFormat(int limit){
        int number;
        Scanner scanner = new Scanner(System.in);
        messageFromCLI = scanner.nextLine().trim();
        while (true) {
            try {
                number = Integer.parseInt(messageFromCLI);
                if(number<=0 || number>limit)
                    throw new Exception();
            } catch (Exception e) {
                System.out.println("That is not a good number! Try again...");
            }
        }
    }
}

