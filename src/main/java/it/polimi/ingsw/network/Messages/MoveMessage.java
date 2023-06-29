package it.polimi.ingsw.network.Messages;
import it.polimi.ingsw.utility.Position;

/**
 * This message is used from client and servers to aks or send game moves
 */
public class MoveMessage extends Message {
    private final Position[] move;
    private final int column;

    /**
     * Constructor: Creates a move message request
     * @author Francesco Lo Mastro
     */
    public MoveMessage(){
        super(MessageType.PLAYER_MOVE_REQUEST);
        move=null;
        column=-1;
    }

    /**
     * Constructor: Creates a move message answer including chosen positions and insert column
     * @param move the array of positions the user asked to draw
     * @param column an integer representing the column the user wants to insert his cards
     * @author Francesco Lo Mastro
     */
    public MoveMessage(Position[] move, int column){
        super(MessageType.PLAYER_MOVE_ANSWER);
        this.move=move;
        this.column = column;
    }

    /**It returns a move made by user as a list of position in grid
     * @return the array of positions the user asked to draw
     * @author Francesco Lo Mastro
     */
    public Position[] getMove() {
        return move;
    }

    /**It returns the column where the player wants to add card
     * @return an integer representing the column the user wants to insert his cards
     * @author Francesco Lo Mastro
     */
    public int getColumn(){
        return column;
    }
}
