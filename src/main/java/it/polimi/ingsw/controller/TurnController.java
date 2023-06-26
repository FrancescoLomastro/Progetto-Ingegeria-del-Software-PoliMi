package it.polimi.ingsw.controller;

import it.polimi.ingsw.network.Messages.*;
import it.polimi.ingsw.model.Cards.ObjectCard;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.utility.Couple;
import it.polimi.ingsw.utility.Position;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

import static it.polimi.ingsw.network.Messages.MessageType.*;

/**
 * A controller created each time a turn needs to be handled
 */
public class TurnController implements Runnable, Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private final Game game;
    private final GameController gameController;
    private MoveMessage message;
    private int currPlayerIndex;
    private String currentPlayer;
    private boolean flagCountdown;

    /**
     * the constructor of the class TurnController
     * @param game : the game which the TurnController is controlling
     * @param gameController : the Game controller associated to the game
     * */
    public TurnController(Game game, GameController gameController){
        this.flagCountdown =false;
        this.game = game;
        this.gameController = gameController;
        currPlayerIndex = 0;
        currentPlayer = game.getPlayers()[0].getName();
        initClientObjectInPlayer();
        gameController.notifyAllMessage(new GameMessage(START_GAME_MESSAGE));
        gameController.sendMessageToASpecificUser(
                new MoveMessage(), game.getPlayers()[0].getName());
    }
    /**It shares initial information with player at the beginning of the game
     * @author: Francesco Gregorio Lo Mastro
     * */
    public void initClientObjectInPlayer()
    {
        String[] playerNames= new String[game.getNumPlayers()];
        ObjectCard[][][] playerLibraries = new ObjectCard[game.getNumPlayers()][][];
        String[] commonGoals = new String[2];
        commonGoals[0] = game.getCommonGoalCard()[0].getDescription();
        commonGoals[1] = game.getCommonGoalCard()[1].getDescription();
        for(int i=0; i<game.getNumPlayers(); i++)
        {
            playerNames[i]=game.getPlayers()[i].getName();
            playerLibraries[i]=game.getLibrary(game.getPlayers()[i].getName());
        }

        for(int i=0; i<game.getNumPlayers(); i++)
        {
            SetupMessage message= new SetupMessage(
                    game.getCommonGoalCard()[0].getPoints(),
                    game.getCommonGoalCard()[1].getPoints(),
                    game.getPlayers()[i].getPersonalCardId(),
                    game.getCommonGoalCardId(0),
                    game.getCommonGoalCardId(1),
                    game.getCentralScore(),
                    game.getGrid(),
                    playerNames,
                    game.getPlayers()[i].getPersonalGoalCard().getGoalVector(),
                    commonGoals,
                    countActualPointAndShare(),
                    playerLibraries
            );
            gameController.sendMessageToASpecificUser(message,playerNames[i]);
        }
        countActualPointAndShare();
    }
    /**It counts player's point and return it in array
     * @author: Riccardo Figini
     * @return {@code ArrayList<Couple<String, Integer>>} Array with points
     * */
    private ArrayList<Couple<String, Integer>> countActualPointAndShare() {
        ArrayList<Couple<String, Integer>> list = new ArrayList<>();
        for(int i=0; i<game.getNumPlayers(); i++){
            list.add(new Couple<>(game.getPlayers()[i].getName(), game.getPlayers()[i].getPoints()));
        }
        return list;
    }

    /**
     * a method to call to start the turn thread, it saves the message and runs the new thread
     * @param message : the message to save
     * */
    public void startTheTurn(MoveMessage message){
        if(message!= null && message.getSenderName().equals(currentPlayer)){
            this.message = message;
            new Thread(this).start();
        }
        else {
            gameController.sendMessageToASpecificUser(new MoveMessage(), currentPlayer);
        }
    }

    /**
     * implementation of Runnable interface's method run
     * */
    public void run()
    {
        Message moveResult;
        moveResult = game.manageTurn(message.getSenderName(), message.getMove(), message.getColumn());
        if (moveResult.getType() == GOOD_MOVE_ANSWER) {

            gameController.sendMessageToASpecificUser(moveResult, message.getSenderName());

            ObjectCard[][] oldLibrary = game.getLibrary(message.getSenderName());
            LibraryMessage messageLibrary = new LibraryMessage(game.getLibrary(message.getSenderName()), message.getSenderName(),
                    message.getMove(), findFilledPositionInLibrary(message.getColumn(), message.getMove(), oldLibrary) );
            gameController.notifyAllMessage(messageLibrary);

            GridMessage messageGrid = new GridMessage(game.getGrid(), GridMessage.TypeOfGridMessage.UPDATE_AFTER_MOVE);
            gameController.notifyAllMessage(messageGrid);

            if (game.checkEndLibrary(message.getSenderName()) && !flagCountdown) {
                flagCountdown = true;
                int points= game.firstLibraryCompletion(message.getSenderName());
                gameController.notifyAllMessage(new AlmostOverMessage(message.getSenderName(),points));
            }

            if (((GoodMoveMessage) moveResult).getGainedPointsFirstCard() > 0 || ((GoodMoveMessage) moveResult).getGainedPointsSecondCard() > 0) {

                CommonGoalMessage commonGoalMessage = new CommonGoalMessage(
                        ((GoodMoveMessage) moveResult).getGainedPointsFirstCard(),
                        ((GoodMoveMessage) moveResult).getGainedPointsSecondCard(),
                        message.getSenderName(),
                        game.getCommonGoalCard()[0].getPoints(),
                        game.getCommonGoalCard()[1].getPoints()
                );

                gameController.notifyAllMessage(commonGoalMessage);
            }


            currPlayerIndex++;
            if (currPlayerIndex == game.getNumPlayers()) {
                currPlayerIndex = 0;
            }
            currentPlayer = game.getPlayers()[currPlayerIndex].getName();

            if (flagCountdown && currPlayerIndex == 0) {
                handleEndGame();
                return;
            }
            gameController.sendMessageToASpecificUser(new MoveMessage(), currentPlayer); // richiedo la mossa al giocatore successivo
            gameController.updateFile();
        } else if (moveResult.getType() == BAD_MOVE_ANSWER) {
            gameController.sendMessageToASpecificUser(moveResult, message.getSenderName()); // avviso il giocatore che la mossa non è andata a buon fine
        }
    }
    /**it returns cells are be filled with the move in library
     * @author: Riccardo Figini
     * @param column Column where card are been inserted
     * @param move Move
     * @param oldLibrary Old library, before move
     * @return {@code Position[]} Array with position
     * */
    private Position[] findFilledPositionInLibrary(int column, Position[] move, ObjectCard[][] oldLibrary) {
        Position[] tmp = new Position[move.length];
        int index=5;
        for(; index>=0; index--)
            if(oldLibrary[index][column]==null)
                break;
        index++;
        int i=move.length-1;
        for(; i>=0; i--){
            tmp[i]=new Position(index, column);
            index++;
        }
        return tmp;
    }
    /**It handles researches of a winner and close the game
     * @author: Riccardo Figini
     * */
    private void handleEndGame() {
        ArrayList<Couple<String, Integer>> list = game.findRanking();
        gameController.notifyAllMessage(new GameMessage(MessageType.GAME_OVER_MESSAGE));
        gameController.notifyAllMessage(new ScoreMessage(countActualPointAndShare()));
        for (Couple<String, Integer> stringIntegerCouple : list) {
            gameController.sendMessageToASpecificUser(
                    new WinnerMessage(
                            stringIntegerCouple.getSecond(),
                            list),
                    stringIntegerCouple.getFirst());
        }
        gameController.closeGame();
    }
    /**It returns first player after after reload
     * @author: Riccardo FIgini
     * @return {@code String} Player's name
     * */
    public String getPlayerAfterReload() {
        return currentPlayer;
    }

}
