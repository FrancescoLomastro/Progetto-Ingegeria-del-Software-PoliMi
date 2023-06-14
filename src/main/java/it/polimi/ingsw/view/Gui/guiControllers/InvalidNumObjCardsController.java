package it.polimi.ingsw.view.Gui.guiControllers;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class InvalidNumObjCardsController implements Initializable {

    public Button retry_btn;

    /**
     * This method is used to initialize the controller of the "InvalidNumObjCards.fxml" GUI. In particular,
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

    private void onRetry(ActionEvent event) {
        Node node = (Node) event.getSource();
        Stage currentStage = (Stage) node.getScene().getWindow();
        currentStage.close();
    }
}
