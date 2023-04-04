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

public class CommonGoalCard1Test {
    CommonGoalCard commonGoalCard;
    //Type never affects this algorithm
    Library library;
    /**Constructor
     * @author: Riccardo Figini
     * @throws IOException throws an exception*/
    public CommonGoalCard1Test() {
        commonGoalCard = new CommonGoalCard1();
    }

    /**Library's set up with null
     * @author: Riccardo Figini
     * */
    @Before
    public void setUp() {
        library = new Library();
    }


    @Test
    public void isSatisfied_correctInputCase0_trueInOutput(){
        library.insertCardsInLibrary(0,
                new ObjectCard("", Color.PINK , Type.FIRST),
                new ObjectCard("", Color.BLUE , Type.FIRST),
                new ObjectCard("", Color.PINK , Type.FIRST),
                new ObjectCard("", Color.BLUE , Type.FIRST),
                new ObjectCard("", Color.PINK , Type.FIRST),
                new ObjectCard("", Color.YELLOW , Type.FIRST));
        library.insertCardsInLibrary(1,
                new ObjectCard("", Color.BLUE , Type.FIRST),
                new ObjectCard("", Color.PINK , Type.FIRST),
                new ObjectCard("", Color.BLUE , Type.FIRST),
                new ObjectCard("", Color.PINK , Type.FIRST),
                new ObjectCard("", Color.YELLOW , Type.FIRST));
        library.insertCardsInLibrary(2,
                new ObjectCard("", Color.PINK , Type.FIRST),
                new ObjectCard("", Color.BLUE , Type.FIRST),
                new ObjectCard("", Color.PINK , Type.FIRST),
                new ObjectCard("", Color.YELLOW , Type.FIRST));
        library.insertCardsInLibrary(3,
                new ObjectCard("", Color.BLUE , Type.FIRST),
                new ObjectCard("", Color.PINK , Type.FIRST),
                new ObjectCard("", Color.YELLOW , Type.FIRST));
        library.insertCardsInLibrary(4,
                new ObjectCard("", Color.PINK , Type.FIRST),
                new ObjectCard("", Color.YELLOW , Type.FIRST));
        Assert.assertTrue(commonGoalCard.isSatisfied(library));
    }

    @Test
    public void isSatisfied_correctInputCase1_trueInOutput(){
        library.insertCardsInLibrary(0,
                new ObjectCard("", Color.BLUE , Type.FIRST),
                new ObjectCard("", Color.PINK , Type.FIRST),
                new ObjectCard("", Color.BLUE , Type.FIRST),
                new ObjectCard("", Color.PINK , Type.FIRST),
                new ObjectCard("", Color.GREEN , Type.FIRST));
        library.insertCardsInLibrary(1,
                new ObjectCard("", Color.PINK , Type.FIRST),
                new ObjectCard("", Color.BLUE , Type.FIRST),
                new ObjectCard("", Color.PINK , Type.FIRST),
                new ObjectCard("", Color.GREEN , Type.FIRST));
        library.insertCardsInLibrary(2,
                new ObjectCard("", Color.BLUE , Type.FIRST),
                new ObjectCard("", Color.PINK , Type.FIRST),
                new ObjectCard("", Color.GREEN , Type.FIRST));
        library.insertCardsInLibrary(3,
                new ObjectCard("", Color.PINK , Type.FIRST),
                new ObjectCard("", Color.GREEN , Type.FIRST));
        library.insertCardsInLibrary(4,
                new ObjectCard("", Color.GREEN , Type.FIRST));
        Assert.assertTrue(commonGoalCard.isSatisfied(library));
    }


    @Test
    public void isSatisfied_correctInputCase3_trueInOutput(){
        library.insertCardsInLibrary(0,
                new ObjectCard("", Color.GREEN , Type.FIRST),
                new ObjectCard("", Color.WHITE , Type.FIRST));
        library.insertCardsInLibrary(1,
                new ObjectCard("", Color.BLUE , Type.FIRST),
                new ObjectCard("", Color.PINK , Type.FIRST),
                new ObjectCard("", Color.WHITE , Type.FIRST));
        library.insertCardsInLibrary(2,
                new ObjectCard("", Color.PINK , Type.FIRST),
                new ObjectCard("", Color.BLUE , Type.FIRST),
                new ObjectCard("", Color.PINK , Type.FIRST),
                new ObjectCard("", Color.WHITE , Type.FIRST));
        library.insertCardsInLibrary(3,
                new ObjectCard("", Color.BLUE , Type.FIRST),
                new ObjectCard("", Color.PINK , Type.FIRST),
                new ObjectCard("", Color.BLUE , Type.FIRST),
                new ObjectCard("", Color.PINK , Type.FIRST),
                new ObjectCard("", Color.WHITE , Type.FIRST));
        library.insertCardsInLibrary(4,
                new ObjectCard("", Color.PINK , Type.FIRST),
                new ObjectCard("", Color.BLUE , Type.FIRST),
                new ObjectCard("", Color.PINK , Type.FIRST),
                new ObjectCard("", Color.BLUE , Type.FIRST),
                new ObjectCard("", Color.PINK , Type.FIRST),
                new ObjectCard("", Color.WHITE , Type.FIRST));

        Assert.assertTrue(commonGoalCard.isSatisfied(library));
    }

    @Test
    public void isSatisfied_correctInputCase4_trueInOutput(){
        library.insertCardsInLibrary(0,
                new ObjectCard("", Color.WHITE , Type.FIRST));
        library.insertCardsInLibrary(1,
                new ObjectCard("", Color.PINK , Type.FIRST),
                new ObjectCard("", Color.WHITE , Type.FIRST));
        library.insertCardsInLibrary(2,
                new ObjectCard("", Color.BLUE , Type.FIRST),
                new ObjectCard("", Color.PINK , Type.FIRST),
                new ObjectCard("", Color.WHITE , Type.FIRST));
        library.insertCardsInLibrary(3,
                new ObjectCard("", Color.PINK , Type.FIRST),
                new ObjectCard("", Color.BLUE , Type.FIRST),
                new ObjectCard("", Color.PINK , Type.FIRST),
                new ObjectCard("", Color.WHITE , Type.FIRST));
        library.insertCardsInLibrary(4,
                new ObjectCard("", Color.BLUE , Type.FIRST),
                new ObjectCard("", Color.PINK , Type.FIRST),
                new ObjectCard("", Color.BLUE , Type.FIRST),
                new ObjectCard("", Color.PINK , Type.FIRST),
                new ObjectCard("", Color.WHITE , Type.FIRST));
        Assert.assertTrue(commonGoalCard.isSatisfied(library));
    }

    @Test
    public void isSatisfied_correctInputEmptyTable_falseInOutput(){
        Assert.assertFalse(commonGoalCard.isSatisfied(library));
    }

    @Test
    public void isSatisfied_correctInput_falseInOutput(){
        library.insertCardsInLibrary(0,
                new ObjectCard("", Color.LIGHTBLUE , Type.FIRST),
                new ObjectCard("", Color.LIGHTBLUE , Type.FIRST),
                new ObjectCard("", Color.BLUE , Type.FIRST),
                new ObjectCard("", Color.PINK , Type.FIRST),
                new ObjectCard("", Color.GREEN , Type.FIRST),
                new ObjectCard("", Color.YELLOW , Type.FIRST));
        library.insertCardsInLibrary(1,
                new ObjectCard("", Color.PINK , Type.FIRST),
                new ObjectCard("", Color.WHITE , Type.FIRST),
                new ObjectCard("", Color.WHITE , Type.FIRST),
                new ObjectCard("", Color.GREEN , Type.FIRST),
                new ObjectCard("", Color.YELLOW , Type.FIRST));
        library.insertCardsInLibrary(2,
                new ObjectCard("", Color.BLUE , Type.FIRST),
                new ObjectCard("", Color.PINK , Type.FIRST),
                new ObjectCard("", Color.GREEN , Type.FIRST),
                new ObjectCard("", Color.YELLOW , Type.FIRST));
        library.insertCardsInLibrary(3,
                new ObjectCard("", Color.PINK , Type.FIRST),
                new ObjectCard("", Color.GREEN , Type.FIRST),
                new ObjectCard("", Color.YELLOW , Type.FIRST),
                new ObjectCard("", Color.WHITE , Type.FIRST),
                new ObjectCard("", Color.WHITE , Type.FIRST));
        library.insertCardsInLibrary(4,
                new ObjectCard("", Color.LIGHTBLUE , Type.FIRST),
                new ObjectCard("", Color.LIGHTBLUE , Type.FIRST),
                new ObjectCard("", Color.BLUE , Type.FIRST),
                new ObjectCard("", Color.PINK , Type.FIRST),
                new ObjectCard("", Color.WHITE , Type.FIRST),
                new ObjectCard("", Color.WHITE , Type.FIRST));
        Assert.assertFalse(commonGoalCard.isSatisfied(library));
    }
}