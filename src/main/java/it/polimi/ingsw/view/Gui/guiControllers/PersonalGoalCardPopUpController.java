package it.polimi.ingsw.view.Gui.guiControllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class PersonalGoalCardPopUpController implements Initializable {

    @FXML
    private Label title_label;
    @FXML
    private Pane picture_pane;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        picture_pane.getStyleClass().add("personal" + ViewFactory.getInstance().getClientModel().getPersonalGoalCardNum());
        title_label.setText("Here's you're personal goal, don't tell anyone!");
    }
}
