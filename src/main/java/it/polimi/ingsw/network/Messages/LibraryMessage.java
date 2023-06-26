package it.polimi.ingsw.network.Messages;
import it.polimi.ingsw.model.Cards.ObjectCard;
import it.polimi.ingsw.utility.Position;

/**
 * This message is used by server to update players on a library update.
 */
public class LibraryMessage extends Message {
    private final ObjectCard[][] library;
    private final String libraryOwner;
    private Position[] gridCardRemoved =null;
    private Position[] libraryCardAdded =null;

    /**
     * Contructor: Creates a update message for a library.
     * @param library a matrix of object cards representing an updated library
     * @param libraryOwner the owner of the library that is updated
     * @param gridCardRemoved vector of positions representing cards removed from central grid with the move (used for GUI animation)
     * @param LibraryCardAdded vector of positions representing cards added in the library with the move (used for GUI animation)
     */
    public LibraryMessage(ObjectCard[][]library, String libraryOwner, Position[] gridCardRemoved, Position[] LibraryCardAdded){
        super(MessageType.UPDATE_LIBRARY_MESSAGE);
        this.library=library;
        this.libraryOwner = libraryOwner;
        this.gridCardRemoved = gridCardRemoved;
        this.libraryCardAdded = LibraryCardAdded;
    }

    /**
     * @return the username of the updated library owner
     */
    public String getLibraryOwner() {
        return libraryOwner;
    }

    /**
     * @return a matrix of object cards representing an updated library
     */
    public ObjectCard[][] getLibrary() {
        return library;
    }

    /**
     * @return a vector of positions representing cards removed from central grid with the move (used for GUI animation)
     */
    public Position[] getGridCardRemoved() {
        return gridCardRemoved;
    }

    /**
     * @return a vector of positions representing cards added in the library with the move (used for GUI animation)
     */
    public Position[] getLibraryCardAdded() {
        return libraryCardAdded;
    }
}
