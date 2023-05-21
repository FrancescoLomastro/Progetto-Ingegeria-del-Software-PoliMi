package it.polimi.ingsw.View.Gui.guiControllers;

import it.polimi.ingsw.View.Gui.guiControllers.BoardComponents.Common_C;
import it.polimi.ingsw.View.Gui.guiControllers.BoardComponents.Griglia_C;
import it.polimi.ingsw.View.Gui.guiControllers.BoardComponents.Libreria_C;
import it.polimi.ingsw.View.Gui.guiControllers.BoardComponents.Personal_C;
import it.polimi.ingsw.model.Cards.ObjectCard;
import it.polimi.ingsw.model.Enums.Color;
import it.polimi.ingsw.model.Utility.Position;
import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.util.Duration;


import java.io.IOException;
import java.net.URL;
import java.util.*;

public class Board_C implements Initializable {

    public Label moveLabel;
    public Button don_button;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        don_button.setVisible(false);

        moveLabel.setVisible(false);
        moveLabel.setStyle("-fx-text-fill: white;" +
                "-fx-font-size: 20px;" +
                "-fx-effect: dropshadow(gaussian, black, 1, 1, 0, 0);" +
                "-fx-text-alignment: center center");

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


        int numGoals = 3;
        setupGoals(numGoals);
    }


    private void setupGoals(int numGoals) {
        loader = new FXMLLoader(getClass().getResource("/fxml/BoardComponents/CommonGoal.fxml"));
        try {
            son = loader.load();
            Common_C controller = loader.getController();
            controller.setListeners(right,numGoals+0.5);
            right.getChildren().add(son);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        loader = new FXMLLoader(getClass().getResource("/fxml/BoardComponents/CommonGoal.fxml"));

        try {
            son = loader.load();
            Common_C controller = loader.getController();
            controller.setListeners(right,numGoals+0.5);
            right.getChildren().add(son);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        loader = new FXMLLoader(getClass().getResource("/fxml/BoardComponents/PersonalGoal.fxml"));
        try {
            son = loader.load();
            Personal_C controller = loader.getController();
            controller.setListeners(right,numGoals+0.5);
            right.getChildren().add(son);
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
                libraries.put(players[i],controller);
                left.getChildren().add(son);
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

                centralGrid.setRowIndex(pane, i);
                centralGrid.setColumnIndex(pane, j);
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

        don_button.setVisible(true);
        moveLabel.setVisible(true);
        moveLabel.getStyle();

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

        for (Node node : centralGrid.getChildren()) {

            node.setOnMouseClicked(event -> {

                node.setStyle("-fx-border-color: RED;" +
                        "-fx-border-width: 1.5;");

                Integer rowIndex = GridPane.getRowIndex(node);
                Integer columnIndex = GridPane.getColumnIndex(node);
                Position position = new Position(rowIndex,columnIndex);
                positions.add(position);
            });

        }

        don_button.setOnAction(ActionEvent -> {
            if ( positions.size()>=1 && positions.size()<=3 ){
                System.out.println("RETURN POSITIONS");

                Position[] arrayPos = new Position[positions.size()];
                int pos_index = 0;

                for(Position pos : positions) {
                    arrayPos[pos_index] = pos;
                    pos_index+=1;
                }

                ViewFactory.getInstance().setPositions(arrayPos);
                ViewFactory.getInstance().showColumnQuestion();

                don_button.setVisible(false);
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

}