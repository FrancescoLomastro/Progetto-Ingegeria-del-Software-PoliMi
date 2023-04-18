package it.polimi.ingsw.Network.Messages;

public class ChatMessage extends Message
{
    private String text;
    public ChatMessage(String userName, String text) {
        super(userName, MessageType.CHAT_MESSAGE);
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
