package it.polimi.ingsw.view.Gui.guiControllers;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * This class governs the GUI dedicated to handling the phase of incorrect entry of the total number of players participating in the game.
 * It is used as an intermediary between the fixed parts of the GUI of the 'InvalidNumPlayers.fxml' file and
 * the dynamic information that the controller sends to the graphic components of the associated fxml file.
 * In addition, the class manages the interaction between the user and the various graphic components of the scene.
 *
 * @author Alberto Aniballi
*/

public class InvalidNumPlayersController implements Initializable {

    public Button retry_btn;

    /**
     * This method is used to initialize the controller of the "InvalidNumPlayers.fxml" GUI. In particular,
     * it sets a listener for the retry button.
     *
     * @param url the url used to resolve relative paths for the root object, or null if the location is not known.
     * @param resourceBundle the resources used to localize the root object, or null if the root object was not localized.
     * @author Alberto Aniballi
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        retry_btn.setOnAction(event -> onRetry(event));
    }

    /**
     * This method is used to close the "InvalidNumPlayers" stage and reactivate the previous stage.
     *
     * @param event the event that triggers the activation of the method;
     * @author Alberto Aniballi
     */
    private void onRetry(ActionEvent event) {
        Node node = (Node) event.getSource();
        Stage currentStage = (Stage) node.getScene().getWindow();
        currentStage.close();
    }
}
