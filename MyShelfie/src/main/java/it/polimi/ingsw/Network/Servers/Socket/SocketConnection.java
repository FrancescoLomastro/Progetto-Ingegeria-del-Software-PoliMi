package it.polimi.ingsw.Network.Servers.Socket;



import it.polimi.ingsw.Network.Messages.*;
import it.polimi.ingsw.Network.Servers.Connection;
import it.polimi.ingsw.Network.StatusNetwork;
import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.controller.GameController;
import it.polimi.ingsw.controller.ServerReceiver;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class SocketConnection implements Connection,Runnable {
    StatusNetwork statusNetwork;
    private ServerReceiver serverReceiver;
    private final Socket socket;
    private final ObjectInputStream in;
    private final ObjectOutputStream out;
    private String playerName;

    public SocketConnection(ServerReceiver serverReceiver, Socket clientSocket) throws IOException {
        this.serverReceiver = serverReceiver;
        this.socket = clientSocket;
        this.in = new ObjectInputStream(socket.getInputStream());
        this.out = new ObjectOutputStream(socket.getOutputStream());
        statusNetwork=StatusNetwork.AFTER_ACCEPTION_SOCKET_BEFORE_LOGIN_MESSAGE;
    }

    @Override
    public void run() {
        boolean continueCicle=true;
        while (continueCicle)
        {
            try {
                Message message = (Message) in.readObject();
                onMessage(message);
            } catch (IOException | ClassNotFoundException e) {
                System.out.println(Controller.ANSI_BLU + "Failed communication with client's socket: " + e + GameController.ANSI_RESET);
                try {
                    socket.close();
                } catch (IOException ex) {
                    System.out.println(Controller.ANSI_BLU + "Failed closing socket\n[Details]\n" + ex + GameController.ANSI_RESET);
                }
                serverReceiver.tryToDisconnect(this, playerName);
                continueCicle=false;
            }
        }
    }

    public void onMessage(Message message) {
        if(message.getType()== MessageType.SOCKET_LOGIN_REQUEST)
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
        else {
            throw new IOException();
        }
    }

    @Override
    public StatusNetwork getStatusNetwork() {
        return statusNetwork;
    }

    @Override
    public void setStatusNetwork(StatusNetwork statusNetwork) {
        this.statusNetwork=statusNetwork;
    }

    @Override
    public String getPlayerName() {
        return playerName;
    }

    @Override
    public void setPlayerName(String playerName) {
        this.playerName=playerName;
    }

}
