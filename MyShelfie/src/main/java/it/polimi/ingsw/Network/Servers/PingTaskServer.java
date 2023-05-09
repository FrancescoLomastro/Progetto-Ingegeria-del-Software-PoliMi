package it.polimi.ingsw.Network.Servers;

import java.util.TimerTask;

public class PingTaskServer extends TimerTask {

    private String playerUsername;

    public PingTaskServer(String playerUsername){

        this.playerUsername = playerUsername;
    }
    @Override
    public void run()
    {
        System.out.println("Connection timed out with " + playerUsername);
        //System.exit(1);
    }
}
