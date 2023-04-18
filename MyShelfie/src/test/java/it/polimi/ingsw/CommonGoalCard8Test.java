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

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

/**
 * @author: Alberto Aniballi
 */
public class CommonGoalCard8Test {

    CommonGoalCard commonGoalCard;
    //Type never affects this algo

    Library library;
    /**Constructor
     * @author: Riccardo Figini
     * @throws IOException throws an exception*/
    public CommonGoalCard8Test() throws IOException {
        commonGoalCard = new CommonGoalCard8();
    }

    /**Library's set up with null
     * @author: Riccardo Figini
     * */
    @Before
    public void setUp(){
        library = new Library();
    }
    /**Method used to insert ObjectCard in matrix
     * @author: Riccardo Figini
     * @param row row
     * @param col column
     * @param color color*/

    /**
     * First Test: three columns with 3 different types and the other cells are all empty.
     * @author: Alberto Aniballi
     * */ @Test
    public void isSatisfied_correctInputThreeColumnsOtherEmpty_trueInOutput(){
        assertDoesNotThrow(()-> {

                    library.insertCardsInLibrary(0,new ObjectCard("",Color.LIGHTBLUE,Type.FIRST));
        library.insertCardsInLibrary(0,new ObjectCard("",Color.LIGHTBLUE,Type.FIRST));
        library.insertCardsInLibrary(0,new ObjectCard("",Color.BLUE,Type.FIRST));
        library.insertCardsInLibrary(0,new ObjectCard("",Color.BLUE,Type.FIRST));
        library.insertCardsInLibrary(0,new ObjectCard("",Color.WHITE,Type.FIRST));
        library.insertCardsInLibrary(0,new ObjectCard("",Color.WHITE,Type.FIRST));

        library.insertCardsInLibrary(1,new ObjectCard("",Color.LIGHTBLUE,Type.FIRST));
        library.insertCardsInLibrary(1,new ObjectCard("",Color.LIGHTBLUE,Type.FIRST));
        library.insertCardsInLibrary(1,new ObjectCard("",Color.BLUE,Type.FIRST));
        library.insertCardsInLibrary(1,new ObjectCard("",Color.BLUE,Type.FIRST));
        library.insertCardsInLibrary(1,new ObjectCard("",Color.WHITE,Type.FIRST));
        library.insertCardsInLibrary(1,new ObjectCard("",Color.WHITE,Type.FIRST));

        library.insertCardsInLibrary(2,new ObjectCard("",Color.LIGHTBLUE,Type.FIRST));
        library.insertCardsInLibrary(2,new ObjectCard("",Color.LIGHTBLUE,Type.FIRST));
        library.insertCardsInLibrary(2,new ObjectCard("",Color.BLUE,Type.FIRST));
        library.insertCardsInLibrary(2,new ObjectCard("",Color.BLUE,Type.FIRST));
        library.insertCardsInLibrary(2,new ObjectCard("",Color.WHITE,Type.FIRST));
        library.insertCardsInLibrary(2,new ObjectCard("",Color.WHITE,Type.FIRST));
        Assert.assertTrue(commonGoalCard.isSatisfied(library));});
    }

    /**
     * Second Test: three columns with 3 different types and the other cells are all filled with random objectCards.
     * @author: Alberto Aniballi
     * */
    @Test
    public void isSatisfied_correctInputThreeColumnsOtherRowsRandom_trueInOutput(){
        assertDoesNotThrow(()-> {

                    library.insertCardsInLibrary(0,new ObjectCard("",Color.LIGHTBLUE,Type.FIRST));
        library.insertCardsInLibrary(0,new ObjectCard("",Color.LIGHTBLUE,Type.FIRST));
        library.insertCardsInLibrary(0,new ObjectCard("",Color.BLUE,Type.FIRST));
        library.insertCardsInLibrary(0,new ObjectCard("",Color.BLUE,Type.FIRST));
        library.insertCardsInLibrary(0,new ObjectCard("",Color.WHITE,Type.FIRST));
        library.insertCardsInLibrary(0,new ObjectCard("",Color.WHITE,Type.FIRST));

        library.insertCardsInLibrary(1,new ObjectCard("",Color.LIGHTBLUE,Type.FIRST));
        library.insertCardsInLibrary(1,new ObjectCard("",Color.LIGHTBLUE,Type.FIRST));
        library.insertCardsInLibrary(1,new ObjectCard("",Color.BLUE,Type.FIRST));
        library.insertCardsInLibrary(1,new ObjectCard("",Color.BLUE,Type.FIRST));
        library.insertCardsInLibrary(1,new ObjectCard("",Color.WHITE,Type.FIRST));
        library.insertCardsInLibrary(1,new ObjectCard("",Color.WHITE,Type.FIRST));

        library.insertCardsInLibrary(2,new ObjectCard("",Color.LIGHTBLUE,Type.FIRST));
        library.insertCardsInLibrary(2,new ObjectCard("",Color.LIGHTBLUE,Type.FIRST));
        library.insertCardsInLibrary(2,new ObjectCard("",Color.BLUE,Type.FIRST));
        library.insertCardsInLibrary(2,new ObjectCard("",Color.BLUE,Type.FIRST));
        library.insertCardsInLibrary(2,new ObjectCard("",Color.WHITE,Type.FIRST));
        library.insertCardsInLibrary(2,new ObjectCard("",Color.WHITE,Type.FIRST));

        library.insertCardsInLibrary(3,new ObjectCard("",Color.LIGHTBLUE,Type.FIRST));
        library.insertCardsInLibrary(3,new ObjectCard("",Color.PINK,Type.FIRST));
        library.insertCardsInLibrary(3,new ObjectCard("",Color.BLUE,Type.FIRST));
        library.insertCardsInLibrary(3,new ObjectCard("",Color.GREEN,Type.FIRST));
        library.insertCardsInLibrary(3,new ObjectCard("",Color.YELLOW,Type.FIRST));
        library.insertCardsInLibrary(3,new ObjectCard("",Color.WHITE,Type.FIRST));

        library.insertCardsInLibrary(4,new ObjectCard("",Color.LIGHTBLUE,Type.FIRST));
        library.insertCardsInLibrary(4,new ObjectCard("",Color.PINK,Type.FIRST));
        library.insertCardsInLibrary(4,new ObjectCard("",Color.BLUE,Type.FIRST));
        library.insertCardsInLibrary(4,new ObjectCard("",Color.GREEN,Type.FIRST));
        library.insertCardsInLibrary(4,new ObjectCard("",Color.YELLOW,Type.FIRST));
        library.insertCardsInLibrary(4,new ObjectCard("",Color.WHITE,Type.FIRST));
        Assert.assertTrue(commonGoalCard.isSatisfied(library));});
    }

    /**
     * Third Test: 5 columns with more than 3 different types of objectCard so output is false.
     * @author: Alberto Aniballi
     * */
    @Test
    public void isSatisfied_correctInput5ColumnsWithMoreThanThreeTypes_falseInOutput(){
        assertDoesNotThrow(()-> {
            library.insertCardsInLibrary(0, new ObjectCard("", Color.LIGHTBLUE, Type.FIRST));
            library.insertCardsInLibrary(0, new ObjectCard("", Color.LIGHTBLUE, Type.FIRST));
            library.insertCardsInLibrary(0, new ObjectCard("", Color.BLUE, Type.FIRST));
            library.insertCardsInLibrary(0, new ObjectCard("", Color.BLUE, Type.FIRST));
            library.insertCardsInLibrary(0, new ObjectCard("", Color.GREEN, Type.FIRST));
            library.insertCardsInLibrary(0, new ObjectCard("", Color.WHITE, Type.FIRST));

            library.insertCardsInLibrary(1, new ObjectCard("", Color.LIGHTBLUE, Type.FIRST));
            library.insertCardsInLibrary(1, new ObjectCard("", Color.LIGHTBLUE, Type.FIRST));
            library.insertCardsInLibrary(1, new ObjectCard("", Color.BLUE, Type.FIRST));
            library.insertCardsInLibrary(1, new ObjectCard("", Color.BLUE, Type.FIRST));
            library.insertCardsInLibrary(1, new ObjectCard("", Color.GREEN, Type.FIRST));
            library.insertCardsInLibrary(1, new ObjectCard("", Color.WHITE, Type.FIRST));

            library.insertCardsInLibrary(2, new ObjectCard("", Color.LIGHTBLUE, Type.FIRST));
            library.insertCardsInLibrary(2, new ObjectCard("", Color.LIGHTBLUE, Type.FIRST));
            library.insertCardsInLibrary(2, new ObjectCard("", Color.BLUE, Type.FIRST));
            library.insertCardsInLibrary(2, new ObjectCard("", Color.BLUE, Type.FIRST));
            library.insertCardsInLibrary(2, new ObjectCard("", Color.GREEN, Type.FIRST));
            library.insertCardsInLibrary(2, new ObjectCard("", Color.WHITE, Type.FIRST));

            library.insertCardsInLibrary(3, new ObjectCard("", Color.LIGHTBLUE, Type.FIRST));
            library.insertCardsInLibrary(3, new ObjectCard("", Color.LIGHTBLUE, Type.FIRST));
            library.insertCardsInLibrary(3, new ObjectCard("", Color.BLUE, Type.FIRST));
            library.insertCardsInLibrary(3, new ObjectCard("", Color.BLUE, Type.FIRST));
            library.insertCardsInLibrary(3, new ObjectCard("", Color.GREEN, Type.FIRST));
            library.insertCardsInLibrary(3, new ObjectCard("", Color.WHITE, Type.FIRST));

            library.insertCardsInLibrary(4, new ObjectCard("", Color.LIGHTBLUE, Type.FIRST));
            library.insertCardsInLibrary(4, new ObjectCard("", Color.LIGHTBLUE, Type.FIRST));
            library.insertCardsInLibrary(4, new ObjectCard("", Color.BLUE, Type.FIRST));
            library.insertCardsInLibrary(4, new ObjectCard("", Color.BLUE, Type.FIRST));
            library.insertCardsInLibrary(4, new ObjectCard("", Color.GREEN, Type.FIRST));
            library.insertCardsInLibrary(4, new ObjectCard("", Color.WHITE, Type.FIRST));
            Assert.assertFalse(commonGoalCard.isSatisfied(library));
        });
    }

    /**
     * Fourth Test: All columns have 2 different types of objectCard so output is true.
     * @author: Alberto Aniballi
     * */
    @Test
    public void isSatisfied_correctInput5RowsWithFiveTypes_trueInOutput(){
        assertDoesNotThrow(()-> {

                    library.insertCardsInLibrary(0,new ObjectCard("",Color.LIGHTBLUE,Type.FIRST));
        library.insertCardsInLibrary(0,new ObjectCard("",Color.LIGHTBLUE,Type.FIRST));
        library.insertCardsInLibrary(0,new ObjectCard("",Color.LIGHTBLUE,Type.FIRST));
        library.insertCardsInLibrary(0,new ObjectCard("",Color.WHITE,Type.FIRST));
        library.insertCardsInLibrary(0,new ObjectCard("",Color.GREEN,Type.FIRST));
        library.insertCardsInLibrary(0,new ObjectCard("",Color.WHITE,Type.FIRST));

        library.insertCardsInLibrary(1,new ObjectCard("",Color.LIGHTBLUE,Type.FIRST));
        library.insertCardsInLibrary(1,new ObjectCard("",Color.LIGHTBLUE,Type.FIRST));
        library.insertCardsInLibrary(1,new ObjectCard("",Color.LIGHTBLUE,Type.FIRST));
        library.insertCardsInLibrary(1,new ObjectCard("",Color.WHITE,Type.FIRST));
        library.insertCardsInLibrary(1,new ObjectCard("",Color.GREEN,Type.FIRST));
        library.insertCardsInLibrary(1,new ObjectCard("",Color.WHITE,Type.FIRST));

        library.insertCardsInLibrary(2,new ObjectCard("",Color.LIGHTBLUE,Type.FIRST));
        library.insertCardsInLibrary(2,new ObjectCard("",Color.LIGHTBLUE,Type.FIRST));
        library.insertCardsInLibrary(2,new ObjectCard("",Color.LIGHTBLUE,Type.FIRST));
        library.insertCardsInLibrary(2,new ObjectCard("",Color.WHITE,Type.FIRST));
        library.insertCardsInLibrary(2,new ObjectCard("",Color.GREEN,Type.FIRST));
        library.insertCardsInLibrary(2,new ObjectCard("",Color.WHITE,Type.FIRST));

        library.insertCardsInLibrary(3,new ObjectCard("",Color.LIGHTBLUE,Type.FIRST));
        library.insertCardsInLibrary(3,new ObjectCard("",Color.LIGHTBLUE,Type.FIRST));
        library.insertCardsInLibrary(3,new ObjectCard("",Color.LIGHTBLUE,Type.FIRST));
        library.insertCardsInLibrary(3,new ObjectCard("",Color.WHITE,Type.FIRST));
        library.insertCardsInLibrary(3,new ObjectCard("",Color.GREEN,Type.FIRST));
        library.insertCardsInLibrary(3,new ObjectCard("",Color.WHITE,Type.FIRST));

        library.insertCardsInLibrary(4,new ObjectCard("",Color.LIGHTBLUE,Type.FIRST));
        library.insertCardsInLibrary(4,new ObjectCard("",Color.LIGHTBLUE,Type.FIRST));
        library.insertCardsInLibrary(4,new ObjectCard("",Color.LIGHTBLUE,Type.FIRST));
        library.insertCardsInLibrary(4,new ObjectCard("",Color.WHITE,Type.FIRST));
        library.insertCardsInLibrary(4,new ObjectCard("",Color.GREEN,Type.FIRST));
        library.insertCardsInLibrary(4,new ObjectCard("",Color.WHITE,Type.FIRST));

        Assert.assertTrue(commonGoalCard.isSatisfied(library));});
    }

    /**
     * Fifth Test: All columns have only 1 type of objectCard so output is false.
     * @author: Alberto Aniballi
     * */
    @Test
    public void isSatisfied_correctInput5ColumnsWithOnlyOneType_trueInOutput(){
        assertDoesNotThrow(()-> {

        library.insertCardsInLibrary(4,new ObjectCard("",Color.BLUE,Type.FIRST));
        library.insertCardsInLibrary(4,new ObjectCard("",Color.BLUE,Type.FIRST));
        library.insertCardsInLibrary(4,new ObjectCard("",Color.WHITE,Type.FIRST));
        library.insertCardsInLibrary(4,new ObjectCard("",Color.WHITE,Type.FIRST));
        library.insertCardsInLibrary(4,new ObjectCard("",Color.WHITE,Type.FIRST));
        library.insertCardsInLibrary(4,new ObjectCard("",Color.WHITE,Type.FIRST));

        library.insertCardsInLibrary(3,new ObjectCard("",Color.BLUE,Type.FIRST));
        library.insertCardsInLibrary(3,new ObjectCard("",Color.BLUE,Type.FIRST));
        library.insertCardsInLibrary(3,new ObjectCard("",Color.BLUE,Type.FIRST));
        library.insertCardsInLibrary(3,new ObjectCard("",Color.WHITE,Type.FIRST));
        library.insertCardsInLibrary(3,new ObjectCard("",Color.WHITE,Type.FIRST));
        library.insertCardsInLibrary(3,new ObjectCard("",Color.WHITE,Type.FIRST));

        library.insertCardsInLibrary(2,new ObjectCard("",Color.BLUE,Type.FIRST));
        library.insertCardsInLibrary(2,new ObjectCard("",Color.BLUE,Type.FIRST));
        library.insertCardsInLibrary(2,new ObjectCard("",Color.BLUE,Type.FIRST));
        library.insertCardsInLibrary(2,new ObjectCard("",Color.BLUE,Type.FIRST));
        library.insertCardsInLibrary(2,new ObjectCard("",Color.WHITE,Type.FIRST));
        library.insertCardsInLibrary(2,new ObjectCard("",Color.WHITE,Type.FIRST));

        library.insertCardsInLibrary(1,new ObjectCard("",Color.PINK,Type.FIRST));
        library.insertCardsInLibrary(1,new ObjectCard("",Color.PINK,Type.FIRST));
        library.insertCardsInLibrary(1,new ObjectCard("",Color.PINK,Type.FIRST));
        library.insertCardsInLibrary(1,new ObjectCard("",Color.PINK,Type.FIRST));
        library.insertCardsInLibrary(1,new ObjectCard("",Color.PINK,Type.FIRST));
        library.insertCardsInLibrary(1,new ObjectCard("",Color.WHITE,Type.FIRST));
        Assert.assertTrue(commonGoalCard.isSatisfied(library));});
    }

    /**
     * Sixth Test: all rows are empty.
     * @author: Alberto Aniballi
     * */
    @Test
    public void isSatisfied_correctInputAllColumnsEmpty_falseInOutput(){
        Assert.assertFalse(commonGoalCard.isSatisfied(library));
    }

}
