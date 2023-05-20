package it.polimi.ingsw.View.Gui.guiControllers;

import it.polimi.ingsw.Network.Client.ClientModel;
import it.polimi.ingsw.Network.Messages.Message;
import it.polimi.ingsw.Network.Messages.MessageGrid;
import it.polimi.ingsw.Network.ObserverImplementation.Observer;
import it.polimi.ingsw.View.Gui.GuiApplication;
import it.polimi.ingsw.View.OBSMessages.OBS_Message;
import it.polimi.ingsw.View.View;
import it.polimi.ingsw.model.Cards.ObjectCard;
import it.polimi.ingsw.model.Utility.Position;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;
import javafx.stage.Modality;

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

    private Scene loadScene(FXMLLoader loader) {
        Scene scene = null;
        try {
            scene = new Scene(loader.load());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return scene;
    }
    private void switchScene(FXMLLoader loader) {
        Scene scene = loadScene(loader);
        primaryStage.setScene(scene);
    }
    private void createStage(FXMLLoader loader,int minHeight,int minWidth,boolean lockStage) {
        Stage newStage = new Stage();
        Scene scene = loadScene(loader);
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


    /**
     * Shows the start scene
     */
    public void showStart() {
        Platform.runLater(()->{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Start.fxml"));
           primaryStage.setMaximized(true);
            primaryStage.setFullScreen(true);
            primaryStage.setFullScreenExitHint("");
            primaryStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
            switchScene(loader);
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
            switchScene(loader);
            primaryStage.setFullScreen(true);
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
            switchScene(loader);
            primaryStage.setFullScreen(true);
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
            switchScene(loader);
        });
    }


    /**
     * Shows the form that collect the new username that the user will use
     */
    @Override
    public void onInvalidUsername() {
        Platform.runLater(() -> {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/InvalidUsername.fxml"));
            switchScene(loader);
            primaryStage.setFullScreen(true);
        });
    }


    @Override
    public void chatMessage(String username, String text) {
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
//        if(oldGrid!=null && newLibrary!=null) {
//            Platform.runLater(() -> {
//                NuovaBoardController boardSceneController = (NuovaBoardController) currentController;
//                boardSceneController.runAMove(library, username, oldGrid, newLibrary);
//            });
//        }
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
            switchScene(loader);
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
            switchScene(loader);
            primaryStage.setFullScreen(true);
        });
    }
    public void showInvalidPort() {
        Platform.runLater(()->
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/InvalidPort.fxml"));
            createStage(loader,200,320,true);
        });
    }

    public void showInvalidNumPlayers() {
        Platform.runLater(()->{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/InvalidNumPlayers.fxml"));
            createStage(loader,200,320,true);
        });

    }

    public void notifyAllOBS(OBS_Message msg) {
        setChanged();
        notifyObservers(msg);
    }

}