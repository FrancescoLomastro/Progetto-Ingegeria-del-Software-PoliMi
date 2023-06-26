package it.polimi.ingsw.network.Messages;
import it.polimi.ingsw.utility.Position;
public class MoveMessage extends GameMessage {
    private final Position[] move;
    private final int column;
    public MoveMessage(){
        super(MessageType.PLAYER_MOVE_REQUEST);
        move=null;
        column=-1;
    }
    public MoveMessage(Position[] move, int column){
        super(MessageType.PLAYER_MOVE_ANSWER);
        this.move=move;
        this.column = column;
    }
    public Position[] getMove() {
        return move;
    }
    public int getColumn(){
        return column;
    }
}
