package it.polimi.ingsw.Network.Messages;

import it.polimi.ingsw.model.Cards.ObjectCard;
import it.polimi.ingsw.model.Utility.Couple;

import java.util.ArrayList;

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
    public SetupMessage(int pointCardCommon1, int pointCardCommon2, int personalNumber, int numCom1, int numCom2, int centralPointCard, ObjectCard[][] grid, String[] playersName,
                        ArrayList<Couple> personalGoalCards, String[] commonGoalCards , ObjectCard[][]... playersLibraries) {
        super("server", MessageType.SETUP_MESSAGE);
        this.grid=grid;
        this.playersLibraries=playersLibraries;
        this.playersName=playersName;
        this.personalGoalCards=personalGoalCards;
        description1=commonGoalCards[0];
        description2=commonGoalCards[1];
        this.personalNumber=personalNumber;
        this.numCom1=numCom1;
        this.numCom2=numCom2;
        this.centralPointCard=centralPointCard;
        this.pointCardCommon1=pointCardCommon1;
        this.pointCardCommon2=pointCardCommon2;
    }

    public int getCentralPointCard() {
        return centralPointCard;
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

    public int getPersonalNumber() {
        return personalNumber;
    }

    public int getNumCom1() {
        return numCom1;
    }

    public int getNumCom2() {
        return numCom2;
    }

    public int getPointCardCommon1() {
        return pointCardCommon1;
    }

    public int getPointCardCommon2() {
        return pointCardCommon2;
    }
}
