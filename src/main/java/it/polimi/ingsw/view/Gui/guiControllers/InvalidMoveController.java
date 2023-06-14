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
     * @author Riccardo Figini
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        retry_btn.setOnAction(this::onRetry);
        text_label.setText(text);
    }
    private void onRetry(ActionEvent event) {
        Node node = (Node) event.getSource();
        Stage currentStage = (Stage) node.getScene().getWindow();
        currentStage.close();
    }
    public void setText(String s){
        this.text=s;
    }
}
