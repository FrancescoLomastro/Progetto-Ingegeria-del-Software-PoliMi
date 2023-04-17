package it.polimi.ingsw.controller;

import it.polimi.ingsw.Network.Messages.Message;
import it.polimi.ingsw.model.Game;

import static it.polimi.ingsw.Network.Messages.MessageType.MY_MOVE_ANSWER;
import static it.polimi.ingsw.Network.Messages.MessageType.START_GAME_MESSAGE;

public class TurnController implements Runnable{

    Game game;

    Message message;

    public TurnController(Game game){

        this.game = game;
    }

    public void startTheTurn(Message message){

        this.message = message;
        new Thread(this).start();
    }

    public void run(){

        if(message != null){

            switch(message){

                case START_GAME_MESSAGE -> initGame();

                case MY_MOVE_ANSWER -> makeAMove();
            }
        }else throw new RuntimeException("messaggio non valido");
    }

    private void makeAMove(){


    }

    private void initGame(){


    }
}
