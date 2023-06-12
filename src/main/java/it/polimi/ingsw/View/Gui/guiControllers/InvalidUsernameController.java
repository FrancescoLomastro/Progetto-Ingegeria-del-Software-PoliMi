package it.polimi.ingsw.View.Gui.guiControllers;

import it.polimi.ingsw.View.OBSMessages.OBS_ChangedUsernameMessage;
import it.polimi.ingsw.View.OBSMessages.OBS_InitialInfoMessage;
import javafx.event.Event;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * This class governs the GUI dedicated to managing the unavailable choice of a player name by a client, it allows to choose another player name.
 * It is used as an intermediary between the fixed parts of the GUI of the 'InvalidUsername.fxml' file and
 * the dynamic information that the controller sends to the graphic components of the associated fxml file.
 * In addition, the class manages the interaction between the user and the various graphic components of the scene.
 *
 * @author Alberto Aniballi
 */
public class InvalidUsernameController implements Initializable {
    public TextField username_textfield;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        username_textfield.setOnKeyPressed(event -> getUsernameFromInput(event));
    }

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
