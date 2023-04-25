package it.polimi.ingsw.controller;

import it.polimi.ingsw.Network.Messages.*;
import it.polimi.ingsw.Network.Servers.Connection;
import it.polimi.ingsw.Network.Servers.RMI.RMIShared;
import it.polimi.ingsw.model.Game;

import java.io.IOException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * the controller of a specific game
 * @author Andrea Ferrini
 * */
public class GameController implements Runnable, ServerReceiver {
    private Game game;
    private TurnController turnController;
    private int gameId;
    private Map<String, Connection> clients;
    private int limitOfPlayers;
    private final String serverNameRMI;
    private final int portServerRMI=9000;

    private int sendMessageAll =0;
    private int sendMessageToSpecifica=0;

    /**
     * constructor
     * @param gameId : identifies the game that game controller is controlling
     * */
    public GameController(int gameId) {
        this.clients= new HashMap<>();
        this.gameId = gameId;
        this.serverNameRMI="ServerGame"+gameId;
    }



    /**
     * this method adds a player in this game
     * @param username : the player's username
     * @param connection : the player's connection
     * */
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



    /**
     * implementation of the method run, in Runnable interface
     * */
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
        initGame();
        System.out.println("Inizializzata partita e mandato il messaggio");
    }



    /**
     * sends a message to all the players
     * */
    public void newServerMessages(){
        for(Map.Entry<String, Connection> entry : clients.entrySet()){
            try {
                entry.getValue().sendMessage(new NewGameServerMessage(serverNameRMI, portServerRMI, this));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }



    /**
     * initialization of a game
     * */
    public void initGame(){
        // inizializzo il game
        try {
            game = new Game(clients.size() , gameId);
        }catch(IOException e){
            throw new RuntimeException("Error" + e);
        }

        //inizializzo i giocatori nel game
        for (Map.Entry<String, Connection> entry : clients.entrySet()) {
            String key = entry.getKey();
            game.setNextPlayer(key);
        }
        this.turnController = new TurnController(game, this);
    }











    /**
     * sends the start game message
     * */
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
        switch (message.getType()){
            case MY_MOVE_ANSWER -> turnController.startTheTurn((MessageMove) message);
            case CHAT_MESSAGE -> notifyAllMessage(message);
        }
    }




/*
    public void login(String username, Connection connection) {

        for(Map.Entry<String, Connection> entry : clients.entrySet()){

            if(entry.equals(username)){
                entry.setValue(connection);
            }
        }
    }
*/
    /**
     * it sends a message to all the users in the game
     * @param message : the message to send
     * */
    public void notifyAllMessage(Message message){
        System.out.println(sendMessageAll + ") Message send all, Type: " + message.getType());
        sendMessageAll++;
        for(Map.Entry<String, Connection> entry : clients.entrySet()){
            try {
                Connection value = entry.getValue();
                value.sendMessage(message);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * it sends a message to a specific user
     * @param message : the message to send
     * @param username : the username who will receive that message
     * */
    public void sendMessageToASpecificUser(Message message, String username){
        System.out.println(sendMessageToSpecifica + ") Message send specific, Type: " + message.getType());
        sendMessageToSpecifica++;
        try {
            clients.get(username).sendMessage(message);
        }
        catch (Exception e){
            System.out.println("Error in sending message" + e);
        }
    }








    public int getLimitOfPlayers()
    {
        return limitOfPlayers;
    }



    public void setLimitOfPlayers(int value)
    {
        limitOfPlayers=value;
    }



    /**
     * @return the size of the hashmap that contains the players
     * */
    public int getSize()
    {
        return clients.size();
    }



    /**
     * @param username : a player's username
     * @return true if this player is registered in this game
     * */
    public boolean isRegistered(String username)
    {
        return clients.keySet().contains(username);
    }
}
