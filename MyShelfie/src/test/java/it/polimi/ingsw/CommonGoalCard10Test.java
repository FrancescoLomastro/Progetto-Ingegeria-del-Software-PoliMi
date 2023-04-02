package it.polimi.ingsw;

import it.polimi.ingsw.model.Cards.CommonGoalCard;
import it.polimi.ingsw.model.Cards.ConcreteCommonCards.CommonGoalCard10;
import it.polimi.ingsw.model.Cards.ConcreteCommonCards.CommonGoalCard9;
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
public class CommonGoalCard10Test {
    CommonGoalCard commonGoalCard;
    //Type never affects this algorithm
    Type type = Type.FIRST;

    Library library;
    /**Constructor
     * @author: Riccardo Figini
     * @throws IOException throws an exception*/
    public CommonGoalCard10Test() throws IOException {
        commonGoalCard = new CommonGoalCard10();
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
     * First Test: 8 object cards of the same type and the other cells are empty.
     * @author: Alberto Aniballi
     * */
    @Test
    public void isSatisfied_oneCorrectInputOtherCellsEmpty_trueInOutput(){
        insertElement(0,0,Color.BEIGE);
        insertElement(1,0,Color.BEIGE);
        insertElement(2,0,Color.BEIGE);
        insertElement(1,1,Color.BEIGE);
        insertElement(1,2,Color.BEIGE);
        insertElement(0,2,Color.BEIGE);
        insertElement(0,1,Color.BEIGE);
        insertElement(2,2,Color.BEIGE);
        Assert.assertTrue(commonGoalCard.isSatisfied(library));
    }

    /**
     * Second Test: 8 object cards of the same type and the other cells are filled with random filler.
     * @author: Alberto Aniballi
     * */
    @Test
    public void isSatisfied_oneCorrectInputOtherCellsRandom_trueInOutput(){
        insertElement(0,0,Color.BEIGE);
        insertElement(1,0,Color.BEIGE);
        insertElement(2,0,Color.BEIGE);
        insertElement(1,1,Color.BEIGE);
        insertElement(1,2,Color.BEIGE);
        insertElement(0,2,Color.BEIGE);
        insertElement(0,1,Color.BEIGE);
        insertElement(2,2,Color.BEIGE);
        fillEmptyPart();
        Assert.assertTrue(commonGoalCard.isSatisfied(library));
    }

    /**
     * Third Test: seven object cards of the same type and others are empty, so the condition is not satisfied.
     * @author: Alberto Aniballi
     * */
    @Test
    public void isSatisfied_noCorrectInput_falseInOutput(){
        insertElement(0,0,Color.BEIGE);
        insertElement(1,0,Color.BEIGE);
        insertElement(2,0,Color.BEIGE);
        insertElement(1,1,Color.BEIGE);
        insertElement(1,2,Color.BEIGE);
        insertElement(0,2,Color.BEIGE);
        insertElement(0,1,Color.BEIGE);
        Assert.assertFalse(commonGoalCard.isSatisfied(library));
    }

    /**
     * Fourth Test: all rows are empty.
     * @author: Alberto Aniballi
     * */
    @Test
    public void isSatisfied_correctInputAllColumnsEmpty_falseInOutput(){
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
