package it.polimi.ingsw.controller;

import it.polimi.ingsw.network.Messages.Message;
import it.polimi.ingsw.network.Servers.Connection;

public interface ServerReceiver {
    public void onMessage(Message message);
    public void tryToDisconnect(Connection connection, String playerName);
}
