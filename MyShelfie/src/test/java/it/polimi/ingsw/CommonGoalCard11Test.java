package it.polimi.ingsw;


import it.polimi.ingsw.model.Cards.CommonGoalCard;
import it.polimi.ingsw.model.Cards.ConcreteCommonCards.*;
import it.polimi.ingsw.model.Cards.*;
import it.polimi.ingsw.model.Enums.*;
import it.polimi.ingsw.model.Player.*;
import it.polimi.ingsw.model.Enums.Type;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * @author: Alberto Aniballi
 */
public class CommonGoalCard11Test {

    CommonGoalCard commonGoalCard;
    //Type never affects this algorithm
    Type type = Type.FIRST;

    Library library;
    /**Constructor
     * @author: Riccardo Figini
     * @throws IOException throws an exception*/
    public CommonGoalCard11Test() throws IOException {
        commonGoalCard = new CommonGoalCard11();

    }

    /**Library's set up with null
     * @author: Riccardo Figini
     * */
    @Before
    public void setUp(){
        library = new Library();
    }


    /**
     * Second Test Increasing: 1,2,3,4,5 -> answer true.
     * @author: Alberto Aniballi
     * */
    @Test
    public void isSatisfied_IncreasingDisposition_trueInOutput(){
        library.insertCardsInLibrary(0, new ObjectCard("", Color.PINK , Type.FIRST));

        library.insertCardsInLibrary(1, new ObjectCard("", Color.PINK , Type.FIRST));
        library.insertCardsInLibrary(1, new ObjectCard("", Color.BLUE , Type.FIRST));

        library.insertCardsInLibrary(2, new ObjectCard("", Color.GREEN , Type.FIRST));
        library.insertCardsInLibrary(2, new ObjectCard("", Color.PINK , Type.FIRST));
        library.insertCardsInLibrary(2, new ObjectCard("", Color.YELLOW , Type.FIRST));

        library.insertCardsInLibrary(3, new ObjectCard("", Color.YELLOW , Type.FIRST));
        library.insertCardsInLibrary(3, new ObjectCard("", Color.BLUE , Type.FIRST));
        library.insertCardsInLibrary(3, new ObjectCard("", Color.YELLOW , Type.FIRST));
        library.insertCardsInLibrary(3, new ObjectCard("", Color.BLUE , Type.FIRST));

        library.insertCardsInLibrary(4, new ObjectCard("", Color.YELLOW , Type.FIRST));
        library.insertCardsInLibrary(4, new ObjectCard("", Color.BLUE , Type.FIRST));
        library.insertCardsInLibrary(4, new ObjectCard("", Color.YELLOW , Type.FIRST));
        library.insertCardsInLibrary(4, new ObjectCard("", Color.BLUE , Type.FIRST));
        library.insertCardsInLibrary(4, new ObjectCard("", Color.PINK , Type.FIRST));

        Assert.assertTrue(commonGoalCard.isSatisfied(library));
    }

    @Test
    public void isSatisfied_DecreasingDisposition_trueInOutput(){
        library.insertCardsInLibrary(4, new ObjectCard("", Color.PINK , Type.FIRST));

        library.insertCardsInLibrary(3, new ObjectCard("", Color.PINK , Type.FIRST));
        library.insertCardsInLibrary(3, new ObjectCard("", Color.BLUE , Type.FIRST));

        library.insertCardsInLibrary(2, new ObjectCard("", Color.GREEN , Type.FIRST));
        library.insertCardsInLibrary(2, new ObjectCard("", Color.PINK , Type.FIRST));
        library.insertCardsInLibrary(2, new ObjectCard("", Color.YELLOW , Type.FIRST));

        library.insertCardsInLibrary(1, new ObjectCard("", Color.YELLOW , Type.FIRST));
        library.insertCardsInLibrary(1, new ObjectCard("", Color.BLUE , Type.FIRST));
        library.insertCardsInLibrary(1, new ObjectCard("", Color.YELLOW , Type.FIRST));
        library.insertCardsInLibrary(1, new ObjectCard("", Color.BLUE , Type.FIRST));

        library.insertCardsInLibrary(0, new ObjectCard("", Color.YELLOW , Type.FIRST));
        library.insertCardsInLibrary(0, new ObjectCard("", Color.BLUE , Type.FIRST));
        library.insertCardsInLibrary(0, new ObjectCard("", Color.YELLOW , Type.FIRST));
        library.insertCardsInLibrary(0, new ObjectCard("", Color.BLUE , Type.FIRST));
        library.insertCardsInLibrary(0, new ObjectCard("", Color.PINK , Type.FIRST));

        Assert.assertTrue(commonGoalCard.isSatisfied(library));
    }
    @Test
    public void isSatisfied_DecreasingDispositionWithAnEmptyColumn_falseInOutput(){


        library.insertCardsInLibrary(3, new ObjectCard("", Color.PINK , Type.FIRST));
        library.insertCardsInLibrary(3, new ObjectCard("", Color.BLUE , Type.FIRST));

        library.insertCardsInLibrary(2, new ObjectCard("", Color.GREEN , Type.FIRST));
        library.insertCardsInLibrary(2, new ObjectCard("", Color.PINK , Type.FIRST));
        library.insertCardsInLibrary(2, new ObjectCard("", Color.YELLOW , Type.FIRST));

        library.insertCardsInLibrary(1, new ObjectCard("", Color.YELLOW , Type.FIRST));
        library.insertCardsInLibrary(1, new ObjectCard("", Color.BLUE , Type.FIRST));
        library.insertCardsInLibrary(1, new ObjectCard("", Color.YELLOW , Type.FIRST));
        library.insertCardsInLibrary(1, new ObjectCard("", Color.BLUE , Type.FIRST));

        library.insertCardsInLibrary(0, new ObjectCard("", Color.YELLOW , Type.FIRST));
        library.insertCardsInLibrary(0, new ObjectCard("", Color.BLUE , Type.FIRST));
        library.insertCardsInLibrary(0, new ObjectCard("", Color.YELLOW , Type.FIRST));
        library.insertCardsInLibrary(0, new ObjectCard("", Color.BLUE , Type.FIRST));
        library.insertCardsInLibrary(0, new ObjectCard("", Color.PINK , Type.FIRST));

        Assert.assertFalse(commonGoalCard.isSatisfied(library));
    }
    @Test
    public void isSatisfied_IncreasingDispositionWithAnEmptyColumn_falseInOutput(){

        library.insertCardsInLibrary(1, new ObjectCard("", Color.PINK , Type.FIRST));
        library.insertCardsInLibrary(1, new ObjectCard("", Color.BLUE , Type.FIRST));

        library.insertCardsInLibrary(2, new ObjectCard("", Color.GREEN , Type.FIRST));
        library.insertCardsInLibrary(2, new ObjectCard("", Color.PINK , Type.FIRST));
        library.insertCardsInLibrary(2, new ObjectCard("", Color.YELLOW , Type.FIRST));

        library.insertCardsInLibrary(3, new ObjectCard("", Color.YELLOW , Type.FIRST));
        library.insertCardsInLibrary(3, new ObjectCard("", Color.BLUE , Type.FIRST));
        library.insertCardsInLibrary(3, new ObjectCard("", Color.YELLOW , Type.FIRST));
        library.insertCardsInLibrary(3, new ObjectCard("", Color.BLUE , Type.FIRST));

        library.insertCardsInLibrary(4, new ObjectCard("", Color.YELLOW , Type.FIRST));
        library.insertCardsInLibrary(4, new ObjectCard("", Color.BLUE , Type.FIRST));
        library.insertCardsInLibrary(4, new ObjectCard("", Color.YELLOW , Type.FIRST));
        library.insertCardsInLibrary(4, new ObjectCard("", Color.BLUE , Type.FIRST));
        library.insertCardsInLibrary(4, new ObjectCard("", Color.PINK , Type.FIRST));

        Assert.assertFalse(commonGoalCard.isSatisfied(library));
    }
@Test
    public void isSatisfied_IncreasingDispositionNotFromStart_falseInOutput(){

        library.insertCardsInLibrary(1, new ObjectCard("", Color.BLUE , Type.FIRST));

        library.insertCardsInLibrary(2, new ObjectCard("", Color.PINK , Type.FIRST));
        library.insertCardsInLibrary(2, new ObjectCard("", Color.YELLOW , Type.FIRST));

        library.insertCardsInLibrary(3, new ObjectCard("", Color.BLUE , Type.FIRST));
        library.insertCardsInLibrary(3, new ObjectCard("", Color.YELLOW , Type.FIRST));
        library.insertCardsInLibrary(3, new ObjectCard("", Color.BLUE , Type.FIRST));

        library.insertCardsInLibrary(4, new ObjectCard("", Color.BLUE , Type.FIRST));
        library.insertCardsInLibrary(4, new ObjectCard("", Color.YELLOW , Type.FIRST));
        library.insertCardsInLibrary(4, new ObjectCard("", Color.BLUE , Type.FIRST));
        library.insertCardsInLibrary(4, new ObjectCard("", Color.PINK , Type.FIRST));

        Assert.assertFalse(commonGoalCard.isSatisfied(library));
    }
    @Test
    public void isSatisfied_DecreasingDispositionNotFromStart_falseInOutput(){

        library.insertCardsInLibrary(3, new ObjectCard("", Color.BLUE , Type.FIRST));

        library.insertCardsInLibrary(2, new ObjectCard("", Color.PINK , Type.FIRST));
        library.insertCardsInLibrary(2, new ObjectCard("", Color.YELLOW , Type.FIRST));

        library.insertCardsInLibrary(1, new ObjectCard("", Color.BLUE , Type.FIRST));
        library.insertCardsInLibrary(1, new ObjectCard("", Color.YELLOW , Type.FIRST));
        library.insertCardsInLibrary(1, new ObjectCard("", Color.BLUE , Type.FIRST));

        library.insertCardsInLibrary(0, new ObjectCard("", Color.BLUE , Type.FIRST));
        library.insertCardsInLibrary(0, new ObjectCard("", Color.YELLOW , Type.FIRST));
        library.insertCardsInLibrary(0, new ObjectCard("", Color.BLUE , Type.FIRST));
        library.insertCardsInLibrary(0, new ObjectCard("", Color.PINK , Type.FIRST));

        Assert.assertFalse(commonGoalCard.isSatisfied(library));
    }

    @Test
    public void isSatisfied_IncreasingDispositionWithABadColumn_trueInOutput(){
        library.insertCardsInLibrary(0, new ObjectCard("", Color.PINK , Type.FIRST));

        library.insertCardsInLibrary(1, new ObjectCard("", Color.PINK , Type.FIRST));
        library.insertCardsInLibrary(1, new ObjectCard("", Color.BLUE , Type.FIRST));

        library.insertCardsInLibrary(2, new ObjectCard("", Color.GREEN , Type.FIRST));
        library.insertCardsInLibrary(2, new ObjectCard("", Color.PINK , Type.FIRST));
        library.insertCardsInLibrary(2, new ObjectCard("", Color.YELLOW , Type.FIRST));

        library.insertCardsInLibrary(3, new ObjectCard("", Color.YELLOW , Type.FIRST));
        library.insertCardsInLibrary(3, new ObjectCard("", Color.BLUE , Type.FIRST));
        library.insertCardsInLibrary(3, new ObjectCard("", Color.YELLOW , Type.FIRST));
        library.insertCardsInLibrary(3, new ObjectCard("", Color.BLUE , Type.FIRST));
        library.insertCardsInLibrary(3, new ObjectCard("", Color.BLUE , Type.FIRST));

        library.insertCardsInLibrary(4, new ObjectCard("", Color.YELLOW , Type.FIRST));
        library.insertCardsInLibrary(4, new ObjectCard("", Color.BLUE , Type.FIRST));
        library.insertCardsInLibrary(4, new ObjectCard("", Color.YELLOW , Type.FIRST));
        library.insertCardsInLibrary(4, new ObjectCard("", Color.BLUE , Type.FIRST));
        library.insertCardsInLibrary(4, new ObjectCard("", Color.PINK , Type.FIRST));

        Assert.assertFalse(commonGoalCard.isSatisfied(library));
    }

    @Test
    public void isSatisfied_DecreasingDispositionWithABadColumn_trueInOutput(){
        library.insertCardsInLibrary(4, new ObjectCard("", Color.PINK , Type.FIRST));

        library.insertCardsInLibrary(3, new ObjectCard("", Color.PINK , Type.FIRST));
        library.insertCardsInLibrary(3, new ObjectCard("", Color.BLUE , Type.FIRST));

        library.insertCardsInLibrary(2, new ObjectCard("", Color.GREEN , Type.FIRST));
        library.insertCardsInLibrary(2, new ObjectCard("", Color.PINK , Type.FIRST));
        library.insertCardsInLibrary(2, new ObjectCard("", Color.YELLOW , Type.FIRST));

        library.insertCardsInLibrary(1, new ObjectCard("", Color.YELLOW , Type.FIRST));
        library.insertCardsInLibrary(1, new ObjectCard("", Color.BLUE , Type.FIRST));
        library.insertCardsInLibrary(1, new ObjectCard("", Color.YELLOW , Type.FIRST));
        library.insertCardsInLibrary(1, new ObjectCard("", Color.BLUE , Type.FIRST));
        library.insertCardsInLibrary(1, new ObjectCard("", Color.BLUE , Type.FIRST));

        library.insertCardsInLibrary(0, new ObjectCard("", Color.YELLOW , Type.FIRST));
        library.insertCardsInLibrary(0, new ObjectCard("", Color.BLUE , Type.FIRST));
        library.insertCardsInLibrary(0, new ObjectCard("", Color.YELLOW , Type.FIRST));
        library.insertCardsInLibrary(0, new ObjectCard("", Color.BLUE , Type.FIRST));
        library.insertCardsInLibrary(0, new ObjectCard("", Color.PINK , Type.FIRST));

        Assert.assertFalse(commonGoalCard.isSatisfied(library));
    }

    /**
     * Ninth Test: all rows are empty.
     * @author: Alberto Aniballi
     * */
    @Test
    public void isSatisfied_correctInputAllColumnsEmpty_falseInOutput(){
        Assert.assertFalse(commonGoalCard.isSatisfied(library));
    }

    /**
     * Tenth Test: all rows full random.
     * @author: Alberto Aniballi
     * */
    @Test
    public void isSatisfied_RandomInputAllRows_falseInOutput(){
        library.insertCardsInLibrary(0,new ObjectCard("",Color.PINK,Type.FIRST));
        library.insertCardsInLibrary(0,new ObjectCard("",Color.BLUE,Type.FIRST));
        library.insertCardsInLibrary(0,new ObjectCard("",Color.PINK,Type.FIRST));
        library.insertCardsInLibrary(0,new ObjectCard("",Color.BLUE,Type.FIRST));
        library.insertCardsInLibrary(0,new ObjectCard("",Color.WHITE,Type.FIRST));
        library.insertCardsInLibrary(0,new ObjectCard("",Color.WHITE,Type.FIRST));

        library.insertCardsInLibrary(1,new ObjectCard("",Color.BLUE,Type.FIRST));
        library.insertCardsInLibrary(1,new ObjectCard("",Color.PINK,Type.FIRST));
        library.insertCardsInLibrary(1,new ObjectCard("",Color.WHITE,Type.FIRST));
        library.insertCardsInLibrary(1,new ObjectCard("",Color.WHITE,Type.FIRST));
        library.insertCardsInLibrary(1,new ObjectCard("",Color.BLUE,Type.FIRST));
        library.insertCardsInLibrary(1,new ObjectCard("",Color.PINK,Type.FIRST));

        library.insertCardsInLibrary(2,new ObjectCard("",Color.WHITE,Type.FIRST));
        library.insertCardsInLibrary(2,new ObjectCard("",Color.WHITE,Type.FIRST));
        library.insertCardsInLibrary(2,new ObjectCard("",Color.PINK,Type.FIRST));
        library.insertCardsInLibrary(2,new ObjectCard("",Color.BLUE,Type.FIRST));
        library.insertCardsInLibrary(2,new ObjectCard("",Color.WHITE,Type.FIRST));
        library.insertCardsInLibrary(2,new ObjectCard("",Color.WHITE,Type.FIRST));

        library.insertCardsInLibrary(3,new ObjectCard("",Color.BLUE,Type.FIRST));
        library.insertCardsInLibrary(3,new ObjectCard("",Color.PINK,Type.FIRST));
        library.insertCardsInLibrary(3,new ObjectCard("",Color.WHITE,Type.FIRST));
        library.insertCardsInLibrary(3,new ObjectCard("",Color.WHITE,Type.FIRST));
        library.insertCardsInLibrary(3,new ObjectCard("",Color.BLUE,Type.FIRST));
        library.insertCardsInLibrary(3,new ObjectCard("",Color.PINK,Type.FIRST));

        library.insertCardsInLibrary(4,new ObjectCard("",Color.PINK,Type.FIRST));
        library.insertCardsInLibrary(4,new ObjectCard("",Color.BLUE,Type.FIRST));
        library.insertCardsInLibrary(4,new ObjectCard("",Color.PINK,Type.FIRST));
        library.insertCardsInLibrary(4,new ObjectCard("",Color.BLUE,Type.FIRST));
        library.insertCardsInLibrary(4,new ObjectCard("",Color.WHITE,Type.FIRST));
        library.insertCardsInLibrary(4,new ObjectCard("",Color.WHITE,Type.FIRST));
        Assert.assertFalse(commonGoalCard.isSatisfied(library));
    }
}
