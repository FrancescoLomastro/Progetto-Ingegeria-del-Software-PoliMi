package it.polimi.ingsw.View;

import it.polimi.ingsw.Network.Client.ClientModel;
import it.polimi.ingsw.Network.ObserverImplementation.Observable;
import it.polimi.ingsw.View.OBSMessages.OBS_Message;
import it.polimi.ingsw.model.Cards.ObjectCard;

public abstract class View extends Observable<OBS_Message> implements Runnable {
    protected final int defaultRMIPort = 9000;
    protected final int defaultSocketPort = 8000;
    public abstract void startView();
    public abstract void askInitialInfo();
    public abstract void askNumberOfPlayers(int min, int max);
    public abstract void askMove();
    public abstract void onInvalidUsername();


    public abstract void printMessage(String string);
    public abstract void showGrid(ObjectCard[][] grid);
    public abstract void showLibrary(ObjectCard[][] library, String username);
    public abstract void printAll(ClientModel clientObject);
    public abstract void startChat();
    public abstract void printPoints(ClientModel clientObject);
    public abstract void errorCreatingClient(String chosenAddress, int chosenPort);

    public abstract void chatMessage(String username, String text);

    public void startGame(String gameStarted) {
    }
}
