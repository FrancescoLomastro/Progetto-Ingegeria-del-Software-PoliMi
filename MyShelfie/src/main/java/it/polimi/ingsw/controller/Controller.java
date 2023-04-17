package it.polimi.ingsw.controller;

import it.polimi.ingsw.Network.Messages.*;
import it.polimi.ingsw.Network.Servers.Connection;
import it.polimi.ingsw.model.Utility.Request;
import java.io.IOException;
import java.util.ArrayList;

public class Controller implements ServerReceiver
{
    private ArrayList<GameController> games;
    private GameController currentGame;
    private Request waitedRequest;
    private boolean isAsking;
    private final int minimumPlayers = 1;
    private final int maximumPlayers = 4;

    public Controller() {
        games = new ArrayList<>();
        currentGame= new GameController(games.size());
        waitedRequest=null;
        isAsking=false;
    }

     public void login(String username, Connection connection) {
            if (availableUsername(username)) {
                if (currentGame.getSize() == 0) {
                    if(isAsking)
                    {
                        try {
                            connection.sendMessage(new ErrorMessage("A lobby is being created from another user, please retry soon"));
                        } catch (IOException e) {

                            System.out.println("Couldn't ask number to the client " + username + ", dropping the request...");
                        }
                    }
                    else
                    {
                        waitedRequest = new Request(username, connection);
                        isAsking=true;
                        try {
                            connection.sendMessage(new PlayerNumberRequest(minimumPlayers, maximumPlayers));
                        } catch (IOException e) {

                            System.out.println("Couldn't ask number to the client " + username + ", dropping the request...");
                            waitedRequest = null;
                        }
                    }
                } else {
                    try {
                        connection.sendMessage(new AcceptedLoginMessage());
                        addPlayer(username, connection);
                    } catch (IOException e) {

                        System.out.println("Couldn't contanct client " + waitedRequest.getUsername());
                    }
                }
            } else {
                try {
                    connection.sendMessage(new InvalidUsernameMessage());
                } catch (IOException e) {

                    System.out.println("Problem contacting " + username + ", dropping the request...");
                    waitedRequest = null;
                }
            }

    }

     private void addPlayer(String username, Connection connection) {

            currentGame.addPlayer(username,connection);
            games.add(currentGame);
         if(currentGame.getSize()==currentGame.getLimitOfPlayers())
         {
             new Thread(currentGame).start();
             currentGame=new GameController(games.size());
        }
    }

    private boolean availableUsername(String username) {
        for(GameController gc : games)
        {
            if(gc.isRegistered(username))
            {
                return false;
            }
        }
        return true;
    }
    @Override
    synchronized public void onMessage(Message message) {

            MessageType type = message.getType();
            switch (type)
            {
                case LOGIN_REQUEST ->
                {
                    LoginMessage msg = (LoginMessage) message;
                    login(msg.getUsername(),msg.getClientConnection());
                }
                case PLAYERNUMBER_ANSWER ->
                {
                    if (waitedRequest.getUsername().equals(message.getUsername())) {

                        try {

                            waitedRequest.getConnection().sendMessage(new AcceptedLoginMessage());
                            PlayerNumberAnswer msg = (PlayerNumberAnswer) message;
                            currentGame.setLimitOfPlayers(msg.getPlayerNumber());
                            isAsking=false;
                            addPlayer(waitedRequest.getUsername(), waitedRequest.getConnection());
                        } catch (IOException e) {

                            System.out.println("Couldn't contact client " + waitedRequest.getUsername());
                        } finally {

                            waitedRequest = null;
                        }
                    }
                }
            }
    }

}
