package it.polimi.ingsw.Network.Messages;
import it.polimi.ingsw.model.Cards.ObjectCard;
public class MessageGrid extends MessageGame{
    private final ObjectCard[][] grid;
    public MessageGrid(ObjectCard[][] objectCards){
        super(MessageType.UPDATE_GRID_MESSAGE);
        this.grid=objectCards;
    }
    public ObjectCard[][] getGrid() {
        return grid;
    }
}
