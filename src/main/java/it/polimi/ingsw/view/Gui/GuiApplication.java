package it.polimi.ingsw.view.Gui;

import it.polimi.ingsw.view.Gui.guiControllers.ViewFactory;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * This class is starts the GUI application. Its responsibility is to initialize the primary stage.
 *
 * @author Alberto Aniballi
 */
public class GuiApplication extends Application {
    private ViewFactory viewFactory;

    /**
     * This method sets the primary stage and make the first scene appear to the client: the "Start" scene.
     *
     * @param stage : the primary stage;
     * @author Alberto Aniballi
     */
    @Override
    public void start(Stage stage) throws Exception {
        viewFactory.setPrimaryStage(stage);
        viewFactory.showStart();
    }

    /**
     * This method initialize the static viewFactory object.
     *
     * @author Alberto Aniballi
     */
    public GuiApplication() {
        this.viewFactory = ViewFactory.getInstance();
    }
}
