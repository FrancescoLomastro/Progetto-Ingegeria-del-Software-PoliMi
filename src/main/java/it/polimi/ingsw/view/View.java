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
 * @author Francesco Lo Mastro
 */
public abstract class View extends Observable<OBS_Message> implements Runnable, Observer<ClientModel,Message> {
    protected ClientModel clientModel;
    protected final int defaultRMIPort = 9000;
    protected final int defaultSocketPort = 8000;

    /**
     * Abstract method for starting the mana java-FX thread.
     *
     * @author: Francesco Lo Mastro
     */
    public abstract void startView();

    /**
     * Abstract method for asking initial info.
     *
     * @author: Francesco Lo Mastro
     */
    public abstract void askInitialInfo();

    /**
     * Abstract method for asking number of players to the first player.
     *
     * @param min: minimum number of players;
     * @param max: maximum number of players;
     * @author: Francesco Lo Mastro
     */
    public abstract void askNumberOfPlayers(int min, int max);

    /**
     * Abstract method for asking a move to players.
     *
     * @author: Francesco Lo Mastro
     */
    public abstract void askMove();

    /**
     * Abstract method for starting the mana java-FX thread.
     *
     * @author: Francesco Lo Mastro
     */
    public abstract void onInvalidUsername();

    /**
     * Abstract method for showing the form that collect the new username that the user will use.
     *
     * @param string: the string displaying current lobby's information
     * @author: Francesco Lo Mastro
     */
    public abstract void lobbyUpdate(String string);

    /**
     * Abstract method for accepting login.
     *
     * @author: Francesco Lo Mastro
     */
    public abstract void acceptedLogin();

    /**
     * Abstract method for printing in chat message from server.
     *
     * @param string Message from server
     * @author: Francesco Lo Mastro
     */
    public abstract void printMessage(String string);

    /**
     * Abstract method for showing the grid gui.
     *
     * @param grid: the current grid;
     * @param typeOfGridMessage: the message sent by the grid;
     * @author: Francesco Lo Mastro
     */
    public abstract void showGrid(ObjectCard[][] grid, GridMessage.TypeOfGridMessage typeOfGridMessage);

    /**
     * Abstract method for showing the library gui.
     *
     * @param library: previous library;
     * @param username: the player username;
     * @param old: the old grid;
     * @param inTable: updated library;
     * @author: Francesco Lo Mastro
     */
    public abstract void showLibrary(ObjectCard[][] library, String username, Position[] old, Position[] inTable);

    /**
     * Abstract method for printing anything in the chat.
     *
     * @author: Francesco Lo Mastro
     */
    public abstract void printAll();

    /**
     * Abstract method for starting the chat.
     *
     * @author: Francesco Lo Mastro
     */
    public abstract void startChat();

    /**
     * Abstract method for printing final rank.
     *
     * @author: Francesco Lo Mastro
     */
    public abstract void printFinalRank(WinnerMessage msg);

    /**
     * Abstract method for warning the
     * user that there was a problem during the creation of its player to participate in the game.
     *
     * @param chosenAddress : the server address chosen by the player
     * @param chosenPort : the port chosen by the player
     * @author: Francesco Lo Mastro
     */
    public abstract void errorCreatingClient(String chosenAddress, int chosenPort);

    /**
     * Abstract method for printing a message in players chat.
     *
     * @param username: the player username who sent the message;
     * @param text: the message text;
     * @author: Francesco Lo Mastro
     */
    public abstract void chatMessage(String username, String text);

    /**
     * Abstract method for starting the game.
     *
     * @author: Francesco Lo Mastro
     */
    public abstract void startGame();

    /**
     * Abstract method for entering the ending phase of the game.
     *
     * @param arg: the message;
     * @author: Francesco Lo Mastro
     */
    public abstract void almostOver(AlmostOverMessage arg);


    public abstract void update(ClientModel o, Message arg);

    /**
     * Abstract method for getting client model.
     *
     * @author: Francesco Lo Mastro
     */
    public ClientModel getClientModel() {
        return clientModel;
    }

    /**
     * Abstract method for getting default rmi port.
     *
     * @author: Francesco Lo Mastro
     */
    public int getDefaultRMIPort() {
        return defaultRMIPort;
    }

    /**
     * Abstract method for getting default socket port.
     *
     * @author: Francesco Lo Mastro
     */
    public int getDefaultSocketPort() {
        return defaultSocketPort;
    }

    /**
     * Abstract method for closing the game.
     *
     * @param string: string to be printed
     * @author: Francesco Lo Mastro
     */
    public abstract void closeGame(String string);

    /**
     * Abstract method for signaling a bad move.
     *
     * @param msg: message to be displayed
     * @author: Francesco Lo Mastro
     */
    public abstract void onBadMoveAnswer(BadMoveMessage msg);
}
