package it.polimi.ingsw;

import it.polimi.ingsw.controller.ClientController;

public class ClientApp {
    public static void main(String[] args) {
        //Per ora avvia predefinita la Cli, poi metteremo un args
        ClientController clientController = new ClientController();
        clientController.startView();
    }
}
