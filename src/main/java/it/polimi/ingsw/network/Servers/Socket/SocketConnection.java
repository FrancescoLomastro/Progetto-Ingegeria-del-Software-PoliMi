package it.polimi.ingsw.network.Servers.Socket;



import it.polimi.ingsw.network.Messages.*;
import it.polimi.ingsw.network.Servers.Connection;
import it.polimi.ingsw.network.StatusNetwork;
import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.controller.GameController;
import it.polimi.ingsw.controller.ServerReceiver;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * This class serves as connection for the management of network communication via Socket technology.
 * The class takes care of receiving messages by creating an ObjectInputStream that will be used for receiving Socket message and
 * sending socket messages by creating an ObjectOutputStream. The two stream are dedicated to a specific client.
 * Moreover, it is used to check and validate network status.
 *
 * @author Alberto Aniballi
 */
public class SocketConnection extends Connection implements Runnable {
    StatusNetwork statusNetwork;
    private ServerReceiver serverReceiver;
    private final Socket socket;
    private final ObjectInputStream in;
    private final ObjectOutputStream out;
    private final Object outLock = new Object();
    private final Object inLock = new Object();

    /**
     * Constructor of SocketConnection instances.
     * It is used to instantiate a new serverReceiver and a socket that will manage server-side communication using specific
     * OutputStream and InputStream.
     *
     * @param serverReceiver: it is the controller that will act as serverReceiver.
     * @param clientSocket: it is the socket dedicated to listening to a specific client messages.
     * @author Alberto Aniballi
     */
    public SocketConnection(ServerReceiver serverReceiver, Socket clientSocket) throws IOException {
        this.serverReceiver = serverReceiver;
        this.socket = clientSocket;
        this.in = new ObjectInputStream(socket.getInputStream());
        this.out = new ObjectOutputStream(socket.getOutputStream());
        statusNetwork=StatusNetwork.AFTER_ACCEPTION_SOCKET_BEFORE_LOGIN_MESSAGE;
    }


    /**
     * This method activates the socket server-side thread that is used to continuously listen to client requests
     * using a specific ObjectInputStream dedicated to socket connection with this client.
     * It also takes care of alerting the client in case of failed communication with the socket server.
     *
     * @author Alberto Aniballi
     */
    @Override
    public void run() {
        boolean continueCycle=true;
        while (continueCycle)
        {
            try
            {
                Message message;
                synchronized (inLock)
                {
                   message = (Message) in.readObject();
                }
                onMessage(message);
            } catch (IOException | ClassNotFoundException e) {
                System.out.println(Controller.ANSI_BLU + "Failed communication with client's socket: " + e + GameController.ANSI_RESET);
                try {
                    socket.close();
                } catch (IOException ex) {
                    System.out.println(Controller.ANSI_BLU + "Failed closing socket\n[Details]\n" + ex + GameController.ANSI_RESET);
                }
                serverReceiver.tryToDisconnect(this, playerName);
                continueCycle=false;
            }
        }
    }


    /**
     * This method takes care of activating the socket server-side controller for receiving client requests.
     *
     * @param message: it is the specific message to which the controller must respond.
     * @author Alberto Aniballi
     */
    public void onMessage(Message message) {
        if(message.getType()== MessageType.SOCKET_LOGIN_REQUEST)
        {
            SocketLoginMessage msg = (SocketLoginMessage) message;
            LoginMessage newMessage = new LoginMessage(msg.getSenderName(),this);
            serverReceiver.onMessage(newMessage);
        }
        else
        {
            serverReceiver.onMessage(message);
        }
    }


    /**
     * This method takes care of activating the socket server-side controller for sending answers to client using the specif ObjectOutputStream.
     * The method is also responsible for alerting the creation of a new Server for managing the game socket communication.
     *
     * @param message: it is the specific message that the controller sends as answer to a client request.
     * @author Alberto Aniballi
     */
    @Override
    public void sendMessage(Message message) throws IOException {
        if(out!=null) {
            if(message.getType()==MessageType.NEW_GAME_SERVER_MESSAGE){
                NewGameServerMessage msg = (NewGameServerMessage) message;
                serverReceiver =msg.getServerReceiver();
            }
            synchronized (outLock) {
                out.writeObject(message);
                out.reset();
            }
        }
        else {
            throw new IOException();
        }
    }

    /**
     * It gets current network status.
     *
     * @author Alberto Aniballi
     */
    @Override
    public StatusNetwork getStatusNetwork() {
        return statusNetwork;
    }

    /**
     * It sets the network status.
     *
     * @param statusNetwork: the network status to be set.
     * @author Alberto Aniballi
     */
    @Override
    public void setStatusNetwork(StatusNetwork statusNetwork) {
        this.statusNetwork=statusNetwork;
    }

    /**
     * It gets the player name dedicated to this socketConnection.
     *
     * @author Alberto Aniballi
     */
    @Override
    public String getPlayerName() {
        return playerName;
    }

    /**
     * It sets the player name dedicated to this socketConnection.
     *
     * @author Alberto Aniballi
     */
    @Override
    public void setPlayerName(String playerName) {
        this.playerName=playerName;
    }

}
