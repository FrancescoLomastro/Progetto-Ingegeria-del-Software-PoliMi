package it.polimi.ingsw.View.Gui.guiControllers;

import javafx.event.Event;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class PlayerNumberRequestController implements Initializable {
    public TextField input_number_players;
    private int numPlayers;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        input_number_players.setOnKeyPressed(event -> getNumPlayersFromInput(event));
    }

    public void setNumPlayers(int numPlayers) {
        this.numPlayers = numPlayers;
    }

    private void getNumPlayersFromInput(KeyEvent keyEvent) {

        String numPlayers_Input;
        int parsed_numPlayers;
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
                    } else {
                        setNumPlayers(Integer.parseInt(numPlayers_Input));
                        input_number_players.setDisable(true);
                        ViewFactory.getInstance().setEvent(keyEvent);
                        ViewFactory.getInstance().showAcceptedLogin();
                    }
                }
            }
        }

    }
}
