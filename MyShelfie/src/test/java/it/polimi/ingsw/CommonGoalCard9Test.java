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
public class CommonGoalCard9Test {
    CommonGoalCard commonGoalCard;
    Library library;
    /**Constructor
     * @author: Riccardo Figini
     * @throws IOException throws an exception*/
    public CommonGoalCard9Test() throws IOException {
        commonGoalCard = new CommonGoalCard9();
    }

    /**Library's set up with null
     * @author: Riccardo Figini
     * */
    @Before
    public void setUp(){
        library = new Library();
    }


    /**
     * First Test: one X combination.
     * @author: Alberto Aniballi
     * */
    @Test
    public void isSatisfied_oneCorrectInputOtherCellsEmpty_trueInOutput()
    {
        assertDoesNotThrow(()-> {

        library.insertCardsInLibrary(0,new ObjectCard("",Color.PINK,Type.FIRST));
        library.insertCardsInLibrary(0,new ObjectCard("",Color.PINK,Type.FIRST));
        library.insertCardsInLibrary(0,new ObjectCard("",Color.PINK,Type.FIRST));
        library.insertCardsInLibrary(0,new ObjectCard("",Color.WHITE,Type.FIRST));
        library.insertCardsInLibrary(0,new ObjectCard("",Color.GREEN,Type.FIRST));
        library.insertCardsInLibrary(0,new ObjectCard("",Color.WHITE,Type.FIRST));

        library.insertCardsInLibrary(1,new ObjectCard("",Color.GREEN,Type.FIRST));
        library.insertCardsInLibrary(1,new ObjectCard("",Color.GREEN,Type.FIRST));
        library.insertCardsInLibrary(1,new ObjectCard("",Color.GREEN,Type.FIRST));
        library.insertCardsInLibrary(1,new ObjectCard("",Color.GREEN,Type.FIRST));
        library.insertCardsInLibrary(1,new ObjectCard("",Color.WHITE,Type.FIRST));
        library.insertCardsInLibrary(1,new ObjectCard("",Color.GREEN,Type.FIRST));

        library.insertCardsInLibrary(2,new ObjectCard("",Color.BLUE,Type.FIRST));
        library.insertCardsInLibrary(2,new ObjectCard("",Color.BLUE,Type.FIRST));
        library.insertCardsInLibrary(2,new ObjectCard("",Color.BLUE,Type.FIRST));
        library.insertCardsInLibrary(2,new ObjectCard("",Color.WHITE,Type.FIRST));
        library.insertCardsInLibrary(2,new ObjectCard("",Color.GREEN,Type.FIRST));
        library.insertCardsInLibrary(2,new ObjectCard("",Color.WHITE,Type.FIRST));
        Assert.assertTrue(commonGoalCard.isSatisfied(library));});
    }

    /**
     * Second Test: no X combination
     * @author: Alberto Aniballi
     * */
    @Test
    public void isSatisfied_noCorrectInput_falseInOutput(){
        assertDoesNotThrow(()-> {
        library.insertCardsInLibrary(0,new ObjectCard("",Color.GREEN,Type.FIRST));
        library.insertCardsInLibrary(1,new ObjectCard("",Color.GREEN,Type.FIRST));
        library.insertCardsInLibrary(2,new ObjectCard("",Color.GREEN,Type.FIRST));
        library.insertCardsInLibrary(3,new ObjectCard("",Color.GREEN,Type.FIRST));
        library.insertCardsInLibrary(4,new ObjectCard("",Color.GREEN,Type.FIRST));

        library.insertCardsInLibrary(0,new ObjectCard("",Color.WHITE,Type.FIRST));
        library.insertCardsInLibrary(1,new ObjectCard("",Color.WHITE,Type.FIRST));
        library.insertCardsInLibrary(2,new ObjectCard("",Color.WHITE,Type.FIRST));
        library.insertCardsInLibrary(3,new ObjectCard("",Color.WHITE,Type.FIRST));
        library.insertCardsInLibrary(4,new ObjectCard("",Color.WHITE,Type.FIRST));

        library.insertCardsInLibrary(0,new ObjectCard("",Color.GREEN,Type.FIRST));
        library.insertCardsInLibrary(1,new ObjectCard("",Color.GREEN,Type.FIRST));
        library.insertCardsInLibrary(2,new ObjectCard("",Color.GREEN,Type.FIRST));
        library.insertCardsInLibrary(3,new ObjectCard("",Color.GREEN,Type.FIRST));
        library.insertCardsInLibrary(4,new ObjectCard("",Color.GREEN,Type.FIRST));

        library.insertCardsInLibrary(0,new ObjectCard("",Color.WHITE,Type.FIRST));
        library.insertCardsInLibrary(1,new ObjectCard("",Color.WHITE,Type.FIRST));
        library.insertCardsInLibrary(2,new ObjectCard("",Color.WHITE,Type.FIRST));
        library.insertCardsInLibrary(3,new ObjectCard("",Color.WHITE,Type.FIRST));
        library.insertCardsInLibrary(4,new ObjectCard("",Color.WHITE,Type.FIRST));

        library.insertCardsInLibrary(0,new ObjectCard("",Color.GREEN,Type.FIRST));
        library.insertCardsInLibrary(1,new ObjectCard("",Color.GREEN,Type.FIRST));
        library.insertCardsInLibrary(2,new ObjectCard("",Color.GREEN,Type.FIRST));
        library.insertCardsInLibrary(3,new ObjectCard("",Color.GREEN,Type.FIRST));
        library.insertCardsInLibrary(4,new ObjectCard("",Color.GREEN,Type.FIRST));

        library.insertCardsInLibrary(0,new ObjectCard("",Color.WHITE,Type.FIRST));
        library.insertCardsInLibrary(1,new ObjectCard("",Color.WHITE,Type.FIRST));
        library.insertCardsInLibrary(2,new ObjectCard("",Color.WHITE,Type.FIRST));
        library.insertCardsInLibrary(3,new ObjectCard("",Color.WHITE,Type.FIRST));
        library.insertCardsInLibrary(4,new ObjectCard("",Color.WHITE,Type.FIRST));
        Assert.assertFalse(commonGoalCard.isSatisfied(library));});
    }

    /**
     * Third Test: Left up corner no same color.
     * @author: Alberto Aniballi
     * */
    @Test
    public void isSatisfied_noCorrectInputLeftUpCornerWrong_falseInOutput(){
        assertDoesNotThrow(()-> {
        library.insertCardsInLibrary(0,new ObjectCard("",Color.PINK,Type.FIRST));
        library.insertCardsInLibrary(0,new ObjectCard("",Color.PINK,Type.FIRST));
        library.insertCardsInLibrary(0,new ObjectCard("",Color.PINK,Type.FIRST));
        library.insertCardsInLibrary(0,new ObjectCard("",Color.WHITE,Type.FIRST));
        library.insertCardsInLibrary(0,new ObjectCard("",Color.GREEN,Type.FIRST));
        library.insertCardsInLibrary(0,new ObjectCard("",Color.PINK,Type.FIRST));

        library.insertCardsInLibrary(1,new ObjectCard("",Color.GREEN,Type.FIRST));
        library.insertCardsInLibrary(1,new ObjectCard("",Color.GREEN,Type.FIRST));
        library.insertCardsInLibrary(1,new ObjectCard("",Color.GREEN,Type.FIRST));
        library.insertCardsInLibrary(1,new ObjectCard("",Color.GREEN,Type.FIRST));
        library.insertCardsInLibrary(1,new ObjectCard("",Color.WHITE,Type.FIRST));
        library.insertCardsInLibrary(1,new ObjectCard("",Color.GREEN,Type.FIRST));

        library.insertCardsInLibrary(2,new ObjectCard("",Color.BLUE,Type.FIRST));
        library.insertCardsInLibrary(2,new ObjectCard("",Color.BLUE,Type.FIRST));
        library.insertCardsInLibrary(2,new ObjectCard("",Color.BLUE,Type.FIRST));
        library.insertCardsInLibrary(2,new ObjectCard("",Color.WHITE,Type.FIRST));
        library.insertCardsInLibrary(2,new ObjectCard("",Color.GREEN,Type.FIRST));
        library.insertCardsInLibrary(2,new ObjectCard("",Color.WHITE,Type.FIRST));
        Assert.assertFalse(commonGoalCard.isSatisfied(library));});
    }

    /**
     * Fourth Test: Left down corner no same color.
     * @author: Alberto Aniballi
     * */
    @Test
    public void isSatisfied_noCorrectInputLeftDownCornerWrong_falseInOutput(){
        assertDoesNotThrow(()-> {
        library.insertCardsInLibrary(0,new ObjectCard("",Color.PINK,Type.FIRST));
        library.insertCardsInLibrary(0,new ObjectCard("",Color.PINK,Type.FIRST));
        library.insertCardsInLibrary(0,new ObjectCard("",Color.PINK,Type.FIRST));
        library.insertCardsInLibrary(0,new ObjectCard("",Color.PINK,Type.FIRST));
        library.insertCardsInLibrary(0,new ObjectCard("",Color.GREEN,Type.FIRST));
        library.insertCardsInLibrary(0,new ObjectCard("",Color.WHITE,Type.FIRST));

        library.insertCardsInLibrary(1,new ObjectCard("",Color.GREEN,Type.FIRST));
        library.insertCardsInLibrary(1,new ObjectCard("",Color.GREEN,Type.FIRST));
        library.insertCardsInLibrary(1,new ObjectCard("",Color.GREEN,Type.FIRST));
        library.insertCardsInLibrary(1,new ObjectCard("",Color.GREEN,Type.FIRST));
        library.insertCardsInLibrary(1,new ObjectCard("",Color.WHITE,Type.FIRST));
        library.insertCardsInLibrary(1,new ObjectCard("",Color.GREEN,Type.FIRST));

        library.insertCardsInLibrary(2,new ObjectCard("",Color.BLUE,Type.FIRST));
        library.insertCardsInLibrary(2,new ObjectCard("",Color.BLUE,Type.FIRST));
        library.insertCardsInLibrary(2,new ObjectCard("",Color.BLUE,Type.FIRST));
        library.insertCardsInLibrary(2,new ObjectCard("",Color.WHITE,Type.FIRST));
        library.insertCardsInLibrary(2,new ObjectCard("",Color.GREEN,Type.FIRST));
        library.insertCardsInLibrary(2,new ObjectCard("",Color.WHITE,Type.FIRST));
        Assert.assertFalse(commonGoalCard.isSatisfied(library));});
    }

    /**
     * Fifth Test: Right up corner no same color.
     * @author: Alberto Aniballi
     * */
    @Test
    public void isSatisfied_noCorrectInputRightUpCornerWrong_falseInOutput(){
        assertDoesNotThrow(()-> {

        library.insertCardsInLibrary(0,new ObjectCard("",Color.PINK,Type.FIRST));
        library.insertCardsInLibrary(0,new ObjectCard("",Color.PINK,Type.FIRST));
        library.insertCardsInLibrary(0,new ObjectCard("",Color.PINK,Type.FIRST));
        library.insertCardsInLibrary(0,new ObjectCard("",Color.WHITE,Type.FIRST));
        library.insertCardsInLibrary(0,new ObjectCard("",Color.GREEN,Type.FIRST));
        library.insertCardsInLibrary(0,new ObjectCard("",Color.WHITE,Type.FIRST));

        library.insertCardsInLibrary(1,new ObjectCard("",Color.GREEN,Type.FIRST));
        library.insertCardsInLibrary(1,new ObjectCard("",Color.GREEN,Type.FIRST));
        library.insertCardsInLibrary(1,new ObjectCard("",Color.GREEN,Type.FIRST));
        library.insertCardsInLibrary(1,new ObjectCard("",Color.GREEN,Type.FIRST));
        library.insertCardsInLibrary(1,new ObjectCard("",Color.WHITE,Type.FIRST));
        library.insertCardsInLibrary(1,new ObjectCard("",Color.GREEN,Type.FIRST));

        library.insertCardsInLibrary(2,new ObjectCard("",Color.BLUE,Type.FIRST));
        library.insertCardsInLibrary(2,new ObjectCard("",Color.BLUE,Type.FIRST));
        library.insertCardsInLibrary(2,new ObjectCard("",Color.BLUE,Type.FIRST));
        library.insertCardsInLibrary(2,new ObjectCard("",Color.WHITE,Type.FIRST));
        library.insertCardsInLibrary(2,new ObjectCard("",Color.GREEN,Type.FIRST));
        library.insertCardsInLibrary(2,new ObjectCard("",Color.PINK,Type.FIRST));
        Assert.assertFalse(commonGoalCard.isSatisfied(library));});
    }

    /**
     * Sixth Test: Right down corner no same color.
     * @author: Alberto Aniballi
     * */
    @Test
    public void isSatisfied_noCorrectInputRightDownCornerWrong_falseInOutput(){
        assertDoesNotThrow(()-> {
        library.insertCardsInLibrary(0,new ObjectCard("",Color.PINK,Type.FIRST));
        library.insertCardsInLibrary(0,new ObjectCard("",Color.PINK,Type.FIRST));
        library.insertCardsInLibrary(0,new ObjectCard("",Color.PINK,Type.FIRST));
        library.insertCardsInLibrary(0,new ObjectCard("",Color.WHITE,Type.FIRST));
        library.insertCardsInLibrary(0,new ObjectCard("",Color.GREEN,Type.FIRST));
        library.insertCardsInLibrary(0,new ObjectCard("",Color.WHITE,Type.FIRST));

        library.insertCardsInLibrary(1,new ObjectCard("",Color.GREEN,Type.FIRST));
        library.insertCardsInLibrary(1,new ObjectCard("",Color.GREEN,Type.FIRST));
        library.insertCardsInLibrary(1,new ObjectCard("",Color.GREEN,Type.FIRST));
        library.insertCardsInLibrary(1,new ObjectCard("",Color.GREEN,Type.FIRST));
        library.insertCardsInLibrary(1,new ObjectCard("",Color.WHITE,Type.FIRST));
        library.insertCardsInLibrary(1,new ObjectCard("",Color.GREEN,Type.FIRST));

        library.insertCardsInLibrary(2,new ObjectCard("",Color.BLUE,Type.FIRST));
        library.insertCardsInLibrary(2,new ObjectCard("",Color.BLUE,Type.FIRST));
        library.insertCardsInLibrary(2,new ObjectCard("",Color.BLUE,Type.FIRST));
        library.insertCardsInLibrary(2,new ObjectCard("",Color.PINK,Type.FIRST));
        library.insertCardsInLibrary(2,new ObjectCard("",Color.GREEN,Type.FIRST));
        library.insertCardsInLibrary(2,new ObjectCard("",Color.WHITE,Type.FIRST));
        Assert.assertFalse(commonGoalCard.isSatisfied(library));});
    }

    /**
     * Fifth Test: all rows are empty.
     * @author: Alberto Aniballi
     * */
    @Test
    public void isSatisfied_correctInputAllColumnsEmpty_falseInOutput(){
        Assert.assertFalse(commonGoalCard.isSatisfied(library));
    }
}
