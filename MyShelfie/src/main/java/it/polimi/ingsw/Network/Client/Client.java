package it.polimi.ingsw.Network.Client;

import it.polimi.ingsw.Network.Messages.Message;
import it.polimi.ingsw.View.View;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Timer;

/**
 * This class is a generic Client. It can be implemented type RMI or Socket. Exist some generic method */
public abstract class Client extends UnicastRemoteObject {
    private String username;
    private final String serverAddress;
    private final int serverPort;
    protected final transient ArrayList<Message> messageQueue;
    protected final transient long TIMEOUT = 15000;
    protected Timer timer;

    /**Construtor
     * @author: Riccardo Figini
     * */
    public Client(String username, String address, int port) throws RemoteException {
        super();
        this.messageQueue= new ArrayList<>();
        this.username = username;
        this.serverAddress = address;
        this.serverPort = port;
        this.timer=new Timer();
    }

    /**
     * This method is called from MessageQueueHandler thread and return list of message
     * @author: Riccardo Figini
     * @return {@code ArrayList<Message>} list of generic message*/
    public ArrayList<Message> getMessageQueue() {
        ArrayList<Message> list;
        synchronized (messageQueue)
        {
            list= new ArrayList<>(messageQueue);
            messageQueue.clear();
        }
        return list;
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
