package it.polimi.ingsw.model.Cards;

import it.polimi.ingsw.model.Player.*;

public abstract class CommonGoalCard extends Card {
    private ScorePointCard scorePointCard;
    /**Constructor
     * @author Riccardo Figini
     * */

    public CommonGoalCard(){
        super();
    }
    /**Abstract function. Each subclass implements code to verify if
     * someone has reached the common goal
     * @author Riccardo Figini
     * @param lib player's library
     * @return boolean: true if the library respect common goal features */
    public abstract boolean isSatisfied(Library lib);

    public void setScorePointCard(ScorePointCard scorePointCard) {
        this.scorePointCard = scorePointCard;
    }

    /**This method get current common goal point
     * @author Riccardo Figini
     * @return scorePointCard.getScore() int score point*/
    public int getPoints(){
        return scorePointCard.getScore();
    }


    public int getScoreWithDecrease(){

        return scorePointCard.getScoreWithDecrease();
    }

}