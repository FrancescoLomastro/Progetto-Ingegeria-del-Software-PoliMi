package it.polimi.ingsw.model;

public class Player {
    private String name;
    private int points;
    private Library library;
    private PersonalGoalCard personalGoalCard;
    public Player(String name) {
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
        int countPersonalGoalCardPoints = this.personalGoalCard.countPersonalCardPoints(getLibrary());

        // count points counting adjacent cards of the same color in the final library configuration
        int countLibraryAdjacentPoints = library.countAdjacentPoints();

        int final_points = points + countPersonalGoalCardPoints + countLibraryAdjacentPoints;

        return final_points;
    }

    private PersonalGoalCard generatePersonalGoalCard() {
        Card.casualGenerationOfNumber()
        this.personalGoalCard = new PersonalGoalCard("Ciao",10,10); // da valutare poi come creare effettivamente sta carta
    }
}
