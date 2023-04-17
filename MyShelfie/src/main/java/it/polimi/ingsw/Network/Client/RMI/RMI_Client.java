package it.polimi.ingsw.Network.Client.RMI;



import it.polimi.ingsw.Network.Client.Client;
import it.polimi.ingsw.Network.Client.RMIClientConnection;
import it.polimi.ingsw.Network.Messages.Message;
import it.polimi.ingsw.Network.Messages.NewGameServerMessage;
import it.polimi.ingsw.Network.Messages.RMILoginMessage;
import it.polimi.ingsw.Network.Servers.RMI.RMISharedInterface;
import it.polimi.ingsw.View.View;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMI_Client extends Client implements RMIClientConnection {
    private RMISharedInterface server;
    public RMI_Client(String username, String address, int port, View view) throws RemoteException
    {
        super(username, address, port, view);
    }

    public void changeServer(NewGameServerMessage message){
        String serverName = message.getServerName();
        int port = message.getPort();
        try {
            Registry registry = LocateRegistry.getRegistry(getServerAddress(), port);
            server = (RMISharedInterface) registry.lookup(serverName);
        }catch (RemoteException | NotBoundException e) {
            throw new RuntimeException("Failed connecting to RMI server"+e);
        }
    }

    @Override
    public void connect() {
        try {
            Registry registry = LocateRegistry.getRegistry(getServerAddress(), getServerPort());
            server = (RMISharedInterface) registry.lookup("MyShelfieServerRMI");
            server.onMessage(new RMILoginMessage(getUsername(),this));
        }catch (RemoteException | NotBoundException e) {
            throw new RuntimeException("Failed connecting to RMI server"+e);
        }
    }

    @Override
    public void sendMessage(Message message) throws RemoteException {
        if (server == null) {
            throw new RemoteException();
        }
        server.onMessage(message);
    }

    @Override
    public void onMessage(Message message) {
        synchronized (messageQueue)
        {
            messageQueue.add(message);
        }
    }
}
