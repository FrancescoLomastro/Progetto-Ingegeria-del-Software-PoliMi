package it.polimi.ingsw.View.Gui.guiControllers;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class ErrorClientCreationController implements Initializable {
    public Label errorConnection_label;
    public Button retry_btn;

    private int chosenPort;
    private String chosenAddress;

    public void setChosenAddress(String chosenAddress) {
        this.chosenAddress = chosenAddress;
    }

    public void setChosenPort(int chosenPort) {
        this.chosenPort = chosenPort;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        errorConnection_label.setStyle("-fx-text-alignment: center");
        errorConnection_label.setText("WARNING: \n It was impossible to create a client \n and contact the server at ["+chosenAddress+","+chosenPort+"]");
        retry_btn.setOnAction(event -> onRetry(event));
    }

    private void onRetry(ActionEvent event) {
        ViewFactory.getInstance().askInitialInfo();
    }
}
