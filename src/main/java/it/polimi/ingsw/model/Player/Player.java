package it.polimi.ingsw.model.Player;

import it.polimi.ingsw.model.CardGenerator.CardGenerator;
import it.polimi.ingsw.model.Cards.CommonGoalCard;
import it.polimi.ingsw.model.Cards.PersonalGoalCard;

import java.io.Serializable;

public class Player implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private int points;
    private Library library;
    private PersonalGoalCard personalGoalCard;
    private CardGenerator cardGenerator;

    public Player(String name, CardGenerator cardGenerator) {
        this.cardGenerator = cardGenerator;
        this.name = name;
        this.points = 0;
        this.library = new Library();
        this.personalGoalCard = cardGenerator.generatePersonalGoalCard();
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
        int countPersonalGoalCardPoints = this.personalGoalCard.countPersonalGoalCardPoints(library);
        int countLibraryAdjacentPoints = library.countAdjacentPoints();
        int final_points = points + countPersonalGoalCardPoints + countLibraryAdjacentPoints;
        return final_points;
    }

    public boolean isCommonGoalCardSatisfied(CommonGoalCard commonGoalCard) {
        return commonGoalCard.isSatisfied(getLibrary());
    }

    public int getNumPersonalGoal(){
        return personalGoalCard.getCardId();
    }
}
