package it.polimi.ingsw.view.OBSMessages;
/**
 * This abstract representation for OBS_Message
 */
public abstract class OBS_Message {
    private final OBS_MessageType type;
    OBS_Message(OBS_MessageType type) {
        this.type = type;
    }

    /**
     * This method return the type of the message.
     *
     * @author Francesco Lo Mastro
     */
    public OBS_MessageType getType() {
        return type;
    }
}
