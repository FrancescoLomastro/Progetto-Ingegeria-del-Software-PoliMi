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
public class CommonGoalCard10Test {
    CommonGoalCard commonGoalCard;
    Library library;
    /**Constructor
     * @author: Riccardo Figini
     * @throws IOException throws an exception*/
    public CommonGoalCard10Test() throws IOException {
        commonGoalCard = new CommonGoalCard10();

    }

    /**Library's set up with null
     * @author: Riccardo Figini
     * */
    @Before
    public void setUp(){
        library = new Library();
    }


    /**
     * First Test: 8 object cards of the same type and the other cells are empty.
     * @author: Alberto Aniballi
     * */
    @Test
    public void isSatisfied_oneCorrectInputOtherCellsEmpty_trueInOutput(){
        library.insertCardsInLibrary(0,new ObjectCard("",Color.PINK,Type.FIRST));
        library.insertCardsInLibrary(0,new ObjectCard("",Color.PINK,Type.FIRST));
        library.insertCardsInLibrary(0,new ObjectCard("",Color.PINK,Type.FIRST));

        library.insertCardsInLibrary(1,new ObjectCard("",Color.PINK,Type.FIRST));
        library.insertCardsInLibrary(1,new ObjectCard("",Color.PINK,Type.FIRST));

        library.insertCardsInLibrary(2,new ObjectCard("",Color.PINK,Type.FIRST));
        library.insertCardsInLibrary(2,new ObjectCard("",Color.PINK,Type.FIRST));
        library.insertCardsInLibrary(2,new ObjectCard("",Color.PINK,Type.FIRST));
        Assert.assertTrue(commonGoalCard.isSatisfied(library));
    }

    /**
     * Second Test: 8 object cards of the same type and the other cells are filled with random filler.
     * @author: Alberto Aniballi
     * */
    @Test
    public void isSatisfied_oneCorrectInputOtherCellsRandom_trueInOutput(){
        library.insertCardsInLibrary(0,new ObjectCard("",Color.PINK,Type.FIRST));
        library.insertCardsInLibrary(0,new ObjectCard("",Color.PINK,Type.FIRST));
        library.insertCardsInLibrary(0,new ObjectCard("",Color.PINK,Type.FIRST));

        library.insertCardsInLibrary(1,new ObjectCard("",Color.PINK,Type.FIRST));
        library.insertCardsInLibrary(1,new ObjectCard("",Color.PINK,Type.FIRST));

        library.insertCardsInLibrary(2,new ObjectCard("",Color.PINK,Type.FIRST));
        library.insertCardsInLibrary(2,new ObjectCard("",Color.PINK,Type.FIRST));
        library.insertCardsInLibrary(2,new ObjectCard("",Color.PINK,Type.FIRST));

        library.insertCardsInLibrary(0,new ObjectCard("",Color.WHITE,Type.FIRST));
        library.insertCardsInLibrary(0,new ObjectCard("",Color.YELLOW,Type.FIRST));
        library.insertCardsInLibrary(0,new ObjectCard("",Color.WHITE,Type.FIRST));

        library.insertCardsInLibrary(1,new ObjectCard("",Color.WHITE,Type.FIRST));
        library.insertCardsInLibrary(1,new ObjectCard("",Color.YELLOW,Type.FIRST));
        library.insertCardsInLibrary(1,new ObjectCard("",Color.BLUE,Type.FIRST));
        library.insertCardsInLibrary(1,new ObjectCard("",Color.YELLOW,Type.FIRST));

        library.insertCardsInLibrary(2,new ObjectCard("",Color.WHITE,Type.FIRST));
        library.insertCardsInLibrary(2,new ObjectCard("",Color.YELLOW,Type.FIRST));
        library.insertCardsInLibrary(2,new ObjectCard("",Color.WHITE,Type.FIRST));

        library.insertCardsInLibrary(3,new ObjectCard("",Color.BLUE,Type.FIRST));
        library.insertCardsInLibrary(3,new ObjectCard("",Color.GREEN,Type.FIRST));
        library.insertCardsInLibrary(3,new ObjectCard("",Color.BLUE,Type.FIRST));
        library.insertCardsInLibrary(3,new ObjectCard("",Color.GREEN,Type.FIRST));
        library.insertCardsInLibrary(3,new ObjectCard("",Color.BLUE,Type.FIRST));
        library.insertCardsInLibrary(3,new ObjectCard("",Color.GREEN,Type.FIRST));

        library.insertCardsInLibrary(4,new ObjectCard("",Color.BLUE,Type.FIRST));
        library.insertCardsInLibrary(4,new ObjectCard("",Color.GREEN,Type.FIRST));
        library.insertCardsInLibrary(4,new ObjectCard("",Color.BLUE,Type.FIRST));
        library.insertCardsInLibrary(4,new ObjectCard("",Color.GREEN,Type.FIRST));
        library.insertCardsInLibrary(4,new ObjectCard("",Color.BLUE,Type.FIRST));
        library.insertCardsInLibrary(4,new ObjectCard("",Color.GREEN,Type.FIRST));
        library.stampa();
        Assert.assertTrue(commonGoalCard.isSatisfied(library));
    }

    /**
     * Third Test: seven object cards of the same type and others are empty, so the condition is not satisfied.
     * @author: Alberto Aniballi
     * */
    @Test
    public void isSatisfied_noCorrectInput_falseInOutput(){
        library.insertCardsInLibrary(0,new ObjectCard("",Color.PINK,Type.FIRST));
        library.insertCardsInLibrary(0,new ObjectCard("",Color.PINK,Type.FIRST));
        library.insertCardsInLibrary(0,new ObjectCard("",Color.PINK,Type.FIRST));

        library.insertCardsInLibrary(1,new ObjectCard("",Color.PINK,Type.FIRST));
        library.insertCardsInLibrary(1,new ObjectCard("",Color.PINK,Type.FIRST));

        library.insertCardsInLibrary(2,new ObjectCard("",Color.PINK,Type.FIRST));
        library.insertCardsInLibrary(2,new ObjectCard("",Color.PINK,Type.FIRST));
        Assert.assertFalse(commonGoalCard.isSatisfied(library));
    }

    /**
     * Fourth Test: all rows are empty.
     * @author: Alberto Aniballi
     * */
    @Test
    public void isSatisfied_correctInputAllColumnsEmpty_falseInOutput(){
        Assert.assertFalse(commonGoalCard.isSatisfied(library));
    }
}
