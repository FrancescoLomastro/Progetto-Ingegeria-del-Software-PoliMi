package it.polimi.ingsw.View.Gui.guiControllers;

import it.polimi.ingsw.Network.Client.ClientModel;
import it.polimi.ingsw.Network.Messages.Message;
import it.polimi.ingsw.Network.ObserverImplementation.Observable;
import it.polimi.ingsw.View.Gui.GuiApplication;
import it.polimi.ingsw.View.OBSMessages.OBS_Message;
import it.polimi.ingsw.View.View;
import it.polimi.ingsw.model.Cards.ObjectCard;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.Modality;

/**
 * ViewFactory class contains methods related to the creation of stages during our program lifecycle.
 * The main responsibilities is to interact with specific "guiControllers" to direct interactions among users and stages.d
 *
 * @author Alberto Aniballi
 */
public class ViewFactory extends View {
    private static ViewFactory instance = null;
    private Stage stage;

    public static ViewFactory getInstance() {
        if (instance==null) {
            instance = new ViewFactory();
        }
        return instance;
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
        stage.setScene(scene);
    }

    private void createStage(FXMLLoader loader,int minHeight,int minWidth,boolean lockStage) {

        Scene scene = loadScene(loader);
        Stage stage = new Stage();
        this.stage= stage;
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setMinHeight(minHeight);
        stage.setMinWidth(minWidth);

        if (lockStage) {
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } else {
            stage.show();
        }
    }
    public void showStart() {
        Platform.runLater(()->{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Start.fxml"));
            createStage(loader,700,900,false);
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

    public void closeStage(Stage stage) {
        stage.close();
    }


    @Override
    public void startView() {
        Application.launch(GuiApplication.class);
    }

    @Override
    public void askInitialInfo() {
        Platform.runLater(()->{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ClientLogin.fxml"));
            switchScene(loader);
        });
    }


    @Override
    public void errorCreatingClient(String chosenAddress, int chosenPort) {

    }

    @Override
    public void startGame() {
        Platform.runLater(() -> {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Livingroom.fxml"));
            stage.setMinHeight(800);
            stage.setMinWidth(800);
            stage.setWidth(800);
            stage.setHeight(800);
            switchScene(loader);
        });
    }

    @Override
    public void chatMessage(String username, String text) {

    }

    @Override
    public void askNumberOfPlayers(int min, int max) {
        Platform.runLater(()->{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PlayerNumberRequest.fxml"));
            switchScene(loader);
        });

    }

    @Override
    public void askMove() {

    }

    @Override
    public void onInvalidUsername() {
        Platform.runLater(() -> {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/InvalidUsername.fxml"));
            switchScene(loader);
        });
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
        });
    }

    @Override
    public void showGrid(ObjectCard[][] grid) {

    }

    @Override
    public void showLibrary(ObjectCard[][] library, String username) {

    }

    @Override
    public void printAll(ClientModel clientObject) {

    }

    @Override
    public void startChat() {

    }

    @Override
    public void printPoints(ClientModel clientObject) {

    }



    @Override
    public void run() {
        //per ora lascialo stare
    }


    //Chiama questo metodo dai controllers delle scene pr notificare gli osservatori
    public void notifyAllOBS(OBS_Message msg)
    {
        setChanged();
        notifyObservers(msg);
    }
}