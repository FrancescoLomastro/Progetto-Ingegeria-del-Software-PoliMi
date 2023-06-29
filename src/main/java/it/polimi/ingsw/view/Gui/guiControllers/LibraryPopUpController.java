package it.polimi.ingsw.view.Gui.guiControllers;

import it.polimi.ingsw.model.Cards.ObjectCard;
import it.polimi.ingsw.model.Player.Player;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * This class is the controller associated to the "libraryPopUp.fxml" file.
 *
 * @author Andrea Ferrini
 */
public class LibraryPopUpController implements Initializable {

    private ObjectCard[][] library;
    @FXML
    public Label username_label;
    @FXML
    public GridPane library_gridPane;
    private String username;
    private Player player;

    /**
     * This method is used to initialize the controller of the "libraryPopUp.fxml" GUI.
     *
     * @param url the url used to resolve relative paths for the root object, or null if the location is not known.
     * @param resourceBundle the resources used to localize the root object, or null if the root object was not localized.
     * @author Andrea Ferrini
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        username_label.setText(username);
        initLibrary();
        updateLibrary(library);
    }

    /**
     * This method initialize the library.
     *
     * @author Andrea Ferrini
     */
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


    /**
     * This method update the library.
     *
     * @param library : current library
     * @author Andrea Ferrini
     */
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

    /**
     * This method get the library.
     *
     * @author Andrea Ferrini
     */
    public ObjectCard[][] getLibrary() {
        return library;
    }

    /**
     * This method set the library.
     *
     * @param library : library matrix;
     * @author Andrea Ferrini
     */
    public void setLibrary(ObjectCard[][] library) {
        this.library = library;
    }

    /**
     * This method get the username.
     *
     * @author Andrea Ferrini
     */
    public String getUsername() {
        return username;
    }

    /**
     * This method set the username.
     *
     * @param username : username to be displayed;
     * @author Andrea Ferrini
     */
    public void setUsername(String username) {
        this.username = username;
    }
}
