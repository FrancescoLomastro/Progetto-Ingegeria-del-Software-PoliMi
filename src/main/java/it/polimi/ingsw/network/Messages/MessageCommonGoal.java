package it.polimi.ingsw.network.Messages;

/**
 * class for messages about the reach of a common goal
 * @author Andrea Ferrini
 * */
public class MessageCommonGoal extends Message{
    private final String player;
    private final int gainedPointsFirstCard;
    private final int gainedPointsSecondCard;
    private final int pointAvailable1;
    private final int pointAvailable2;

    public MessageCommonGoal(int gainedPointsFirstCard, int gainedPointsSecondCard, String username, int p1, int p2) {
        super(MessageType.COMMON_GOAL);
        player=username;
        this.gainedPointsFirstCard=gainedPointsFirstCard;
        this.gainedPointsSecondCard=gainedPointsSecondCard;
        pointAvailable1=p1;
        pointAvailable2=p2;
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
    public int getPointAvailable1() {
        return pointAvailable1;
    }
    public int getPointAvailable2() {
        return pointAvailable2;
    }
}
