package it.polimi.ingsw.view.OBSMessages;

/**
 * This message is used only to comunicate a message type to client controller
 */
public class OBS_OnlyTypeMessage extends OBS_Message {

    /**
     * This method is used to construct a only type message to be sent to the server.
     *
     * @param o: message type;
     * @author Francesco Lo Mastro
     */
    public OBS_OnlyTypeMessage(OBS_MessageType o) {
        super(o);
    }
}
