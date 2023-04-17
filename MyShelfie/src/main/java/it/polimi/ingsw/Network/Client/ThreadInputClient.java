package it.polimi.ingsw.Network.Client;


import java.io.IOException;
import java.util.Scanner;

public class ThreadInputClient implements Runnable{
    private String messageFromCLI;
    private final Client client;
    private MessageType command;
    private Message message;

    public ThreadInputClient(Client client, MessageType command, Message message){
        messageFromCLI=null;
        this.client=client;
        this.command=command;
        this.message=message;
    }
    @Override
    public void run() {
        switch(command){
            case INVALIDUSERNAME_MESSAGE -> askName();
            case MY_MOVE_REQUEST -> askMove();
            case PLAYERNUMBER_REQUEST -> askNumberOfPlayer();
        }
    }

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

    private void askMove() {
        System.out.println("It's your turn, next input has to be your move. Please use #move command: ");
        Scanner scanner = new Scanner(System.in);
        messageFromCLI = scanner.nextLine().trim();
        manageTurn();
        try {
            client.sendMessage(new MessageMove(translateIntoNumber()));
        }
        catch (IOException e){
            System.out.println("Something goes wrong, " + e);
        }
    }


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

    private Position[] translateIntoNumber() {
        return null;
    }

    private void manageTurn(){
        while(!messageFromCLI.startsWith("#move") || !goodFormat()){
            System.out.print("Format error, please enter again:");
            Scanner scanner = new Scanner(System.in);
            messageFromCLI = scanner.nextLine().trim();
        }
    }

    private boolean goodFormat(){
        //TODO decidere il formato
        return true;
    }

    private boolean isANumber(char charAt) {
        return charAt>'0' && charAt < '9';
    }

}

