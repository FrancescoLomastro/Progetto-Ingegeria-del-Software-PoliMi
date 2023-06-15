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

    /**
     * This method is used to set the ip address that a new player chose during the pre-game lobby.
     * The chosen ip address will be displayed inside the warning message of the stage.
     *
     * @param chosenAddress the chosen ip address;
     * @author Alberto Aniballi
     */
    public void setChosenAddress(String chosenAddress) {
        this.chosenAddress = chosenAddress;
    }

    /**
     * This method is used to set the port that a new player chose during the pre-game lobby.
     * The chosen port number will be displayed inside the warning message of the stage.
     *
     * @param chosenPort the chosen ip address;
     * @author Alberto Aniballi
     */
    public void setChosenPort(int chosenPort) {
        this.chosenPort = chosenPort;
    }

    /**
     * This method is used to initialize the controller of the "ErrorClientCreation.fxml" GUI.
     *
     * @param url the url used to resolve relative paths for the root object, or null if the location is not known.
     * @param resourceBundle the resources used to localize the root object, or null if the root object was not localized.
     * @author Alberto Aniballi
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();

        errorConnection_label.setStyle("-fx-text-alignment: center");
        errorConnection_label.setText("WARNING: \n It was impossible to create a client \n and contact the server at ["+chosenAddress+","+chosenPort+"]");
        retry_btn.setOnAction(event -> onRetry(event));
    }

    /**
     * This method is used to close the "ErrorClientCreation" stage and reactivate the previous stage.
     *
     * @param event the event that triggers the activation of the method;
     * @author Alberto Aniballi
     */
    private void onRetry(ActionEvent event) {
        ViewFactory.getInstance().askInitialInfo();
    }
}
