package it.polimi.ingsw.view.Gui.guiControllers;

import it.polimi.ingsw.view.Gui.guiControllers.BoardComponents.Libreria_C;
import it.polimi.ingsw.view.OBSMessages.OBS_MoveMessage;
import it.polimi.ingsw.model.Cards.ObjectCard;
import it.polimi.ingsw.enums.Color;
import it.polimi.ingsw.utility.Position;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * This class governs the GUI of the insertion phase of the library column chosen by the player to insert the cards just chosen from the grid.
 * It is used as an intermediary between the fixed parts of the GUI of the 'ColumnInsertionQuestion.fxml' file and
 * the dynamic information that the controller sends to the graphic components of the associated fxml file.
 * In addition, the class manages the interaction between the user and the various graphic components of the scene.
 *
 * @author Alberto Aniballi
 */
public class ColumnInsertionQuestionController implements Initializable {
    public AnchorPane external_player_container;

    public VBox internal_vbox_container;
    public TextField input_column_question;
    public VBox ForLib;
    private ObjectCard[][] lib;
    Stage stage;
    private ViewFactory viewFactory;
    private Libreria_C controller;

    /**
     * This method is used to initialize the controller of the "ColumnInsertionQuestion.fxml" GUI.
     *
     * @param url the url used to resolve relative paths for the root object, or null if the location is not known.
     * @param resourceBundle the resources used to localize the root object, or null if the root object was not localized.
     * @author Riccardo Figini
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        input_column_question.setOnKeyPressed(this::getColumnFromPlayer);
        ini();
        updateLibrary(viewFactory.getClientModel().getLibrary(ViewFactory.getInstance().getClientModel().getMyName()));
    }

    /**
     *
     *
     * @author Riccardo Figini
     */
    public void ini(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/BoardComponents/Libreria.fxml"));
        Libreria_C controller;
        try
        {
            Pane son = loader.load();
            controller= loader.getController();
            controller.setListeners(ForLib, 1);
            controller.setText("Your library");
            this.controller=controller;
            ForLib.getChildren().add(son);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        GridPane libraryPane= controller.getGrid();
        for (int i=0;i<libraryPane.getRowCount();i++)
        {
            for (int j=0;j<libraryPane.getColumnCount();j++)
            {
                Pane pane = new Pane();
                pane.prefWidthProperty().bind(libraryPane.getColumnConstraints().get(0).prefWidthProperty());
                pane.prefHeightProperty().bind(libraryPane.getColumnConstraints().get(0).prefWidthProperty());
                pane.getStyleClass().add("invisibleCells");
                GridPane.setRowIndex(pane, i);
                GridPane.setColumnIndex(pane, j);
                libraryPane.getChildren().add(pane);
            }
        }
    }

    /**
     * This method is used to update the player's personal library once he has chosen the grid cards and
     * chosen the column in his library where to place the object cards.
     *
     * @param library the player's library to be updated.
     * @author Riccardo Figini
     */
    public void updateLibrary(ObjectCard[][] library) {
        GridPane libraryPane= controller.getGrid();
        for (Node node : libraryPane.getChildren()) {
            Integer rowIndex = GridPane.getRowIndex(node);
            Integer columnIndex = GridPane.getColumnIndex(node);
            if (library[rowIndex][columnIndex] != null && library[rowIndex][columnIndex].getColor()!= Color.EMPTY) {
                node.getStyleClass().remove("invisibleCells");
                node.getStyleClass().add("texture_"+library[rowIndex][columnIndex].getColor().getRelativeInt()+
                        "_"+library[rowIndex][columnIndex].getType().getRelativeInt());
            }
        }
    }

    /**
     * This method is used to retrieve, from the dedicated text field, the column that a new player chooses to insert object cards.
     * It processes the column number only after the player presses enter on the keyboard. Furthermore, this method checks if
     * a valid number has been chosen.
     *
     * @param event the event that triggers the activation of the method;
     * @author Alberto Aniballi
     */
    public void getColumnFromPlayer(KeyEvent event) {

        String numPlayers_Input;
        int parsedChosenColumn=0;
        boolean invalid_input=false;

        if (event.getCode() == KeyCode.ENTER) {
            numPlayers_Input = input_column_question.getText().trim();

            if (numPlayers_Input.length() > 0) {
                try {
                    parsedChosenColumn = Integer.parseInt(numPlayers_Input)-1;
                } catch (NumberFormatException e) {
                    invalid_input = true;
                } finally {
                    if (invalid_input) {
                        input_column_question.setText("");
                    } else
                    {
                        Position[] positions = ViewFactory.getInstance().getPositions();
                        ViewFactory.getInstance().notifyAllOBS(new OBS_MoveMessage(positions,parsedChosenColumn));
                        viewFactory.resetGrid();
                        Node node = (Node) event.getSource();
                        Stage currentStage = (Stage) node.getScene().getWindow();
                        currentStage.close();
                    }
                }
            }
        }

    }

    public void setStageAndSetupListeners(Stage stage, ViewFactory viewFactory, ObjectCard[][] lib) {
        this.stage=stage;
        this.viewFactory=viewFactory;
        this.lib=lib;
        stage.setOnCloseRequest((event)->{
            viewFactory.askMove();
        });
    }
}
