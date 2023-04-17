package it.polimi.ingsw.controller;

import org.example.Messages.Message;

public interface ServerReceiver {
    public void onMessage(Message message);
}
