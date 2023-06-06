package it.polimi.ingsw.Network.Messages;

import java.util.List;

public class LobbyUpdateMessage extends Message {
    private final List<String> usernames;
    private final int limitOfPlayers;
    private final String textFromServer;
    public LobbyUpdateMessage(List<String> usernames, int limitOfPlayers, String textFromServer) {
        super("Server",MessageType.LOBBY_UPDATE_MESSAGE);
        this.usernames=usernames;
        this.textFromServer=textFromServer;
        this.limitOfPlayers=limitOfPlayers;
    }

    public String getTextFromServer() {
        return textFromServer;
    }

    public List<String> getUsernames() {
        return usernames;
    }

    public int getLimitOfPlayers() {
        return limitOfPlayers;
    }
}
