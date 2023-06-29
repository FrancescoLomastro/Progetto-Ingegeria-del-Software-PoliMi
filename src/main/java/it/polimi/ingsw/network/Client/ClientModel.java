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
import java.util.LinkedHashMap;
import java.util.Map;

/**This class is a not complete copy of a model maintained by server.
 * It represents the status of the game
 * @author Riccardo Figini
 * @author Francesco Lo Mastro
 * */

public class ClientModel extends Observable<Message> {
    private ObjectCard[][] grid;
    private final HashMap<String, ObjectCard[][]> librariesMap;
    private ArrayList<Couple> personalGoalCardMatrix;
    private final Map<String, Integer> pointsMap;
    private String firstCommonGoalDescription;
    private int firstCommonGoalId;
    private int secondCommonGoalId;
    private int personalGoalId;
    private String secondCommonGoalDescription;
    private final int[] commonGoalPointsVector;
    private String myName;
    public ClientModel(){
        super();
        librariesMap = new LinkedHashMap<>();
        pointsMap = new HashMap<>();
        commonGoalPointsVector = new int[] {8,8};
    }
    /**It sets a new grid. This method is used to update central grid
     * @author Francesco Lo Mastro
     * @param grid new grid
     * @param typeOfGridMessage Type of update. It can be "initialized" or "after_move"
     * */
    public void setGrid(ObjectCard[][] grid, GridMessage.TypeOfGridMessage typeOfGridMessage) {
        this.grid = getObjectCards(grid);
        setChanged();
        notifyObservers(new GridMessage(this.grid, typeOfGridMessage) );
    }
    /**It updates a player's library. If the last two parameters are null, this update is an initialization, otherwise
     * it is a movement after move. The Last two parameters are useful for the view
     * @author Francesco Lo Mastro
     * @param name Name of player
     * @param library new library
     * @param newInLibrary new element in a library. It identifies new positions in the library's grid
     * @param oldInGrid It identifies removed position in old grid
     * */
    public void setLibrary(String name, ObjectCard[][] library, Position[] oldInGrid, Position[] newInLibrary) {
        ObjectCard[][] obs = getObjectCards(library);
        librariesMap.replace(name, obs);
        setChanged();
        notifyObservers(new LibraryMessage(obs, name, oldInGrid, newInLibrary));
    }
    /**It returns a new grid of ObjectCard with card "empty" instead of null
     * @author Francesco Lo Mastro
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
     * @author Riccardo Figini
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
        this.personalGoalCardMatrix =goalList;
    }

    /**This method updates/adds points in the model and create a special message for the view. This message
     * uses variable already in the scope to set (in order) player's point, number of common goal reached with
     * attribute "card" and the name of the player. The last two parameters are available common goal card's point
     * @author Riccardo Figini
     * @param msg Common goal card's message
     * */
    public void addPoint(CommonGoalMessage msg){
        int score, card=0;
        if(0 != msg.getGainedPointsFirstCard()) {
            card=1;
            commonGoalPointsVector[0] = msg.getRemainingPointsFirstCard();
        }
        if(0 != msg.getGainedPointsSecondCard()) {
            if(card==0)
                card=2;
            else
                card=3;
            commonGoalPointsVector[1] = msg.getRemainingPointsSecondCard();
        }
        score = msg.getGainedPointsSecondCard() + msg.getGainedPointsFirstCard();
        score = pointsMap.get(msg.getPlayerWhoScored()) + score;
        pointsMap.replace(msg.getPlayerWhoScored(), score);
        setChanged();
        notifyObservers(new CommonGoalMessage(score, card, msg.getPlayerWhoScored(), commonGoalPointsVector[0], commonGoalPointsVector[1]));
    }
    /**It returns the central grid
     * @author Francesco Lo Mastro
     * */
    public ObjectCard[][] getGrid() {
        return grid;
    }
    /**It returns a vector with points available for both common goal cards
     * @author Francesco Lo Mastro
     * @return int[] vector with points
     * */
    public int[] getCommonGoalPointsVector() {
        return commonGoalPointsVector;
    }
    /**It returns the library of a player specification in input parameter
     * @author Francesco Lo Mastro
     * @param name name of player
     * @return ObjectCard[][] matrix of object cards or null id player does not exist
     * */
    public ObjectCard[][] getLibrary(String name)
    {
        return librariesMap.get(name);
    }
    /**It sets first common goal's description
     * @param firstCommonGoalDescription description
     * @author Francesco Lo Mastro
     * */
    public void setFirstCommonGoalDescription(String firstCommonGoalDescription) {
        this.firstCommonGoalDescription = firstCommonGoalDescription;
    }
    /**It sets the second common goal's description
     * @param secondCommonGoalDescription description
     * @author Francesco Lo Mastro
     * */
    public void setSecondCommonGoalDescription(String secondCommonGoalDescription) {
        this.secondCommonGoalDescription = secondCommonGoalDescription;
    }
    /**it returns a map with couple name-library
     * @author Franscesco Lo Mastro
     * @return {@code Map<String, ObjectCard[][]>} return a map
     * */
    public Map<String, ObjectCard[][]> getAllLibrary() {
        return librariesMap;
    }
    /**It returns a copy of personal goal card
     * @author Francesco Lo Mastro
     * @return arrayList with persona goal card
     * */
    public ArrayList<Couple> getPersonalGoalCardMatrix() {
        return new ArrayList<>(personalGoalCardMatrix);
    }
    /**It returns the description of the first common goal card
     * @author Francesco Lo Mastro
     * @return description of card
     * */
    public String getFirstCommonGoalDescription() {
        return firstCommonGoalDescription;
    }
    /**It returns the description of the second common goal card
     * @author Francesco Lo Mastro
     * @return description of card
     * */
    public String getSecondCommonGoalDescription() {
        return secondCommonGoalDescription;
    }
    /**It sets points to a player
     * @author Francesco Lo Mastro
     * @param first name of player
     * @param second points of player
     * */
    public void setPointToPlayer(String first, Integer second) {
        pointsMap.replace(first, second);
    }
    /**It returns a map with couple name-points, name of player in the game
     * @author Francesco Lo Mastro
     * @return {@code Map<String, Integer>}
     * */
    public Map<String, Integer> getPointsMap() {
        return pointsMap;
    }
    /**This method sets up all information when the game begins. It sets all data in clientModel and then
     * calls an observed that will print all information on view
     * @author Francesco Lo Mastro
     * @param msg message with all initial informazione
     * */
    public void setup(SetupMessage msg)
    {
        commonGoalPointsVector[0]=msg.getFirstCommonGoalCardPoints();
        commonGoalPointsVector[1]=msg.getSecondCommonGoalCardPoints();
        firstCommonGoalId =msg.getFirstCommonGoalCardId();
        secondCommonGoalId =msg.getSecondCommonGoalCardId();
        personalGoalId =msg.getPersonalGoalId();
        setFirstCommonGoalDescription(msg.getFirstCommonGoalDescription());
        setSecondCommonGoalDescription(msg.getSecondCommonGoalDescription());
        setPersonalGoalCard(msg.getPersonalGoalCard());
        for(int i = 0; i<msg.getPlayerNames().length; i++)
        {
            librariesMap.put(msg.getPlayerNames()[i],getObjectCards(msg.getPlayersLibraries()[i]));
            pointsMap.put(msg.getPlayerNames()[i], 0);
        }
        this.grid=msg.getGrid();
        setChanged();
        notifyObservers(new SetupMessage(
                msg.getFirstCommonGoalCardPoints(),
                msg.getSecondCommonGoalCardPoints(),
                msg.getPersonalGoalId(),
                msg.getFirstCommonGoalCardId(),
                msg.getSecondCommonGoalCardId(),
                msg.getCentralPointCard(),
                getObjectCards(grid),
                msg.getPlayerNames(),
                msg.getPersonalGoalCard(),
                new String[]{msg.getFirstCommonGoalDescription(),msg.getSecondCommonGoalDescription()},
                msg.getPlayersPoints(),
                getObjectCards(msg.getPlayersLibraries())));
    }
    /**It returns an array of string with the name of players in the game
     * @author Francesco Lo Mastro
     * @return String[] list of player
     * */
    public String[] getPlayerNames() {
        return librariesMap.keySet().toArray(new String[0]);
    }
    /**It returns the name of owner of this client model
     * @author Francesco Lo Mastro
     * @return String name of owner player
     * */
    public String getMyName() {
        return myName;
    }
    /**It sets the name of clientModel's owner
     * @author Francesco Lo Mastro
     * @param myName name of player
     * */
    public void setMyName(String myName) {
        this.myName = myName;
    }
    /**It returns the ID of first common goal card
     * @author Francesco Lo Mastro
     * @return int with ID number
     * */
    public int getFirstCommonGoalId() {
        return firstCommonGoalId;
    }
    /**It returns the number ID of personal jail card
     * @author Francesco Lo Mastro*/
    public int getPersonalGoalId() {
        return personalGoalId;
    }
    /**It returns the ID of second common goal card
     * @author Francesco Lo Mastro
     * @return int with ID number
     * */
    public int getSecondCommonGoalId() {
        return secondCommonGoalId;
    }
    /**This method is called when someone fill is a library
     * @author Francesco Lo Mastro
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

