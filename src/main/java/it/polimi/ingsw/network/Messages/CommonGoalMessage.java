package it.polimi.ingsw.network.Messages;

/**
 * This message is used to inform all players in the game that a common goal was reached by a player
 */
public class CommonGoalMessage extends Message{
    private final String playerWhoScored;
    private final int gainedPointsFirstCard;
    private final int gainedPointsSecondCard;
    private final int remainingPointsFirstCard;
    private final int remainingPointsSecondCard;

    /**
     * Constructor: Creates a message with all info for a common goal event
     * @author: Francesco Gregorio Lo Mastro
     * @author: Andrea Ferrini
     * @author: Riccardo Figini
     * @param gainedPointsFirstCard points gained from the first common goal card
     * @param gainedPointsSecondCard points gained from the second common goal card
     * @param playerWhoScored is the name of the player who scored the common goal card
     * @param remainingPointsFirstCard the remaining points in the first common goal card
     * @param remainingPointsSecondCard the remaining points in the second common goal card
     */
    public CommonGoalMessage(int gainedPointsFirstCard, int gainedPointsSecondCard, String playerWhoScored, int remainingPointsFirstCard, int remainingPointsSecondCard) {
        super(MessageType.COMMON_GOAL_REACHED_MESSAGE);
        this.playerWhoScored =playerWhoScored;
        this.gainedPointsFirstCard=gainedPointsFirstCard;
        this.gainedPointsSecondCard=gainedPointsSecondCard;
        this.remainingPointsFirstCard =remainingPointsFirstCard;
        this.remainingPointsSecondCard =remainingPointsSecondCard;
    }

    /**It returns the player the score some points
     * @return the name of the player who scored the common goal card
     * @author: Francesco Gregorio Lo Mastro
     * @author: Andrea Ferrini
     * @author: Riccardo Figini
     */
    public String getPlayerWhoScored() {
        return playerWhoScored;
    }

    /**It returns the point gain form first common goal
     * @return the points gained from the first common goal card
     * @author: Francesco Gregorio Lo Mastro
     * @author: Andrea Ferrini
     * @author: Riccardo Figini
     */
    public int getGainedPointsFirstCard() {
        return gainedPointsFirstCard;
    }

    /**It returns the point gain from the second common goal
     * @return the points gained from the second common goal card
     * @author: Francesco Gregorio Lo Mastro
     * @author: Andrea Ferrini
     * @author: Riccardo Figini
     */
    public int getGainedPointsSecondCard() {
        return gainedPointsSecondCard;
    }
    /**It returns the points that are available from the first common goal card
     * @return the remaining points in the first common goal car
     * @author: Francesco Gregorio Lo Mastro
     * @author: Andrea Ferrini
     * @author: Riccardo Figini
     */
    public int getRemainingPointsFirstCard() {
        return remainingPointsFirstCard;
    }
    /**It returns the points that are available from the second common goal card
     * @return the remaining points in the second common goal card
     * @author: Francesco Gregorio Lo Mastro
     * @author: Andrea Ferrini
     * @author: Riccardo Figini
     */
    public int getRemainingPointsSecondCard() {
        return remainingPointsSecondCard;
    }
}
