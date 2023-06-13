package it.polimi.ingsw.network.Messages;

import it.polimi.ingsw.utility.Couple;

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