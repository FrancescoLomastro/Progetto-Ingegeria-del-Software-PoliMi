package it.polimi.ingsw.View.Gui.guiControllers;

import it.polimi.ingsw.Network.Client.ClientModel;
import it.polimi.ingsw.Network.Messages.Message;
import it.polimi.ingsw.Network.Messages.MessageGrid;
import it.polimi.ingsw.Network.ObserverImplementation.Observer;
import it.polimi.ingsw.View.Gui.GuiApplication;
import it.polimi.ingsw.View.OBSMessages.OBS_Message;
import it.polimi.ingsw.View.OBSMessages.OBS_MessageType;
import it.polimi.ingsw.View.OBSMessages.OBS_OnlyTypeMessage;
import it.polimi.ingsw.View.View;
import it.polimi.ingsw.model.Cards.ObjectCard;
import it.polimi.ingsw.model.Utility.Position;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Modality;

import java.io.IOException;

/**
 * ViewFactory class contains methods related to the creation of stages during our program lifecycle.
 * The main responsibilities is to interact with specific "guiControllers" to direct interactions among users and stages.d
 *
 * @author Alberto Aniballi
 */
public class ViewFactory extends View implements Observer<ClientModel, Message> {
    private static ViewFactory instance = null;
    private Initializable currentController;
    private Stage primaryStage;
    private Scene primaryScene;
    private Position[] positions;
    private final int MIN_HEIGHT = 600;
    private final int MIN_WIDTH = 900 ;

    /**
     * Returns or creates the only instance of the ViewFactory
     * @return
     */
    public static ViewFactory getInstance() {
        if (instance==null) {
            instance = new ViewFactory();
        }
        return instance;
    }

    public ViewFactory() {
        clientModel=new ClientModel();
        clientModel.addObserver(this);
    }


    /**
     * Starts the main FX thread
     */
    @Override
    public void startView() {
        Application.launch(GuiApplication.class);
    }

    private Scene loadScene_old(FXMLLoader loader) {
        Scene scene = null;
        try
        {
            scene = new Scene(loader.load());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return scene;
    }
    private void switchScene_old(FXMLLoader loader) {
        Scene scene = loadScene_old(loader);
        primaryStage.setScene(scene);
    }
    private void createStage_old(FXMLLoader loader, int minHeight, int minWidth, boolean lockStage) {
        Stage newStage = new Stage();
        Scene scene = loadScene_old(loader);
        newStage.setScene(scene);
        newStage.setResizable(false);
        newStage.setMinHeight(minHeight);
        newStage.setMinWidth(minWidth);

        if (lockStage) {
            newStage.initOwner(primaryStage);
            newStage.initModality(Modality.APPLICATION_MODAL);
            newStage.showAndWait();
        } else {
            newStage.show();
        }
    }
    public void setPrimaryStage(Stage primaryStage)
    {
        this.primaryStage = primaryStage;
    }
    public void closeStage(Stage stage) {
        stage.close();
    }

    private void createMainStage(FXMLLoader loader)
    {
        primaryStage.setMinHeight(MIN_HEIGHT);
        primaryStage.setMinWidth(MIN_WIDTH);
        primaryStage.setMinHeight(MIN_HEIGHT);
        primaryStage.setMinWidth(MIN_WIDTH);
        try {
            primaryScene = new Scene(loader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        primaryStage.setScene(primaryScene);
    }
    private void changeRoot(FXMLLoader loader)
    {
        try {
            primaryStage.getScene().setRoot(loader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Shows the start scene
     */
    public void showStart() {
        Platform.runLater(()->{
          FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Start.fxml"));
          createMainStage(loader);
          primaryStage.getScene().setOnKeyPressed(keyEvent -> notifyAllOBS(new OBS_OnlyTypeMessage(OBS_MessageType.START)));
          primaryStage.show();
        });
    }


    /**
     * Shows the form that collects the initial info of the user
     */
    @Override
    public void askInitialInfo() {
        Platform.runLater(()->{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ClientLogin.fxml"));
            changeRoot(loader);
        });
    }


    /**
     * Shows the form that collects the number of player to build the lobby
     * @param min
     * @param max
     */
    @Override
    public void askNumberOfPlayers(int min, int max) {
        Platform.runLater(()->{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PlayerNumberRequest.fxml"));
            switchScene_old(loader);
        });
    }


    /**
     * Scene/Message to show when the game starts
     */
    @Override
    public void startGame() {
       //Commentato per il momento perchè altrimenti non mostra la griglia
       /* Platform.runLater(()->{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AcceptedLogin.fxml"));
            switchScene(loader);
        });*/
    }


    /**
     * Shows the board scene
     */
    @Override
    public void onServerChanged() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Board.fxml"));
        loader.setControllerFactory(controllerClass -> {
            Board_C controller = new Board_C();
            currentController=controller;
            return controller;
        });
        Platform.runLater(() -> {
            switchScene_old(loader);
        });
    }


    /**
     * Shows the form that collect the new username that the user will use
     */
    @Override
    public void onInvalidUsername() {
        Platform.runLater(() -> {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/InvalidUsername.fxml"));
            switchScene_old(loader);
            primaryStage.setFullScreen(true);
        });
    }


    @Override
    public void chatMessage(String username, String text) {
    }


    public void setPositions(Position[] positions) {
        this.positions = positions;
    }

    public Position[] getPositions() {
        return positions;
    }

    public void showColumnQuestion() {
        Platform.runLater(()->
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ColumnInsertionQuestion.fxml"));
            createStage_old(loader,250,350,true);
        });
    }

    @Override
    public void askMove() {
        Platform.runLater(() ->
        {
            Board_C boardSceneController = (Board_C) currentController;
            boardSceneController.onAskMove();
        });
    }

    @Override
    public void showGrid(ObjectCard[][] grid, MessageGrid.TypeOfGridMessage typeOfGridMessage) {
        /*if (typeOfGridMessage != MessageGrid.TypeOfGridMessage.UPDATE_AFTER_MOVE) {
//            Platform.runLater(() ->
//            {
//                NuovaBoardController boardSceneController = (NuovaBoardController) currentController;
//                boardSceneController.updateGrid(grid);
//            });
        }
        else
        {*/
            Platform.runLater(() ->
            {
                Board_C boardSceneController = (Board_C) currentController;
                boardSceneController.updateGrid(grid);
            });
        //}
    }

    @Override
    public void showLibrary(ObjectCard[][] library, String username, Position[] oldGrid, Position[] newLibrary) {
        if(oldGrid!=null && newLibrary!=null) {
           Platform.runLater(() -> {
               Board_C boardSceneController = (Board_C) currentController;
               boardSceneController.runAMove(library, username, oldGrid, newLibrary);
            });
        }
        Platform.runLater(() ->
        {
            Board_C boardSceneController = (Board_C) currentController;
            boardSceneController.updateLibrary(library,username);
        });
    }

    @Override
    public void printAll() {

    }

    @Override
    public void startChat() {

    }

    @Override
    public void printPoints() {

    }



    @Override
    public void run() {
        //per ora lascialo stare
    }




    @Override
    public void printMessage(String string) {
        Platform.runLater(()->{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AcceptedLogin.fxml"));
            loader.setControllerFactory(controllerClass -> {
                AcceptedLoginController controller = new AcceptedLoginController();


                int indexSplitter = 33;
                String currNum_players = string.substring(0,indexSplitter);
                String member_names = string.substring(indexSplitter);
                controller.setCurrent_numPlayers(currNum_players);
                controller.setMember_players(member_names);


                return controller;
            });
            switchScene_old(loader);
            primaryStage.setFullScreen(true);
        });
    }

    @Override
    public void errorCreatingClient(String chosenAddress, int chosenPort) {
        Platform.runLater(() -> {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(("/fxml/ErrorClientCreation.fxml")));
            loader.setControllerFactory(controllerClass -> {
                ErrorClientCreationController controller = new ErrorClientCreationController();
                controller.setChosenAddress(chosenAddress);
                controller.setChosenPort(chosenPort);
                return controller;
            });
            switchScene_old(loader);
            primaryStage.setFullScreen(true);
        });
    }
    public void showInvalidPort() {
        Platform.runLater(()->
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/InvalidPort.fxml"));
            createStage_old(loader,200,320,true);
        });
    }

    public void showInvalidNumPlayers() {
        Platform.runLater(()->{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/InvalidNumPlayers.fxml"));
            createStage_old(loader,200,320,true);
        });

    }

    public void showInvalidNumberOfCards() {
        Platform.runLater(()->{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/InvalidNumObjCards.fxml"));
            createStage_old(loader,200,320,true);
        });
    }

    public void notifyAllOBS(OBS_Message msg) {
        setChanged();
        notifyObservers(msg);
    }

}