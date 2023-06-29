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

public class CommonGoalCard6Test {
    CommonGoalCard commonGoalCard;
    //Type never affects this algorithm
    Library library;
    /**Constructor
     * @author: Riccardo Figini
     * @throws IOException throws an exception*/
    public CommonGoalCard6Test() throws IOException {
        commonGoalCard = new CommonGoalCard6();
    }

    /**Library's set up with null
     * @author: Riccardo Figini
     * */
    @Before
    public void setUp(){
        library = new Library();
    }

    @Test
    public void isSatisfied_2SquareAndArectangle_trueInOutput(){
        assertDoesNotThrow(()->{
        library.insertCardsInLibrary(0,new ObjectCard("", Color.BLUE,Type.FIRST));
        library.insertCardsInLibrary(0,new ObjectCard("",Color.BLUE,Type.FIRST));
        library.insertCardsInLibrary(0,new ObjectCard("",Color.BLUE,Type.FIRST));
        library.insertCardsInLibrary(0,new ObjectCard("",Color.BLUE,Type.FIRST));
        library.insertCardsInLibrary(0,new ObjectCard("",Color.WHITE,Type.FIRST));
        library.insertCardsInLibrary(0,new ObjectCard("",Color.WHITE,Type.FIRST));
        library.insertCardsInLibrary(1,new ObjectCard("",Color.PINK,Type.FIRST));
        library.insertCardsInLibrary(1,new ObjectCard("",Color.PINK,Type.FIRST));
        library.insertCardsInLibrary(1,new ObjectCard("",Color.PINK,Type.FIRST));
        library.insertCardsInLibrary(1,new ObjectCard("",Color.PINK,Type.FIRST));
        library.insertCardsInLibrary(1,new ObjectCard("",Color.WHITE,Type.FIRST));
        library.insertCardsInLibrary(1,new ObjectCard("",Color.WHITE,Type.FIRST));

        library.insertCardsInLibrary(3,new ObjectCard("",Color.GREEN,Type.FIRST));
        library.insertCardsInLibrary(3,new ObjectCard("",Color.GREEN,Type.FIRST));
        library.insertCardsInLibrary(4,new ObjectCard("",Color.GREEN,Type.FIRST));
        library.insertCardsInLibrary(4,new ObjectCard("",Color.GREEN,Type.FIRST));
        Assert.assertTrue(commonGoalCard.isSatisfied(library));});
    }
    @Test
    public void isSatisfied_IntersectedTwoSquare_falseInOutput(){
        assertDoesNotThrow(()->{
        library.insertCardsInLibrary(0,new ObjectCard("",Color.PINK,Type.FIRST));
        library.insertCardsInLibrary(0,new ObjectCard("",Color.BLUE,Type.FIRST));
        library.insertCardsInLibrary(0,new ObjectCard("",Color.PINK,Type.FIRST));
        library.insertCardsInLibrary(0,new ObjectCard("",Color.BLUE,Type.FIRST));
        library.insertCardsInLibrary(0,new ObjectCard("",Color.WHITE,Type.FIRST));
        library.insertCardsInLibrary(0,new ObjectCard("",Color.WHITE,Type.FIRST));

        library.insertCardsInLibrary(1,new ObjectCard("",Color.BLUE,Type.FIRST));
        library.insertCardsInLibrary(1,new ObjectCard("",Color.PINK,Type.FIRST));
        library.insertCardsInLibrary(1,new ObjectCard("",Color.BLUE,Type.FIRST));
        library.insertCardsInLibrary(1,new ObjectCard("",Color.WHITE,Type.FIRST));
        library.insertCardsInLibrary(1,new ObjectCard("",Color.WHITE,Type.FIRST));
        library.insertCardsInLibrary(1,new ObjectCard("",Color.WHITE,Type.FIRST));

        library.insertCardsInLibrary(2,new ObjectCard("",Color.PINK,Type.FIRST));
        library.insertCardsInLibrary(2,new ObjectCard("",Color.BLUE,Type.FIRST));
        library.insertCardsInLibrary(2,new ObjectCard("",Color.PINK,Type.FIRST));
        library.insertCardsInLibrary(2,new ObjectCard("",Color.WHITE,Type.FIRST));
        library.insertCardsInLibrary(2,new ObjectCard("",Color.WHITE,Type.FIRST));
        library.insertCardsInLibrary(2,new ObjectCard("",Color.BLUE,Type.FIRST));

        Assert.assertFalse(commonGoalCard.isSatisfied(library));});
    }

    @Test
    public void isSatisfied_FullMatrix2SquareInCorners_trueInOutput(){
        assertDoesNotThrow(()->{
        library.insertCardsInLibrary(0,new ObjectCard("",Color.PINK,Type.FIRST));
        library.insertCardsInLibrary(0,new ObjectCard("",Color.BLUE,Type.FIRST));
        library.insertCardsInLibrary(0,new ObjectCard("",Color.PINK,Type.FIRST));
        library.insertCardsInLibrary(0,new ObjectCard("",Color.BLUE,Type.FIRST));
        library.insertCardsInLibrary(0,new ObjectCard("",Color.WHITE,Type.FIRST));
        library.insertCardsInLibrary(0,new ObjectCard("",Color.WHITE,Type.FIRST));

        library.insertCardsInLibrary(1,new ObjectCard("",Color.BLUE,Type.FIRST));
        library.insertCardsInLibrary(1,new ObjectCard("",Color.PINK,Type.FIRST));
        library.insertCardsInLibrary(1,new ObjectCard("",Color.BLUE,Type.FIRST));
        library.insertCardsInLibrary(1,new ObjectCard("",Color.PINK,Type.FIRST));
        library.insertCardsInLibrary(1,new ObjectCard("",Color.WHITE,Type.FIRST));
        library.insertCardsInLibrary(1,new ObjectCard("",Color.WHITE,Type.FIRST));

        library.insertCardsInLibrary(2,new ObjectCard("",Color.PINK,Type.FIRST));
        library.insertCardsInLibrary(2,new ObjectCard("",Color.BLUE,Type.FIRST));
        library.insertCardsInLibrary(2,new ObjectCard("",Color.PINK,Type.FIRST));
        library.insertCardsInLibrary(2,new ObjectCard("",Color.BLUE,Type.FIRST));
        library.insertCardsInLibrary(2,new ObjectCard("",Color.PINK,Type.FIRST));
        library.insertCardsInLibrary(2,new ObjectCard("",Color.BLUE,Type.FIRST));

        library.insertCardsInLibrary(3,new ObjectCard("",Color.GREEN,Type.FIRST));
        library.insertCardsInLibrary(3,new ObjectCard("",Color.GREEN,Type.FIRST));
        library.insertCardsInLibrary(3,new ObjectCard("",Color.BLUE,Type.FIRST));
        library.insertCardsInLibrary(3,new ObjectCard("",Color.PINK,Type.FIRST));
        library.insertCardsInLibrary(3,new ObjectCard("",Color.BLUE,Type.FIRST));
        library.insertCardsInLibrary(3,new ObjectCard("",Color.PINK,Type.FIRST));

        library.insertCardsInLibrary(4,new ObjectCard("",Color.GREEN,Type.FIRST));
        library.insertCardsInLibrary(4,new ObjectCard("",Color.GREEN,Type.FIRST));
        library.insertCardsInLibrary(4,new ObjectCard("",Color.PINK,Type.FIRST));
        library.insertCardsInLibrary(4,new ObjectCard("",Color.BLUE,Type.FIRST));
        library.insertCardsInLibrary(4,new ObjectCard("",Color.PINK,Type.FIRST));
        library.insertCardsInLibrary(4,new ObjectCard("",Color.BLUE,Type.FIRST));
        Assert.assertTrue(commonGoalCard.isSatisfied(library));});
    }
    @Test
    public void isSatisfied_FullMatrix1Square_falseInOutput(){
        assertDoesNotThrow(()->{
        library.insertCardsInLibrary(0,new ObjectCard("",Color.YELLOW,Type.FIRST));
        library.insertCardsInLibrary(0,new ObjectCard("",Color.LIGHTBLUE,Type.FIRST));
        library.insertCardsInLibrary(0,new ObjectCard("",Color.YELLOW,Type.FIRST));
        library.insertCardsInLibrary(0,new ObjectCard("",Color.LIGHTBLUE,Type.FIRST));
        library.insertCardsInLibrary(0,new ObjectCard("",Color.YELLOW,Type.FIRST));
        library.insertCardsInLibrary(0,new ObjectCard("",Color.LIGHTBLUE,Type.FIRST));

        library.insertCardsInLibrary(1,new ObjectCard("",Color.PINK,Type.FIRST));
        library.insertCardsInLibrary(1,new ObjectCard("",Color.GREEN,Type.FIRST));
        library.insertCardsInLibrary(1,new ObjectCard("",Color.PINK,Type.FIRST));
        library.insertCardsInLibrary(1,new ObjectCard("",Color.GREEN,Type.FIRST));
        library.insertCardsInLibrary(1,new ObjectCard("",Color.PINK,Type.FIRST));
        library.insertCardsInLibrary(1,new ObjectCard("",Color.GREEN,Type.FIRST));

        library.insertCardsInLibrary(2,new ObjectCard("",Color.YELLOW,Type.FIRST));
        library.insertCardsInLibrary(2,new ObjectCard("",Color.LIGHTBLUE,Type.FIRST));
        library.insertCardsInLibrary(2,new ObjectCard("",Color.WHITE,Type.FIRST));
        library.insertCardsInLibrary(2,new ObjectCard("",Color.WHITE,Type.FIRST));
        library.insertCardsInLibrary(2,new ObjectCard("",Color.YELLOW,Type.FIRST));
        library.insertCardsInLibrary(2,new ObjectCard("",Color.LIGHTBLUE,Type.FIRST));

        library.insertCardsInLibrary(3,new ObjectCard("",Color.PINK,Type.FIRST));
        library.insertCardsInLibrary(3,new ObjectCard("",Color.GREEN,Type.FIRST));
        library.insertCardsInLibrary(3,new ObjectCard("",Color.WHITE,Type.FIRST));
        library.insertCardsInLibrary(3,new ObjectCard("",Color.WHITE,Type.FIRST));
        library.insertCardsInLibrary(3,new ObjectCard("",Color.PINK,Type.FIRST));
        library.insertCardsInLibrary(3,new ObjectCard("",Color.GREEN,Type.FIRST));

        Assert.assertFalse(commonGoalCard.isSatisfied(library));});
    }
    @Test
    public void isSatisfied_correctInput_falseInOutput(){
        Assert.assertFalse(commonGoalCard.isSatisfied(library));
    }


}