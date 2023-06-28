package it.polimi.ingsw.view.Gui.guiControllers.BoardComponents;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

/**
 * This class governs the GUI of each player library so that it maintain its initial proportion in the case the main board
 * stage is resized.
 *
 * @author Francesco Gregorio Lo Mastro
 */
public class Library_C {
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
    @FXML
    Label nameLabel;
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
    int i=0;

    /**
     * This method is used to set up correct ratios to the different components of the common goal card.
     * A listener is set to height and width property to make component responsive.
     *
     * @param father: vbox containing the components;
     * @param parts: number of parts;
     * @author Francesco Gregorio Lo Mastro
     */
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

    /**
     * This method is used to scale components dimension.
     *
     * @param width: the component width;
     * @param height: the component height;
     * @author Francesco Gregorio Lo Mastro
     */
    private void scaleDimension(double width,double height) {

        double min = Math.min(width,height/fatherParts);
        anchor.setPrefSize(min,min);
        anchor.setMaxSize(min,min);
        grid.setPrefSize(min*gridRatioW,min*gridRatioH);
        grid.setLayoutX(min*layoutXRatio);
        grid.setLayoutY(min*layoutYRatio);
        grid.setHgap(min*hGapRatio);
    }

    /**
     * This method is used to make each component keep its proportion in the case the stage is resized.
     *
     * @author Francesco Gregorio Lo Mastro
     */
    private void maintainProportion() {
        bottom.setPrefHeight(anchor.getPrefHeight()*bottomRatio);
        nameLabel.setFont(Font.font("Monotype Corsiva",anchor.getPrefHeight()*bottomRatio));
        top.setPrefHeight(anchor.getPrefHeight()*topRatio);
        left.setPrefWidth(anchor.getPrefWidth()*leftRatio);
        right.setPrefWidth(anchor.getPrefWidth()*rightRatio);
    }

    /**
     * This method is used to get the grid.
     *
     * @author Francesco Gregorio Lo Mastro
     */
    public GridPane getGrid()
    {
        return grid;
    }

    /**
     * This method is used to set the name of the library owner.
     *
     * @param text: name of the library owner;
     * @author Francesco Gregorio Lo Mastro
     */
    public void setText(String text)
    {
        nameLabel.setText(text);
    }

    /**
     * This method is used to get the anchor pane.
     *
     * @author Francesco Gregorio Lo Mastro
     */
    public AnchorPane getAnchor() {
        return anchor;
    }
}
