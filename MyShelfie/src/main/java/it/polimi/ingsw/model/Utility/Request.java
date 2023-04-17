package it.polimi.ingsw.model.Utility;

import org.example.Servers.Connection;

public class Request {
    private String username;
    private Connection connection;
    public Request(String username, Connection connection) {
        this.connection=connection;
        this.username=username;
    }

    public String getUsername() {
        return username;
    }

    public Connection getConnection() {
        return connection;
    }


}
