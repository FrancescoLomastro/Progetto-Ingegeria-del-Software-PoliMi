package it.polimi.ingsw.view.Gui.guiControllers.BoardComponents;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * This class governs the GUI of personal goal cards so that it maintain its initial proportion in the case the main board
 * stage is resized.
 *
 * @author Francesco Gregorio Lo Mastro
 */
public class Personal_C {
    @FXML
    AnchorPane anchor;
    @FXML
    Pane image;
     double fatherParts;
    double imageRatio;

    /**
     * This method is used to set up correct ratios to the different components of the common goal card.
     * A listener is set to height and width property to make component responsive.
     *
     * @param father: vbox containing the components;
     * @param parts: number of parts;
     * @author Francesco Gregorio Lo Mastro
     */
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
    }

    /**
     * This method is used to make each component keep its proportion in the case the stage is resized.
     *
     * @author Francesco Gregorio Lo Mastro
     */
    private void maintainProportion() {
        image.setPrefWidth(anchor.getWidth()*imageRatio);
    }

    /**
     * This method is used to get the image.
     *
     * @author Francesco Gregorio Lo Mastro
     */
    public Pane getImage() {
        return image;
    }
}



