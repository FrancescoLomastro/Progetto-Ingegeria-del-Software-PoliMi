package it.polimi.ingsw.View.Gui.guiControllers;

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
