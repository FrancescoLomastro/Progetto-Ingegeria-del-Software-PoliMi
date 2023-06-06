package it.polimi.ingsw.controller;

import it.polimi.ingsw.Network.Messages.Message;
import it.polimi.ingsw.Network.Servers.Connection;

public interface ServerReceiver {
    public void onMessage(Message message);
    public void tryToDisconnect(Connection connection, String playerName);
}
