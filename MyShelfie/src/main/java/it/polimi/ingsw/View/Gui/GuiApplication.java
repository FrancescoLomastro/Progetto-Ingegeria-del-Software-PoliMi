package it.polimi.ingsw.View.Gui;

import it.polimi.ingsw.Network.Client.ClientModel;
import it.polimi.ingsw.View.Gui.guiControllers.ViewFactory;
import javafx.application.Application;
import javafx.stage.Stage;

public class GuiApplication extends Application {

    ViewFactory viewFactory;
    @Override
    public void start(Stage stage) throws Exception {
        viewFactory= ViewFactory.getInstance();
        viewFactory.showClientLogin();
    }
}
