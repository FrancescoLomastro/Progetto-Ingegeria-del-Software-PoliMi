package it.polimi.ingsw.model.Cards;

public class ScorePointCard {
    private int score;

    private final int numberOfPlayer;

    /**Constructor
     * @author Riccardo Figini
     * @param numberOfPlayer number of player*/
    public ScorePointCard(int numberOfPlayer){
        this.numberOfPlayer=numberOfPlayer;
        this.score=8;
    }
    /**Methods that decreases/change scorecard, called when players reach common goal
     * @author Riccardo Figini
     * */
    private void changeScore(){
        //TODO cambio dell'immagine
        switch (score){
            case 8:
                switch (numberOfPlayer) {
                    case 2 -> score = 4;
                    case 3, 4 -> score = 6;
                    default -> {
                    }
                }
                break;
            case 6: score = 4;break;
            case 4: score =2;break;
            case 2: score = 0;break;
            default:break;
        }
    }
    /**Method that return current common goal's point. Automatically decrease the
     * score of common goal card (calling changeScore)
     * @author Riccardo Figini
     * @return number score of card*/
    public int getScore() {
        int number = score;
        changeScore();
        return number;
    }
}
