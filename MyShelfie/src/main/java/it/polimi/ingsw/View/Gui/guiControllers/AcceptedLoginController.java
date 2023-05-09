package it.polimi.ingsw.View.Gui.guiControllers;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class AcceptedLoginController implements Initializable {
    public Label current_numPlayers_label;
    public Button login_button;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /*
        Una volta che è stato trovato il modo corretto di implementare connessione tra client e server GUI, capire
        come trasferire il numero corrente di players qui.
         */
        current_numPlayers_label.setText("Currently in lobby: 1/3 players");
        login_button.setOnAction(event -> onLog(event));
    }

    public void onLog(ActionEvent event) {
        ViewFactory.getInstance().setEvent(event);
        ViewFactory.getInstance().showLivingroom();
    }
}
