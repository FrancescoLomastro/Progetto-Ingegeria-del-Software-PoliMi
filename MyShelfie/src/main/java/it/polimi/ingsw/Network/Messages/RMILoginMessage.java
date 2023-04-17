package it.polimi.ingsw.Network.Messages;

import org.example.Clients.RMIClientConnection;

public class RMILoginMessage extends Message {
    private RMIClientConnection clientConnection =null;
    public RMILoginMessage(String username) {
        super(username,MessageType.RMI_LOGIN_REQUEST);
    }
    public RMILoginMessage(String username, RMIClientConnection clientConnection) {
        super(username,MessageType.RMI_LOGIN_REQUEST);
        this.clientConnection = clientConnection;
    }

    public void setConnection(RMIClientConnection clientConnection) {
        this.clientConnection = clientConnection;
    }

    public RMIClientConnection getConnection()
    {
        return clientConnection;
    }
}
