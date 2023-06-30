package it.polimi.ingsw.view.Gui.guiControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * This class governs the GUI used to signal players the choice of an invalid move.
 * This class is used as an intermediary between the fixed parts of the GUI of the 'InvalidMove.fxml' file and
 * the dynamic information that the controller sends to the graphic components of the associated fxml file.
 *
 * @author Riccardo Figini, Alberto Aniballi
 */
public class InvalidMoveController implements Initializable {
    @FXML
    public Button retry_btn;
    @FXML
    public Label text_label;
    private String text;

    /**
     * This method is used to initialize the controller of the "InvalidMove.fxml" GUI.
     *
     * @param url the url used to resolve relative paths for the root object, or null if the location is not known.
     * @param resourceBundle the resources used to localize the root object, or null if the root object was not localized.
     * @author Riccardo Figini, Alberto Aniballi
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        retry_btn.setOnAction(this::onRetry);
        text_label.setText(text);
    }

    /**
     * This method is used to close the "InvalidMove" stage and reactivate the previous stage.
     *
     * @param event the event that triggers the activation of the method;
     * @author Riccardo Figini, Alberto Aniballi
     */
    private void onRetry(ActionEvent event) {
        Node node = (Node) event.getSource();
        Stage currentStage = (Stage) node.getScene().getWindow();
        currentStage.close();
    }

    /**
     * This method is used to set the text in the text label of the stage.
     *
     * @param s the string to be displayed.
     * @author Riccardo Figini
     */
    public void setText(String s){
        this.text=s;
    }
}
