package it.polimi.ingsw;

import it.polimi.ingsw.enums.Color;
import it.polimi.ingsw.model.Cards.CommonGoalCard;
import it.polimi.ingsw.model.Cards.ConcreteCommonCards.*;
import it.polimi.ingsw.model.Cards.*;
import it.polimi.ingsw.model.Player.*;
import it.polimi.ingsw.enums.Type;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

/**
 * @author: Alberto Aniballi
 */
public class CommonGoalCard7Test {

    CommonGoalCard commonGoalCard;
    Library library;
    /**Constructor
     * @author: Riccardo Figini
     * @throws IOException throws an exception*/
    public CommonGoalCard7Test() throws IOException {
        commonGoalCard = new CommonGoalCard7();
    }

    /**Library's set up with null
     * @author: Riccardo Figini
     * */
    @Before
    public void setUp(){
        library =  new Library();
    }
    /**
     * First Test: two rows with 5 different types and the other cells are all empty.
     * @author: Alberto Aniballi
     * */
    @Test
    public void isSatisfied_correctInputOnlyTwoRows_trueInOutput(){
        assertDoesNotThrow(()->{
        library.insertCardsInLibrary(0,new ObjectCard("", Color.WHITE,Type.FIRST));
        library.insertCardsInLibrary(1,new ObjectCard("",Color.BLUE,Type.FIRST));
        library.insertCardsInLibrary(2,new ObjectCard("",Color.GREEN,Type.FIRST));
        library.insertCardsInLibrary(3,new ObjectCard("",Color.PINK,Type.FIRST));
        library.insertCardsInLibrary(4,new ObjectCard("",Color.LIGHTBLUE,Type.FIRST));

        library.insertCardsInLibrary(0,new ObjectCard("",Color.BLUE,Type.FIRST));
        library.insertCardsInLibrary(1,new ObjectCard("",Color.BLUE,Type.FIRST));
        library.insertCardsInLibrary(2,new ObjectCard("",Color.BLUE,Type.FIRST));
        library.insertCardsInLibrary(3,new ObjectCard("",Color.BLUE,Type.FIRST));
        library.insertCardsInLibrary(4,new ObjectCard("",Color.BLUE,Type.FIRST));

        library.insertCardsInLibrary(0,new ObjectCard("",Color.LIGHTBLUE,Type.FIRST));
        library.insertCardsInLibrary(1,new ObjectCard("",Color.BLUE,Type.FIRST));
        library.insertCardsInLibrary(2,new ObjectCard("",Color.GREEN,Type.FIRST));
        library.insertCardsInLibrary(3,new ObjectCard("",Color.PINK,Type.FIRST));
        library.insertCardsInLibrary(4,new ObjectCard("",Color.WHITE,Type.FIRST));
        Assert.assertTrue(commonGoalCard.isSatisfied(library));});
    }

    /**
     * Second Test: two rows with 5 different types and the other cells are all filled with objectCards.
     * @author: Alberto Aniballi
     * */
    @Test
    public void isSatisfied_correctInputOnlyTwoRowsOtherRowsRandom_trueInOutput(){
        assertDoesNotThrow(()->{
        library.insertCardsInLibrary(0,new ObjectCard("",Color.BLUE,Type.FIRST));
        library.insertCardsInLibrary(1,new ObjectCard("",Color.GREEN,Type.FIRST));
        library.insertCardsInLibrary(2,new ObjectCard("",Color.WHITE,Type.FIRST));
        library.insertCardsInLibrary(3,new ObjectCard("",Color.LIGHTBLUE,Type.FIRST));
        library.insertCardsInLibrary(4,new ObjectCard("",Color.BLUE,Type.FIRST));

        library.insertCardsInLibrary(0,new ObjectCard("",Color.PINK,Type.FIRST));
        library.insertCardsInLibrary(1,new ObjectCard("",Color.BLUE,Type.FIRST));
        library.insertCardsInLibrary(2,new ObjectCard("",Color.GREEN,Type.FIRST));
        library.insertCardsInLibrary(3,new ObjectCard("",Color.WHITE,Type.FIRST));
        library.insertCardsInLibrary(4,new ObjectCard("",Color.GREEN,Type.FIRST));

        library.insertCardsInLibrary(0,new ObjectCard("",Color.PINK,Type.FIRST));
        library.insertCardsInLibrary(1,new ObjectCard("",Color.PINK,Type.FIRST));
        library.insertCardsInLibrary(2,new ObjectCard("",Color.BLUE,Type.FIRST));
        library.insertCardsInLibrary(3,new ObjectCard("",Color.BLUE,Type.FIRST));
        library.insertCardsInLibrary(4,new ObjectCard("",Color.BLUE,Type.FIRST));

        library.insertCardsInLibrary(0,new ObjectCard("",Color.BLUE,Type.FIRST));
        library.insertCardsInLibrary(1,new ObjectCard("",Color.BLUE,Type.FIRST));
        library.insertCardsInLibrary(2,new ObjectCard("",Color.BLUE,Type.FIRST));
        library.insertCardsInLibrary(3,new ObjectCard("",Color.BLUE,Type.FIRST));
        library.insertCardsInLibrary(4,new ObjectCard("",Color.BLUE,Type.FIRST));

        library.insertCardsInLibrary(0,new ObjectCard("",Color.BLUE,Type.FIRST));
        library.insertCardsInLibrary(1,new ObjectCard("",Color.LIGHTBLUE,Type.FIRST));
        library.insertCardsInLibrary(2,new ObjectCard("",Color.GREEN,Type.FIRST));
        library.insertCardsInLibrary(3,new ObjectCard("",Color.PINK,Type.FIRST));
        library.insertCardsInLibrary(4,new ObjectCard("",Color.WHITE,Type.FIRST));

        library.insertCardsInLibrary(0,new ObjectCard("",Color.LIGHTBLUE,Type.FIRST));
        library.insertCardsInLibrary(1,new ObjectCard("",Color.BLUE,Type.FIRST));
        library.insertCardsInLibrary(2,new ObjectCard("",Color.PINK,Type.FIRST));
        library.insertCardsInLibrary(3,new ObjectCard("",Color.YELLOW,Type.FIRST));
        library.insertCardsInLibrary(4,new ObjectCard("",Color.WHITE,Type.FIRST));
        Assert.assertTrue(commonGoalCard.isSatisfied(library));});
    }

    /**
     * Third Test: 5 rows have only 4 different type of objectCard so output is false.
     * @author: Alberto Aniballi
     * */
    @Test
    public void isSatisfied_correctInput5RowsWithFourTypes_falseInOutput(){
        assertDoesNotThrow(()->{
        library.insertCardsInLibrary(0,new ObjectCard("",Color.LIGHTBLUE,Type.FIRST));
        library.insertCardsInLibrary(1,new ObjectCard("",Color.PINK,Type.FIRST));
        library.insertCardsInLibrary(2,new ObjectCard("",Color.GREEN,Type.FIRST));
        library.insertCardsInLibrary(3,new ObjectCard("",Color.WHITE,Type.FIRST));
        library.insertCardsInLibrary(4,new ObjectCard("",Color.WHITE,Type.FIRST));

        library.insertCardsInLibrary(0,new ObjectCard("",Color.LIGHTBLUE,Type.FIRST));
        library.insertCardsInLibrary(1,new ObjectCard("",Color.PINK,Type.FIRST));
        library.insertCardsInLibrary(2,new ObjectCard("",Color.BLUE,Type.FIRST));
        library.insertCardsInLibrary(3,new ObjectCard("",Color.BLUE,Type.FIRST));
        library.insertCardsInLibrary(4,new ObjectCard("",Color.WHITE,Type.FIRST));

        library.insertCardsInLibrary(0,new ObjectCard("",Color.LIGHTBLUE,Type.FIRST));
        library.insertCardsInLibrary(1,new ObjectCard("",Color.GREEN,Type.FIRST));
        library.insertCardsInLibrary(2,new ObjectCard("",Color.GREEN,Type.FIRST));
        library.insertCardsInLibrary(3,new ObjectCard("",Color.BLUE,Type.FIRST));
        library.insertCardsInLibrary(4,new ObjectCard("",Color.WHITE,Type.FIRST));

        library.insertCardsInLibrary(0,new ObjectCard("",Color.PINK,Type.FIRST));
        library.insertCardsInLibrary(1,new ObjectCard("",Color.PINK,Type.FIRST));
        library.insertCardsInLibrary(2,new ObjectCard("",Color.GREEN,Type.FIRST));
        library.insertCardsInLibrary(3,new ObjectCard("",Color.BLUE,Type.FIRST));
        library.insertCardsInLibrary(4,new ObjectCard("",Color.WHITE,Type.FIRST));

        library.insertCardsInLibrary(0,new ObjectCard("",Color.BLUE,Type.FIRST));
        library.insertCardsInLibrary(1,new ObjectCard("",Color.LIGHTBLUE,Type.FIRST));
        library.insertCardsInLibrary(2,new ObjectCard("",Color.GREEN,Type.FIRST));
        library.insertCardsInLibrary(3,new ObjectCard("",Color.PINK,Type.FIRST));
        library.insertCardsInLibrary(4,new ObjectCard("",Color.WHITE,Type.FIRST));

        library.insertCardsInLibrary(0,new ObjectCard("",Color.LIGHTBLUE,Type.FIRST));
        library.insertCardsInLibrary(1,new ObjectCard("",Color.LIGHTBLUE,Type.FIRST));
        library.insertCardsInLibrary(2,new ObjectCard("",Color.GREEN,Type.FIRST));
        library.insertCardsInLibrary(3,new ObjectCard("",Color.BLUE,Type.FIRST));
        library.insertCardsInLibrary(4,new ObjectCard("",Color.WHITE,Type.FIRST));

        Assert.assertFalse(commonGoalCard.isSatisfied(library));});
    }

    /**
     * Fourth Test: All rows have 5 different types of objectCard so output is true.
     * @author: Alberto Aniballi
     * */
    @Test
    public void isSatisfied_correctInput5RowsWithFiveTypes_trueInOutput(){
        assertDoesNotThrow(()->{
        library.insertCardsInLibrary(0,new ObjectCard("",Color.LIGHTBLUE,Type.FIRST));
        library.insertCardsInLibrary(1,new ObjectCard("",Color.PINK,Type.FIRST));
        library.insertCardsInLibrary(2,new ObjectCard("",Color.GREEN,Type.FIRST));
        library.insertCardsInLibrary(3,new ObjectCard("",Color.BLUE,Type.FIRST));
        library.insertCardsInLibrary(4,new ObjectCard("",Color.WHITE,Type.FIRST));

        library.insertCardsInLibrary(0,new ObjectCard("",Color.LIGHTBLUE,Type.FIRST));
        library.insertCardsInLibrary(1,new ObjectCard("",Color.PINK,Type.FIRST));
        library.insertCardsInLibrary(2,new ObjectCard("",Color.GREEN,Type.FIRST));
        library.insertCardsInLibrary(3,new ObjectCard("",Color.BLUE,Type.FIRST));
        library.insertCardsInLibrary(4,new ObjectCard("",Color.WHITE,Type.FIRST));

        library.insertCardsInLibrary(0,new ObjectCard("",Color.LIGHTBLUE,Type.FIRST));
        library.insertCardsInLibrary(1,new ObjectCard("",Color.PINK,Type.FIRST));
        library.insertCardsInLibrary(2,new ObjectCard("",Color.GREEN,Type.FIRST));
        library.insertCardsInLibrary(3,new ObjectCard("",Color.BLUE,Type.FIRST));
        library.insertCardsInLibrary(4,new ObjectCard("",Color.WHITE,Type.FIRST));

        library.insertCardsInLibrary(0,new ObjectCard("",Color.LIGHTBLUE,Type.FIRST));
        library.insertCardsInLibrary(1,new ObjectCard("",Color.PINK,Type.FIRST));
        library.insertCardsInLibrary(2,new ObjectCard("",Color.GREEN,Type.FIRST));
        library.insertCardsInLibrary(3,new ObjectCard("",Color.BLUE,Type.FIRST));
        library.insertCardsInLibrary(4,new ObjectCard("",Color.WHITE,Type.FIRST));

        library.insertCardsInLibrary(0,new ObjectCard("",Color.LIGHTBLUE,Type.FIRST));
        library.insertCardsInLibrary(1,new ObjectCard("",Color.PINK,Type.FIRST));
        library.insertCardsInLibrary(2,new ObjectCard("",Color.GREEN,Type.FIRST));
        library.insertCardsInLibrary(3,new ObjectCard("",Color.BLUE,Type.FIRST));
        library.insertCardsInLibrary(4,new ObjectCard("",Color.WHITE,Type.FIRST));

        library.insertCardsInLibrary(0,new ObjectCard("",Color.LIGHTBLUE,Type.FIRST));
        library.insertCardsInLibrary(1,new ObjectCard("",Color.PINK,Type.FIRST));
        library.insertCardsInLibrary(2,new ObjectCard("",Color.GREEN,Type.FIRST));
        library.insertCardsInLibrary(3,new ObjectCard("",Color.BLUE,Type.FIRST));
        library.insertCardsInLibrary(4,new ObjectCard("",Color.WHITE,Type.FIRST));
        Assert.assertTrue(commonGoalCard.isSatisfied(library));});
    }

    /**
     * Fifth Test: All rows have only 1 type of objectCard so output is false.
     * @author: Alberto Aniballi
     * */
    @Test
    public void isSatisfied_correctInput5RowsWithOnlyOneType_falseInOutput(){
        assertDoesNotThrow(()-> {
            library.insertCardsInLibrary(0, new ObjectCard("", Color.WHITE, Type.FIRST));
            library.insertCardsInLibrary(1, new ObjectCard("", Color.WHITE, Type.FIRST));
            library.insertCardsInLibrary(2, new ObjectCard("", Color.WHITE, Type.FIRST));
            library.insertCardsInLibrary(3, new ObjectCard("", Color.WHITE, Type.FIRST));
            library.insertCardsInLibrary(4, new ObjectCard("", Color.WHITE, Type.FIRST));
            library.insertCardsInLibrary(0, new ObjectCard("", Color.WHITE, Type.FIRST));
            library.insertCardsInLibrary(1, new ObjectCard("", Color.WHITE, Type.FIRST));
            library.insertCardsInLibrary(2, new ObjectCard("", Color.WHITE, Type.FIRST));
            library.insertCardsInLibrary(3, new ObjectCard("", Color.WHITE, Type.FIRST));
            library.insertCardsInLibrary(4, new ObjectCard("", Color.WHITE, Type.FIRST));
            library.insertCardsInLibrary(0, new ObjectCard("", Color.WHITE, Type.FIRST));
            library.insertCardsInLibrary(1, new ObjectCard("", Color.WHITE, Type.FIRST));
            library.insertCardsInLibrary(2, new ObjectCard("", Color.WHITE, Type.FIRST));
            library.insertCardsInLibrary(3, new ObjectCard("", Color.WHITE, Type.FIRST));
            library.insertCardsInLibrary(4, new ObjectCard("", Color.WHITE, Type.FIRST));
            library.insertCardsInLibrary(0, new ObjectCard("", Color.WHITE, Type.FIRST));
            library.insertCardsInLibrary(1, new ObjectCard("", Color.WHITE, Type.FIRST));
            library.insertCardsInLibrary(2, new ObjectCard("", Color.WHITE, Type.FIRST));
            library.insertCardsInLibrary(3, new ObjectCard("", Color.WHITE, Type.FIRST));
            library.insertCardsInLibrary(4, new ObjectCard("", Color.WHITE, Type.FIRST));
            library.insertCardsInLibrary(0, new ObjectCard("", Color.WHITE, Type.FIRST));
            library.insertCardsInLibrary(1, new ObjectCard("", Color.WHITE, Type.FIRST));
            library.insertCardsInLibrary(2, new ObjectCard("", Color.WHITE, Type.FIRST));
            library.insertCardsInLibrary(3, new ObjectCard("", Color.WHITE, Type.FIRST));
            library.insertCardsInLibrary(4, new ObjectCard("", Color.WHITE, Type.FIRST));
            library.insertCardsInLibrary(0, new ObjectCard("", Color.WHITE, Type.FIRST));
            library.insertCardsInLibrary(1, new ObjectCard("", Color.WHITE, Type.FIRST));
            library.insertCardsInLibrary(2, new ObjectCard("", Color.WHITE, Type.FIRST));
            library.insertCardsInLibrary(3, new ObjectCard("", Color.WHITE, Type.FIRST));
            library.insertCardsInLibrary(4, new ObjectCard("", Color.WHITE, Type.FIRST));
            Assert.assertFalse(commonGoalCard.isSatisfied(library));
        });
    }

    /**
     * Sixth Test: all rows are empty.
     * @author: Alberto Aniballi
     * */
    @Test
    public void isSatisfied_correctInputAllRowsEmpty_falseInOutput(){
        Assert.assertFalse(commonGoalCard.isSatisfied(library));
    }

}