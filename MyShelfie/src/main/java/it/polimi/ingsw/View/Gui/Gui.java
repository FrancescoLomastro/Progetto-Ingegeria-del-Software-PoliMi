package it.polimi.ingsw.View.Gui;

import it.polimi.ingsw.Network.Client.ClientModel;
import javafx.application.Application;
import javafx.stage.Stage;

public class Gui extends Application {
    private ClientModel clientModel;

    @Override
    public void start(Stage stage) throws Exception {
        this.clientModel = new ClientModel();
        clientModel.getViewFactory().showClientLogin();
    }
}
