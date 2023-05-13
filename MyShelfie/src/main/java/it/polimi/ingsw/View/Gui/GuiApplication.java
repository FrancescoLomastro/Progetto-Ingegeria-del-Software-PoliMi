package it.polimi.ingsw.View.Gui;

import it.polimi.ingsw.View.Gui.guiControllers.ViewFactory;
import javafx.application.Application;
import javafx.stage.Stage;

public class GuiApplication extends Application {
        private ViewFactory viewFactory;

    public GuiApplication() {
        this.viewFactory = ViewFactory.getInstance();
    }

    @Override
    public void start(Stage stage) throws Exception {
        viewFactory.setPrimaryStage(stage);
        viewFactory.showStart();
    }
}
