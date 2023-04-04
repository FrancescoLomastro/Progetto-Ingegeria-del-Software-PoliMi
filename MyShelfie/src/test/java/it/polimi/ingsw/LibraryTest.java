package it.polimi.ingsw;

import it.polimi.ingsw.model.Cards.ObjectCard;
import it.polimi.ingsw.model.Enums.Color;
import it.polimi.ingsw.model.Enums.Type;
import it.polimi.ingsw.model.Player.Library;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;

/**
 * This class is used to test library class.
 *
 * @author: Alberto Aniballi
 */

public class LibraryTest {

    Library library;
    Type type = Type.FIRST;

    public LibraryTest() {
        library = new Library(5,6);
    }

    private void insertElement(int row, int col, Color color){
        ObjectCard objectCards = new ObjectCard("", color , type);
        library.insertCardInObjectCards(objectCards, row,col);
    }

    @Before
    public void setUp(){
        for(int i=0; i<library.getNumberOfRows(); i++){
            for(int j=0; j<library.getNumberOfColumns(); j++){
                library.insertCardInObjectCards(null, i,j);
            }
        }
    }

    @Test
    public void testIntVariable() {
        int expected = 5;
        int actual = 5;
        assertEquals(expected, actual);
    }

    /**
     * First test: one group of 4 same type object cards, start row (0,0) -> answer 4
     */
    @Test
    public void test1_countSameColorNeighbours_trueInOutput(){
        insertElement(0,0, Color.BEIGE);
        insertElement(1,0, Color.BEIGE);
        insertElement(2,0, Color.BEIGE);
        insertElement(3,0, Color.BEIGE);

        HashSet<String> checkedCells = new HashSet<>();
        checkedCells.add((0)+"_"+(0));
        int neighboursWithSameColorCounter = library.countSameColorNeighbours(0,0,checkedCells);
        assertEquals(neighboursWithSameColorCounter,4);
        //Assert.assertTrue(commonGoalCard.isSatisfied(library));
    }

    /**
     * Second test: one group,in column, of 4 same type object cards. Start row (2,0) -> answer 4
     */
    @Test
    public void test2_countSameColorNeighbours_trueInOutput(){
        insertElement(0,0, Color.BEIGE);
        insertElement(1,0, Color.BEIGE);
        insertElement(2,0, Color.BEIGE);
        insertElement(3,0, Color.BEIGE);

        HashSet<String> checkedCells = new HashSet<>();
        checkedCells.add((2)+"_"+(0));
        int neighboursWithSameColorCounter = library.countSameColorNeighbours(0,0,checkedCells);
        assertEquals(neighboursWithSameColorCounter,4);
        //Assert.assertTrue(commonGoalCard.isSatisfied(library));
    }

    /**
     * Third test: one group,in row, of 5 same type object cards. Start row (2,0) -> answer 5
     */
    @Test
    public void test3_countSameColorNeighbours_trueInOutput(){
        insertElement(2,0, Color.BEIGE);
        insertElement(2,1, Color.BEIGE);
        insertElement(2,2, Color.BEIGE);
        insertElement(2,3, Color.BEIGE);
        insertElement(2,4, Color.BEIGE);

        HashSet<String> checkedCells = new HashSet<>();
        checkedCells.add((2)+"_"+(0));
        int neighboursWithSameColorCounter = library.countSameColorNeighbours(2,0,checkedCells);
        assertEquals(neighboursWithSameColorCounter,5);
        //Assert.assertTrue(commonGoalCard.isSatisfied(library));
    }

    /**
     * Fourth test: one group,in row, of 1 same type object cards. Start row (2,2) -> answer 1
     */
    @Test
    public void test4_countSameColorNeighbours_trueInOutput(){
        insertElement(2,0, Color.BEIGE);
        insertElement(2,1, Color.BEIGE);
        insertElement(2,2, Color.BEIGE);
        insertElement(2,3, Color.BEIGE);
        insertElement(2,4, Color.BEIGE);

        HashSet<String> checkedCells = new HashSet<>();
        checkedCells.add((2)+"_"+(2));
        int neighboursWithSameColorCounter = library.countSameColorNeighbours(2,2,checkedCells);
        assertEquals(neighboursWithSameColorCounter,5);
        //Assert.assertTrue(commonGoalCard.isSatisfied(library));
    }

    /**
     * Fifth test: one group,forming a L, of 5 same type object cards. Start row (2,2) -> answer 5
     */
    @Test
    public void test5_countSameColorNeighbours_trueInOutput(){
        insertElement(2,0, Color.BEIGE);
        insertElement(2,1, Color.BEIGE);
        insertElement(2,2, Color.BEIGE);
        insertElement(1,2, Color.BEIGE);
        insertElement(0,2, Color.BEIGE);

        HashSet<String> checkedCells = new HashSet<>();
        checkedCells.add((2)+"_"+(2));
        int neighboursWithSameColorCounter = library.countSameColorNeighbours(2,2,checkedCells);
        assertEquals(neighboursWithSameColorCounter,5);
        //Assert.assertTrue(commonGoalCard.isSatisfied(library));
    }

    /**
     * Sixth test: one group,forming a S, of 6 same type object cards. Start row (2,0) -> answer 6
     */
    @Test
    public void test6_countSameColorNeighbours_trueInOutput(){
        insertElement(3,0, Color.BEIGE);
        insertElement(2,0, Color.BEIGE);
        insertElement(2,1, Color.BEIGE);
        insertElement(2,2, Color.BEIGE);
        insertElement(1,2, Color.BEIGE);
        insertElement(1,3, Color.BEIGE);

        HashSet<String> checkedCells = new HashSet<>();
        checkedCells.add((2)+"_"+(0));
        int neighboursWithSameColorCounter = library.countSameColorNeighbours(2,0,checkedCells);
        assertEquals(neighboursWithSameColorCounter,6);
        //Assert.assertTrue(commonGoalCard.isSatisfied(library));
    }

    /**
     * Seventh test: one group,forming a X, of 10 same type object cards. Start row (2,2) -> answer 10
     */
    @Test
    public void test7_countSameColorNeighbours_trueInOutput(){
        insertElement(2,0, Color.BEIGE);
        insertElement(2,1, Color.BEIGE);
        insertElement(2,2, Color.BEIGE);
        insertElement(1,2, Color.BEIGE);
        insertElement(0,2, Color.BEIGE);
        insertElement(3,2, Color.BEIGE);
        insertElement(4,2, Color.BEIGE);
        insertElement(5,2, Color.BEIGE);
        insertElement(2,3, Color.BEIGE);
        insertElement(2,4, Color.BEIGE);

        HashSet<String> checkedCells = new HashSet<>();
        checkedCells.add((2)+"_"+(2));
        int neighboursWithSameColorCounter = library.countSameColorNeighbours(2,2,checkedCells);
        assertEquals(neighboursWithSameColorCounter,10);
        //Assert.assertTrue(commonGoalCard.isSatisfied(library));
    }



    /**
     * Eighth test: one group of 1 same type object cards. Start row (2,0) -> answer 1
     */
    @Test
    public void test8_countSameColorNeighbours_trueInOutput(){
        insertElement(2,0, Color.BEIGE);

        HashSet<String> checkedCells = new HashSet<>();
        checkedCells.add((2)+"_"+(0));
        int neighboursWithSameColorCounter = library.countSameColorNeighbours(2,0,checkedCells);
        assertEquals(neighboursWithSameColorCounter,1);
        //Assert.assertTrue(commonGoalCard.isSatisfied(library));
    }

    /**
     * Ninth test: one cube of 9 same type object cards. Start row (2,2) -> answer 9
     */
    @Test
    public void test9_countSameColorNeighbours_trueInOutput(){
        insertElement(1,1, Color.BEIGE);
        insertElement(1,2, Color.BEIGE);
        insertElement(1,3, Color.BEIGE);
        insertElement(2,1, Color.BEIGE);
        insertElement(2,2, Color.BEIGE);
        insertElement(2,3, Color.BEIGE);
        insertElement(3,1, Color.BEIGE);
        insertElement(3,2, Color.BEIGE);
        insertElement(3,3, Color.BEIGE);


        HashSet<String> checkedCells = new HashSet<>();
        checkedCells.add((2)+"_"+(2));
        int neighboursWithSameColorCounter = library.countSameColorNeighbours(2,2,checkedCells);
        assertEquals(neighboursWithSameColorCounter,9);
        //Assert.assertTrue(commonGoalCard.isSatisfied(library));
    }

    /**
     * Tenth test: two crossing S forming one unique group same type object cards. Start row (2,2) -> answer 19
     */
    @Test
    public void test10_countSameColorNeighbours_trueInOutput(){
        insertElement(0,0, Color.BEIGE);
        insertElement(0,1, Color.BEIGE);
        insertElement(0,2, Color.BEIGE);
        insertElement(1,2, Color.BEIGE);
        insertElement(2,2, Color.BEIGE);
        insertElement(2,1, Color.BEIGE);
        insertElement(2,0, Color.BEIGE);
        insertElement(3,0, Color.BEIGE);
        insertElement(4,0, Color.BEIGE);
        insertElement(5,0, Color.BEIGE);
        insertElement(2,3, Color.BEIGE);
        insertElement(2,4, Color.BEIGE);
        insertElement(1,4, Color.BEIGE);
        insertElement(0,4, Color.BEIGE);
        insertElement(3,2, Color.BEIGE);
        insertElement(4,2, Color.BEIGE);
        insertElement(5,2, Color.BEIGE);
        insertElement(5,3, Color.BEIGE);
        insertElement(5,4, Color.BEIGE);

        HashSet<String> checkedCells = new HashSet<>();
        checkedCells.add((2)+"_"+(2));
        int neighboursWithSameColorCounter = library.countSameColorNeighbours(2,2,checkedCells);
        assertEquals(neighboursWithSameColorCounter,19);
        //Assert.assertTrue(commonGoalCard.isSatisfied(library));
    }
}
