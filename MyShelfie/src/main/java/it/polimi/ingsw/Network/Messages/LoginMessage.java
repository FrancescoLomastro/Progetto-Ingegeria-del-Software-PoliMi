package it.polimi.ingsw.Network.Messages;


import it.polimi.ingsw.Network.Servers.Connection;

public class LoginMessage extends Message{
    private Connection clientConnection;
    public LoginMessage(String userName, Connection connection) {
        super(userName, MessageType.LOGIN_REQUEST);
        this.clientConnection=connection;
    }

    public Connection getClientConnection() {
        return clientConnection;
    }
}
