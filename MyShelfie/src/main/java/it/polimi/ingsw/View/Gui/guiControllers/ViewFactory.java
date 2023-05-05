package it.polimi.ingsw.View.Gui.guiControllers;

import it.polimi.ingsw.Network.Client.ClientModel;
import it.polimi.ingsw.Network.Messages.Message;
import it.polimi.ingsw.Network.ObserverImplementation.Observable;
import it.polimi.ingsw.View.View;
import it.polimi.ingsw.model.Cards.ObjectCard;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Modality;

/**
 * ViewFactory class contains methods related to the creation of stages during our program lifecycle.
 * The main responsibilities is to interact with specific "guiControllers" to direct interactions among users and stages.
 *
 * @author Alberto Aniballi
 */
public class ViewFactory extends View {

    private static ViewFactory instance = null;

    public static ViewFactory getInstance() {
        if (instance==null) {
            instance = new ViewFactory();
        }
        return instance;
    }

    //scrivi questo metodo in AskInitialInfo
    public void showClientLogin() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ClientLogin.fxml"));
        createStage(loader,700,900,false);
    }


    public void showInvalidPort() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/InvalidPort.fxml"));
        createStage(loader,200,320,true);
    }

    private void createStage(FXMLLoader loader,int minHeight,int minWidth,boolean lockStage) {

        Scene scene = null;
        try {
            scene = new Scene(loader.load());
        } catch (Exception e) {
            e.printStackTrace();
        }

        Stage stage = new Stage();
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


    @Override
    public void askInitialInfo() {

    }
    @Override
    public void errorCreatingClient(String chosenAddress, int chosenPort) {

    }

    @Override
    public void askNumberOfPlayers(int min, int max) {

    }

    @Override
    public void askMove() {

    }

    @Override
    public void onInvalidUsername() {

    }

    @Override
    public void printAString(String string) {

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
}