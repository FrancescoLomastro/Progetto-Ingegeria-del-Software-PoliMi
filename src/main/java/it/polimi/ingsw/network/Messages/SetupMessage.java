package it.polimi.ingsw.network.Messages;

import it.polimi.ingsw.model.Cards.ObjectCard;
import it.polimi.ingsw.utility.Couple;

import java.util.ArrayList;

/**
 * This is a message that contains all the elements needed by clients to begin a game.
 * Such as libraries, usernames, grid, cards etc.
 */
public class SetupMessage extends Message {
    private final String[] playerNames;
    private final ObjectCard[][][] playersLibraries;
    private final ObjectCard[][] grid;
    private final ArrayList<Couple> personalGoalCardMatrix;
    private final int personalGoalId;
    private final String firstCommonGoalDescription;
    private final int firstCommonGoalCardId;
    private final String secondCommonGoalDescription;
    private final int secondCommonGoalCardId;
    private final int firstCommonGoalCardPoints;
    private final int secondCommonGoalCardPoints;
    private final int centralPointCard;
    private final ArrayList<Couple<String, Integer>> playersPoints;

    /**
     * Constructor: Creates a setup message
     * @param firstCommonGoalCardPoints points on common goal card 1
     * @param secondCommonGoalCardPoints points on common goal card 2
     * @param personalGoalNumber the ID of a personal goal card (GUI)
     * @param firstCommonGoalCardId the ID of the first common goal card (GUI)
     * @param secondCommonGoalCardId the ID of the second common goal card (GUI)
     * @param centralPointCard the points on the grid
     * @param grid the central grid representation
     * @param playerNames a list of player names
     * @param personalGoalCard a representation of the personal goal card for CLI
     * @param commonGoalCards the descriptions of common goal cards
     * @param playersPoints map of players point
     * @param playersLibraries a vector of players libraries
     */
    public SetupMessage(int firstCommonGoalCardPoints, int secondCommonGoalCardPoints, int personalGoalNumber, int firstCommonGoalCardId, int secondCommonGoalCardId, int centralPointCard, ObjectCard[][] grid, String[] playerNames,
                        ArrayList<Couple> personalGoalCard, String[] commonGoalCards , ArrayList<Couple<String, Integer>> playersPoints , ObjectCard[][]... playersLibraries) {
        super("server", MessageType.INITIAL_SETUP_MESSAGE);
        this.grid=grid;
        this.playersLibraries=playersLibraries;
        this.playerNames = playerNames;
        this.personalGoalCardMatrix =personalGoalCard;
        this.firstCommonGoalDescription =commonGoalCards[0];
        this.secondCommonGoalDescription =commonGoalCards[1];
        this.personalGoalId =personalGoalNumber;
        this.firstCommonGoalCardId = firstCommonGoalCardId;
        this.secondCommonGoalCardId = secondCommonGoalCardId;
        this.centralPointCard=centralPointCard;
        this.firstCommonGoalCardPoints = firstCommonGoalCardPoints;
        this.secondCommonGoalCardPoints = secondCommonGoalCardPoints;
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
    public String getFirstCommonGoalDescription() {
        return firstCommonGoalDescription;
    }
    /**
     * @return the description of the second common goal card
     */
    public String getSecondCommonGoalDescription() {
        return secondCommonGoalDescription;
    }
    /**
     * @return a vector with all player names
     */
    public String[] getPlayerNames() {
        return playerNames;
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
        return personalGoalCardMatrix;
    }

    /**
     * @return the Id of the personal goal card
     */
    public int getPersonalGoalId() {
        return personalGoalId;
    }

    /**
     * @return the Id of the first common goal card
     */
    public int getFirstCommonGoalCardId() {
        return firstCommonGoalCardId;
    }

    /**
     * @return the Id of the first common goal card
     */
    public int getSecondCommonGoalCardId() {
        return secondCommonGoalCardId;
    }

    /**
     * @return the points on the first common goal card
     */
    public int getFirstCommonGoalCardPoints() {
        return firstCommonGoalCardPoints;
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
    public int getSecondCommonGoalCardPoints() {
        return secondCommonGoalCardPoints;
    }
}
