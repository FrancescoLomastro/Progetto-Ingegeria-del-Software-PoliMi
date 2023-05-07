package it.polimi.ingsw.Network.Messages;

import it.polimi.ingsw.model.Cards.ObjectCard;

public class ServerPingMessage extends Message{

    String playerUsername;
    public ServerPingMessage(String playerUsername){
        super(MessageType.PING_MESSAGE);
        this.playerUsername = playerUsername;
    }

    public String getPlayerUsername() {
        return playerUsername;
    }

    public void setPlayerUsername(String playerUsername) {
        this.playerUsername = playerUsername;
    }
}
