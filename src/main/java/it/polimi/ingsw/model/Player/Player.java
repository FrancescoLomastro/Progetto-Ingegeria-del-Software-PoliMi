package it.polimi.ingsw.model.Player;

import it.polimi.ingsw.model.CardGenerator.CardGenerator;
import it.polimi.ingsw.model.Cards.PersonalGoalCard;

import java.io.Serial;
import java.io.Serializable;

/**
 * This class represent the model of a player.
 * The player class is used to reach libraries, personal cards and player points
 */
public class Player implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private final String name;
    private int points;
    private final Library library;
    private final PersonalGoalCard personalGoalCard;


    /**
     * Contructor: This method creates a player, with a name and a personal goal card
     * @param name The name of the player
     * @param cardGenerator The game card generator
     * @author: Alberto Aniballi
     */
    public Player(String name, CardGenerator cardGenerator) {
        this.name = name;
        this.points = 0;
        this.library = new Library();
        this.personalGoalCard = cardGenerator.generatePersonalGoalCard();
    }




    /**It returns the name of player
     * @return The name of the player
     *  @author: Alberto Aniballi
     */
    public String getName() {
        return name;
    }




    /**It returns the actual points of player
     * @return The points of the player
     *  @author: Alberto Aniballi
     */
    public int getPoints() {
        return points;
    }




    /**It returns the personal goal card of player
     * @return The personal goal card of the player
     *  @author: Alberto Aniballi
     */
    public PersonalGoalCard getPersonalGoalCard() {
        return personalGoalCard;
    }





    /**It returns the library of player
     * @return The library of the player
     *  @author: Alberto Aniballi
     */
    public Library getLibrary() {
        return library;
    }





    /**
     * This method is used to add points to the player
     *  @author: Alberto Aniballi
     * @param points the amount of points to add
     */
    public void addPoints(int points) {
        this.points+=points;
    }




    /**
     * It counts and returns the points of player at the end of the game. So it counts also
     * adjacent points
     * @author: Alberto Aniballi
     * @return The number of points realized by the personal goal card and by the library adolescences in the player's library.
     */
    public int countFinalPoints() {
        int countPersonalGoalCardPoints = this.personalGoalCard.countPersonalGoalCardPoints(library);
        int countLibraryAdjacentPoints = library.countAdjacentPoints();
        int final_points = points + countPersonalGoalCardPoints + countLibraryAdjacentPoints;
        return final_points;
    }




    /**It returns the ID of the personal goal card
     * @author: Francesco Lo Mastro
     * @return The personal goal card ID of the player
     */
    public int getPersonalCardId(){
        return personalGoalCard.getCardId();
    }
}
