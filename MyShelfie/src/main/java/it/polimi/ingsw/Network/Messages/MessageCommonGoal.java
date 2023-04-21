package it.polimi.ingsw.Network.Messages;

/**
 * class for messages about the reach of a common goal
 * @author Andrea Ferrini
 * */
public class MessageCommonGoal extends Message{
    private final String player;
    private final int gainedPointsFirstCard;
    public final int gainedPointsSecondCard;
    public MessageCommonGoal(int gainedPointsFirstCard, int gainedPointsSecondCard, String username) {
        super(MessageType.COMMON_GOAL);
        player=username;
        this.gainedPointsFirstCard=gainedPointsFirstCard;
        this.gainedPointsSecondCard=gainedPointsSecondCard;
    }
    public String getPlayer() {
        return player;
    }
    public int getGainedPointsFirstCard() {
        return gainedPointsFirstCard;
    }
    public int getGainedPointsSecondCard() {
        return gainedPointsSecondCard;
    }
}
