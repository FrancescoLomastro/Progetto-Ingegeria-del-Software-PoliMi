package it.polimi.ingsw.Network.Client;

import it.polimi.ingsw.Network.Messages.Message;
import java.util.ArrayList;
/**This class manages queue containing messages. It has a thread that every n-millisecond controls if something is arrived from server*/
public class MessageQueueHandler implements Runnable {
    private final Client client;
    /**
     * Constructor
     * @author: Riccardo Figini*/
    public MessageQueueHandler(Client client) {
        this.client=client;
    }
    /**
     * It controls every 500 millis if exist message from server
     * @author: Riccardo Figini*/
    @Override
    public void run() {
        ArrayList<Message> list=null;
        while (true)
        {
            list=client.getMessageQueue();
            if(list.size()!=0)
            {
                list.forEach(client::updateView);
            }
            else
            {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
