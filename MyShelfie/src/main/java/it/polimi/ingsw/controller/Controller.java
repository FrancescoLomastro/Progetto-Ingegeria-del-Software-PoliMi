package it.polimi.ingsw.controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import it.polimi.ingsw.Network.Messages.*;
import it.polimi.ingsw.Network.Servers.Connection;
import it.polimi.ingsw.Network.StatusNetwork;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.Utility.Request;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import static it.polimi.ingsw.controller.GameController.ANSI_RESET;

public class Controller implements ServerReceiver
{
    public final static String ANSI_BLU ="\u001B[34m ";
    private Map<String, String> oldPlayer;
    private final ArrayList<GameController> games;
    private GameController currentGame;
    private Map<String, Connection> currentPlayerConnectionReferences;
    private Request waitedRequest;
    private boolean isAsking;
    private static final int minimumPlayers = 2;
    private static final int maximumPlayers = 4;
    String pathFileWithNumberOfGame="src/main/resources/gameFile/gameNumbers.json";
    public Controller() {
        games = new ArrayList<>();
        currentGame= new GameController(choseNewNumberForGame(), this);
        currentPlayerConnectionReferences = new HashMap<>();
        waitedRequest=null;
        isAsking=false;
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
        System.out.println("File has been read, players: " + oldPlayer.size());
        for(Map.Entry<String, String> entry : oldPlayer.entrySet()){
            System.out.println("- " + entry.getKey() + ", " + entry.getValue());
        }
    }

    /**It returns jsonArray with number of ongoing game
     * @author: Riccardo Figini
     * @return {@code JsonArray} Array with number
     * */
    private JsonObject getArrayJsonWithNumberGame() {
        Gson gson = new Gson();
        oldPlayer = new LinkedHashMap<>();
        Reader reader;
        try {
            reader = new FileReader(pathFileWithNumberOfGame);
        } catch (FileNotFoundException e) {
            System.out.println(ANSI_BLU + "No file with old games, impossible to restore unfinished game" + ANSI_RESET);
            System.out.println(ANSI_BLU + "Server will continue without restoring games..."+ ANSI_RESET);
            oldPlayer=null;
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
            oldPlayer.put(s, gameId);
        }
    }

    public void login(String username, Connection connection) {
        connection.setStatusNetwork(StatusNetwork.ARRIVED_LOGIN_MESSAGE);
        int id = isOldPlayer(username);
        if(id == -1) {
            if (availableUsername(username)) {
                connection.setPlayerName(username);
                if (currentGame.getSizeArrayConnection() == 0) {
                    if (isAsking) {
                        connection.setStatusNetwork(StatusNetwork.SEND_ERROR_MESSAGE_CLIENT_NEED_TO_BE_CLOSED);
                        try {
                            connection.sendMessage(new ErrorMessage("A lobby is being created from another user, please retry soon"));
                        } catch (IOException e) {
                            tryToDisconnect(connection, username);
                        }
                    } else {
                        waitedRequest = new Request(username, connection);
                        isAsking = true;
                        connection.setStatusNetwork(StatusNetwork.AFTER_REQUEST_NUMBER_PLAYER);
                        try {
                            connection.sendMessage(new PlayerNumberRequest(minimumPlayers, maximumPlayers));
                        } catch (IOException e) {
                            tryToDisconnect(connection, username);
                            waitedRequest = null;
                        }
                    }
                } else {
                    connection.setStatusNetwork(StatusNetwork.AFTER_SEND_ACCEPT_MESSAGE);
                    try {
                        connection.sendMessage(new AcceptedLoginMessage(currentGame.getLimitOfPlayers()));
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
            }
        }
        else
            manageOldGame(username, connection, id);
    }

    private int isOldPlayer(String username) {
        for(Map.Entry<String, String> entry : oldPlayer.entrySet()){
            if(entry.getKey().equals(username)){
                System.out.println("Old player has joined: " + username);
                return Integer.parseInt(entry.getValue());
            }
        }
        return -1;
    }

    private void addPlayer(String username, Connection connection) {
        games.add(currentGame);
        currentGame.addPlayer(username,connection);
        currentGame.startTimer(username,connection,this);
        currentPlayerConnectionReferences.put(username, connection);
        if(currentGame.getSizeArrayConnection()==currentGame.getLimitOfPlayers())
        {
            writeNewGameInExecution(currentGame.getGameId());
            changeStatusToEveryone(StatusNetwork.NEW_GAME_IS_STARTING, currentGame);
            new Thread(currentGame).start();
            int num = choseNewNumberForGame();
            currentGame=new GameController(num, this);
            currentPlayerConnectionReferences = new HashMap<>();
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
            currentGame = new GameController(choseNewNumberForGame(), this);
            return;
        }

        JsonArray jsonArray = jsonObject.getAsJsonArray("numOfGame");

        if(jsonArray==null) {
            System.out.println(ANSI_BLU + "Impossible to open jsonArray" + ANSI_RESET);
            changeStatusToEveryone(StatusNetwork.SEND_ERROR_MESSAGE_CLIENT_NEED_TO_BE_CLOSED, currentGame);
            destroyGame("Server has some problems, file not found. Game will be closed", currentGame);
            currentGame = new GameController(choseNewNumberForGame(), this);
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
            currentGame = new GameController(choseNewNumberForGame(), this);
        }
    }

    /**It chose available number for game. It controls a file with every game
     * @author: Riccardo Figini
     * @return {@code int} Available number
     * */
    private int choseNewNumberForGame() {
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

    private boolean availableUsername(String username) {
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
            connection.sendMessage(new AcceptedLoginMessage(currentGame.getLimitOfPlayers()));
        } catch (IOException e) {
            tryToDisconnect(connection, username);
        }

        if(gameController.getSizeArrayConnection() == gameController.getLimitOfPlayers()) {
            gameController.restartGameAfterReload();
            removePlayerFromOldList(gameController);
        }
    }

    private void deleteGameFile(String s) throws IOException {
        Files.delete(Paths.get(s));
    }

    private void removePlayerFromOldList(GameController gameController) {
        for(int i = 0; i< gameController.getNamesOfPlayer().size(); i++){
            oldPlayer.remove(gameController.getNamesOfPlayer().get(i));
        }
    }

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
            case PLAYER_NUMBER_ANSWER ->
            {
                if (waitedRequest.getUsername().equals(message.getUsername())) {
                    waitedRequest.getConnection().setStatusNetwork(StatusNetwork.AFTER_SEND_ACCEPT_MESSAGE_WITH_NUMBER_PLAYER);
                    try {
                        PlayerNumberAnswer msg = (PlayerNumberAnswer) message;
                        currentGame.setLimitOfPlayers(msg.getPlayerNumber());
                        waitedRequest.getConnection().sendMessage(new AcceptedLoginMessage(currentGame.getLimitOfPlayers()));
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
                for(int i = games.size()-1; i>=0; i--)
                {
                    GameController gc = games.get(i);
                    if (gc.getNamesOfPlayer().contains(username))
                        gc.renewTimer(username);
                }
            }
        }
    }

    @Override
    public void tryToDisconnect(Connection connection, String playerName) {
        switch (connection.getStatusNetwork()){
            case AFTER_SEND_ACCEPT_MESSAGE_WITH_NUMBER_PLAYER -> {
                changeStatusToEveryone(StatusNetwork.SEND_ERROR_MESSAGE_CLIENT_NEED_TO_BE_CLOSED, currentGame);
                destroyGame(playerName, "First player left, game will be closed", currentGame);
                currentGame = new GameController(choseNewNumberForGame(), this);
                waitedRequest = null;
                isAsking =false;
            }
            case AFTER_JOIN_LOBBY_OLD_PLAYER, AFTER_SEND_ACCEPT_MESSAGE -> {
                System.out.println(ANSI_BLU + "Couldn't contanct client " + playerName + ANSI_RESET);
                disconnectPlayerFromCurrentGame(playerName);
            }
            case AFTER_REQUEST_NUMBER_PLAYER ->{
                System.out.println(ANSI_BLU + "Couldn't ask number to the client " + playerName + ", dropping the request..." + ANSI_RESET);
                currentGame = new GameController(choseNewNumberForGame(), this);
                waitedRequest = null;
                isAsking =false;
            }
            case AFTER_SEND_INVALID_NAME_MESSAGE ->
                    System.out.println(ANSI_BLU + "Problem contacting " + playerName + ", dropping the request..." + ANSI_RESET);
            //Giocatore scartato, tanto non era stato svolto alcun cambiamento
            case SEND_ERROR_MESSAGE_CLIENT_NEED_TO_BE_CLOSED ->
                    System.out.println(ANSI_BLU + "Couldn't ask number to the client " + playerName + ", dropping the request..." + ANSI_RESET);
            //Giocatore scartato, tanto non era stato svolto alcun cambiamento
            case SEND_MESSSGE_GAME_IS_NOT_AVAILABLE_FOR_RELOAD->
                    System.out.println(ANSI_BLU + "Problem in contacting " + playerName + ", droping the message..." + ANSI_RESET);
            default ->{
                for (GameController gameController: games){
                    if(gameController.getNamesOfPlayer().contains(playerName)) {
                        gameController.tryToDisconnect(connection, playerName);
                    }
                }
            }
        }
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
            System.out.println(ANSI_BLU + "Impossible delete file" + ANSI_RESET);
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
        /*
    * if (gc.getNamesOfPlayer() == null || gc.getNamesOfPlayer().size() == 0) {
                games.remove(gc);
                continue;
            }
     */
    }
    /**It removes player from specific not in execution game
     * @author: Riccardo Figini
     * @param playerName Name of player to remove
     * */
    public void disconnectPlayerFromCurrentGame(String playerName) {
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
    /**It chages status to every player in currentGame
     * @author: Riccardo Figini
     * @param statusNetwork network's status
     * */
    public void changeStatusToEveryone(StatusNetwork statusNetwork, GameController gameController){
        for(int i=0; i<gameController.getNamesOfPlayer().size(); i++){
            changeStatusOfConnection(gameController.getNamesOfPlayer().get(i), statusNetwork);
        }
    }
    /**It changes status of specific player in game
     * @author: Riccardo Figini
     * @param player Player's name
     * @param statusNetwork Network's status
     * */
    public void changeStatusOfConnection(String player, StatusNetwork statusNetwork){
        Connection connection = currentPlayerConnectionReferences.get(player);
        if(connection!=null)
            connection.setStatusNetwork(statusNetwork);
    }

    public void removeGame(GameController gameController) {
        games.remove(gameController);
    }
}