package it.polimi.ingsw.network.Messages;
import it.polimi.ingsw.utility.Position;
public class MessageMove extends MessageGame{
    private final Position[] move;
    private final int column;
    public MessageMove(){
        super(MessageType.PLAYER_MOVE_REQUEST);
        move=null;
        column=-1;
    }
    public MessageMove(Position[] move, int column){
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
