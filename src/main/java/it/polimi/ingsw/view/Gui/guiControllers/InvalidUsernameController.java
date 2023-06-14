package it.polimi.ingsw.view.Gui.guiControllers;

import it.polimi.ingsw.view.OBSMessages.OBS_ChangedUsernameMessage;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * This class governs the GUI dedicated to managing the unavailable choice of a player name by a client, it allows to choose another player name.
 * It is used as an intermediary between the fixed parts of the GUI of the 'InvalidUsername.fxml' file and
 * the dynamic information that the controller sends to the graphic components of the associated fxml file.
 *
 * @author Alberto Aniballi
 */
public class InvalidUsernameController implements Initializable {
    public TextField username_textfield;

    /**
     * This method is used to initialize the fxml controller of the "invalid name" GUI.
     *
     * @param url the url used to resolve relative paths for the root object, or null if the location is not known.
     * @param resourceBundle the resources used to localize the root object, or null if the root object was not localized.
     * @author Alberto Aniballi
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        username_textfield.setOnKeyPressed(event -> getUsernameFromInput(event));
    }

    /**
     * This method is used to retrieve, from the dedicated text field, the new name that a new player writes. It processes the name
     * only after the player presses enter on the keyboard.
     *
     * @param keyEvent the event that triggers the activation of the method;
     * @author Alberto Aniballi
     */
    private void getUsernameFromInput(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            String username_text = username_textfield.getText().trim();

            if (username_text.length() > 0) {
                OBS_ChangedUsernameMessage usernameMessage = new OBS_ChangedUsernameMessage(username_text);
                ViewFactory.getInstance().notifyAllOBS(usernameMessage);
            }
        }
    }
}
