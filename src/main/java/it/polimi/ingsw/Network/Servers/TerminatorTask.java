package it.polimi.ingsw.Network.Servers;

import it.polimi.ingsw.Network.Messages.ServerPingMessage;
import it.polimi.ingsw.controller.GameController;
import it.polimi.ingsw.controller.ServerReceiver;

import java.io.IOException;
import java.util.TimerTask;

public class TerminatorTask extends TimerTask {

    private final ServerReceiver server;
    private final Connection connection;

    public TerminatorTask(ServerReceiver server, Connection connection){
        this.server = server;
        this.connection = connection;
    }
    @Override
    public void run()
    {
        System.out.println("Time out connection, player: "+connection.getPlayerName());
        server.tryToDisconnect(connection,connection.getPlayerName());
    }
}

