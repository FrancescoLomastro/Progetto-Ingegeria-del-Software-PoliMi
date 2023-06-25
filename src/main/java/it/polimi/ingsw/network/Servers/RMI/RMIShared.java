package it.polimi.ingsw.network.Servers.RMI;


import it.polimi.ingsw.network.Messages.LoginMessage;
import it.polimi.ingsw.network.Messages.Message;
import it.polimi.ingsw.network.Messages.MessageType;
import it.polimi.ingsw.network.Messages.RMILoginMessage;
import it.polimi.ingsw.controller.ServerReceiver;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * This class serves as RMIShared object for the management of network communication via RMI technology server-side.
 * The class takes care of receiving messages by creating the serverReceiver that will be used for answering to client requests.
 *
 * @author Alberto Aniballi
 */
public class RMIShared extends UnicastRemoteObject implements RMISharedInterface {

    private static final long serialVersionUID= 1L;
    private ServerReceiver serverReceiver;

    /**
     * Constructor of RMIShared instances.
     * It is used to instantiate a new serverReceiver.
     *
     * @param serverReceiver: it is the controller that will act as serverReceiver.
     * @author Alberto Aniballi
     */
    public RMIShared(ServerReceiver serverReceiver) throws RemoteException {
        this.serverReceiver = serverReceiver;
    }

    /**
     * This method takes care of activating the controller for preparing RMI server-side responses to client requests.
     *
     * @param message: it is the specific message to which the controller must respond.
     * @author Alberto Aniballi
     */
    @Override
    public void onMessage(Message message) throws RemoteException {
        if(message.getType()== MessageType.RMI_LOGIN_REQUEST)
        {
            RMILoginMessage msg = (RMILoginMessage) message;
            Message newMessage= new LoginMessage(msg.getSenderName(),new RMIConnection(msg.getConnection()));
            serverReceiver.onMessage(newMessage);
        }
        else
        {
            serverReceiver.onMessage(message);
        }
    }

}
