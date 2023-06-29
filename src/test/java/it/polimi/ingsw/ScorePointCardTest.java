package it.polimi.ingsw;

import it.polimi.ingsw.model.Cards.ScorePointCard;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ScorePointCardTest {
    ScorePointCard scorePointCard4Players;
    ScorePointCard scorePointCard3Players;
    ScorePointCard scorePointCard2Players;
    public ScorePointCardTest(){
        scorePointCard4Players = new ScorePointCard(4);
        scorePointCard3Players = new ScorePointCard(3);
        scorePointCard2Players = new ScorePointCard(2);
    }

    @Before
    public void setUp(){

    }

    /**
     * Creates a scorePointCard for each number of players possible, and tests its decrease
     */
    @Test
    public void getScoreWithDecrease__correctOutput(){
        Assert.assertEquals(scorePointCard4Players.getScoreWithDecrease(), 8);
        Assert.assertEquals(scorePointCard4Players.getScoreWithDecrease(), 6);
        Assert.assertEquals(scorePointCard4Players.getScoreWithDecrease(), 4);
        Assert.assertEquals(scorePointCard4Players.getScoreWithDecrease(), 2);
        Assert.assertEquals(scorePointCard3Players.getScoreWithDecrease(), 8);
        Assert.assertEquals(scorePointCard3Players.getScoreWithDecrease(), 6);
        Assert.assertEquals(scorePointCard3Players.getScoreWithDecrease(), 4);
        Assert.assertEquals(scorePointCard2Players.getScoreWithDecrease(), 8);
        Assert.assertEquals(scorePointCard2Players.getScoreWithDecrease(), 4);
        Assert.assertEquals(scorePointCard4Players.getScoreWithDecrease(), 0);
        Assert.assertEquals(scorePointCard3Players.getScoreWithDecrease(), 0);
        Assert.assertEquals(scorePointCard2Players.getScoreWithDecrease(), 0);
    }

    @After
    public void tearDown(){

    }
}
