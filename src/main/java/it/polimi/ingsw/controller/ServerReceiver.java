package it.polimi.ingsw.controller;

import it.polimi.ingsw.network.Messages.Message;
import it.polimi.ingsw.network.Servers.Connection;

/**
 * Interface that identifies an object able to receive message and act as a controller
 */
public interface ServerReceiver {
    /**
     * Used to do actions when a message is recieved
     * @param message the message recieved
     */
    public void onMessage(Message message);

    /**
     * Disconnects a connection with a client
     * @param connection
     * @param playerName
     */
    public void tryToDisconnect(Connection connection, String playerName);
}
