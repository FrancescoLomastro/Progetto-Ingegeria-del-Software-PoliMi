package it.polimi.ingsw.network.Messages;

/**
 * This message is an error message sent by a server to a client
 */
public class ErrorMessage extends Message {
    private String errorText;

    /**
     * Constructor: Creates the message with the error text
     * @param errorText the text of the error
     * @author: Francesco Gregorio Lo Mastro
     * @author: Andrea Ferrini
     * @author: Riccardo Figini
     */
    public ErrorMessage(String errorText) {
        super("Server",MessageType.ERROR_MESSAGE);
        this.errorText =errorText;
    }

    /**It returns the text that specifies the error
     * @return the error text in the message
     * @author: Francesco Gregorio Lo Mastro
     * @author: Andrea Ferrini
     * @author: Riccardo Figini
     */
    public String getErrorText() {
        return errorText;
    }
}
