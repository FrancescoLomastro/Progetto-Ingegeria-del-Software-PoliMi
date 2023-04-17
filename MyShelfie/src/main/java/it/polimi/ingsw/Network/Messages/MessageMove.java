package it.polimi.ingsw.Network.Messages;

import org.example.UtilyGame.Position;

public class MessageMove extends MessageGame{
    Position[] move;

    public MessageMove(){
        super(MessageType.MY_MOVE_REQUEST);
    }

    public MessageMove(Position[] move){
        super(MessageType.MY_MOVE_ANSWER);
        this.move=move;
    }

    public MessageMove(MessageType messageType){
        super(messageType);
    }

    public Position[] getMove() {
        return move;
    }

    public void setMove(Position[] move) {
        this.move = move;
    }
}
