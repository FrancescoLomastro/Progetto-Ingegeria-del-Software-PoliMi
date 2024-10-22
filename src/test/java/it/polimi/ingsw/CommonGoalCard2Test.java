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

public class CommonGoalCard2Test {
    CommonGoalCard commonGoalCard;

    Library library;

    public CommonGoalCard2Test() throws IOException {
        commonGoalCard = new CommonGoalCard2();
    }
    /**Library's set up with null
     * @author: Riccardo Figini
     * */
    @Before
    public void setUp() {
        library = new Library();
    }

    /**
     * First test: 4 groups of 4 cards of same color -> answer true
     */
    @Test
    public void isSatisfied_4SeparateColumn_trueInOutput(){
        assertDoesNotThrow(()-> {
            library.insertCardsInLibrary(0,
                    new ObjectCard("", Color.BLUE, Type.FIRST),
                    new ObjectCard("", Color.GREEN, Type.FIRST),
                    new ObjectCard("", Color.WHITE, Type.FIRST),
                    new ObjectCard("", Color.WHITE, Type.FIRST),
                    new ObjectCard("", Color.WHITE, Type.FIRST),
                    new ObjectCard("", Color.WHITE, Type.FIRST));
            library.insertCardsInLibrary(1,
                    new ObjectCard("", Color.BLUE, Type.FIRST));

            library.insertCardsInLibrary(2,
                    new ObjectCard("", Color.BLUE, Type.FIRST),
                    new ObjectCard("", Color.YELLOW, Type.FIRST),
                    new ObjectCard("", Color.WHITE, Type.FIRST),
                    new ObjectCard("", Color.WHITE, Type.FIRST),
                    new ObjectCard("", Color.WHITE, Type.FIRST),
                    new ObjectCard("", Color.WHITE, Type.FIRST));

            library.insertCardsInLibrary(3,
                    new ObjectCard("", Color.BLUE, Type.FIRST));

            library.insertCardsInLibrary(4, new ObjectCard("", Color.BLUE, Type.FIRST));
            library.insertCardsInLibrary(4,
                    new ObjectCard("", Color.WHITE, Type.FIRST),
                    new ObjectCard("", Color.WHITE, Type.FIRST),
                    new ObjectCard("", Color.WHITE, Type.FIRST),
                    new ObjectCard("", Color.WHITE, Type.FIRST),
                    new ObjectCard("", Color.WHITE, Type.FIRST));
            Assert.assertTrue(commonGoalCard.isSatisfied(library));
        });
    }

    /**
     * Second test: 4 groups of 4 of different color -> answer true
     */
    @Test
    public void isSatisfied_4SeparateRows_trueInOutput(){
        assertDoesNotThrow(()-> {
            library.insertCardsInLibrary(0,
                    new ObjectCard("", Color.PINK, Type.FIRST),
                    new ObjectCard("", Color.BLUE, Type.FIRST),
                    new ObjectCard("", Color.LIGHTBLUE, Type.FIRST),
                    new ObjectCard("", Color.YELLOW, Type.FIRST),
                    new ObjectCard("", Color.LIGHTBLUE, Type.FIRST),
                    new ObjectCard("", Color.WHITE, Type.FIRST));
            library.insertCardsInLibrary(1,
                    new ObjectCard("", Color.PINK, Type.FIRST),
                    new ObjectCard("", Color.BLUE, Type.FIRST),
                    new ObjectCard("", Color.GREEN, Type.FIRST),
                    new ObjectCard("", Color.YELLOW, Type.FIRST),
                    new ObjectCard("", Color.GREEN, Type.FIRST),
                    new ObjectCard("", Color.WHITE, Type.FIRST));
            library.insertCardsInLibrary(2,
                    new ObjectCard("", Color.PINK, Type.FIRST),
                    new ObjectCard("", Color.BLUE, Type.FIRST),
                    new ObjectCard("", Color.LIGHTBLUE, Type.FIRST),
                    new ObjectCard("", Color.YELLOW, Type.FIRST),
                    new ObjectCard("", Color.LIGHTBLUE, Type.FIRST),
                    new ObjectCard("", Color.WHITE, Type.FIRST));
            library.insertCardsInLibrary(3,
                    new ObjectCard("", Color.PINK, Type.FIRST),
                    new ObjectCard("", Color.BLUE, Type.FIRST),
                    new ObjectCard("", Color.GREEN, Type.FIRST),
                    new ObjectCard("", Color.YELLOW, Type.FIRST),
                    new ObjectCard("", Color.GREEN, Type.FIRST),
                    new ObjectCard("", Color.WHITE, Type.FIRST));
            library.insertCardsInLibrary(4,
                    new ObjectCard("", Color.PINK, Type.FIRST),
                    new ObjectCard("", Color.BLUE, Type.FIRST),
                    new ObjectCard("", Color.WHITE, Type.FIRST),
                    new ObjectCard("", Color.YELLOW, Type.FIRST),
                    new ObjectCard("", Color.PINK, Type.FIRST),
                    new ObjectCard("", Color.WHITE, Type.FIRST));
            Assert.assertTrue(commonGoalCard.isSatisfied(library));
        });
    }

    /**
     * Third test: 3 groups of 4 -> answer false
     */
    @Test
    public void isSatisfied_3SeparateGroups_falseInOutput(){
        assertDoesNotThrow(()-> {
            library.insertCardsInLibrary(0,
                    new ObjectCard("", Color.PINK, Type.FIRST),
                    new ObjectCard("", Color.LIGHTBLUE, Type.FIRST),
                    new ObjectCard("", Color.WHITE, Type.FIRST),
                    new ObjectCard("", Color.WHITE, Type.FIRST),
                    new ObjectCard("", Color.WHITE, Type.FIRST),
                    new ObjectCard("", Color.WHITE, Type.FIRST));
            library.insertCardsInLibrary(1,
                    new ObjectCard("", Color.PINK, Type.FIRST),
                    new ObjectCard("", Color.LIGHTBLUE, Type.FIRST),
                    new ObjectCard("", Color.PINK, Type.FIRST),
                    new ObjectCard("", Color.YELLOW, Type.FIRST),
                    new ObjectCard("", Color.PINK, Type.FIRST),
                    new ObjectCard("", Color.BLUE, Type.FIRST));
            library.insertCardsInLibrary(2,
                    new ObjectCard("", Color.PINK, Type.FIRST),
                    new ObjectCard("", Color.BLUE, Type.FIRST),
                    new ObjectCard("", Color.BLUE, Type.FIRST),
                    new ObjectCard("", Color.BLUE, Type.FIRST),
                    new ObjectCard("", Color.BLUE, Type.FIRST),
                    new ObjectCard("", Color.BLUE, Type.FIRST));
            library.insertCardsInLibrary(4,
                    new ObjectCard("", Color.YELLOW, Type.FIRST),
                    new ObjectCard("", Color.BLUE, Type.FIRST),
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
    public void isSatisfied_2Groups_falseInOutput(){
        assertDoesNotThrow(()-> {
            library.insertCardsInLibrary(0,
                    new ObjectCard("", Color.WHITE, Type.FIRST),
                    new ObjectCard("", Color.WHITE, Type.FIRST),
                    new ObjectCard("", Color.WHITE, Type.FIRST),
                    new ObjectCard("", Color.WHITE, Type.FIRST),
                    new ObjectCard("", Color.WHITE, Type.FIRST),
                    new ObjectCard("", Color.WHITE, Type.FIRST));
            library.insertCardsInLibrary(1,
                    new ObjectCard("", Color.GREEN, Type.FIRST),
                    new ObjectCard("", Color.GREEN, Type.FIRST),
                    new ObjectCard("", Color.GREEN, Type.FIRST),
                    new ObjectCard("", Color.GREEN, Type.FIRST),
                    new ObjectCard("", Color.GREEN, Type.FIRST),
                    new ObjectCard("", Color.GREEN, Type.FIRST));
            Assert.assertFalse(commonGoalCard.isSatisfied(library));
        });
    }

    /**
     * Fifth test: no groups of same color
     */
    @Test
    public void isSatisfied_noGroups_falseInOutput(){
        assertDoesNotThrow(()-> {
            library.insertCardsInLibrary(0,
                    new ObjectCard("", Color.WHITE, Type.FIRST),
                    new ObjectCard("", Color.PINK, Type.FIRST),
                    new ObjectCard("", Color.LIGHTBLUE, Type.FIRST),
                    new ObjectCard("", Color.BLUE, Type.FIRST),
                    new ObjectCard("", Color.YELLOW, Type.FIRST),
                    new ObjectCard("", Color.WHITE, Type.FIRST));
            library.insertCardsInLibrary(1,
                    new ObjectCard("", Color.GREEN, Type.FIRST),
                    new ObjectCard("", Color.WHITE, Type.FIRST),
                    new ObjectCard("", Color.PINK, Type.FIRST),
                    new ObjectCard("", Color.LIGHTBLUE, Type.FIRST),
                    new ObjectCard("", Color.BLUE, Type.FIRST),
                    new ObjectCard("", Color.YELLOW, Type.FIRST));
            library.insertCardsInLibrary(2,
                    new ObjectCard("", Color.YELLOW, Type.FIRST),
                    new ObjectCard("", Color.GREEN, Type.FIRST),
                    new ObjectCard("", Color.WHITE, Type.FIRST),
                    new ObjectCard("", Color.PINK, Type.FIRST),
                    new ObjectCard("", Color.LIGHTBLUE, Type.FIRST),
                    new ObjectCard("", Color.BLUE, Type.FIRST));
            library.insertCardsInLibrary(3,
                    new ObjectCard("", Color.BLUE, Type.FIRST),
                    new ObjectCard("", Color.YELLOW, Type.FIRST),
                    new ObjectCard("", Color.GREEN, Type.FIRST),
                    new ObjectCard("", Color.WHITE, Type.FIRST),
                    new ObjectCard("", Color.PINK, Type.FIRST),
                    new ObjectCard("", Color.LIGHTBLUE, Type.FIRST));
            library.insertCardsInLibrary(4,
                    new ObjectCard("", Color.LIGHTBLUE, Type.FIRST),
                    new ObjectCard("", Color.BLUE, Type.FIRST),
                    new ObjectCard("", Color.YELLOW, Type.FIRST),
                    new ObjectCard("", Color.GREEN, Type.FIRST),
                    new ObjectCard("", Color.WHITE, Type.FIRST),
                    new ObjectCard("", Color.PINK, Type.FIRST));

            Assert.assertFalse(commonGoalCard.isSatisfied(library));
        });
    }

    /**
     * Sixth test: empty
     */
    @Test
    public void isSatisfied_emptyLibrary_falseInOutput(){
        Assert.assertFalse(commonGoalCard.isSatisfied(library));
    }

}
