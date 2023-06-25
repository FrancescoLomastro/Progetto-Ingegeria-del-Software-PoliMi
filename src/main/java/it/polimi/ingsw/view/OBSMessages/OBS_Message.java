package it.polimi.ingsw.view.OBSMessages;
/**
 * This abstract class need to differentiate that the server can send to the client or vice versa
 */
public abstract class OBS_Message {
    private final OBS_MessageType type;
    OBS_Message(OBS_MessageType type) {
        this.type = type;
    }
    public OBS_MessageType getType() {
        return type;
    }
}
