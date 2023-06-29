package it.polimi.ingsw.network.Client;

import it.polimi.ingsw.network.Messages.Message;
import it.polimi.ingsw.controller.ClientController;

import java.util.ArrayList;

/**This class manages queue containing messages.
 * It has a thread that every n-millisecond controls if something is arrived from server.
 * This class only implements method to access messages, but it has not the queue It
 * can reach the queue thanks to "client" reference*/
public class CommunicationQueueHandler implements Runnable {
    private final Client client;
    private final ClientController clientController;

    /**
     * Constructor
     * @author: Riccardo Figini
     * */
    public CommunicationQueueHandler(Client client, ClientController clientController) {
        this.client=client;
        this.clientController= clientController;
    }
    /**
     * It controls every 200 millis if exist message from server
     * @author: Riccardo Figini
     * @author: Francesco Lo Mastro
     * */
    @Override
    public void run() {
        ArrayList<Message> list;
        while (true)
        {
            list=client.getCommunicationMessageQueue();
            if(list!=null && list.size()!=0)
            {
                list.forEach(clientController::onMessage);
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
