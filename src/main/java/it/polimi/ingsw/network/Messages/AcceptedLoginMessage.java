package it.polimi.ingsw.network.Messages;

/**
 * This class is ised as an accepted login message sent from server to a client
 */
public class AcceptedLoginMessage extends Message {
    private final String acceptedName;

    /**
     * Constructor: creates the message inserting an attribute that contains
     * the accepted username string
     * @author: Francesco Lo Mastro
     * @param acceptedName the string representing the username accepted
     */
    public AcceptedLoginMessage(String acceptedName){
        super("Server", MessageType.ACCEPTED_LOGIN_ANSWER);
        this.acceptedName = acceptedName;
    }

    /**It returns the name of player, that is the name of client that make the request
     * @return the accepted username
     * @author: Francesco Lo Mastro
     * @author: Andrea Ferrini
     * @author: Riccardo Figini
     */
    public String getAcceptedName() {
        return acceptedName;
    }
}
