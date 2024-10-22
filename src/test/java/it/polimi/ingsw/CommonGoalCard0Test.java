package it.polimi.ingsw;

import it.polimi.ingsw.enums.Color;
import it.polimi.ingsw.enums.Type;
import it.polimi.ingsw.model.Cards.CommonGoalCard;
import it.polimi.ingsw.model.Cards.ConcreteCommonCards.*;
import it.polimi.ingsw.model.Cards.*;
import it.polimi.ingsw.model.Player.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class CommonGoalCard0Test {
    CommonGoalCard commonGoalCard;

    Library library;

    public CommonGoalCard0Test() {
        commonGoalCard = new CommonGoalCard0();
    }
    @Before
    public void setUp() {
        library = new Library();
    }


    /**
     * First test: 6 couples of same color -> answer true
     */
    @Test
    public void isSatisfied_separateBlock_1_trueInOutput() {
        assertDoesNotThrow(()->{
            library.insertCardsInLibrary(0,new ObjectCard("", Color.PINK, Type.FIRST));
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
            Assert.assertTrue(commonGoalCard.isSatisfied(library));});
    }

    /**
     * Second test: 6 separate couples of different color -> answer true
     */
    @Test
    public void isSatisfied_separateBlock_2_trueInOutput(){
        assertDoesNotThrow(()-> {
            library.insertCardsInLibrary(0, new ObjectCard("", Color.PINK, Type.FIRST));
            library.insertCardsInLibrary(0, new ObjectCard("", Color.BLUE, Type.FIRST));
            library.insertCardsInLibrary(0, new ObjectCard("", Color.PINK, Type.FIRST));
            library.insertCardsInLibrary(0, new ObjectCard("", Color.BLUE, Type.FIRST));
            library.insertCardsInLibrary(0, new ObjectCard("", Color.WHITE, Type.FIRST));
            library.insertCardsInLibrary(0, new ObjectCard("", Color.WHITE, Type.FIRST));

            library.insertCardsInLibrary(1, new ObjectCard("", Color.YELLOW, Type.FIRST));
            library.insertCardsInLibrary(1, new ObjectCard("", Color.PINK, Type.FIRST));
            library.insertCardsInLibrary(1, new ObjectCard("", Color.LIGHTBLUE, Type.FIRST));
            library.insertCardsInLibrary(1, new ObjectCard("", Color.LIGHTBLUE, Type.FIRST));
            library.insertCardsInLibrary(1, new ObjectCard("", Color.YELLOW, Type.FIRST));
            library.insertCardsInLibrary(1, new ObjectCard("", Color.PINK, Type.FIRST));

            library.insertCardsInLibrary(2, new ObjectCard("", Color.BLUE, Type.FIRST));
            library.insertCardsInLibrary(2, new ObjectCard("", Color.BLUE, Type.FIRST));
            library.insertCardsInLibrary(2, new ObjectCard("", Color.YELLOW, Type.FIRST));
            library.insertCardsInLibrary(2, new ObjectCard("", Color.WHITE, Type.FIRST));
            library.insertCardsInLibrary(2, new ObjectCard("", Color.GREEN, Type.FIRST));
            library.insertCardsInLibrary(2, new ObjectCard("", Color.GREEN, Type.FIRST));

            library.insertCardsInLibrary(3, new ObjectCard("", Color.PINK, Type.FIRST));
            library.insertCardsInLibrary(3, new ObjectCard("", Color.BLUE, Type.FIRST));
            library.insertCardsInLibrary(3, new ObjectCard("", Color.PINK, Type.FIRST));
            library.insertCardsInLibrary(3, new ObjectCard("", Color.PINK, Type.FIRST));
            library.insertCardsInLibrary(3, new ObjectCard("", Color.BLUE, Type.FIRST));
            library.insertCardsInLibrary(3, new ObjectCard("", Color.PINK, Type.FIRST));

            library.insertCardsInLibrary(4, new ObjectCard("", Color.BLUE, Type.FIRST));
            library.insertCardsInLibrary(4, new ObjectCard("", Color.WHITE, Type.FIRST));
            library.insertCardsInLibrary(4, new ObjectCard("", Color.BLUE, Type.FIRST));
            library.insertCardsInLibrary(4, new ObjectCard("", Color.WHITE, Type.FIRST));
            library.insertCardsInLibrary(4, new ObjectCard("", Color.YELLOW, Type.FIRST));
            library.insertCardsInLibrary(4, new ObjectCard("", Color.YELLOW, Type.FIRST));
            Assert.assertTrue(commonGoalCard.isSatisfied(library));
        });
    }

    /**
     * Third test: 6 couples but not separate of same color -> answer false
     */
    @Test
    public void isSatisfied_notSeparateBlock_1_falseInOutput(){
        assertDoesNotThrow(()-> {
            library.insertCardsInLibrary(0,
                    new ObjectCard("", Color.WHITE, Type.FIRST),
                    new ObjectCard("", Color.WHITE, Type.FIRST),
                    new ObjectCard("", Color.WHITE, Type.FIRST),
                    new ObjectCard("", Color.WHITE, Type.FIRST));
            library.insertCardsInLibrary(2,
                    new ObjectCard("", Color.BLUE, Type.FIRST),
                    new ObjectCard("", Color.BLUE, Type.FIRST),
                    new ObjectCard("", Color.BLUE, Type.FIRST),
                    new ObjectCard("", Color.BLUE, Type.FIRST));
            library.insertCardsInLibrary(4,
                    new ObjectCard("", Color.YELLOW, Type.FIRST),
                    new ObjectCard("", Color.YELLOW, Type.FIRST),
                    new ObjectCard("", Color.YELLOW, Type.FIRST),
                    new ObjectCard("", Color.YELLOW, Type.FIRST));
            Assert.assertFalse(commonGoalCard.isSatisfied(library));
        });
    }

    /**
     * Fourth test: 2 full columns of same color -> answer false
     */
    @Test
    public void isSatisfied_twoColumnsFullColored_falseInOutput(){
        assertDoesNotThrow(()-> {
            library.insertCardsInLibrary(0,
                    new ObjectCard("", Color.WHITE, Type.FIRST),
                    new ObjectCard("", Color.WHITE, Type.FIRST),
                    new ObjectCard("", Color.WHITE, Type.FIRST),
                    new ObjectCard("", Color.WHITE, Type.FIRST),
                    new ObjectCard("", Color.WHITE, Type.FIRST),
                    new ObjectCard("", Color.WHITE, Type.FIRST));
            library.insertCardsInLibrary(2,
                    new ObjectCard("", Color.YELLOW, Type.FIRST),
                    new ObjectCard("", Color.YELLOW, Type.FIRST),
                    new ObjectCard("", Color.YELLOW, Type.FIRST),
                    new ObjectCard("", Color.YELLOW, Type.FIRST),
                    new ObjectCard("", Color.YELLOW, Type.FIRST),
                    new ObjectCard("", Color.YELLOW, Type.FIRST));
            Assert.assertFalse(commonGoalCard.isSatisfied(library));
        });
    }

    /**
     * Fifth test: no couples of same color
     */
    @Test
    public void isSatisfied_noCouplesColor_falseInOutput(){
        assertDoesNotThrow(()-> {
            library.insertCardsInLibrary(0,
                    new ObjectCard("", Color.WHITE, Type.FIRST),
                    new ObjectCard("", Color.PINK, Type.FIRST),
                    new ObjectCard("", Color.LIGHTBLUE, Type.FIRST),
                    new ObjectCard("", Color.BLUE, Type.FIRST),
                    new ObjectCard("", Color.YELLOW, Type.FIRST),
                    new ObjectCard("", Color.GREEN, Type.FIRST));

            library.insertCardsInLibrary(1,
                    new ObjectCard("", Color.YELLOW, Type.FIRST),
                    new ObjectCard("", Color.BLUE, Type.FIRST),
                    new ObjectCard("", Color.LIGHTBLUE, Type.FIRST),
                    new ObjectCard("", Color.PINK, Type.FIRST),
                    new ObjectCard("", Color.WHITE, Type.FIRST),
                    new ObjectCard("", Color.GREEN, Type.FIRST));

            library.insertCardsInLibrary(2,
                    new ObjectCard("", Color.BLUE, Type.FIRST),
                    new ObjectCard("", Color.LIGHTBLUE, Type.FIRST),
                    new ObjectCard("", Color.PINK, Type.FIRST),
                    new ObjectCard("", Color.WHITE, Type.FIRST),
                    new ObjectCard("", Color.GREEN, Type.FIRST),
                    new ObjectCard("", Color.YELLOW, Type.FIRST));

            library.insertCardsInLibrary(3,
                    new ObjectCard("", Color.LIGHTBLUE, Type.FIRST),
                    new ObjectCard("", Color.PINK, Type.FIRST),
                    new ObjectCard("", Color.WHITE, Type.FIRST),
                    new ObjectCard("", Color.GREEN, Type.FIRST),
                    new ObjectCard("", Color.YELLOW, Type.FIRST),
                    new ObjectCard("", Color.BLUE, Type.FIRST));

            library.insertCardsInLibrary(4,
                    new ObjectCard("", Color.PINK, Type.FIRST),
                    new ObjectCard("", Color.WHITE, Type.FIRST),
                    new ObjectCard("", Color.GREEN, Type.FIRST),
                    new ObjectCard("", Color.YELLOW, Type.FIRST),
                    new ObjectCard("", Color.BLUE, Type.FIRST),
                    new ObjectCard("", Color.LIGHTBLUE, Type.FIRST));
            Assert.assertFalse(commonGoalCard.isSatisfied(library));
        });
    }


    /**
     * Sixth test: empty
     */
    @Test
    public void isSatisfied_correctInputEmptyTable_falseInOutput(){
        Assert.assertFalse(commonGoalCard.isSatisfied(library));
    }
//
}
