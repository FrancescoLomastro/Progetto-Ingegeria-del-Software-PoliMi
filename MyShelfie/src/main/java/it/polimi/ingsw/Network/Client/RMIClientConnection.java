package it.polimi.ingsw.Network.Client;



import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIClientConnection extends Remote {
    void onMessage(Message message) throws RemoteException;
}
