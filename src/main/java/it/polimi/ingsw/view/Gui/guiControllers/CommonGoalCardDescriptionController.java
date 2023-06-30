package it.polimi.ingsw.view.Gui.guiControllers;

import it.polimi.ingsw.network.Client.ClientModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * This class is the controller associated to the "CommonGoalCardDescription.fxml" file.
 *
 * @author Andrea Ferrini
 */
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
    }

    /**
     * This method set the correct common goal card picture in the dedicated pane.
     *
     * @author Andrea Ferrini
     */
    private void setPicture(){

        ClientModel clientModel = ViewFactory.getInstance().getClientModel();

        if (num == 1) {
            picture_pane.getStyleClass().add("common"+clientModel.getFirstCommonGoalId());
        }
        else if(num == 2){
            picture_pane.getStyleClass().add("common"+clientModel.getSecondCommonGoalId());
        }
    }

    /**
     * This method gets the common goal card description.
     *
     * @return String, description of the card
     * @author Andrea Ferrini
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method set the common goal card description.
     *
     * @param description : description to be displayed;
     * @author Andrea Ferrini
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * This method gets the common goal card number.
     *
     * @return int, number of the cards
     * @author Andrea Ferrini
     */
    public int getNum() {
        return num;
    }

    /**
     * This method gets the common goal card number.
     *
     * @param num number to be displayed;
     * @author Andrea Ferrini
     */
    public void setNum(int num) {
        this.num = num;
    }
}
