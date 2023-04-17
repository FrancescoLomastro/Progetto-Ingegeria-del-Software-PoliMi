package it.polimi.ingsw.controller;

import org.example.Messages.*;
import org.example.Servers.Connection;
import org.example.Servers.RMI.RMIShared;

import java.io.IOException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameController implements Runnable, ServerReceiver {
    private Map<String, Connection> clients;
    private int limitOfPlayers;
    private final String serverNameRMI;
    private final int portServerRMI=9000;
    public GameController(int numberOfGame) {
        clients= new HashMap<>();
        serverNameRMI="ServerGame"+numberOfGame;

    }
    public int getSize()
    {
        return clients.size();
    }
    public boolean isRegistered(String username)
    {
        return clients.keySet().contains(username);
    }

    public int getLimitOfPlayers() {
        return limitOfPlayers;
    }

    public void setLimitOfPlayers(int value) {
        limitOfPlayers=value;
    }

    public void addPlayer(String username, Connection connection) {

        clients.put(username,connection);
        List<String> usernames = clients.keySet().stream().toList();
        clients.values().forEach(x->
        {
            try {
                x.sendMessage(new LobbyUpdateMessage(usernames,limitOfPlayers));
            } catch (IOException e) {

                throw new RuntimeException(e);
            }
        });
    }

    public void newServerMessages(){
        for(Map.Entry<String, Connection> entry : clients.entrySet()){
            try {

                entry.getValue().sendMessage(new NewGameServerMessage(serverNameRMI, portServerRMI, this));
            } catch (IOException e) {

                throw new RuntimeException(e);
            }
        }
    }

    public void startGameMessages(){
        for(Map.Entry<String, Connection> entry : clients.entrySet()){

            try {

                entry.getValue().sendMessage(new MessageGame(MessageType.START_GAME_MESSAGE));
            } catch (IOException e) {

                throw new RuntimeException(e);
            }
        }
    }
    @Override
    synchronized public void onMessage(Message message) {

    }

    @Override
    public void run() {
        try {
            RMIShared gameShared = new RMIShared(this);
            Registry registry = LocateRegistry.getRegistry(portServerRMI);
            registry.bind(serverNameRMI, gameShared);
        }
        catch (Exception e){

            throw new RuntimeException(e);
        }
        newServerMessages();
        startGameMessages();

        /////////////////////////
        try {
            Thread.sleep(60000);
        } catch (InterruptedException e) {

            throw new RuntimeException(e);
        }
        startGameMessages();
        /////////////////////////
    }

    public void login(String username, Connection connection) {

        for(Map.Entry<String, Connection> entry : clients.entrySet()){

            if(entry.equals(username)){

                entry.setValue(connection);
            }
        }
    }
}
