package it.polimi.ingsw.model.Cards;

import it.polimi.ingsw.model.Player.*;

import java.io.Serializable;

public abstract class CommonGoalCard extends Card implements Serializable {
    private static final long serialVersionUID = 1L;
    protected int num;
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
    public int getCardId() {
        return num;
    }
}