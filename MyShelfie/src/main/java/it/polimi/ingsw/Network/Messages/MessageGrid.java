package it.polimi.ingsw.Network.Messages;

import org.example.UtilyGame.ObjectCard;

public class MessageGrid extends MessageGame{
    ObjectCard[][] grid;

    public MessageGrid(){
        super(MessageType.UPDATE_GRID_MESSAGE);
    }

    public MessageGrid(ObjectCard[][] objectCards){
        super(MessageType.UPDATE_GRID_MESSAGE);
        this.grid=objectCards;
    }
    public ObjectCard[][] getGrid() {
        return grid;
    }

    public void setGrid(ObjectCard[][] grid) {
        this.grid = grid;
    }
}
