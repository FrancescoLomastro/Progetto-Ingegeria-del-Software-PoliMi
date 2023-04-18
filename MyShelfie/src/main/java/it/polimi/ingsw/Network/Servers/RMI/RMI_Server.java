package it.polimi.ingsw.Network.Servers.RMI;

import it.polimi.ingsw.controller.ServerReceiver;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMI_Server {
    private ServerReceiver serverReceiver;
    private final int port;
    public RMI_Server(ServerReceiver serverReceiver, int rmiPort) {
        this.serverReceiver = serverReceiver;
        this.port=rmiPort;
    }

    public void turnOn() throws RemoteException, AlreadyBoundException {
        RMIShared sharedObject = new RMIShared(serverReceiver);
        Registry registry = LocateRegistry.createRegistry(port);
        registry.bind("MyShelfieServerRMI",sharedObject);
    }
}
