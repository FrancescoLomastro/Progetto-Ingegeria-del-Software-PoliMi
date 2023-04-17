package it.polimi.ingsw.controller;
import org.example.Clients.RMIClientConnection;
import org.example.Messages.Message;
import org.example.Servers.Connection;
import java.rmi.RemoteException;

public class RMIConnectionGame implements Connection {
    private RMIClientConnection clientConnection;

    public RMIConnectionGame(RMIClientConnection clientConnection) {

        this.clientConnection=clientConnection;
    }
    @Override
    public void sendMessage(Message message) throws RemoteException {

        clientConnection.onMessage(message);
    }
}
