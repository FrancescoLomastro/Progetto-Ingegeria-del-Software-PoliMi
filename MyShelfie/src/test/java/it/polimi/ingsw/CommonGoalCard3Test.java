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

public class CommonGoalCard3Test {
    CommonGoalCard commonGoalCard;
    Library library;
    /**Constructor
     * @author: Riccardo Figini*/
    public CommonGoalCard3Test()  {
        commonGoalCard = new CommonGoalCard3();
    }

    @Before
    public void setUp()
    {
        library = new Library();
    }

    /**It fills empty cell
     * @author: Riccardo Figini
     * */
    @Test
    public void isSatisfied_correctInputAllRowsHaveSameColor_trueInOutput(){
        for(int i=0; i<library.getNumberOfRows(); i++){
            for(int j=0; j<library.getNumberOfColumns(); j++){
                library.insertCardsInLibrary(j,new ObjectCard("",Color.getEnumFromRelativeInt(i),Type.FIRST));
            }
        }
        Assert.assertTrue(commonGoalCard.isSatisfied(library));
    }

    @Test
    public void isSatisfied_correctInputAllRowsHaveTwoDifferentColor_trueInOutput(){
        for(int i=0; i<library.getNumberOfRows(); i++){
            for(int j=0; j<library.getNumberOfColumns(); j++){
                library.insertCardsInLibrary(j,new ObjectCard("",Color.getEnumFromRelativeInt(j%2),Type.FIRST));
            }
        }
        Assert.assertTrue(commonGoalCard.isSatisfied(library));
    }

    @Test
    public void isSatisfied_correctInputAllRowsHaveThreeDifferentColor_trueInOutput(){
        for(int i=0; i<library.getNumberOfRows(); i++){
            for(int j=0; j<library.getNumberOfColumns(); j++){
                library.insertCardsInLibrary(j,new ObjectCard("",Color.getEnumFromRelativeInt(j%3),Type.FIRST));
            }
        }
        Assert.assertTrue(commonGoalCard.isSatisfied(library));
    }

    @Test
    public void isSatisfied_correctInputAllRowsHaveAllDifferentColor_falseInOutput(){
        for(int i=0; i<library.getNumberOfRows(); i++){
            for(int j=0; j<library.getNumberOfColumns(); j++){
                library.insertCardsInLibrary(j,new ObjectCard("",Color.getEnumFromRelativeInt(j),Type.FIRST));
            }
        }
        Assert.assertFalse(commonGoalCard.isSatisfied(library));
    }

    @Test
    public void isSatisfied_correctInputEmptyTable_falseInOutput(){
        Assert.assertFalse(commonGoalCard.isSatisfied(library));
    }

    @Test
    public void isSatisfied_onlyThreeRowsWithCorrectColors_falseInOutput(){
        for(int i=0; i<3; i++){
            for(int j=0; j<library.getNumberOfColumns(); j++){
                library.insertCardsInLibrary(j,new ObjectCard("",Color.getEnumFromRelativeInt(j%3),Type.FIRST));
            }
        }
        Assert.assertFalse(commonGoalCard.isSatisfied(library));
    }
    @Test
    public void isSatisfied_exactlyFourRowsWithCorrectColors_trueInOutput(){
        for(int i=0; i<4; i++){
            for(int j=0; j<library.getNumberOfColumns(); j++){
                library.insertCardsInLibrary(j,new ObjectCard("",Color.getEnumFromRelativeInt(j%3),Type.FIRST));
            }
        }
        Assert.assertTrue(commonGoalCard.isSatisfied(library));
    }


}