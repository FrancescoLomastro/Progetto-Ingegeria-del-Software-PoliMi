package it.polimi.ingsw.network.Messages;


import java.io.Serializable;

/**
 * This abstract class need to differentiate that the server can send to the client or vice versa
 */
public abstract class Message implements Serializable {
    private static final long serialVersionUID = 1L;
    private String userName;
    private final MessageType type;

    Message(String userName, MessageType type) {
        this.userName = userName;
        this.type = type;
    }
    Message(MessageType type) {
        this.type = type;
    }

    public String getUsername() {
        return userName;
    }

    public MessageType getType() {
        return type;
    }
    public void setUserName(String name) {
        this.userName=name;
    }
}
