package it.polimi.ingsw.network.Messages;

/**
 * This message is an error message sent by a server to a client
 */
public class ErrorMessage extends Message {
    private String errorText;

    /**
     * Constructor: Creates the message with the error text
     * @param errorText the text of the error
     */
    public ErrorMessage(String errorText) {
        super("Server",MessageType.ERROR_MESSAGE);
        this.errorText =errorText;
    }

    /**
     * @return the error text in the message
     */
    public String getErrorText() {
        return errorText;
    }
}
