package it.polimi.ingsw.View.Gui.guiControllers;

import it.polimi.ingsw.model.Cards.ObjectCard;
import it.polimi.ingsw.model.Utility.Couple;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;


public class WinnerSceneController implements Initializable {

    private String winner;
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
    ArrayList<Couple<String, Integer>> finalRanking;
    @FXML
    private void handleButtonAction(String winner) {

        ViewFactory.getInstance().onLibraryClick(winner);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        winner_label.setText(winner + " won!");

        button_show_library.setOnMouseClicked(mouseEvent -> {

            handleButtonAction(winner);
        });

        if(finalRanking.size() > 1) {

            second_label.setText("2nd: " + finalRanking.get(1).getFirst() + " (" + finalRanking.get(1).getSecond() + " pts)");
        }

        if(finalRanking.size() > 2) {

            third_label.setText("3rd: " + finalRanking.get(2).getFirst() + " (" + finalRanking.get(2).getSecond() + " pts)");
        }

        if(finalRanking.size() > 3) {

            fourth_label.setText("4th: " + finalRanking.get(3).getFirst() + " (" + finalRanking.get(3).getSecond() + " pts)");
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

    public ArrayList<Couple<String, Integer>> getFinalRanking() {
        return finalRanking;
    }

    public void setFinalRanking(ArrayList<Couple<String, Integer>> finalRanking) {
        this.finalRanking = finalRanking;
    }
}
