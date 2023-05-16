package it.polimi.ingsw.View.Gui.guiControllers;

import it.polimi.ingsw.View.OBSMessages.OBS_InitialInfoMessage;
import it.polimi.ingsw.controller.ClientController;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Screen;

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
    public AnchorPane external_options_container;
    public AnchorPane clientLoging_container;
    public AnchorPane container_anchorPane;
    public ImageView backgound_image;
    private String chosenUsername;
    private int chosenTechnology;
    private String chosenIPAddress;
    private int chosenPort;
    private ClientController clientController;

    public void setChosenUsername(String chosenUsername) {
        this.chosenUsername = chosenUsername;
    }

    public void setChosenTechnology(int chosenTechnology) {
        this.chosenTechnology = chosenTechnology;
    }

    public void setChosenIPAddress(String chosenIPAddress) {
        this.chosenIPAddress = chosenIPAddress;
    }

    public void setChosenPort(int chosenPort) {
        this.chosenPort = chosenPort;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();

        backgound_image.setFitWidth(bounds.getWidth());
        backgound_image.setFitHeight(bounds.getHeight());
        backgound_image.setScaleX(1.25);
        backgound_image.setScaleY(1.1);

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
        login_button.setOnAction(event -> onLogin(event));
    }

    public void getUsernameFromInput(KeyEvent keyEvent) {

        if (keyEvent.getCode() == KeyCode.ENTER) {
            String username_text = username_textfield.getText().trim();

            if (username_text.length() > 0) {
                setChosenUsername(username_text);
                //title_label.setText("Welcome " +username_text + "!");
                username_textfield.setDisable(true);
                rmi_button.setDisable(false);
                socket_button.setDisable(false);
                title_label.setStyle("-fx-text-alignment: center");
            }
        }

    }

    public void getServerTechnologyFromInput(RadioButton radioButton) {

        if (radioButton.getText().equals("RMI")) {
            setChosenTechnology(0);
        } else {
            setChosenTechnology(1);
        }

        rmi_button.setDisable(true);
        socket_button.setDisable(true);
        server_textfield.setDisable(false);
        server_textfield.setText("Default localhost");

    }

    public void getServerFromInput(KeyEvent keyEvent) {

        if (keyEvent.getCode() == KeyCode.ENTER) {
            String server_ip = server_textfield.getText().trim();

            if (server_ip.length() > 0) {

                if (server_ip.contains("Default")) {
                    setChosenIPAddress("localhost");
                }
                else {
                    setChosenIPAddress(server_ip);
                }

                server_textfield.setDisable(true);
                port_number_textfield.setDisable(false);

                if (chosenTechnology == 0) {
                    port_number_textfield.setText("Default RMI port number");
                } else {
                    port_number_textfield.setText("Default Socket port number");
                }
            }
        }

    }

    public void getPortFromInput(KeyEvent keyEvent) {

        String server_port;
        int parsed_port;
        boolean invalid_input=false;

        if (keyEvent.getCode() == KeyCode.ENTER) {
            server_port = port_number_textfield.getText().trim();

            if (server_port.contains("Default")) {
                if (chosenTechnology == 1) {
                    setChosenPort(8000);
                } else {
                    setChosenPort(9000);
                }
                port_number_textfield.setDisable(true);
                login_button.setVisible(true);

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
                        setChosenPort(Integer.parseInt(server_port));
                        port_number_textfield.setDisable(true);
                        login_button.setVisible(true);
                    }
                }
            }
        }
    }

    public void onLogin(ActionEvent event) {
        OBS_InitialInfoMessage initialInfoMessage = new OBS_InitialInfoMessage(chosenUsername,chosenTechnology,chosenIPAddress,chosenPort);
        ViewFactory.getInstance().notifyAllOBS(initialInfoMessage);
    }
}