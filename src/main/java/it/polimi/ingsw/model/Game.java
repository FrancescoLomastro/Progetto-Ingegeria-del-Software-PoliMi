package it.polimi.ingsw.model;
import it.polimi.ingsw.model.Player.Library;
import it.polimi.ingsw.network.Messages.Message;
import it.polimi.ingsw.network.Messages.BadMoveMessage;
import it.polimi.ingsw.network.Messages.GoodMoveMessage;
import it.polimi.ingsw.exceptions.InvalidMoveException;
import it.polimi.ingsw.model.CardGenerator.CardGenerator;
import it.polimi.ingsw.model.Cards.CommonGoalCard;
import it.polimi.ingsw.model.Cards.ObjectCard;
import it.polimi.ingsw.model.LivingRoom.Grid;
import it.polimi.ingsw.model.LivingRoom.LivingRoom;
import it.polimi.ingsw.model.Player.Player;
import it.polimi.ingsw.utility.Couple;
import it.polimi.ingsw.utility.Position;

import java.io.IOException;
import java.io.Serial;
import java.io.Serializable;
import java.util.*;

/**
 * This class is used as a model representation of the game.
 * All game components as grid, cards and players library are reachable by using this class.
 * Also this class offers the method to perfor, a player move
 */
public class Game implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private final int numPlayers;
    private final Player[] players;
    private final LivingRoom livingRoom;
    private final CardGenerator cardGenerator;
    private int playerIndex;




    /**
     * constructor of the class Game
     * @param numPlayers: the number of the players in that game
     */
    public Game(int numPlayers) throws IOException {
        this.cardGenerator=new CardGenerator(numPlayers);
        this.numPlayers=numPlayers;
        this.players = new Player[numPlayers];
        this.livingRoom = new LivingRoom(numPlayers,  2,cardGenerator);
        this.playerIndex = 0;
    }




    /**
     * @return the number of players of the game
     */
    public int getNumPlayers() {
        return numPlayers;
    }




    /**
     * @return a matrix representation of the central grid
     */
    public ObjectCard[][] getGrid() {
        return livingRoom.getGrid().getMatrix();
    }




    /**
     * Returns a matrix representation of the passed user library
     * @param username the username of the player who owns the library
     * @return a matrix representation of user's library
     */
    public ObjectCard[][] getLibrary(String username) {
        return searchByUsername(username).getLibrary().getMatrix();
    }




    /**
     * @return an array containing all the player in the game
     */
    public Player[] getPlayers() {
        Player[] answer = new Player[numPlayers];
        System.arraycopy(players, 0, answer, 0, numPlayers);
        return answer;
    }




    /**
     * This method searches a player in a game, starting from his username
     * @param username : the player username
     * @return Player : the Player whose username matches with the parameter
     */
    private Player searchByUsername(String username){

        for(Player player : players){

            if(player.getName().equals(username)){

                return player;
            }
        }
        return null;
    }




    /**
     * This method manages a turn.
     * Checks if a move is valid, then inserts card in player's library and then controls common goals.
     * @param username: the turn player
     * @param move: an array of positions to identify the cells of the grid where the player takes his object cards
     * @param column: the column of the player's library in which he's going to insert the object cards he took in his move
     */
    public Message manageTurn(String username, Position[] move, int column){
        Player player = searchByUsername(username);
        ObjectCard[] objectCards;
        Grid grid = livingRoom.getGrid();
        try{
            objectCards = checkMove(player, move, column);
            player.getLibrary().insertCardsInLibrary(column, objectCards);
            if(grid.needRefill())
                grid.refill();
            return checkCommonGoal(player, livingRoom.getCommonGoalCards());
        }
        catch (InvalidMoveException e){
            return new BadMoveMessage(e.getMessage());
        }
    }




    /**
     * Creates a message that contains the points of the passed common goal cards, earned by the player's library
     * @param player: player whose library will be checked
     * @param commonGoalCards: the two common goal cards to check
     */
    private Message checkCommonGoal(Player player, CommonGoalCard[] commonGoalCards){
        int points1=0, points2=0;
        Library userlibrary = player.getLibrary();
        boolean[] satisfiedGoal = userlibrary.getSatisfiedGoal();
        if(!satisfiedGoal[0])
        {
            if (commonGoalCards[0].isSatisfied(userlibrary)) {
                userlibrary.satisfyCommonGoal(0);
                points1 = commonGoalCards[0].getScoreWithDecrease();
                player.addPoints(points1);
            }
        }
        if(!satisfiedGoal[1])
        {
            if (commonGoalCards[1].isSatisfied(userlibrary))
            {
                userlibrary.satisfyCommonGoal(1);
                points2 = commonGoalCards[1].getScoreWithDecrease();
                player.addPoints(points2);
            }
        }
        return new GoodMoveMessage(points1, points2);
    }




    /**
     * This method checks the validity of a user move and then runs the move itself.
     * @param player: player whose move needs to be checked and performed
     * @param move: an array of positions that identifies the cells of the grid where the player takes his object cards
     * @param column: the column of the player's library in which he's going to insert the object cards he took in his move
     * @return {@code ObjectCard[]} It returns an array with draw card (not position, but card)
     */
    private ObjectCard[] checkMove(Player player, Position[] move, int column) throws InvalidMoveException{
        Grid grid = livingRoom.getGrid();
        grid.isDrawAvailable(move);
        player.getLibrary().isMoveAvailable(column,move.length);
        return grid.draw(move);
    }




    /**
     * It finds ranking of player
     * @author: Andrea Ferrini
     * @return the game ranking in a form of an arraylist. Each node of the list is a pair of Name and Points ordered
     * from higher to lower scoring.
     */
    public ArrayList<Couple<String, Integer>> findRanking(){
        ArrayList<Couple<String, Integer>> list = new ArrayList<>();
        for(int i=0; i<numPlayers; i++){
            list.add(new Couple<>(players[i].getName(), players[i].countFinalPoints()));
        }
        for(int i=0; i<numPlayers; i++){
            for(int j=i+1; j<numPlayers; j++){
                if(list.get(j).getSecond()>list.get(i).getSecond()){
                    Couple<String, Integer> tmp = list.get(i);
                    list.set(i, list.get(j));
                    list.set(j, tmp);
                }
            }
        }

        return list;
    }




    /**
     * This method inserts in game a new player with a name
     * @param name the name of the player
     */
    public void setNextPlayer(String name){
        this.players[playerIndex] = new Player(name, cardGenerator);
        playerIndex++;
    }




    /**
     * This method is used to check if the user identified by the parameter has filled his library
     * @param username is the username of the player
     * @return true if the player has a full library
     */
    public boolean checkEndLibrary(String username){
        Player player = searchByUsername(username);
        return player.getLibrary().isFull();
    }





    /**
     * @author: Riccardo Figini
     * @return an array with common goal card of the living room
     */
    public CommonGoalCard[] getCommonGoalCard(){
        return livingRoom.getCommonGoalCards();
    }




    /**
     * @param index the index of the requested card
     * @return the card Id of the goal card in the index position
     */
    public int getCommonGoalCardId(int index) {
        if(index<0 || index > livingRoom.getCommonGoalCards().length-1)
            throw new RuntimeException("Invalid number common goal card");
        else
            return livingRoom.getCommonGoalCards()[index].getCardId();
    }




    /**
     * This method is used to assign the point at the center of the living room to a specific user.
     * After this method is called, the points at the center are actually consumed.
     * @param username the username of the player that will gain the points
     * @return the amount of points earned by the player
     */
    public int firstLibraryCompletion(String username)
    {
        Player playerWhoFills = searchByUsername(username);
        int points=livingRoom.consumeCentralPoints();
        playerWhoFills.addPoints(points);
        return points;
    }




    /**
     * @return The amount of points available at the center of the living room.
     */
    public int getCentralScore()
    {
        return livingRoom.getCentralScorePoints();
    }
}