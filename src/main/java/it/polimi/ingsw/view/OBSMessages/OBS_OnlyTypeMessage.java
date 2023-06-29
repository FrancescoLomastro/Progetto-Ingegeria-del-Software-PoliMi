package it.polimi.ingsw.view.OBSMessages;
/**This class is an auxiliary class used to communicate a type of message*/
public class OBS_OnlyTypeMessage extends OBS_Message {

    /**
     * This method is used to construct a only type message to be sent to the server.
     *
     * @param o: message type;
     * @author Francesco Gregorio Lo Mastro
     */
    public OBS_OnlyTypeMessage(OBS_MessageType o) {
        super(o);
    }
}
