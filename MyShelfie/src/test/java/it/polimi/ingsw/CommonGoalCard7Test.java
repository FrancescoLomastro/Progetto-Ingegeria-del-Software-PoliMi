package it.polimi.ingsw;

import it.polimi.ingsw.model.Cards.CommonGoalCard;
import it.polimi.ingsw.model.Cards.ConcreteCommonCards.CommonGoalCard6;
import it.polimi.ingsw.model.Cards.ConcreteCommonCards.CommonGoalCard7;
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
public class CommonGoalCard7Test {

    CommonGoalCard commonGoalCard;
    //Type never affects this algorithm
    Type type = Type.FIRST;

    Library library;
    /**Constructor
     * @author: Riccardo Figini
     * @throws IOException throws an exception*/
    public CommonGoalCard7Test() throws IOException {
        commonGoalCard = new CommonGoalCard7();
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
     * First Test: two rows with 5 different types and the other cells are all empty.
     * @author: Alberto Aniballi
     * */
    @Test
    public void isSatisfied_correctInputOnlyTwoRows_trueInOutput(){
        insertElement(0,0,Color.BEIGE);
        insertElement(0,1,Color.BLUE);
        insertElement(0,2,Color.GREEN);
        insertElement(0,3,Color.PINK);
        insertElement(0,4,Color.LIGHTBLUE);
        insertElement(1,0,Color.BEIGE);
        insertElement(1,1,Color.BLUE);
        insertElement(1,2,Color.GREEN);
        insertElement(1,3,Color.PINK);
        insertElement(1,4,Color.LIGHTBLUE);
        Assert.assertTrue(commonGoalCard.isSatisfied(library));
    }

    /**
     * Second Test: two rows with 5 different types and the other cells are all filled with random objectCards.
     * @author: Alberto Aniballi
     * */
    @Test
    public void isSatisfied_correctInputOnlyTwoRowsOtherRowsRandom_trueInOutput(){
        insertElement(0,0,Color.BEIGE);
        insertElement(0,1,Color.BLUE);
        insertElement(0,2,Color.GREEN);
        insertElement(0,3,Color.PINK);
        insertElement(0,4,Color.LIGHTBLUE);
        insertElement(1,0,Color.BEIGE);
        insertElement(1,1,Color.BLUE);
        insertElement(1,2,Color.GREEN);
        insertElement(1,3,Color.PINK);
        insertElement(1,4,Color.LIGHTBLUE);
        fillEmptyPart();
        Assert.assertTrue(commonGoalCard.isSatisfied(library));
    }

    /**
     * Third Test: 5 rows have only 4 different type of objectCard so output is false.
     * @author: Alberto Aniballi
     * */
    @Test
    public void isSatisfied_correctInput5RowsWithFourTypes_falseInOutput(){
        insertElement(0,0,Color.BEIGE);
        insertElement(0,1,Color.BEIGE);
        insertElement(0,2,Color.GREEN);
        insertElement(0,3,Color.PINK);
        insertElement(0,4,Color.LIGHTBLUE);
        insertElement(1,0,Color.BEIGE);
        insertElement(1,1,Color.BLUE);
        insertElement(1,2,Color.BLUE);
        insertElement(1,3,Color.PINK);
        insertElement(1,4,Color.LIGHTBLUE);
        insertElement(2,0,Color.BEIGE);
        insertElement(2,1,Color.BLUE);
        insertElement(2,2,Color.GREEN);
        insertElement(2,3,Color.GREEN);
        insertElement(2,4,Color.LIGHTBLUE);
        insertElement(3,0,Color.BEIGE);
        insertElement(3,1,Color.BLUE);
        insertElement(3,2,Color.GREEN);
        insertElement(3,3,Color.PINK);
        insertElement(3,4,Color.PINK);
        insertElement(4,0,Color.BEIGE);
        insertElement(4,1,Color.BLUE);
        insertElement(4,2,Color.GREEN);
        insertElement(4,3,Color.LIGHTBLUE);
        insertElement(4,4,Color.LIGHTBLUE);
        Assert.assertFalse(commonGoalCard.isSatisfied(library));
    }

    /**
     * Fourth Test: All rows have 5 different types of objectCard so output is true.
     * @author: Alberto Aniballi
     * */
    @Test
    public void isSatisfied_correctInput5RowsWithFiveTypes_trueInOutput(){
        insertElement(0,0,Color.BEIGE);
        insertElement(0,1,Color.BLUE);
        insertElement(0,2,Color.GREEN);
        insertElement(0,3,Color.PINK);
        insertElement(0,4,Color.LIGHTBLUE);
        insertElement(1,0,Color.BEIGE);
        insertElement(1,1,Color.BLUE);
        insertElement(1,2,Color.GREEN);
        insertElement(1,3,Color.PINK);
        insertElement(1,4,Color.LIGHTBLUE);
        insertElement(2,0,Color.BEIGE);
        insertElement(2,1,Color.BLUE);
        insertElement(2,2,Color.GREEN);
        insertElement(2,3,Color.PINK);
        insertElement(2,4,Color.LIGHTBLUE);
        insertElement(3,0,Color.BEIGE);
        insertElement(3,1,Color.BLUE);
        insertElement(3,2,Color.GREEN);
        insertElement(3,3,Color.PINK);
        insertElement(3,4,Color.LIGHTBLUE);
        insertElement(4,0,Color.BEIGE);
        insertElement(4,1,Color.BLUE);
        insertElement(4,2,Color.GREEN);
        insertElement(4,3,Color.PINK);
        insertElement(4,4,Color.LIGHTBLUE);
        insertElement(5,0,Color.BEIGE);
        insertElement(5,1,Color.BLUE);
        insertElement(5,2,Color.GREEN);
        insertElement(5,3,Color.PINK);
        insertElement(5,4,Color.LIGHTBLUE);
        Assert.assertTrue(commonGoalCard.isSatisfied(library));
    }

    /**
     * Fifth Test: All rows have only 1 type of objectCard so output is false.
     * @author: Alberto Aniballi
     * */
    @Test
    public void isSatisfied_correctInput5RowsWithOnlyOneType_falseInOutput(){
        insertElement(0,0,Color.BEIGE);
        insertElement(0,1,Color.BEIGE);
        insertElement(0,2,Color.BEIGE);
        insertElement(0,3,Color.BEIGE);
        insertElement(0,4,Color.BEIGE);
        insertElement(1,0,Color.BEIGE);
        insertElement(1,1,Color.BEIGE);
        insertElement(1,2,Color.BEIGE);
        insertElement(1,3,Color.BEIGE);
        insertElement(1,4,Color.BEIGE);
        insertElement(2,0,Color.BEIGE);
        insertElement(2,1,Color.BEIGE);
        insertElement(2,2,Color.BEIGE);
        insertElement(2,3,Color.BEIGE);
        insertElement(2,4,Color.BEIGE);
        insertElement(3,0,Color.BEIGE);
        insertElement(3,1,Color.BEIGE);
        insertElement(3,2,Color.BEIGE);
        insertElement(3,3,Color.BEIGE);
        insertElement(3,4,Color.BEIGE);
        insertElement(4,0,Color.BEIGE);
        insertElement(4,1,Color.BEIGE);
        insertElement(4,2,Color.BEIGE);
        insertElement(4,3,Color.BEIGE);
        insertElement(4,4,Color.BEIGE);
        insertElement(5,0,Color.BEIGE);
        insertElement(5,1,Color.BEIGE);
        insertElement(5,2,Color.BEIGE);
        insertElement(5,3,Color.BEIGE);
        insertElement(5,4,Color.BEIGE);
        Assert.assertFalse(commonGoalCard.isSatisfied(library));
    }

    /**
     * Sixth Test: all rows are empty.
     * @author: Alberto Aniballi
     * */
    @Test
    public void isSatisfied_correctInputAllRowsEmpty_falseInOutput(){
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
