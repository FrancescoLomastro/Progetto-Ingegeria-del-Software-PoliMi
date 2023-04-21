package it.polimi.ingsw.Network.Client;

import it.polimi.ingsw.Network.Messages.*;
import it.polimi.ingsw.Network.ObserverImplementation.Observable;
import it.polimi.ingsw.model.Cards.CommonGoalCard;
import it.polimi.ingsw.model.Cards.ObjectCard;
import it.polimi.ingsw.model.Utility.Couple;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class ClientObject extends Observable<Message> {
    private ObjectCard[][] grid;
    private final HashMap<String, ObjectCard[][]> library;
    private ArrayList<Couple> goalVector;
    private Map<String, Integer> points;
    private int pointFirstCommonGoalCard;
    private String descriptionFirstCommonGoal;
    private String descriptionSecondCommonGoal;
    private int pointSecondCommonGoalCard;

    private final ObjectCard[][] defaultLibrary = {{null, null, null, null, null}, {null, null, null, null, null},
            {null, null, null, null, null},{null, null, null, null, null},{null, null, null, null, null}};
    public ClientObject(){
        library = new HashMap<>();
        points = new HashMap<>();
        pointFirstCommonGoalCard = pointSecondCommonGoalCard = 8;
    }
    public void setGrid(ObjectCard[][] grid) {
        this.grid = grid;
        setChanged();
        notifyObservers(new MessageGrid(copy(grid)));
    }
    public void setLibrary(String name, ObjectCard[][] library) {
        this.library.put(name, library);
        setChanged();
        notifyObservers(new MessageLibrary(copy(library), name));
    }
    public void setPersonalGoalCard(ArrayList<Couple> goalVector){
        this.goalVector=goalVector;
    }
    public void addPoint(MessageCommonGoal msg){
        int score;
        pointFirstCommonGoalCard = msg.getPointAvailable1();
        pointSecondCommonGoalCard = msg.getPointAvailable2();
        score = msg.getGainedPointsSecondCard() + msg.getGainedPointsFirstCard();
        score = points.get(msg.getPlayer()) + score;
        points.replace(msg.getPlayer(), score);
        setChanged();
        notifyObservers(new MessageCommonGoal(score, -1, msg.getPlayer(), pointFirstCommonGoalCard, pointSecondCommonGoalCard));
    }
    public ObjectCard[][] getGrid() {
        return copy(grid);
    }
    public ObjectCard[][] getLibrary(String name){
        return copy(library.get(name));
    }
    private ObjectCard[][] copy(ObjectCard[][] e){
        ObjectCard[][] t = new ObjectCard[e.length][e[0].length];
        for(int i=0; i<e.length;i++){
            for(int j=0; j<e[i].length; j++){
                t[i][j]=new ObjectCard(e[i][j].getDescription(), e[i][j].getColor(), e[i][j].getType());
            }
        }
        return t;
    }
    public void addPlayer(String name){
        library.put(name, defaultLibrary);
        points.put(name, 0);
    }

    public void setDescriptionFirstCommonGoal(String descriptionFirstCommonGoal) {
        this.descriptionFirstCommonGoal = descriptionFirstCommonGoal;
    }

    public void setDescriptionSecondCommonGoal(String descriptionSecondCommonGoal) {
        this.descriptionSecondCommonGoal = descriptionSecondCommonGoal;
    }

    public Map<String, ObjectCard[][]> getAllLibrary() {
        return library;
    }

    public ArrayList<Couple> getGoalVector() {
        return goalVector;
    }

    public String getDescriptionFirstCommonGoal() {
        return descriptionFirstCommonGoal;
    }

    public String getDescriptionSecondCommonGoal() {
        return descriptionSecondCommonGoal;
    }
}

