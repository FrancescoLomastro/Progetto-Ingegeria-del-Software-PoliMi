package it.polimi.ingsw.network.Servers.RMI;
import it.polimi.ingsw.network.Client.RMIClientConnection;
import it.polimi.ingsw.network.Messages.Message;
import it.polimi.ingsw.network.Servers.Connection;
import it.polimi.ingsw.network.StatusNetwork;

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

    /**
     * This method takes care of activating the rmi server-side controller for sending answers to clients.
     *
     * @param message: it is the specific message that the controller sends as answer to a client request.
     * @author Alberto Aniballi
     */
    @Override
    public void sendMessage(Message message) throws RemoteException {
        clientConnection.onMessage(message);
    }

    /**
     * It gets current network status.
     *
     * @author Alberto Aniballi
     */
    @Override
    public StatusNetwork getStatusNetwork() {
        return statusNetwork;
    }

    /**
     * It sets the network status.
     *
     * @param statusNetwork: the network status to be set.
     * @author Alberto Aniballi
     */
    @Override
    public void setStatusNetwork(StatusNetwork statusNetwork) {
        this.statusNetwork=statusNetwork;
    }

    /**
     * It gets the player name dedicated to this socketConnection.
     *
     * @author Alberto Aniballi
     */
    @Override
    public String getPlayerName() {
        return playerName;
    }

    /**
     * It sets the player name dedicated to this socketConnection.
     *
     * @param playerName : the name of the player;
     * @author Alberto Aniballi
     */
    @Override
    public void setPlayerName(String playerName) {
        this.playerName=playerName;
    }

}
