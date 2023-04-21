package it.polimi.ingsw.Network.Messages;
import it.polimi.ingsw.model.Cards.ObjectCard;
public class MessageLibrary extends MessageGame{
    private final ObjectCard[][] library;
    private final String player;
    public MessageLibrary(ObjectCard[][]library, String player){
        super(MessageType.UPDATE_LIBRARY_MESSAGE);
        this.library=library;
        this.player=player;
    }
    public String getPlayer() {
        return player;
    }
    public ObjectCard[][] getLibrary() {
        return library;
    }
}
