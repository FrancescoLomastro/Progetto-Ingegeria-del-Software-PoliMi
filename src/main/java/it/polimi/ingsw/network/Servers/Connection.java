package it.polimi.ingsw.network.Servers;
import it.polimi.ingsw.network.Messages.Message;
import it.polimi.ingsw.network.StatusNetwork;
import it.polimi.ingsw.controller.GameController;
import it.polimi.ingsw.controller.ServerReceiver;

import java.io.IOException;
import java.util.Timer;
/**
 * This class is used to send or receive message from client. It represents generic connection that can be
 * RMI or socket. */
public abstract class Connection {
    private transient Timer pingerTimer;
    private transient Timer terminatorTimer;
    protected String playerName;
    public abstract void sendMessage(Message message) throws IOException;
    public abstract StatusNetwork getStatusNetwork();
    public abstract void setStatusNetwork(StatusNetwork statusNetwork);
    public abstract String getPlayerName();
    public abstract void setPlayerName(String playerName);
    /**It resets timer
     * @param server Server receiver
     * */
    public void resetTimer(ServerReceiver server) {
        pingerTimer.cancel();
        terminatorTimer.cancel();
        pingerTimer =new Timer();
        terminatorTimer= new Timer();
        pingerTimer.schedule(new PingerTask(server,this),GameController.PING_GAP);
        terminatorTimer.schedule(new TerminatorTask(server,this), GameController.PING_TIMEOUT);
    }
    /**It starts ping timer at the first time
     * @param server Server (controller or gameController) that implement "ServerReceiver". It handles connection error
     * */
    public void startTimer(ServerReceiver server) {
        pingerTimer = new Timer();
        terminatorTimer = new Timer();
        pingerTimer.schedule(new PingerTask(server,this),0);
        terminatorTimer.schedule(new TerminatorTask(server,this), GameController.PING_TIMEOUT);
    }
    /**It destroys player/connection's ping. Ping message will stop
     * @author: Francesco Gregorio Lo Mastro*/
    public void destroyPing(){
        pingerTimer.cancel();
    }
}
