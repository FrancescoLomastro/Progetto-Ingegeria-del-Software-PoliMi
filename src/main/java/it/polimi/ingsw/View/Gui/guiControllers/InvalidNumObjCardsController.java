package it.polimi.ingsw.View.Gui.guiControllers;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class InvalidNumObjCardsController implements Initializable {

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
