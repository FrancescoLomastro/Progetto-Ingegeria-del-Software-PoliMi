package it.polimi.ingsw.View.Gui.guiControllers;

import it.polimi.ingsw.model.Cards.ObjectCard;
import it.polimi.ingsw.model.Enums.Type;
import it.polimi.ingsw.model.Utility.Position;
import javafx.animation.PathTransition;
import javafx.animation.ScaleTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.util.Duration;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class BoardSceneController implements Initializable {
    public AnchorPane anchorPane_library1;
    public GridPane gridPane_library1;
    @FXML
    AnchorPane anchorPane;
    @FXML
    GridPane gridPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeGrid();
    }
    public void updateGrid(ObjectCard[][] grid) {
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


    public void runAMove(ObjectCard[][] library, String username, Position[] oldInGrid, Position[] newInLibrary) {
            /*SO CHE è UNA VERSIONE STUPIDA E SI PUò CREARE TUTTO ASSIEME IN UNA BOTTA
            * Prendere la libreria del giocatore corrispondente tramite il nome. Deve essere assolutamente necessaria questa cosa
            * Per portar avanti il codice uso l'unica libreria che ho inserito per l'esempio*/
        System.out.println(oldInGrid[0].getRow() + " " + oldInGrid[0].getColumn());
        System.out.println(newInLibrary[0].getRow() + " " + newInLibrary[0].getColumn());
        Point2D[][] point2D_Start_End = new Point2D[oldInGrid.length][2];
        for(int i=0; i<oldInGrid.length; i++){
            Node node = gridPane.getChildren().get(oldInGrid[i].getRow()*gridPane.getColumnCount()+oldInGrid[i].getColumn());
            point2D_Start_End[i][0] = node.localToScene(0,0);
            node = gridPane_library1.getChildren().get(newInLibrary[i].getRow()*gridPane_library1.getColumnCount()+newInLibrary[i].getColumn());

            Bounds cellBounds = node.localToScene(node.getBoundsInLocal());
            Point2D cellPosition = new Point2D(cellBounds.getMinX(), cellBounds.getMinY());
            Scene scene = node.getScene();
            Point2D tmp = scene.getRoot().sceneToLocal(cellPosition);

            point2D_Start_End[i][1] = tmp;
            System.out.println(point2D_Start_End[i][0].getX() + " " + point2D_Start_End[i][0].getY() );
            System.out.println(point2D_Start_End[i][1].getX() + " " + point2D_Start_End[i][1].getY() );
        }
        //Creazione delle linee dai punti estrapolati prima
        Line[] lines = new Line[oldInGrid.length];
        for(int i=0; i<oldInGrid.length; i++){
            lines[i] = new Line(point2D_Start_End[i][0].getX(),point2D_Start_End[i][0].getY(), point2D_Start_End[i][1].getX(), point2D_Start_End[i][1].getY() );
            lines[i].setStroke(Color.RED);
            anchorPane.getChildren().add(lines[i]);
        }
        //Prelevare le immagini da "trasportare"
        Pane[] panes = new Pane[oldInGrid.length];
        for(int i=0; i<oldInGrid.length; i++){
            panes[i] = new Pane();
            panes[i].getStyleClass().add(gridPane.getChildren().get(oldInGrid[i].getRow()*gridPane.getColumnCount()+oldInGrid[i].getColumn()).getStyleClass().get(0));
            panes[i].setPrefHeight(((Pane)gridPane.getChildren().get(oldInGrid[i].getRow()*gridPane.getColumnCount()+oldInGrid[i].getColumn())).getHeight() );
            panes[i].setPrefWidth(((Pane)gridPane.getChildren().get(oldInGrid[i].getRow()*gridPane.getColumnCount()+oldInGrid[i].getColumn())).getWidth() );
            anchorPane.getChildren().add(panes[i]);
        }
        //ridimensionamento delle immagine con una transizione
        /*
        ScaleTransition[] scaleTransitions=new ScaleTransition[oldInGrid.length];
        double h = ((Pane)gridPane_library1.getChildren().get(0)).getHeight();
        double w = ((Pane)gridPane_library1.getChildren().get(0)).getWidth();
        for(int i=0; i<oldInGrid.length; i++){
            scaleTransitions[i] = new ScaleTransition(Duration.seconds(1));
            scaleTransitions[i].setToX((((Pane) gridPane_library1.getChildren().get(0)).getHeight()));
            scaleTransitions[i].setToY((((Pane) gridPane_library1.getChildren().get(0)).getWidth()));
            scaleTransitions[i].setAutoReverse(false);
            scaleTransitions[i].setCycleCount(1);
            scaleTransitions[i].setNode(panes[i]);
        }

         */
        //creazione dei percorsi
        PathTransition[] pathTransitions = new PathTransition[oldInGrid.length];
        for(int i=0; i<oldInGrid.length; i++){
            pathTransitions[i] = new PathTransition();
            pathTransitions[i].setPath(lines[i]);
            pathTransitions[i].setDuration(Duration.seconds(1));
            pathTransitions[i].setAutoReverse(false);
            pathTransitions[i].setCycleCount(1);
            final int index=i;
            pathTransitions[i].setOnFinished(actionEvent -> {
                anchorPane.getChildren().remove(panes[index]);
                //TODO aggiornamento effettivo della griglia, tipo update grid
            });
            pathTransitions[i].setNode(panes[i]);
        }
        //Aggiunto tutto quello che ho appena creato ed elimino le immagini dalla griglia
        for(int i=0; i<oldInGrid.length; i++) {
            pathTransitions[i].play();
            //scaleTransitions[i].play();
            gridPane.getChildren().get(oldInGrid[i].getRow()*gridPane.getColumnCount()+oldInGrid[i].getColumn()).getStyleClass().remove(
                    gridPane.getChildren().get(oldInGrid[i].getRow()*gridPane.getColumnCount()+oldInGrid[i].getColumn()).getStyleClass());
            gridPane.getChildren().get(oldInGrid[i].getRow()*gridPane.getColumnCount()+oldInGrid[i].getColumn()).getStyleClass().add("invisibleCells");
        }
    }
}
