package it.polimi.ingsw.tasks;

import it.polimi.ingsw.network.Messages.ErrorMessage;
import it.polimi.ingsw.controller.ClientController;

import java.util.TimerTask;

public class PingTaskClient extends TimerTask {
    ClientController clientController;
    public PingTaskClient(ClientController clientController) {
        this.clientController=clientController;
    }

    /**This method activates client controller in case of an error message from the server.
     * */
    @Override
    public void run()
    {
        clientController.onMessage(new ErrorMessage("Timeout connection with server"));
    }
}
