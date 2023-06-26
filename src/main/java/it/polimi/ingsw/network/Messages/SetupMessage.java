package it.polimi.ingsw.network.Messages;

import it.polimi.ingsw.model.Cards.ObjectCard;
import it.polimi.ingsw.utility.Couple;

import java.util.ArrayList;

/**
 * This is a message that contains all the elements needed by clients to begin a game.
 * Such as libraries, usernames, grid, cards etc.
 */
public class SetupMessage extends Message {
    private final String[] playersName;
    private final ObjectCard[][][] playersLibraries;
    private final ObjectCard[][] grid;
    private final ArrayList<Couple> personalGoalCards;
    private final int personalNumber;
    private final String description1;
    private final int numCom1;
    private final String description2;
    private final int numCom2;
    private final int pointCardCommon1;
    private final int pointCardCommon2;
    private final int centralPointCard;
    private final ArrayList<Couple<String, Integer>> playersPoints;

    /**
     * Constructor: Creates a setup message
     * @param pointCardCommon1 points on common goal card 1
     * @param pointCardCommon2 points on common goal card 2
     * @param personalGoalNumber the ID of a personal goal card (GUI)
     * @param numCom1 the ID of the first common goal card (GUI)
     * @param numCom2 the ID of the second common goal card (GUI)
     * @param centralPointCard the points on the grid
     * @param grid the central grid representation
     * @param playersName a list of player names
     * @param personalGoalCard a representation of the personal goal card for CLI
     * @param commonGoalCards the descriptions of common goal cards
     * @param playersPoints map of players point
     * @param playersLibraries a vector of players libraries
     */
    public SetupMessage(int pointCardCommon1, int pointCardCommon2, int personalGoalNumber, int numCom1, int numCom2, int centralPointCard, ObjectCard[][] grid, String[] playersName,
                        ArrayList<Couple> personalGoalCard, String[] commonGoalCards , ArrayList<Couple<String, Integer>> playersPoints ,ObjectCard[][]... playersLibraries) {
        super("server", MessageType.INITIAL_SETUP_MESSAGE);
        this.grid=grid;
        this.playersLibraries=playersLibraries;
        this.playersName=playersName;
        this.personalGoalCards=personalGoalCard;
        this.description1=commonGoalCards[0];
        this.description2=commonGoalCards[1];
        this.personalNumber=personalGoalNumber;
        this.numCom1=numCom1;
        this.numCom2=numCom2;
        this.centralPointCard=centralPointCard;
        this.pointCardCommon1=pointCardCommon1;
        this.pointCardCommon2=pointCardCommon2;
        this.playersPoints=playersPoints;
    }

    /**
     * @return the points on the grid
     */
    public int getCentralPointCard() {
        return centralPointCard;
    }

    /**
     * @return the description of the first common goal card
     */
    public String getDescription1() {
        return description1;
    }
    /**
     * @return the description of the second common goal card
     */
    public String getDescription2() {
        return description2;
    }
    /**
     * @return a vector with all player names
     */
    public String[] getPlayersName() {
        return playersName;
    }

    /**
     * @return a vector of matrix, representing each library
     */
    public ObjectCard[][][] getPlayersLibraries() {
        return playersLibraries;
    }
    /**
     * @return a matrix, representing the central grid
     */
    public ObjectCard[][] getGrid() {
        return grid;
    }
    /**
     * @return an arraylist of Couples, representing a personal goal card {Couple = (Color, Position)}
     */
    public ArrayList<Couple> getPersonalGoalCard() {
        return personalGoalCards;
    }

    /**
     * @return the Id of the personal goal card
     */
    public int getPersonalNumber() {
        return personalNumber;
    }

    /**
     * @return the Id of the first common goal card
     */
    public int getNumCom1() {
        return numCom1;
    }

    /**
     * @return the Id of the first common goal card
     */
    public int getNumCom2() {
        return numCom2;
    }

    /**
     * @return the points on the first common goal card
     */
    public int getPointCardCommon1() {
        return pointCardCommon1;
    }

    /**
     * @return an arraylist with the point of each player
     */
    public ArrayList<Couple<String, Integer>> getPlayersPoints() {
        return playersPoints;
    }

    /**
     * @return the points on the first common goal card
     */
    public int getPointCardCommon2() {
        return pointCardCommon2;
    }
}
