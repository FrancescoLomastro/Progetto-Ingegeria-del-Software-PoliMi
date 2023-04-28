package it.polimi.ingsw.Network.Client;

import it.polimi.ingsw.Network.Messages.Message;
import it.polimi.ingsw.Network.Messages.MessageServerError;
import it.polimi.ingsw.controller.ClientController;

import java.io.IOException;
import java.util.ArrayList;

public class TimerClientMessageQueue implements Runnable{

    private final Client client;
    private ClientController clientController;

    private int messageQueueSize;

    public TimerClientMessageQueue(Client client, ClientController clientController) {

        this.client = client;
        this.clientController = clientController;
    }

    @Override
    public void run() {

        try {
            Thread.sleep(120000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        MessageServerError messageServerError = new MessageServerError();
        try {
            client.sendMessage(messageServerError);

            //opzionale
            System.out.println(messageServerError.getDescription());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.exit(0);
    }
}
