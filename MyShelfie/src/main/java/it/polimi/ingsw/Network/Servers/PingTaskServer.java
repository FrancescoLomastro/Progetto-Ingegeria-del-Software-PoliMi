package it.polimi.ingsw.Network.Servers;

import it.polimi.ingsw.controller.ServerReceiver;

import java.util.TimerTask;

public class PingTaskServer extends TimerTask {

    private final ServerReceiver server;
    private final Connection connection;
    private String playerUsername;

    public PingTaskServer(String playerUsername, ServerReceiver server, Connection connection){
        this.playerUsername = playerUsername;
        this.server = server;
        this.connection = connection;
    }
    @Override
    public void run()
    {
        System.out.println("Connessione scaduta con "+playerUsername);
        server.tryToDisconnect(connection,playerUsername);
    }
}
