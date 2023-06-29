package it.polimi.ingsw.view.OBSMessages;
/** This message represents a chat message sent from view to client controller*/
public class OBS_ChatMessage extends OBS_Message {
    private final String chatMessage;

    /**
     * This method return the new chat message to be written in the chat.
     *
     * @author Francesco Lo Mastro
     */
    public String getChatMessage() {
        return chatMessage;
    }

    /** Contructor: Creates the message
     * @author: Francesco Lo Mastro
     * @param chatMessage chat's message
     * */
    public OBS_ChatMessage(String chatMessage) {
        super(OBS_MessageType.CHAT);
        this.chatMessage=chatMessage;
    }
}
