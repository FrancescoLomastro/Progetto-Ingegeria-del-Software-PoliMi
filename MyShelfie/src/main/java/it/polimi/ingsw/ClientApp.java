package it.polimi.ingsw;

import it.polimi.ingsw.View.Gui.GuiApplication;
import it.polimi.ingsw.controller.ClientController;
import javafx.application.Application;

public class ClientApp {
    public static void main(String[] args)
    {
        ClientController clientController;
        if(args!= null && args.length!=0 && args[0].equals("CLI"))
        {
            clientController = new ClientController(args[0]);
            clientController.startView();
        }
        else
        {
            clientController = new ClientController("GUI");
            Application.launch(GuiApplication.class);
        }
    }
}
