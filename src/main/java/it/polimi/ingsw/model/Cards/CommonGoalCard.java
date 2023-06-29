package it.polimi.ingsw.model.Cards;

import it.polimi.ingsw.model.Player.*;

import java.io.Serial;
import java.io.Serializable;

public abstract class CommonGoalCard extends Card implements Serializable {
    @Serial
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
     * @return boolean: true if the library respects common goal features */
    public abstract boolean isSatisfied(Library lib);
    /**It sets score point card after creation because it needs number of players, during
     * creation card does not have it
     * @author: Riccardo Figini
     * @param scorePointCard Score point card with proper number of players
     * */
    public void setScorePointCard(ScorePointCard scorePointCard) {
        this.scorePointCard = scorePointCard;
    }

    /**This method get current common goal point
     * @author Riccardo Figini
     * @author: Andrea Ferrini
     * @return scorePointCard.getScore() int score point*/
    public int getPoints(){
        return scorePointCard.getScore();
    }


    /**It returns the points available, then decrease it according to rules based
     *  on the number of players
     * @author: Andrea ferrini
     * @return int point
     * */
    public int getScoreWithDecrease(){

        return scorePointCard.getScoreWithDecrease();
    }
    /**It returns the num of card
     * @author: Francesco Lo Mastro*/
    public int getCardId() {
        return num;
    }
}