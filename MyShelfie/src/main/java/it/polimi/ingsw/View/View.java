package it.polimi.ingsw.View;

import it.polimi.ingsw.Network.Client.ClientModel;
import it.polimi.ingsw.Network.Messages.*;
import it.polimi.ingsw.Network.ObserverImplementation.Observable;
import it.polimi.ingsw.Network.ObserverImplementation.Observer;
import it.polimi.ingsw.View.OBSMessages.OBS_Message;
import it.polimi.ingsw.model.Cards.ObjectCard;
import it.polimi.ingsw.model.Utility.Position;

public abstract class View extends Observable<OBS_Message> implements Runnable, Observer<ClientModel,Message> {

    protected ClientModel clientModel;
    protected final int defaultRMIPort = 9000;
    protected final int defaultSocketPort = 8000;
    public abstract void startView();
    public abstract void askInitialInfo();
    public abstract void askNumberOfPlayers(int min, int max);
    public abstract void askMove();
    public abstract void onInvalidUsername();

    public abstract void lobbyUpdate(String string);
    public abstract void acceptedLogin();
    public abstract void printMessage(String string);
    public abstract void showGrid(ObjectCard[][] grid, MessageGrid.TypeOfGridMessage typeOfGridMessage);
    public abstract void showLibrary(ObjectCard[][] library, String username, Position[] old, Position[] inTable);
    public abstract void printAll();
    public abstract void startChat();
    public abstract void printPoints();
    public abstract void errorCreatingClient(String chosenAddress, int chosenPort);

    public abstract void chatMessage(String username, String text);

    public abstract void startGame();


    public abstract void almostOver(AlmostOverMessage arg);

    public abstract void update(ClientModel o, Message arg);


    public ClientModel getClientModel() {
        return clientModel;
    }
    public abstract void onServerChanged();                    //NON SERVE, POSSIAMO TOGLIERLO

    public int getDefaultRMIPort() {
        return defaultRMIPort;
    }

    public int getDefaultSocketPort() {
        return defaultSocketPort;
    }
}
