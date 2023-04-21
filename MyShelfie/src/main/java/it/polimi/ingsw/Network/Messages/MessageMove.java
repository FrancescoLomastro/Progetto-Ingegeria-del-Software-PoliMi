package it.polimi.ingsw.Network.Messages;
import it.polimi.ingsw.model.Utility.Position;
public class MessageMove extends MessageGame{
    private final Position[] move;
    private final int column;
    public MessageMove(){
        super(MessageType.MY_MOVE_REQUEST);
        move=null;
        column=-1;
    }
    public MessageMove(Position[] move, int column){
        super(MessageType.MY_MOVE_ANSWER);
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
