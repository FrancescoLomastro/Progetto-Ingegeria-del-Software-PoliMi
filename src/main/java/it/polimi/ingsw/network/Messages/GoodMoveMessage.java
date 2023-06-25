package it.polimi.ingsw.network.Messages;

/**
 * This message is used to inform a client whose move was performed successfully and gained points
 */
public class GoodMoveMessage extends Message{
    private int gainedPointsFirstCard = 0;
    private int gainedPointsSecondCard = 0;

    /**
     * Constructor: Creates a massage with the amount of points gained from the 2 common goal cards
     * @param points1 points gained from the first common goal card
     * @param points2 points gained from the second common goal card
     */
    public GoodMoveMessage(int points1, int points2) {
        super(MessageType.GOOD_MOVE_ANSWER);
        gainedPointsFirstCard=points1;
        gainedPointsSecondCard=points2;
    }

    /**
     * @return the points gained from the first common goal card
     */
    public int getGainedPointsFirstCard() {
        return gainedPointsFirstCard;
    }


    /**
     * @return the points gained from the second common goal card
     */
    public int getGainedPointsSecondCard() {
        return gainedPointsSecondCard;
    }

}
