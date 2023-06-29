package it.polimi.ingsw.network.Client;

import it.polimi.ingsw.network.Messages.Message;
import java.rmi.Remote;
import java.rmi.RemoteException;
public interface RMIClientConnection extends Remote {
    /**Interface of client RMI used by server to send messages to a client
     * @author: Francesco Lo Mastro*/
    void onMessage(Message message) throws RemoteException;
}
