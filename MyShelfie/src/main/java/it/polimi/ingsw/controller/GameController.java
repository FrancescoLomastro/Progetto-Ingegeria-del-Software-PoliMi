package it.polimi.ingsw.controller;

import it.polimi.ingsw.Network.Messages.*;
import it.polimi.ingsw.Network.Servers.CentralServer;
import it.polimi.ingsw.Network.Servers.Connection;
import it.polimi.ingsw.Network.Servers.PingTaskServer;
import it.polimi.ingsw.Network.Servers.RMI.RMIShared;
import it.polimi.ingsw.Network.StatusNetwork;
import it.polimi.ingsw.model.Game;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.rmi.NoSuchObjectException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
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
    private transient Map<String, Connection> clients;
    private int limitOfPlayers;
    private final String serverNameRMI;
    private String gameFilePath;
    private int sendMessageAll =0;
    private int sendMessageToSpecific =0;
    private final ArrayList<String> testArray = new ArrayList<>();
    private transient Controller controller;
    private boolean gameInGame;
    private transient RMIShared gameShared;
    private int PING_TIMEOUT = 10000;

    public void startTimer(String username,Connection connection,ServerReceiver server)
    {
        connection.startTimer(PING_TIMEOUT,server);
        try {
            connection.sendMessage(new ServerPingMessage(username));
        } catch (IOException e) {
            tryToDisconnect(connection, username);
        }
    }
    public void renewTimer(String username)
    {
        Connection conn= clients.get(username);
        conn.resetTimer(PING_TIMEOUT,this);
        try {
            conn.sendMessage(new ServerPingMessage(username));
        } catch (IOException e) {
            tryToDisconnect(clients.get(username), username);
        }
    }
    /**
     * constructor
     * @param gameId : identifies the game that game controller is controlling
     * */
    public GameController(int gameId, Controller controller) {
        this.clients= new LinkedHashMap<>();
        this.gameId = gameId;
        this.serverNameRMI="ServerGame"+gameId;
        this.controller = controller;
        gameInGame=false;
    }
    public void reloadPlayerCreatingNewMap(Controller controller){
        this.controller = controller;
        clients = new LinkedHashMap<>();
    }
    /**
     * this method adds a player in this game
     * @param username : the player's username
     * @param connection : the player's connection
     * */
    public void addPlayer(String username, Connection connection) {
        clients.put(username,connection);
        if(game == null || (testArray.size() != game.getNumPlayers()))
            testArray.add(username);
        List<String> usernames = clients.keySet().stream().toList();
        clients.values().forEach(x->
        {
            try {
                x.sendMessage(new LobbyUpdateMessage(usernames,limitOfPlayers, "New player join"));
            } catch (IOException e) {
                tryToDisconnect(connection, username);
            }
        });
    }
    /**
     * implementation of the method run, in Runnable interface
     * */
    @Override
    public void run() {
        try {
            gameShared = new RMIShared(this);
            Registry registry = LocateRegistry.getRegistry(CentralServer.rmiPort);
            registry.bind(serverNameRMI, gameShared);
        }
        catch (Exception e){
            System.out.println(Controller.ANSI_BLU + "Impossible to allocate server game. Game will be closed" + ANSI_RESET);
            gameNeedToBeClosed("Server cannot create new server for game");
        }
        newServerMessages();
        initGame();
        initGameFile();
        System.out.println("Inizializzata partita e mandato il messaggio");
        controller.changeStatusToEveryone(StatusNetwork.IN_GAME, this);
        gameInGame=true;
    }

    private void initGameFile() {
        gameFilePath = "src/main/resources/gameFile/"+serverNameRMI+".bin";
        try {
            FileOutputStream file = new FileOutputStream(gameFilePath);
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(this);
            out.close();
        } catch (IOException e) {
            System.out.println(Controller.ANSI_BLU + "Initialization of game file has problems, " + e + ANSI_RESET);
            gameNeedToBeClosed("Game's file cannot be created, game will be closed");
        }
    }

    /**
     * sends a message to all the players
     * */
    public void newServerMessages(){
        for(Map.Entry<String, Connection> entry : clients.entrySet()){
            try {
                entry.getValue().sendMessage(new NewGameServerMessage(serverNameRMI, CentralServer.rmiPort, this));
            } catch (IOException e) {
                System.out.println(Controller.ANSI_BLU + "Error in sending message " + e + ANSI_RESET);
                gameNeedToBeClosed(entry.getKey(), entry.getKey()+ " has some problem, game will be closed and delete");
            }
        }
    }

    @Override
    synchronized public void onMessage(Message message) {
        System.out.println(ANSI_GREEN + "Message has arrived: " + message.getType() + ", " + message.getUsername() + ANSI_RESET);
        switch (message.getType()){
            case MY_MOVE_ANSWER -> turnController.startTheTurn((MessageMove) message);
            case CHAT_MESSAGE -> notifyAllMessage(message);
            case PING_MESSAGE -> {
                String username = message.getUsername();
                renewTimer(username);
            }
        }
    }
    /**
     * It manages error that can came from network
     * @author: Riccardo Figini
     * @param connection Connection that raise a problem
     * @param playerName player that causes the problem
     * */
    @Override
    public void tryToDisconnect(Connection connection, String playerName) {
        switch (connection.getStatusNetwork()){
            case SEND_ERROR_MESSAGE_CLIENT_NEED_TO_BE_CLOSED -> {
                System.out.println(Controller.ANSI_BLU + "Problem in contacting " + playerName + " during closing, droping the message..." + ANSI_RESET);
            }
            case IN_GAME, NEW_GAME_IS_STARTING -> {
                System.out.println(Controller.ANSI_BLU + "Closing game for some problem..." + ANSI_RESET);
                gameNeedToBeClosed(playerName,"Problem with a player, game will be closed and destroy");
            }
            case END_OF_THE_GAME -> {
                System.out.println(Controller.ANSI_BLU +"Player left server, game is finished correctly"+ ANSI_RESET);
            }
            default -> {
                System.out.println(Controller.ANSI_BLU + "Somethings goes wrong with state of network. Game will be closed" + ANSI_RESET);
                System.out.println(Controller.ANSI_BLU + "Player: "+ playerName + ", Status:  " + connection.getStatusNetwork() + ANSI_RESET);
                gameNeedToBeClosed(playerName,"Problem with a player, game will be closed and destroy");
            }
        }
    }
    /**It is called when occurs an error and game need to be closed
     * @author: Riccardo Figini
     * @param message Message with motivation of error
     * */
    private void gameNeedToBeClosed(String message){
        controller.changeStatusToEveryone(StatusNetwork.SEND_ERROR_MESSAGE_CLIENT_NEED_TO_BE_CLOSED, this);
        controller.destroyGame(message, this);
    }
    /**It is called when occurs an error and game need to be closed. It sends an warn message to every one in the lobby
     * except the player passed
     * @author: Riccardo Figini
     * @param message Message with motivation of error
     * @param player Player that left the game, it will not receive the error message
     * */
    private void gameNeedToBeClosed(String message, String player){
        controller.changeStatusToEveryone(StatusNetwork.SEND_ERROR_MESSAGE_CLIENT_NEED_TO_BE_CLOSED, this);
        controller.destroyGame(player, message, this);
    }
    /**
     * initialization of a game
     * @author: Riccardo Figini
     * */
    public void initGame(){

        try {
            game = new Game(clients.size());
        }catch(IOException e){
            System.out.println(Controller.ANSI_BLU + "Error in game creation" + e + ANSI_RESET);
            gameNeedToBeClosed("Game has some problem in allocation, game will be closed");
        }

        //inizializzo i giocatori nel game
        for (Map.Entry<String, Connection> entry : clients.entrySet()) {
            String key = entry.getKey();
            game.setNextPlayer(key);
        }

        this.turnController = new TurnController(game, this);
    }

    /**
     * it sends a message to all the users in the game
     *  @author: Riccardo Figini
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
                tryToDisconnect(clients.get(entry.getKey()), entry.getKey());
            }
        }
    }
    /**
     * it sends a message to a specific user
     * @param message : the message to send
     * @param username : the username who will receive that message
     * */
    public void sendMessageToASpecificUser(Message message, String username){
        System.out.println(sendMessageToSpecific + ") Message send specific, Type: " + message.getType() + ", " + username);
        sendMessageToSpecific++;
        try {
            clients.get(username).sendMessage(message);
        }
        catch (Exception e){
            tryToDisconnect(clients.get(username), username);
        }
    }
    /**Save Game data on a file
     * @author: Riccardo Figini
     * */
    public void updateFile() {
        try {
            FileOutputStream file = new FileOutputStream(gameFilePath);
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(this);
            out.close();
            System.out.println("Memory updated");
        } catch (IOException e) {
            System.out.println(Controller.ANSI_BLU + "Updating file error, " + e + ANSI_RESET);
            System.out.println(Controller.ANSI_BLU + "This turn is not saved" + ANSI_RESET);
        }
    }

    /**It returns an arrayList with name of player
     * @author: Riccardo Figini
     * @return {@code ArrayList<String>} List of player
     * */
    public ArrayList<String> getNamesOfPlayer(){
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
    public int getSizeArrayConnection()
    {
        return clients.size();
    }
    /**It initialized game (model and client) when a game is restarted
     *  @author: Riccardo Figini
     * */
    public void restartGameAfterReload(){
        String player;
        setUpOldServerGame();
        newServerMessages();
        notifyAllMessage(new MessageGame(MessageType.START_GAME_MESSAGE));
        turnController.initClientObjectInPlayer();
        for(Map.Entry<String, Connection> entry: clients.entrySet()){
            entry.getValue().setPlayerName(entry.getKey());
            entry.getValue().setStatusNetwork(StatusNetwork.IN_GAME);
        }
        player = turnController.getPlayerAfterReload();
        sendMessageToASpecificUser(new MessageMove(), player);
    }
    /**Set server game when a game is restarted
     *  @author: Riccardo Figini
     * */
    private void setUpOldServerGame() {
        try {
            gameShared = new RMIShared(this);
            Registry registry = LocateRegistry.getRegistry(CentralServer.rmiPort);
            registry.bind(serverNameRMI, gameShared);
        }
        catch (Exception e){
            System.out.println((Controller.ANSI_BLU + "GameController can be loaded on registry " + e + ANSI_RESET));
            gameNeedToBeClosed("Game Controller server cannot be allocated");
        }
    }
    /**Delete file game when it is over (it can be called also when game is not over). Remove gameServer from registry
     *  @author: Riccardo Figini
     * */
    public void closeGame() {
        String path = gameFilePath;
        try {
            Files.delete(Paths.get(path));
            controller.deleteGameFromArrayContainer(gameId);
        } catch (IOException e) {
            System.out.println(Controller.ANSI_BLU + "File game can't be deleted, name cannot be used again" + ANSI_RESET);
        }
        controller.changeStatusToEveryone(StatusNetwork.END_OF_THE_GAME, this);
        try {
            UnicastRemoteObject.unexportObject(gameShared, true);
        } catch (NoSuchObjectException e) {
            System.out.println(Controller.ANSI_BLU + "Impossible to close server game "+ gameId + ANSI_RESET);
        }
        controller.removeGame(this);
    }
    /**
     * Remove player from lobby
     * @author: Riccardo Figini
     * @param name Name of player to be removed
     * */
    public void removePlayer(String name){
        for(int i=0; i<testArray.size(); i++) {
            if (testArray.get(i).equals(name)) {
                testArray.remove(i);
                break;
            }
        }
        clients.remove(name);
        if(!gameInGame)
            notifyAllMessage(new LobbyUpdateMessage(clients.keySet().stream().toList(),limitOfPlayers, "Players left"));
    }
}
