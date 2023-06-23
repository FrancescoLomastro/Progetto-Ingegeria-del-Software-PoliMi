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

    public ArrayList<Message> getCommunicationMessageQueue() {
        ArrayList<Message> list;
        synchronized (communicationMessageQueue)
        {
            list= new ArrayList<>(communicationMessageQueue);
            communicationMessageQueue.clear();
        }
        return list;
    }
    public Message getPingMessage() {
        Message msg;
        synchronized (pingMessage)
        {
            msg= pingMessage.orElse(null);
            pingMessage = Optional.empty();
        }
        return msg;
    }

    public void changeUsername(String username)
    {
        this.username=username;
        newUsernameProposal();
    }

    public abstract void newUsernameProposal();

    public String getUsername() {
        return username;
    }


    public String getServerAddress() {
        return serverAddress;
    }


    public int getServerPort() {
        return serverPort;
    }


    public abstract void connect();


    public abstract void sendMessage(Message message) throws IOException;


}
