package it.polimi.ingsw.network.Messages;
import it.polimi.ingsw.controller.GameController;
import it.polimi.ingsw.controller.ServerReceiver;

/**
 * This message is used for the process of server changing when a Game is created. Messages will be forwarded to a GameController instead of Controller
 * This message arrives from controller RMI and Socket network interface.
 * Server Socket interface will use this message to change their serverReceiver
 * Server RMI interface do not use directly this message, but client RMI will use this message to find a new shared RMI server.
 */
public class NewGameServerMessage extends Message{
    private final String RMIserverName;
    private final int RMIServerPort;
    private final transient ServerReceiver serverReceiver;

    /**
     * Constructor: Creates a message with a the name and the port of the new server.
     * @param RMIserverName a string representing the name of the new server (needed for RMI registry in client side)
     * @param RMIServerPort an integer indicating the new game port (needed for RMI registry in client side)
     * @param serverReceiver a new server receiver where incoming message will be forwarded. Not transmitted in the network (needed for socket server interface)
     */
    public NewGameServerMessage(String RMIserverName, int RMIServerPort, GameController serverReceiver){
        super(MessageType.NEW_GAME_SERVER_MESSAGE);
        this.RMIserverName = RMIserverName;
        this.RMIServerPort = RMIServerPort;
        this.serverReceiver = serverReceiver;
    }

    /**
     * @return a string representing the name of the new server (needed for RMI registry in client side)
     */
    public String getServerName() {
        return RMIserverName;
    }

    /**
     * @return an integer indicating the new game port (needed for RMI registry in client side)
     */
    public int getRMIServerPort() {
        return RMIServerPort;
    }

    /**
     * @return a new server receiver where incoming message will be forwarded (needed for socket server interface)
     */
    public ServerReceiver getServerReceiver() {
        return serverReceiver;
    }
}
