package it.polimi.ingsw.View.Gui.guiControllers;

import it.polimi.ingsw.Network.Client.ClientModel;
import it.polimi.ingsw.Network.Messages.*;
import it.polimi.ingsw.Network.ObserverImplementation.Observer;
import it.polimi.ingsw.View.Gui.GuiApplication;
import it.polimi.ingsw.View.OBSMessages.OBS_Message;
import it.polimi.ingsw.View.OBSMessages.OBS_MessageType;
import it.polimi.ingsw.View.OBSMessages.OBS_OnlyTypeMessage;
import it.polimi.ingsw.View.View;
import it.polimi.ingsw.model.Cards.ObjectCard;
import it.polimi.ingsw.model.Utility.Couple;
import it.polimi.ingsw.model.Utility.Position;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.Modality;

import java.io.IOException;
import java.util.ArrayList;

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
    private Stage chatStage;
    private ChatController controllerChat;
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
            primaryStage.getScene().setOnKeyPressed(null);
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
            changeRoot(loader);
        });
    }


    /**
     * Scene/Message to show when the game starts
     */
    @Override
    public void startGame() {
        Platform.runLater(()->{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/GameHasBegun.fxml"));
            createStage_old(loader,200,320,true);
        });
    }

    @Override
    public void almostOver(AlmostOverMessage arg) {
        Platform.runLater(() ->
        {
            Board_C boardSceneController = (Board_C) currentController;
            boardSceneController.almostOver(arg);
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
    public void lobbyUpdate(String string) {
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
            changeRoot(loader);
        });
    }

    @Override
    public void acceptedLogin() {
        //da aggiornare il Accept
    }


    @Override
    public void chatMessage(String username, String text) {
        Platform.runLater(()->{
            controllerChat.printMessage(text, username);
        });
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
            Stage newStage = new Stage();
            loader.setControllerFactory(controller ->{
                ColumnInsertionQuestionController controller1 = new ColumnInsertionQuestionController();
                controller1.setStageAndSetupListeners(newStage, this, clientModel.getLibrary(clientModel.getMyName()));
                return controller1;
            });
            Scene scene;
            try {
                scene = new Scene(loader.load());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            newStage.setScene(scene);
            newStage.setResizable(true);
            newStage.initOwner(primaryStage);
            newStage.initModality(Modality.APPLICATION_MODAL);
            newStage.showAndWait();
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
        Platform.runLater(() ->
        {
            Board_C boardSceneController = (Board_C) currentController;
            boardSceneController.updateGrid(grid);
        });
    }

    @Override
    public void showLibrary(ObjectCard[][] library, String username, Position[] oldGrid, Position[] newLibrary) {
        if(oldGrid!=null && newLibrary!=null) {
           Platform.runLater(() -> {
               Board_C boardSceneController = (Board_C) currentController;
               boardSceneController.runAMove(library, username, oldGrid, newLibrary);
            });
        }
        else {
            Platform.runLater(() ->
            {
                Board_C boardSceneController = (Board_C) currentController;
                boardSceneController.updateLibrary(library, username);
            });
        }
    }

    /**This method is necessary in CLI version because it prints everything when it's your turn. In Gui version it prints in chat
     * "is your turn"
     * @author: Riccardo Figini
     * */
    @Override
    public void printAll() {
        chatMessage("Server", "It's your turn");
    }

    @Override
    public void startChat() {
        Platform.runLater(() -> {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Chat.fxml"));
            Scene scene = loadScene_old(loader);

            chatStage = new Stage();
            chatStage.setOnCloseRequest((event -> {
                event.consume();
                chatStage.setIconified(true);
            }));
            primaryStage.setOnCloseRequest((event)->{
                chatStage.close();
            });
            chatStage.setScene(scene);
            controllerChat=loader.getController();
            chatStage.setResizable(false);
            chatStage.setMinHeight(600);
            chatStage.setMinWidth(370);
            chatStage.setMaxHeight(600);
            chatStage.setMaxWidth(370);

            chatStage.show();
            chatStage.setIconified(true);
        });

    }

    /**This method is called to print points when game is over (in cli version), here prints in chat
     * that the game is over*/
    @Override
    public void printPoints() {
        chatMessage("Server", "Game is over");
    }



    @Override
    public void run() {
        //per ora lascialo stare
    }



    /**This method print in chat message from server. Messages can be useful information about game's flow
     * @author: Riccardo Figini
     * @param string Message from server
     * */
    @Override
    public void printMessage(String string) {
        chatMessage("Server", string);
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
            changeRoot(loader);
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

    public void onLibraryClick(String username) {

        ObjectCard[][] lib = clientModel.getLibrary(username);

        Platform.runLater(() -> {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/libraryPopUp.fxml"));

            loader.setControllerFactory(controllerClass -> {

                LibraryPopUpController libraryPopUpController = new LibraryPopUpController();

                libraryPopUpController.setUsername(username);

                libraryPopUpController.setLibrary(lib);

                return libraryPopUpController;
            });

            createStage_old(loader, 200, 320, true);
        });
    }


    @Override
    public void update(ClientModel o, Message arg) {
        switch (arg.getType())
        {
            case SETUP_MESSAGE -> {
                showBoard(arg);
            }
            case UPDATE_GRID_MESSAGE -> {
                ObjectCard[][] obs = ((MessageGrid) arg).getGrid();
                showGrid(obs, ((MessageGrid) arg).getTypeOfGridMessage());
            }
            case UPDATE_LIBRARY_MESSAGE -> {
                ObjectCard[][] obs = ((MessageLibrary) arg).getLibrary();
                showLibrary(obs, ((MessageLibrary) arg).getOwnerOfLibrary(),((MessageLibrary) arg).getCardInGrid(), ((MessageLibrary) arg).getCardInLibr() );
            }
            case ALMOST_OVER -> {
                AlmostOverMessage msg = (AlmostOverMessage) arg;
                almostOver((AlmostOverMessage) arg);
            }
            case COMMON_GOAL ->
            {
                updatePoints((MessageCommonGoal) arg);
            }
        }
    }

    private void updatePoints(MessageCommonGoal arg)
    {
        Platform.runLater(() ->
        {
            Board_C boardSceneController = (Board_C) currentController;
            boardSceneController.updatePoints(arg);
            chatMessage("Server", arg.getPlayer()+" has reach common goal card");
        });
    }

    private void showBoard(Message arg) {
        createBoard();
        Platform.runLater(() -> {
            SetupMessage msg = (SetupMessage) arg;
            showGrid(msg.getGrid(),MessageGrid.TypeOfGridMessage.INIT);
            for (int i=0; i<msg.getPlayersName().length;i++)
            {
                showLibrary(msg.getPlayersLibraries()[i],msg.getPlayersName()[i],null,null);
            }
            showCentralPoints(msg.getCentralPointCard());
        });
    }

    private void createBoard() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Board.fxml"));
        loader.setControllerFactory(controllerClass -> {
            Board_C controller = new Board_C();
            currentController=controller;
            return controller;
        });
        Platform.runLater(() -> {
            changeRoot(loader);
        });
    }

    public void resetGrid() {
        Platform.runLater(() -> {
            Board_C boardSceneController = (Board_C) currentController;
            boardSceneController.resetBorderInGrid();
        });
    }

    public void showWinnerScene(ArrayList<Couple<String, Integer>> finalRanking){
        Platform.runLater(() -> {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/WinnerScene.fxml"));
            loader.setControllerFactory(controllerClass -> {
                WinnerSceneController winnerSceneController= new WinnerSceneController();
                winnerSceneController.setWinner(finalRanking.get(0).getFirst());
                winnerSceneController.setFinalRanking(finalRanking);
                return winnerSceneController;
            });
            createStage_old(loader, 200, 320, true);
        });
    }

    public void onCommonGoalCardClick(String description, int num) {
        Platform.runLater(() -> {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/CommonGoalCardDescription.fxml"));
            loader.setControllerFactory(controllerClass -> {
                CommonGoalCardDescriptionController commonGoalCardDescriptionController= new CommonGoalCardDescriptionController();
                commonGoalCardDescriptionController.setDescription(description);
                commonGoalCardDescriptionController.setNum(num);
                return commonGoalCardDescriptionController;
            });
            createStage_old(loader, 200, 320, true);
        });
    }

    public void onPersonalGoalCardClick() {
        Platform.runLater(() -> {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PersonalGoalCardPopUp.fxml"));
            loader.setControllerFactory(controllerClass -> {
                PersonalGoalCardPopUpController personalGoalCardPopUpController= new PersonalGoalCardPopUpController();
                return personalGoalCardPopUpController;
            });
            createStage_old(loader, 200, 320, true);
        });
    }
    public Stage getChatStage() {
        return chatStage;
    }

    public void showChat() {
        chatStage.setIconified(false);
    }
    public void showCentralPoints(int centralPoints) {
        Board_C boardSceneController = (Board_C) currentController;
        boardSceneController.showCentralPoints(centralPoints);
    }
    public Stage getPrimaryStage(){
        return primaryStage;
    }
}