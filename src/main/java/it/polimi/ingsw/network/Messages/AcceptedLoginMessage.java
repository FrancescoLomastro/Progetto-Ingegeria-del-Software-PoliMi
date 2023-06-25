package it.polimi.ingsw.network.Messages;

/**
 * This class is ised as an accepted login message sent from server to a client
 */
public class AcceptedLoginMessage extends Message {
    private final String acceptedName;

    /**
     * Constructor: creates the message inserting an attribute that contains
     * the accepted username string
     * @param acceptedName the string representing the username accepted
     */
    public AcceptedLoginMessage(String acceptedName){
        super("Server", MessageType.ACCEPTED_LOGIN_ANSWER);
        this.acceptedName = acceptedName;
    }

    /**
     * @return the accepted username
     */
    public String getAcceptedName() {
        return acceptedName;
    }
}
