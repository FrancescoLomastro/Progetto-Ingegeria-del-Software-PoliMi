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
    public void isSatisfied_correctInputCheckFisrtLastColumn_trueInOutput(){
        for(int i=0; i<library.getNumberOfRows(); i++)
            library.insertCardsInLibrary(0,new ObjectCard("",Color.getEnumFromRelativeInt(i),Type.FIRST));
        for(int i=0; i<library.getNumberOfRows(); i++)
            library.insertCardsInLibrary(4,new ObjectCard("",Color.getEnumFromRelativeInt(i),Type.FIRST));
        Assert.assertTrue(commonGoalCard.isSatisfied(library));
    }

    @Test
    public void isSatisfied_correctInputCheckSecondFourthColumn_trueInOutput(){
        for(int i=0; i<library.getNumberOfRows(); i++)
            library.insertCardsInLibrary(1,new ObjectCard("",Color.getEnumFromRelativeInt(i),Type.FIRST));
        for(int i=0; i<library.getNumberOfRows(); i++)
            library.insertCardsInLibrary(3,new ObjectCard("",Color.getEnumFromRelativeInt(i),Type.FIRST));
        Assert.assertTrue(commonGoalCard.isSatisfied(library));
    }
    @Test
    public void isSatisfied_correctInputCheckthirdFourthColumn_trueInOutput(){
        for(int i=0; i<library.getNumberOfRows(); i++)
            library.insertCardsInLibrary(2,new ObjectCard("",Color.getEnumFromRelativeInt(i),Type.FIRST));
        for(int i=0; i<library.getNumberOfRows(); i++)
            library.insertCardsInLibrary(3,new ObjectCard("",Color.getEnumFromRelativeInt(i),Type.FIRST));
        Assert.assertTrue(commonGoalCard.isSatisfied(library));
    }

    @Test
    public void isSatisfied_correctInputEmptyMatrix_falseInOutput(){
        Assert.assertFalse(commonGoalCard.isSatisfied(library));
    }
    @Test
    public void isSatisfied_correctInputFullMatrix_falseInOutput(){
        for(int i=0; i<library.getNumberOfRows()-1; i++)
            for(int j=0; j<library.getNumberOfColumns(); j++)
                library.insertCardsInLibrary(j,new ObjectCard("",Color.getEnumFromRelativeInt(i),Type.FIRST));
        library.insertCardsInLibrary(0,new ObjectCard("",Color.getEnumFromRelativeInt(0),Type.FIRST));
        library.insertCardsInLibrary(1,new ObjectCard("",Color.getEnumFromRelativeInt(0),Type.FIRST));
        library.insertCardsInLibrary(2,new ObjectCard("",Color.getEnumFromRelativeInt(0),Type.FIRST));
        library.insertCardsInLibrary(4,new ObjectCard("",Color.getEnumFromRelativeInt(0),Type.FIRST));

        library.insertCardsInLibrary(3,new ObjectCard("",Color.getEnumFromRelativeInt(5),Type.FIRST));
        library.stampa();
        Assert.assertFalse(commonGoalCard.isSatisfied(library));
    }

}