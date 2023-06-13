package it.polimi.ingsw.view.Gui.guiControllers;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * This class governs the GUI dedicated to the error phase of creating a player within the game, after the user has entered and submitted the initial data
 * required to be able to participate in the game.
 * It is used as an intermediary between the fixed parts of the GUI of the 'ErrorClientCreation.fxml' file and
 * the dynamic information that the controller sends to the graphic components of the associated fxml file.
 * In addition, the class manages the interaction between the user and the various graphic components of the scene.
 *
 * @author Alberto Aniballi
 */
public class ErrorClientCreationController implements Initializable {
    public Label errorConnection_label;
    public Button retry_btn;
    public AnchorPane container_anchorPane;

    private int chosenPort;
    private String chosenAddress;

    public void setChosenAddress(String chosenAddress) {
        this.chosenAddress = chosenAddress;
    }

    public void setChosenPort(int chosenPort) {
        this.chosenPort = chosenPort;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();

        errorConnection_label.setStyle("-fx-text-alignment: center");
        errorConnection_label.setText("WARNING: \n It was impossible to create a client \n and contact the server at ["+chosenAddress+","+chosenPort+"]");
        retry_btn.setOnAction(event -> onRetry(event));
    }

    private void onRetry(ActionEvent event) {
        ViewFactory.getInstance().askInitialInfo();
    }
}
