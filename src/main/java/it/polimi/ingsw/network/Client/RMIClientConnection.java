package it.polimi.ingsw.network.Client;

import it.polimi.ingsw.network.Messages.Message;
import java.rmi.Remote;
import java.rmi.RemoteException;
public interface RMIClientConnection extends Remote {
    void onMessage(Message message) throws RemoteException;
}
