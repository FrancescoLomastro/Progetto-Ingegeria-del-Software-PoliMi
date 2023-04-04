package it.polimi.ingsw;

import it.polimi.ingsw.model.Cards.CommonGoalCard;
import it.polimi.ingsw.model.Cards.ConcreteCommonCards.CommonGoalCard4;
import it.polimi.ingsw.model.Cards.ObjectCard;
import it.polimi.ingsw.model.Enums.Color;
import it.polimi.ingsw.model.Enums.Type;
import it.polimi.ingsw.model.Player.Library;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class CommonGoalCard4Test {
    CommonGoalCard commonGoalCard;

    Library library;
    /**Constructor
     * @author: Riccardo Figini
     * @throws IOException throws an exception*/
    public CommonGoalCard4Test() throws IOException {
        commonGoalCard = new CommonGoalCard4();
    }

    /**Library's set up with null
     * @author: Riccardo Figini
     * */
    @Before
    public void setUp(){
        library = new Library();
    }

    @Test
    public void isSatisfied_correctInput_trueInOutput(){
        library.insertCardsInLibrary(0,
                new ObjectCard("", Color.GREEN , Type.FIRST),
                new ObjectCard("", Color.YELLOW , Type.FIRST),
                new ObjectCard("", Color.YELLOW , Type.FIRST),
                new ObjectCard("", Color.YELLOW, Type.FIRST),
                new ObjectCard("", Color.YELLOW , Type.FIRST),
                new ObjectCard("", Color.GREEN , Type.FIRST));
        library.insertCardsInLibrary(4,
                new ObjectCard("", Color.GREEN , Type.FIRST),
                new ObjectCard("", Color.YELLOW , Type.FIRST),
                new ObjectCard("", Color.YELLOW , Type.FIRST),
                new ObjectCard("", Color.YELLOW, Type.FIRST),
                new ObjectCard("", Color.YELLOW , Type.FIRST),
                new ObjectCard("", Color.GREEN , Type.FIRST));
        Assert.assertTrue(commonGoalCard.isSatisfied(library));
    }

    @Test
    public void isSatisfied_correctInput_FalseInOutput(){
        library.insertCardsInLibrary(0,
                new ObjectCard("", Color.GREEN , Type.FIRST),
                new ObjectCard("", Color.YELLOW , Type.FIRST),
                new ObjectCard("", Color.YELLOW , Type.FIRST),
                new ObjectCard("", Color.YELLOW, Type.FIRST),
                new ObjectCard("", Color.YELLOW , Type.FIRST),
                new ObjectCard("", Color.GREEN , Type.FIRST));
        library.insertCardsInLibrary(4,
                new ObjectCard("", Color.GREEN , Type.FIRST),
                new ObjectCard("", Color.YELLOW , Type.FIRST),
                new ObjectCard("", Color.YELLOW , Type.FIRST),
                new ObjectCard("", Color.YELLOW, Type.FIRST),
                new ObjectCard("", Color.YELLOW , Type.FIRST),
                new ObjectCard("", Color.YELLOW , Type.FIRST));
        Assert.assertFalse(commonGoalCard.isSatisfied(library));
    }
    @Test
    public void isSatisfied_correctInput1_FalseInOutput1(){
        library.insertCardsInLibrary(0,
                new ObjectCard("", Color.GREEN , Type.FIRST),
                new ObjectCard("", Color.YELLOW , Type.FIRST),
                new ObjectCard("", Color.YELLOW , Type.FIRST),
                new ObjectCard("", Color.YELLOW, Type.FIRST),
                new ObjectCard("", Color.YELLOW , Type.FIRST),
                new ObjectCard("", Color.GREEN , Type.FIRST));
        library.insertCardsInLibrary(4,
                new ObjectCard("", Color.GREEN , Type.FIRST));
        Assert.assertFalse(commonGoalCard.isSatisfied(library));
    }
}
