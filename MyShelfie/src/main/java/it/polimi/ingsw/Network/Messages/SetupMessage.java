package it.polimi.ingsw.Network.Messages;

import it.polimi.ingsw.model.Cards.ObjectCard;
import it.polimi.ingsw.model.Utility.Couple;

import java.util.ArrayList;

public class SetupMessage extends Message {
    private String[] playersName;
    private ObjectCard[][][] playersLibraries;
    private ObjectCard[][] grid;
    private ArrayList<Couple> personalGoalCards;
    private String description1;
    private String description2;



    public SetupMessage(ObjectCard[][] grid, String[] playersName, ArrayList<Couple> personalGoalCards, String[] commonGoalCards , ObjectCard[][]... playersLibraries) {
        super("server", MessageType.SETUP_MESSAGE);
        this.grid=grid;
        this.playersLibraries=playersLibraries;
        this.playersName=playersName;
        this.personalGoalCards=personalGoalCards;
        description1=commonGoalCards[0];
        description2=commonGoalCards[1];
    }

    public String getDescription1() {
        return description1;
    }
    public String getDescription2() {
        return description2;
    }
    public String[] getPlayersName() {
        return playersName;
    }

    public ObjectCard[][][] getPlayersLibraries() {
        return playersLibraries;
    }

    public ObjectCard[][] getGrid() {
        return grid;
    }
    public ArrayList<Couple> getPersonalGoalCard() {
        return personalGoalCards;
    }

}
