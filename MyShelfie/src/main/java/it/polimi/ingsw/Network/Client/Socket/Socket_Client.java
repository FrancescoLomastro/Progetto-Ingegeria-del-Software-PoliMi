package it.polimi.ingsw.Network.Client.Socket;
import it.polimi.ingsw.Network.Client.Client;
import it.polimi.ingsw.Network.Messages.Message;
import it.polimi.ingsw.Network.Messages.SocketLoginMessage;
import it.polimi.ingsw.View.View;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.rmi.RemoteException;
/**Class that manage socket communication */
public class Socket_Client extends Client implements Runnable{
    private transient Socket socket;
    private transient ObjectInputStream in;
    private transient ObjectOutputStream out;
    /**
     * Constructor
     * @author: Riccardo Figini
     * @param address Server's address
     * @param port Server's port
     * @param username Client's name*/
    public Socket_Client(String username, String address, int port) throws RemoteException
    {
        super(username, address, port);
    }
    /**
     * Client makes login on server, to join in a game.
     * It is an Override because the creation of connection depend on RMI/Socket
     * @author: Riccardo Figini
     * */
    @Override
    public void connect() {
        try {
            socket = new Socket(getServerAddress(), getServerPort());
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
            sendMessage(new SocketLoginMessage(getUsername()));
            new Thread(this).start();
        } catch (IOException e)
        {
            throw new RuntimeException("Failed connecting to RMI server "+e);
        }
    }
    /**
     * Send generic message to server
     * @author: Riccardo Figini
     * @param message message*/
    @Override
    public void sendMessage(Message message) throws IOException {
        message.setUserName(getUsername());
        if (out != null) {
            out.writeObject(message);
            out.reset();
        }
    }
    /**
     * This thread is called when client calls method connection. It continues to read
     * stream with message from server
     * @author: Riccardo Figini*/
    @Override
    public void run()
    {
        while (true)
        {
            try {
                Message message = (Message) in.readObject();
                synchronized (messageQueue) {
                    messageQueue.add(message);
                }
            } catch (IOException e) {
                throw new RuntimeException("Failed communication with server " + e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException("Failed communication with server " + e);
            }
        }
    }
}
