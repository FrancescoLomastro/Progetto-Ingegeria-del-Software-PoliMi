package it.polimi.ingsw;

import it.polimi.ingsw.model.Cards.CommonGoalCard;
import it.polimi.ingsw.model.Cards.ConcreteCommonCards.CommonGoalCard0;
import it.polimi.ingsw.model.Cards.ConcreteCommonCards.CommonGoalCard1;
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

public class CommonGoalCard1Test {
    CommonGoalCard commonGoalCard;
    //Type never affects this algorithm
    Type type = Type.FIRST;

    Library library;
    /**Constructor
     * @author: Riccardo Figini
     * @throws IOException throws an exception*/
    public CommonGoalCard1Test() throws IOException {
        commonGoalCard = new CommonGoalCard1();
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
     * @param row
     * @param col column
     * @param color*/
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

    @Test
    public void isSatisfied_correctInputCase0_trueInOutput(){
        insertElement(0,0,Color.YELLOW);
        insertElement(1,1,Color.YELLOW);
        insertElement(2,2,Color.YELLOW);
        insertElement(3,3,Color.YELLOW);
        insertElement(4,4,Color.YELLOW);
        fillEmptyPart();
        Assert.assertTrue(commonGoalCard.isSatisfied(library));
    }
    @Test
    public void isSatisfied_correctInputCase1_trueInOutput(){
        insertElement(1,0,Color.GREEN);
        insertElement(2,1,Color.GREEN);
        insertElement(3,2,Color.GREEN);
        insertElement(4,3,Color.GREEN);
        insertElement(5,4,Color.GREEN);
        fillEmptyPart();
        Assert.assertTrue(commonGoalCard.isSatisfied(library));
    }
    @Test
    public void isSatisfied_correctInputCase3_trueInOutput(){
        insertElement(0,4,Color.BEIGE);
        insertElement(1,3,Color.BEIGE);
        insertElement(2,2,Color.BEIGE);
        insertElement(3,1,Color.BEIGE);
        insertElement(4,0,Color.BEIGE);
        fillEmptyPart();
        Assert.assertTrue(commonGoalCard.isSatisfied(library));
    }
    @Test
    public void isSatisfied_correctInputCase4_trueInOutput(){
        insertElement(1,4,Color.BEIGE);
        insertElement(2,3,Color.BEIGE);
        insertElement(3,2,Color.BEIGE);
        insertElement(4,1,Color.BEIGE);
        insertElement(5,0,Color.BEIGE);
        fillEmptyPart();
        Assert.assertTrue(commonGoalCard.isSatisfied(library));
    }
    @Test
    public void isSatisfied_correctInputEmptyTable_falseInOutput(){
        Assert.assertFalse(commonGoalCard.isSatisfied(library));
    }
    @Test
    public void isSatisfied_correctInput_falseInOutput(){
        insertElement(1,4,Color.BEIGE);
        insertElement(2,3,Color.BEIGE);
        insertElement(3,2,Color.BEIGE);
        insertElement(4,1,Color.BEIGE);
        insertElement(0,4,Color.BEIGE);
        insertElement(1,3,Color.BEIGE);
        insertElement(2,2,Color.BEIGE);
        insertElement(3,1,Color.BEIGE);
        insertElement(1,0,Color.GREEN);
        insertElement(2,1,Color.GREEN);
        insertElement(3,2,Color.GREEN);
        insertElement(4,3,Color.GREEN);
        insertElement(0,0,Color.YELLOW);
        insertElement(1,1,Color.YELLOW);
        insertElement(2,2,Color.YELLOW);
        insertElement(3,3,Color.YELLOW);
        insertElement(4,4, Color.LIGHTBLUE);
        insertElement(5,4, Color.LIGHTBLUE);
        insertElement(4,0, Color.LIGHTBLUE);
        insertElement(5,0, Color.LIGHTBLUE);
        fillEmptyPart();
        Assert.assertTrue(commonGoalCard.isSatisfied(library));
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
