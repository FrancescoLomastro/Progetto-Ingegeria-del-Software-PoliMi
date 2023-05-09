package it.polimi.ingsw.View.Gui.guiControllers;

import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.WindowEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class AcceptedLoginController implements Initializable {
    public Label current_numPlayers_label;
    private String string;

    public void setString(String string) {
        this.string = string;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        current_numPlayers_label.setText(string);
    }
}