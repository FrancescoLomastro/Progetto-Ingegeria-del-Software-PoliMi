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
     * @author Francesco Lo Mastro
     * @author Andrea Ferrini
     * @author Riccardo Figini
     */
    public LibraryMessage(ObjectCard[][]library, String libraryOwner, Position[] gridCardRemoved, Position[] LibraryCardAdded){
        super(MessageType.UPDATE_LIBRARY_MESSAGE);
        this.library=library;
        this.libraryOwner = libraryOwner;
        this.gridCardRemoved = gridCardRemoved;
        this.libraryCardAdded = LibraryCardAdded;
    }

    /**
     * It returns the library's owner
     * @return the username of the updated library owner
     * @author Francesco Lo Mastro
     */
    public String getLibraryOwner() {
        return libraryOwner;
    }

    /**It returns an updated library
     * @return a matrix of object cards representing an updated library
     * @author Francesco Lo Mastro
     */
    public ObjectCard[][] getLibrary() {
        return library;
    }

    /**It returns card removed from grid
     * @return a vector of positions representing cards removed from central grid with the move (used for GUI animation)
     * @author Francesco Lo Mastro
     */
    public Position[] getGridCardRemoved() {
        return gridCardRemoved;
    }

    /**
     * It returns card added in library
     * @return a vector of positions representing cards added in the library with the move (used for GUI animation)
     */
    public Position[] getLibraryCardAdded() {
        return libraryCardAdded;
    }
}
