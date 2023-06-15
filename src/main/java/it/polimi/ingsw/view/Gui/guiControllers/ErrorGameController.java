package it.polimi.ingsw.view.Gui.guiControllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * This class governs the GUI used to show the players an error occurring during the game which will cause the closure of the game.
 * This class used as an intermediary between the fixed parts of the GUI of the 'ErrorGame.fxml' file and
 * the dynamic information that the controller sends to the graphic components of the associated fxml file.
 *
 * @author Riccardo Figini
 */
public class ErrorGameController implements Initializable {
    @FXML
    public Label title_label;

    /**
     * This method is used to initialize the controller of the "ErrorGame.fxml" GUI.
     *
     * @param url the url used to resolve relative paths for the root object, or null if the location is not known.
     * @param resourceBundle the resources used to localize the root object, or null if the root object was not localized.
     * @author Riccardo Figini
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        title_label.setText("Error! Game will be closed");
    }
}
