package it.polimi.ingsw.controller;

import it.polimi.ingsw.Network.Messages.*;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.Utility.Couple;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

import static it.polimi.ingsw.Network.Messages.MessageType.*;

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

    public void initClientObjectInPlayer() {
        gameController.notifyAllMessage(new MessageGrid(game.getGrid()));
        for(int i=0; i<game.getNumPlayers(); i++){
            gameController.notifyAllMessage(new MessageInitPlayer(game.getPlayers()[i].getName()));
            gameController.notifyAllMessage(new MessageLibrary(game.getLibrary(game.getPlayers()[i].getName()), game.getPlayers()[i].getName()));
            gameController.sendMessageToASpecificUser(new MessagePersonalGoal(game.getPlayers()[i].getPersonalGoalCard().getGoalVector()), game.getPlayers()[i].getName());
            gameController.notifyAllMessage(
                    new MessaggeInitCommondGoal(game.getCommonGoalCard()[0].getDescription(), game.getCommonGoalCard()[1].getDescription()));
        }
        countActualPointAndShare();
    }

    private void countActualPointAndShare() {
        ArrayList<Couple<String, Integer>> list = new ArrayList<>();
        for(int i=0; i<game.getNumPlayers(); i++){
            list.add(new Couple<>(game.getPlayers()[i].getName(), game.getPlayers()[i].getPoints()));
        }
        gameController.notifyAllMessage(new MessagePoints(list));
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
    public void run() {
        Message moveResult;
        moveResult = game.manageTurn(message.getUsername(), message.getMove(), message.getColumn());
        if (moveResult.getType() == AFTER_MOVE_POSITIVE) {

            MessageGrid messageGrid = new MessageGrid(game.getGrid());
            gameController.notifyAllMessage(messageGrid);

            MessageLibrary messageLibrary = new MessageLibrary(game.getLibrary(message.getUsername()), message.getUsername());
            gameController.notifyAllMessage(messageLibrary);

            gameController.sendMessageToASpecificUser(moveResult, message.getUsername()); // avviso il giocatore che la mossa è adnata a buon fine

            /*Controllo se la sua libraria è terminata, allora attivo il countdown*/
            if (game.checkEndLibrary(message.getUsername()) && !flagCountdown) {
                flagCountdown = true;
                gameController.notifyAllMessage(new MessageGame(ALMOST_OVER));
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

    private void handleEndGame() {
        ArrayList<Couple<String, Integer>> list = game.findWinner();
        gameController.notifyAllMessage(new MessageGame(MessageType.GAME_IS_OVER));
        countActualPointAndShare();
        for(int i=0; i<list.size(); i++) {
            gameController.sendMessageToASpecificUser(new MessageWinner(
                            list.get(0).getFirst(),
                            list.get(i).getSecond()),
                    list.get(i).getFirst());
        }
        gameController.closeGame();
    }

    public String getPlayerAfterReload() {
        return currentPlayer;
    }

}
