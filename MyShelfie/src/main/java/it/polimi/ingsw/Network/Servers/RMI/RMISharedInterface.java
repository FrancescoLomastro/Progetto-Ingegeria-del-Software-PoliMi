package it.polimi.ingsw.Network.Servers.RMI;


import it.polimi.ingsw.Network.Messages.Message;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMISharedInterface extends Remote {
    public void onMessage(Message message) throws RemoteException;
}
