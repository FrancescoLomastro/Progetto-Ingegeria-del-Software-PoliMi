package it.polimi.ingsw.View.Gui.guiControllers.BoardComponents;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

import java.net.URL;
import java.util.ResourceBundle;

public class Punto_C {

    @FXML
    AnchorPane anchor;
    @FXML
    BorderPane borderPane;
    @FXML
    Label label;
    private double fatherParts;
    private double fontRatio;
    AnchorPane relatedLibrary;

    public void setListeners(VBox father, AnchorPane relatedLibrary)
    {
        this.relatedLibrary=relatedLibrary;
        fontRatio=label.getFont().getSize()/anchor.getPrefWidth();

        father.heightProperty().addListener(((observableValue, oldHeight, newHeight) ->
        {
            scaleDimension(father.getWidth());
        }
        ));
        father.widthProperty().addListener(((observableValue, oldHeight, newHeight) ->
        {
            scaleDimension(father.getWidth());
        }
        ));
    }

    private void scaleDimension(double width) {
        anchor.setPrefHeight(relatedLibrary.getPrefHeight());
        anchor.setPrefWidth(width);
        anchor.setMaxHeight(relatedLibrary.getPrefHeight());
        anchor.setMaxWidth(width);
        borderPane.setPrefSize(width,width);
        label.setFont(Font.font(anchor.getPrefWidth()*fontRatio));
    }

    public Label getPointsLabel()
    {
        return label;
    }
    public BorderPane getPointsPane()
    {
        return borderPane;
    }
}
