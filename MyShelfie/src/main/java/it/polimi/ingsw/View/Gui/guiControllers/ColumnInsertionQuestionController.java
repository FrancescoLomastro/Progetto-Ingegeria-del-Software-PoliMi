package it.polimi.ingsw.View.Gui.guiControllers;

import it.polimi.ingsw.View.OBSMessages.OBS_MoveMessage;
import it.polimi.ingsw.View.OBSMessages.OBS_NumberOfPlayerMessage;
import it.polimi.ingsw.model.Utility.Position;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class ColumnInsertionQuestionController implements Initializable {
    public AnchorPane external_player_container;

    public VBox internal_vbox_container;
    public TextField input_column_question;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        input_column_question.setOnKeyPressed(event -> getColumnFromPlayer(event));
    }

    public void getColumnFromPlayer(KeyEvent event) {

        String numPlayers_Input;
        int parsedChosenColumn=0;
        boolean invalid_input=false;

        if (event.getCode() == KeyCode.ENTER) {
            numPlayers_Input = input_column_question.getText().trim();

            if (numPlayers_Input.length() > 0) {
                try {
                    parsedChosenColumn = Integer.parseInt(numPlayers_Input);
                } catch (NumberFormatException e) {
                    invalid_input = true;
                } finally {
                    if (invalid_input) {
                        input_column_question.setText("");
                    } else
                    {
                        Position[] positions = ViewFactory.getInstance().getPositions();
                        ViewFactory.getInstance().notifyAllOBS(new OBS_MoveMessage(positions,parsedChosenColumn));
                    }
                }
            }
        }

    }
}
