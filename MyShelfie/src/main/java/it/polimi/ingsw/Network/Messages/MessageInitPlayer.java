package it.polimi.ingsw.Network.Messages;

import java.io.Serializable;

public class MessageInitPlayer extends MessageGame implements Serializable  {
    private final String player;
    public MessageInitPlayer(String player){
        super(MessageType.INIT_PLAYER_MESSAGE);
        this.player=player;
    }
    public String getPlayer() {
        return player;
    }
}
