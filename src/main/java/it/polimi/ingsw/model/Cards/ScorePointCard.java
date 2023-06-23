package it.polimi.ingsw.model.Cards;

import java.io.Serializable;

public class ScorePointCard implements Serializable {
    private static final long serialVersionUID = 1L;
    private int score;
    private final int numberOfPlayer;

    /**Constructor
     * @author Riccardo Figini
     * @param numberOfPlayer number of player*/
    public ScorePointCard(int numberOfPlayer){
        this.numberOfPlayer=numberOfPlayer;
        this.score=8;
    }

    /**Methods that decrease/change scorecard, called when players reach common goal
     * @author Riccardo Figini
     * */
    private void changeScore(){
        switch (score) {
            case 8 -> {
                switch (numberOfPlayer) {
                    case 2 -> score = 4;
                    case 3, 4 -> score = 6;
                    default -> {
                    }
                }
            }
            case 6 -> score = 4;
            case 4 -> {
                if (numberOfPlayer == 2 || numberOfPlayer == 3)
                    score = 0;
                else
                    score = 2;
            }
            case 2 -> score = 0;
            default -> {
            }
        }
    }
    /**
     * Method that returns the current common goal's point. Automatically decrease the
     * score of common goal card (calling changeScore)
     * @author Riccardo Figini
     * @return {@code int}: It returns point made with common goal card*/
    public int getScoreWithDecrease() {
        int number = score;
        changeScore();
        return number;
    }

    public int getScore() {
        return score;
    }
}
