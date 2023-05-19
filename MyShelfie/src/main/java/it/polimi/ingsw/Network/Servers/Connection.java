package it.polimi.ingsw.Network.Servers;



import it.polimi.ingsw.Network.Messages.Message;
import it.polimi.ingsw.Network.StatusNetwork;
import it.polimi.ingsw.controller.ServerReceiver;

import java.io.IOException;
import java.util.Timer;

public abstract class Connection {
    private transient Timer timer;
    protected String playerName;
    public abstract void sendMessage(Message message) throws IOException;
    public abstract StatusNetwork getStatusNetwork();
    public abstract void setStatusNetwork(StatusNetwork statusNetwork);
    public abstract String getPlayerName();
    public abstract void setPlayerName(String playerName);


    public void resetTimer(int time, ServerReceiver server) {
        timer.cancel();
        timer=new Timer();
        timer.schedule(new PingTaskServer(playerName,server,this),time);
    }

    public void startTimer(int time,ServerReceiver server) {
        timer = new Timer();
        timer.schedule(new PingTaskServer(playerName,server,this),time);
    }
}
