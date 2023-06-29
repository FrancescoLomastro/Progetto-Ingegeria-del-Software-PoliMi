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
public class CommonGoalCard11Test {

    CommonGoalCard commonGoalCard;
    //Type never affects this algorithm
    Type type = Type.FIRST;

    Library library;
    public CommonGoalCard11Test() throws IOException {
        commonGoalCard = new CommonGoalCard11();

    }

    @Before
    public void setUp(){
        library = new Library();
    }


    @Test
    public void isSatisfied_IncreasingDisposition_trueInOutput(){
        assertDoesNotThrow(()-> {
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

        Assert.assertTrue(commonGoalCard.isSatisfied(library));});
    }

    @Test
    public void isSatisfied_IncreasingDispositionWithOnly4Columns_falseInOutput(){
        assertDoesNotThrow(()-> {
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

        Assert.assertFalse(commonGoalCard.isSatisfied(library));});

    }


    @Test
    public void isSatisfied_SecondIncreasingDisposition_trueInOutput(){
        assertDoesNotThrow(()-> {
        library.insertCardsInLibrary(0, new ObjectCard("", Color.PINK , Type.FIRST));
        library.insertCardsInLibrary(0, new ObjectCard("", Color.PINK , Type.FIRST));

        library.insertCardsInLibrary(1, new ObjectCard("", Color.PINK , Type.FIRST));
        library.insertCardsInLibrary(1, new ObjectCard("", Color.BLUE , Type.FIRST));
        library.insertCardsInLibrary(1, new ObjectCard("", Color.BLUE , Type.FIRST));

        library.insertCardsInLibrary(2, new ObjectCard("", Color.GREEN , Type.FIRST));
        library.insertCardsInLibrary(2, new ObjectCard("", Color.PINK , Type.FIRST));
        library.insertCardsInLibrary(2, new ObjectCard("", Color.YELLOW , Type.FIRST));
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
        library.insertCardsInLibrary(4, new ObjectCard("", Color.PINK , Type.FIRST));

        Assert.assertTrue(commonGoalCard.isSatisfied(library));});
    }


    @Test
    public void isSatisfied_DecreasingDisposition_trueInOutput(){
        assertDoesNotThrow(()-> {
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

        Assert.assertTrue(commonGoalCard.isSatisfied(library));});
    }


    @Test
    public void isSatisfied_decreasingDispositionWithOnly4Column_falseInOutput(){
        assertDoesNotThrow(()-> {
        library.insertCardsInLibrary(3, new ObjectCard("", Color.PINK , Type.FIRST));

        library.insertCardsInLibrary(2, new ObjectCard("", Color.GREEN , Type.FIRST));
        library.insertCardsInLibrary(2, new ObjectCard("", Color.PINK , Type.FIRST));

        library.insertCardsInLibrary(1, new ObjectCard("", Color.YELLOW , Type.FIRST));
        library.insertCardsInLibrary(1, new ObjectCard("", Color.BLUE , Type.FIRST));
        library.insertCardsInLibrary(1, new ObjectCard("", Color.YELLOW , Type.FIRST));

        library.insertCardsInLibrary(0, new ObjectCard("", Color.YELLOW , Type.FIRST));
        library.insertCardsInLibrary(0, new ObjectCard("", Color.BLUE , Type.FIRST));
        library.insertCardsInLibrary(0, new ObjectCard("", Color.YELLOW , Type.FIRST));
        library.insertCardsInLibrary(0, new ObjectCard("", Color.BLUE , Type.FIRST));

        Assert.assertFalse(commonGoalCard.isSatisfied(library));});
    }


    @Test
    public void isSatisfied_SecondDecreasingDisposition_trueInOutput(){
        assertDoesNotThrow(()-> {
        library.insertCardsInLibrary(4, new ObjectCard("", Color.PINK , Type.FIRST));
        library.insertCardsInLibrary(4, new ObjectCard("", Color.PINK , Type.FIRST));

        library.insertCardsInLibrary(3, new ObjectCard("", Color.PINK , Type.FIRST));
        library.insertCardsInLibrary(3, new ObjectCard("", Color.BLUE , Type.FIRST));
        library.insertCardsInLibrary(3, new ObjectCard("", Color.BLUE , Type.FIRST));

        library.insertCardsInLibrary(2, new ObjectCard("", Color.GREEN , Type.FIRST));
        library.insertCardsInLibrary(2, new ObjectCard("", Color.PINK , Type.FIRST));
        library.insertCardsInLibrary(2, new ObjectCard("", Color.YELLOW , Type.FIRST));
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
        library.insertCardsInLibrary(0, new ObjectCard("", Color.PINK , Type.FIRST));

        Assert.assertTrue(commonGoalCard.isSatisfied(library));});
    }

    @Test
    public void isSatisfied_DecreasingDispositionWithAnEmptyColumn_falseInOutput(){
        assertDoesNotThrow(()-> {
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

        Assert.assertFalse(commonGoalCard.isSatisfied(library));});
    }
    @Test
    public void isSatisfied_IncreasingDispositionWithAnEmptyColumn_falseInOutput(){
        assertDoesNotThrow(()-> {
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

        Assert.assertFalse(commonGoalCard.isSatisfied(library));});
    }

    @Test
    public void isSatisfied_IncreasingDispositionWithABadColumn_trueInOutput(){
        assertDoesNotThrow(()-> {
            library.insertCardsInLibrary(0, new ObjectCard("", Color.PINK, Type.FIRST));

            library.insertCardsInLibrary(1, new ObjectCard("", Color.PINK, Type.FIRST));
            library.insertCardsInLibrary(1, new ObjectCard("", Color.BLUE, Type.FIRST));

            library.insertCardsInLibrary(2, new ObjectCard("", Color.GREEN, Type.FIRST));
            library.insertCardsInLibrary(2, new ObjectCard("", Color.PINK, Type.FIRST));
            library.insertCardsInLibrary(2, new ObjectCard("", Color.YELLOW, Type.FIRST));

            library.insertCardsInLibrary(3, new ObjectCard("", Color.YELLOW, Type.FIRST));
            library.insertCardsInLibrary(3, new ObjectCard("", Color.BLUE, Type.FIRST));
            library.insertCardsInLibrary(3, new ObjectCard("", Color.YELLOW, Type.FIRST));
            library.insertCardsInLibrary(3, new ObjectCard("", Color.BLUE, Type.FIRST));
            library.insertCardsInLibrary(3, new ObjectCard("", Color.BLUE, Type.FIRST));

            library.insertCardsInLibrary(4, new ObjectCard("", Color.YELLOW, Type.FIRST));
            library.insertCardsInLibrary(4, new ObjectCard("", Color.BLUE, Type.FIRST));
            library.insertCardsInLibrary(4, new ObjectCard("", Color.YELLOW, Type.FIRST));
            library.insertCardsInLibrary(4, new ObjectCard("", Color.BLUE, Type.FIRST));
            library.insertCardsInLibrary(4, new ObjectCard("", Color.PINK, Type.FIRST));

            Assert.assertFalse(commonGoalCard.isSatisfied(library));

        });
    }

    @Test
    public void isSatisfied_DecreasingDispositionWithABadColumn_trueInOutput(){
        assertDoesNotThrow(()-> {
            library.insertCardsInLibrary(4, new ObjectCard("", Color.PINK, Type.FIRST));

            library.insertCardsInLibrary(3, new ObjectCard("", Color.PINK, Type.FIRST));
            library.insertCardsInLibrary(3, new ObjectCard("", Color.BLUE, Type.FIRST));

            library.insertCardsInLibrary(2, new ObjectCard("", Color.GREEN, Type.FIRST));
            library.insertCardsInLibrary(2, new ObjectCard("", Color.PINK, Type.FIRST));
            library.insertCardsInLibrary(2, new ObjectCard("", Color.YELLOW, Type.FIRST));

            library.insertCardsInLibrary(1, new ObjectCard("", Color.YELLOW, Type.FIRST));
            library.insertCardsInLibrary(1, new ObjectCard("", Color.BLUE, Type.FIRST));
            library.insertCardsInLibrary(1, new ObjectCard("", Color.YELLOW, Type.FIRST));
            library.insertCardsInLibrary(1, new ObjectCard("", Color.BLUE, Type.FIRST));
            library.insertCardsInLibrary(1, new ObjectCard("", Color.BLUE, Type.FIRST));

            library.insertCardsInLibrary(0, new ObjectCard("", Color.YELLOW, Type.FIRST));
            library.insertCardsInLibrary(0, new ObjectCard("", Color.BLUE, Type.FIRST));
            library.insertCardsInLibrary(0, new ObjectCard("", Color.YELLOW, Type.FIRST));
            library.insertCardsInLibrary(0, new ObjectCard("", Color.BLUE, Type.FIRST));
            library.insertCardsInLibrary(0, new ObjectCard("", Color.PINK, Type.FIRST));

            Assert.assertFalse(commonGoalCard.isSatisfied(library));
        });
    }


    @Test
    public void isSatisfied_allColumnsEmpty_falseInOutput(){
        Assert.assertFalse(commonGoalCard.isSatisfied(library));
    }


    @Test
    public void isSatisfied_AlternateInputAllRows_falseInOutput(){
        assertDoesNotThrow(()-> {
            library.insertCardsInLibrary(0, new ObjectCard("", Color.PINK, Type.FIRST));
            library.insertCardsInLibrary(0, new ObjectCard("", Color.BLUE, Type.FIRST));
            library.insertCardsInLibrary(0, new ObjectCard("", Color.PINK, Type.FIRST));
            library.insertCardsInLibrary(0, new ObjectCard("", Color.BLUE, Type.FIRST));
            library.insertCardsInLibrary(0, new ObjectCard("", Color.WHITE, Type.FIRST));
            library.insertCardsInLibrary(0, new ObjectCard("", Color.WHITE, Type.FIRST));

            library.insertCardsInLibrary(1, new ObjectCard("", Color.BLUE, Type.FIRST));
            library.insertCardsInLibrary(1, new ObjectCard("", Color.PINK, Type.FIRST));
            library.insertCardsInLibrary(1, new ObjectCard("", Color.WHITE, Type.FIRST));
            library.insertCardsInLibrary(1, new ObjectCard("", Color.WHITE, Type.FIRST));
            library.insertCardsInLibrary(1, new ObjectCard("", Color.BLUE, Type.FIRST));
            library.insertCardsInLibrary(1, new ObjectCard("", Color.PINK, Type.FIRST));

            library.insertCardsInLibrary(2, new ObjectCard("", Color.WHITE, Type.FIRST));
            library.insertCardsInLibrary(2, new ObjectCard("", Color.WHITE, Type.FIRST));
            library.insertCardsInLibrary(2, new ObjectCard("", Color.PINK, Type.FIRST));
            library.insertCardsInLibrary(2, new ObjectCard("", Color.BLUE, Type.FIRST));
            library.insertCardsInLibrary(2, new ObjectCard("", Color.WHITE, Type.FIRST));
            library.insertCardsInLibrary(2, new ObjectCard("", Color.WHITE, Type.FIRST));

            library.insertCardsInLibrary(3, new ObjectCard("", Color.BLUE, Type.FIRST));
            library.insertCardsInLibrary(3, new ObjectCard("", Color.PINK, Type.FIRST));
            library.insertCardsInLibrary(3, new ObjectCard("", Color.WHITE, Type.FIRST));
            library.insertCardsInLibrary(3, new ObjectCard("", Color.WHITE, Type.FIRST));
            library.insertCardsInLibrary(3, new ObjectCard("", Color.BLUE, Type.FIRST));
            library.insertCardsInLibrary(3, new ObjectCard("", Color.PINK, Type.FIRST));

            library.insertCardsInLibrary(4, new ObjectCard("", Color.PINK, Type.FIRST));
            library.insertCardsInLibrary(4, new ObjectCard("", Color.BLUE, Type.FIRST));
            library.insertCardsInLibrary(4, new ObjectCard("", Color.PINK, Type.FIRST));
            library.insertCardsInLibrary(4, new ObjectCard("", Color.BLUE, Type.FIRST));
            library.insertCardsInLibrary(4, new ObjectCard("", Color.WHITE, Type.FIRST));
            library.insertCardsInLibrary(4, new ObjectCard("", Color.WHITE, Type.FIRST));
            Assert.assertFalse(commonGoalCard.isSatisfied(library));
        });
    }
}
