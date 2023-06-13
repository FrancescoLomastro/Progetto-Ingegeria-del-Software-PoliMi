package it.polimi.ingsw.model;
import it.polimi.ingsw.network.Messages.Message;
import it.polimi.ingsw.network.Messages.MessageAfterMoveNegative;
import it.polimi.ingsw.network.Messages.MessageAfterMovePositive;
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

/*
class Game
*@author Andrea Ferrini
*/
public class Game implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private final int numPlayers;
    private final Player[] players;
    private final Grid grid;
    private final LivingRoom livingRoom;
    private final CardGenerator cardGenerator;
    private int index;
    /**
     * constructor of the class Game
     * @param numPlayers: the number of the players in that game
     */
    public Game(int numPlayers) throws IOException {
        this.cardGenerator=new CardGenerator(numPlayers);
        this.numPlayers=numPlayers;
        this.players = new Player[numPlayers];
        this.livingRoom = new LivingRoom(numPlayers,  2,cardGenerator);
        this.grid = livingRoom.getGrid();
        this.index = 0;
    }

    public int getNumPlayers() {
        return numPlayers;
    }

    public ObjectCard[][] getGrid() {
        return grid.getMatrix();
    }

    public ObjectCard[][] getLibrary(String username) {

        return searchByUsername(username).getLibrary().getMatrix();
    }

    public Player[] getPlayers() {
        Player[] answer = new Player[numPlayers];

        System.arraycopy(players, 0, answer, 0, numPlayers);

        return answer;
    }

    /**
     * this method searches a player in a game, starting from his username
     * @param username : the turn player
     * @return Player : the Player whose username matches with the parameter
     */
    public Player searchByUsername(String username){

        for(Player player : players){

            if(player.getName().equals(username)){

                return player;
            }
        }
        return null;
    }

    /**
     * It manages turn. The first step is check is move is valid. Then it inserts card in player's library. Finally,  it controls common goal
     * @param username: the turn player
     * @param move: an array of positions to identify the cells of the grid where the player takes his object cards
     * @param column: the column of the player's library in which he's going to insert the object cards he took in his move
     */
    public Message manageTurn(String username, Position[] move, int column){
        Player player = searchByUsername(username);
        ObjectCard[] obs;
        try{
            obs = checkMove(player, move, column);
            player.getLibrary().insertCardsInLibrary(column, obs);
            if(grid.needRefill())
                grid.refill();
            return checkCommonGoal(player, livingRoom.getCommonGoalCards());
        }
        catch (InvalidMoveException e){
            return new MessageAfterMoveNegative(e.getMessage());
        }
    }

    /**
     * @param player: the turn player
     * @param commonGoalCards: the two common goal cards that are in the living room
     */
    private Message checkCommonGoal(Player player, CommonGoalCard[] commonGoalCards){
        int points1=0, points2=0;
        boolean goalCards[] = player.getLibrary().getSatisfiedGoal();
        if(goalCards[0]==false)
        {
            if (commonGoalCards[0].isSatisfied(player.getLibrary())) {
                player.getLibrary().satisfyCommonGoal(0);
                points1 = commonGoalCards[0].getScoreWithDecrease();
                player.addPoints(points1);
            }
        }
        if(goalCards[1]==false)
        {
            if (commonGoalCards[1].isSatisfied(player.getLibrary()))
            {
                player.getLibrary().satisfyCommonGoal(1);
                points2 = commonGoalCards[1].getScoreWithDecrease();
                player.addPoints(points2);
            }
        }
        return new MessageAfterMovePositive(points1, points2);
    }

    /**
     * @param player: the turn player
     * @param move: an array of positions to identify the cells of the grid where the player takes his object cards
     * @param column: the column of the player's library in which he's going to insert the object cards he took in his move
     */
    private ObjectCard[] checkMove(Player player, Position[] move, int column) throws InvalidMoveException{
        grid.isDrawAvailable(move);
        ObjectCard[] obs;
        obs = grid.tryDraw(move);
        player.getLibrary().isMoveAvailable(column,obs);
        grid.draw(move);
        return obs;
    }

    /**
     * @return ArrayList<Couple<String, Integer>>: list
     */
    public ArrayList<Couple<String, Integer>> findWinner(){
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
    public void setNextPlayer(String name){
        this.players[index] = new Player(name, cardGenerator);
        index ++;
    }

    public boolean checkEndLibrary(String username){
        Player player = searchByUsername(username);
        return player.getLibrary().isFull();
    }
    /**Return array with common goal card
     * @author: Riccardo Figini
     * @return CommonGaolCard[]*/
    public CommonGoalCard[] getCommonGoalCard(){
        return livingRoom.getCommonGoalCards();
    }

    public int getNumCommonGoal(int index) {
        if(index!=0 && index!=1)
            throw new RuntimeException("Invalid number common goal card");
        else
            return livingRoom.getCommonGoalCards()[index].getNum();
    }

    /** DA RIFARE IL COMMENTO: assegna la carta centale del livingroom all'username passato come parametro*/
    public int assignFillerPoints(String username)
    {
        Player fillerPlayer = searchByUsername(username);
        int points=livingRoom.consumeCentralPoints();
        fillerPlayer.addPoints(points);
        return points;
    }
    public int getCentralPointCard()
    {
        return livingRoom.getCentralScorePoints();
    }
}