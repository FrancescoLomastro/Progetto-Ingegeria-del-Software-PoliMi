package it.polimi.ingsw.View.Gui.guiControllers;

import it.polimi.ingsw.model.Cards.ObjectCard;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

// MANCA L'INVOCAZIONE DELLA SCHERMATA AL MOMENTO GIUSTO

public class WinnerSceneController implements Initializable {

    private  int i;
    private String winner;
    private String[] others;
    @FXML
    public Label winner_label;
    @FXML
    public Label second_label;
    @FXML
    public Label third_label;
    @FXML
    public Label fourth_label;

    @FXML
    Button button_show_library;
    ObjectCard[][] winnerLibrary;
    Map<String, Integer> points;
    @FXML
    private void handleButtonAction(String winner) {

        ViewFactory.getInstance().onLibraryClick(winner);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        points = new HashMap<>();

        winner_label.setText("player " + winner + " won!");

        button_show_library.setOnMouseClicked(mouseEvent -> {

            handleButtonAction(winner);
        });

        // non va bene perchè non è ordinato in base ai punteggi
        others = ViewFactory.getInstance().getClientModel().getPlayerNames();
        if(others.length > 1) {
            second_label.setText("2nd: " + others[1]);
        }

        if(others.length > 2) {
            third_label.setText("3rd: " + others[2]);
        }

        if(others.length > 3) {
            fourth_label.setText("4th: " + others[3]);
        }
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public ObjectCard[][] getWinnerLibrary() {
        return winnerLibrary;
    }

    public void setWinnerLibrary(ObjectCard[][] winnerLibrary) {
        this.winnerLibrary = winnerLibrary;
    }
}
