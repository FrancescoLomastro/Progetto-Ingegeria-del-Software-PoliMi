package it.polimi.ingsw;

import it.polimi.ingsw.model.Cards.CommonGoalCard;
import it.polimi.ingsw.model.Cards.ConcreteCommonCards.CommonGoalCard11;
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

/**
 * @author: Alberto Aniballi
 */
public class CommonGoalCard11Test {

    CommonGoalCard commonGoalCard;
    //Type never affects this algorithm
    Type type = Type.FIRST;

    Library library;
    /**Constructor
     * @author: Riccardo Figini
     * @throws IOException throws an exception*/
    public CommonGoalCard11Test() throws IOException {
        commonGoalCard = new CommonGoalCard11();
        library = new Library(5,6);
    }

    /**Library's set up with null
     * @author: Riccardo Figini
     * */
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
                    insertElement(i, j, Color.getEnumFromRelativeInt(random.nextInt(6)));
            }
        }
    }

    /**
     * First Test Increasing: 0,1,2,3,4 -> answer true.
     * @author: Alberto Aniballi
     * */
    @Test
    public void isSatisfied_IncreasingCorrectInputOtherCellsEmpty_1_trueInOutput(){
        insertElement(0,1,Color.BEIGE);
        insertElement(0,2,Color.BEIGE);
        insertElement(1,2,Color.BEIGE);
        insertElement(0,3,Color.BEIGE);
        insertElement(1,3,Color.BEIGE);
        insertElement(2,3,Color.BEIGE);
        insertElement(0,4,Color.BEIGE);
        insertElement(1,4,Color.BEIGE);
        insertElement(2,4,Color.BEIGE);
        insertElement(3,4,Color.BEIGE);
        Assert.assertTrue(commonGoalCard.isSatisfied(library));
    }

    /**
     * Second Test Increasing: 1,2,3,4,5 -> answer true.
     * @author: Alberto Aniballi
     * */
    @Test
    public void isSatisfied_IncreasingCorrectInputOtherCellsEmpty_2_trueInOutput(){
        insertElement(0,0,Color.BEIGE);
        insertElement(0,1,Color.BEIGE);
        insertElement(1,1,Color.BEIGE);
        insertElement(0,2,Color.BEIGE);
        insertElement(1,2,Color.BEIGE);
        insertElement(2,2,Color.BEIGE);
        insertElement(0,3,Color.BEIGE);
        insertElement(1,3,Color.BEIGE);
        insertElement(2,3,Color.BEIGE);
        insertElement(3,3,Color.BEIGE);
        insertElement(0,4,Color.BEIGE);
        insertElement(1,4,Color.BEIGE);
        insertElement(2,4,Color.BEIGE);
        insertElement(3,4,Color.BEIGE);
        insertElement(4,4,Color.BEIGE);
        Assert.assertTrue(commonGoalCard.isSatisfied(library));
    }

    /**
     * Third Test Increasing: 2,3,4,5,6 -> answer true.
     * @author: Alberto Aniballi
     * */
    @Test
    public void isSatisfied_IncreasingCorrectInputOtherCellsEmpty_3_trueInOutput(){
        insertElement(0,0,Color.BEIGE);
        insertElement(1,0,Color.BEIGE);
        insertElement(0,1,Color.BEIGE);
        insertElement(1,1,Color.BEIGE);
        insertElement(2,1,Color.BEIGE);
        insertElement(0,2,Color.BEIGE);
        insertElement(1,2,Color.BEIGE);
        insertElement(2,2,Color.BEIGE);
        insertElement(3,2,Color.BEIGE);
        insertElement(0,3,Color.BEIGE);
        insertElement(1,3,Color.BEIGE);
        insertElement(2,3,Color.BEIGE);
        insertElement(3,3,Color.BEIGE);
        insertElement(4,3,Color.BEIGE);
        insertElement(0,4,Color.BEIGE);
        insertElement(1,4,Color.BEIGE);
        insertElement(2,4,Color.BEIGE);
        insertElement(3,4,Color.BEIGE);
        insertElement(4,4,Color.BEIGE);
        insertElement(5,4,Color.BEIGE);
        Assert.assertTrue(commonGoalCard.isSatisfied(library));
    }

    /**
     * Fourth Test Increasing: 1,1,2,3,4 -> answer false.
     * @author: Alberto Aniballi
     * */
    @Test
    public void isSatisfied_NoCorrectInputOtherCellsEmpty_1_trueInOutput(){
        insertElement(0,0,Color.BEIGE);
        insertElement(0,1,Color.BEIGE);
        insertElement(0,2,Color.BEIGE);
        insertElement(1,2,Color.BEIGE);
        insertElement(0,3,Color.BEIGE);
        insertElement(1,3,Color.BEIGE);
        insertElement(2,3,Color.BEIGE);
        insertElement(0,4,Color.BEIGE);
        insertElement(1,4,Color.BEIGE);
        insertElement(2,4,Color.BEIGE);
        insertElement(3,4,Color.BEIGE);
        Assert.assertFalse(commonGoalCard.isSatisfied(library));
    }

    /**
     * Fourth Test Increasing: 1,2,2,3,4 -> answer false.
     * @author: Alberto Aniballi
     * */
    @Test
    public void isSatisfied_NoCorrectInputOtherCellsEmpty_2_trueInOutput(){
        insertElement(0,0,Color.BEIGE);
        insertElement(0,1,Color.BEIGE);
        insertElement(1,1,Color.BEIGE);
        insertElement(0,2,Color.BEIGE);
        insertElement(1,2,Color.BEIGE);
        insertElement(0,3,Color.BEIGE);
        insertElement(1,3,Color.BEIGE);
        insertElement(2,3,Color.BEIGE);
        insertElement(0,4,Color.BEIGE);
        insertElement(1,4,Color.BEIGE);
        insertElement(2,4,Color.BEIGE);
        insertElement(3,4,Color.BEIGE);
        Assert.assertFalse(commonGoalCard.isSatisfied(library));
    }

    /**
     * Fifth Test Decreasing: 4,3,2,1,0 -> answer true.
     * @author: Alberto Aniballi
     * */
    @Test
    public void isSatisfied_DecreasingCorrectInputOtherCellsEmpty_1_trueInOutput(){
        insertElement(0,0,Color.BEIGE);
        insertElement(1,0,Color.BEIGE);
        insertElement(2,0,Color.BEIGE);
        insertElement(3,0,Color.BEIGE);
        insertElement(0,1,Color.BEIGE);
        insertElement(1,1,Color.BEIGE);
        insertElement(2,1,Color.BEIGE);
        insertElement(0,2,Color.BEIGE);
        insertElement(1,2,Color.BEIGE);
        insertElement(0,3,Color.BEIGE);
        Assert.assertTrue(commonGoalCard.isSatisfied(library));
    }

    /**
     * Sixth Test Decreasing: 5,4,3,2,1 -> answer true.
     * @author: Alberto Aniballi
     * */
    @Test
    public void isSatisfied_DecreasingCorrectInputOtherCellsEmpty_2_trueInOutput(){
        insertElement(0,0,Color.BEIGE);
        insertElement(1,0,Color.BEIGE);
        insertElement(2,0,Color.BEIGE);
        insertElement(3,0,Color.BEIGE);
        insertElement(4,0,Color.BEIGE);
        insertElement(0,1,Color.BEIGE);
        insertElement(1,1,Color.BEIGE);
        insertElement(2,1,Color.BEIGE);
        insertElement(3,1,Color.BEIGE);
        insertElement(0,2,Color.BEIGE);
        insertElement(1,2,Color.BEIGE);
        insertElement(2,2,Color.BEIGE);
        insertElement(0,3,Color.BEIGE);
        insertElement(1,3,Color.BEIGE);
        insertElement(0,4,Color.BEIGE);
        Assert.assertTrue(commonGoalCard.isSatisfied(library));
    }

    /**
     * Seventh Test Decreasing: 6,5,4,3,2,1 -> answer true.
     * @author: Alberto Aniballi
     * */
    @Test
    public void isSatisfied_DecreasingCorrectInputOtherCellsEmpty_3_trueInOutput(){
        insertElement(0,0,Color.BEIGE);
        insertElement(1,0,Color.BEIGE);
        insertElement(2,0,Color.BEIGE);
        insertElement(3,0,Color.BEIGE);
        insertElement(4,0,Color.BEIGE);
        insertElement(5,0,Color.BEIGE);
        insertElement(0,1,Color.BEIGE);
        insertElement(1,1,Color.BEIGE);
        insertElement(2,1,Color.BEIGE);
        insertElement(3,1,Color.BEIGE);
        insertElement(4,1,Color.BEIGE);
        insertElement(0,2,Color.BEIGE);
        insertElement(1,2,Color.BEIGE);
        insertElement(2,2,Color.BEIGE);
        insertElement(3,2,Color.BEIGE);
        insertElement(0,3,Color.BEIGE);
        insertElement(1,3,Color.BEIGE);
        insertElement(2,3,Color.BEIGE);
        insertElement(0,4,Color.BEIGE);
        insertElement(1,4,Color.BEIGE);
        Assert.assertTrue(commonGoalCard.isSatisfied(library));
    }

    /**
     * Eighth Test Decreasing: 6,5,4,3,3 -> answer false.
     * @author: Alberto Aniballi
     * */
    @Test
    public void isSatisfied_NoCorrectInputOtherCellsEmpty_3_trueInOutput(){
        insertElement(0,0,Color.BEIGE);
        insertElement(1,0,Color.BEIGE);
        insertElement(2,0,Color.BEIGE);
        insertElement(3,0,Color.BEIGE);
        insertElement(4,0,Color.BEIGE);
        insertElement(5,0,Color.BEIGE);
        insertElement(0,1,Color.BEIGE);
        insertElement(1,1,Color.BEIGE);
        insertElement(2,1,Color.BEIGE);
        insertElement(3,1,Color.BEIGE);
        insertElement(4,1,Color.BEIGE);
        insertElement(0,2,Color.BEIGE);
        insertElement(1,2,Color.BEIGE);
        insertElement(2,2,Color.BEIGE);
        insertElement(3,2,Color.BEIGE);
        insertElement(0,3,Color.BEIGE);
        insertElement(1,3,Color.BEIGE);
        insertElement(2,3,Color.BEIGE);
        insertElement(0,4,Color.BEIGE);
        insertElement(1,4,Color.BEIGE);
        insertElement(2,4,Color.BEIGE);
        Assert.assertFalse(commonGoalCard.isSatisfied(library));
    }

    /**
     * Ninth Test: all rows are empty.
     * @author: Alberto Aniballi
     * */
    @Test
    public void isSatisfied_correctInputAllColumnsEmpty_falseInOutput(){
        Assert.assertFalse(commonGoalCard.isSatisfied(library));
    }

    /**
     * Tenth Test: all rows full random.
     * @author: Alberto Aniballi
     * */
    @Test
    public void isSatisfied_RandomInputAllRows_falseInOutput(){
        fillEmptyPart();
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
