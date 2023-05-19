package it.polimi.ingsw.View.Gui.guiControllers.BoardComponents;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class Griglia_C {
    @FXML
    AnchorPane anchor;
    @FXML
    GridPane grid;
    double gridRatio;
    double layoutXRatio,layoutYRatio;
    public void setListeners(BorderPane father) {
        gridRatio= grid.getPrefHeight()/anchor.getPrefHeight();
        layoutXRatio= grid.getLayoutX()/anchor.getPrefWidth();
        layoutYRatio= grid.getLayoutY()/anchor.getPrefHeight();
        father.heightProperty().addListener(((observableValue, oldHeight, newHeight) ->
        {
            scaleDimension(father.getWidth(),father.getHeight());
        }
        ));
        father.widthProperty().addListener(((observableValue, oldHeight, newHeight) ->
        {
            scaleDimension(father.getWidth(),father.getHeight());
        }
        ));
    }

    private void scaleDimension(double width,double height) {
        double min = Math.min(width,height);
        anchor.setPrefSize(min,min);
        anchor.setMaxSize(min,min);
        grid.setPrefSize(min*gridRatio,min*gridRatio);
        grid.setLayoutX(min*layoutXRatio);
        grid.setLayoutY(min*layoutYRatio);
    }
    public GridPane getGrid()
    {
        return grid;
    }
}

