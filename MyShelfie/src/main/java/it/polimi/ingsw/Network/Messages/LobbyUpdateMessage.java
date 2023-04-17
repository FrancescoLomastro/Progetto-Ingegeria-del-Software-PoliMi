package it.polimi.ingsw.Network.Messages;

import java.util.List;

public class LobbyUpdateMessage extends Message {
    private List<String> usernames;
    private int limitOfPlayers;
    public LobbyUpdateMessage(List<String> usernames, int limitOfPlayers ) {
        super("Server",MessageType.LOBBYUPDATE_MESSAGE);
        this.usernames=usernames;
        this.limitOfPlayers=limitOfPlayers;
    }

    public List<String> getUsernames() {
        return usernames;
    }

    public int getLimitOfPlayers() {
        return limitOfPlayers;
    }
}
