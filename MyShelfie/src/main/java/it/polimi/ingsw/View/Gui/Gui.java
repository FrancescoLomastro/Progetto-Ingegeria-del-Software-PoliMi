package it.polimi.ingsw.View.Gui;

import it.polimi.ingsw.Network.Client.ClientModel;
import it.polimi.ingsw.View.Gui.guiControllers.ViewFactory;
import it.polimi.ingsw.View.View;
import it.polimi.ingsw.model.Cards.ObjectCard;
import javafx.application.Platform;

public class Gui extends View {
    @Override
    public void startView() {
        Platform.runLater(() -> ViewFactory.getInstance().showStart());
    }

    @Override
    public void askInitialInfo() {
        Platform.runLater(() -> ViewFactory.getInstance().askInitialInfo()) ;
    }

    @Override
    public void askNumberOfPlayers(int min, int max) {
        Platform.runLater(() -> ViewFactory.getInstance().askNumberOfPlayers(2,4));
    }

    @Override
    public void askMove() {

    }

    @Override
    public void onInvalidUsername() {

    }

    @Override
    public void printMessage(String string) {

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
    public void errorCreatingClient(String chosenAddress, int chosenPort) {

    }

    @Override
    public void run() {

    }
}
