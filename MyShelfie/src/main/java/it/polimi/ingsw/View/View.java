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


    public abstract void printMessage(String string);
    public abstract void showGrid(ObjectCard[][] grid, MessageGrid.TypeOfGridMessage typeOfGridMessage);
    public abstract void showLibrary(ObjectCard[][] library, String username, Position[] old, Position[] inTable);
    public abstract void printAll();
    public abstract void startChat();
    public abstract void printPoints();
    public abstract void errorCreatingClient(String chosenAddress, int chosenPort);

    public abstract void chatMessage(String username, String text);

    public abstract void startGame();

    @Override
    public void update(ClientModel o, Message arg) {
        switch (arg.getType())
        {
            case SETUP_MESSAGE -> {
                SetupMessage msg = (SetupMessage) arg;
                showGrid(msg.getGrid(),MessageGrid.TypeOfGridMessage.INIT);
                for (int i=0; i<msg.getPlayersName().length;i++)
                {
                    showLibrary(msg.getPlayersLibraries()[i],msg.getPlayersName()[i],null,null);
                }
            }
            case UPDATE_GRID_MESSAGE -> {
                ObjectCard[][] obs = ((MessageGrid) arg).getGrid();
                showGrid(obs, ((MessageGrid) arg).getTypeOfGridMessage());
            }
            case UPDATE_LIBRARY_MESSAGE -> {
                ObjectCard[][] obs = ((MessageLibrary) arg).getLibrary();
                showLibrary(obs, ((MessageLibrary) arg).getOwnerOfLibrary(),((MessageLibrary) arg).getCardInGrid(), ((MessageLibrary) arg).getCardInLibr() );
            }
            //case COMMON_GOAL -> showPoint( (MessageCommonGoal) arg);
        }
    }


    public ClientModel getClientModel() {
        return clientModel;
    }
    public abstract void onServerChanged();

    public int getDefaultRMIPort() {
        return defaultRMIPort;
    }

    public int getDefaultSocketPort() {
        return defaultSocketPort;
    }
}
