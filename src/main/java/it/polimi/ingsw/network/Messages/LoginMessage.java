package it.polimi.ingsw.network.Messages;


import it.polimi.ingsw.network.Servers.Connection;

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
