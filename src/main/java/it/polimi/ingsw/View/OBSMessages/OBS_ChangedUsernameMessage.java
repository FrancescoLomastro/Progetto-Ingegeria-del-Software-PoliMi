package it.polimi.ingsw.View.OBSMessages;

public class OBS_ChangedUsernameMessage extends OBS_Message {
    private String changedUsername;

    public String getChangedUsername() {
        return changedUsername;
    }

    public OBS_ChangedUsernameMessage(String changedUsername) {
        super(OBS_MessageType.CHANGED_USERNAME);
        this.changedUsername= changedUsername;
    }
}
