package it.polimi.ingsw.utility;

import it.polimi.ingsw.network.Servers.Connection;

import java.io.Serializable;


public class Request implements Serializable {
    private static final long serialVersionUID = 1L;
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
