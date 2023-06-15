package it.polimi.ingsw.view.Gui.guiControllers;

import it.polimi.ingsw.view.OBSMessages.OBS_InitialInfoMessage;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Screen;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * This class governs the GUI of the phase in which the user enters the login information that will then be used to connect the player to the game or the pre-game lobby.
 * It is used as an intermediary between the fixed parts of the GUI of the 'ClientLogin.fxml' file and
 * the dynamic information that the controller sends to the graphic components of the associated fxml file.
 * In addition, the class manages the interaction between the user and the various graphic components of the scene.
 *
 * @author Alberto Aniballi
 */
public class ClientLoginController implements Initializable {
    public RadioButton socket_button;
    public TextField username_textfield;
    public HBox technology_type_selector;
    public RadioButton rmi_button;
    public TextField server_textfield;
    public TextField port_number_textfield;
    public Button play_button;
    public Label title_label;
    public AnchorPane external_options_container;
    public AnchorPane container_anchorPane;
    private String chosenUsername;
    private int chosenTechnology;
    private String chosenIPAddress;
    private int chosenPort;

    /**
     * This method is used to set the name that a new player chooses during the pre-game lobby.
     * In this way the name of the new player is stored, later it will be sent to the controller.
     *
     * @param chosenUsername the name of the player.
     * @author Alberto Aniballi
     */
    public void setChosenUsername(String chosenUsername) {
        this.chosenUsername = chosenUsername;
    }

    /**
     * This method is used to set the technology that a new player chooses during the pre-game lobby.
     * The technology can be of type socket or rmi, it will be used to connect the player to the server throughout
     * the entire game without the possibility to change it afterward.
     *
     * @param chosenTechnology the chosen technology.
     * @author Alberto Aniballi
     */
    public void setChosenTechnology(int chosenTechnology) {
        this.chosenTechnology = chosenTechnology;
    }

    /**
     * This method is used to set the ip address that a new player chooses during the pre-game lobby.
     * The chosen ip address will be used to connect the player to the server throughout the entire game
     * without the possibility to change it afterward.
     *
     * @param chosenIPAddress the chosen ip address;
     * @author Alberto Aniballi
     */
    public void setChosenIPAddress(String chosenIPAddress) {
        this.chosenIPAddress = chosenIPAddress;
    }

    /**
     * This method is used to set the port number that a new player chooses during the pre-game lobby.
     * The chosen port number will be used to connect the player to the server throughout the entire game
     * without the possibility to change it afterward.
     *
     * @param chosenPort the chosen port number.
     * @author Alberto Aniballi
     */
    public void setChosenPort(int chosenPort) {
        this.chosenPort = chosenPort;
    }

    /**
     * This method is used to initialize the fxml controller of the client login GUI.
     *
     * @param url the url used to resolve relative paths for the root object, or null if the location is not known.
     * @param resourceBundle the resources used to localize the root object, or null if the root object was not localized.
     * @author Alberto Aniballi
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Screen screen = Screen.getPrimary();

        socket_button.setDisable(true);
        rmi_button.setDisable(true);
        server_textfield.setDisable(true);
        port_number_textfield.setDisable(true);
        play_button.setVisible(false);

        username_textfield.setOnKeyPressed(event -> getUsernameFromInput(event));
        rmi_button.setOnAction(actionEvent -> getServerTechnologyFromInput(rmi_button));
        socket_button.setOnAction(actionEvent -> getServerTechnologyFromInput(socket_button));
        server_textfield.setOnKeyPressed(event -> getServerFromInput(event));
        port_number_textfield.setOnKeyPressed(event -> getPortFromInput(event));
        play_button.setOnAction(event -> onLogin(event));
    }

    /**
     * This method is used to retrieve, from the dedicated text field, the name that a new player writes. It processes the name
     * only after the player presses enter on the keyboard. Furthermore, this method update the title label using the name
     * that the player has just written on the text field.
     *
     * @param keyEvent the event that triggers the activation of the method;
     * @author Alberto Aniballi
     */
    public void getUsernameFromInput(KeyEvent keyEvent) {

        if (keyEvent.getCode() == KeyCode.ENTER) {
            String username_text = username_textfield.getText().trim();

            if (username_text.length() > 0) {
                setChosenUsername(username_text);
                title_label.setText("Welcome " +username_text + "!");
                rmi_button.setDisable(false);
                socket_button.setDisable(false);
                rmi_button.requestFocus();
            }
        }

    }

    /**
     * This method is used to retrieve the technology that a new player clicks on the "technology type" radio button.
     * Furthermore, this method update the default ip address and port number on the server text field and port number
     * text field.
     *
     * @param radioButton the radio button used to show available technology types.
     * @author Alberto Aniballi
     */
    public void getServerTechnologyFromInput(RadioButton radioButton) {

        if (radioButton.getText().equals("RMI")) {
            setChosenTechnology(0);
            port_number_textfield.setText("Default ("+ViewFactory.getInstance().getDefaultRMIPort()+")");
        } else {
            setChosenTechnology(1);
            port_number_textfield.setText("Default ("+ViewFactory.getInstance().getDefaultSocketPort()+")");
        }
        server_textfield.setDisable(false);
        server_textfield.setText("Default localhost");
        server_textfield.requestFocus();
    }

    /**
     * This method is used to retrieve, from the dedicated text field, the server that a new player writes. It processes
     * the server only after the player presses enter on the keyboard.
     *
     * @param keyEvent the event that triggers the activation of the method;
     * @author Alberto Aniballi
     */
    public void getServerFromInput(KeyEvent keyEvent) {

        if (keyEvent.getCode() == KeyCode.ENTER) {
            String server_ip = server_textfield.getText().trim();

            if (server_ip.length() > 0) {

                if (server_ip.equals("Default localhost"))
                {
                    setChosenIPAddress("localhost");
                }
                else
                {
                    setChosenIPAddress(server_ip);
                }
            }
            port_number_textfield.setDisable(false);
            port_number_textfield.requestFocus();
        }

    }

    /**
     * This method is used to retrieve, from the dedicated text field, the port number that a new player writes.
     * It processes the port number only after the player presses enter on the keyboard.
     *
     * @param keyEvent the event that triggers the activation of the method;
     * @author Alberto Aniballi
     */
    public void getPortFromInput(KeyEvent keyEvent) {

        String server_port;
        int parsed_port = 0;
        boolean invalid_input=false;

        if (keyEvent.getCode() == KeyCode.ENTER) {
            server_port = port_number_textfield.getText().trim();

            if (server_port.contains("Default")) {
                if (chosenTechnology == 0) {
                    setChosenPort(ViewFactory.getInstance().getDefaultRMIPort());
                } else {
                    setChosenPort(ViewFactory.getInstance().getDefaultSocketPort());
                }
                play_button.setVisible(true);
                play_button.requestFocus();

            } else if (server_port.length() > 0) {
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
                        setChosenPort(parsed_port);
                        play_button.setVisible(true);
                        play_button.requestFocus();
                    }
                }
            }
        }
    }

    /**
     * This method is used to send all the information, collected from the player, to the controller.
     * It notifies the controller by creating a new "OBS_InitialInfoMessage" message.
     *
     * @param event the event that triggers the activation of the method;
     * @author Alberto Aniballi
     */
    public void onLogin(ActionEvent event) {
            OBS_InitialInfoMessage initialInfoMessage = new OBS_InitialInfoMessage(chosenUsername, chosenTechnology, chosenIPAddress, chosenPort);
            ViewFactory.getInstance().notifyAllOBS(initialInfoMessage);
    }
}