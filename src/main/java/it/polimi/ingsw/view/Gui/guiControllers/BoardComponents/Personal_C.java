package it.polimi.ingsw.view.Gui.guiControllers.BoardComponents;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class Personal_C {
    @FXML
    AnchorPane anchor;
    @FXML
    Pane image;
     double fatherParts;
    double imageRatio;

    public void setListeners(VBox father, double parts)
    {
        fatherParts = parts;
        imageRatio = image.getPrefWidth()/anchor.getPrefWidth();

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
        image.setPrefWidth(anchor.getWidth()*imageRatio);
    }

    public Pane getImage() {
        return image;
    }
}



