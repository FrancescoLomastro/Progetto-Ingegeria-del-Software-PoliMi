package it.polimi.ingsw.View.Gui.guiControllers;

import it.polimi.ingsw.model.Cards.ObjectCard;
import it.polimi.ingsw.model.Player.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class WinnerSceneController implements Initializable {

    private Player winner;
    private Player[] others;
    public Label title_label;
    public Label second;
    public Label third;
    public Label fourth;
    private ObjectCard[][] winnerLibrary;

    @FXML
    Button button;
    @FXML
    private void handleButtonAction(ActionEvent event) {
        // aprire la libreria del vincitore

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        winnerLibrary = winner.getLibrary().getMatrix();

        title_label.setText("player " + winner.getName() + " won!");

        //fare i controlli sul numero di giocatori
        second.setText("2nd: " + others[0].getName());
        third.setText("3rd: " + others[1].getName());
        fourth.setText("4th: " + others[2].getName());
    }

    public void setWinner(String winner) {
    }
}
