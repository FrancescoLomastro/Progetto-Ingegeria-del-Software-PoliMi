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
     */
    Message(String senderName, MessageType type) {
        this.senderName = senderName;
        this.type = type;
    }
    /**
     * Specifies only the message type, used when the sender name will be added in a second moment
     * @param type type of the message
     */
    Message(MessageType type) {
        this.type = type;
    }

    /**
     * @return the sender name
     */
    public String getSenderName() {
        return senderName;
    }

    /**
     * @return the type of the message
     */
    public MessageType getType() {
        return type;
    }

    /**
     * Sets the sender name field of the message as the parameter
     * @param name the sender name to be set
     */
    public void setSenderName(String name) {
        this.senderName =name;
    }
}
