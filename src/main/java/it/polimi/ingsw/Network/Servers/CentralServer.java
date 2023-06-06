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

/**
 * This class serves as a central server for the subsequent creation of two dedicated servers,
 * one for the management of network communication via RMI technology and the other for the management of network communication via Socket technology.
 * Moreover, the class takes care of receiving server-side messages by activating the controller that handles the pre-game phase.
 *
 * @author Alberto Aniballi
 */
public class CentralServer {
    public final static int socketPort = 8000;
    public final static int rmiPort = 9000;
    private final Controller controller;

    /**
     * Constructor of CentralServer instances.
     * Instantiate a new controller that handles pre-game phase.
     * @author Alberto Aniballi
     */
    public CentralServer() {
        controller= new Controller();
    }

    /**
     * This method activates two different servers, one RMI and the other Socket, ensuring that both technologies function correctly at the same time.
     * The two servers are associated to two different ports but to the same controller instantiated at the time of initialisation.
     * @author Alberto Aniballi
     */
    public void start() {
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

    /**
     * This method instantiate a new CentralServer and starts it.
     * In order for MyShelfie to function properly, it is always necessary to run this main method
     * to ensure that there is a central server listening for connection requests from clients.
     * @author Alberto Aniballi
     */
    public static void main(String[] args) {
        CentralServer centralServer = new CentralServer();
        centralServer.start();
    }
}
