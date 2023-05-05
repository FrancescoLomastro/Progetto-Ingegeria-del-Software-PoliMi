package it.polimi.ingsw.View.Gui.guiControllers;

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
public class ViewFactory {

    private static ViewFactory instance = null;

    public static ViewFactory getInstance() {
        if (instance==null) {
            instance = new ViewFactory();
        }
        return instance;
    }

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
}