package it.polimi.ingsw.network.Messages;
import it.polimi.ingsw.model.Cards.ObjectCard;
import it.polimi.ingsw.utility.Position;

/**
 * This message is used by server to update players on a library update.
 */
public class LibraryMessage extends Message {
    private final ObjectCard[][] library;
    private final String libraryOwner;
    private Position[] cardInGrid=null;
    private Position[] cardInLibr=null;
    public LibraryMessage(ObjectCard[][]library, String libraryOwner, Position[] cardInGrid, Position[] cardInLibr){
        super(MessageType.UPDATE_LIBRARY_MESSAGE);
        this.library=library;
        this.libraryOwner = libraryOwner;
        this.cardInGrid=cardInGrid;
        this.cardInLibr=cardInLibr;
    }
    public LibraryMessage(ObjectCard[][]library, String libraryOwner){
        super(MessageType.UPDATE_LIBRARY_MESSAGE);
        this.library=library;
        this.libraryOwner = libraryOwner;
    }
    public String getOwnerOfLibrary() {
        return libraryOwner;
    }
    public ObjectCard[][] getLibrary() {
        return library;
    }
    public String getLibraryOwner() {
        return libraryOwner;
    }
    public Position[] getCardInGrid() {
        return cardInGrid;
    }
    public Position[] getCardInLibr() {
        return cardInLibr;
    }
}
