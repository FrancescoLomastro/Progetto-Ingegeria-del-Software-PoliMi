package it.polimi.ingsw.Network.Servers;



import it.polimi.ingsw.Network.Messages.Message;

import java.io.IOException;


public interface Connection {
    public void sendMessage(Message message) throws IOException;

}
