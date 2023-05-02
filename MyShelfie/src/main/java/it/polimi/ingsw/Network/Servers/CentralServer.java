package it.polimi.ingsw.Network.Servers;

import it.polimi.ingsw.Network.Messages.Message;
import it.polimi.ingsw.Network.Servers.RMI.RMI_Server;
import it.polimi.ingsw.Network.Servers.Socket.Socket_Server;
import it.polimi.ingsw.controller.Controller;


import java.io.IOException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

public class CentralServer {
    private final int socketPort = 8000;
    private final int rmiPort = 9000;
    private Controller controller;
    private Map<String,Connection> clients;


    public CentralServer()
    {
        controller= new Controller();
        clients = new HashMap<>();
    }

    public void start()
    {
        RMI_Server rmiServer = new RMI_Server(controller,rmiPort);
        try {
            rmiServer.turnOn();
        } catch (Exception e) {
            System.out.println("Failed to create RMI server" + e);
        }
        try {
            Socket_Server socketServer = new Socket_Server(controller, socketPort);
            socketServer.turnOn();
        } catch (IOException e) {
            System.out.println("Failed to create Socket server" + e);
        }
    }

    public static void main(String[] args) {
        CentralServer centralServer = new CentralServer();
        centralServer.start();
    }

    public void login(String username,Connection connection)
    {
        controller.login(username,connection);
    }


    public void onMessage(Message message) {
        controller.onMessage(message);
    }


}
