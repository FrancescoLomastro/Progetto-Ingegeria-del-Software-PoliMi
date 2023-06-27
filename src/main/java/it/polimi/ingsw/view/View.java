package it.polimi.ingsw.view;

import it.polimi.ingsw.network.Client.ClientModel;
import it.polimi.ingsw.network.Messages.*;
import it.polimi.ingsw.network.ObserverImplementation.Observable;
import it.polimi.ingsw.network.ObserverImplementation.Observer;
import it.polimi.ingsw.view.OBSMessages.OBS_Message;
import it.polimi.ingsw.model.Cards.ObjectCard;
import it.polimi.ingsw.utility.Position;

/**
 * This class is the abstract class for managing the creation of stages, the succession of scenes and their changes during the game.
 *
 * @author Francesco Gregorio Lo Mastro
 */
public abstract class View extends Observable<OBS_Message> implements Runnable, Observer<ClientModel,Message> {
    protected ClientModel clientModel;
    protected final int defaultRMIPort = 9000;
    protected final int defaultSocketPort = 8000;

    /**
     * Abstract method for starting the mana java-FX thread.
     *
     * @author: Francesco Gregorio Lo Mastro
     */
    public abstract void startView();

    /**
     * Abstract method for starting the mana java-FX thread.
     *
     * @author: Francesco Gregorio Lo Mastro
     */
    public abstract void askInitialInfo();

    /**
     * Abstract method for starting the mana java-FX thread.
     *
     * @author: Francesco Gregorio Lo Mastro
     */
    public abstract void askNumberOfPlayers(int min, int max);

    /**
     * Abstract method for starting the mana java-FX thread.
     *
     * @author: Francesco Gregorio Lo Mastro
     */
    public abstract void askMove();

    /**
     * Abstract method for starting the mana java-FX thread.
     *
     * @author: Francesco Gregorio Lo Mastro
     */
    public abstract void onInvalidUsername();

    /**
     * Abstract method for starting the mana java-FX thread.
     *
     * @author: Francesco Gregorio Lo Mastro
     */
    public abstract void lobbyUpdate(String string);

    /**
     * Abstract method for starting the mana java-FX thread.
     *
     * @author: Francesco Gregorio Lo Mastro
     */
    public abstract void acceptedLogin();

    /**
     * Abstract method for starting the mana java-FX thread.
     *
     * @author: Francesco Gregorio Lo Mastro
     */
    public abstract void printMessage(String string);

    /**
     * Abstract method for starting the mana java-FX thread.
     *
     * @author: Francesco Gregorio Lo Mastro
     */
    public abstract void showGrid(ObjectCard[][] grid, GridMessage.TypeOfGridMessage typeOfGridMessage);

    /**
     * Abstract method for starting the mana java-FX thread.
     *
     * @author: Francesco Gregorio Lo Mastro
     */
    public abstract void showLibrary(ObjectCard[][] library, String username, Position[] old, Position[] inTable);

    /**
     * Abstract method for starting the mana java-FX thread.
     *
     * @author: Francesco Gregorio Lo Mastro
     */
    public abstract void printAll();

    /**
     * Abstract method for starting the mana java-FX thread.
     *
     * @author: Francesco Gregorio Lo Mastro
     */
    public abstract void startChat();

    /**
     * Abstract method for starting the mana java-FX thread.
     *
     * @author: Francesco Gregorio Lo Mastro
     */
    public abstract void printFinalRank(WinnerMessage msg);

    /**
     * Abstract method for starting the mana java-FX thread.
     *
     * @author: Francesco Gregorio Lo Mastro
     */
    public abstract void errorCreatingClient(String chosenAddress, int chosenPort);

    /**
     * Abstract method for starting the mana java-FX thread.
     *
     * @author: Francesco Gregorio Lo Mastro
     */
    public abstract void chatMessage(String username, String text);

    /**
     * Abstract method for starting the mana java-FX thread.
     *
     * @author: Francesco Gregorio Lo Mastro
     */
    public abstract void startGame();

    /**
     * Abstract method for starting the mana java-FX thread.
     *
     * @author: Francesco Gregorio Lo Mastro
     */
    public abstract void almostOver(AlmostOverMessage arg);

    /**
     * Abstract method for starting the mana java-FX thread.
     *
     * @author: Francesco Gregorio Lo Mastro
     */
    public abstract void update(ClientModel o, Message arg);

    /**
     * Abstract method for starting the mana java-FX thread.
     *
     * @author: Francesco Gregorio Lo Mastro
     */
    public ClientModel getClientModel() {
        return clientModel;
    }

    /**
     * Abstract method for starting the mana java-FX thread.
     *
     * @author: Francesco Gregorio Lo Mastro
     */
    public int getDefaultRMIPort() {
        return defaultRMIPort;
    }

    /**
     * Abstract method for starting the mana java-FX thread.
     *
     * @author: Francesco Gregorio Lo Mastro
     */
    public int getDefaultSocketPort() {
        return defaultSocketPort;
    }

    /**
     * Abstract method for starting the mana java-FX thread.
     *
     * @author: Francesco Gregorio Lo Mastro
     */
    public abstract void closeGame(String string);

    /**
     * Abstract method for starting the mana java-FX thread.
     *
     * @author: Francesco Gregorio Lo Mastro
     */
    public abstract void onBadMoveAnswer(BadMoveMessage msg);
}
