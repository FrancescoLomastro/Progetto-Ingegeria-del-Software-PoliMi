package it.polimi.ingsw.Network.Servers;

import it.polimi.ingsw.ClientApp;
import it.polimi.ingsw.Network.Messages.ErrorMessage;
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
