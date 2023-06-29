package it.polimi.ingsw.network.Messages;


import java.io.Serializable;

/**
 * The abstract representation of a generic network message.
 * A generic message has a sender and an enum called type.
 */
public abstract class Message implements Serializable {
    private static final long serialVersionUID = 1L;
    private String senderName;
    private final MessageType type;

    /**
     * Constructor: specifies both senderName and type
     * @param senderName sender username
     * @param type type of the message
     *@author: Francesco Lo Mastro
     */
    Message(String senderName, MessageType type) {
        this.senderName = senderName;
        this.type = type;
    }
    /**
     * Specifies only the message type, used when the sender name will be added in a second moment
     * @param type type of the message
     * @author: Francesco Lo Mastro
     */
    Message(MessageType type) {
        this.type = type;
    }

    /**It returns the sender of message
     * @return the sender name
     * @author: Francesco Lo Mastro
     */
    public String getSenderName() {
        return senderName;
    }

    /**It returns the type of message
     *
     * @return the type of the message
     *@author: Francesco Lo Mastro
     */
    public MessageType getType() {
        return type;
    }

    /**It set the sender's name
     * Sets the sender name field of the message as the parameter
     * @param name the sender name to be set
     * @author: Francesco Lo Mastro
     */
    public void setSenderName(String name) {
        this.senderName =name;
    }
}
