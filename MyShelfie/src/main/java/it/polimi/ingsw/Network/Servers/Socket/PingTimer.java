package it.polimi.ingsw.Network.Servers.Socket;

import java.util.Timer;

public class PingTimer extends Timer {

    private String playerUsername;

    public String getPlayerUsername() {
        return playerUsername;
    }

    public void setPlayerUsername(String playerUsername) {
        this.playerUsername = playerUsername;
    }
}
