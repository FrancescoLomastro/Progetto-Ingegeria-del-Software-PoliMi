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

public class CommonGoalCard5Test {
    CommonGoalCard commonGoalCard;

    Library library;
    /**Constructor
     * @author: Riccardo Figini
     * @throws IOException throws an exception*/
    public CommonGoalCard5Test() throws IOException {
        commonGoalCard = new CommonGoalCard5();
    }

    /**Library's set up with null
     * @author: Riccardo Figini
     * */
    @Before
    public void setUp(){
        library = new Library();
    }



    @Test
    public void isSatisfied_FirstAndLastColumn_trueInOutput(){
        assertDoesNotThrow(()-> {
            for (int i = 0; i < library.getNumberOfRows(); i++)
                library.insertCardsInLibrary(0, new ObjectCard("", Color.getEnumFromRelativeInt(i), Type.FIRST));
            for (int i = 0; i < library.getNumberOfRows(); i++)
                library.insertCardsInLibrary(4, new ObjectCard("", Color.getEnumFromRelativeInt(i), Type.FIRST));
            Assert.assertTrue(commonGoalCard.isSatisfied(library));

        });
    }

    @Test
    public void isSatisfied_SecondFourthColumn_trueInOutput(){
        assertDoesNotThrow(()-> {
            for (int i = 0; i < library.getNumberOfRows(); i++)
                library.insertCardsInLibrary(1, new ObjectCard("", Color.getEnumFromRelativeInt(i), Type.FIRST));
            for (int i = 0; i < library.getNumberOfRows(); i++)
                library.insertCardsInLibrary(3, new ObjectCard("", Color.getEnumFromRelativeInt(i), Type.FIRST));
            Assert.assertTrue(commonGoalCard.isSatisfied(library));

        });
    }
    @Test
    public void isSatisfied_ThirdFourthColumn_trueInOutput(){
        assertDoesNotThrow(()->{
        for(int i=0; i<library.getNumberOfRows(); i++)
            library.insertCardsInLibrary(2,new ObjectCard("",Color.getEnumFromRelativeInt(i),Type.FIRST));
        for(int i=0; i<library.getNumberOfRows(); i++)
            library.insertCardsInLibrary(3,new ObjectCard("",Color.getEnumFromRelativeInt(i),Type.FIRST));
        Assert.assertTrue(commonGoalCard.isSatisfied(library));

        });

    }

    @Test
    public void isSatisfied_EmptyMatrix_falseInOutput(){
        Assert.assertFalse(commonGoalCard.isSatisfied(library));
    }
    @Test
    public void isSatisfied_WrongFullMatrix_falseInOutput(){
        assertDoesNotThrow(()->{
        for(int i=0; i<library.getNumberOfRows()-1; i++)
            for(int j=0; j<library.getNumberOfColumns(); j++)
                library.insertCardsInLibrary(j,new ObjectCard("",Color.getEnumFromRelativeInt(i),Type.FIRST));
        library.insertCardsInLibrary(0,new ObjectCard("",Color.getEnumFromRelativeInt(0),Type.FIRST));
        library.insertCardsInLibrary(1,new ObjectCard("",Color.getEnumFromRelativeInt(0),Type.FIRST));
        library.insertCardsInLibrary(2,new ObjectCard("",Color.getEnumFromRelativeInt(0),Type.FIRST));
        library.insertCardsInLibrary(4,new ObjectCard("",Color.getEnumFromRelativeInt(0),Type.FIRST));

        library.insertCardsInLibrary(3,new ObjectCard("",Color.getEnumFromRelativeInt(5),Type.FIRST));
        Assert.assertFalse(commonGoalCard.isSatisfied(library));

        });
    }

}