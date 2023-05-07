package it.polimi.ingsw.View.Gui.guiControllers;

import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class AcceptedLoginController implements Initializable {
    public Label current_numPlayers_label;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /*
        Una volta che è stato trovato il modo corretto di implementare connessione tra client e server GUI, capire
        come trasferire il numero corrente di players qui.
         */
        current_numPlayers_label.setText("Currently in lobby: 1/3 players");
    }
}
