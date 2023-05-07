package it.polimi.ingsw.View.Gui;

import it.polimi.ingsw.Network.Client.ClientModel;
import it.polimi.ingsw.View.Gui.guiControllers.ViewFactory;
import javafx.application.Application;
import javafx.stage.Stage;

public class GuiApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        ViewFactory.getInstance().showStart();
    }
}
