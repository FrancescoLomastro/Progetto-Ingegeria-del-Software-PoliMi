package it.polimi.ingsw.model.Player;

import it.polimi.ingsw.model.Cards.*;
import it.polimi.ingsw.model.CardGenerator.*;

public class Player {
    private String name;
    private int points;
    private Library library;
    private PersonalGoalCard personalGoalCard;
    private CardGenerator cardGenerator;

    public Player(String name,CardGenerator cardGenerator) {
        this.cardGenerator=cardGenerator;
        this.name = name;
        this.points = 0;
        this.library = new Library(20,20); // Ipotizzo dimensione 20x20 al momento, da valutare insiem
        personalGoalCard = generatePersonalGoalCard(); // non so se ha senso chiamare un metodo per generare (come da UML) o istanziare direttamente la carta qui
    }

    public String getName() {
        return name;
    }

    public int getPoints() {
        return points;
    }

    public PersonalGoalCard getPersonalGoalCard() {
        return personalGoalCard;
    }

    public Library getLibrary() {
        return library;
    }

    public void addPoints(int points) {
        this.points+=points;
    }

    public int countFinalPoints() {
        // count points comparing the personal goal card configuration with the final library disposition
        int countPersonalGoalCardPoints = this.personalGoalCard.countPersonalGoalCardPoints(getLibrary());

        // count points counting adjacent cards of the same color in the final library configuration
        int countLibraryAdjacentPoints = library.countAdjacentPoints();

        int final_points = points + countPersonalGoalCardPoints + countLibraryAdjacentPoints;

        return final_points;
    }

    private PersonalGoalCard generatePersonalGoalCard() {
        return cardGenerator.generatePersonalGoalCard();
    }

    public boolean isCommonGoalCardSatisfied(CommonGoalCard commonGoalCard) {
        return commonGoalCard.isSatisfied(getLibrary()); // da verificare
    }
}
