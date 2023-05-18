package it.polimi.ingsw.Network.Servers.RMI;

import it.polimi.ingsw.Network.UtilsForRMI;
import it.polimi.ingsw.controller.ServerReceiver;

import java.net.*;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Enumeration;

/**
 * This class serves as server for the management of network communication via RMI technology.
 * The class takes care of receiving messages by creating the serverReceiver that will be used for receiving any kind of RMI message.
 *
 * @author Alberto Aniballi
 */
public class RMI_Server {
    private ServerReceiver serverReceiver;
    private final int port;

    /**
     * Constructor of RMI_Server instances.
     * It is used to instantiate a new serverReceiver and the specific port to be used for RMI technology.
     *
     * @param serverReceiver: it is the controller that will act as serverReceiver.
     * @param rmiPort: it is the chosen port for RMI network.
     * @author Alberto Aniballi
     */
    public RMI_Server(ServerReceiver serverReceiver, int rmiPort) {
        this.serverReceiver = serverReceiver;
        this.port=rmiPort;
    }

    /**
     * This method activates the RMI connection server-side instantiating an RMIShared object and a registry linked to the RMI port.
     *
     * @author Alberto Aniballi
     */
    public void turnOn() throws Exception{
        String address = UtilsForRMI.getLocalIp();
        if(address==null)
            address="127.0.0.1";
        System.setProperty("java.rmi.server.hostname", address);
        System.out.println("Server RMI on at "+address);
        RMIShared sharedObject = new RMIShared(serverReceiver);
        Registry registry = LocateRegistry.createRegistry(port);
        registry.bind("MyShelfieServerRMI",sharedObject);
    }



}
