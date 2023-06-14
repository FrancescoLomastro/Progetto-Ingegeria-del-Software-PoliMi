package it.polimi.ingsw.view.Gui.guiControllers;

import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * This class governs the GUI of the transition phase between a login request and its acceptance by the controller.
 * It is used as an intermediary between the fixed parts of the GUI of the 'AcceptedLogin.fxml' file and
 * the dynamic information that the controller sends to the graphic components of the associated fxml file.
 *
 * @author Alberto Aniballi
 */
public class AcceptedLoginController implements Initializable {
    public Label current_numPlayers_label;
    public Label member_players;
    public AnchorPane container_anchorPane;
    private String current_numPlayers;
    private String members;

    /**
     * This method update the current number of players label when a new player connect to the initial game lobby.
     * In this way the new number of current players will appear in the updated label text.
     *
     * @param current_numPlayers the updated number of players;
     * @author Alberto Aniballi
     */
    public void setCurrent_numPlayers(String current_numPlayers) {
        this.current_numPlayers = current_numPlayers;
    }

    /**
     * This method update the current member_players label when a new player connect to the initial game lobby.
     * In this way the name of the new player will appear in the updated label text.
     *
     * @param members the updated number of players;
     * @author Alberto Aniballi
     */
    public void setMember_players(String members) {
        this.members = members;
    }

    /**
     * This method is used to initialize the fxml controller of the accepted login GUI using the correct number and names
     * of already connected players, so that the lobby can dynamically change when players connect.
     *
     * @param url the url used to resolve relative paths for the root object, or null if the location is not known.
     * @param resourceBundle the resources used to localize the root object, or null if the root object was not localized.
     * @author Alberto Aniballi
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();

        current_numPlayers_label.setText(current_numPlayers);
        member_players.setText(members);
    }
}