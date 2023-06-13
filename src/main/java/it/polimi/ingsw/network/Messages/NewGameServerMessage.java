package it.polimi.ingsw.network.Messages;
import it.polimi.ingsw.controller.GameController;
public class NewGameServerMessage extends Message{
    private final String server;
    private final int port;
    private final transient GameController gameController;
    public NewGameServerMessage(String server, int port, GameController gameController){
        super(MessageType.NEW_GAME_SERVER_MESSAGE);
        this.server=server;
        this.port=port;
        this.gameController=gameController;
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
