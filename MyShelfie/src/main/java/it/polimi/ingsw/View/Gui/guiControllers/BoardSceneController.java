package it.polimi.ingsw.View.Gui.guiControllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.*;

import java.io.IOException;
import java.net.URL;
import java.nio.Buffer;
import java.util.ResourceBundle;

public class BoardSceneController implements Initializable {

    @FXML
    AnchorPane externalAnchorPane;

    @FXML
    BorderPane borderPane;
    @FXML
    VBox leftArea;
    @FXML
    VBox rightArea;
    @FXML
    HBox bottomArea;
    @FXML
    HBox topArea;Z
    @FXML
    VBox central;
    double leftRatioW,rightRatioW;
    double bottomRatioH, topRatioH;
    AnchorPane grid;
    GridPane_Controller controller;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        leftRatioW= leftArea.getPrefWidth()/ externalAnchorPane.getPrefWidth();
        rightRatioW= rightArea.getPrefWidth()/ externalAnchorPane.getPrefWidth();

        topRatioH= topArea.getPrefHeight()/ externalAnchorPane.getPrefHeight();
        bottomRatioH= bottomArea.getPrefHeight()/ externalAnchorPane.getPrefHeight();
        loadGrid();

        externalAnchorPane.heightProperty().addListener(((observableValue, oldHeight, newHeight) ->
        {
            topArea.setPrefHeight(externalAnchorPane.getHeight()*topRatioH);
            bottomArea.setPrefHeight(externalAnchorPane.getHeight()*bottomRatioH);

            //controller.resize(externalAnchorPane.getWidth()-leftArea.getPrefWidth()-rightArea.getPrefWidth(),externalAnchorPane.getHeight()-topArea.getPrefHeight()-bottomArea.getPrefHeight());
        }
        ));
        externalAnchorPane.widthProperty().addListener(((observableValue, oldHeight, newHeiggetht) ->
        {
            leftArea.setPrefWidth(externalAnchorPane.getWidth()*leftRatioW);
            rightArea.setPrefWidth(externalAnchorPane.getWidth()*rightRatioW);

           // controller.resize(externalAnchorPane.getWidth()-leftArea.getPrefWidth()-rightArea.getPrefWidth(),externalAnchorPane.getHeight()-topArea.getPrefHeight()-bottomArea.getPrefHeight());
        }
        ));

        //initializeGrid();
    }


    private void loadGrid() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/GridPane.fxml"));
        try {
            grid= loader.load();
            controller=loader.getController();
            borderPane.setCenter(grid);
            controller.ss();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /*private void initializeGrid() {
        GridPane gridPane = controller.getGridPane();
        for (int i=0;i<gridPane.getColumnCount();i++)
        {
            for (int j=0;j<gridPane.getRowCount();j++)
            {
                Pane btn = new Pane();
                btn.prefWidthProperty().bind(gridPane.getColumnConstraints().get(i).prefWidthProperty());
                btn.prefHeightProperty().bind(gridPane.getColumnConstraints().get(i).prefWidthProperty());//è giusto così, height non funziona
                btn.getStyleClass().add("invisibleCells"); //nel file GridPane.css

                gridPane.setRowIndex(btn, i);
                gridPane.setColumnIndex(btn, j);
                gridPane.getChildren().add(btn);
            }
        }
    }*/

}
