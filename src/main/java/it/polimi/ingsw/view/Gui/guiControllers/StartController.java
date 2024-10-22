package it.polimi.ingsw.view.Gui.guiControllers;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * This class governs the GUI dedicated to the initial start scene:
 * it manages the interaction between the user and the graphic components of the scene.
 *
 * @author Alberto Aniballi
 */
public class StartController implements Initializable {
    @FXML
    public Label initialLabel;
    @FXML
    public AnchorPane background;

    private Scene scene;

    /**
     * This method is used to set the scene on the stage.
     *
     * @param scene the scene to be set.
     * @author Francesco Lo Mastro
     */
    public void setScene(Scene scene) {
        this.scene = scene;
    }

    /**
     * This method is used to initialize the controller of the "Start.fxml" GUI.
     *
     * @param url the url used to resolve relative paths for the root object, or null if the location is not known.
     * @param resourceBundle the resources used to localize the root object, or null if the root object was not localized.
     * @author Francesco Lo Mastro
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(2), initialLabel);
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.0);
        fadeTransition.setCycleCount(fadeTransition.INDEFINITE);
        fadeTransition.setAutoReverse(true);
        fadeTransition.play();
    }


}