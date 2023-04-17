package it.polimi.ingsw.Network.Messages;


import java.io.Serializable;

/**
 * This abstract class need to differentiate that the server can send to the client or vice versa
 */
public abstract class Message implements Serializable {
    private static final long serialVersionUID = 1L;
    private final String userName;
    private final MessageType type;

    Message(String userName, MessageType type) {
        this.userName = userName;
        this.type = type;
    }
    Message(MessageType type) {
        this.userName = "Unknow";
        this.type = type;
    }

    public String getUsername() {
        return userName;
    }

    public MessageType getType() {
        return type;
    }
}
