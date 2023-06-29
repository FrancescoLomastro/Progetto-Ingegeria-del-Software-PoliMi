package it.polimi.ingsw.tasks;

import it.polimi.ingsw.controller.ClientController;
import it.polimi.ingsw.controller.ServerReceiver;
import it.polimi.ingsw.network.Messages.ChatMessage;
import it.polimi.ingsw.network.Messages.ErrorMessage;
import it.polimi.ingsw.network.Servers.Connection;

import java.io.IOException;
import java.util.TimerTask;

public class NumberRequestTask extends TimerTask {
    private final ServerReceiver server;
    private final Connection connection;

    public NumberRequestTask(ServerReceiver server, Connection connection) {
        this.server = server;
        this.connection = connection;
    }

    /**
     * This method is called when a specific timer ends. It will destroy a connection with
     * a specific user because he did not answer to ping
     * @author Francesco Lo Mastro
     */
    @Override
    public void run() {
        System.out.println("Time out for a reply by player: " + connection.getPlayerName());
        try {
            connection.sendMessage(new ErrorMessage("Too much time has passed for a reply"));
        } catch (IOException e) {
            System.out.println("Could not inform client of time expiration for a reply to a lobby number request");
        }
        server.tryToDisconnect(connection, connection.getPlayerName());
    }
}