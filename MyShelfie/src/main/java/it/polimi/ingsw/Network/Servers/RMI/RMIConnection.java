package it.polimi.ingsw.Network.Servers.RMI;

import org.example.Clients.RMIClientConnection;
import org.example.Messages.Message;
import org.example.Servers.Connection;

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
