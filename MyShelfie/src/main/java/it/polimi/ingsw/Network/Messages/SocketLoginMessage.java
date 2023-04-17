package it.polimi.ingsw.Network.Messages;

import org.example.Clients.RMIClientConnection;

public class SocketLoginMessage extends Message {
    public SocketLoginMessage(String username) {
        super(username, MessageType.SOCKET_LOGIN_REQUEST);
    }
}