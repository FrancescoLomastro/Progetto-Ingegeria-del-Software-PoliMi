package it.polimi.ingsw.Network.Servers.RMI;

import it.polimi.ingsw.Network.UtilsForRMI;
import it.polimi.ingsw.controller.ServerReceiver;

import java.net.*;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Enumeration;

public class RMI_Server {
    private ServerReceiver serverReceiver;
    private final int port;
    public RMI_Server(ServerReceiver serverReceiver, int rmiPort) {
        this.serverReceiver = serverReceiver;
        this.port=rmiPort;
    }

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
