package it.polimi.ingsw.Network.Client;

import it.polimi.ingsw.Network.Messages.*;
import it.polimi.ingsw.Network.ObserverImplementation.Observable;
import it.polimi.ingsw.View.ViewFactory;
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
    private  ObjectCard[][] defaultLibrary;

    private final ViewFactory viewFactory;

    public ClientModel(){
        librariesMap = new HashMap<>();
        pointsMap = new HashMap<>();
        pointsCommonGoalCards = new int[] {8,8};
        defaultLibrary = new ObjectCard[6][5];
        this.viewFactory = new ViewFactory();

        for(int i=0; i<6; i++){
            for(int j=0; j<5; j++){
                defaultLibrary[i][j] = new ObjectCard("Empty", Color.EMPTY, Type.FIRST);
            }
        }
    }



    public void setGrid(ObjectCard[][] grid) {
        ObjectCard[][] obs = getObjectCards(grid);
        this.grid = obs;
        setChanged();
        notifyObservers(new MessageGrid(this.grid));
        //notifyObservers(new MessageGrid(copy(grid))); //da chiedere ai prof
    }

    public void setLibrary(String name, ObjectCard[][] library) {
        ObjectCard[][] obs = getObjectCards(library);
        librariesMap.replace(name, obs);
        setChanged();
        notifyObservers(new MessageLibrary(obs, name));
        //notifyObservers(new MessageLibrary(copy(library), name)); //da chiedere ai prof
    }

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


    public void setPersonalGoalCard(ArrayList<Couple> goalList)
    {
        this.goalList=goalList;
    }



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
        //return copy(grid);
    }




    public ObjectCard[][] getLibrary(String name)
    {
        return librariesMap.get(name);
       // return copy(librariesMap.get(name));
    }

    public ViewFactory getViewFactory() {
        return this.viewFactory;
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

    public void setPointToPlayer(String first, Integer second) {
        pointsMap.replace(first, second);
    }

    public Map<String, Integer> getPointsMap() {
        return pointsMap;
    }
}

