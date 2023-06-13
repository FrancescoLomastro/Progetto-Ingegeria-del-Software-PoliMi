package it.polimi.ingsw.network.Messages;

public class AlmostOverMessage extends Message{
    private final String fillerName;
    private final int fillerPoints;
    public AlmostOverMessage(String fillerName, int fillerPoints)
    {
        super(MessageType.ALMOST_OVER);
        this.fillerName=fillerName;
        this.fillerPoints=fillerPoints;
    }
    public String getFillerName() {
        return fillerName;
    }

    public int getFillerPoints() {
        return fillerPoints;
    }
}
