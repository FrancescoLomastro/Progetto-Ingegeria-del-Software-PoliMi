package it.polimi.ingsw.model;

import javax.swing.*;

public class Game {
    private final int numPlayers;
    public Game(int numPlayers){
        this.numPlayers=numPlayers;
        //mancano i costruttori delle altre classi
    }
    public void setUp(){

    }
    public void turn(Player player, Couple[] move, int column){

    }

    private boolean checkCommonGoal(Player player){

        return true/*Da modificare*/;
    }

    private boolean checkMove(Player player, Couple[] move){
        return true/*Da modificare*/;
    }

    public Player winner(){
        return null;/*Da modificare*/
    }
}
