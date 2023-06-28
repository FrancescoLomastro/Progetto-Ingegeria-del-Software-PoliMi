package it.polimi.ingsw.view.Gui.guiControllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * This class is the controller associated to the "PersonalGoalCardPopUp.fxml" file.
 *
 * @author Andrea Ferrini
 */
public class PersonalGoalCardPopUpController implements Initializable {

    @FXML
    private Label title_label;
    @FXML
    private Pane picture_pane;

    /**
     * This method is used to initialize the controller of the "PersonalGoalCardPopUp.fxml" GUI.
     *
     * @param url the url used to resolve relative paths for the root object, or null if the location is not known.
     * @param resourceBundle the resources used to localize the root object, or null if the root object was not localized.
     * @author Andrea Ferrini
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        picture_pane.getStyleClass().add("personal" + ViewFactory.getInstance().getClientModel().getPersonalGoalId());
        title_label.setText("Here's you're personal goal, don't tell anyone!");
    }
}
