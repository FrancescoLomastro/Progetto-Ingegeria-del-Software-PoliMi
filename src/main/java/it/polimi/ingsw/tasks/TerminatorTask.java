package it.polimi.ingsw.tasks;

import it.polimi.ingsw.controller.ServerReceiver;
import it.polimi.ingsw.network.Servers.Connection;

import java.util.TimerTask;

public class TerminatorTask extends TimerTask {

    private final ServerReceiver server;
    private final Connection connection;

    public TerminatorTask(ServerReceiver server, Connection connection){
        this.server = server;
        this.connection = connection;
    }
    /**This method is called when a specific timer ends. It will destroy a connection with
     * a specific user because he did not answer to ping
     * */
    @Override
    public void run()
    {
        System.out.println(" Time out connection, player: "+connection.getPlayerName());
        server.tryToDisconnect(connection,connection.getPlayerName());
    }
}
