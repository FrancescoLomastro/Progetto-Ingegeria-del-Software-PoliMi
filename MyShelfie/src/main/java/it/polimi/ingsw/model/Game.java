package it.polimi.ingsw.model;

import it.polimi.ingsw.model.CardGenerator.CardGenerator;
import it.polimi.ingsw.model.Cards.CommonGoalCard;
import it.polimi.ingsw.model.Cards.ObjectCard;
import it.polimi.ingsw.model.LivingRoom.Grid;
import it.polimi.ingsw.model.LivingRoom.LivingRoom;
import it.polimi.ingsw.model.Player.Library;
import it.polimi.ingsw.model.Player.Player;
import it.polimi.ingsw.model.Utility.Position;

import java.io.IOException;


/*
class Game
*@author Andrea Ferrini
*/
public class Game {

    private int gameNumber = 0;
    private final int numPlayers;
    private int i;
    private Player[] players;
    private Grid grid;
    private LivingRoom livingRoom;
    private Player winnerPlayer; // lo uso nel metodo winner come valore di return
    private CardGenerator cardGenerator;

    /**
     * constructor of the class Game
     * @param numPlayers: the number of the players in that game
     * @param gameNumber: to identify multiple games
     */
    public Game(int numPlayers, int gameNumber) throws IOException {
        this.cardGenerator=new CardGenerator();
        this.gameNumber = gameNumber;
        this.numPlayers=numPlayers;
        this.players = new Player[numPlayers];
        this.livingRoom = new LivingRoom(numPlayers, 0, 2,cardGenerator);
        this.winnerPlayer = new Player("winner", cardGenerator); // devo passargli una string
        this.grid = livingRoom.getGrid();
    }

    public int getNumPlayers() {
        return numPlayers;
    }

    public ObjectCard[][] getGrid() {
        return grid.getMatrix();
    }

    public ObjectCard[][] getLibrary(Player player) {
        return player.getLibrary().getMatrix();
    }

    public Player[] getPlayers() {
        Player[] answer = new Player[numPlayers];

        for(int i=0;i<numPlayers;i++) {
            answer[i] = players[i];
        }

        return answer;
    }

    /**
     * @param player: the turn player
     * @param move: an array of positions to identify the cells of the grid where the player takes his object cards
     * @param column: the column of the player's library in which he's going to insert the object cards he took in his move
     */
    public void manageTurn(Player player, Position[] move, int column){

        if(checkMove(player, move, column)){ //se la mossa è lecita

            //esecuzione mossa
            player.getLibrary().insertCardsInLibrary(column, grid.draw(move));

            // alla fine del turno
            checkCommonGoal(player, livingRoom.getCommonGoalCards());
        }
        else{

            throw new RuntimeException("mossa non valida ahhahahhahahhhahhhahahahahahahahahhahahhahahah");
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
        }

        if(commonGoalCards[1].isSatisfied(player.getLibrary())){

            player.addPoints(commonGoalCards[1].getPoints());
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

        if(grid.isDrawAvailable(move)){

            return player.getLibrary().isMoveAvailable(column,grid.draw(move)); //se la mossa è fattibile, controllo se la colonna della libreria è disponibile per compiere la mossa

        }
        else{ // se la mossa è a priori infattibile

            return false;
        }
    }

    /**
     * @return Player: the winner player
     */
    public Player findWinner(){

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

            if(library.getMatrix()[i][column]==null){

                return i;
            }
        }
        return -1; // gestire l'eccezione (sempre se non è gia stato fatto un controllo prima)
    }
}
