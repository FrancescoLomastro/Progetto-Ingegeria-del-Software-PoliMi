package it.polimi.ingsw.Network.Client;



import it.polimi.ingsw.Network.Messages.Message;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIClientConnection extends Remote {
    void onMessage(Message message) throws RemoteException;
}
