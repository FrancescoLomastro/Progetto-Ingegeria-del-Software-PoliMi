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

/*
class TurnController
*@author Andrea Ferrini
*/
public class TurnController implements Runnable, Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private final Game game;
    private final GameController gameController;
    private MessageMove message;
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
        gameController.notifyAllMessage(new MessageGame(START_GAME_MESSAGE));
        gameController.sendMessageToASpecificUser(
                new MessageMove(), game.getPlayers()[0].getName());
    }

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
                    game.getPlayers()[i].getNumPersonalGoal(),
                    game.getNumCommonGoal(0),
                    game.getNumCommonGoal(1),
                    game.getCentralPointCard(),
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
    public void startTheTurn(MessageMove message){
        if(message!= null && message.getUsername().equals(currentPlayer)){
            this.message = message;
            new Thread(this).start();
        }
        else {
            gameController.sendMessageToASpecificUser(new MessageMove(), currentPlayer);
        }
    }

    /**
     * implementation of Runnable interface's method run
     * */
    public void run()
    {
        Message moveResult;
        moveResult = game.manageTurn(message.getUsername(), message.getMove(), message.getColumn());
        if (moveResult.getType() == AFTER_MOVE_POSITIVE) {

            gameController.sendMessageToASpecificUser(moveResult, message.getUsername());

            ObjectCard[][] oldLibrary = game.getLibrary(message.getUsername());
            MessageLibrary messageLibrary = new MessageLibrary(game.getLibrary(message.getUsername()), message.getUsername(),
                    message.getMove(), findFilledPositionInLibrary(message.getColumn(), message.getMove(), oldLibrary) );
            gameController.notifyAllMessage(messageLibrary);

            MessageGrid messageGrid = new MessageGrid(game.getGrid(), MessageGrid.TypeOfGridMessage.UPDATE_AFTER_MOVE);
            gameController.notifyAllMessage(messageGrid);


            /*Controllo se la sua libraria è terminata, allora attivo il countdown*/
            if (game.checkEndLibrary(message.getUsername()) && !flagCountdown) {
                flagCountdown = true;
                int points= game.assignFillerPoints(message.getUsername());
                gameController.notifyAllMessage(new AlmostOverMessage(message.getUsername(),points));
            }


            //se il player ha completato almeno un obiettivo comune, informo tutti i giocatori
            if (((MessageAfterMovePositive) moveResult).getGainedPointsFirstCard() > 0 || ((MessageAfterMovePositive) moveResult).getGainedPointsSecondCard() > 0) {

                // copio nel messaggio del common goal i punti guadagnati, di cui ho tenuto traccia nel messageAfterMovePositive
                MessageCommonGoal messageCommonGoal = new MessageCommonGoal(
                        ((MessageAfterMovePositive) moveResult).getGainedPointsFirstCard(),
                        ((MessageAfterMovePositive) moveResult).getGainedPointsSecondCard(),
                        message.getUsername(),
                        game.getCommonGoalCard()[0].getPoints(),
                        game.getCommonGoalCard()[1].getPoints()
                );

                // e notifico a tutti i giocatori
                gameController.notifyAllMessage(messageCommonGoal);
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
            gameController.sendMessageToASpecificUser(new MessageMove(), currentPlayer); // richiedo la mossa al giocatore successivo
            gameController.updateFile();
        } else if (moveResult.getType() == AFTER_MOVE_NEGATIVE) {
            gameController.sendMessageToASpecificUser(moveResult, message.getUsername()); // avviso il giocatore che la mossa non è andata a buon fine
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
        ArrayList<Couple<String, Integer>> list = game.findWinner();
        gameController.notifyAllMessage(new MessageGame(MessageType.GAME_IS_OVER));
        gameController.notifyAllMessage(new MessagePoints(countActualPointAndShare()));
        for (Couple<String, Integer> stringIntegerCouple : list) {
            gameController.sendMessageToASpecificUser(new MessageWinner(
                            list.get(0).getFirst(),
                            stringIntegerCouple.getSecond(), list),
                    stringIntegerCouple.getFirst());
        }
        gameController.closeGame();
    }

    public String getPlayerAfterReload() {
        return currentPlayer;
    }

}
