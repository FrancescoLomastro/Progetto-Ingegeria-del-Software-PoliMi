package it.polimi.ingsw.View.Gui.guiControllers;

import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.WindowEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class AcceptedLoginController implements Initializable {
    public Label current_numPlayers_label;
    public Label member_players;
    private String current_numPlayers;
    private String members;


    public void setCurrent_numPlayers(String current_numPlayers) {
        this.current_numPlayers = current_numPlayers;
    }

    public void setMember_players(String members) {
        this.members = members;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        current_numPlayers_label.setText(current_numPlayers);
        member_players.setText(members);
    }
}