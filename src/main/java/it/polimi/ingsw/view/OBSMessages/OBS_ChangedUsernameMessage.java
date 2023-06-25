package it.polimi.ingsw.view.OBSMessages;
/**This message is used to update player's name if it is not available
 * @author: Francesco gregorio Lo Mastro */
public class OBS_ChangedUsernameMessage extends OBS_Message {
    private final String changedUsername;
    public String getChangedUsername() {
        return changedUsername;
    }
    /**It sets a new name
     * @author: Franceco Gregorio Lo Mastro
     * @param changedUsername new player's name
     * */
    public OBS_ChangedUsernameMessage(String changedUsername) {
        super(OBS_MessageType.CHANGED_USERNAME);
        this.changedUsername= changedUsername;
    }
}
