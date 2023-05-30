package it.polimi.ingsw;
import it.polimi.ingsw.controller.ClientController;

 public class ClientApp {
    public static void main(String[] args)
    {
        ClientController clientController;
        if(args!= null && args.length!=0 && args[0].equals("CLI"))
        {
            clientController = new ClientController("CLI");
        }
        else
        {
            clientController = new ClientController("GUI");
        }
        clientController.turnOnView();
    }
}
