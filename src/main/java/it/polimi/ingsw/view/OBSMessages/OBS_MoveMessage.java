package it.polimi.ingsw.view.OBSMessages;
import it.polimi.ingsw.utility.Position;
/**This message is used to communicate a move to server
 */
public class OBS_MoveMessage extends OBS_Message {
    private  Position[] move;
    private  int column;

    /**
     * This method is used to construct a move message to be sent to the server.
     *
     * @param move : position of chosen object cards;
     * @param column : chosen library column
     * @author Francesco Gregorio Lo Mastro
     */
    public OBS_MoveMessage(Position[] move, int column) {
        super(OBS_MessageType.MOVE);
        this.move=move;
        this.column=column;
    }

    /**
     * This method return the position of object cards chosen by the player.
     *
     * @author Francesco Gregorio Lo Mastro
     */
    public Position[] getMove() {
        return move;
    }

    /**
     * This method return the library column chosen by the player.
     *
     * @author Francesco Gregorio Lo Mastro
     */
    public int getColumn(){
        return column;
    }
}
