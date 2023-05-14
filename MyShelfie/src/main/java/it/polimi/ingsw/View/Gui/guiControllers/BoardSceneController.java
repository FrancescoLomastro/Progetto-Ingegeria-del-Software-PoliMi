package it.polimi.ingsw.View.Gui.guiControllers;

import it.polimi.ingsw.model.Cards.ObjectCard;
import it.polimi.ingsw.model.Enums.Color;
import it.polimi.ingsw.model.Enums.Type;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.net.URL;
import java.util.ResourceBundle;

public class BoardSceneController implements Initializable {
    @FXML
    AnchorPane anchorPane;
    @FXML
    ImageView wholeBackground;
    @FXML
    GridPane gridPane;
    private ObjectCard[][] grid;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeGrid();
        if(grid!=null)
        testFill();
    }
//funziona ma non useremo questa
    private void testFill() {
        for (Node node : gridPane.getChildren()) {
            Integer rowIndex = GridPane.getRowIndex(node);
            Integer columnIndex = GridPane.getColumnIndex(node);


            if (grid[rowIndex][columnIndex] != null) {
                node.getStyleClass().remove("invisibleCells");
                node.getStyleClass().add("texture_"+grid[rowIndex][columnIndex].getColor().getRelativeInt()+"_"+grid[rowIndex][columnIndex].getType().getRelativeInt());
            }
        }
    }

    private void initializeGrid() {
        for (int i=0;i<gridPane.getColumnCount();i++)
        {
            for (int j=0;j<gridPane.getRowCount();j++)
            {
                Pane btn = new Pane();
                btn.prefWidthProperty().bind(gridPane.getColumnConstraints().get(i).prefWidthProperty());
                btn.prefHeightProperty().bind(gridPane.getRowConstraints().get(j).prefHeightProperty());
                btn.getStyleClass().add("invisibleCells");

                gridPane.setRowIndex(btn, i);
                gridPane.setColumnIndex(btn, j);
                gridPane.getChildren().add(btn);
            }
        }
    }

    public void setGrid(ObjectCard[][] grid) {
        this.grid =grid;
    }
}
