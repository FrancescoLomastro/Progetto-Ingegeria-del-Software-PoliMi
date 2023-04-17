package it.polimi.ingsw.controller;

import it.polimi.ingsw.Network.Messages.Message;
import it.polimi.ingsw.Network.Messages.MessageMove;
import it.polimi.ingsw.model.Game;

import static it.polimi.ingsw.Network.Messages.MessageType.MY_MOVE_ANSWER;
import static it.polimi.ingsw.Network.Messages.MessageType.START_GAME_MESSAGE;

public class TurnController implements Runnable{

    Game game;

    MessageMove message;

    public TurnController(Game game){

        this.game = game;
    }

    public void startTheTurn(MessageMove message){
        this.message = message;
        new Thread(this).start();
    }

    public void run(){

        if(message != null){

            game.manageTurn(message.getUsername(), message.getMove(), message.getColumn());
        }else throw new RuntimeException("messaggio non valido");
    }

    private void makeAMove(){


    }

    private void initGame(){


    }
}
