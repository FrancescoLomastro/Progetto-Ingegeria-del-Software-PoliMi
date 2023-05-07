package it.polimi.ingsw.View.Gui.guiControllers;

import it.polimi.ingsw.Network.Client.ClientModel;
import it.polimi.ingsw.Network.Messages.Message;
import it.polimi.ingsw.Network.ObserverImplementation.Observable;
import it.polimi.ingsw.View.Gui.GuiApplication;
import it.polimi.ingsw.View.OBSMessages.OBS_Message;
import it.polimi.ingsw.View.View;
import it.polimi.ingsw.model.Cards.ObjectCard;
import javafx.application.Application;
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

    public static ViewFactory getInstance() {
        if (instance==null) {
            instance = new ViewFactory();
        }
        return instance;
    }




    /*showLoginClient è diventato getiInitialInfo*/

    public void showStart() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Start.fxml"));
        createStage(loader,700,900,false);
    }

// COME PASSARE VARIABILI//
     /* @Override
    public void askInitialInfo(String s) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ClientLogin.fxml"));

        // Personalizza il controller per passare il parametro 's'
        loader.setControllerFactory(controllerClass -> {
            ClientLoginController controller = new ClientLoginController();
            controller.setInitialInfo(s);                                          //da implementare in ogni controller
            return controller;
        });

        createStage(loader, 700, 900, false);
    }
    ///////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////

    public class ennesimaScenaController implements Initializable {

    @FXML
    private String initialInfo;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Utilizza la variabile 'initialInfo' come desideri
        System.out.println("Initial Info: " + initialInfo);
        //oppure assegnala ad una label
    }

    // ... altri metodi del controller
}

    */


    public void showClientLogin() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ClientLogin.fxml"));
        createStage(loader,700,900,false);
    }

    public void showInvalidPort() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/InvalidPort.fxml"));
        createStage(loader,200,320,true);
    }

    public void showInvalidNumPlayers() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/InvalidNumPlayers.fxml"));
        createStage(loader,200,320,true);
    }

    public void showPlayerNumberRequest(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PlayerNumberRequest.fxml"));
        switchScene(loader,event);
    }

    public void showAcceptedLogin(KeyEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AcceptedLogin.fxml"));
        switchScene(loader,event);
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

    private void switchScene(FXMLLoader loader, Event event) {

        Scene scene = loadScene(loader);
        Node node = (Node) event.getSource();
        Stage currentStage = (Stage) node.getScene().getWindow();

        currentStage.setScene(scene);
    }

    private void createStage(FXMLLoader loader,int minHeight,int minWidth,boolean lockStage) {

        Scene scene = loadScene(loader);
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

    public void closeStage(Stage stage) {
        stage.close();
    }


    @Override
    public void askInitialInfo() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ClientLogin.fxml"));
        createStage(loader,700,900,false);
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


    //Chiama questo metodo dai controllers delle scene pr notificare gli osservatori
    public void notifyAllOBS(OBS_Message msg)
    {
        setChanged();
        notifyObservers(msg);
    }
}