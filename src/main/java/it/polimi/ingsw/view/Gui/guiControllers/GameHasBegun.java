package it.polimi.ingsw.view.Gui.guiControllers;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class GameHasBegun implements Initializable {
    public Button thank;

    /**
     * This method is used to initialize the controller of the "GameHasBegun.fxml" GUI.
     *
     * @param url the url used to resolve relative paths for the root object, or null if the location is not known.
     * @param resourceBundle the resources used to localize the root object, or null if the root object was not localized.
     * @author Riccardo Figini
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        thank.setOnAction(this::onClose);
    }
    private void onClose(ActionEvent event) {
        Node node = (Node) event.getSource();
        Stage currentStage = (Stage) node.getScene().getWindow();
        currentStage.close();
    }
}
