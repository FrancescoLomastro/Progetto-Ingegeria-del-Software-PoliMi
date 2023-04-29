package it.polimi.ingsw.Network.Servers.RMI;

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
        String address = getLocalIp();
        System.setProperty("java.rmi.server.hostname", address);
        System.out.println("Server RMI on at "+address);
        RMIShared sharedObject = new RMIShared(serverReceiver);
        Registry registry = LocateRegistry.createRegistry(port);
        registry.bind("MyShelfieServerRMI",sharedObject);
    }


    private String getLocalIp() {
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                NetworkInterface ni = interfaces.nextElement();
                if (!ni.isLoopback() && ni.isUp()) {
                    for (InterfaceAddress address : ni.getInterfaceAddresses())
                    {
                        InetAddress inetAddress = address.getAddress();
                        if (inetAddress != null && inetAddress instanceof Inet4Address)
                        {
                            return inetAddress.getHostAddress();
                        }
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return null;
    }
}
