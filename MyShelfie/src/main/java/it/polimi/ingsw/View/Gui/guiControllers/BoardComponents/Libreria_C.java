package it.polimi.ingsw.View.Gui.guiControllers.BoardComponents;

import javafx.fxml.FXML;
import javafx.scene.layout.*;

public class Libreria_C {
    @FXML
    AnchorPane anchor;
    @FXML
    Pane bottom;
    @FXML
    Pane top;
    @FXML
    Pane left;
    @FXML
    Pane right;
    double bottomRatio;
    double topRatio;
    double leftRatio;
    double rightRatio;
    double fatherParts;
    @FXML
    GridPane grid;
    double gridRatioW;
    double gridRatioH;
    double layoutXRatio,layoutYRatio;
    double hGapRatio;
    double centerRatio;

    public void setListeners(VBox father,double parts)
    {
        fatherParts = parts;
        bottomRatio = bottom.getPrefHeight()/anchor.getPrefHeight();
        topRatio = top.getPrefHeight()/anchor.getPrefHeight();
        leftRatio = left.getPrefWidth()/anchor.getPrefWidth();
        rightRatio = right.getPrefWidth()/anchor.getPrefWidth();
        gridRatioW= grid.getPrefWidth()/anchor.getPrefWidth();
        gridRatioH= grid.getPrefHeight()/anchor.getPrefHeight();
        layoutXRatio= grid.getLayoutX()/anchor.getPrefWidth();
        layoutYRatio= grid.getLayoutY()/anchor.getPrefHeight();
        hGapRatio = grid.getHgap()/anchor.getPrefWidth();


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

    private void scaleDimension(double width,double height) {
        double min = Math.min(width,height/fatherParts);
        anchor.setPrefSize(min,min);
        anchor.setMaxSize(min,min);
        grid.setPrefSize(min*gridRatioW,min*gridRatioH);
        grid.setLayoutX(min*layoutXRatio);
        grid.setLayoutY(min*layoutYRatio);
        grid.setHgap(min*hGapRatio);
    }

    private void maintainProportion() {
        bottom.setPrefHeight(anchor.getPrefHeight()*bottomRatio);
        top.setPrefHeight(anchor.getPrefHeight()*topRatio);
        left.setPrefWidth(anchor.getPrefWidth()*leftRatio);
        right.setPrefWidth(anchor.getPrefWidth()*rightRatio);
    }
    public GridPane getGrid()
    {
        return grid;
    }
}
