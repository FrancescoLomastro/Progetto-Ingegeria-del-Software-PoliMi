package it.polimi.ingsw.Network.Client;

import it.polimi.ingsw.Network.Messages.Message;
import it.polimi.ingsw.Network.Messages.MessageCommonGoal;
import it.polimi.ingsw.Network.Messages.MessageLibrary;
import it.polimi.ingsw.Network.ObserverImplementation.Observer;
import it.polimi.ingsw.View.CLI;
import it.polimi.ingsw.model.Cards.ObjectCard;
import it.polimi.ingsw.model.Enums.Color;
import it.polimi.ingsw.model.Utility.Couple;
import it.polimi.ingsw.model.Utility.Position;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * This class reacts to notify from clientObject class and prints update*/
public class ThreadOutputClient implements Observer<ClientObject, Message> {
    private final CLI cli;
    /**
     * Constructor
     * @author: Riccardo Figini
     * @param client client*/
    public ThreadOutputClient(CLI client){
        this.cli=client;
    }
    /**
     * Show grid
     * @author: Riccardo Figini
     * */
    public void showGrid(){
        ObjectCard[][] grid = cli.getClientObject().getGrid();
        for(int i=0; i<grid.length; i++){
            for(int j=0; j<grid[i].length; j++){
                System.out.print(
                        "| "+grid[i][j]+" "
                );
            }
            System.out.println("");
        }
    }
    /**
     * Show Library of specific player
     * @author: Riccardo Figini
     * @param library library to print
     * */
    public void showLibrary(ObjectCard[][] library){
        for(int i=0; i<library.length; i++){
            for(int j=0; j<library[i].length; j++){
                System.out.print(
                        "| "+library[i][j]+" "
                );
            }
            System.out.println("");
        }
    }
    /**Print a generic string
     * @author: Riccardo Figini*/
    public void printAString(String s){
        System.out.println(s);
    }
    /**
     * This is used after observable notify
     * @author: Riccardo Figini
     * @param o ClientObject with game's information
     * @param arg message
     * */
    @Override
    public void update(ClientObject o, Message arg) {
        switch (arg.getType()){
            case UPDATE_GRID_MESSAGE -> showGrid();
            case UPDATE_LIBRARY_MESSAGE -> {
                ObjectCard[][] obs = ((MessageLibrary) arg).getLibrary();
                showLibrary(obs);
            }
            case COMMON_GOAL -> showPoint( (MessageCommonGoal) arg);
        }
    }

    private void showPoint(MessageCommonGoal arg) {
        System.out.println("Common/s goal have been reached by: " + arg.getPlayer());
        System.out.println("He has " + arg.getGainedPointsFirstCard() + "points now");
        System.out.println("Point for common goal card 1: " + arg.getPointAvailable1());
        System.out.println("Point for common goal card 2: " + arg.getPointAvailable2());
    }

    public void printAll(ClientObject clientObject) {
        showGrid();
        System.out.println("First common goal: " + clientObject.getDescriptionFirstCommonGoal());
        System.out.println("Second common goal: " + clientObject.getDescriptionSecondCommonGoal());
        System.out.println("Your personal goal:" );
        printPersonalGaol(clientObject.getGoalVector());
        Map<String, ObjectCard[][]> map = clientObject.getAllLibrary();
        for(Map.Entry<String, ObjectCard[][]> entry : map.entrySet() ){
            System.out.println(entry.getKey() +"'s library");
            showLibrary(entry.getValue());
        }
    }

    private void printPersonalGaol(ArrayList<Couple> goalVector) {
        for(int i=0; i<goalVector.size(); i++){
            Position p = (Position) (goalVector.get(i).getFirst());
            Color c = (Color) (goalVector.get(i).getSecond());
            System.out.println("row: " + p.getRow() + ", column: " + p.getColumn()+", color: "+ c);
        }
    }
}
