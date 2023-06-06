package it.polimi.ingsw.Network.Messages;

import it.polimi.ingsw.Network.Servers.Connection;
import it.polimi.ingsw.controller.ServerReceiver;
import it.polimi.ingsw.model.Cards.ObjectCard;

public class ServerPingMessage extends Message{

    public ServerPingMessage(String playerUsername){
        super(playerUsername,MessageType.PING_MESSAGE);
    }
}
