package it.polimi.ingsw.network.Messages;


import it.polimi.ingsw.network.Client.RMIClientConnection;

/**
 * This message is a login message used by RMI clients
 */
public class RMILoginMessage extends Message {
    private RMIClientConnection clientConnection =null;

    /**
     * Constructor: creates a login request for RMI server interfaces
     * @param username the name of the client
     * @param clientConnection the client stub used by server to contact the RMI client in the future
     */
    public RMILoginMessage(String username, RMIClientConnection clientConnection) {
        super(username,MessageType.RMI_LOGIN_REQUEST);
        this.clientConnection = clientConnection;
    }

    /**
     * @return a client stub used by server to contact the RMI client
     */
    public RMIClientConnection getConnection()
    {
        return clientConnection;
    }
}
