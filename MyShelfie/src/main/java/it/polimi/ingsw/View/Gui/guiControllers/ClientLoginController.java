package it.polimi.ingsw.View.Gui.guiControllers;

import it.polimi.ingsw.controller.ClientController;
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
    private String userName;
    private String serverTechnology;
    private String serverIP;
    private int portNumber;
    private ClientController clientController;

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setServerTechnology(String serverTechnology) {
        this.serverTechnology = serverTechnology;
    }

    public void setServerIP(String serverIP) {
        this.serverIP = serverIP;
    }

    public void setPortNumber(int portNumber) {
        this.portNumber = portNumber;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        socket_button.setDisable(true);
        rmi_button.setDisable(true);
        server_textfield.setDisable(true);
        port_number_textfield.setDisable(true);
        login_button.setVisible(false);

        username_textfield.setOnKeyPressed(event -> getUsernameFromInput(event));
        rmi_button.setOnAction(actionEvent -> getServerTechnologyFromInput(rmi_button));
        socket_button.setOnAction(actionEvent -> getServerTechnologyFromInput(socket_button));
        server_textfield.setOnKeyPressed(event -> getServerFromInput(event));
        port_number_textfield.setOnKeyPressed(event -> getPortFromInput(event));
      //  login_button.setOnAction(event -> onLogin());     Qui devi notificare l'osservatore, non creare tu il client
    }

    public void getUsernameFromInput(KeyEvent keyEvent) {

        if (keyEvent.getCode() == KeyCode.ENTER) {
            String username_text = username_textfield.getText();

            if (username_text.length() > 0) {
                setUserName(username_text);
                title_label.setText("Welcome " +username_text + "!");
                username_textfield.setDisable(true);
                rmi_button.setDisable(false);
                socket_button.setDisable(false);
            }
        }

    }

    public void getServerTechnologyFromInput(RadioButton radioButton) {

        if (radioButton.getText().equals("RMI")) {
            setServerTechnology("RMI");
        } else {
            setServerTechnology("Socket");
        }

        rmi_button.setDisable(true);
        socket_button.setDisable(true);
        server_textfield.setDisable(false);

    }

    public void getServerFromInput(KeyEvent keyEvent) {

        if (keyEvent.getCode() == KeyCode.ENTER) {
            String server_ip = server_textfield.getText();

            if (server_ip.length() > 0) {
                setServerIP(server_ip);
                server_textfield.setDisable(true);
                port_number_textfield.setDisable(false);
            }
        }

    }

    public void getPortFromInput(KeyEvent keyEvent) {

        String server_port;
        int parsed_port;
        boolean invalid_input=false;

        if (keyEvent.getCode() == KeyCode.ENTER) {
            server_port = port_number_textfield.getText();
            if (server_port.length() > 0) {
                try {
                    parsed_port = Integer.parseInt(server_port);
                    if (parsed_port <= 0) {
                        invalid_input = true;
                    }
                } catch (NumberFormatException e) {
                    invalid_input = true;
                } finally {
                    if (invalid_input) {
                        port_number_textfield.setText("");
                        ViewFactory.getInstance().showInvalidPort();
                    } else {
                        setPortNumber(Integer.parseInt(server_port));
                        port_number_textfield.setDisable(true);
                        login_button.setVisible(true);
                    }
                }
            }
        }
    }


    //DA TOGLIERE, NON TE NE OCCUPI TU
   /* public void onLogin() {

        clientController = new ClientController();

        switch (serverTechnology) {
            case "RMI" -> {
                clientController.createClient(userName,0,serverIP,portNumber);
            }
            case "Socket" -> {
                clientController.createClient(userName,1,serverIP,portNumber);
            }
        }
    }*/
}
