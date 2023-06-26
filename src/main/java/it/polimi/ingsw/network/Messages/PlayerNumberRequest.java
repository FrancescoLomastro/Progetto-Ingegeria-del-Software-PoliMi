package it.polimi.ingsw.network.Messages;


/**
 * This message is used by the server to send a client a request for the max number of players to include in the lobby
 */
public class PlayerNumberRequest extends Message {
    private int minimumPlayers;
    private int maximumPlayers;

    /**
     * Constructor: Create a player number request with 2 limits for the number
     * @param minimumPlayers inferior limit
     * @param maximumPlayers superior limit
     */
    public PlayerNumberRequest(int minimumPlayers, int maximumPlayers) {
        super("Server",MessageType.PLAYER_NUMBER_REQUEST);
        this.maximumPlayers=maximumPlayers;
        this.minimumPlayers=minimumPlayers;
    }

    /**
     * @return the inferior limit of player allowed in a game
     */
    public int getMinimumPlayers() {
        return minimumPlayers;
    }

    /**
     * @return the superior limit of player allowed in a game
     */
    public int getMaximumPlayers() {
        return maximumPlayers;
    }
}
