package it.polimi.ingsw;

import it.polimi.ingsw.model.Cards.CommonGoalCard;
import it.polimi.ingsw.model.Cards.ConcreteCommonCards.CommonGoalCard3;
import it.polimi.ingsw.model.Cards.ObjectCard;
import it.polimi.ingsw.model.Enums.Color;
import it.polimi.ingsw.model.Enums.Type;
import it.polimi.ingsw.model.Player.Library;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Random;

public class CommonGoalCard3Test {
    CommonGoalCard commonGoalCard;
    //Type never affects this algorithm
    Type type = Type.FIRST;

    Library library;
    /**Constructor
     * @author: Riccardo Figini*/
    public CommonGoalCard3Test() throws IOException {
        commonGoalCard = new CommonGoalCard3();
        library = new Library(5,6);
    }
    @Before
    public void setUp(){
        for(int i=0; i<library.getNumberOfRows(); i++){
            for(int j=0; j<library.getNumberOfColumns(); j++){
                library.insertCardInObjectCards(null, i,j);
            }
        }
    }
    /**Method used to insert ObjectCard in matrix
     * @author: Riccardo Figini
     * @param row row
     * @param col column
     * @param color color*/
    private void insertElement(int row, int col, Color color){
        ObjectCard objectCards = new ObjectCard("", color , type);
        library.insertCardInObjectCards(objectCards, row,col);
    }
    /**It fills empty cell
     * @author: Riccardo Figini
     * */
    private void fillEmptyPart(){
        Random random = new Random();
        for(int i=0; i<library.getNumberOfRows(); i++){
            for (int j=0; j<library.getNumberOfColumns(); j++){
                if(library.getLibrary()[i][j]==null)
                    insertElement(i, j, Color.getEnumFromRelativeInt(random.nextInt(5)));
            }
        }
    }

    @Test
    public void isSatisfied_correctInputAllRowsHaveSameColor_trueInOutpu(){
        for(int i=0; i<library.getNumberOfRows(); i++){
            for(int j=0; j<library.getNumberOfColumns(); j++){
                insertElement(i, j, Color.getEnumFromRelativeInt(i));
            }
        }
        Assert.assertTrue(commonGoalCard.isSatisfied(library));
    }
    @Test
    public void isSatisfied_correctInputAllRowsHaveTwoDifferentColor_trueInOutpu(){
        for(int i=0; i<library.getNumberOfRows(); i++){
            for(int j=0; j<library.getNumberOfColumns(); j++){
                insertElement(i, j, Color.getEnumFromRelativeInt(j%2));
            }
        }
        Assert.assertTrue(commonGoalCard.isSatisfied(library));
    }
    @Test
    public void isSatisfied_correctInputAllRowsHaveThreeDifferentColor_trueInOutpu(){
        for(int i=0; i<library.getNumberOfRows(); i++){
            for(int j=0; j<library.getNumberOfColumns(); j++){
                insertElement(i, j, Color.getEnumFromRelativeInt(j%3));
            }
        }
        Assert.assertTrue(commonGoalCard.isSatisfied(library));
    }
    @Test
    public void isSatisfied_correctInputAllRowsHaveAllDifferentColor_falseInOutpu(){
        for(int i=0; i<library.getNumberOfRows(); i++){
            for(int j=0; j<library.getNumberOfColumns(); j++){
                insertElement(i, j, Color.getEnumFromRelativeInt(j));
            }
        }
        Assert.assertFalse(commonGoalCard.isSatisfied(library));
    }
    @Test
    public void isSatisfied_correctInputEmptyTable_falseInOutput(){
        Assert.assertFalse(commonGoalCard.isSatisfied(library));
    }

    @After
    public void tearDown() {
        for(int i=0; i<library.getNumberOfRows(); i++){
            for(int j=0; j<library.getNumberOfColumns(); j++){
                library.insertCardInObjectCards(null, i,j);
            }
        }
    }

}
