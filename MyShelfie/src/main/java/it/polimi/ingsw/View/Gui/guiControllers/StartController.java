package it.polimi.ingsw.View.Gui.guiControllers;
import it.polimi.ingsw.View.OBSMessages.OBS_MessageType;
import it.polimi.ingsw.View.OBSMessages.OBS_OnlyTypeMessage;
import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class StartController implements Initializable {
    @FXML
    public Label initialLabel;
    @FXML
    public ImageView title_img;
    @FXML
    public ImageView background;
    @FXML
    public AnchorPane anchorPane;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        anchorPane.setOnMouseClicked(event -> onStart(event));

        background.fitWidthProperty().bind(anchorPane.widthProperty());
        background.fitHeightProperty().bind(anchorPane.heightProperty());
        background.setScaleX(1.5);
        background.setScaleY(1.5);

        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(5), background);
        translateTransition.setFromX(-200);
        translateTransition.setToX(200);
        translateTransition.setFromY(-200);
        translateTransition.setToY(200);

        PauseTransition pauseTransition = new PauseTransition(Duration.millis(500));

        SequentialTransition sequentialTransition = new SequentialTransition(translateTransition, pauseTransition);
        sequentialTransition.setCycleCount(sequentialTransition.INDEFINITE);
        sequentialTransition.setAutoReverse(true);
        sequentialTransition.play();

        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(2), initialLabel);
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.0);
        fadeTransition.setCycleCount(sequentialTransition.INDEFINITE);
        fadeTransition.setAutoReverse(true);

        fadeTransition.play();
    }

    private void onStart(MouseEvent event) {
        ViewFactory.getInstance().notifyAllOBS(new OBS_OnlyTypeMessage(OBS_MessageType.START));
    }
}