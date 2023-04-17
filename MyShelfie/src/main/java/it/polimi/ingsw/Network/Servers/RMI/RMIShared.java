package it.polimi.ingsw.Network.Servers.RMI;

import org.example.Controller.ServerReceiver;
import org.example.Messages.LoginMessage;
import org.example.Messages.Message;
import org.example.Messages.MessageType;
import org.example.Messages.RMILoginMessage;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RMIShared extends UnicastRemoteObject implements RMISharedInterface {

    private static final long serialVersionUID= 1L;
    private ServerReceiver serverReceiver;
    public RMIShared(ServerReceiver serverReceiver) throws RemoteException {
        this.serverReceiver = serverReceiver;
    }
    @Override
    public void onMessage(Message message) throws RemoteException {
        if(message.getType()== MessageType.RMI_LOGIN_REQUEST)
        {
            RMILoginMessage msg = (RMILoginMessage) message;
            Message newMessage= new LoginMessage(msg.getUsername(),new RMIConnection(msg.getConnection()));
            serverReceiver.onMessage(newMessage);
        }
        else
        {
            serverReceiver.onMessage(message);
        }
    }

}
