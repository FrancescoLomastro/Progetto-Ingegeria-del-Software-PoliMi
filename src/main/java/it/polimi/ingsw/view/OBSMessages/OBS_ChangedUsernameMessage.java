package it.polimi.ingsw.view.OBSMessages;

/**
 * Message used by a View to notify client controller the new username to use
 * @author Francesco Lo Mastro 
 * */
public class OBS_ChangedUsernameMessage extends OBS_Message {
    private final String changedUsername;

    /**
     * This method return the new username chosen by the player.
     *
     * @author Francesco Lo Mastro
     */
    public String getChangedUsername() {
        return changedUsername;
    }
    /** Contructor: Creates the message
     * @author Franceco Lo Mastro
     * @param changedUsername new player's name
     * */
    public OBS_ChangedUsernameMessage(String changedUsername) {
        super(OBS_MessageType.CHANGED_USERNAME);
        this.changedUsername= changedUsername;
    }
}
