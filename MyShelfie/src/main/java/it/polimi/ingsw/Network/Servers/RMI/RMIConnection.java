package it.polimi.ingsw.Network.Servers.RMI;
import it.polimi.ingsw.Network.Client.RMIClientConnection;
import it.polimi.ingsw.Network.Messages.Message;
import it.polimi.ingsw.Network.Servers.Connection;
import it.polimi.ingsw.Network.StatusNetwork;

import java.rmi.RemoteException;
/**
 * This class represents RMI connection with client*/
public class RMIConnection extends Connection {
    private RMIClientConnection clientConnection;
    private StatusNetwork statusNetwork;
    public RMIConnection(RMIClientConnection clientConnection) {
        this.clientConnection=clientConnection;
        statusNetwork=StatusNetwork.AFTER_ACCEPTION_SOCKET_BEFORE_LOGIN_MESSAGE;
    }
    @Override
    public void sendMessage(Message message) throws RemoteException {

        clientConnection.onMessage(message);
    }
    @Override
    public StatusNetwork getStatusNetwork() {
        return statusNetwork;
    }
    @Override
    public void setStatusNetwork(StatusNetwork statusNetwork) {
        this.statusNetwork=statusNetwork;
    }
    @Override
    public String getPlayerName() {
        return playerName;
    }
    @Override
    public void setPlayerName(String playerName) {
        this.playerName=playerName;
    }

}
