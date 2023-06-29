package it.polimi.ingsw.network.Messages;

/**
 * This message is used as a ping message between network interfaces
 */
public class ServerPingMessage extends Message{

    /**
     * Constructor: Creates a ping message
     * @param username it's the sender username
     *                 @author: Francesco Gregorio Lo Mastro
     */
    public ServerPingMessage(String username){
        super(username,MessageType.PING_MESSAGE);
    }
}
