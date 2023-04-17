package it.polimi.ingsw.Network.Messages;


import it.polimi.ingsw.model.Utility.Position;

public class MessageMove extends MessageGame{
    Position[] move;

    int column;

    public MessageMove(){
        super(MessageType.MY_MOVE_REQUEST);
    }

    public MessageMove(Position[] move, int column){
        super(MessageType.MY_MOVE_ANSWER);
        this.move=move;
        this.column = column;
    }

    public MessageMove(MessageType messageType){
        super(messageType);
    }

    public Position[] getMove() {
        return move;
    }

    public int getColumn(){

        return column;
    }

    public void setMove(Position[] move) {
        this.move = move;
    }
}
