package it.polimi.ingsw.model;

import it.polimi.ingsw.Network.Messages.Message;
import it.polimi.ingsw.Network.Messages.MessageAfterMoveNegative;
import it.polimi.ingsw.Network.Messages.MessageAfterMovePositive;
import it.polimi.ingsw.Network.Messages.MessageCommonGoal;
import it.polimi.ingsw.exceptions.InvalidMoveException;
import it.polimi.ingsw.model.CardGenerator.CardGenerator;
import it.polimi.ingsw.model.Cards.CommonGoalCard;
import it.polimi.ingsw.model.Cards.ObjectCard;
import it.polimi.ingsw.model.LivingRoom.Grid;
import it.polimi.ingsw.model.LivingRoom.LivingRoom;
import it.polimi.ingsw.model.Player.Library;
import it.polimi.ingsw.model.Player.Player;
import it.polimi.ingsw.model.Utility.Couple;
import it.polimi.ingsw.model.Utility.Position;

import javax.swing.*;
import java.io.IOException;
import java.util.*;


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

    private int index;

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
        this.livingRoom = new LivingRoom(numPlayers,  2,cardGenerator);
        this.winnerPlayer = new Player("winner", cardGenerator); // devo passargli una string
        this.grid = livingRoom.getGrid();
        this.index = 0;
    }

    public int getNumPlayers() {
        return numPlayers;
    }

    public ObjectCard[][] getGrid() {
        return grid.getMatrix();
    }

    public ObjectCard[][] getLibrary(String username) {

        return searchByUsername(username).getLibrary().getMatrix();
    }

    public Player[] getPlayers() {
        Player[] answer = new Player[numPlayers];

        for(int i=0;i<numPlayers;i++) {
            answer[i] = players[i];
        }

        return answer;
    }

    /**
     * this method searches a player in a game, starting from his username
     * @param username : the turn player
     * @return Player : the Player whose username matches with the parameter
     */
    public Player searchByUsername(String username){

        for(Player player : players){

            if(player.getName().equals(username)){

                return player;
            }
        }
        return null;
    }

    /**
     * @param username: the turn player
     * @param move: an array of positions to identify the cells of the grid where the player takes his object cards
     * @param column: the column of the player's library in which he's going to insert the object cards he took in his move
     */
    public Message manageTurn(String username, Position[] move, int column, MessageAfterMovePositive messageAfterMovePositive){

        Player player = searchByUsername(username);
        try{
            checkMove(player, move, column);
            player.getLibrary().insertCardsInLibrary(column, grid.draw(move));

            checkCommonGoal(player, livingRoom.getCommonGoalCards(),  messageAfterMovePositive);
            return messageAfterMovePositive;
        }
        catch (InvalidMoveException e){

            MessageAfterMoveNegative messageAfterMoveNegative = new MessageAfterMoveNegative();
            messageAfterMoveNegative.setInvelidmessage(e.getMessage());
            return messageAfterMoveNegative;
        }
    }

    /**
     * @param player: the turn player
     * @param commonGoalCards: the two common goal cards that are in the living room
     */
    private void checkCommonGoal(Player player, CommonGoalCard[] commonGoalCards, MessageAfterMovePositive messageAfterMove){

        //DA FARE: INVIARE IL messageAfterMove DOPO IL CONSEGUIMENTO DEGLI OBIETTIVI COMUNI
        // SALVARE ANCHE IL NUMERO DI PUNTI AGGIUNTI, PER DIRLO AL PLAYER CHE LI HA GUADAGNATI fatto



        // da gestire come scegliere l'algoritmo giusto tra i 12

        if(commonGoalCards[0].isSatisfied(player.getLibrary())){

            commonGoalCards[0].getScoreWithDecrease();

            messageAfterMove.setGainedPointsFirstCard(commonGoalCards[0].getPoints());

            player.addPoints(commonGoalCards[0].getPoints());
        }

        if(commonGoalCards[1].isSatisfied(player.getLibrary())){

            commonGoalCards[1].getScoreWithDecrease();

            messageAfterMove.setGainedPointsSecondCard(commonGoalCards[1].getPoints());

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
    private void checkMove(Player player, Position[] move, int column) throws InvalidMoveException{
        grid.isDrawAvailable(move);
        player.getLibrary().isMoveAvailable(column,grid.draw(move));
    }

    /**
     * @return ArrayList<Couple<String, Integer>>: list
     */
    public ArrayList<Couple<String, Integer>> findWinner(){
        ArrayList<Couple<String, Integer>> list = new ArrayList<>();
        for(int i=0; i<numPlayers; i++){
            list.add(new Couple<>(players[i].getName(), players[i].countFinalPoints()));
        }

        for(int i=0; i<numPlayers; i++){
            for(int j=i+1; j<numPlayers; j++){
                if(list.get(j).getSecond()>list.get(i).getSecond()){
                    Couple<String, Integer> tmp = list.get(i);
                    list.set(i, list.get(j));
                    list.set(j, tmp);
                }
            }
        }

        return list;
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
        return -1; // gestire l'eccezione (sempre se non Ã¨ gia stato fatto un controllo prima)
    }

    public void setNextPlayer(String name){

        this.players[index] = new Player(name, cardGenerator);
        index ++;
    }

    public boolean checkEndLibrary(String username){
        Player player = searchByUsername(username);
        return player.getLibrary().isFull();
    }
}