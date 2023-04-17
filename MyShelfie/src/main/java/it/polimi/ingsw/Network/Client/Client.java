package it.polimi.ingsw.Network.Client;

import it.polimi.ingsw.Network.Messages.Message;
import it.polimi.ingsw.View.View;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
/**
 * This class is a generic Client. It can be implemented type RMI or Socket. Exist some generic method */
public abstract class Client extends UnicastRemoteObject {
    private final String username;
    private final String serverAddress;
    private final int serverPort;
    private transient View view;
    protected final transient ArrayList<Message> messageQueue;
    /**Construtor
     * @author: Riccardo Figini
     * */
    public Client(String username, String address, int port ,View view) throws RemoteException
    {
        super();
        this.messageQueue= new ArrayList<>();
        this.username = username;
        this.serverAddress = address;
        this.serverPort = port;
        this.view=view;
        new Thread(new MessageQueueHandler(this)).start();
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
    /**Update view sending a message with "command"
     * @author: Riccardo Figini
     * @param message Message from server*/
    public void updateView(Message message) {
        view.update(message);
    }

}
