package it.polimi.ingsw.Network.Servers.RMI;


import it.polimi.ingsw.Network.Messages.Message;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * This interface serves as an abstraction for RMIShared objects enforcing class that implements it to implement onMessage method.
 *
 * @author Alberto Aniballi
 */
public interface RMISharedInterface extends Remote {

    /**
     * This method takes care of activating the controller for preparing server-side responses to client requests.
     * The method must be implemented by all classes that implement RMISharedInterface.
     *
     * @param message: it is the specific message to which the controller must respond.
     * @author Alberto Aniballi
     */
    public void onMessage(Message message) throws RemoteException;
}
