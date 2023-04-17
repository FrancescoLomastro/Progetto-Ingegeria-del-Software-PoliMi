package it.polimi.ingsw.controller;

import it.polimi.ingsw.Network.Messages.Message;

public interface ServerReceiver {
    public void onMessage(Message message);
}
