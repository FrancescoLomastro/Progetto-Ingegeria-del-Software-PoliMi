package it.polimi.ingsw.Network.Messages;

import org.example.Controller.GameController;

public class NewGameServerMessage extends Message{
    private final String server;
    private final int port;
    private transient GameController gameController;
    public NewGameServerMessage(String server, int port, GameController gameController){
        super(MessageType.NEW_GAME_SERVER_MESSAGE);
        this.server=server;
        this.port=port;
    }
    public String getServerName() {
        return server;
    }
    public int getPort() {
        return port;
    }
    public GameController getGameController() {
        return gameController;
    }
}
