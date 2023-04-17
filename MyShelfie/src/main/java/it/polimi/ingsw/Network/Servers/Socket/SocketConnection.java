package it.polimi.ingsw.Network.Servers.Socket;

import org.example.Controller.ServerReceiver;
import org.example.Messages.*;
import org.example.Servers.Connection;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class SocketConnection implements Connection,Runnable {

    private ServerReceiver serverReceiver;
    private final Socket socket;
    private final ObjectInputStream in;
    private final ObjectOutputStream out;

    boolean connected=false;

    public SocketConnection(ServerReceiver serverReceiver, Socket clientSocket) throws IOException {
        this.serverReceiver = serverReceiver;
        this.socket = clientSocket;
        this.in = new ObjectInputStream(socket.getInputStream());
        this.out = new ObjectOutputStream(socket.getOutputStream());
    }

    @Override
    public void run() {
        while (true)
        {
            try {
                Message message = (Message) in.readObject();
                onMessage(message);
            } catch (IOException e) {
                System.out.println("Failed communication with client's socket\n[Details]\n" + e);
                System.out.println("Closing remote socket...");
                try {
                    socket.close();
                } catch (IOException ex) {
                    System.out.println("Failed closing socket\n[Details]\n" + ex);
                }
                finally
                {
                    break;
                }
            } catch (ClassNotFoundException e) {
                System.out.println("Failed to cast client message, message dropped\n[Details]\n" + e);
            }
        }
    }

    public void onMessage(Message message) {
        if(message.getType()==MessageType.SOCKET_LOGIN_REQUEST)
        {
            SocketLoginMessage msg = (SocketLoginMessage) message;
            LoginMessage newMessage = new LoginMessage(msg.getUsername(),this);
            serverReceiver.onMessage(newMessage);
        }
        else
        {
            serverReceiver.onMessage(message);
        }
    }

    @Override
    public void sendMessage(Message message) throws IOException {
        if(out!=null) {
            if(message.getType()==MessageType.NEW_GAME_SERVER_MESSAGE){
                NewGameServerMessage msg = (NewGameServerMessage) message;
                serverReceiver =msg.getGameController();
            }
            out.writeObject(message);
            out.reset();
        }
        else throw new IOException();
    }

}
