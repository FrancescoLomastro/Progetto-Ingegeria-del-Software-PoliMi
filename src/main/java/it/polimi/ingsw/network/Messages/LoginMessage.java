package it.polimi.ingsw.network.Messages;


import it.polimi.ingsw.network.Servers.Connection;

/**
 * This message is build after a server network interface receives an RMILoginMessage or SocketLoginMessage.
 * This message in not serialized but it acts as a wrapper for login messages.
 * The message is "virtually" sent from server network interfaces to server Controllers
 */
public class LoginMessage extends Message{

    private Connection clientConnection;

    /**
     * Constructor: creates a generic login message
     * @param userName is the sender username
     * @param connection is a connection that server will use to talk to the sender of this message
     * @author Francesco Lo Mastro
     */
    public LoginMessage(String userName, Connection connection) {
        super(userName, MessageType.LOGIN_REQUEST);
        this.clientConnection=connection;
    }

    /**It returns the connection, it is useful for server
     * @return the connection inside the message
     * @author Francesco Lo Mastro
     */
    public Connection getClientConnection() {
        return clientConnection;
    }
}
