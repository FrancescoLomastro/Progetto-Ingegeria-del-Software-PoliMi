package it.polimi.ingsw.model.Player;

import it.polimi.ingsw.model.CardGenerator.CardGenerator;
import it.polimi.ingsw.model.Cards.PersonalGoalCard;

import java.io.Serializable;

/**
 * This class represent the model of a player.
 * The player class is used to reach libraries, personal cards and player points
 */
public class Player implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private int points;
    private Library library;
    private PersonalGoalCard personalGoalCard;
    private CardGenerator cardGenerator;



    /**
     * Contructor: This method creates a player, with a name and a personal goal card
     * @param name The name of the player
     * @param cardGenerator The game card generator
     */
    public Player(String name, CardGenerator cardGenerator) {
        this.cardGenerator = cardGenerator;
        this.name = name;
        this.points = 0;
        this.library = new Library();
        this.personalGoalCard = cardGenerator.generatePersonalGoalCard();
    }




    /**
     * @return The name of the player
     */
    public String getName() {
        return name;
    }




    /**
     * @return The points of the player
     */
    public int getPoints() {
        return points;
    }




    /**
     * @return The personal goal card of the player
     */
    public PersonalGoalCard getPersonalGoalCard() {
        return personalGoalCard;
    }





    /**
     * @return The library of the player
     */
    public Library getLibrary() {
        return library;
    }





    /**
     * This method is used to add points to the player
     * @param points the amount of points to add
     */
    public void addPoints(int points) {
        this.points+=points;
    }




    /**
     * @return The amount of points realized by the personal goal card and by the library adolescences in the player's library.
     */
    public int countFinalPoints() {
        int countPersonalGoalCardPoints = this.personalGoalCard.countPersonalGoalCardPoints(library);
        int countLibraryAdjacentPoints = library.countAdjacentPoints();
        int final_points = points + countPersonalGoalCardPoints + countLibraryAdjacentPoints;
        return final_points;
    }




    /**
     * @return The personal goal card ID of the player
     */
    public int getPersonalCardId(){
        return personalGoalCard.getCardId();
    }
}
