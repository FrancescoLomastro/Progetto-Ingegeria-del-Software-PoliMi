package it.polimi.ingsw;

import it.polimi.ingsw.model.CardGenerator.CardGenerator;
import it.polimi.ingsw.model.Cards.CommonGoalCard;
import it.polimi.ingsw.model.Cards.ConcreteCommonCards.CommonGoalCard0;
import it.polimi.ingsw.model.Cards.ConcreteCommonCards.CommonGoalCard2;
import it.polimi.ingsw.model.Cards.ObjectCard;
import it.polimi.ingsw.model.Enums.Color;
import it.polimi.ingsw.model.Enums.Type;
import it.polimi.ingsw.model.Player.Library;
import org.junit.*;
import org.junit.Assert;

import java.io.IOException;
public class CommonGoalCard2Test {
    CommonGoalCard commonGoalCard;
    Type type = Type.FIRST;

    Library library;

    public CommonGoalCard2Test() throws IOException {
        commonGoalCard = new CommonGoalCard2();
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

    /**
     * First test: 4 groups of 4 cards of same color -> answer true
     */
    @Test
    public void isSatisfied_correctInputSeparateBlock_1_trueInOutput(){
        insertElement(0,0,Color.BEIGE);
        insertElement(1,0, Color.BEIGE);
        insertElement(2,0, Color.BEIGE);
        insertElement(3,0, Color.BEIGE);

        insertElement(0,2,Color.BEIGE);
        insertElement(1,2, Color.BEIGE);
        insertElement(2,2, Color.BEIGE);
        insertElement(3,2, Color.BEIGE);

        insertElement(0,4,Color.BEIGE);
        insertElement(1,4, Color.BEIGE);
        insertElement(2,4, Color.BEIGE);
        insertElement(3,4, Color.BEIGE);

        insertElement(5,0,Color.BEIGE);
        insertElement(5,1, Color.BEIGE);
        insertElement(5,2, Color.BEIGE);
        insertElement(5,3, Color.BEIGE);

        Assert.assertTrue(commonGoalCard.isSatisfied(library));
    }

    /**
     * Second test: 4 groups of 4 of different color -> answer true
     */
    @Test
    public void isSatisfied_correctInputSeparateBlock_2_trueInOutput(){
        insertElement(0,0,Color.BEIGE);
        insertElement(0,1, Color.BEIGE);
        insertElement(0,2,Color.BEIGE);
        insertElement(0,3, Color.BEIGE);

        insertElement(2, 0, Color.YELLOW);
        insertElement(2, 1, Color.YELLOW);
        insertElement(2, 2, Color.YELLOW);
        insertElement(2, 3, Color.YELLOW);

        insertElement(4, 0, Color.BLUE);
        insertElement(4, 1, Color.BLUE);
        insertElement(4, 2, Color.BLUE);
        insertElement(4, 3, Color.BLUE);

        insertElement(5, 0, Color.PINK);
        insertElement(5, 1, Color.PINK);
        insertElement(5, 2, Color.PINK);
        insertElement(5, 3, Color.PINK);

        Assert.assertTrue(commonGoalCard.isSatisfied(library));
    }

    /**
     * Third test: 3 groups of 4 -> answer false
     */
    @Test
    public void isSatisfied_noCorrectInputSeparateBlock_1_falseInOutput(){
        insertElement(0,0,Color.BEIGE);
        insertElement(1,0, Color.BEIGE);
        insertElement(2,0,Color.BEIGE);
        insertElement(3,0, Color.BEIGE);
        insertElement(0, 4, Color.YELLOW);
        insertElement(1, 4, Color.YELLOW);
        insertElement(2,4,Color.YELLOW);
        insertElement(3,4, Color.YELLOW);
        insertElement(0, 2, Color.BLUE);
        insertElement(1, 2, Color.BLUE);
        insertElement(2, 2, Color.BLUE);
        insertElement(3, 2, Color.BLUE);
        insertElement(0, 1, Color.BLUE);
        insertElement(1, 1, Color.PINK);
        insertElement(5, 1, Color.GREEN);
        insertElement(5, 4, Color.YELLOW);
        Assert.assertFalse(commonGoalCard.isSatisfied(library));
    }

    /**
     * Fourth test: 2 full columns of same color -> answer false
     */
    @Test
    public void isSatisfied_noCorrectInputAdjacentCard_2_falseInOutput(){
        insertElement(0,1,Color.GREEN);
        insertElement(1,1, Color.GREEN);
        insertElement(2, 1, Color.GREEN);
        insertElement(3, 1, Color.GREEN);
        insertElement(4, 1, Color.GREEN);
        insertElement(5, 1, Color.GREEN);

        insertElement(0, 0, Color.BEIGE);
        insertElement(1, 0, Color.BEIGE);
        insertElement(2, 0, Color.BEIGE);
        insertElement(3, 0, Color.BEIGE);
        insertElement(4, 0, Color.BEIGE);
        insertElement(5, 0, Color.BEIGE);
        Assert.assertFalse(commonGoalCard.isSatisfied(library));
    }

    /**
     * Fifth test: no groups of same color
     */
    @Test
    public void isSatisfied_correctInput_falseInOutput(){
        insertElement(0, 0, Color.GREEN);
        insertElement(1, 0, Color.YELLOW);
        insertElement(2, 0, Color.BLUE);
        insertElement(3, 0, Color.LIGHTBLUE);
        insertElement(4, 0, Color.PINK);
        insertElement(5, 0, Color.BEIGE);

        insertElement(0,1, Color.YELLOW);
        insertElement(1, 1, Color.BLUE);
        insertElement(2, 1, Color.LIGHTBLUE);
        insertElement(3, 1, Color.PINK);
        insertElement(4, 1, Color.BEIGE);
        insertElement(5,1,Color.GREEN);

        insertElement(0, 2, Color.BLUE);
        insertElement(1, 2, Color.LIGHTBLUE);
        insertElement(2, 2, Color.PINK);
        insertElement(3, 2, Color.BEIGE);
        insertElement(4,2,Color.GREEN);
        insertElement(5,2, Color.YELLOW);

        insertElement(0, 3, Color.LIGHTBLUE);
        insertElement(1, 3, Color.PINK);
        insertElement(2, 3, Color.BEIGE);
        insertElement(3,3,Color.GREEN);
        insertElement(4,3, Color.YELLOW);
        insertElement(5, 3, Color.BLUE);

        insertElement(0, 4, Color.PINK);
        insertElement(1, 4, Color.BEIGE);
        insertElement(2,4,Color.GREEN);
        insertElement(3,4, Color.YELLOW);
        insertElement(4, 4, Color.BLUE);
        insertElement(5, 4, Color.LIGHTBLUE);

        Assert.assertFalse(commonGoalCard.isSatisfied(library));
    }

    /**
     * Sixth test: empty
     */
    @Test
    public void isSatisfied_correctInputEmptyTable_falseInOutput(){
        Assert.assertFalse(commonGoalCard.isSatisfied(library));
    }

    /**
     * Seventh test: 4 groups of 4 in S shape or L shape -> answer true
     **/
    @Test
    public void isSatisfied_correctInputSeparateBlock_3_trueInOutput(){
        insertElement(0,0,Color.BEIGE);
        insertElement(0,1, Color.BEIGE);
        insertElement(1,1,Color.BEIGE);
        insertElement(1,2, Color.BEIGE);

        insertElement(1, 0, Color.YELLOW);
        insertElement(2, 0, Color.YELLOW);
        insertElement(2, 1, Color.YELLOW);
        insertElement(3, 1, Color.YELLOW);

        insertElement(2, 3, Color.BLUE);
        insertElement(3, 3, Color.BLUE);
        insertElement(3, 4, Color.BLUE);
        insertElement(4, 4, Color.BLUE);

        insertElement(5, 1, Color.PINK);
        insertElement(5, 2, Color.PINK);
        insertElement(5, 3, Color.PINK);
        insertElement(4, 3, Color.PINK);

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
