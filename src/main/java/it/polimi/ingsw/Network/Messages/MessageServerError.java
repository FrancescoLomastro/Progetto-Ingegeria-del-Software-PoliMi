package it.polimi.ingsw.Network.Messages;

public class MessageServerError extends Message{

    // opzionale
    private String description = "the server is not responding for unknown reasons";
    public MessageServerError(){

        super(MessageType.SERVER_ERROR);
    }

    public String getDescription() {
        return description;
    }
}
