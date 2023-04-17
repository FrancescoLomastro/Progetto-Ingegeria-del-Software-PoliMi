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

public class Socket_Client extends Client implements Runnable{

    private transient Socket socket;
    private transient ObjectInputStream in;
    private transient ObjectOutputStream out;

    public Socket_Client(String username, String address, int port, View view) throws RemoteException
    {
        super(username, address, port,view);
    }

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

    @Override
    public void sendMessage(Message message) throws IOException {
        if (out != null) {
            out.writeObject(message);
            out.reset();
        }
    }


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
