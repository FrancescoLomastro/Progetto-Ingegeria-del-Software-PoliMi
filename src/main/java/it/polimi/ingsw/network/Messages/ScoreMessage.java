package it.polimi.ingsw.network.Messages;

import it.polimi.ingsw.utility.Couple;

import java.util.ArrayList;

/**
 * This is a message containing an update on the points of all players in the game
 */
public class ScoreMessage extends Message {
    ArrayList<Couple<String, Integer>> list;

    /**
     * Constructor: Creates a message containing the list of points for all players
     * @param list a list of Couples representing username and relative points
     * @author: Francesco Gregorio Lo Mastro
     */
    public ScoreMessage(ArrayList<Couple<String, Integer>> list){
        super(MessageType.FINAL_POINTS_MESSAGE);
        this.list=list;
    }

    /**It returns points score by every player, this message is used at the end of a game
     * @return a list of Couples representing username and relative points
     * @author: Francesco Gregorio Lo Mastro
     */
    public ArrayList<Couple<String, Integer>> getList() {
        return list;
    }
}