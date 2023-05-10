package it.polimi.ingsw.Network.Servers.RMI;



import it.polimi.ingsw.Network.Client.RMIClientConnection;
import it.polimi.ingsw.Network.Messages.Message;
import it.polimi.ingsw.Network.Messages.MessageType;
import it.polimi.ingsw.Network.Messages.NewGameServerMessage;
import it.polimi.ingsw.Network.Servers.Connection;
import it.polimi.ingsw.Network.StatusNetwork;
import it.polimi.ingsw.controller.ServerReceiver;

import java.io.IOException;
import java.rmi.RemoteException;

public class RMIConnection implements Connection {
    private RMIClientConnection clientConnection;
    private StatusNetwork statusNetwork;
    private String playerName;
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
