package it.polimi.ingsw.tasks;

import it.polimi.ingsw.network.Messages.ServerPingMessage;
import it.polimi.ingsw.controller.ServerReceiver;
import it.polimi.ingsw.network.Servers.Connection;

import java.io.IOException;
import java.util.TimerTask;

public class PingerTask extends TimerTask {

    private final ServerReceiver server;
    private final Connection connection;

    public PingerTask(ServerReceiver server, Connection connection){
        this.server = server;
        this.connection = connection;
    }
    /**This method sends a ping message to a client, in case of error call tryDoDisconnect
     * */
    @Override
    public void run()
    {
            try {
                connection.sendMessage(new ServerPingMessage(connection.getPlayerName()));
            } catch (IOException e) {
                connection.destroyPing();
                server.tryToDisconnect(connection, connection.getPlayerName());
            }
    }
}
