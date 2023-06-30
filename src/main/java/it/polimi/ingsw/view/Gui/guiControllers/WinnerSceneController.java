package it.polimi.ingsw.view.Gui.guiControllers;

import it.polimi.ingsw.model.Cards.ObjectCard;
import it.polimi.ingsw.utility.Couple;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * This class is the controller associated to the "WinnerScene.fxml" file.
 *
 * @author Andrea Ferrini
 */
public class WinnerSceneController implements Initializable {
    private String winner;
    @FXML
    public Label first_label;
    @FXML
    public Label second_label;
    @FXML
    public Label third_label;
    @FXML
    public Label fourth_label;

    ArrayList<Couple<String, Integer>> finalRanking;


    /**
     * This method is used to initialize the controller of the "WinnerScene.fxml" GUI.
     *
     * @param url the url used to resolve relative paths for the root object, or null if the location is not known.
     * @param resourceBundle the resources used to localize the root object, or null if the root object was not localized.
     * @author Andrea Ferrini
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        first_label.setText("1st: " + finalRanking.get(0).getFirst() + " (" + finalRanking.get(0).getSecond() + " pts)");

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

    /**
     * This method get the winner of the game.
     *
     * @return the winner
     * @author Andrea Ferrini
     */
    public String getWinner() {
        return winner;
    }

    /**
     * This method set the winner of the game.
     *
     * @param winner : the name of the winner
     * @author Andrea Ferrini
     */
    public void setWinner(String winner) {
        this.winner = winner;
    }

    /**
     * This method get the final ranking of the game.
     *
     * @return the final ranking
     * @author Andrea Ferrini
     */
    public ArrayList<Couple<String, Integer>> getFinalRanking() {
        return finalRanking;
    }

    /**
     * This method get the final game ranking.
     *
     * @param finalRanking : the array containing each player associated to its points.
     * @author Andrea Ferrini
     */
    public void setFinalRanking(ArrayList<Couple<String, Integer>> finalRanking) {
        this.finalRanking = finalRanking;
    }
}
