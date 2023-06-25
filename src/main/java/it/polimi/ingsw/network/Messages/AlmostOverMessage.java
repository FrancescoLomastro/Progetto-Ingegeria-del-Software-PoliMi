package it.polimi.ingsw.network.Messages;

/**
 * This message is used from the server to report all players in a game when the first library
 * in the game is fully filled with objectCards
 */
public class AlmostOverMessage extends Message{
    private final String fillerName;
    private final int fillerPoints;

    /**
     * Constructor: Creates a message with the filler username and the filler points won by him
     * @param fillerName is the username of the player who filled his library as first
     * @param fillerPoints is the amount of points that the player won as he fills his library
     */
    public AlmostOverMessage(String fillerName, int fillerPoints)
    {
        super("Server",MessageType.ALMOST_OVER_MESSAGE);
        this.fillerName=fillerName;
        this.fillerPoints=fillerPoints;
    }

    /**
     * @return the filler player username
     */
    public String getFillerName() {
        return fillerName;
    }

    /**
     * @return the points the amount of points that the player won as he fills his library
     */
    public int getFillerPoints() {
        return fillerPoints;
    }
}
