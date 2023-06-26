package it.polimi.ingsw.network.Client;

import it.polimi.ingsw.network.Messages.Message;
import it.polimi.ingsw.network.Messages.ServerPingMessage;
import it.polimi.ingsw.tasks.PingTaskClient;
import it.polimi.ingsw.controller.ClientController;

import java.io.IOException;
import java.util.Timer;

/**This class manages queue containing messages. It has a thread that every n-millisecond controls if something is arrived from server*/
public class PingHandler implements Runnable {
    private final Client client;
    private ClientController clientController;
    protected final int PING_TIMEOUT = 10000;
    private Timer pingTimer;
    private boolean timerScheduled;

    /**
     * Constructor
     * @author: Riccardo Figini*/
    public PingHandler(Client client, ClientController clientController) {
        this.client=client;
        this.clientController= clientController;
        this.pingTimer = new Timer();
        this.timerScheduled=false;
    }
    /**
     * It controls every 200 millis if exist message from server
     * @author: Riccardo Figini*/
    @Override
    public void run() {
        Message message;
        while (true)
        {
            message=client.getPingMessage();
            if(message!=null)
            {
                if(timerScheduled)
                {
                    pingTimer.cancel();
                }
                timerScheduled=true;
                pingTimer=new Timer();
                pingTimer.schedule(new PingTaskClient(clientController),PING_TIMEOUT);
                try
                {
                    client.sendMessage(new ServerPingMessage(client.getUsername()));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
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
    /**It is called when server sends an error and ping needs to be stopped
     * @author: Riccardo Figini
     * */
    public void shutDown() {
        pingTimer.cancel();
        timerScheduled=true;
    }
}
