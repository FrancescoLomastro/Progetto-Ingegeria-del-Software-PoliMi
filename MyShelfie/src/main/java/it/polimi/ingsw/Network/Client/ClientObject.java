package it.polimi.ingsw.Network.Client;

import it.polimi.ingsw.Network.Messages.Message;
import it.polimi.ingsw.Network.Messages.MessageGrid;
import it.polimi.ingsw.Network.Messages.MessageLibrary;
import it.polimi.ingsw.Network.ObserverImplementation.Observable;
import it.polimi.ingsw.model.Cards.ObjectCard;

import java.util.HashMap;



public class ClientObject extends Observable<Message> {
    private ObjectCard[][] grid;
    private final HashMap<String, ObjectCard[][]> library;

    private final ObjectCard[][] defaultLibrary = {{null, null, null, null, null}, {null, null, null, null, null},
            {null, null, null, null, null},{null, null, null, null, null},{null, null, null, null, null}};

    public ClientObject(){
        library = new HashMap<>();
    }

    public ClientObject(ObjectCard[][] grid, HashMap<String, ObjectCard[][]> library) {
        this.grid = grid;
        this.library = library;
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


    public void addPlayer(String name){
        library.put(name, defaultLibrary);
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
}

