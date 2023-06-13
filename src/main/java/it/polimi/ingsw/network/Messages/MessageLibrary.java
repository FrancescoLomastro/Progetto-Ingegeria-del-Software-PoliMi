package it.polimi.ingsw.network.Messages;
import it.polimi.ingsw.model.Cards.ObjectCard;
import it.polimi.ingsw.utility.Position;

public class MessageLibrary extends MessageGame{
    private final ObjectCard[][] library;
    private final String player;
    private Position[] cardInGrid=null;
    private Position[] cardInLibr=null;
    public MessageLibrary(ObjectCard[][]library, String player, Position[] cardInGrid, Position[] cardInLibr){
        super(MessageType.UPDATE_LIBRARY_MESSAGE);
        this.library=library;
        this.player=player;
        this.cardInGrid=cardInGrid;
        this.cardInLibr=cardInLibr;
    }
    public MessageLibrary(ObjectCard[][]library, String player){
        super(MessageType.UPDATE_LIBRARY_MESSAGE);
        this.library=library;
        this.player=player;
    }
    public String getOwnerOfLibrary() {
        return player;
    }
    public ObjectCard[][] getLibrary() {
        return library;
    }
    public String getPlayer() {
        return player;
    }
    public Position[] getCardInGrid() {
        return cardInGrid;
    }
    public Position[] getCardInLibr() {
        return cardInLibr;
    }
}
