package it.polimi.ingsw.View.Gui.guiControllers;

import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.WindowEvent;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * This class governs the GUI of the transition phase between a login request and its acceptance by the controller.
 * It is used as an intermediary between the fixed parts of the GUI of the 'AcceptedLogin.fxml' file and
 * the dynamic information that the controller sends to the graphic components of the associated fxml file.
 * In addition, the class manages the interaction between the user and the various graphic components of the scene.
 *
 * @author Alberto Aniballi
 */
public class AcceptedLoginController implements Initializable {
    public Label current_numPlayers_label;
    public Label member_players;
    public AnchorPane container_anchorPane;
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
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();

        current_numPlayers_label.setText(current_numPlayers);
        member_players.setText(members);
    }
}