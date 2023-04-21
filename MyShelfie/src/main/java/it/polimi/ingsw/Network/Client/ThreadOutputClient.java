package it.polimi.ingsw.Network.Client;

import it.polimi.ingsw.Network.Messages.Message;
import it.polimi.ingsw.Network.Messages.MessageLibrary;
import it.polimi.ingsw.Network.ObserverImplementation.Observer;
import it.polimi.ingsw.View.CLI;
import it.polimi.ingsw.model.Cards.ObjectCard;
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
     * @param message message where is contained username
     * */
    public void showLibrary(MessageLibrary message){
        ObjectCard[][] library = cli.getClientObject().getLibrary(message.getPlayer());
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
            case UPDATE_LIBRARY_MESSAGE -> showLibrary( (MessageLibrary) arg);
        }
    }
}
