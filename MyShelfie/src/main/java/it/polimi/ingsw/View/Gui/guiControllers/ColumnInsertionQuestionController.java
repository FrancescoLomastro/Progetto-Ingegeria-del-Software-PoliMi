package it.polimi.ingsw.View.Gui.guiControllers;

import it.polimi.ingsw.View.Gui.guiControllers.BoardComponents.Libreria_C;
import it.polimi.ingsw.View.OBSMessages.OBS_MoveMessage;
import it.polimi.ingsw.View.OBSMessages.OBS_NumberOfPlayerMessage;
import it.polimi.ingsw.model.Cards.ObjectCard;
import it.polimi.ingsw.model.Enums.Color;
import it.polimi.ingsw.model.Utility.Position;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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

public class ColumnInsertionQuestionController implements Initializable {
    public AnchorPane external_player_container;

    public VBox internal_vbox_container;
    public TextField input_column_question;
    private ObjectCard[][] lib;
    Stage stage;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        input_column_question.setOnKeyPressed(event -> getColumnFromPlayer(event));
        ini();
    }

    public void ini(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/BoardComponents/Libreria.fxml"));
        Libreria_C controller;
        try
        {
            Pane son = loader.load();
            controller= loader.getController();
            controller.setListeners(internal_vbox_container,2);
            internal_vbox_container.getChildren().add(son);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        GridPane libraryPane= controller.getGrid();
        for (Node node : libraryPane.getChildren()) {
            Integer rowIndex = GridPane.getRowIndex(node);
            Integer columnIndex = GridPane.getColumnIndex(node);

            if (lib[rowIndex][columnIndex] != null && lib[rowIndex][columnIndex].getColor()!= Color.EMPTY) {
                node.getStyleClass().remove("invisibleCells");
                node.getStyleClass().add("texture_"+lib[rowIndex][columnIndex].getColor().getRelativeInt()+
                        "_"+lib[rowIndex][columnIndex].getType().getRelativeInt());
            }
        }
    }

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
                        System.out.println("Move message sent!");

                        Node node = (Node) event.getSource();
                        Stage currentStage = (Stage) node.getScene().getWindow();
                        currentStage.close();
                    }
                }
            }
        }

    }

    public void setStageAndSetupListeners(Stage stage, ViewFactory viewFactory) {
        this.stage=stage;
        stage.setOnCloseRequest((event)->{
            viewFactory.askMove();
        });
    }

    public void setLibrary(ObjectCard[][] libreria_c) {
        this.lib=libreria_c;
    }
}
