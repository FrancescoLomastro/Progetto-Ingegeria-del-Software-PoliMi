package it.polimi.ingsw.View.Gui.guiControllers;

import it.polimi.ingsw.model.Cards.ObjectCard;
import it.polimi.ingsw.model.Utility.Position;
import javafx.animation.PathTransition;
import javafx.animation.ScaleTransition;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class NuovaBoardController implements Initializable {
    public BorderPane external_board;
    public BorderPane internal_borderPane;
    public GridPane grid4;
    public GridPane grid3;
    public GridPane grid2;
    public GridPane grid1;
    public AnchorPane generalAnchor;
    private double ratioHorizontal;
    private double ratioVertical;
    public AnchorPane centerAnchorPane;
    public GridPane gridpaneCenter;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ratioHorizontal = (1 - (880 - AnchorPane.getLeftAnchor(gridpaneCenter) - AnchorPane.getRightAnchor(gridpaneCenter))
            /880) / 2;
        ratioVertical = (1 - (667 - AnchorPane.getTopAnchor(gridpaneCenter) - AnchorPane.getBottomAnchor(gridpaneCenter))
                /667) / 2;
        System.out.println(ratioHorizontal);
        System.out.println(ratioVertical);
        gridpaneCenter.widthProperty().addListener(((observableValue, number, t1) -> {
            double shift = centerAnchorPane.getWidth()* ratioHorizontal;
            AnchorPane.setLeftAnchor(gridpaneCenter, shift);
            AnchorPane.setRightAnchor(gridpaneCenter, shift);
        }));

        gridpaneCenter.widthProperty().addListener(((observableValue, number, t1) -> {
            double shift = centerAnchorPane.getHeight()* ratioVertical;
            AnchorPane.setTopAnchor(gridpaneCenter, shift);
            AnchorPane.setBottomAnchor(gridpaneCenter, shift);
        }));
        initializeGrid();
    }

    private void initializeGrid() {
        for (int i=0;i<gridpaneCenter.getColumnCount();i++)
        {
            for (int j=0;j<gridpaneCenter.getRowCount();j++)
            {
                Pane btn = new Pane();
                btn.prefWidthProperty().bind(gridpaneCenter.getColumnConstraints().get(i).prefWidthProperty());
                btn.prefHeightProperty().bind(gridpaneCenter.getRowConstraints().get(j).prefHeightProperty());
                btn.getStyleClass().add("invisibleCells");

                gridpaneCenter.setRowIndex(btn, i);
                gridpaneCenter.setColumnIndex(btn, j);
                gridpaneCenter.getChildren().add(btn);
            }
        }
    }

    public void updateGrid(ObjectCard[][] grid) {
        for (Node node : gridpaneCenter.getChildren()) {
            Integer rowIndex = GridPane.getRowIndex(node);
            Integer columnIndex = GridPane.getColumnIndex(node);

            if (grid[rowIndex][columnIndex] != null) {
                node.getStyleClass().remove("invisibleCells");
                node.getStyleClass().add("texture_"+grid[rowIndex][columnIndex].getColor().getRelativeInt()+"_"+grid[rowIndex][columnIndex].getType().getRelativeInt());
            }
        }
    }


    public void runAMove(ObjectCard[][] library, String username, Position[] oldInGrid, Position[] newInLibrary) {
        /*SO CHE è UNA VERSIONE STUPIDA E SI PUò CREARE TUTTO ASSIEME IN UNA BOTTA
         * Prendere la libreria del giocatore corrispondente tramite il nome. Deve essere assolutamente necessaria questa cosa
         * Per portar avanti il codice uso l'unica libreria che ho inserito per l'esempio*/
        System.out.println(oldInGrid[0].getRow() + " " + oldInGrid[0].getColumn());
        System.out.println(newInLibrary[0].getRow() + " " + newInLibrary[0].getColumn());
        Point2D tmp;
        Point2D[][] point2D_Start_End = new Point2D[oldInGrid.length][2];
        for(int i=0; i<oldInGrid.length; i++){
            //NODO DI PARTENZA
            Node node = gridpaneCenter.getChildren().get(oldInGrid[i].getRow()*gridpaneCenter.getColumnCount()+oldInGrid[i].getColumn());
            tmp = node.localToParent(0,0);
            tmp = new Point2D(tmp.getX() + ((Pane)node).getWidth()/2 + gridpaneCenter.localToScene(0,0).getX(), tmp.getY() + ((Pane)node).getHeight()/2+ gridpaneCenter.localToScene(0,0).getY() );
            point2D_Start_End[i][0] = tmp;
            //NODO DI ARRIVO
            Node node2 = grid1.getChildren().get(newInLibrary[i].getRow()*grid1.getColumnCount()+newInLibrary[i].getColumn());
            Bounds cellBounds2 = node2.localToScene(node2.getBoundsInLocal());
            Point2D cellPosition2 = new Point2D(cellBounds2.getMinX(), cellBounds2.getMinY());
            Scene scene2 = node2.getScene();
            tmp = scene2.getRoot().sceneToLocal(cellPosition2);
            tmp = new Point2D(tmp.getX() + ((Pane)node2).getWidth()/2, tmp.getY() + ((Pane)node2).getHeight()/2 );
            point2D_Start_End[i][1] = tmp;
            //STAMPA NODI
            System.out.println(point2D_Start_End[i][0].getX() + " " + point2D_Start_End[i][0].getY() );
            System.out.println(point2D_Start_End[i][1].getX() + " " + point2D_Start_End[i][1].getY() );
        }
        //Creazione delle linee dai punti estrapolati prima
        Line[] lines = new Line[oldInGrid.length];
        for(int i=0; i<oldInGrid.length; i++){
            lines[i] = new Line(point2D_Start_End[i][0].getX(),point2D_Start_End[i][0].getY(), point2D_Start_End[i][1].getX(), point2D_Start_End[i][1].getY() );
            lines[i].setStroke(Color.RED);
            generalAnchor.getChildren().add(lines[i]);
        }
        //Prelevare le immagini da "trasportare"
        Pane[] panes = new Pane[oldInGrid.length];
        for(int i=0; i<oldInGrid.length; i++){
            panes[i] = new Pane();
            panes[i].getStyleClass().add(gridpaneCenter.getChildren().get(oldInGrid[i].getRow()*gridpaneCenter.getColumnCount()+oldInGrid[i].getColumn()).getStyleClass().get(0));
            panes[i].setPrefHeight(((Pane)gridpaneCenter.getChildren().get(oldInGrid[i].getRow()*gridpaneCenter.getColumnCount()+oldInGrid[i].getColumn())).getHeight() );
            panes[i].setPrefWidth(((Pane)gridpaneCenter.getChildren().get(oldInGrid[i].getRow()*gridpaneCenter.getColumnCount()+oldInGrid[i].getColumn())).getWidth() );
            generalAnchor.getChildren().add(panes[i]);
        }
        //ridimensionamento delle immagine con una transizione
        ScaleTransition[] scaleTransitions=new ScaleTransition[oldInGrid.length];
        double h = ((Pane)grid1.getChildren().get(0)).getHeight();
        double w = ((Pane)grid1.getChildren().get(0)).getWidth();
        for(int i=0; i<oldInGrid.length; i++){
            scaleTransitions[i] = new ScaleTransition(Duration.seconds(1), panes[i]);
            scaleTransitions[i].setToX( w/ ((Pane)gridpaneCenter.getChildren().get(oldInGrid[i].getRow()*gridpaneCenter.getColumnCount()+oldInGrid[i].getColumn())).getWidth() );
            scaleTransitions[i].setToY( h/ ((Pane)gridpaneCenter.getChildren().get(oldInGrid[i].getRow()*gridpaneCenter.getColumnCount()+oldInGrid[i].getColumn())).getHeight());
            scaleTransitions[i].setAutoReverse(false);
            scaleTransitions[i].setCycleCount(1);
        }
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
                generalAnchor.getChildren().remove(panes[index]);
            });
            pathTransitions[i].setNode(panes[i]);
        }
        //Aggiunto tutto quello che ho appena creato ed elimino le immagini dalla griglia
        for(int i=0; i<oldInGrid.length; i++) {
            pathTransitions[i].play();
            scaleTransitions[i].play();
            gridpaneCenter.getChildren().get(oldInGrid[i].getRow()*gridpaneCenter.getColumnCount()+oldInGrid[i].getColumn()).getStyleClass().remove(
                    gridpaneCenter.getChildren().get(oldInGrid[i].getRow()*gridpaneCenter.getColumnCount()+oldInGrid[i].getColumn()).getStyleClass().toString());
            gridpaneCenter.getChildren().get(oldInGrid[i].getRow()*gridpaneCenter.getColumnCount()+oldInGrid[i].getColumn()).getStyleClass().add("invisibleCells");
            Node node2 = gridpaneCenter.getChildren().get(newInLibrary[i].getRow()*gridpaneCenter.getColumnCount()+newInLibrary[i].getColumn());
            node2.getStyleClass().add(panes[i].getStyle());
        }
    }

}


/*
        verticalBoxCenter.widthProperty().addListener((obs, oldHeight, newHeight)->{
            if(horizontalBoxCenter.getHeight() > newHeight.doubleValue()){
                if(newHeight.doubleValue()>600.0) {
                    verticalBoxCenter.minWidth(horizontalBoxCenter.getHeight());
                    verticalBoxCenter.setPrefWidth(horizontalBoxCenter.getHeight());
                    verticalBoxCenter.setMaxWidth(horizontalBoxCenter.getHeight());
                    verticalBoxCenter.setMaxHeight(horizontalBoxCenter.getHeight());
                    verticalBoxCenter.setPrefHeight(horizontalBoxCenter.getHeight());
                    verticalBoxCenter.setMinHeight(horizontalBoxCenter.getHeight());
                }
            }
        });
        horizontalBoxCenter.heightProperty().addListener((obs, oldWidth, newWitdh)->{
            if(verticalBoxCenter.getWidth() > newWitdh.doubleValue()){
                if(newWitdh.doubleValue()>600.0) {
                    horizontalBoxCenter.setMaxHeight(verticalBoxCenter.getWidth());
                    horizontalBoxCenter.setPrefHeight(verticalBoxCenter.getWidth());
                    horizontalBoxCenter.setMinHeight(verticalBoxCenter.getWidth());
                }
            }
        });

 */




/*
* GridPane gridOnLeft = new GridPane();
        gridOnLeft.setAlignment(Pos.CENTER);
        gridOnLeft.setHgap(15);
        borderPane.setLeft(gridOnLeft);
        gridOnLeft.minHeight(600);
        gridOnLeft.minWidth(200);
        AnchorPane anchorPane_onLeft_Cells_0 = new AnchorPane();
        AnchorPane anchorPane_onLeft_Cells_1 = new AnchorPane();
        AnchorPane anchorPane_onLeft_Cells_2 = new AnchorPane();
        AnchorPane anchorPane_onLeft_Cells_3 = new AnchorPane();
        anchorPane_onLeft_Cells_3.heightProperty().addListener((obs, oldWidth, newWidth)->{
            anchorPane_onLeft_Cells_3.setPrefHeight(anchorPane_onLeft_Cells_3.getWidth());
        });
        anchorPane_onLeft_Cells_3.widthProperty().addListener((obs, oldWidth, newWidth)->{
            anchorPane_onLeft_Cells_3.setPrefWidth(anchorPane_onLeft_Cells_3.getHeight());
        });
        gridOnLeft.addColumn(0,  anchorPane_onLeft_Cells_0);
        gridOnLeft.addColumn(1,  anchorPane_onLeft_Cells_1);
        gridOnLeft.addColumn(2,  anchorPane_onLeft_Cells_2);
        gridOnLeft.addColumn(3,  anchorPane_onLeft_Cells_3);*/


/*
* AnchorPane general = new AnchorPane();
        general.setPrefHeight(500);
        general.setPrefWidth(1000);
        BorderPane borderPane = new BorderPane();
        GridPane gridOnLeft = new GridPane();
        gridOnLeft.setAlignment(Pos.CENTER);
        gridOnLeft.setHgap(15);
        //borderPane.setLeft(gridOnLeft);
        borderPane.setCenter(gridOnLeft);
        gridOnLeft.minHeight(600);
        gridOnLeft.minWidth(200);
        AnchorPane anchorPane_onLeft_Cells_0 = new AnchorPane();
        anchorPane_onLeft_Cells_0.setStyle("-fx-background-color: Red");
        AnchorPane anchorPane_onLeft_Cells_1 = new AnchorPane();
        anchorPane_onLeft_Cells_1.setStyle("-fx-background-color: blue");
        AnchorPane anchorPane_onLeft_Cells_2 = new AnchorPane();
        anchorPane_onLeft_Cells_2.setStyle("-fx-background-color: green");
        AnchorPane anchorPane_onLeft_Cells_3 = new AnchorPane();
        anchorPane_onLeft_Cells_3.setStyle("-fx-background-color: yellow");
        anchorPane_onLeft_Cells_3.heightProperty().addListener((obs, oldWidth, newWidth)->{
            anchorPane_onLeft_Cells_3.setPrefHeight(anchorPane_onLeft_Cells_3.getWidth());
        });
        anchorPane_onLeft_Cells_3.widthProperty().addListener((obs, oldWidth, newWidth)->{
            anchorPane_onLeft_Cells_3.setPrefWidth(anchorPane_onLeft_Cells_3.getHeight());
        });
        gridOnLeft.getChildren().add(anchorPane_onLeft_Cells_0);
        gridOnLeft.getChildren().add(anchorPane_onLeft_Cells_1);
        gridOnLeft.getChildren().add(anchorPane_onLeft_Cells_2);
        gridOnLeft.getChildren().add(anchorPane_onLeft_Cells_3);
        gridOnLeft.getChildren().get(0).minWidth(100);
        gridOnLeft.getChildren().get(1).minWidth(100);
        gridOnLeft.getChildren().get(2).minWidth(100);
        gridOnLeft.getChildren().get(3).minWidth(100);
        gridOnLeft.getChildren().get(0).minHeight(100);
        gridOnLeft.getChildren().get(1).minHeight(100);
        gridOnLeft.getChildren().get(2).minHeight(100);
        gridOnLeft.getChildren().get(3).minHeight(100);

        general.getChildren().add(borderPane);
        Scene scene = new Scene(general);
        stage.setScene(scene);
        stage.show();*/

/*
ChangeListener<Number> sizeChangeListener = new ChangeListener<Number>() {
            double previousWidth = center_external_anchorPane.getWidth();
            double previousHeight = center_external_anchorPane.getHeight();

            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                double newWidth = center_external_anchorPane.getWidth();
                double newHeight = center_external_anchorPane.getHeight();

                if (newWidth != previousWidth && newHeight != previousHeight) {
                    if (newWidth < newHeight) {
                        center_external_anchorPane.setPrefWidth(newWidth);
                    } else {
                        center_external_anchorPane.setPrefHeight(newHeight);
                    }
                }

                previousWidth = newWidth;
                previousHeight = newHeight;
            }
        };
        center_external_anchorPane.widthProperty().addListener(sizeChangeListener);
        center_external_anchorPane.heightProperty().addListener(sizeChangeListener);

 */