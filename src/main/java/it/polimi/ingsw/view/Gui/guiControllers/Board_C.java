package it.polimi.ingsw.view.Gui.guiControllers;

import it.polimi.ingsw.network.Client.ClientModel;
import it.polimi.ingsw.network.Messages.AlmostOverMessage;
import it.polimi.ingsw.network.Messages.CommonGoalMessage;
import it.polimi.ingsw.view.Gui.guiControllers.BoardComponents.*;
import it.polimi.ingsw.model.Cards.ObjectCard;
import it.polimi.ingsw.enums.Color;
import it.polimi.ingsw.utility.Couple;
import it.polimi.ingsw.utility.Position;
import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import java.io.IOException;
import java.net.URL;
import java.util.*;

/**
 * This class governs the GUI of the game board where libraries, object cards grid and different types of goal cards are
 * present. It is used as an intermediary between the fixed parts of the GUI of the 'Board.fxml' file and
 * the dynamic information that the controller sends to the graphic components of the following fxml files:
 * "CommonGoal.fxml", "Griglia.fxml", "Libreria.fxml", "PersonalGoal.fxml" and "Punto.fxml".
 * In addition, the class manages the interaction between players and the various graphic components of the scene.
 *
 * @author Alberto Aniballi, Riccardo Figini, Francesco Gregorio Lo Mastro, Andrea Ferrini
 */
public class Board_C implements Initializable {
    @FXML
    public Label moveLabel;
    @FXML
    public Button doneButton;
    public Button openChatButton;
    public VBox vbox_buttons;
    @FXML
    AnchorPane anchor;
    @FXML
    HBox bottom;
    @FXML
    HBox top;
    @FXML
    HBox left;
    @FXML
    VBox right;
    @FXML
    BorderPane center;
    @FXML
    BorderPane internalBorderPane;
    @FXML
    VBox left_grid;
    @FXML
    VBox left_points;
    double bottomRatio;
    double topRatio;
    double leftRatio;
    double rightRatio;
    FXMLLoader loader;
    AnchorPane son;
    GridPane centralGrid;
    Pane centralPointCard;
    Map<String,Libreria_C> libraries;
    Map<String,Punto_C> points;
    private Pane cardPoint1;
    private Pane cardPoint2;
     double left_pointsRatio;
     double left_gridRatio;
     private Timeline animationChatButton;

    /**
     * This method is used to manage players' clicks on libraries.
     *
     * @param username: the name of the player owning the library;
     * @author Andrea Ferrini
     */
    @FXML
    private void handleLibrariesClick(String username) {
        ViewFactory.getInstance().onLibraryClick(username);
    }

    /**
     * This method is used to initialize the controller of the "Board.fxml" GUI. In particular, it sets the graphic
     * components in the correct locations and starts the grid, libraries and personal goal cards initialization.
     *
     * @param url the url used to resolve relative paths for the root object, or null if the location is not known.
     * @param resourceBundle the resources used to localize the root object, or null if the root object was not localized.
     * @author Francesco Gregorio Lo Mastro
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        vbox_buttons.setAlignment(Pos.CENTER);
        vbox_buttons.setSpacing(15);

        doneButton.setVisible(false);
        openChatButton.setOnMouseClicked(actionEvent -> openChat());


        moveLabel.setVisible(false);


        bottomRatio = bottom.getPrefHeight()/anchor.getPrefHeight();
        topRatio = top.getPrefHeight()/anchor.getPrefHeight();
        leftRatio = left.getPrefWidth()/anchor.getPrefWidth();
        rightRatio = right.getPrefWidth()/anchor.getPrefWidth();
        left_gridRatio= left_grid.getPrefWidth()/left.getPrefWidth();
        left_pointsRatio= left_points.getPrefWidth()/left.getPrefWidth();

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

    /**
     * This method is used to manage players' clicks on common goal cards currently present in the board.
     *
     * @param description: common goal card description;
     * @param num: common goal card number;
     * @author Andrea Ferrini
     */
    @FXML
    private void handleCommonGoalCardClick(String description, int num){
        ViewFactory.getInstance().onCommonGoalCardClick(description, num);
    }

    /**
     * This method is used to manage players' clicks on their personal goal card.
     *
     * @author Andrea Ferrini
     */
    @FXML
    private void handlePersonalGoalCardClick(){

        ViewFactory.getInstance().onPersonalGoalCardClick();
    }

    /**
     * This method is used to set up the correct common goal cards and personal goal cards that were assigned during
     * initialization of the game. Listeners and event handlers are set for the goal card components.
     *
     * @author Francesco Gregorio Lo Mastro, Riccardo Figini
     */
    private void setupGoals() {
        ClientModel clientModel = ViewFactory.getInstance().getClientModel();
        loader = new FXMLLoader(getClass().getResource("/fxml/BoardComponents/CommonGoal.fxml"));

        try {
            son = loader.load();
            Common_C controller = loader.getController();
            controller.setListeners(right,3.5);
            controller.setListeners(right,3+0.5);
            controller.getImage().getStyleClass().add("common"+clientModel.getFirstCommonGoalId());
            controller.getPoint().getStyleClass().add("point"+clientModel.getCommonGoalPointsVector()[0]);
            this.cardPoint1=controller.getPoint();
            right.getChildren().add(son);

            son.setOnMouseClicked(mouseEvent -> {
                handleCommonGoalCardClick(ViewFactory.getInstance().getClientModel().getFirstCommonGoalDescription(), 1);
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
            controller.getImage().getStyleClass().add("common"+clientModel.getSecondCommonGoalId());
            controller.getPoint().getStyleClass().add("point"+clientModel.getCommonGoalPointsVector()[0]);
            this.cardPoint2=controller.getPoint();
            right.getChildren().add(son);

            son.setOnMouseClicked(mouseEvent -> {
                handleCommonGoalCardClick(ViewFactory.getInstance().getClientModel().getSecondCommonGoalDescription(), 2);
            });

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        loader = new FXMLLoader(getClass().getResource("/fxml/BoardComponents/PersonalGoal.fxml"));
        try {
            son = loader.load();
            Personal_C controller = loader.getController();
            controller.setListeners(right,3.5);
            controller.getImage().getStyleClass().add("personal"+clientModel.getPersonalGoalId());
            controller.setListeners(right,3+0.5);
            right.getChildren().add(son);

            son.setOnMouseClicked(mouseEvent -> {

                handlePersonalGoalCardClick();
            });

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * This method is used to initialize each player library on the main board.
     * Listeners and event handlers are set for library components.
     *
     * @author Francesco Gregorio Lo Mastro
     */
    private void initializeLibraries() {
        libraries= new HashMap<>();
        points= new HashMap<>();
        String[] players= ViewFactory.getInstance().getClientModel().getPlayerNames();
        Libreria_C controllerL=null;
        Punto_C controllerP=null;
        for (int i=0;i<players.length;i++)
        {
            loader = new FXMLLoader(getClass().getResource("/fxml/BoardComponents/Libreria.fxml"));
            try
            {
                son = loader.load();
                controllerL= loader.getController();
                controllerL.setListeners(left_grid,players.length+0.5);
                controllerL.setText(players[i]);
                libraries.put(players[i],controllerL);
                left_grid.getChildren().add(son);

                int index = i;
                son.setOnMouseClicked(mouseEvent -> {

                    handleLibrariesClick(players[index]);
                });
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            GridPane libraryGrid = controllerL.getGrid();
            for (int r=0;r<libraryGrid.getRowCount();r++)
            {
                for (int c=0;c<libraryGrid.getColumnCount();c++)
                {
                    Pane pane = new Pane();
                    pane.prefWidthProperty().bind(libraryGrid.getColumnConstraints().get(c).prefWidthProperty());
                    pane.prefHeightProperty().bind(libraryGrid.getRowConstraints().get(r).prefHeightProperty());//è giusto così, height non funziona
                    pane.getStyleClass().add("invisible");

                    GridPane.setRowIndex(pane, r);
                    GridPane.setColumnIndex(pane, c);
                    libraryGrid.getChildren().add(pane);
                }
            }
           loader = new FXMLLoader(getClass().getResource("/fxml/BoardComponents/Punto.fxml"));
            try
            {
                son = loader.load();
                controllerP= loader.getController();
                controllerP.setListeners(left_points,controllerL.getAnchor());
                points.put(players[i],controllerP);
                controllerP.getPointsLabel().setText("0");
                left_points.getChildren().add(son);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    /**
     * This method is used to make each board component maintain its proportion in case the stage is resized.
     *
     * @author Francesco Gregorio Lo Mastro
     */
    private void maintainProportion() {
        left.setPrefWidth(anchor.getWidth()*leftRatio);
        right.setPrefWidth(anchor.getWidth()*rightRatio);
        top.setPrefHeight(anchor.getHeight()*topRatio);
        bottom.setPrefHeight(anchor.getHeight()*bottomRatio);
        left_grid.setPrefWidth(left.getPrefWidth()*left_gridRatio);
        left_points.setPrefWidth(left.getPrefWidth()*left_pointsRatio);
    }

    /**
     * This method is used to initialize object cards grid on the main board.
     *
     * @author Francesco Gregorio Lo Mastro
     */
     private void initializeGrid() {
         loader = new FXMLLoader(getClass().getResource("/fxml/BoardComponents/Griglia.fxml"));
         try {
             son = loader.load();
             Griglia_C controller = loader.getController();
             center.setCenter(son);
             controller.setListeners(center);
             centralGrid=controller.getGrid();
             centralPointCard= controller.getCentralPointCard();
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

    /**
     * This method is used to update object cards grid on the main board, its main responsibilities are to set
     * object card to cell of the grid and to update the grid once a player choose object cards from the grid.
     *
     * @param grid: the current grid;
     * @author Francesco Gregorio Lo Mastro
     */
    public void updateGrid(ObjectCard[][] grid) {
        for (Node node : centralGrid.getChildren()) {
            Integer rowIndex = GridPane.getRowIndex(node);
            Integer columnIndex = GridPane.getColumnIndex(node);

            if (grid[rowIndex][columnIndex] != null && grid[rowIndex][columnIndex].getColor()!= Color.EMPTY) {
                while(node.getStyleClass().remove("invisibleCells"));
                node.getStyleClass().add("texture_"+grid[rowIndex][columnIndex].getColor().getRelativeInt()+
                        "_"+grid[rowIndex][columnIndex].getType().getRelativeInt());
            }
        }
    }

    /**
     * This method is used to update player libraries of the main board, its main responsibilities is to set
     * to update the library once a player choose object cards from the grid.
     *
     * @param library: the current library of the player;
     * @param username: the name of the library owner;
     * @author Francesco Gregorio Lo Mastro
     */
    public void updateLibrary(ObjectCard[][] library, String username) {
        GridPane libraryPane= libraries.get(username).getGrid();
        for (Node node : libraryPane.getChildren()) {
            Integer rowIndex = GridPane.getRowIndex(node);
            Integer columnIndex = GridPane.getColumnIndex(node);

            if (library[rowIndex][columnIndex] != null && library[rowIndex][columnIndex].getColor()!= Color.EMPTY) {
                while(node.getStyleClass().remove("invisibleCells"));
                node.getStyleClass().add("texture_"+library[rowIndex][columnIndex].getColor().getRelativeInt()+
                        "_"+library[rowIndex][columnIndex].getType().getRelativeInt());
            }
        }
    }

    /**
     * This method is used to activate the possibility of a player to choose object cards from the grid during its turn.
     * Once a player has chosen object cards two cases are possible: the case in which an invalid number of object cards
     * is chosen or the case in which the player is directed to the "ColumnInsertionQuestion" stage.
     *
     * @author Alberto Aniballi, Riccardo Figini
     */
    public void onAskMove() {
        doneButton.setVisible(true);
        doneButton.requestFocus();
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
                        if (positions.get(index).getRow() == rowIndex &&
                                positions.get(index).getColumn() == columnIndex) {

                            positionToEliminate = index;

                        }
                    }
                    positions.remove(positionToEliminate);

                } else {
                    if(!node.getStyleClass().get(0).equals("invisibleCells")) {
                        node.setStyle("-fx-border-color: RED;" +
                                "-fx-border-width: 1.5;");

                        Position position = new Position(rowIndex, columnIndex);
                        positions.add(position);
                    }
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

                for (Node node: centralGrid.getChildren()) {
                    node.setOnMouseClicked(null);
                }

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

    /**
     * This method is used in the case the player make a valid object cards choice from the grid.
     * In particular, the method manages the transition of chosen object cards from the grid to the player library.
     *
     * @param library: the player library;
     * @param name: player name;
     * @param oldInGrid: the positions of the cards chosen from the grid;
     * @param newInLibrary: the positions of the cards inserted in the library;
     * @author Riccardo Figini
     */
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
        double dim=((Pane)panesGrid[0]).getHeight();
        String s;
        for(int i=0; i<oldInGrid.length; i++){
            Image image = new javafx.scene.image.Image(getClass().getResourceAsStream("/images/originals/objectCards/"+(panesGrid[i]).getStyleClass().get(0).substring(8, 11)+".png"));
            panes[i] = new Rectangle(dim,dim,dim,dim);
            panes[i].getStyleClass().addAll((panesGrid[i]).getStyleClass());
            panes[i].toFront();
            panes[i].setFill(new ImagePattern(image));
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

    /**
     * This method is used to reset object cards border to black in the grid.
     *
     * @author Riccardo Figini, Alberto Aniballi
     */
    public void resetBorderInGrid() {
        for (Node node : centralGrid.getChildren()) {
            node.setStyle("-fx-border-color: BLACK;" +
                    "-fx-border-width: 0;");
        }
    }

    /**
     * This method is used to manage players' clicks on the "open chat" button present in the board.
     * It makes the chat appear.
     *
     * @author Alberto Aniballi, Riccardo Figini
     */
    public void openChat() {
        if(animationChatButton!=null)
            animationChatButton.stop();
        openChatButton.setStyle("-fx-border-color: transparent;");
        ViewFactory.getInstance().showChat();
    }

    /**
     * This method is used to show central points in the "centralPointCard".
     *
     * @param centralPoints: the number of points;
     * @author Francesco Gregorio Lo Mastro
     */
    public void showCentralPoints(int centralPoints) {
        centralPointCard.getStyleClass().add("centralPointCard_"+centralPoints);
    }

    /**
     * This method is used to enter the phase in which the game is almost over.
     *
     * @param arg: the almost over message;
     * @author Francesco Gregorio Lo Mastro
     */
    public void almostOver(AlmostOverMessage arg) {
        int updatedPoints= ViewFactory.getInstance().getClientModel().getPointsMap().get(arg.getFillerName());
        centralPointCard.getStyleClass().removeAll(centralPointCard.getStyleClass());
        centralPointCard.getStyleClass().add("point_invisible");
        BorderPane fillerPane = points.get(arg.getFillerName()).getPointsPane();
        Label fillerLabel = points.get(arg.getFillerName()).getPointsLabel();
        fillerLabel.setText(""+updatedPoints);
        fillerPane.getStyleClass().removeAll(fillerPane.getStyleClass());
        fillerPane.getStyleClass().add("points_filler");
        fillerLabel.setStyle("-fx-text-fill: black;");
    }

    /**
     * This method is used to set common goal cards points.
     *
     * @param card1: first common goal card points;
     * @param card2: second common goal card points;
     * @author Riccardo Figini
     */
    public void initCommonGoalPoints(int card1, int card2){
        cardPoint1.getStyleClass().removeAll();
        cardPoint2.getStyleClass().removeAll();
        cardPoint1.getStyleClass().add("point"+card1);
        cardPoint2.getStyleClass().add("point"+card2);
    }

    /**
     * This method is used to update common goal cards points in the case a player reaches common goal objective.
     *
     * @param arg: common goal message;
     * @author Riccardo Figini, Francesco Gregorio Lo Mastro
     */
    public void updatePoints(CommonGoalMessage arg) {
        Map<String,Integer> map= ViewFactory.getInstance().getClientModel().getPointsMap();
        points.get(arg.getPlayerWhoScored()).getPointsLabel().setText(""+map.get(arg.getPlayerWhoScored()));

        if(arg.getGainedPointsSecondCard()==1 || arg.getGainedPointsSecondCard()==3){
            cardPoint1.getStyleClass().removeAll("point8", "point6", "point4","point2", "point0");
            cardPoint1.getStyleClass().add("point"+arg.getRemainingPointsFirstCard());
        }
        if(arg.getGainedPointsSecondCard()==2 || arg.getGainedPointsSecondCard()==3){
            cardPoint2.getStyleClass().removeAll("point8", "point6", "point4","point2", "point0");
            cardPoint2.getStyleClass().add("point"+arg.getRemainingPointsSecondCard());
        }
    }

    /**
     * This method is used to set each player points.
     *
     * @param playersPoints: the array containing the mapping of each player to its points;
     * @author Riccardo Figini
     */
    public void setPlayersPoints(ArrayList<Couple<String, Integer>> playersPoints) {
        for (Couple<String, Integer> playersPoint : playersPoints) {
            points.get(playersPoint.getFirst()).getPointsLabel().setText("" + playersPoint.getSecond());
        }
    }

    /**
     * This method is used to set up the chat button animation.
     *
     * @author Riccardo Figini
     */
    public void setupAnimationChatButton(){
        openChatButton.setStyle("-fx-border-color: transparent;-fx-border-width: 10;");
        Duration duration = Duration.seconds(2);
        animationChatButton = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(openChatButton.styleProperty(), "-fx-border-color: CRIMSON;")),
                new KeyFrame(duration.divide(2), new KeyValue(openChatButton.styleProperty(), "-fx-border-color: TRANSPARENT;")),
                new KeyFrame(duration, new KeyValue(openChatButton.styleProperty(), "-fx-border-color: CRIMSON;"))
        );
        animationChatButton.setCycleCount(Animation.INDEFINITE);
    }

    /**
     * This method is used to activate the chat button animation.
     *
     * @author Riccardo Figini
     */
    public void notifyMessage() {
        if(animationChatButton==null)
            setupAnimationChatButton();
        animationChatButton.play();
    }
}
