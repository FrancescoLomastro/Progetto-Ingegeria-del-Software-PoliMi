package it.polimi.ingsw.view.OBSMessages;
/**This class handles a chat message */
public class OBS_ChatMessage extends OBS_Message {
    private final String chatMessage;

    /**
     * This method return the new chat message to be written in the chat.
     *
     * @author Francesco Gregorio Lo Mastro
     */
    public String getChatMessage() {
        return chatMessage;
    }
    /**It sets a message, it will be sent to server
     * @author: Francesco Gregorio Lo Mastro
     * @param chatMessage chat's message
     * */
    public OBS_ChatMessage(String chatMessage) {
        super(OBS_MessageType.CHAT);
        this.chatMessage=chatMessage;
    }
}
