package it.polimi.ingsw.View.Gui.guiControllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ErrorGameController implements Initializable {
    @FXML
    public Label title_label;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        title_label.setText("Error! Game will be closed");
    }
}
