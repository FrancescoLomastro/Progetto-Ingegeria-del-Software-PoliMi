package it.polimi.ingsw.view.Gui.guiControllers.BoardComponents;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

/**
 * This class governs the GUI of the points displayer so that it maintain its initial proportion in the case the main board
 * stage is resized.
 *
 * @author Francesco Gregorio Lo Mastro
 */
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

    /**
     * This method is used to set up correct ratios to the different components of the common goal card.
     * A listener is set to height and width property to make component responsive.
     *
     * @param father: vbox containing the components;
     * @param relatedLibrary: the specific library considered;
     * @author Francesco Gregorio Lo Mastro
     */
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

    /**
     * This method is used to scale components dimension.
     *
     * @param width: the component width;
     * @author Francesco Gregorio Lo Mastro
     */
    private void scaleDimension(double width) {
        anchor.setPrefHeight(relatedLibrary.getPrefHeight());
        anchor.setPrefWidth(width);
        anchor.setMaxHeight(relatedLibrary.getPrefHeight());
        anchor.setMaxWidth(width);
        borderPane.setPrefSize(width,width);
        label.setFont(Font.font(anchor.getPrefWidth()*fontRatio));
    }

    /**
     * This method is used to get the points label.
     *
     * @author Francesco Gregorio Lo Mastro
     */
    public Label getPointsLabel()
    {
        return label;
    }

    /**
     * This method is used to get the border pane containing points.
     *
     * @author Francesco Gregorio Lo Mastro
     */
    public BorderPane getPointsPane()
    {
        return borderPane;
    }
}
