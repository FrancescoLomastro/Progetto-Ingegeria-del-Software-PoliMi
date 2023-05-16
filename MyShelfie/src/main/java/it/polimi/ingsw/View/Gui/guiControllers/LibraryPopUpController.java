package it.polimi.ingsw.View.Gui.guiControllers;

import it.polimi.ingsw.model.Cards.ObjectCard;
import it.polimi.ingsw.model.Player.Player;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class LibraryPopUpController implements Initializable {
    @FXML
    AnchorPane anchorPane;
    @FXML
    GridPane gridPane;

    private ObjectCard[][] library;
    public Label username_label;
    public GridPane library_gridPane;
    private String username;
    private Player player;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        username_label.setText(username);
    }

    public void initLibrary(){

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

    public void updateLibrary(ObjectCard[][] library){
        for (Node node : gridPane.getChildren()) {
            Integer rowIndex = GridPane.getRowIndex(node);
            Integer columnIndex = GridPane.getColumnIndex(node);

            if (library[rowIndex][columnIndex] != null) {
                node.getStyleClass().remove("invisibleCells");
                node.getStyleClass().add("texture_"+library[rowIndex][columnIndex].getColor().getRelativeInt()+"_"+library[rowIndex][columnIndex].getType().getRelativeInt());
            }
        }

    }

    public ObjectCard[][] getLibrary() {
        return library;
    }

    public void setLibrary(ObjectCard[][] library) {
        this.library = library;
    }
}
