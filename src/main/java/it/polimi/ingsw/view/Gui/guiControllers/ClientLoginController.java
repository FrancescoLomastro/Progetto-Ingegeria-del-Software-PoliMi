package it.polimi.ingsw.view.Gui.guiControllers;

import it.polimi.ingsw.view.OBSMessages.OBS_InitialInfoMessage;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * This class governs the GUI of the phase in which the user enters the login information that will then be used to connect
 * the player to the game or the pre-game lobby.
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
    public ToggleGroup toggleGroup;
    private String chosenUsername;
    private int chosenTechnology=-1;
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
     * @param url            the url used to resolve relative paths for the root object, or null if the location is not known.
     * @param resourceBundle the resources used to localize the root object, or null if the root object was not localized.
     * @author Alberto Aniballi
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        rmi_button.setOnAction(actionEvent -> getServerTechnologyFromInput(rmi_button));
        socket_button.setOnAction(actionEvent -> getServerTechnologyFromInput(socket_button));
        play_button.setOnAction(event -> onLogin());
        username_textfield.setOnKeyPressed(keyEvent -> {
            if(keyEvent.getCode()== KeyCode.ENTER){
                rmi_button.requestFocus();
            }
        });
        server_textfield.setOnKeyPressed(keyEvent -> {
            if(keyEvent.getCode()== KeyCode.ENTER){
                port_number_textfield.requestFocus();
            }
        });
        port_number_textfield.setOnKeyPressed(keyEvent -> {
            if(keyEvent.getCode()== KeyCode.ENTER){
                play_button.requestFocus();
            }
        });
    }

    /**
     * This method is used to retrieve, from the dedicated text field, the name that a new player writes.
     *
     * @author Alberto Aniballi
     * @return boolean: true if name is in good format
     */
    public boolean getUsernameFromInput() {
        String username_text = username_textfield.getText().trim();
        if (username_text.length() > 0) {
            setChosenUsername(username_text);
            rmi_button.requestFocus();
            return true;
        }
        return false;
    }


    /**
     * This method is used to retrieve the technology that a new player clicks on the "technology type" radio button.
     * Furthermore, this method updates the default ip address and port number on the server text field and port number
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
        server_textfield.setText("Default localhost");
        server_textfield.requestFocus();
    }

    /**
     * This method is used to retrieve, from the dedicated text field, the server that a new player writes.
     *
     * @author Alberto Aniballi
     * @return boolean: true if IP is in good format
     */
    public boolean getServerFromInput() {
        String server_ip = server_textfield.getText().trim();
        if (server_ip.length() > 0) {
            if (server_ip.equals("Default localhost")) {
                setChosenIPAddress("localhost");
            } else {
                setChosenIPAddress(server_ip);
            }
            port_number_textfield.requestFocus();
            return true;
        }
        return false;
    }

    /**
     * This method is used to retrieve, from the dedicated text field, the port number that a new player writes.
     *
     * @author Alberto Aniballi
     * @return boolean: true if port is in good format
     */
    public boolean getPortFromInput() {
        String server_port;
        int parsed_port;

        server_port = port_number_textfield.getText().trim();
        if (server_port.contains("Default")) {
            if (chosenTechnology == 0) {
                setChosenPort(ViewFactory.getInstance().getDefaultRMIPort());
            } else {
                setChosenPort(ViewFactory.getInstance().getDefaultSocketPort());
            }
            play_button.requestFocus();
            return true;
        }
        else if (server_port.length() > 0) {
            try {
                parsed_port = Integer.parseInt(server_port);
                if (parsed_port <= 0) {
                    return false;
                }
                setChosenPort(parsed_port);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return false;
    }

    /**
     * This method is used to send all the information, collected from the player, to the controller.
     * It notifies the controller by creating a new "OBS_InitialInfoMessage" message.
     * In case some data are not correct, it shows an error pop up and ask again the data
     * @author Alberto Aniballi
     */
    public void onLogin() {
        if(!getUsernameFromInput()) {
            ViewFactory.getInstance().showInvalidPort("Name not valid");
            username_textfield.setText("");
            return;
        }
        if(chosenTechnology==-1){
            ViewFactory.getInstance().showInvalidPort("Technology not valid");
            toggleGroup.selectToggle(null);
            return;
        }
        if(!getPortFromInput()){
            ViewFactory.getInstance().showInvalidPort("Port not valid");
            port_number_textfield.setText("");
            return;
        }
        if(!getServerFromInput()){
            ViewFactory.getInstance().showInvalidPort("IP-address not valid");
            server_textfield.setText("");
            return;
        }
        OBS_InitialInfoMessage initialInfoMessage = new OBS_InitialInfoMessage(chosenUsername, chosenTechnology, chosenIPAddress, chosenPort);
        ViewFactory.getInstance().notifyAllOBS(initialInfoMessage);
    }
}