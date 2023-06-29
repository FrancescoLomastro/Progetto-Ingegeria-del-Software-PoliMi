package it.polimi.ingsw.network.Client;
import it.polimi.ingsw.network.Messages.Message;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Optional;
/**
 * This class is a generic Client. It can be implemented type RMI or Socket. Exists some generic method */
public abstract class Client extends UnicastRemoteObject {
    private String username;
    private final String serverAddress;
    private final int serverPort;
    protected final transient ArrayList<Message> communicationMessageQueue;
    protected transient Optional<Message> pingMessage;

    /**Construtor
     * @author: Riccardo Figini
     * */
    public Client(String username, String address, int port) throws RemoteException {
        super();
        this.communicationMessageQueue= new ArrayList<>();
        this.pingMessage=  Optional.empty();
        this.username = username;
        this.serverAddress = address;
        this.serverPort = port;
    }
    /**This method returs ad arraylist with messages from server, and clear the queue where messages
     * were contained
     * @author: Francesco Lo Mastro
     * @return {@code ArrayList<Message>} An array with messages
     * */
    public ArrayList<Message> getCommunicationMessageQueue() {
        ArrayList<Message> list;
        synchronized (communicationMessageQueue)
        {
            list= new ArrayList<>(communicationMessageQueue);
            communicationMessageQueue.clear();
        }
        return list;
    }
    /**It returns a ping message if exists, then deletes the ping
     * @author: Francesco Lo Mastro
     * @return {@code Message}
     * */
    public Message getPingMessage() {
        Message msg;
        synchronized (pingMessage)
        {
            msg= pingMessage.orElse(null);
            pingMessage = Optional.empty();
        }
        return msg;
    }
    /**This method is called to send server new name for user connection
     * @author: Francesco Lo Mastro
     * @param username Player's new username */
    public void changeUsername(String username)
    {
        this.username=username;
        newUsernameProposal();
    }
    /**Abstract method that creates a new connection with server with a new name
     * @author: Francesco Lo Mastro
     * */
    public abstract void newUsernameProposal();
    /**
     * It returns the name of player
     * @author: Francesco Lo Mastro
     * @return String with the name
     * */
    public String getUsername() {
        return username;
    }
    /**It returns the IP address of server
     * @author: Francesco Lo Mastro
     * @return String with IP address*/
    public String getServerAddress() {
        return serverAddress;
    }
    /**It returns and int with the number of the port of server
     * @author: Francesco Lo Mastro
     * @return int with port number
     * */
    public int getServerPort() {
        return serverPort;
    }
    /**Abstract method implemented in an RMI and Socket version, it connects a client to the server
     * when a client has all infromation
     * @author: Francesco Lo Mastro*/
    public abstract void connect();
    /**It is an abstract method implemented in RMI and socket that send message
     * to server
     * @author: Francesco Lo Mastro
     * @param message to be sent
     * */
    public abstract void sendMessage(Message message) throws IOException;
}
