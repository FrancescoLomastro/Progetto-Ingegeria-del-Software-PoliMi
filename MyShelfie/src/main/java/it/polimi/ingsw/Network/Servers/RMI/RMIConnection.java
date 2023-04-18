package it.polimi.ingsw.Network.Servers.RMI;



import it.polimi.ingsw.Network.Client.RMIClientConnection;
import it.polimi.ingsw.Network.Messages.Message;
import it.polimi.ingsw.Network.Servers.Connection;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RMIConnection implements Connection {
    private RMIClientConnection clientConnection;
    public RMIConnection(RMIClientConnection clientConnection) {
        this.clientConnection=clientConnection;
    }
    @Override
    public void sendMessage(Message message) throws RemoteException {
        clientConnection.onMessage(message);
    }

}
