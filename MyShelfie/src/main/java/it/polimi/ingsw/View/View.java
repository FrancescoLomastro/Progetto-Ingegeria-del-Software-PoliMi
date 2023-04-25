package it.polimi.ingsw.View;

import it.polimi.ingsw.Network.Client.ClientModel;
import it.polimi.ingsw.Network.Messages.Message;
import it.polimi.ingsw.Network.ObserverImplementation.Observable;
import it.polimi.ingsw.model.Cards.ObjectCard;

public abstract class View extends Observable<Message> {

    public abstract String askUsername();
    public abstract int askTechnology();
    public abstract String askAddress();
    public abstract void updateObserversWithMessage(Message message);
    public abstract int askPort(int chosenTechnology, int defaultPort);
    public abstract void printAString(String string);
    public abstract int askNumberOfPlayers(int min, int max);
    public abstract String onInvalidUsername();
    public abstract void showGrid(ObjectCard[][] grid);
    public abstract void showLibrary(ObjectCard[][] library);
    //da rivedere
    public abstract void printAll(ClientModel clientObject);
    //da rivedere
    public abstract Message askMove();
}
