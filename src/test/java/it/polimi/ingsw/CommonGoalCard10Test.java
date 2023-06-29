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
public class CommonGoalCard10Test {
    CommonGoalCard commonGoalCard;
    Library library;
    /**Constructor
     * @author: Riccardo Figini
     * @throws IOException throws an exception*/
    public CommonGoalCard10Test() throws IOException {
        commonGoalCard = new CommonGoalCard10();

    }

    @Before
    public void setUp(){
        library = new Library();
    }


    /**
     * First Test: 8 object cards of the same type and the other cells are empty.
     * @author: Alberto Aniballi
     * */
    @Test
    public void isSatisfied_correctInputOtherCellsEmpty_trueInOutput(){
        assertDoesNotThrow(()-> {
        library.insertCardsInLibrary(0,new ObjectCard("", Color.PINK,Type.FIRST));
        library.insertCardsInLibrary(0,new ObjectCard("",Color.PINK,Type.FIRST));
        library.insertCardsInLibrary(0,new ObjectCard("",Color.PINK,Type.FIRST));

        library.insertCardsInLibrary(1,new ObjectCard("",Color.PINK,Type.FIRST));
        library.insertCardsInLibrary(1,new ObjectCard("",Color.PINK,Type.FIRST));

        library.insertCardsInLibrary(2,new ObjectCard("",Color.PINK,Type.FIRST));
        library.insertCardsInLibrary(2,new ObjectCard("",Color.PINK,Type.FIRST));
        library.insertCardsInLibrary(2,new ObjectCard("",Color.PINK,Type.FIRST));
        Assert.assertTrue(commonGoalCard.isSatisfied(library));});
    }

    /**
     * Second Test: 8 object cards of the same type and the other cells are filled .
     * @author: Alberto Aniballi
     * */
    @Test
    public void isSatisfied_correctInputOtherCellsfilled_trueInOutput(){
        assertDoesNotThrow(()-> {
            library.insertCardsInLibrary(0, new ObjectCard("", Color.PINK, Type.FIRST));
            library.insertCardsInLibrary(0, new ObjectCard("", Color.PINK, Type.FIRST));
            library.insertCardsInLibrary(0, new ObjectCard("", Color.PINK, Type.FIRST));

            library.insertCardsInLibrary(1, new ObjectCard("", Color.PINK, Type.FIRST));
            library.insertCardsInLibrary(1, new ObjectCard("", Color.PINK, Type.FIRST));

            library.insertCardsInLibrary(2, new ObjectCard("", Color.PINK, Type.FIRST));
            library.insertCardsInLibrary(2, new ObjectCard("", Color.PINK, Type.FIRST));
            library.insertCardsInLibrary(2, new ObjectCard("", Color.PINK, Type.FIRST));

            library.insertCardsInLibrary(0, new ObjectCard("", Color.WHITE, Type.FIRST));
            library.insertCardsInLibrary(0, new ObjectCard("", Color.YELLOW, Type.FIRST));
            library.insertCardsInLibrary(0, new ObjectCard("", Color.WHITE, Type.FIRST));

            library.insertCardsInLibrary(1, new ObjectCard("", Color.WHITE, Type.FIRST));
            library.insertCardsInLibrary(1, new ObjectCard("", Color.YELLOW, Type.FIRST));
            library.insertCardsInLibrary(1, new ObjectCard("", Color.BLUE, Type.FIRST));
            library.insertCardsInLibrary(1, new ObjectCard("", Color.YELLOW, Type.FIRST));

            library.insertCardsInLibrary(2, new ObjectCard("", Color.WHITE, Type.FIRST));
            library.insertCardsInLibrary(2, new ObjectCard("", Color.YELLOW, Type.FIRST));
            library.insertCardsInLibrary(2, new ObjectCard("", Color.WHITE, Type.FIRST));

            library.insertCardsInLibrary(3, new ObjectCard("", Color.BLUE, Type.FIRST));
            library.insertCardsInLibrary(3, new ObjectCard("", Color.GREEN, Type.FIRST));
            library.insertCardsInLibrary(3, new ObjectCard("", Color.BLUE, Type.FIRST));
            library.insertCardsInLibrary(3, new ObjectCard("", Color.GREEN, Type.FIRST));
            library.insertCardsInLibrary(3, new ObjectCard("", Color.BLUE, Type.FIRST));
            library.insertCardsInLibrary(3, new ObjectCard("", Color.GREEN, Type.FIRST));

            library.insertCardsInLibrary(4, new ObjectCard("", Color.BLUE, Type.FIRST));
            library.insertCardsInLibrary(4, new ObjectCard("", Color.GREEN, Type.FIRST));
            library.insertCardsInLibrary(4, new ObjectCard("", Color.BLUE, Type.FIRST));
            library.insertCardsInLibrary(4, new ObjectCard("", Color.GREEN, Type.FIRST));
            library.insertCardsInLibrary(4, new ObjectCard("", Color.BLUE, Type.FIRST));
            library.insertCardsInLibrary(4, new ObjectCard("", Color.GREEN, Type.FIRST));
            Assert.assertTrue(commonGoalCard.isSatisfied(library));
        });
    }

    /**
     * Third Test: seven object cards of the same type and others are empty, so the condition is not satisfied.
     * @author: Alberto Aniballi
     * */
    @Test
    public void isSatisfied_only7cards_falseInOutput(){
        assertDoesNotThrow(()-> {
            library.insertCardsInLibrary(0, new ObjectCard("", Color.PINK, Type.FIRST));
            library.insertCardsInLibrary(0, new ObjectCard("", Color.PINK, Type.FIRST));
            library.insertCardsInLibrary(0, new ObjectCard("", Color.PINK, Type.FIRST));

            library.insertCardsInLibrary(1, new ObjectCard("", Color.PINK, Type.FIRST));
            library.insertCardsInLibrary(1, new ObjectCard("", Color.PINK, Type.FIRST));

            library.insertCardsInLibrary(2, new ObjectCard("", Color.PINK, Type.FIRST));
            library.insertCardsInLibrary(2, new ObjectCard("", Color.PINK, Type.FIRST));
            Assert.assertFalse(commonGoalCard.isSatisfied(library));
        });
    }

    /**
     * Fourth Test: all rows are empty.
     * @author: Alberto Aniballi
     * */
    @Test
    public void isSatisfied_allColumnsEmpty_falseInOutput(){
        Assert.assertFalse(commonGoalCard.isSatisfied(library));
    }
}
