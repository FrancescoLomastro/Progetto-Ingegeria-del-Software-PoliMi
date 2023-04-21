package it.polimi.ingsw.Network.Messages;

import it.polimi.ingsw.model.Cards.PersonalGoalCard;
import it.polimi.ingsw.model.Utility.Couple;

import java.util.ArrayList;

public class MessagePersonalGoal extends Message {
    private final ArrayList<Couple> goalVector;
    public MessagePersonalGoal(ArrayList<Couple> personalGoalCard) {
        super(MessageType.INIT_PERSONAL_GOAL);
        goalVector = personalGoalCard;
    }
    public ArrayList<Couple> getGoalVector() {
        return goalVector;
    }
}
