package it.polimi.ingsw.View.Gui.guiControllers;

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
 * In addition, the class manages the interaction between the user and the various graphic components of the scene.
 *
 * @author Alberto Aniballi
 */
public class InvalidPortController implements Initializable {
    public Button retry_btn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        retry_btn.setOnAction(event -> onRetry(event));
    }

    private void onRetry(ActionEvent event) {
        Node node = (Node) event.getSource();
        Stage currentStage = (Stage) node.getScene().getWindow();
        currentStage.close();
    }

}
