package it.polimi.ingsw.Network.Client;



import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public abstract class Client extends UnicastRemoteObject {
    private transient Status status;
    private final String username;
    private final String serverAddress;
    private final int serverPort;
    private transient View view;
    protected final transient ArrayList<Message> messageQueue;
    public Client(String username, String address, int port ,View view) throws RemoteException
    {
        super();
        this.messageQueue= new ArrayList<>();
        this.username = username;
        this.serverAddress = address;
        this.serverPort = port;
        this.view=view;
        this.status=Status.BEFORE_GAME;
        new Thread(new MessageQueueHandler(this)).start();
    }

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

    public void updateView(Message message) {
        view.update(message);
    }

   public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }
}
