package it.polimi.ingsw.network.Servers;

import it.polimi.ingsw.network.Messages.ErrorMessage;
import it.polimi.ingsw.controller.ClientController;

import java.util.TimerTask;

public class PingTaskClient extends TimerTask {
    ClientController clientController;
    public PingTaskClient(ClientController clientController) {
        this.clientController=clientController;
    }
    @Override
    public void run()
    {
        clientController.onMessage(new ErrorMessage("Timeout connection with server"));
    }
}
