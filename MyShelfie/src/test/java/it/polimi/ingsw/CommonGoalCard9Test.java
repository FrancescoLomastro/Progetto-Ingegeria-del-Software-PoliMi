package it.polimi.ingsw;

import it.polimi.ingsw.model.Cards.CommonGoalCard;
import it.polimi.ingsw.model.Cards.ConcreteCommonCards.CommonGoalCard8;
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
public class CommonGoalCard9Test {
    CommonGoalCard commonGoalCard;
    //Type never affects this algorithm
    Type type = Type.FIRST;

    Library library;
    /**Constructor
     * @author: Riccardo Figini
     * @throws IOException throws an exception*/
    public CommonGoalCard9Test() throws IOException {
        commonGoalCard = new CommonGoalCard9();
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
     * First Test: one X combination.
     * @author: Alberto Aniballi
     * */
    @Test
    public void isSatisfied_oneCorrectInputOtherCellsEmpty_trueInOutput(){
        insertElement(0,0,Color.BEIGE);
        insertElement(1,0,Color.GREEN);
        insertElement(2,0,Color.BEIGE);
        insertElement(1,1,Color.BEIGE);
        insertElement(1,2,Color.GREEN);
        insertElement(0,2,Color.BEIGE);
        insertElement(0,1,Color.GREEN);
        insertElement(2,2,Color.BEIGE);
        insertElement(2,1,Color.GREEN);
        fillEmptyPart();
        Assert.assertTrue(commonGoalCard.isSatisfied(library));
    }

    /**
     * Second Test: no X combination
     * @author: Alberto Aniballi
     * */
    @Test
    public void isSatisfied_noCorrectInput_falseInOutput(){
        insertElement(0,0,Color.BEIGE);
        insertElement(0,1,Color.BEIGE);
        insertElement(0,2,Color.BEIGE);
        insertElement(0,3,Color.BEIGE);
        insertElement(0,4,Color.BEIGE);
        insertElement(1,0,Color.GREEN);
        insertElement(1,1,Color.GREEN);
        insertElement(1,2,Color.GREEN);
        insertElement(1,3,Color.GREEN);
        insertElement(1,4,Color.GREEN);
        insertElement(2,0,Color.BEIGE);
        insertElement(2,1,Color.BEIGE);
        insertElement(2,2,Color.BEIGE);
        insertElement(2,3,Color.BEIGE);
        insertElement(2,4,Color.BEIGE);
        insertElement(3,0,Color.GREEN);
        insertElement(3,1,Color.GREEN);
        insertElement(3,2,Color.GREEN);
        insertElement(3,3,Color.GREEN);
        insertElement(3,4,Color.GREEN);
        insertElement(4,0,Color.BEIGE);
        insertElement(4,1,Color.BEIGE);
        insertElement(4,2,Color.BEIGE);
        insertElement(4,3,Color.BEIGE);
        insertElement(4,4,Color.BEIGE);
        insertElement(5,0,Color.GREEN);
        insertElement(5,1,Color.GREEN);
        insertElement(5,2,Color.GREEN);
        insertElement(5,3,Color.GREEN);
        insertElement(5,4,Color.GREEN);
        Assert.assertFalse(commonGoalCard.isSatisfied(library));
    }

    /**
     * Third Test: Left up corner no same color.
     * @author: Alberto Aniballi
     * */
    @Test
    public void isSatisfied_noCorrectInputLeftUpCornerWrong_falseInOutput(){
        insertElement(0,0,Color.YELLOW);
        insertElement(1,0,Color.GREEN);
        insertElement(2,0,Color.BEIGE);
        insertElement(1,1,Color.BEIGE);
        insertElement(1,2,Color.GREEN);
        insertElement(0,2,Color.BEIGE);
        insertElement(0,1,Color.GREEN);
        insertElement(2,2,Color.BEIGE);
        insertElement(2,1,Color.GREEN);
        Assert.assertFalse(commonGoalCard.isSatisfied(library));
    }

    /**
     * Fourth Test: Left down corner no same color.
     * @author: Alberto Aniballi
     * */
    @Test
    public void isSatisfied_noCorrectInputLeftDownCornerWrong_falseInOutput(){
        insertElement(0,0,Color.BEIGE);
        insertElement(1,0,Color.GREEN);
        insertElement(2,0,Color.BLUE);
        insertElement(1,1,Color.BEIGE);
        insertElement(1,2,Color.GREEN);
        insertElement(0,2,Color.BEIGE);
        insertElement(0,1,Color.GREEN);
        insertElement(2,2,Color.BEIGE);
        insertElement(2,1,Color.GREEN);
        Assert.assertFalse(commonGoalCard.isSatisfied(library));
    }

    /**
     * Fifth Test: Right up corner no same color.
     * @author: Alberto Aniballi
     * */
    @Test
    public void isSatisfied_noCorrectInputRightUpCornerWrong_falseInOutput(){
        insertElement(0,0,Color.BEIGE);
        insertElement(1,0,Color.GREEN);
        insertElement(2,0,Color.BEIGE);
        insertElement(1,1,Color.BEIGE);
        insertElement(1,2,Color.GREEN);
        insertElement(0,2,Color.BLUE);
        insertElement(0,1,Color.GREEN);
        insertElement(2,2,Color.BEIGE);
        insertElement(2,1,Color.GREEN);
        Assert.assertFalse(commonGoalCard.isSatisfied(library));
    }

    /**
     * Sixth Test: Right down corner no same color.
     * @author: Alberto Aniballi
     * */
    @Test
    public void isSatisfied_noCorrectInputRightDownCornerWrong_falseInOutput(){
        insertElement(0,0,Color.BEIGE);
        insertElement(1,0,Color.GREEN);
        insertElement(2,0,Color.BEIGE);
        insertElement(1,1,Color.BEIGE);
        insertElement(1,2,Color.GREEN);
        insertElement(0,2,Color.BEIGE);
        insertElement(0,1,Color.GREEN);
        insertElement(2,2,Color.BLUE);
        insertElement(2,1,Color.GREEN);
        Assert.assertFalse(commonGoalCard.isSatisfied(library));
    }

    /**
     * Fifth Test: all rows are empty.
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
