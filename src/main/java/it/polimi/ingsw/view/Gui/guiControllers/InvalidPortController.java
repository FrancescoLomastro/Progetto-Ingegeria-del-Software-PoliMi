package it.polimi.ingsw.view.Gui.guiControllers;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * This class governs the GUI dedicated to managing the unavailable or incorrect choice of connection port between client and server.
 * It is used as an intermediary between the fixed parts of the GUI of the 'InvalidPort.fxml' file and
 * the dynamic information that the controller sends to the graphic components of the associated fxml file.
 *
 * @author Alberto Aniballi
 */
public class InvalidPortController implements Initializable {
    public Button retry_btn;

    /**
     * This method is used to initialize the fxml controller of the "invalid port" GUI.
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
     * This method is used to close the "invalid port" stage and reactivate the previous stage.
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
