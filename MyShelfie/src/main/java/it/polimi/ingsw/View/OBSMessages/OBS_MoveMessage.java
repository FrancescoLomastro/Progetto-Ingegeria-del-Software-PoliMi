package it.polimi.ingsw.View.OBSMessages;

import it.polimi.ingsw.model.Utility.Position;

public class OBS_MoveMessage extends OBS_Message
{
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
