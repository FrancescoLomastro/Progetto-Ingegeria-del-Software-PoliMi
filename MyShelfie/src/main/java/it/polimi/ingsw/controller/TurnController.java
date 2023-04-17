package it.polimi.ingsw.controller;

import it.polimi.ingsw.Network.Messages.*;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.Player.Player;

import static it.polimi.ingsw.Network.Messages.MessageType.*;

/*
class TurnController
*@author Andrea Ferrini
*/
public class TurnController implements Runnable{

    private Game game;
    private GameController gameController;
    private MessageMove message;

    private int currPlayerIndex;
    private String currentPlayer;

    /**
     * the constructor of the class TurnController
     * @param game : the game which the TurnController is controlling
     * @param gameController : the Game controller associated to the game
     * */
    public TurnController(Game game, GameController gameController){

        this.game = game;
        this.gameController = gameController;
        currPlayerIndex = 0;
        currentPlayer = game.getPlayers()[0].getName();
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

            Message messageGrid = new MessageGrid();
            Message messageLibrary = new MessageLibrary();

            moveResult = game.manageTurn(message.getUsername(), message.getMove(), message.getColumn());

            if(moveResult.equals(AFTER_MOVE_POSITIVE)){

                // notifyAll di grid e library + eventtualemnte commongol

                ((MessageGrid) messageGrid).setGrid(game.getGrid()); // messaggio con griglia aggiornata
                gameController.notifyAllMessage(messageGrid);

                ((MessageLibrary) messageLibrary).setLibrary(game.getLibrary(message.getUsername())); // messaggio con libreria aggiornata
                ((MessageLibrary) messageLibrary).setPlayer(message.getUsername());
                gameController.notifyAllMessage(messageLibrary);

                gameController.sendMessageToASpecificUser(moveResult, message.getUsername()); // avviso il giocatore che la mossa è adnata a buon fine

                Message messageCommonGoal = new MessageCommonGoal();

                //se il player ha completato almeno un obiettivo comune, informo tutti i giocatori
                if(((MessageCommonGoal) messageCommonGoal).getGainedPointsFirstCard() > 0 || ((MessageCommonGoal) messageCommonGoal).getGainedPointsSecondCard() > 0){

                    gameController.notifyAllMessage(messageCommonGoal);
                }

                currPlayerIndex ++;
                if(currPlayerIndex == 4){

                    currPlayerIndex = 0;
                }

                currentPlayer = game.getPlayers()[currPlayerIndex].getName();

                gameController.sendMessageToASpecificUser(new MessageMove(), currentPlayer); // richiedo la mossa al giocatore successivo
            }
            else if(moveResult.equals(AFTER_MOVE_NEGATIVE)){

                gameController.sendMessageToASpecificUser(moveResult, message.getUsername()); // avviso il giocatore che la mossa non è andata a buon fine
            }

        }else throw new RuntimeException("messaggio non valido");
    }

    private void makeAMove(){


    }

    private void initGame(){


    }
}
