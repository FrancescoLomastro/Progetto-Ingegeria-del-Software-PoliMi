package it.polimi.ingsw.Network.Messages;

import it.polimi.ingsw.model.Utility.Couple;

import java.util.ArrayList;


public class MessagePoints extends Message {
    ArrayList<Couple<String, Integer>> list;
    public MessagePoints(ArrayList<Couple<String, Integer>> list){
        super(MessageType.POINTS_MESSAGE);
        this.list=list;
    }

    public ArrayList<Couple<String, Integer>> getList() {
        return list;
    }
}