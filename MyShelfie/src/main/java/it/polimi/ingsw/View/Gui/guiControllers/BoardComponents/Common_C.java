package it.polimi.ingsw.View.Gui.guiControllers.BoardComponents;


import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class Common_C {

    @FXML
    AnchorPane anchor;
    @FXML
    Pane image;
    @FXML
    Pane point;

    double imageRatio;
    double pointlayoutXRatio;
    double pointlayoutYRatio;
    double pointRatio;
    double fatherParts;

    public void setListeners(VBox father, double parts)
    {
        fatherParts = parts;
        imageRatio = image.getPrefHeight()/anchor.getPrefHeight();
        pointRatio = point.getPrefHeight()/anchor.getPrefWidth();
        pointlayoutXRatio = point.getLayoutX()/anchor.getPrefWidth();
        pointlayoutYRatio = point.getLayoutY()/anchor.getPrefHeight();

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
    }

    private void maintainProportion() {
        image.setPrefHeight(anchor.getHeight()*imageRatio);
        point.setPrefSize(anchor.getWidth()*pointRatio,anchor.getWidth()*pointRatio);
        point.setLayoutX(anchor.getWidth()* pointlayoutXRatio);
        point.setLayoutY(anchor.getHeight()* pointlayoutYRatio);

    }

    public Pane getPoint() {
        return point;
    }

    public Pane getImage() {
        return image;
    }
}



