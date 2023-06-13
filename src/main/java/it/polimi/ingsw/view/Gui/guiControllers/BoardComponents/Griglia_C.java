package it.polimi.ingsw.view.Gui.guiControllers.BoardComponents;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class Griglia_C {
    @FXML
    AnchorPane anchor;
    @FXML
    GridPane grid;
    @FXML
    Pane centralPointCard;
    double gridRatio;
    double layoutXRatio,layoutYRatio;

    double pointlayoutXRatio;
    double pointlayoutYRatio;
    double pointRatio;
    public void setListeners(BorderPane father) {
        gridRatio= grid.getPrefHeight()/anchor.getPrefHeight();
        layoutXRatio= grid.getLayoutX()/anchor.getPrefWidth();
        layoutYRatio= grid.getLayoutY()/anchor.getPrefHeight();
        pointRatio = centralPointCard.getPrefHeight()/anchor.getPrefWidth();
        pointlayoutXRatio = centralPointCard.getLayoutX()/anchor.getPrefWidth();
        pointlayoutYRatio = centralPointCard.getLayoutY()/anchor.getPrefHeight();


        anchor.heightProperty().addListener(((observableValue, oldHeight, newHeight) ->
        {
            maintainProportion();
        }
        ));
        anchor.widthProperty().addListener(((observableValue, oldHeight, newHeight) ->
        {
            maintainProportion();
        }
        ));

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

    private void maintainProportion() {
        grid.setPrefSize(anchor.getWidth()*gridRatio,anchor.getHeight()*gridRatio);
        grid.setLayoutX(anchor.getWidth()*layoutXRatio);
        grid.setLayoutY(anchor.getHeight()*layoutYRatio);
        centralPointCard.setPrefSize(anchor.getWidth()*pointRatio,anchor.getWidth()*pointRatio);
        centralPointCard.setLayoutX(anchor.getWidth()* pointlayoutXRatio);
        centralPointCard.setLayoutY(anchor.getHeight()* pointlayoutYRatio);
    }

    private void scaleDimension(double width,double height) {
        double min = Math.min(width,height);
        anchor.setPrefSize(min,min);
        anchor.setMaxSize(min,min);
    }
    public GridPane getGrid()
    {
        return grid;
    }
    public Pane getCentralPointCard(){ return centralPointCard;}
}

