package it.polimi.ingsw.View.Gui.guiControllers;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class StartController implements Initializable {
    public Button start_btn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        start_btn.setOnAction(event -> onStart(event));
    }

    private void onStart(ActionEvent event) {
        Node node = (Node) event.getSource();
        Stage currentStage = (Stage) node.getScene().getWindow();
        currentStage.close();

        ViewFactory.getInstance().showClientLogin();
    }
}
