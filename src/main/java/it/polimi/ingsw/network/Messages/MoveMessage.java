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
     */
    public MoveMessage(Position[] move, int column){
        super(MessageType.PLAYER_MOVE_ANSWER);
        this.move=move;
        this.column = column;
    }

    /**
     * @return the array of positions the user asked to draw
     */
    public Position[] getMove() {
        return move;
    }

    /**
     * @return an integer representing the column the user wants to insert his cards
     */
    public int getColumn(){
        return column;
    }
}
