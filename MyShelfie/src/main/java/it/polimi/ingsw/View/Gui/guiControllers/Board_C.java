package it.polimi.ingsw.View.Gui.guiControllers;

import it.polimi.ingsw.Network.Client.ClientModel;
import it.polimi.ingsw.View.Gui.guiControllers.BoardComponents.Common_C;
import it.polimi.ingsw.View.Gui.guiControllers.BoardComponents.Griglia_C;
import it.polimi.ingsw.View.Gui.guiControllers.BoardComponents.Libreria_C;
import it.polimi.ingsw.View.Gui.guiControllers.BoardComponents.Personal_C;
import it.polimi.ingsw.model.Cards.ObjectCard;
import it.polimi.ingsw.model.Enums.Color;
import it.polimi.ingsw.model.Utility.Position;
import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;


import java.io.IOException;
import java.net.URL;
import java.util.*;

public class Board_C implements Initializable {

    @FXML
    public Label moveLabel;
    @FXML
    public Button doneButton;
    @FXML
    AnchorPane anchor;
    @FXML
    HBox bottom;
    @FXML
    HBox top;
    @FXML
    VBox left;
    @FXML
    VBox right;
    @FXML
    BorderPane center;
    @FXML
    BorderPane internalBorderPane;
    double bottomRatio;
    double topRatio;
    double leftRatio;
    double rightRatio;
    FXMLLoader loader;
    AnchorPane son;

    GridPane centralGrid;
    Map<String,Libreria_C> libraries;

    @FXML
    private void handleLibrariesClick(String username) {

        ViewFactory.getInstance().onLibraryClick(username);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        doneButton.setVisible(false);

        moveLabel.setVisible(false);


        bottomRatio = bottom.getPrefHeight()/anchor.getPrefHeight();
        topRatio = top.getPrefHeight()/anchor.getPrefHeight();
        leftRatio = left.getPrefWidth()/anchor.getPrefWidth();
        rightRatio = right.getPrefWidth()/anchor.getPrefWidth();

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

        initializeGrid();

        initializeLibraries();



        setupGoals();
    }

    @FXML
    private void handleCommonGoalCardClick(String description, int num){

        ViewFactory.getInstance().onCommonGoalCardClick(description, num);
    }

    @FXML
    private void handlePersonalGoalCardClick(){

        ViewFactory.getInstance().onPersonalGoalCardClick();
    }

    private void setupGoals() {
        ClientModel clientModel = ViewFactory.getInstance().getClientModel();
        loader = new FXMLLoader(getClass().getResource("/fxml/BoardComponents/CommonGoal.fxml"));
        try {
            son = loader.load();
            Common_C controller = loader.getController();
            controller.setListeners(right,3.5);
            controller.setListeners(right,3+0.5);
            controller.getImage().getStyleClass().add("common"+clientModel.getNumCom1());
            controller.getPoint().getStyleClass().add("point8");
            right.getChildren().add(son);

            son.setOnMouseClicked(mouseEvent -> {

                handleCommonGoalCardClick(ViewFactory.getInstance().getClientModel().getDescriptionFirstCommonGoal(), 1);
            });

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        loader = new FXMLLoader(getClass().getResource("/fxml/BoardComponents/CommonGoal.fxml"));

        try {
            son = loader.load();
            Common_C controller = loader.getController();
            controller.setListeners(right,3.5);
            controller.setListeners(right,3+0.5);
            controller.getImage().getStyleClass().add("common"+clientModel.getNumCom2());
            controller.getPoint().getStyleClass().add("point8");
            right.getChildren().add(son);

            son.setOnMouseClicked(mouseEvent -> {

                handleCommonGoalCardClick(ViewFactory.getInstance().getClientModel().getDescriptionSecondCommonGoal(), 2);
            });

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        loader = new FXMLLoader(getClass().getResource("/fxml/BoardComponents/PersonalGoal.fxml"));
        try {
            son = loader.load();
            Personal_C controller = loader.getController();
            controller.setListeners(right,3.5);
            controller.getImage().getStyleClass().add("personal"+clientModel.getPersonalGoalCardNum());
            controller.setListeners(right,3+0.5);
            right.getChildren().add(son);

            son.setOnMouseClicked(mouseEvent -> {

                handlePersonalGoalCardClick();
            });

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void initializeLibraries() {
        libraries= new HashMap<>();
        String[] players= ViewFactory.getInstance().getClientModel().getPlayerNames();
        for (int i=0;i<players.length;i++)
        {
            loader = new FXMLLoader(getClass().getResource("/fxml/BoardComponents/Libreria.fxml"));
            Libreria_C controller=null;
            try
            {
                son = loader.load();
                controller= loader.getController();
                controller.setListeners(left,players.length+0.5);
                controller.setText(players[i]);
                libraries.put(players[i],controller);
                left.getChildren().add(son);

                Integer index = i;

                son.setOnMouseClicked(mouseEvent -> {

                    handleLibrariesClick(players[index]);
                });
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            GridPane libraryGrid = controller.getGrid();
            for (int r=0;r<libraryGrid.getRowCount();r++)
            {
                for (int c=0;c<libraryGrid.getColumnCount();c++)
                {
                    Pane pane = new Pane();
                    pane.prefWidthProperty().bind(libraryGrid.getColumnConstraints().get(c).prefWidthProperty());
                    pane.prefHeightProperty().bind(libraryGrid.getRowConstraints().get(r).prefHeightProperty());//è giusto così, height non funziona
                    pane.getStyleClass().add("invisible");

                    libraryGrid.setRowIndex(pane, r);
                    libraryGrid.setColumnIndex(pane, c);
                    libraryGrid.getChildren().add(pane);
                }
            }
        }

    }

    private void maintainProportion() {
        left.setPrefWidth(anchor.getWidth()*leftRatio);
        right.setPrefWidth(anchor.getWidth()*rightRatio);
        top.setPrefHeight(anchor.getHeight()*topRatio);
        bottom.setPrefHeight(anchor.getHeight()*bottomRatio);
    }
     private void initializeGrid() {
         loader = new FXMLLoader(getClass().getResource("/fxml/BoardComponents/Griglia.fxml"));
         try {
             son = loader.load();
             Griglia_C controller = loader.getController();
             center.setCenter(son);
             controller.setListeners(center);
             centralGrid=controller.getGrid();
         } catch (IOException e) {
             throw new RuntimeException(e);
         }

        for (int i=0;i<centralGrid.getColumnCount();i++)
        {
            for (int j=0;j<centralGrid.getRowCount();j++)
            {
                Pane pane = new Pane();
                pane.prefWidthProperty().bind(centralGrid.getColumnConstraints().get(i).prefWidthProperty());
                pane.prefHeightProperty().bind(centralGrid.getColumnConstraints().get(j).prefWidthProperty());//è giusto così, height non funziona
                pane.getStyleClass().add("invisibleCells");

                GridPane.setRowIndex(pane, i);
                GridPane.setColumnIndex(pane, j);
                centralGrid.getChildren().add(pane);
            }
        }
    }




    public void updateGrid(ObjectCard[][] grid) {
        for (Node node : centralGrid.getChildren()) {
            Integer rowIndex = GridPane.getRowIndex(node);
            Integer columnIndex = GridPane.getColumnIndex(node);

            if (grid[rowIndex][columnIndex] != null && grid[rowIndex][columnIndex].getColor()!= Color.EMPTY) {
                node.getStyleClass().remove("invisibleCells");
                node.getStyleClass().add("texture_"+grid[rowIndex][columnIndex].getColor().getRelativeInt()+
                        "_"+grid[rowIndex][columnIndex].getType().getRelativeInt());
            }
        }
    }

    public void updateLibrary(ObjectCard[][] library, String username) {
        GridPane libraryPane= libraries.get(username).getGrid();
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


    public void onAskMove() {
        doneButton.setVisible(true);
        moveLabel.setVisible(true);

        PauseTransition pauseTransition = new PauseTransition(Duration.millis(500));

        SequentialTransition sequentialTransition = new SequentialTransition(pauseTransition);
        sequentialTransition.setCycleCount(sequentialTransition.INDEFINITE);
        sequentialTransition.setAutoReverse(true);
        sequentialTransition.play();

        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(2), moveLabel);
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.0);
        fadeTransition.setCycleCount(sequentialTransition.INDEFINITE);
        fadeTransition.setAutoReverse(true);
        fadeTransition.play();

        ArrayList<Position> positions = new ArrayList<>();
        for(Node node : centralGrid.getChildren()){
            Integer rowIndex = GridPane.getRowIndex(node);
            Integer columnIndex = GridPane.getColumnIndex(node);
            if (((node.getStyle().contains("-fx-border-color: RED")))){
                Position position = new Position(rowIndex,columnIndex);
                positions.add(position);
            }
        }
        for (Node node : centralGrid.getChildren()) {

            node.setOnMouseClicked(event -> {

                Integer rowIndex = GridPane.getRowIndex(node);
                Integer columnIndex = GridPane.getColumnIndex(node);

                if (((node.getStyle().contains("-fx-border-color: RED")))) {

                    node.setStyle("-fx-border-color: BLACK;" +
                            "-fx-border-width: 0;");

                    int positionToEliminate = 0;
                    for (int index = 0; index < positions.size(); index++) {
                        if (positions.get(index).getRow()==rowIndex &&
                                positions.get(index).getColumn()==columnIndex) {

                            positionToEliminate = index;

                        }
                    }
                    positions.remove(positionToEliminate);

                } else {

                    node.setStyle("-fx-border-color: RED;" +
                            "-fx-border-width: 1.5;");

                    Position position = new Position(rowIndex,columnIndex);
                    positions.add(position);

                }
            });

        }

        doneButton.setOnAction(ActionEvent -> {
            if ( positions.size()>=1 && positions.size()<=3 ){

                Position[] arrayPos = new Position[positions.size()];
                int pos_index = 0;

                for(Position pos : positions) {
                    arrayPos[pos_index] = pos;
                    pos_index+=1;
                }

                ViewFactory.getInstance().setPositions(arrayPos);
                ViewFactory.getInstance().showColumnQuestion();

                doneButton.setVisible(false);
                moveLabel.setVisible(false);

            } else {
                ViewFactory.getInstance().showInvalidNumberOfCards();

                for (Node node : centralGrid.getChildren()) {

                    node.setStyle("-fx-border-color: BLACK;" +
                            "-fx-border-width: 0;");

                    positions.clear();

                }

            }
        });

    }

    public void runAMove(ObjectCard[][] library, String name, Position[] oldInGrid, Position[] newInLibrary) {
        GridPane playerLibrary = libraries.get(name).getGrid();
        Point2D tmp;
        Point2D[][] point2D_Start_End = new Point2D[oldInGrid.length][2];
        Node[] panesGrid = new Pane[oldInGrid.length];
        for(int i=0; i<oldInGrid.length; i++){
            panesGrid[i]=centralGrid.getChildren().get(oldInGrid[i].getRow()*centralGrid.getColumnCount()+oldInGrid[i].getColumn());
        }
        for(int i=0; i<oldInGrid.length; i++){
            Node node = panesGrid[i];
            tmp = node.localToParent(0,0);
            tmp = new Point2D(tmp.getX() + ((Pane)node).getWidth()/2 + centralGrid.localToScene(0,0).getX(), tmp.getY() + ((Pane)node).getHeight()/2+ centralGrid.localToScene(0,0).getY() );
            point2D_Start_End[i][0] = tmp;
            Node node2 = playerLibrary.getChildren().get(newInLibrary[i].getRow()*playerLibrary.getColumnCount()+newInLibrary[i].getColumn());
            Bounds cellBounds2 = node2.localToScene(node2.getBoundsInLocal());
            Point2D cellPosition2 = new Point2D(cellBounds2.getMinX(), cellBounds2.getMinY());
            Scene scene2 = node2.getScene();
            tmp = scene2.getRoot().sceneToLocal(cellPosition2);
            tmp = new Point2D(tmp.getX() + ((Pane)node2).getWidth()/2, tmp.getY() + ((Pane)node2).getHeight()/2 );
            point2D_Start_End[i][1] = tmp;
        }
        Line[] lines = new Line[oldInGrid.length];
        for(int i=0; i<oldInGrid.length; i++){
            lines[i] = new Line(point2D_Start_End[i][0].getX(),point2D_Start_End[i][0].getY(), point2D_Start_End[i][1].getX(), point2D_Start_End[i][1].getY() );
        }
        Rectangle[] panes = new Rectangle[oldInGrid.length];
        for(int i=0; i<oldInGrid.length; i++){
            double dim=((Pane)panesGrid[i]).getHeight();
            panes[i] = new Rectangle(dim,dim,dim,dim);
            panes[i].getStyleClass().addAll((panesGrid[i]).getStyleClass());
            panes[i].toFront();
            anchor.getChildren().add(panes[i]);
        }
        ScaleTransition[] scaleTransitions=new ScaleTransition[oldInGrid.length];
        double h = ((Pane)playerLibrary.getChildren().get(0)).getHeight();
        double w = ((Pane)playerLibrary.getChildren().get(0)).getWidth();
        for(int i=0; i<oldInGrid.length; i++){
            scaleTransitions[i] = new ScaleTransition(Duration.seconds(1), panes[i]);
            scaleTransitions[i].setToX( w/ ((Pane)panesGrid[i]).getWidth() );
            scaleTransitions[i].setToY( h/ ((Pane)panesGrid[i]).getHeight());
            scaleTransitions[i].setAutoReverse(false);
            scaleTransitions[i].setCycleCount(1);
        }
        PathTransition[] pathTransitions = new PathTransition[oldInGrid.length];
        for(int i=0; i<oldInGrid.length; i++){
            pathTransitions[i] = new PathTransition();
            pathTransitions[i].setPath(lines[i]);
            pathTransitions[i].setDuration(Duration.seconds(1));
            pathTransitions[i].setAutoReverse(false);
            pathTransitions[i].setCycleCount(1);
            final int index=i;
            pathTransitions[i].setOnFinished(actionEvent -> {
                anchor.getChildren().remove(panes[index]);
                updateLibrary(library, name);
            });
            pathTransitions[i].setNode(panes[i]);
        }
        for(int i=0; i<oldInGrid.length; i++) {
            pathTransitions[i].play();
            scaleTransitions[i].play();
            panesGrid[i].getStyleClass().removeAll(panesGrid[i].getStyleClass());
            panesGrid[i].getStyleClass().add("invisibleCells");
        }
    }

    public void resetBorderInGrid() {
        for (Node node : centralGrid.getChildren()) {
            node.setStyle("-fx-border-color: BLACK;" +
                    "-fx-border-width: 0;");
        }
    }
}
