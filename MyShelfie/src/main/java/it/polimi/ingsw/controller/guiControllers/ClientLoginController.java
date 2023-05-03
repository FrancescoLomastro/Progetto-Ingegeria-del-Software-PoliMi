package it.polimi.ingsw.controller.guiControllers;

import it.polimi.ingsw.ClientApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientLoginController implements Initializable {
    public RadioButton socket_button;
    public TextField username_textfield;
    public HBox technology_type_selector;
    public RadioButton rmi_button;
    public TextField server_textfield;
    public TextField port_number_textfield;
    public Button login_button;
    public Label title_label;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        socket_button.setDisable(true);
        rmi_button.setDisable(true);
        server_textfield.setDisable(true);
        port_number_textfield.setDisable(true);
        login_button.setDisable(true);

        username_textfield.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                title_label.setText("Welcome " + username_textfield.getText() + "!");
                username_textfield.setDisable(true);
                rmi_button.setDisable(false);
                socket_button.setDisable(false);
            }
        });
    }

    /*
    @FXML
    public void startGame() {

        String[] args = {};
        ClientApp.main(args);
    }
    */
}
