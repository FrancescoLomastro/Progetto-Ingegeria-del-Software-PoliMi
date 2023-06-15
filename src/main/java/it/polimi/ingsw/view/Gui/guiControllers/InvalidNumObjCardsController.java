package it.polimi.ingsw.view.Gui.guiControllers;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * This class governs the GUI used to signal players the choice of an invalid number of object cards currently in the grid.
 * This class used as an intermediary between the fixed parts of the GUI of the 'InvalidNumObjCards.fxml' file and
 * the dynamic information that the controller sends to the graphic components of the associated fxml file.
 *
 * @author Alberto Aniballi
 */
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

    /**
     * This method is used to close the "InvalidNumObjCardsController" stage and reactivate the main game stage.
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
