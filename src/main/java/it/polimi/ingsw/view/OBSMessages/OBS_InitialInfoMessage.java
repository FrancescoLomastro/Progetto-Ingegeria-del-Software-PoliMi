package it.polimi.ingsw.view.OBSMessages;
/**This message is used to create "client" rmi or socket*/
public class OBS_InitialInfoMessage extends OBS_Message {
    private final String chosenUsername;
    private final int chosenTechnology;
    private final String chosenAddress;
    private final int chosenPort;

    /**
     * This method return the username chosen by the player.
     *
     * @author Francesco Gregorio Lo Mastro
     */
    public String getChosenUsername() {
        return chosenUsername;
    }

    /**
     * This method return the network technology chosen by the player.
     *
     * @author Francesco Gregorio Lo Mastro
     */
    public int getChosenTechnology() {
        return chosenTechnology;
    }

    /**
     * This method return the address chosen by the player.
     *
     * @author Francesco Gregorio Lo Mastro
     */
    public String getChosenAddress() {
        return chosenAddress;
    }

    /**
     * This method return the port chosen by the player.
     *
     * @author Francesco Gregorio Lo Mastro
     */
    public int getChosenPort() {
        return chosenPort;
    }

    /**It sets all information used to create a new client (RMI or socket)
     * @author: Francesco Gregorio Lo Mastro
     * @param chosenAddress Server's address
     * @param chosenPort Server's port
     * @param chosenTechnology Communication technology (RMI or Socket)
     * @param chosenUsername Player's username
     * */
    public OBS_InitialInfoMessage(String chosenUsername, int chosenTechnology, String chosenAddress, int chosenPort) {
        super(OBS_MessageType.INITIAL_INFO);
        this.chosenUsername=chosenUsername;
        this.chosenTechnology=chosenTechnology;
        this.chosenAddress=chosenAddress;
        this.chosenPort=chosenPort;
    }
}
