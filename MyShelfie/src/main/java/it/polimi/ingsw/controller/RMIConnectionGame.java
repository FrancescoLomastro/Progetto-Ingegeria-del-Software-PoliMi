package it.polimi.ingsw.controller;
import it.polimi.ingsw.Network.Client.RMIClientConnection;
import it.polimi.ingsw.Network.Messages.Message;
import it.polimi.ingsw.Network.Servers.Connection;

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
