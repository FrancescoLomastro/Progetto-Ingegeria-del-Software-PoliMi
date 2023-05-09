package it.polimi.ingsw.Network.Client;

import it.polimi.ingsw.Network.Messages.Message;
import it.polimi.ingsw.controller.ClientController;

import java.io.IOException;
import java.util.ArrayList;
/**This class manages queue containing messages. It has a thread that every n-millisecond controls if something is arrived from server*/
public class MessageQueueHandler implements Runnable {
    private final Client client;
    private ClientController clientController;

    /**
     * Constructor
     * @author: Riccardo Figini*/
    public MessageQueueHandler(Client client,ClientController clientController) {
        this.client=client;
        this.clientController= clientController;
    }
    /**
     * It controls every 200 millis if exist message from server
     * @author: Riccardo Figini*/
    @Override
    public void run() {

        TimerClientMessageQueue timerClientMessageQueue = new TimerClientMessageQueue(client);

        Thread timeThread = new Thread(timerClientMessageQueue);
        timeThread.start();

        ArrayList<Message> list = null;

        while (true)
        {
            list=client.getMessageQueue();
            if(list!=null && list.size()!=0)
            {
                list.forEach(x-> {
                    try {
                        clientController.onMessage(x);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });

                timeThread.interrupt();
                try {
                    timeThread.join();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                timeThread = new Thread(timerClientMessageQueue);
                timeThread.start();
            }
            else
            {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
