package it.polimi.ingsw.view.OBSMessages;
import it.polimi.ingsw.utility.Position;
/**This message is used to communicate a move to server
 */
public class OBS_MoveMessage extends OBS_Message {
    private  Position[] move;
    private  int column;
    public OBS_MoveMessage(Position[] move, int column) {
        super(OBS_MessageType.MOVE);
        this.move=move;
        this.column=column;
    }
    public Position[] getMove() {
        return move;
    }
    public int getColumn(){
        return column;
    }
}
