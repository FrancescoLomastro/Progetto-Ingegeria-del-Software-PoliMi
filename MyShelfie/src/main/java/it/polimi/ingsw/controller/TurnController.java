package it.polimi.ingsw.controller;

import it.polimi.ingsw.Network.Messages.*;
import it.polimi.ingsw.Network.Servers.Connection;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.Player.Player;
import it.polimi.ingsw.model.Utility.Couple;

import java.util.ArrayList;
import java.util.Map;

import static it.polimi.ingsw.Network.Messages.MessageType.*;

/*
class TurnController
*@author Andrea Ferrini
*/
public class TurnController implements Runnable{
    private final Game game;
    private final GameController gameController;
    private MessageMove message;
    private int currPlayerIndex;
    private String currentPlayer;
    private boolean flagCountdown;
    private int numberOfTurnUntilEndGame;

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
        numberOfTurnUntilEndGame= game.getNumPlayers();
    }

    /**
     * a method to call to start the turn thread, it saves the message and runs the new thread
     * @param message : the message to save
     * */
    public void startTheTurn(MessageMove message){
        if(message.getUsername().equals(currentPlayer)){
            this.message = message;
            new Thread(this).start();
        }
    }

    /**
     * implementation of Runnable interface's method run
     * */
    public void run(){

        Message moveResult;
        if(message != null){

            MessageGrid messageGrid = new MessageGrid();
            MessageLibrary messageLibrary = new MessageLibrary();

            moveResult = game.manageTurn(message.getUsername(), message.getMove(), message.getColumn());

            if(moveResult.getType()==AFTER_MOVE_POSITIVE){

                // notifyAll di grid e library + eventtualemnte commongol

                messageGrid.setGrid(game.getGrid()); // messaggio con griglia aggiornata
                gameController.notifyAllMessage(messageGrid);

                messageLibrary.setLibrary(game.getLibrary(message.getUsername())); // messaggio con libreria aggiornata
                messageLibrary.setPlayer(message.getUsername());
                gameController.notifyAllMessage(messageLibrary);

                gameController.sendMessageToASpecificUser(moveResult, message.getUsername()); // avviso il giocatore che la mossa Ã¨ adnata a buon fine

                /*Controllo se la sua libraria Ã¨ terminata, allora attivo il countdown*/
                if(game.checkEndLibrary(message.getUsername()) && !flagCountdown)
                    flagCountdown =true;


                MessageCommonGoal messageCommonGoal = new MessageCommonGoal();

                //se il player ha completato almeno un obiettivo comune, informo tutti i giocatori
                if(messageCommonGoal.getGainedPointsFirstCard() > 0 || messageCommonGoal.getGainedPointsSecondCard() > 0){

                    gameController.notifyAllMessage(messageCommonGoal);
                }

                currPlayerIndex ++;
                if(currPlayerIndex == 4){

                    currPlayerIndex = 0;
                }

                currentPlayer = game.getPlayers()[currPlayerIndex].getName();

                if(flagCountdown && currPlayerIndex==0)
                    handleEndGame();
                gameController.sendMessageToASpecificUser(new MessageMove(), currentPlayer); // richiedo la mossa al giocatore successivo
            }
            else if(moveResult.getType()==AFTER_MOVE_NEGATIVE){
                gameController.sendMessageToASpecificUser(moveResult, message.getUsername()); // avviso il giocatore che la mossa non Ã¨ andata a buon fine
            }

        }
        else
            throw new RuntimeException("messaggio non valido");
    }

    private void handleEndGame() {
        ArrayList<Couple<String, Integer>> list = game.findWinner();
        for(int i=0; i<list.size(); i++)
            gameController.sendMessageToASpecificUser(new MessageWinner(
                    list.get(0).getFirst(),
                    list.get(i).getSecond() ), list.get(i).getFirst());
    }
}