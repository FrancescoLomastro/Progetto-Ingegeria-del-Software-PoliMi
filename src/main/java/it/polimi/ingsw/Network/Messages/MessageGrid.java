package it.polimi.ingsw.Network.Messages;
import it.polimi.ingsw.model.Cards.ObjectCard;
public class MessageGrid extends MessageGame{
    private final TypeOfGridMessage typeOfGridMessage;
    private final ObjectCard[][] grid;
    public MessageGrid(ObjectCard[][] objectCards, TypeOfGridMessage typeOfGridMessage){
        super(MessageType.UPDATE_GRID_MESSAGE);
        this.grid=objectCards;
        this.typeOfGridMessage=typeOfGridMessage;
    }
    public ObjectCard[][] getGrid() {
        return grid;
    }
    public enum TypeOfGridMessage{
        UPDATE_AFTER_MOVE, INIT, NEED_TO_BE_PRINTED;
    }

    public TypeOfGridMessage getTypeOfGridMessage() {
        return typeOfGridMessage;
    }
}
