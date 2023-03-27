package it.polimi.ingsw.model;

import javax.swing.*;
import java.util.ArrayList;

public class Game {

    private final int numPlayers;
    private int i;
    private Player[] players;

    private Grid grid;
    private LivingRoom livingRoom;

    private Player winnerPlayer; // lo uso nel metodo winner come valore di return

    public Game(int numPlayers){
        this.numPlayers=numPlayers;
        this.players = new Player[numPlayers];
        this.livingRoom = new LivingRoom(numPlayers, 0, 2);
        winnerPlayer = new Player("winner"); // devo passargli una string
        this.grid = livingRoom.getGrid();
    }

    public int getNumPlayers() {
        return numPlayers;
    }

    public void manageTurn(Player player, Position[] move, int column){

        if(checkMove(player, move, column)){ //se la mossa è lecita

            //esegui la mossa


            // alla fine del turno
            checkCommonGoal(player, livingRoom.getCommonGoalCards());


        }else{

            //mossa non lecita
            //gestire gli output e le azioni successive
        }
    }

    private void checkCommonGoal(Player player, CommonGoalCard[] commonGoalCards){

        // da gestire come scegliere l'algoritmo giusto tra i 12

        if(commonGoalCards[0].isSatisfied(player.getLibrary())){

            player.addPoints(commonGoalCards[0].getPoints());
            commonGoalCards[0].changePoints();
        }

        if(commonGoalCards[1].isSatisfied(player.getLibrary())){

            player.addPoints(commonGoalCards[1].getPoints());
            commonGoalCards[1].changePoints();
        }


        // servono: - this.livingRoom
        //          - carte del giocatore
        //          - libreria del giocatore
        // per controllare l'obiettivo comune

        // chiamo ScoreCard.getScore()
        // se score > 0 --> chiamo player.addPoint

        // avvisare se il player ha riempito la libreria
    }

    private boolean checkMove(Player player, Position[] move, int column){

        if(livingRoom.getGrid().isDrawAvailable(move)){

            return player.getLibrary().checkColumn(grid.draw(move), column); //se la mossa è fattibile, controllo se la colonna della libreria è disponibile per compiere la mossa

        }
        else{ // se la mossa è a priori infattibile

            return false;
        }
    }

    public Player findWinner(){

        // c'è un problema
        // winnerPlayer = players.stream().map(player -> player.getPoints()).reduce((player1, player2) -> (player1 > player2)? player1 : player2);


        winnerPlayer = players[0];

        for(i = 1; i < numPlayers; i++){

            if(players[i].getPoints() > winnerPlayer.getPoints()){

                winnerPlayer = players[i];
            }
        }

        return winnerPlayer;
    }
}

