package it.polimi.ingsw.network.Messages;



public class SocketLoginMessage extends Message {
    public SocketLoginMessage(String username) {
        super(username, MessageType.SOCKET_LOGIN_REQUEST);
    }
}