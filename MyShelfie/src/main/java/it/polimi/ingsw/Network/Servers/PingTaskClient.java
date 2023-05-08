package it.polimi.ingsw.Network.Servers;

import java.util.TimerTask;

public class PingTaskClient extends TimerTask {

    @Override
    public void run()
    {
        System.out.println("Connection timed out with server");
        System.exit(1);
    }
}
