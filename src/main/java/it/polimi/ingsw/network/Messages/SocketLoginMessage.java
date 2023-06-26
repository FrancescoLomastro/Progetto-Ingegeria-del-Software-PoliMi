package it.polimi.ingsw.network.Messages;


/**
 * This message is a login message used by socket clients
 */
public class SocketLoginMessage extends Message {

    /**
     * Constructor: creates a login request for Socket server interfaces
     * @param username the username of the client
     */


    public SocketLoginMessage(String username) {
        super(username, MessageType.SOCKET_LOGIN_REQUEST);
    }
}