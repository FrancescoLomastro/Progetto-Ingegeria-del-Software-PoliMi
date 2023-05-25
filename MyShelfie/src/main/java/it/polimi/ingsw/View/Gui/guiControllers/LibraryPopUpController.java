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

    private ObjectCard[][] library;
    @FXML
    public Label username_label;
    @FXML
    public GridPane library_gridPane;
    private String username;
    private Player player;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        username_label.setText(username);
        initLibrary();
        updateLibrary(library);
        System.out.println(username + " initialized_OK");
    }


    public void initLibrary() {
        int rowCount = library_gridPane.getRowCount();
        int columnCount = library_gridPane.getColumnCount();

        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < columnCount; j++) {
                Pane emptyPane = new Pane();
                library_gridPane.add(emptyPane, j, i);
            }
        }
    }



    public void updateLibrary(ObjectCard[][] library){
        for (Node node : library_gridPane.getChildren()) {
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
