package it.polimi.ingsw.view.Gui.guiControllers;

import it.polimi.ingsw.network.Client.ClientModel;
import it.polimi.ingsw.network.Messages.*;
import it.polimi.ingsw.network.ObserverImplementation.*;
import it.polimi.ingsw.view.Gui.GuiApplication;
import it.polimi.ingsw.view.OBSMessages.OBS_Message;
import it.polimi.ingsw.view.OBSMessages.OBS_MessageType;
import it.polimi.ingsw.view.OBSMessages.OBS_OnlyTypeMessage;
import it.polimi.ingsw.view.View;
import it.polimi.ingsw.model.Cards.ObjectCard;
import it.polimi.ingsw.utility.Couple;
import it.polimi.ingsw.utility.Position;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Modality;

import java.io.IOException;
import java.util.ArrayList;

/**
 * This class manages the creation of stages, the succession of scenes and their changes that characterise the game.
 * The main responsibilities is to interact with specific "guiControllers" to direct interactions among users, stages and scenes.
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
     * Returns or creates the only instance of the ViewFactory.
     *
     * @return: static instance of View Factory
     * @author: Alberto Aniballi
     */
    public static ViewFactory getInstance() {
        if (instance==null) {
            instance = new ViewFactory();
        }
        return instance;
    }

    /**
     * It is the constructor of the class, it has the main responsibility of adding an observer to the super class "clientModel" attribute.
     *
     * @return: static instance of View Factory
     * @author: Francesco Gregorio Lo Mastro
     */
    public ViewFactory() {
        clientModel=new ClientModel();
        clientModel.addObserver(this);
    }

    /**
     * It starts the mana java-FX thread.
     *
     * @author: Alberto Aniballi
     */
    @Override
    public void startView() {
        Application.launch(GuiApplication.class);
    }

    /**
     * This method is used to load a new scene.
     *
     * @param loader: the FXMLLoader instance with the proper URL location
     * @return: the loaded Scene
     * @author: Alberto Aniballi
     */
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

    /**
     * This method is used to switch the scene in the primary stage.
     *
     * @param loader: the FXMLLoader instance with the proper URL location
     * @author: Alberto Aniballi
     */
    private void switchScene_old(FXMLLoader loader) {
        Scene scene = loadScene_old(loader);
        primaryStage.setScene(scene);
    }

    /**
     * This method is used to initialize a new stage by inserting minimum height and width.
     * Furthermore, the method controls the situation in which the new stage must lock user interactions with previous stage.
     *
     * @param loader: the FXMLLoader instance with the proper URL location;
     * @param minHeight: the minimum stage height;
     * @param minWidth: the minimum stage width;
     * @param lockStage: the boolean condition upon which the previous stage is locked;
     * @param closeApplicationOnClose: the boolean condition upon which the appiclation is closed.
     * @author: Alberto Aniballi
     */
    private void createStage_old(FXMLLoader loader, int minHeight, int minWidth, boolean lockStage, boolean closeApplicationOnClose) {
        Stage newStage = new Stage();
        Scene scene = loadScene_old(loader);
        newStage.setScene(scene);
        newStage.setResizable(false);
        newStage.setMinHeight(minHeight);
        newStage.setMinWidth(minWidth);
        if(closeApplicationOnClose){
            newStage.setOnCloseRequest((event)->{
                System.exit(0);
            });
        }
        if (lockStage) {
            newStage.initOwner(primaryStage);
            newStage.initModality(Modality.APPLICATION_MODAL);
            newStage.showAndWait();
        } else {
            newStage.show();
        }
    }

    /**
     * This method is used to set the primary stage.
     *
     * @param primaryStage: the new primary stage.
     * @author: Francesco Gregorio Lo Mastro
     */
    public void setPrimaryStage(Stage primaryStage)
    {
        this.primaryStage = primaryStage;
    }

    /**
     * This method is used to close a stage.
     *
     * @param stage: the stage to be closed.
     * @author: Francesco Gregorio Lo Mastro
     */
    public void closeStage(Stage stage) {
        stage.close();
    }

    /**
     * This method is used to create the main stage of the game and to set the correct scene.
     *
     * @param loader: the FXMLLoader instance with the proper URL location
     * @author: Alberto Aniballi & Francesco Gregorio Lo Mastro
     */
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

    /**
     * This method is used to change root to the primary stage scene.
     *
     * @param loader: the FXMLLoader instance with the proper URL location
     * @author: Francesco Gregorio Lo Mastro
     */
    private void changeRoot(FXMLLoader loader)
    {
        try {
            primaryStage.getScene().setRoot(loader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * This method is used to instantiate the main stage and to load the first scene on it, the scene is the "start" scene.
     * After the scene is correctly loaded, the stage is shown.
     *
     * @author: Alberto Aniballi & Francesco Gregorio Lo Mastro
     */
    public void showStart() {
        Platform.runLater(()->{
          FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Start.fxml"));
          createMainStage(loader);
          primaryStage.getScene().setOnKeyPressed(keyEvent -> notifyAllOBS(new OBS_OnlyTypeMessage(OBS_MessageType.START)));
          primaryStage.show();
        });
        primaryStage.setOnCloseRequest(e -> {
            primaryStage.close();
            Platform.exit();
            System.exit(0);
        });
    }


    /**
     * This method is used to instantiate the "ClientLogin" scene on the primary stage once
     * a new client log into the pre-game phase.
     *
     * @author: Alberto Aniballi
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
     * This method is used to instantiate the "PlayerNumberRequest" scene on a new root.
     *
     * @author: Alberto Aniballi & Francesco Gregorio Lo Mastro
     */
    @Override
    public void askNumberOfPlayers(int min, int max) {
        Platform.runLater(()->{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PlayerNumberRequest.fxml"));
            changeRoot(loader);
        });
    }


    /**
     * This method is used to instantiate a new stage that notifies user of the start of the game.
     *
     * @author: Riccardo Figini
     */
    @Override
    public void startGame() {
        Platform.runLater(()->{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/GameHasBegun.fxml"));
            createStage_old(loader,200,320,true,false);
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

    /**
     * This method is used to instantiate the "InvalidUsername" scene on a new stage during the pre-game phase.
     * It shows the form that collect the new username that the user will use.
     *
     * @author: Alberto Aniballi & Francesco Gregorio Lo Mastro
     */
    @Override
    public void onInvalidUsername() {
        Platform.runLater(() -> {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/InvalidUsername.fxml"));
            switchScene_old(loader);
            primaryStage.setResizable(false);
            primaryStage.setFullScreen(false);
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
            if(controllerChat!=null) {
                controllerChat.printMessage(text, username);
                if (chatStage.isIconified()) {
                    Platform.runLater(() -> {
                        Board_C boardC = (Board_C) currentController;
                        boardC.notifyMessage();
                    });
                }
            }
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
            newStage.setOnCloseRequest(windowEvent -> {
                windowEvent.consume();
            });
        });
    }
    /*
    * Setup board to ask move
    * */
    @Override
    public void askMove() {
        Platform.runLater(() ->
        {
            Board_C boardSceneController = (Board_C) currentController;
            boardSceneController.onAskMove();
        });
    }
    /*
    * Update grid in board
    * */
    @Override
    public void showGrid(ObjectCard[][] grid, MessageGrid.TypeOfGridMessage typeOfGridMessage) {
        Platform.runLater(() ->
        {
            Board_C boardSceneController = (Board_C) currentController;
            boardSceneController.updateGrid(grid);
        });
    }
    /**Update library in board. If update is after a move, it calls an animation
    * @author: Riccardo Figini*/
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
    /**
    * Create stage for chat
     * @author: Riccardo figini
    * */
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
                Platform.exit();
                System.exit(0);
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
    public void printMessage(String s, Message msg){
        if(msg.getType()==MessageType.AFTER_MOVE_NEGATIVE){
            Platform.runLater(()->{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/InvalidMove.fxml"));
                loader.setControllerFactory((con)->{
                    InvalidMoveController invalidMoveController = new InvalidMoveController();
                    invalidMoveController.setText(s);
                    return invalidMoveController;
                });
                createStage_old(loader,200,320,true,false);
            });
        }
        else
            printMessage(s);
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
            createStage_old(loader,200,320,true,false);
        });
    }

    public void showInvalidNumPlayers() {
        Platform.runLater(()->{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/InvalidNumPlayers.fxml"));
            createStage_old(loader,200,320,true,false);
        });
    }

    public void showInvalidNumberOfCards() {
        Platform.runLater(()->{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/InvalidNumObjCards.fxml"));
            createStage_old(loader,200,320,true,false);
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

            createStage_old(loader, 200, 320, true, false);
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
            chatMessage("Server", arg.getPlayer()+" has reach common goal");
        });
    }

    private void showBoard(Message arg) {
        createBoard();
        primaryStage.setResizable(true);
        SetupMessage msg = (SetupMessage) arg;
        showGrid(msg.getGrid(), MessageGrid.TypeOfGridMessage.INIT);
        for (int i = 0; i < msg.getPlayersName().length; i++) {
            showLibrary(msg.getPlayersLibraries()[i], msg.getPlayersName()[i], null, null);
        }
        showCentralPoints(msg.getCentralPointCard());
        showCommonPoints(msg.getPointCardCommon1(), msg.getPointCardCommon2());
        showPointsPlayers(msg.getPlayersPoints());
    }

    private void showPointsPlayers(ArrayList<Couple<String, Integer>> playersPoints) {
        Platform.runLater(()->{
            Board_C boardC = (Board_C) currentController;
            boardC.setPlayersPoints(playersPoints);
        });
    }

    private void showCommonPoints(int pointCardCommon1, int pointCardCommon2) {
        Platform.runLater(()->{
            Board_C boardSceneController = (Board_C) currentController;
            boardSceneController.initCommonGoalPoints(pointCardCommon1, pointCardCommon2);
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
            showPointsPlayers(finalRanking);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/WinnerScene.fxml"));
            loader.setControllerFactory(controllerClass -> {
                WinnerSceneController winnerSceneController= new WinnerSceneController();
                winnerSceneController.setWinner(finalRanking.get(0).getFirst());
                winnerSceneController.setFinalRanking(finalRanking);
                return winnerSceneController;
            });
            createStage_old(loader, 200, 320, true, false);
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
            createStage_old(loader, 200, 320, true, false);
        });
    }

    public void onPersonalGoalCardClick() {
        Platform.runLater(() -> {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PersonalGoalCardPopUp.fxml"));
            loader.setControllerFactory(controllerClass -> {
                PersonalGoalCardPopUpController personalGoalCardPopUpController= new PersonalGoalCardPopUpController();
                return personalGoalCardPopUpController;
            });
            createStage_old(loader, 200, 320, true, false);
        });
    }
    public Stage getChatStage() {
        return chatStage;
    }

    public void showChat() {
        chatStage.setIconified(false);
        chatStage.toFront();
    }
    public void showCentralPoints(int centralPoints) {
        Platform.runLater(()->{
            Board_C boardSceneController = (Board_C) currentController;
            boardSceneController.showCentralPoints(centralPoints);
        });
    }
    public void closeGame(String string) {
        Platform.runLater(()->{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ErrorGame.fxml"));
            createStage_old(loader, 200, 320, true, true);
        });
    }
}