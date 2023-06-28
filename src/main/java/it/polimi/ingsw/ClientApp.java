package it.polimi.ingsw;
import it.polimi.ingsw.controller.ClientController;

/**
 * This class starts the client application. Its responsibility is to distinguish between clients that want to play
 * using the command line interface and clients that want to play using the graphical user interface.
 *
 * @author Francesco Gregorio Lo Mastro
 */
 public class  ClientApp {

    /**
     * It is the main method that runs client applications using either the game CLI or GUI.
     *
     * @param args : configuration arguments to pass to main method;
     * @author Francesco Gregorio Lo Mastro
     */
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
