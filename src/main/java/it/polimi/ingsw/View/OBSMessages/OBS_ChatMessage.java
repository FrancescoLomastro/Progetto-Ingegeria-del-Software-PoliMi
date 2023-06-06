package it.polimi.ingsw.View.OBSMessages;


public class OBS_ChatMessage extends OBS_Message {
    private final String chatMessage;

    public String getChatMessage() {
        return chatMessage;
    }

    public OBS_ChatMessage(String chatMessage) {
        super(OBS_MessageType.CHAT);
        this.chatMessage=chatMessage;
    }
}
