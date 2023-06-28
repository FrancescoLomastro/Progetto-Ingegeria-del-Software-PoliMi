package it.polimi.ingsw.view.Gui.guiControllers.BoardComponents;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

/**
 * This class governs the GUI of obect cards grid so that it maintain its initial proportion in the case the main board
 * stage is resized.
 *
 * @author Francesco Gregorio Lo Mastro
 */
public class Grid_C {
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

    /**
     * This method is used to set up correct ratios to the different components of the common goal card.
     * A listener is set to height and width property to make component responsive.
     *
     * @param father: border pane containing the components;
     * @author Francesco Gregorio Lo Mastro
     */
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

    /**
     * This method is used to make each component keep its proportion in the case the stage is resized.
     *
     * @author Francesco Gregorio Lo Mastro
     */
    private void maintainProportion() {
        grid.setPrefSize(anchor.getWidth()*gridRatio,anchor.getHeight()*gridRatio);
        grid.setLayoutX(anchor.getWidth()*layoutXRatio);
        grid.setLayoutY(anchor.getHeight()*layoutYRatio);
        centralPointCard.setPrefSize(anchor.getWidth()*pointRatio,anchor.getWidth()*pointRatio);
        centralPointCard.setLayoutX(anchor.getWidth()* pointlayoutXRatio);
        centralPointCard.setLayoutY(anchor.getHeight()* pointlayoutYRatio);
    }

    /**
     * This method is used to scale components dimension.
     *
     * @param width: the component width;
     * @param height: the component height;
     * @author Francesco Gregorio Lo Mastro
     */
    private void scaleDimension(double width,double height) {
        double min = Math.min(width,height);
        anchor.setPrefSize(min,min);
        anchor.setMaxSize(min,min);
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
     * This method is used to get the central point card.
     *
     * @author Francesco Gregorio Lo Mastro
     */
    public Pane getCentralPointCard(){ return centralPointCard;}
}

