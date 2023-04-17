package it.polimi.ingsw.Network.Messages;

public class MessageCommonGoal extends Message{

    int gainedPointsFirstCard = 0;

    int gainedPointsSecondCard = 0;

    public MessageCommonGoal(){
        super(MessageType.COMMON_GOAL);
    }

    public int getGainedPointsFirstCard() {
        return gainedPointsFirstCard;
    }

    public void setGainedPointsFirstCard(int gainedPointsFirstCard) {
        this.gainedPointsFirstCard = gainedPointsFirstCard;
    }

    public int getGainedPointsSecondCard() {
        return gainedPointsSecondCard;
    }

    public void setGainedPointsSecondCard(int gainedPointsSecondCard) {
        this.gainedPointsSecondCard = gainedPointsSecondCard;
    }
}
