package it.polimi.ingsw.network.Client;

import it.polimi.ingsw.network.Messages.*;
import it.polimi.ingsw.network.ObserverImplementation.Observable;
//import it.polimi.ingsw.View.Gui.guiControllers.ViewFactory;
import it.polimi.ingsw.model.Cards.ObjectCard;
import it.polimi.ingsw.enums.Color;
import it.polimi.ingsw.enums.Type;
import it.polimi.ingsw.utility.Couple;
import it.polimi.ingsw.utility.Position;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class ClientModel extends Observable<Message> {
    private ObjectCard[][] grid;
    private final HashMap<String, ObjectCard[][]> librariesMap;
    private ArrayList<Couple> goalList;
    private final Map<String, Integer> pointsMap;
    private String descriptionFirstCommonGoal;
    private int numberCommonGoal1;
    private int numberCommonGoal2;
    private int personalGoalCardNumber;
    private String descriptionSecondCommonGoal;
    private final int[] pointsCommonGoalCards;
    private final ObjectCard[][] defaultLibrary;
    private String myName;
    public ClientModel(){
        librariesMap = new HashMap<>();
        pointsMap = new HashMap<>();
        pointsCommonGoalCards = new int[] {8,8};
        defaultLibrary = new ObjectCard[6][5];
        for(int i=0; i<6; i++){
            for(int j=0; j<5; j++){
                defaultLibrary[i][j] = new ObjectCard("Empty", Color.EMPTY, Type.FIRST);
            }
        }
    }
    /**It sets a new grid. This method is used to update central grid
     * @author: Francesco Gregorio Lo Mastro
     * @param grid new grid
     * @param typeOfGridMessage Type of update. It can be "initialized" or "after_move"
     * */
    public void setGrid(ObjectCard[][] grid, MessageGrid.TypeOfGridMessage typeOfGridMessage) {
        this.grid = getObjectCards(grid);
        setChanged();
        notifyObservers(new MessageGrid(this.grid, typeOfGridMessage) );
    }
    /**It updates a player's library. If the last two parameters are null, this update is an initialization, otherwise
     * it is a movement after move. The Last two parameters are useful for the view
     * @author: Francesco Gregorio Lo Mastro
     * @param name Name of player
     * @param library new library
     * @param newInLibrary new element in a library. It identifies new positions in the library's grid
     * @param oldInGrid It identifies removed position in old grid
     * */
    public void setLibrary(String name, ObjectCard[][] library, Position[] oldInGrid, Position[] newInLibrary) {
        ObjectCard[][] obs = getObjectCards(library);
        librariesMap.replace(name, obs);
        setChanged();
        notifyObservers(new MessageLibrary(obs, name, oldInGrid, newInLibrary));
    }
    /**It returns a new grid of ObjectCard with card "empty" instead of null
     * @author: Francesco Gregorio Lo Mastro
     * @param objectCards grid
     * */
    private static ObjectCard[][] getObjectCards(ObjectCard[][] objectCards) {
        ObjectCard[][] obs = new ObjectCard[objectCards.length][objectCards[0].length];
        for(int i = 0; i< objectCards.length; i++){
            for(int j = 0; j< objectCards[i].length; j++){
                if(objectCards[i][j]==null)
                    obs[i][j] = new ObjectCard("Empty", Color.EMPTY, Type.FIRST);
                else
                    obs[i][j] = new ObjectCard(objectCards[i][j].getDescription(), objectCards[i][j].getColor(), objectCards[i][j].getType());
            }
        }
        return obs;
    }
    /**It returns a copy of input grid
     * @author: Riccardo Figini
     * */
    private static ObjectCard[][][] getObjectCards(ObjectCard[][][] objectCards) {
        ObjectCard[][][] toReturn= new ObjectCard[objectCards.length][][];
        for(int k=0;k<objectCards.length;k++)
        {
            toReturn[k]=getObjectCards(objectCards[k]);
        }
        return toReturn;
    }
    public void setPersonalGoalCard(ArrayList<Couple> goalList)
    {
        this.goalList=goalList;
    }

    /**This method updates/adds points in the model and create a special message for the view. This message
     * uses variable already in the scope to set (in order) player's point, number of common goal reached with
     * attribute "card" and the name of the player. The last two parameters are available common goal card's point
     * @author: Riccardo Figini
     * @param msg Common goal card's message
     * */
    public void addPoint(MessageCommonGoal msg){
        int score, card=0;
        if(0 != msg.getGainedPointsFirstCard()) {
            card=1;
            pointsCommonGoalCards[0] = msg.getPointAvailable1();
        }
        if(0 != msg.getGainedPointsSecondCard()) {
            if(card==0)
                card=2;
            else
                card=3;
            pointsCommonGoalCards[1] = msg.getPointAvailable2();
        }
        score = msg.getGainedPointsSecondCard() + msg.getGainedPointsFirstCard();
        score = pointsMap.get(msg.getPlayer()) + score;
        pointsMap.replace(msg.getPlayer(), score);
        setChanged();
        notifyObservers(new MessageCommonGoal(score, card, msg.getPlayer(), pointsCommonGoalCards[0], pointsCommonGoalCards[1]));
    }

    public ObjectCard[][] getGrid()
    {
        return grid;
    }

    public int[] getPointsCommonGoalCards() {
        return pointsCommonGoalCards;
    }

    public ObjectCard[][] getLibrary(String name)
    {
        return librariesMap.get(name);
    }
    /**It adds a player in game. It will be added to all lists with player
     * @author: Francesco Gregorio Lo Mastro
     * @param name name of player
     * */
    public void addPlayer(String name){
        librariesMap.put(name, defaultLibrary);
        pointsMap.put(name, 0);
    }
    public void setDescriptionFirstCommonGoal(String descriptionFirstCommonGoal) {
        this.descriptionFirstCommonGoal = descriptionFirstCommonGoal;
    }
    public void setDescriptionSecondCommonGoal(String descriptionSecondCommonGoal) {
        this.descriptionSecondCommonGoal = descriptionSecondCommonGoal;
    }
    /**it returns a map with couple name-library
     * @author: Franscesco Gregorio Lo Mastro
     * @return {@code Map<String, ObjectCard[][]>} return a map*/
    public Map<String, ObjectCard[][]> getAllLibrary() {
        return librariesMap;
    }
    public ArrayList<Couple> getGoalList() {
        return new ArrayList<>(goalList);
    }
    public String getDescriptionFirstCommonGoal() {
        return descriptionFirstCommonGoal;
    }
    public String getDescriptionSecondCommonGoal() {
        return descriptionSecondCommonGoal;
    }
    public void setPointToPlayer(String first, Integer second) {
        pointsMap.replace(first, second);
    }
    /**It returns a map with couple name-points, name of player in the game
     * @author: Francesco Gregorio Lo Mastro
     * @return {@code Map<String, Integer>}
     * */
    public Map<String, Integer> getPointsMap() {
        return pointsMap;
    }
    /**This method sets up all information when the game begins. It sets all data in clientModel and then
     * calls an observed that will print all information on view
     * @author: Francesco Gregorio Lo Mastro
     * @param msg message with all initial informazione
     * */
    public void setup(SetupMessage msg)
    {
        pointsCommonGoalCards[0]=msg.getPointCardCommon1();
        pointsCommonGoalCards[1]=msg.getPointCardCommon2();
        numberCommonGoal1 =msg.getNumCom1();
        numberCommonGoal2 =msg.getNumCom2();
        personalGoalCardNumber =msg.getPersonalNumber();
        setDescriptionFirstCommonGoal(msg.getDescription1());
        setDescriptionSecondCommonGoal(msg.getDescription2());
        setPersonalGoalCard(msg.getPersonalGoalCard());
        for(int i=0; i<msg.getPlayersName().length;i++)
        {
            addPlayer(msg.getPlayersName()[i]);
            librariesMap.replace(msg.getPlayersName()[i],getObjectCards(msg.getPlayersLibraries()[i]));
        }
        this.grid=msg.getGrid();
        setChanged();
        notifyObservers(new SetupMessage(
                msg.getPointCardCommon1(),
                msg.getPointCardCommon2(),
                msg.getPersonalNumber(),
                msg.getNumCom1(),
                msg.getNumCom2(),
                msg.getCentralPointCard(),
                getObjectCards(grid),
                msg.getPlayersName(),
                msg.getPersonalGoalCard(),
                new String[]{msg.getDescription1(),msg.getDescription2()},
                msg.getPlayersPoints(),
                getObjectCards(msg.getPlayersLibraries())));
    }
    public String[] getPlayerNames() {
        return librariesMap.keySet().toArray(new String[0]);
    }
    public String getMyName() {
        return myName;
    }
    public void setMyName(String myName) {
        this.myName = myName;
    }
    public int getNumberCommonGoal1() {
        return numberCommonGoal1;
    }
    public int getPersonalGoalCardNumber() {
        return personalGoalCardNumber;
    }
    public int getNumberCommonGoal2() {
        return numberCommonGoal2;
    }
    /**This method is called when someone fill is a library
     * @param message message with player's name
     * */
    public void onAlmostOver(AlmostOverMessage message) {
        int score = message.getFillerPoints();
        score = pointsMap.get(message.getFillerName()) + score;
        pointsMap.replace(message.getFillerName(),score);
        setChanged();
        notifyObservers(message);
    }
}

