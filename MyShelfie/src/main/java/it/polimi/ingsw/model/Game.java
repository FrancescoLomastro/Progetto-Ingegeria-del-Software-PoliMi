package it.polimi.ingsw.model;

import javax.swing.*;
import java.util.ArrayList;

/*
class Game
*@author Andrea Ferrini
*/
public class Game {

    private int gameNumber = 0;
    private CardGenerator cardGenerator;


    private final int numPlayers;
    private int i;
    private Player[] players;
    private Grid grid;
    private LivingRoom livingRoom;
    private Player winnerPlayer; // lo uso nel metodo winner come valore di return

    /**
     * constructor of the class Game
     * @param numPlayers: the number of the players in that game
     * @param gameNumber: to identify multiple games
     */
    public Game(int numPlayers, /* dal controller: */ int gameNumber){

        this.gameNumber = gameNumber;
        this.cardGenerator = new CardGenerator(gameNumber);

        this.numPlayers=numPlayers;
        this.players = new Player[numPlayers];
        this.livingRoom = new LivingRoom(numPlayers, 0, 2);
        winnerPlayer = new Player("winner", cardGenerator); // devo passargli una string
        this.grid = livingRoom.getGrid();
    }

    public int getNumPlayers() {
        return numPlayers;
    }

    /**
     * @param player: the turn player
     * @param move: an array of positions to identify the cells of the grid where the player takes his object cards
     * @param column: the column of the player's library in which he's going to insert the object cards he took in his move
     */
    public void manageTurn(Player player, Position[] move, int column){

        if(checkMove(player, move, column)){ //se la mossa è lecita

            // la mossa viene eseguita da Library, all'interno del metodo checkColumn

            // TUTTO QUELLO QUI SOTTO E' INUTILE PERCHE' L'HA FATTO ALBERTO IN LIBRARY
            /*
            TUTTO CIO' E' INUTILE PERCHE' L'HA FATTO ALBERTO IN LIBRARY

            private ObjectCard[] toInsertCards = new ObjectCard[move.length];

            for(i = 0; i < toInsertCards.length; i++){

                toInsertCards[i] = this.grid.getMatrix()[move[i].getX()][move[i].getY()];
            }


            firstAvailableRow = getFirstAvailableRow(player.getLibrary(), column);

            player.getLibrary().insertCardsInLibrary(toInsertCards, firstAvailableRow, column);
            */

            // alla fine del turno
            checkCommonGoal(player, livingRoom.getCommonGoalCards());
        }
        else{

            //mossa non lecita
            //gestire gli output e le azioni successive
        }
    }

    /**
     * @param player: the turn player
     * @param commonGoalCards: the two common goal cards that are in the living room
     */
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

    /**
     * @param player: the turn player
     * @param move: an array of positions to identify the cells of the grid where the player takes his object cards
     * @param column: the column of the player's library in which he's going to insert the object cards he took in his move
     */
    private boolean checkMove(Player player, Position[] move, int column){

        if(livingRoom.getGrid().isDrawAvailable(move)){

            return player.getLibrary().checkColumn(grid.draw(move), column); //se la mossa è fattibile, controllo se la colonna della libreria è disponibile per compiere la mossa

        }
        else{ // se la mossa è a priori infattibile

            return false;
        }
    }

    /**
     * @return Player: the winner player
     */
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

    /**
     * @param library: the turn player's library
     * @param column: the column of the player's library in which he's going to insert the object cards he took in his move
     */
    public int getFirstAvailableRow(Library library, int column){

        for(i = library.getNumberOfColumns(); i > 0; i++){

            if(library.getLibrary()[i][column].equals(null)){

                return i;
            }
        }
        return -1; // gestire l'eccezione (sempre se non è gia stato fatto un controllo prima)
    }
}

