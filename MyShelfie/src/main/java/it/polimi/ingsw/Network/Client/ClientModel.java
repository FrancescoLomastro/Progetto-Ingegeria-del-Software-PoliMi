package it.polimi.ingsw.Network.Client;

import it.polimi.ingsw.Network.Messages.*;
import it.polimi.ingsw.Network.ObserverImplementation.Observable;
import it.polimi.ingsw.model.Cards.ObjectCard;
import it.polimi.ingsw.model.Enums.Color;
import it.polimi.ingsw.model.Enums.Type;
import it.polimi.ingsw.model.Utility.Couple;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class ClientModel extends Observable<Message> {
    private ObjectCard[][] grid;
    private final HashMap<String, ObjectCard[][]> librariesMap;
    private ArrayList<Couple> goalList;
    private Map<String, Integer> pointsMap;
    private String descriptionFirstCommonGoal;
    private String descriptionSecondCommonGoal;
    private int pointsCommonGoalCards[];
    private  ObjectCard[][] defaultLibrary ;

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



    public void setGrid(ObjectCard[][] grid) {
        ObjectCard[][] obs = new ObjectCard[grid.length][grid[0].length];
        for(int i =0; i<grid.length; i++){
            for(int j=0; j<grid[i].length; j++){
                if(grid[i][j]==null)
                    obs[i][j] = new ObjectCard("Empty", Color.EMPTY, Type.FIRST);
                else
                    obs[i][j] = new ObjectCard(grid[i][j].getDescription(), grid[i][j].getColor(), grid[i][j].getType());
            }
        }
        this.grid = obs;
        setChanged();
        notifyObservers(new MessageGrid(this.grid));
        //notifyObservers(new MessageGrid(copy(grid))); //da chiedere ai prof
    }



    public void setLibrary(String name, ObjectCard[][] library) {
        ObjectCard[][] obs = new ObjectCard[library.length][library[0].length];
        for(int i =0; i<library.length; i++){
            for(int j=0; j<library[i].length; j++){
                if(library[i][j]==null)
                    obs[i][j] = new ObjectCard("Empty", Color.EMPTY, Type.FIRST);
                else
                    obs[i][j] = new ObjectCard(library[i][j].getDescription(), library[i][j].getColor(), library[i][j].getType());
            }
        }
        librariesMap.put(name, obs);
        setChanged();
        notifyObservers(new MessageLibrary(obs, name));
        //notifyObservers(new MessageLibrary(copy(library), name)); //da chiedere ai prof
    }



    public void setPersonalGoalCard(ArrayList<Couple> goalList)
    {
        this.goalList=goalList;
    }



    public void addPoint(MessageCommonGoal msg){
        int score;
        pointsCommonGoalCards[0] = msg.getPointAvailable1();
        pointsCommonGoalCards[1] = msg.getPointAvailable2();
        score = msg.getGainedPointsSecondCard() + msg.getGainedPointsFirstCard();
        score = pointsMap.get(msg.getPlayer()) + score;
        pointsMap.replace(msg.getPlayer(), score);
        setChanged();
        notifyObservers(new MessageCommonGoal(score, -1, msg.getPlayer(), pointsCommonGoalCards[0], pointsCommonGoalCards[1]));
    }



    public ObjectCard[][] getGrid()
    {
        return grid;
        //return copy(grid);
    }




    public ObjectCard[][] getLibrary(String name)
    {
        return librariesMap.get(name);
       // return copy(librariesMap.get(name));
    }


/*
    private ObjectCard[][] copy(ObjectCard[][] e){
        ObjectCard[][] t = new ObjectCard[e.length][e[0].length];
        for(int i=0; i<e.length;i++){
            for(int j=0; j<e[i].length; j++)
            {
                if(e[i][j]!=null)
                    t[i][j]=new ObjectCard(e[i][j].getDescription(), e[i][j].getColor(), e[i][j].getType());
            }
        }
        return t;
    }
    */



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



    public Map<String, ObjectCard[][]> getAllLibrary() {
        return librariesMap;
    }



    public ArrayList<Couple> getGoalList() {
        return goalList;
    }



    public String getDescriptionFirstCommonGoal() {
        return descriptionFirstCommonGoal;
    }



    public String getDescriptionSecondCommonGoal() {
        return descriptionSecondCommonGoal;
    }
}
