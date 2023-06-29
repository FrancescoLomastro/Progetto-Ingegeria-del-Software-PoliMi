package it.polimi.ingsw.network.Messages;

/**
 * This message is used to inform a client whose move was performed successfully and gained points
 */
public class GoodMoveMessage extends Message{
    private final int gainedPointsFirstCard;
    private final int gainedPointsSecondCard;

    /**
     * Constructor: Creates a massage with the amount of points gained from the 2 common goal cards
     * @param points1 points gained from the first common goal card
     * @param points2 points gained from the second common goal card
     * @author: Francesco Gregorio Lo Mastro
     * @author: Andrea Ferrini
     * @author: Riccardo Figini
     */
    public GoodMoveMessage(int points1, int points2) {
        super(MessageType.GOOD_MOVE_ANSWER);
        gainedPointsFirstCard=points1;
        gainedPointsSecondCard=points2;
    }

    /**it returns the points gained from the first common goal card
     * @return the points gained from the first common goal card
     * @author: Francesco Gregorio Lo Mastro
     * @author: Andrea Ferrini
     * @author: Riccardo Figini
     */
    public int getGainedPointsFirstCard() {
        return gainedPointsFirstCard;
    }


    /**it returns the points gained from the second common goal card
     * @return the points gained from the second common goal card
     * @author: Francesco Gregorio Lo Mastro
     * @author: Andrea Ferrini
     * @author: Riccardo Figini
     */
    public int getGainedPointsSecondCard() {
        return gainedPointsSecondCard;
    }

}
