package it.polimi.ingsw.network.Messages;

public class ServerPingMessage extends Message{

    public ServerPingMessage(String playerUsername){
        super(playerUsername,MessageType.PING_MESSAGE);
    }
}
