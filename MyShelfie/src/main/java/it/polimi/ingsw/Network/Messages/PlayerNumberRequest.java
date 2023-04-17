package it.polimi.ingsw.Network.Messages;


public class PlayerNumberRequest extends Message {
    private int minimumPlayers;
    private int maximumPlayers;
    public PlayerNumberRequest(int minimumPlayers, int maximumPlayers) {
        super("Server",MessageType.PLAYERNUMBER_REQUEST);
        this.maximumPlayers=maximumPlayers;
        this.minimumPlayers=minimumPlayers;
    }

    public int getMinimumPlayers() {
        return minimumPlayers;
    }

    public int getMaximumPlayers() {
        return maximumPlayers;
    }
}
