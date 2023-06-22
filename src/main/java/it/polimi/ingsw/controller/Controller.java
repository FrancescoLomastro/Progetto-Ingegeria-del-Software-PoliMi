package it.polimi.ingsw.controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import it.polimi.ingsw.network.Messages.*;
import it.polimi.ingsw.network.Servers.Connection;
import it.polimi.ingsw.network.StatusNetwork;
import it.polimi.ingsw.utility.Request;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import static it.polimi.ingsw.controller.GameController.ANSI_RESET;

public class Controller implements ServerReceiver
{
    public final static String ANSI_BLU ="\u001B[34m ";
    public static final String ANSI_PURPLE = "\u001B[35m ";
    private Map<String, String> oldPlayersMap;
    private final ArrayList<GameController> games;
    private GameController currentGame;
    private final Map<String, Connection> playerBeforeJoiningLobby;
    private Request waitedRequest;
    private boolean isAsking;
    private static final int minimumPlayers = 2;
    private static final int maximumPlayers = 4;
    String pathFileWithNumberOfGame="src/main/resources/gameFile/gameNumbers.json";
    public Controller() {
        games = new ArrayList<>();
        currentGame= new GameController(getAvailableID(), this);
        playerBeforeJoiningLobby = new HashMap<>();
        waitedRequest=null;
        isAsking=false;
        games.add(currentGame);
        System.out.println("Do you want restor memory?");
        System.out.println(" - Yes(1)");
        System.out.println(" - No(0), in this case memory will be destroyed (all games)");
        Scanner scanner = new Scanner(System.in);
        String decision;
        while(true) {
            decision = scanner.nextLine().trim();
            if(decision.equals("1") || decision.equals("0"))
                break;
            else
                System.out.println("Error");
        }
        if(decision.equals("1"))
            manageOldPlayer();
        else {
            try {
                destroyAllFile();
            } catch (IOException ignored) {
            }
        }
    }
    /**It handles connection-error
     * @param connection Player's connection
     * @param playerName Player's name
     * */
    @Override
    public void tryToDisconnect(Connection connection, String playerName) {
        switch (connection.getStatusNetwork()){
            case AFTER_SEND_ACCEPT_MESSAGE_WITH_NUMBER_PLAYER -> {
                changeStatusToEveryone(StatusNetwork.SEND_ERROR_MESSAGE_CLIENT_NEED_TO_BE_CLOSED, currentGame);
                currentGame.destroyEveryPing();
                destroyGame(playerName, "First player left, game will be closed", currentGame);
                currentGame = new GameController(getAvailableID(), this);
                waitedRequest = null;
                isAsking =false;
            }
            case AFTER_SEND_ACCEPT_MESSAGE, AFTER_JOIN_LOBBY_OLD_PLAYER -> {
                System.out.println(ANSI_BLU + "Couldn't contact client " + playerName + ANSI_RESET);
                disconnectPlayerFromGame(playerName);
            }
            case AFTER_REQUEST_NUMBER_PLAYER ->{
                System.out.println(ANSI_BLU + "Couldn't ask number to the client " + playerName + ", dropping the request..." + ANSI_RESET);
                currentGame = new GameController(getAvailableID(), this);
                waitedRequest = null;
                isAsking =false;
                if(playerBeforeJoiningLobby.get(playerName)!=null) {
                    playerBeforeJoiningLobby.get(playerName).destroyPing();
                    playerBeforeJoiningLobby.remove(playerName);
                }
            }
            case AFTER_SEND_INVALID_NAME_MESSAGE -> {
                System.out.println(ANSI_BLU + "Problem contacting " + playerName + ", dropping the request..." + ANSI_RESET);
            }
            case SEND_ERROR_MESSAGE_CLIENT_NEED_TO_BE_CLOSED, SEND_MESSSGE_GAME_IS_NOT_AVAILABLE_FOR_RELOAD ->{
                System.out.println(ANSI_BLU + "Problem in contacting " + playerName + ", dropping the message..." + ANSI_RESET);
                if(playerBeforeJoiningLobby.get(playerName)!=null) {
                    playerBeforeJoiningLobby.get(playerName).destroyPing();
                    playerBeforeJoiningLobby.remove(playerName);
                }
            }
            default ->
                searchGameController(playerName).tryToDisconnect(connection, playerName);
        }
    }
    /**It handles message before game
     * @param message message
     * */
    @Override
    synchronized public void onMessage(Message message) {

        if(!(message.getType().equals(MessageType.PING_MESSAGE))) {
            System.out.println(ANSI_PURPLE + "Message has arrived in controller: " + message.getType() + ", " + message.getUsername() + ANSI_RESET);
       }

        MessageType type = message.getType();
        switch (type)
        {
            case LOGIN_REQUEST ->
            {
                LoginMessage msg = (LoginMessage) message;
                msg.getClientConnection().setPlayerName(message.getUsername());
                boolean accepted = login(msg.getUsername(),msg.getClientConnection());
                if(accepted) {
                    if (waitedRequest!= null && waitedRequest.getUsername().equals(msg.getUsername()))
                        currentGame.startTimer(msg.getClientConnection());
                    else {
                        GameController gameController = searchGameController(msg.getUsername());
                        gameController.startTimer(msg.getClientConnection());
                    }
                }
            }
            case PLAYER_NUMBER_ANSWER ->
            {
                if (waitedRequest.getUsername().equals(message.getUsername())) {
                    waitedRequest.getConnection().setStatusNetwork(StatusNetwork.AFTER_SEND_ACCEPT_MESSAGE_WITH_NUMBER_PLAYER);
                    try {
                        PlayerNumberAnswer msg = (PlayerNumberAnswer) message;
                        currentGame.setLimitOfPlayers(msg.getPlayerNumber());
                        waitedRequest.getConnection().sendMessage(new AcceptedLoginMessage(msg.getUsername()));
                        isAsking=false;
                        addPlayer(waitedRequest.getUsername(), waitedRequest.getConnection());
                    } catch (IOException e) {
                        System.out.println(ANSI_BLU + "Couldn't contact client " + waitedRequest.getUsername() + ANSI_RESET);
                        tryToDisconnect(waitedRequest.getConnection(),
                                waitedRequest.getUsername());
                        waitedRequest = null;
                    }
                }
            }
            case PING_MESSAGE -> {
                String username = message.getUsername();
                if(playerBeforeJoiningLobby.containsKey(username)) {
                    Connection connection = playerBeforeJoiningLobby.get(username);
                    connection.resetTimer( this);
                }
                else {
                    if(searchGameController(username)!=null)
                    {
                        searchGameController(username).renewTimer(username);
                    }
                }
            }
        }
    }
    /**It handles message during login. Only
     * @author: Riccardo Figini
     * @param connection Player's connection
     * @param username Player's username
     * @return {@code boolean} true if name is accepted
     * */
    public boolean login(String username, Connection connection) {
        connection.setStatusNetwork(StatusNetwork.ARRIVED_LOGIN_MESSAGE);
        int id = isOldPlayer(username);
        if(id == -1) {
            if (isAvailableUsername(username)) {
                if (currentGame.getSizeOfLobby() == 0) {
                    if (isAsking)
                    {
                        connection.setStatusNetwork(StatusNetwork.SEND_ERROR_MESSAGE_CLIENT_NEED_TO_BE_CLOSED);
                        try {
                            connection.sendMessage(new ErrorMessage("A lobby is being created from another user, please retry soon"));
                        } catch (IOException e) {
                            tryToDisconnect(connection, username);
                        }
                    }
                    else {
                        waitedRequest = new Request(username, connection);
                        isAsking = true;
                        connection.setStatusNetwork(StatusNetwork.AFTER_REQUEST_NUMBER_PLAYER);
                        try {
                            connection.sendMessage(new PlayerNumberRequest(minimumPlayers, maximumPlayers));
                        } catch (IOException e) {
                            waitedRequest = null;
                            tryToDisconnect(connection, username);
                        }
                    }
                } else {
                    connection.setStatusNetwork(StatusNetwork.AFTER_SEND_ACCEPT_MESSAGE);
                    try {
                        connection.sendMessage(new AcceptedLoginMessage(username));
                        addPlayer(username, connection);
                    } catch (IOException e) {
                        tryToDisconnect(connection, username);
                    }
                }
            } else {
                connection.setStatusNetwork(StatusNetwork.AFTER_SEND_INVALID_NAME_MESSAGE);
                try {
                    connection.sendMessage(new InvalidUsernameMessage());
                } catch (IOException e) {
                    tryToDisconnect(connection, username);
                }
                return false;
            }
        }
        else
            manageOldGame(username, connection, id);
        playerBeforeJoiningLobby.put(username, connection);
        return true;
    }
    /**It destroys all file game
     * */
    private void destroyAllFile() throws IOException {
        JsonObject j =  getArrayJsonWithNumberGame();

        if(j==null){
            System.out.println(ANSI_BLU + "File with number ID of game is not reachable. Game is not deleted" + ANSI_RESET);
            return;
        }
        JsonArray jsonArray = j.getAsJsonArray("numOfGame");

        for(int i=0; i< jsonArray.size(); i++){
            deleteGameFile("src/main/resources/gameFile/ServerGame"+jsonArray.get(i).getAsString()+".bin");
            jsonArray.remove(i);
        }

        Gson gson = new Gson();
        Writer writer;
        try {
            writer = new FileWriter(pathFileWithNumberOfGame);
            String jsonWrite = gson.toJson(j);
            writer.write(jsonWrite);
            writer.close();
        }catch (IOException ignored) {
        }
    }
    /**It loads old player name
     * */
    private void manageOldPlayer(){
        JsonArray arrayOfJsonCells = Objects.requireNonNull(getArrayJsonWithNumberGame()).getAsJsonArray("numOfGame");
        if (arrayOfJsonCells == null || arrayOfJsonCells.size() == 0)
            return;
        String number;
        for (JsonElement jsonCellElement : arrayOfJsonCells) {
            number = jsonCellElement.getAsString();
            String path = "src/main/resources/gameFile/ServerGame"+number+".bin";
            getPlayerFromFile(path, number);
        }
        System.out.println("File has been read, players: " + oldPlayersMap.size());
        for(Map.Entry<String, String> entry : oldPlayersMap.entrySet()){
            System.out.println("- " + entry.getKey() + ", " + entry.getValue());
        }
    }
    /**It returns jsonArray with number of ongoing game
     * @author: Riccardo Figini
     * @return {@code JsonArray} Array with number
     * */
    private JsonObject getArrayJsonWithNumberGame() {
        Gson gson = new Gson();
        oldPlayersMap = new LinkedHashMap<>();
        Reader reader;
        try {
            reader = new FileReader(pathFileWithNumberOfGame);
        } catch (FileNotFoundException e) {
            System.out.println(ANSI_BLU + "No file with old games, impossible to restore unfinished game" + ANSI_RESET);
            System.out.println(ANSI_BLU + "Server will continue without restoring games..."+ ANSI_RESET);
            oldPlayersMap =null;
            return null;
        }
        return gson.fromJson(reader, JsonObject.class);
    }
    /**It adds name of player from ongoing game. It reads object "gameController" from a file indicated with path
     * @param path Game's path
     * */
    private void getPlayerFromFile(String path, String gameId) {
        GameController gameController ;
        try {
            FileInputStream finput = new FileInputStream(path);
            ObjectInputStream ois = new ObjectInputStream(finput);
            gameController= (GameController) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println(ANSI_BLU + "Error in opening file, path: "+path+", " + e + ANSI_RESET);
            System.out.println(ANSI_BLU + "Some player are not restored"+ ANSI_RESET);
            return;
        } catch (IOException e) {
            System.out.println(ANSI_BLU + "Error in opening stream to read object, , path: "+path+" " + e + ANSI_RESET);
            System.out.println(ANSI_BLU + "Some player are not restored"+ ANSI_RESET);
            return;
        } catch (ClassNotFoundException e) {
            System.out.println(ANSI_BLU + "Error in reading class from file, path" + path + " " + e + ANSI_RESET);
            System.out.println(ANSI_BLU + "Some player are not restored"+ ANSI_RESET);
            return;
        }
        ArrayList<String> list = gameController.getNamesOfPlayer();
        for (String s : list) {
            oldPlayersMap.put(s, gameId);
        }
    }
    /**It returns game's id where player is contained. If he is not "old player" return -1
     * @author: Riccardo Figini
     * @param username Player's username
     * @return {@code int} game's id or -1
     * */
    private int isOldPlayer(String username) {
        for(Map.Entry<String, String> entry : oldPlayersMap.entrySet()){
            if(entry.getKey().equals(username)){
                System.out.println("Old player has joined: " + username);
                return Integer.parseInt(entry.getValue());
            }
        }
        return -1;
    }
    /**It adds player in currentGame and will start the game if lobby is full
     * @author: Riccardo Figini
     * @param username player's name
     * @param connection player's connection
     * */
    private void addPlayer(String username, Connection connection) {
        currentGame.addPlayer(username,connection);
        playerBeforeJoiningLobby.remove(username);
        if(currentGame.getSizeOfLobby()==currentGame.getLimitOfPlayers())
        {
            writeNewGameInExecution(currentGame.getGameId());
            changeStatusToEveryone(StatusNetwork.NEW_GAME_IS_STARTING, currentGame);
            new Thread(currentGame).start();
            int num = getAvailableID();
            currentGame=new GameController(num, this);
            games.add(currentGame);
        }
    }

    /**Write a new number of game into file
     * @author: Riccardo Figini
     * @param gameId Number to be written
     * */
    private void writeNewGameInExecution(int gameId) {
        Gson gson = new Gson();
        JsonObject jsonObject = getArrayJsonWithNumberGame();

        if(jsonObject==null) {
            System.out.println(ANSI_BLU + "Impossible to open jsonObject" + ANSI_RESET);
            changeStatusToEveryone(StatusNetwork.SEND_ERROR_MESSAGE_CLIENT_NEED_TO_BE_CLOSED, currentGame);
            destroyGame("Server has some problems, file not found. Game will be closed", currentGame);
            currentGame = new GameController(getAvailableID(), this);
            return;
        }

        JsonArray jsonArray = jsonObject.getAsJsonArray("numOfGame");

        if(jsonArray==null) {
            System.out.println(ANSI_BLU + "Impossible to open jsonArray" + ANSI_RESET);
            changeStatusToEveryone(StatusNetwork.SEND_ERROR_MESSAGE_CLIENT_NEED_TO_BE_CLOSED, currentGame);
            destroyGame("Server has some problems, file not found. Game will be closed", currentGame);
            currentGame = new GameController(getAvailableID(), this);
            return;
        }

        Writer writer;
        try {
            writer = new FileWriter(pathFileWithNumberOfGame);
            jsonArray.add(gameId);
            String jsonWrite = gson.toJson(jsonObject);
            writer.write(jsonWrite);
            writer.close();
        }catch (IOException e) {
            System.out.println(ANSI_BLU + "Impossible to open jsonObject" + ANSI_RESET);
            changeStatusToEveryone(StatusNetwork.SEND_ERROR_MESSAGE_CLIENT_NEED_TO_BE_CLOSED, currentGame);
            destroyGame("Server has some problems, file not found. Game will be closed", currentGame);
            currentGame = new GameController(getAvailableID(), this);
        }
    }

    /**It chose available ID for new game. It controls a file with every game
     * @author: Riccardo Figini
     * @return {@code int} Available number
     * */
    private int getAvailableID() {
        JsonObject jsonObject = getArrayJsonWithNumberGame();
        if(jsonObject == null)
            throw new NullPointerException("Problem with file");
        JsonArray arrayOfJsonCells = jsonObject.getAsJsonArray("numOfGame");
        int max=-1, number;
        if (arrayOfJsonCells == null)
            return 1;
        for (JsonElement jsonCellElement : arrayOfJsonCells) {
            number = jsonCellElement.getAsInt();
            if(number>max)
                max=number;
        }
        max++;
        return max;
    }
    /**It controls if in games exists a player with the same name in input
     * @param username player's name to verify
     * @return {@code boolean} true if name is available
     * */
    private boolean isAvailableUsername(String username) {
        for (GameController gc : games) {
            if (gc.getNamesOfPlayer().contains(username))
                return false;
        }
        return true;
    }

    /**It adds player if his game has been re-loaded from file, otherwise load game and then add player
     * @author: Riccardo Figini
     * @param username Player's username
     * @param connection Player's connection
     * @param gameId ID of game
     * */
    private void manageOldGame(String username, Connection connection, int gameId) {
        GameController gameController = null;

        for (GameController game : games) {
            if (game.getGameId() == gameId)
                gameController = game;
        }

        if(gameController == null){
            try {
                gameController = reloadGame(gameId);
                gameController.setStatusGame(GameController.StatusGame.BEFORE_GAME);
            }
            catch (RuntimeException | IOException | ClassNotFoundException e){
                connection.setStatusNetwork(StatusNetwork.SEND_MESSSGE_GAME_IS_NOT_AVAILABLE_FOR_RELOAD);
                try {
                    connection.sendMessage(new ErrorMessage("Server has some problems, old game is not available. It has been remove from file"));
                } catch (IOException ex) {
                    tryToDisconnect(connection, username);
                    return;
                }
                deleteGameFromArrayContainer(gameId);
                try {
                    deleteGameFile("src/main/resources/gameFile/ServerGame"+gameId+".bin");
                }catch (IOException ignored){}
                return;
            }
        }

        connection.setStatusNetwork(StatusNetwork.AFTER_JOIN_LOBBY_OLD_PLAYER);
        try {
            gameController.addPlayer(username, connection);
            connection.sendMessage(new MessageReturnToGame(MessageType.RETURN_TO_OLD_GAME_MESSAGE));
            connection.sendMessage(new AcceptedLoginMessage(username));
        } catch (IOException e) {
            tryToDisconnect(connection, username);
        }

        if(gameController.getSizeOfLobby() == gameController.getLimitOfPlayers()) {
            ArrayList<String> players = gameController.getNamesOfPlayer();
            gameController.restartGameAfterReload();
            removePlayerFromOldList(gameController);
            for(int i=0; i<gameController.getSizeOfLobby(); i++)
                playerBeforeJoiningLobby.remove(players.get(i));
        }
    }
    /**It deletes game file
     * @author: Riccardo Figini
     * @param s File's name to remove */
    private void deleteGameFile(String s) throws IOException {
        Files.delete(Paths.get(s));
    }
    /**When old player finally restart it remove player
     * @author: Riccardo Figini*/
    private void removePlayerFromOldList(GameController gameController) {
        for(int i = 0; i< gameController.getNamesOfPlayer().size(); i++){
            oldPlayersMap.remove(gameController.getNamesOfPlayer().get(i));
        }
    }
    /**It reloads game from file and create new map for plaeyey
     * @author: RIccardo Figini
     * @param gameId Game's ID
     * @return {@code GameController} It returns gameController
     * */
    private GameController reloadGame(int gameId) throws IOException, ClassNotFoundException {
        FileInputStream file = new FileInputStream("src/main/resources/gameFile/ServerGame"+gameId+".bin");
        ObjectInputStream inputStream = new ObjectInputStream(file);
        GameController gameController = (GameController) inputStream.readObject();
        if(gameController == null)
            throw new IOException();
        games.add(gameController);
        gameController.reloadPlayerCreatingNewMap(this);
        return gameController;
    }
    /**It finds specific gameController from player's name
     * @author: Riccardo Figini
     * @param playerName player's game
     * @return {@code GameController} It returns gameController where player is contained
     * */
    public GameController searchGameController(String playerName){
        for (GameController gameController: games){
            if(gameController.getNamesOfPlayer().contains(playerName)) {
                return gameController;
            }
        }
        return null;
    }
    /**Delete number game from file
     * @author: Riccardo Figini
     * @param gameId Game's id
     * */
    public void deleteGameFromArrayContainer(int gameId) {
        JsonObject j =  getArrayJsonWithNumberGame();

        if(j==null){
            System.out.println(ANSI_BLU + "File with number ID of game is not reachable. Game is not deleted" + ANSI_RESET);
            return;
        }
        JsonArray jsonArray = j.getAsJsonArray("numOfGame");

        for(int i=0; i< jsonArray.size(); i++){
            if(jsonArray.get(i).getAsInt() == gameId){
                jsonArray.remove(i);
                break;
            }
        }

        Gson gson = new Gson();
        Writer writer;
        try {
            writer = new FileWriter(pathFileWithNumberOfGame);
            String jsonWrite = gson.toJson(j);
            writer.write(jsonWrite);
            writer.close();
        }catch (IOException e) {
            System.out.println(ANSI_BLU + "Impossible to open file to delete game, " + e + ANSI_RESET);
            System.out.println(ANSI_BLU + gameId + " will be impossibile to use again" + ANSI_RESET);
        }
    }
    /**It destroys game and warn every player apart who creates problem
     * @author: Riccardo Figini
     * @param nameOfPlayerDown Player that throw a problem
     * @param message  Message with an error description
     * */
    public void destroyGame(String message, String nameOfPlayerDown, GameController gameController) {
        if(gameController.getNamesOfPlayer()== null)
            return;
        int limit=gameController.getNamesOfPlayer().size();
        for (int i =0; i<limit; i++) {
            String player = gameController.getNamesOfPlayer().get(i);
            if (!(player.equals(nameOfPlayerDown))) {
                gameController.sendMessageToASpecificUser(new ErrorMessage(message), player);
            }
        }
        if (limit > 0) {
            gameController.getNamesOfPlayer().subList(0, limit).clear();
        }
        deleteGameFromArrayContainer(gameController.getGameId());
        try {
            deleteGameFile("src/main/resources/gameFile/ServerGame"+gameController.getGameId()+".bin");
        } catch (IOException e) {
            System.out.println(ANSI_BLU + "File deletion failed" + ANSI_RESET);
        }
        games.remove(gameController);
    }
    /**It destroys game controller and warn every player
     * @author: Riccardo Figini
     * @param message Message with an error description
     * */
    public void destroyGame(String message, GameController gameController) {
        if(gameController.getNamesOfPlayer()== null)
            return;
        int limit=gameController.getNamesOfPlayer().size();
        for (int i =0; i<limit; i++){
            String player = gameController.getNamesOfPlayer().get(i);
            gameController.sendMessageToASpecificUser(new ErrorMessage(message), player);
        }
        if (limit > 0) {
            gameController.getNamesOfPlayer().subList(0, limit).clear();
        }
        deleteGameFromArrayContainer(gameController.getGameId());
        try {
            deleteGameFile("src/main/resources/gameFile/ServerGame"+gameController.getGameId()+".bin");
        } catch (IOException e) {
            System.out.println(ANSI_BLU + "Impossible delete file" + ANSI_RESET);
        }
        games.remove(gameController);
    }
    /**It removes player from specific not in execution game.
     * @author: Riccardo Figini
     * @param playerName Name of player to remove
     * */
    public void disconnectPlayerFromGame(String playerName) {
        ArrayList<String> players;

        players = currentGame.getNamesOfPlayer();
        if(players.contains(playerName)) {
            currentGame.removePlayer(playerName);
            return;
        }

        for (GameController game : games) {
            players = game.getNamesOfPlayer();
            if (players.contains(playerName)) {
                game.removePlayer(playerName);
            }
        }
    }
    /**It chages status to every player in game
     * @author: Riccardo Figini
     * @param statusNetwork network's status
     * @param gameController Game with connection that need to be changed
     * */
    public void changeStatusToEveryone(StatusNetwork statusNetwork, GameController gameController){
        for(int i=0; i<gameController.getNamesOfPlayer().size(); i++){
            changeStatusOfConnection(statusNetwork, gameController.getClients().get(gameController.getNamesOfPlayer().get(i)));
        }
    }
    /**It changes status of specific player in game
     * @author: Riccardo Figini
     * @param connection Connection that need to be changed
     * @param statusNetwork Network's status
     * */
    public void changeStatusOfConnection(StatusNetwork statusNetwork, Connection connection){
        if(connection!=null)
            connection.setStatusNetwork(statusNetwork);
    }
    /**It removes gameController from list
     * @param gameController gameController
     * */
    public void removeGame(GameController gameController) {
        games.remove(gameController);
    }
}