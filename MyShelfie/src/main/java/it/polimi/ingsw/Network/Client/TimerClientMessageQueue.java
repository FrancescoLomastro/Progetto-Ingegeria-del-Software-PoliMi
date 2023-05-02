package it.polimi.ingsw.Network.Client;


import it.polimi.ingsw.Network.Messages.MessageServerError;
import it.polimi.ingsw.controller.ClientController;

import java.io.IOException;
import java.util.ArrayList;

public class TimerClientMessageQueue implements Runnable{
    private final Client client;
    public TimerClientMessageQueue(Client client) {
        this.client = client;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(10000);
            MessageServerError messageServerError = new MessageServerError();
            try {
                client.sendMessage(messageServerError);
                System.out.println(messageServerError.getDescription());
            } catch (IOException e) {
                return;
            }
            System.exit(0);
        } catch (InterruptedException ignored) {
        }
    }
}
