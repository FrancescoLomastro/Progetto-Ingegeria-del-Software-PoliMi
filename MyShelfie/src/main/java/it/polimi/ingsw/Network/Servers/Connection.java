package it.polimi.ingsw.Network.Servers;

import org.example.Messages.Message;

import java.io.IOException;
import java.rmi.RemoteException;

public interface Connection {
    public void sendMessage(Message message) throws IOException;

}
