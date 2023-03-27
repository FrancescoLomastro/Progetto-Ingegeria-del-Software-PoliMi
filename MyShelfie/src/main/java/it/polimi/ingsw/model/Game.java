package it.polimi.ingsw.model;

import javax.swing.*;
import java.util.ArrayList;

public class Game {

    private final int numPlayers;
    Player[] players;
    LivingRoom livingRoom;
    Player winnerPlayer; // lo uso nel metodo winner come valore di return

    public Game(int numPlayers){
        this.numPlayers=numPlayers;
        this.players = new ArrayList<Player>();
        this.livingRoom = new it.polimi.ingsw.model.LivingRoom();
        winnerPlayer = new it.polimi.ingsw.model.Player(); // devo passargli una string
    }

    public void manageTurn(Player player, Couple[] move, int column){

        if(checkMove(player, move)){ //se la mossa è lecita

            //esegui la mossa


            // alla fine del turno
            if(checkCommonGoal(player)){

            }


        }else{

            //mossa non lecita
            //gestire gli output e le azioni successive
        }
    }

    private boolean checkCommonGoal(Player player){

        // servono: - this.livingRoom
        //          - carte del giocatore
        //          - libreria del giocatore
        // per controllare l'obiettivo comune

        // chiamo ScoreCard.getScore()
        // se score > 0 --> chiamo player.addPoint

        // avvisare se il player ha riempito la libreria
    }

    private boolean checkMove(Player player, Couple[] move){

        if(livingRoom.getGrid().moveAvailable){

            return player.getLibrary().checkColumn; //se la mossa è fattibile, controllo se la colonna della libreria è disponibile per compiere la mossa

        }else{ // se la mossa è a priori infattibile

            return false;
        }
    }

    public Player findWinner(){

        winnerPlayer = players.stream().reduce((player1, player2) -> (player1.getPoints() > player2.getPoints())? player1 : player2);

        return winnerPlayer;
    }
}

