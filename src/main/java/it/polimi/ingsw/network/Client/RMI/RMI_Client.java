package it.polimi.ingsw.network.Client.RMI;



import it.polimi.ingsw.network.Client.Client;
import it.polimi.ingsw.network.Client.RMIClientConnection;
import it.polimi.ingsw.network.Messages.Message;
import it.polimi.ingsw.network.Messages.MessageType;
import it.polimi.ingsw.network.Messages.NewGameServerMessage;
import it.polimi.ingsw.network.Messages.RMILoginMessage;
import it.polimi.ingsw.network.Servers.RMI.RMISharedInterface;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Optional;

public class RMI_Client extends Client implements RMIClientConnection {
    private  RMISharedInterface server;
    /**
     * Constructor
     * @author: Riccardo Figini
     * @param address Server's address
     * @param port Server's port
     * @param username Client's name */
    public RMI_Client(String username, String address, int port) throws RemoteException
    {
        super(username, address, port);
    }

    @Override
    public void newUsernameProposal() {
        Message message = new RMILoginMessage(getUsername(),this);
        try {
            server.onMessage(message);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * This method changes reference to server, from acceptor server to Game server.
     * Thanks to this,cClient can communicate directly with GameController
     * @param message Message contains name and port of server
     * */
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
    /**
     * Client makes login on server, to join in a game.
     * It is an Override because the creation of connection depend on RMI/Socket
     * @author: Riccardo Figini
     * */
    @Override
    public void connect() {
        try {
            Registry registry = LocateRegistry.getRegistry(getServerAddress(), getServerPort());
            server = (RMISharedInterface) registry.lookup("MyShelfieServerRMI");
            Message message = new RMILoginMessage(getUsername(),this);
            server.onMessage(message);
        }catch (RemoteException | NotBoundException e) {
            throw new RuntimeException("Failed connecting to RMI server"+e);
        }
    }
    /**
     * Send generic message to server
     * @author: Riccardo Figini
     * @param message message*/
    @Override
    public void sendMessage(Message message) throws RemoteException {
        message.setUserName(getUsername());
        if (server == null) {
            throw new RemoteException();
        }
        server.onMessage(message);
    }
    /**
     * Receive a message and put it in a queue
     * @param message Generic Message*/
    @Override
    public void onMessage(Message message) {
        if (message.getType() == MessageType.PING_MESSAGE) {
            synchronized (pingMessage) {
                pingMessage = Optional.of(message);
            }
        } else {
            synchronized (communicationMessageQueue) {
                communicationMessageQueue.add(message);
            }
        }
    }
}
