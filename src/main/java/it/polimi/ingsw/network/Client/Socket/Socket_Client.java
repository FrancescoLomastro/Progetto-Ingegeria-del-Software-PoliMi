package it.polimi.ingsw.network.Client.Socket;

import it.polimi.ingsw.network.Client.Client;
import it.polimi.ingsw.network.Messages.Message;
import it.polimi.ingsw.network.Messages.MessageType;
import it.polimi.ingsw.network.Messages.SocketLoginMessage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.rmi.RemoteException;
import java.util.Optional;

/**
 * This class is the Socket extension of the abstract Client class.
 * */
public class Socket_Client extends Client implements Runnable{
    private transient Socket socket;
    private transient ObjectInputStream in;
    private transient ObjectOutputStream out;
    private final Object outLock = new Object();
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
     * It sends a message to server with new name chosen and try to join in a game with it
     * @author: Francesco Lo Mastro
     * */
    @Override
    public void newUsernameProposal() {
        try {
            sendMessage(new SocketLoginMessage(getUsername()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
        } catch (Exception e)
        {
            throw new RuntimeException("Failed connecting to Socket server: "+e);
        }
    }
    /**
     * Send a generic message to server
     * @author: Riccardo Figini
     * @param message message*/
    @Override
    public void sendMessage(Message message) throws IOException {
        message.setSenderName(getUsername());
        synchronized (outLock) {
            if (out != null) {
                out.writeObject(message);
                out.reset();
            }
        }

    }
    /**
     * This thread is called when client calls method connection. It continues to read
     * stream with a message from server
     * @author: Riccardo Figini*/
    @Override
    public void run()
    {
        while (true)
        {
            try {
                Message message = (Message) in.readObject();

                if(message.getType()== MessageType.PING_MESSAGE)
                {
                    synchronized (pingMessage) {
                        pingMessage = Optional.of(message);
                    }
                }
                else
                {
                    synchronized (communicationMessageQueue) {
                        communicationMessageQueue.add(message);
                    }
                }
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException("Failed communication with server " + e);
            }
        }
    }
}
