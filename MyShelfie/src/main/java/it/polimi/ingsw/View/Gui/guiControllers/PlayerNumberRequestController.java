package it.polimi.ingsw.View.Gui.guiControllers;

import it.polimi.ingsw.View.OBSMessages.OBS_NumberOfPlayerMessage;
import it.polimi.ingsw.View.View;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class PlayerNumberRequestController implements Initializable {
    public TextField input_number_players;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        input_number_players.setOnKeyPressed(event -> getNumPlayersFromInput(event));
    }

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