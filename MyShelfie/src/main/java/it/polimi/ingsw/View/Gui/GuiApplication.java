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
        viewFactory.showClientLogin();// qua andrebbe chiamata la schermata iniziale (non il login) che,
        // se premi START notifica l'observer con un Messaggio nuovo (di tipo START, senza ulteriori parametri)
        // in questo modo il controller capirà (me ne occupo io) che deve avviare il metodo AskInitialInfo
    }
}
