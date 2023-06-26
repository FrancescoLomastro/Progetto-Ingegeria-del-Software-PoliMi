package it.polimi.ingsw.view.Gui.guiControllers;

import it.polimi.ingsw.network.Client.ClientModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class CommonGoalCardDescriptionController implements Initializable {

    @FXML
    public Label description_label;
    @FXML
    public Label title_label;
    @FXML
    public Pane picture_pane;
    @FXML
    public ImageView picture;
    private String description;
    private  int num = 0;

    /**
     * This method is used to initialize the controller of the "CommonGoalCardDescription.fxml" GUI.
     *
     * @param url the url used to resolve relative paths for the root object, or null if the location is not known.
     * @param resourceBundle the resources used to localize the root object, or null if the root object was not localized.
     * @author Andrea Ferrini
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        description_label.setText(description);
        title_label.setText("Try to achieve this goal first!");
        setPicture();
        //picture_pane.getStyleClass().add("common1");
    }

    private void setPicture(){

        ClientModel clientModel = ViewFactory.getInstance().getClientModel();

        if (num == 1) {
            picture_pane.getStyleClass().add("common"+clientModel.getFirstCommonGoalId());
        }
        else if(num == 2){
            picture_pane.getStyleClass().add("common"+clientModel.getSecondCommonGoalId());
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
