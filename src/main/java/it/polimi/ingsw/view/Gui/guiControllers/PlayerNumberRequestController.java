package it.polimi.ingsw.view.Gui.guiControllers;

import it.polimi.ingsw.view.OBSMessages.OBS_NumberOfPlayerMessage;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * This class governs the GUI dedicated to managing the choice of the number of players who will participate in the game,
 * the choice is made by the first player to connect to the server.
 * It is used as an intermediary between the fixed parts of the GUI of the 'PlayerNumberRequest.fxml' file and
 * the dynamic information that the controller sends to the graphic components of the associated fxml file.
 * In addition, the class manages the interaction between the user and the various graphic components of the scene.
 *
 * @author Alberto Aniballi
 */
public class PlayerNumberRequestController implements Initializable {
    public TextField input_number_players;
    public VBox internal_vbox_container;

    /**
     * This method is used to initialize the controller of the "PlayerNumberRequest.fxml" GUI. In particular,
     * it sets an event listener to "number of players" text field.
     *
     * @param url the url used to resolve relative paths for the root object, or null if the location is not known.
     * @param resourceBundle the resources used to localize the root object, or null if the root object was not localized.
     * @author Alberto Aniballi
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        input_number_players.setOnKeyPressed(event -> getNumPlayersFromInput(event));
    }

    /**
     * This method is used to retrieve, from the dedicated text field, the number of players that the first new player chooses
     * for the game when it connects to the server. It processes the number only after the player presses enter on the keyboard.
     * Furthermore, this method checks if a valid number has been chosen.
     *
     * @param keyEvent the event that triggers the activation of the method;
     * @author Alberto Aniballi
     */
    private void getNumPlayersFromInput(KeyEvent keyEvent) {

        String numPlayers_Input;
        int parsed_numPlayers=0;
        boolean invalid_input=false;

        if (keyEvent.getCode() == KeyCode.ENTER) {
            numPlayers_Input = input_number_players.getText().trim();

            if (numPlayers_Input.length() > 0) {
                try {
                    parsed_numPlayers = Integer.parseInt(numPlayers_Input);
                    if ((parsed_numPlayers <= 1) || (parsed_numPlayers>4)) {
                        invalid_input = true;
                    }
                } catch (NumberFormatException e) {
                    invalid_input = true;
                } finally {
                    if (invalid_input) {
                        input_number_players.setText("");
                        ViewFactory.getInstance().showInvalidNumPlayers();
                    } else
                    {
                        input_number_players.setDisable(true);
                        ViewFactory.getInstance().notifyAllOBS(new OBS_NumberOfPlayerMessage(parsed_numPlayers));
                    }
                }
            }
        }

    }
}