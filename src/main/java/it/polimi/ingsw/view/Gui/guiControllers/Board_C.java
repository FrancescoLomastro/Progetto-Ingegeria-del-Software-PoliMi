package it.polimi.ingsw.view.Gui.guiControllers;

import it.polimi.ingsw.network.Client.ClientModel;
import it.polimi.ingsw.network.Messages.AlmostOverMessage;
import it.polimi.ingsw.network.Messages.MessageCommonGoal;
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

    @FXML
    private void handleLibrariesClick(String username) {

        ViewFactory.getInstance().onLibraryClick(username);
    }

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
            controller.getPoint().getStyleClass().add("point"+clientModel.getPointsCommonGoalCards()[0]);
            this.cardPoint1=controller.getPoint();
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
            controller.getPoint().getStyleClass().add("point"+clientModel.getPointsCommonGoalCards()[0]);
            this.cardPoint2=controller.getPoint();
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

    private void maintainProportion() {
        left.setPrefWidth(anchor.getWidth()*leftRatio);
        right.setPrefWidth(anchor.getWidth()*rightRatio);
        top.setPrefHeight(anchor.getHeight()*topRatio);
        bottom.setPrefHeight(anchor.getHeight()*bottomRatio);
        left_grid.setPrefWidth(left.getPrefWidth()*left_gridRatio);
        left_points.setPrefWidth(left.getPrefWidth()*left_pointsRatio);
    }
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

    public void resetBorderInGrid() {
        for (Node node : centralGrid.getChildren()) {
            node.setStyle("-fx-border-color: BLACK;" +
                    "-fx-border-width: 0;");
        }
    }

    public void openChat() {
        if(animationChatButton!=null)
            animationChatButton.stop();
        openChatButton.setStyle("-fx-border-color: transparent;");
        ViewFactory.getInstance().showChat();
    }
    public void showCentralPoints(int centralPoints) {
        centralPointCard.getStyleClass().add("centralPointCard_"+centralPoints);
    }

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

    public void initCommonGoalPoints(int card1, int card2){
        cardPoint1.getStyleClass().removeAll();
        cardPoint2.getStyleClass().removeAll();
        cardPoint1.getStyleClass().add("point"+card1);
        cardPoint2.getStyleClass().add("point"+card2);
    }

    public void updatePoints(MessageCommonGoal arg) {
        Map<String,Integer> map= ViewFactory.getInstance().getClientModel().getPointsMap();
        points.get(arg.getPlayer()).getPointsLabel().setText(""+map.get(arg.getPlayer()));

        if(arg.getGainedPointsSecondCard()==1 || arg.getGainedPointsSecondCard()==3){
            cardPoint1.getStyleClass().removeAll("point8", "point6", "point4","point2", "point0");
            cardPoint1.getStyleClass().add("point"+arg.getPointAvailable1());
        }
        if(arg.getGainedPointsSecondCard()==2 || arg.getGainedPointsSecondCard()==3){
            cardPoint2.getStyleClass().removeAll("point8", "point6", "point4","point2", "point0");
            cardPoint2.getStyleClass().add("point"+arg.getPointAvailable2());
        }
    }

    public void setPlayersPoints(ArrayList<Couple<String, Integer>> playersPoints) {
        for (Couple<String, Integer> playersPoint : playersPoints) {
            points.get(playersPoint.getFirst()).getPointsLabel().setText("" + playersPoint.getSecond());
        }
    }

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

    public void notifyMessage() {
        if(animationChatButton==null)
            setupAnimationChatButton();
        animationChatButton.play();
    }
}
