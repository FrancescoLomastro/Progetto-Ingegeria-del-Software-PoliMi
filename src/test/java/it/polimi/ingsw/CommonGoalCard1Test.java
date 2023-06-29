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

public class CommonGoalCard1Test {
    CommonGoalCard commonGoalCard;
    //Type never affects this algorithm
    Library library;
    public CommonGoalCard1Test() {
        commonGoalCard = new CommonGoalCard1();
    }


    @Before
    public void setUp() {
        library = new Library();
    }


    @Test
    public void isSatisfied_yellowDiagonalRowElevated_trueInOutput(){
        assertDoesNotThrow(()-> {
            library.insertCardsInLibrary(0,
                    new ObjectCard("", Color.PINK, Type.FIRST),
                    new ObjectCard("", Color.BLUE, Type.FIRST),
                    new ObjectCard("", Color.PINK, Type.FIRST),
                    new ObjectCard("", Color.BLUE, Type.FIRST),
                    new ObjectCard("", Color.PINK, Type.FIRST),
                    new ObjectCard("", Color.YELLOW, Type.FIRST));
            library.insertCardsInLibrary(1,
                    new ObjectCard("", Color.BLUE, Type.FIRST),
                    new ObjectCard("", Color.PINK, Type.FIRST),
                    new ObjectCard("", Color.BLUE, Type.FIRST),
                    new ObjectCard("", Color.PINK, Type.FIRST),
                    new ObjectCard("", Color.YELLOW, Type.FIRST));
            library.insertCardsInLibrary(2,
                    new ObjectCard("", Color.PINK, Type.FIRST),
                    new ObjectCard("", Color.BLUE, Type.FIRST),
                    new ObjectCard("", Color.PINK, Type.FIRST),
                    new ObjectCard("", Color.YELLOW, Type.FIRST));
            library.insertCardsInLibrary(3,
                    new ObjectCard("", Color.BLUE, Type.FIRST),
                    new ObjectCard("", Color.PINK, Type.FIRST),
                    new ObjectCard("", Color.YELLOW, Type.FIRST));
            library.insertCardsInLibrary(4,
                    new ObjectCard("", Color.PINK, Type.FIRST),
                    new ObjectCard("", Color.YELLOW, Type.FIRST));
            Assert.assertTrue(commonGoalCard.isSatisfied(library));
        });
    }

    @Test
    public void isSatisfied_GreenDiagonal_trueInOutput(){
        assertDoesNotThrow(()-> {
            library.insertCardsInLibrary(0,
                    new ObjectCard("", Color.BLUE, Type.FIRST),
                    new ObjectCard("", Color.PINK, Type.FIRST),
                    new ObjectCard("", Color.BLUE, Type.FIRST),
                    new ObjectCard("", Color.PINK, Type.FIRST),
                    new ObjectCard("", Color.GREEN, Type.FIRST));
            library.insertCardsInLibrary(1,
                    new ObjectCard("", Color.PINK, Type.FIRST),
                    new ObjectCard("", Color.BLUE, Type.FIRST),
                    new ObjectCard("", Color.PINK, Type.FIRST),
                    new ObjectCard("", Color.GREEN, Type.FIRST));
            library.insertCardsInLibrary(2,
                    new ObjectCard("", Color.BLUE, Type.FIRST),
                    new ObjectCard("", Color.PINK, Type.FIRST),
                    new ObjectCard("", Color.GREEN, Type.FIRST));
            library.insertCardsInLibrary(3,
                    new ObjectCard("", Color.PINK, Type.FIRST),
                    new ObjectCard("", Color.GREEN, Type.FIRST));
            library.insertCardsInLibrary(4,
                    new ObjectCard("", Color.GREEN, Type.FIRST));
            Assert.assertTrue(commonGoalCard.isSatisfied(library));
        });
    }


    @Test
    public void isSatisfied_whiteDiagonalRowElevated_trueInOutput(){
        assertDoesNotThrow(()-> {
            library.insertCardsInLibrary(0,
                    new ObjectCard("", Color.GREEN, Type.FIRST),
                    new ObjectCard("", Color.WHITE, Type.FIRST));
            library.insertCardsInLibrary(1,
                    new ObjectCard("", Color.BLUE, Type.FIRST),
                    new ObjectCard("", Color.PINK, Type.FIRST),
                    new ObjectCard("", Color.WHITE, Type.FIRST));
            library.insertCardsInLibrary(2,
                    new ObjectCard("", Color.PINK, Type.FIRST),
                    new ObjectCard("", Color.BLUE, Type.FIRST),
                    new ObjectCard("", Color.PINK, Type.FIRST),
                    new ObjectCard("", Color.WHITE, Type.FIRST));
            library.insertCardsInLibrary(3,
                    new ObjectCard("", Color.BLUE, Type.FIRST),
                    new ObjectCard("", Color.PINK, Type.FIRST),
                    new ObjectCard("", Color.BLUE, Type.FIRST),
                    new ObjectCard("", Color.PINK, Type.FIRST),
                    new ObjectCard("", Color.WHITE, Type.FIRST));
            library.insertCardsInLibrary(4,
                    new ObjectCard("", Color.PINK, Type.FIRST),
                    new ObjectCard("", Color.BLUE, Type.FIRST),
                    new ObjectCard("", Color.PINK, Type.FIRST),
                    new ObjectCard("", Color.BLUE, Type.FIRST),
                    new ObjectCard("", Color.PINK, Type.FIRST),
                    new ObjectCard("", Color.WHITE, Type.FIRST));

            Assert.assertTrue(commonGoalCard.isSatisfied(library));
        });
    }

    @Test
    public void isSatisfied_WhiteDiagonal_trueInOutput(){
        assertDoesNotThrow(()-> {
            library.insertCardsInLibrary(0,
                    new ObjectCard("", Color.WHITE, Type.FIRST));
            library.insertCardsInLibrary(1,
                    new ObjectCard("", Color.PINK, Type.FIRST),
                    new ObjectCard("", Color.WHITE, Type.FIRST));
            library.insertCardsInLibrary(2,
                    new ObjectCard("", Color.BLUE, Type.FIRST),
                    new ObjectCard("", Color.PINK, Type.FIRST),
                    new ObjectCard("", Color.WHITE, Type.FIRST));
            library.insertCardsInLibrary(3,
                    new ObjectCard("", Color.PINK, Type.FIRST),
                    new ObjectCard("", Color.BLUE, Type.FIRST),
                    new ObjectCard("", Color.PINK, Type.FIRST),
                    new ObjectCard("", Color.WHITE, Type.FIRST));
            library.insertCardsInLibrary(4,
                    new ObjectCard("", Color.BLUE, Type.FIRST),
                    new ObjectCard("", Color.PINK, Type.FIRST),
                    new ObjectCard("", Color.BLUE, Type.FIRST),
                    new ObjectCard("", Color.PINK, Type.FIRST),
                    new ObjectCard("", Color.WHITE, Type.FIRST));
            Assert.assertTrue(commonGoalCard.isSatisfied(library));
        });
    }

    @Test
    public void isSatisfied_correctInputEmptyTable_falseInOutput(){
        Assert.assertFalse(commonGoalCard.isSatisfied(library));
    }

    @Test
    public void isSatisfied_maxDiagonalOf4_falseInOutput(){
        assertDoesNotThrow(()-> {
            library.insertCardsInLibrary(0,
                    new ObjectCard("", Color.LIGHTBLUE, Type.FIRST),
                    new ObjectCard("", Color.LIGHTBLUE, Type.FIRST),
                    new ObjectCard("", Color.BLUE, Type.FIRST),
                    new ObjectCard("", Color.PINK, Type.FIRST),
                    new ObjectCard("", Color.GREEN, Type.FIRST),
                    new ObjectCard("", Color.YELLOW, Type.FIRST));
            library.insertCardsInLibrary(1,
                    new ObjectCard("", Color.PINK, Type.FIRST),
                    new ObjectCard("", Color.WHITE, Type.FIRST),
                    new ObjectCard("", Color.WHITE, Type.FIRST),
                    new ObjectCard("", Color.GREEN, Type.FIRST),
                    new ObjectCard("", Color.YELLOW, Type.FIRST));
            library.insertCardsInLibrary(2,
                    new ObjectCard("", Color.BLUE, Type.FIRST),
                    new ObjectCard("", Color.PINK, Type.FIRST),
                    new ObjectCard("", Color.GREEN, Type.FIRST),
                    new ObjectCard("", Color.YELLOW, Type.FIRST));
            library.insertCardsInLibrary(3,
                    new ObjectCard("", Color.PINK, Type.FIRST),
                    new ObjectCard("", Color.GREEN, Type.FIRST),
                    new ObjectCard("", Color.YELLOW, Type.FIRST),
                    new ObjectCard("", Color.WHITE, Type.FIRST),
                    new ObjectCard("", Color.WHITE, Type.FIRST));
            library.insertCardsInLibrary(4,
                    new ObjectCard("", Color.LIGHTBLUE, Type.FIRST),
                    new ObjectCard("", Color.LIGHTBLUE, Type.FIRST),
                    new ObjectCard("", Color.BLUE, Type.FIRST),
                    new ObjectCard("", Color.PINK, Type.FIRST),
                    new ObjectCard("", Color.WHITE, Type.FIRST),
                    new ObjectCard("", Color.WHITE, Type.FIRST));
            Assert.assertFalse(commonGoalCard.isSatisfied(library));
        });
    }
}