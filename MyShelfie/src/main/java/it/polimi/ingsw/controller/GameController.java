package it.polimi.ingsw.controller;

import it.polimi.ingsw.Network.Messages.*;
import it.polimi.ingsw.Network.Servers.Connection;
import it.polimi.ingsw.Network.Servers.RMI.RMIShared;
import it.polimi.ingsw.model.Game;

import java.io.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.*;

/**
 * the controller of a specific game
 * @author Andrea Ferrini
 * */
public class GameController implements Runnable, ServerReceiver, Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m";
    private Game game;
    private TurnController turnController;
    private final int gameId;
    private Map<String, Connection> clients;
    private int limitOfPlayers;
    private final String serverNameRMI;
    private final int portServerRMI=9000;
    private String gameFilePath;
    private int sendMessageAll =0;
    private int sendMessageToSpecifica=0;
    ArrayList<String> testArray = new ArrayList<>();
    private boolean inGame;
    /**
     * constructor
     * @param gameId : identifies the game that game controller is controlling
     * */
    public GameController(int gameId) {
        this.clients= new LinkedHashMap<>();
        this.gameId = gameId;
        this.serverNameRMI="ServerGame"+gameId;
    }
    public void reloadPlayer(){
        clients = new LinkedHashMap<>();
    }
    /**
     * this method adds a player in this game
     * @param username : the player's username
     * @param connection : the player's connection
     * */
    public void addPlayer(String username, Connection connection) {
        clients.put(username,connection);
        testArray.add(username);
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
        initGameFile();
        System.out.println("Inizializzata partita e mandato il messaggio");
    }

    private void initGameFile() {
        gameFilePath = "src/main/resources/gameFile/"+serverNameRMI+".bin";
        try {
            FileOutputStream file = new FileOutputStream(gameFilePath);
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(this);
            out.close();
        } catch (IOException e) {
            System.out.println("Initialization of game file has problems, " + e);
        }
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
            game = new Game(clients.size());
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
        System.out.println(ANSI_GREEN + "Message has arrived: " + message.getType() + ", " + message.getUsername() + ANSI_RESET);
        switch (message.getType()){
            case MY_MOVE_ANSWER -> turnController.startTheTurn((MessageMove) message);
            case CHAT_MESSAGE -> notifyAllMessage(message);
        }
    }
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
            if(message.getType() == MessageType.AFTER_MOVE_POSITIVE)
                updateFile();
            clients.get(username).sendMessage(message);
        }
        catch (Exception e){
            System.out.println("Error in sending message" + e);
        }
    }
    /**Save Game data on a file
     * @author: Riccardo Figini
     * */
    private void updateFile() {
        try {
            FileOutputStream file = new FileOutputStream(gameFilePath);
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(this);
            out.close();
            System.out.println("Memory updated");
        } catch (IOException e) {
            System.out.println("Updating file error, " + e);
        }
    }

    /**It returns an arrayList with name of player
     * @author: Riccardo Figini
     * @return {@code ArrayList<String>} List of player
     * */
    public ArrayList<String> getNameOfPlayer(){
        ArrayList<String> s = new ArrayList<>();
        /*
        for(Map.Entry<String, Connection> entry : clients.entrySet()){
            String tmp = entry.getKey();
            s.add(tmp);
        }
        */
        return testArray;
    }
    public int getGameId() {
        return gameId;
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

    public void restartGameAfterReload(){
        String player;
        setInGame(true);
        setUpOldServerGame();
        newServerMessages();
        turnController.initClientObjectInPlayer();
        player = turnController.getPlayerAfterReload();
        sendMessageToASpecificUser(new MessageMove(), player);
    }

    private void setUpOldServerGame() {
        try {
            RMIShared gameShared = new RMIShared(this);
            Registry registry = LocateRegistry.getRegistry(portServerRMI);
            registry.bind(serverNameRMI, gameShared);
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public boolean isInGame() {
        return inGame;
    }

    public void setInGame(boolean inGame) {
        this.inGame = inGame;
    }
}
