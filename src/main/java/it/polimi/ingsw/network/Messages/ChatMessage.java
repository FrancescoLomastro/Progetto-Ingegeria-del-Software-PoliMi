package it.polimi.ingsw.network.Messages;

/**
 * This message contains a chat text that represents a chat message sent from a player to another
 */
public class ChatMessage extends Message
{
    private String text;

    /**
     * Constructor: Creates a chat message with text and sender name
     * @param userName is the username of the sender of the message
     * @param text is the text of the chat message
     * @author: Francesco Lo Mastro
     */
    public ChatMessage(String userName, String text) {
        super(userName, MessageType.CHAT_MESSAGE);
        this.text = text;
    }

    /**
     * It gets the message
     * @author: Francesco Lo Mastro
     * @return the text of the chat message
     */
    public String getText() {
        return text;
    }
}
