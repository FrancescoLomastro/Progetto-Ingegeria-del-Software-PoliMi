package it.polimi.ingsw;

import it.polimi.ingsw.enums.Color;
import it.polimi.ingsw.exceptions.InvalidMoveException;
import it.polimi.ingsw.model.Cards.*;
import it.polimi.ingsw.model.Player.*;
import it.polimi.ingsw.model.CardGenerator.CardGenerator;
import it.polimi.ingsw.enums.Type;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class LibraryTest {
    private Library library;
    @Before
    public void setUp()
    {
        library=new Library();
    }
    @After
    public void tearDown()
    {}



    /**
     * A methos used oly for tests, fills a given array of objectcards
     */
    private void fillVector(ObjectCard... array)
    {
        CardGenerator cardGenerator= new CardGenerator(4);
        for(int i=0; i<array.length;i++)
        {
            array[i]=cardGenerator.generateObjectCard();
        }
    }

    /**
     * Verify that a new library is full of null objects
     */
    @Test
    public void getMatrix_noInput_nullMatrixOutput()
    {
        ObjectCard[][] testMatrix= new ObjectCard[library.getNumberOfRows()][library.getNumberOfColumns()];
        assertArrayEquals(testMatrix,library.getMatrix());
    }

    /**
     * Fills a library inserting a full column at a time, then checks if it corresponds
     */
    @Test
    public void insertCardsInLibrary_fromEmptyLibrary_correctlyFilledLibrary() {
        assertDoesNotThrow(() -> {

            ObjectCard[][] testMatrix= new ObjectCard[library.getNumberOfRows()][library.getNumberOfColumns()];
            ObjectCard[] testVector = new ObjectCard[library.getNumberOfRows()];
            fillVector(testVector);

            for(int column=0;column<library.getNumberOfColumns();column++)
            {
                for(int row= library.getNumberOfRows()-1,i=0; row>=0;row--,i++) {
                    testMatrix[row][column]= testVector[i];
                }
                library.insertCardsInLibrary(column,testVector);
            }

            for(int i=0; i<library.getNumberOfColumns(); i++){
                for(int j=0; j<library.getNumberOfRows(); j++){
                    assertEquals(testMatrix[j][i], library.getMatrix()[j][i]);
                }
            }
        });
    }

    /**
     * Try to insert too much object cards in the same column
     */
    @Test
    public void insertCardsInLibrary_outOfBoundInput1_exceptionThrown() {
        assertThrows((InvalidMoveException.class), () -> {
            ObjectCard[] testVector = new ObjectCard[library.getNumberOfRows()+1];
            fillVector(testVector);
            library.insertCardsInLibrary(0, testVector);
        });

    }
    @Test
    public void insertCardsInLibrary_outOfBoundInput2_exceptionThrown() {
        assertThrows(InvalidMoveException.class, () -> {

            ObjectCard[] testVector = new ObjectCard[library.getNumberOfRows()+1];
            ObjectCard[] testVector_short = new ObjectCard[1];
            fillVector(testVector);
            fillVector(testVector_short);

            library.insertCardsInLibrary(0,testVector_short);
            library.insertCardsInLibrary(0,testVector);
        });
    }

    @Test
    public void isMoveAvailable_correctInsert_TrueOutput() {
        ObjectCard[] testVector1 = new ObjectCard[library.getNumberOfRows()];
        ObjectCard[] testVector2 = new ObjectCard[1];
        fillVector(testVector1);
        fillVector(testVector2);

        assertTrue(library.isMoveAvailable(0, testVector1.length));
        assertTrue(library.isMoveAvailable(library.getNumberOfColumns() - 1, testVector2.length));

        assertDoesNotThrow(() -> {
            for (int i = 0; i < library.getNumberOfRows() - 1; i++) {
                library.insertCardsInLibrary(0, testVector2);
            }
        });


        assertTrue(library.isMoveAvailable(0, testVector2.length));
    }
    @Test
    public void isMoveAvailable_incorrectInsert_FalseOutput() {
        int testNumber1 = library.getNumberOfRows() + 1;
        assertFalse(library.isMoveAvailable(0, testNumber1));


        int testNumber = 1;
        assertFalse(library.isMoveAvailable(library.getNumberOfColumns(), testNumber));


        ObjectCard[] testVector2 = new ObjectCard[1];
        fillVector(testVector2);

        assertDoesNotThrow(() -> {
            for (int i = 0; i < library.getNumberOfRows(); i++) {
                library.insertCardsInLibrary(0, testVector2);
            }
        });
        assertFalse(library.isMoveAvailable(0, testVector2.length));
    }

    /**
     * Fills a library and than check if it is
     */
    @Test
    public void isFull_cardFilling_trueOutput() {
        assertDoesNotThrow(() -> {

            ObjectCard[] testVector1 = new ObjectCard[library.getNumberOfRows()];
            fillVector(testVector1);

            assertFalse(library.isFull());
            for(int column=0; column< library.getNumberOfColumns();column++)
            {
                library.insertCardsInLibrary(column,testVector1);
            }
            assertTrue(library.isFull());
        });
    }

    /**
     * Fills a column and returns no free cells
     */
    @Test
    public void findNumberOfFreeCells_fullColumnFilling_noFreeCellsOutput() {
        assertDoesNotThrow(() -> {

            ObjectCard[] testVector1 = new ObjectCard[library.getNumberOfRows()];
            fillVector(testVector1);
            for(int column=0; column< library.getNumberOfColumns();column++)
            {
                assertSame(library.findNumberOfFreeCells(column),library.getNumberOfRows());
            }
            library.insertCardsInLibrary(0,testVector1);
            assertSame(library.findNumberOfFreeCells(0),0);
        });
    }
    @Test
    public void findNumberOfFreeCells_fullColumnFilling_correctFreeCellsOutput() {
        assertDoesNotThrow(() -> {
            ObjectCard[] testVector1 = new ObjectCard[2];
            fillVector(testVector1);
            library.insertCardsInLibrary(0,testVector1);
            assertSame(library.findNumberOfFreeCells(0),library.getNumberOfRows()-2);
        });
    }

    /**
     * Inserts some cards in the library and checks if the realized points are correct
     */
    @Test
    public void countAdjacentPoints_correctLibrary_correctScore() {
        assertDoesNotThrow(() -> {
            library.insertCardsInLibrary(0,new ObjectCard("", Color.LIGHTBLUE,Type.FIRST));
            library.insertCardsInLibrary(0,new ObjectCard("",Color.BLUE,Type.FIRST));
            library.insertCardsInLibrary(0,new ObjectCard("",Color.BLUE,Type.FIRST));
            library.insertCardsInLibrary(0,new ObjectCard("",Color.BLUE,Type.FIRST));
            library.insertCardsInLibrary(0,new ObjectCard("",Color.PINK,Type.FIRST));
            library.insertCardsInLibrary(0,new ObjectCard("",Color.PINK,Type.FIRST));

            library.insertCardsInLibrary(1,new ObjectCard("",Color.LIGHTBLUE,Type.FIRST));
            library.insertCardsInLibrary(1,new ObjectCard("",Color.LIGHTBLUE,Type.FIRST));
            library.insertCardsInLibrary(1,new ObjectCard("",Color.YELLOW,Type.FIRST));
            library.insertCardsInLibrary(1,new ObjectCard("",Color.BLUE,Type.FIRST));
            library.insertCardsInLibrary(1,new ObjectCard("",Color.PINK,Type.FIRST));
            library.insertCardsInLibrary(1,new ObjectCard("",Color.PINK,Type.FIRST));

            library.insertCardsInLibrary(2,new ObjectCard("",Color.LIGHTBLUE,Type.FIRST));
            library.insertCardsInLibrary(2,new ObjectCard("",Color.GREEN,Type.FIRST));
            library.insertCardsInLibrary(2,new ObjectCard("",Color.YELLOW,Type.FIRST));
            library.insertCardsInLibrary(2,new ObjectCard("",Color.PINK,Type.FIRST));
            library.insertCardsInLibrary(2,new ObjectCard("",Color.PINK,Type.FIRST));

            library.insertCardsInLibrary(3,new ObjectCard("",Color.GREEN,Type.FIRST));
            library.insertCardsInLibrary(3,new ObjectCard("",Color.GREEN,Type.FIRST));
            library.insertCardsInLibrary(3,new ObjectCard("",Color.YELLOW,Type.FIRST));
            library.insertCardsInLibrary(3,new ObjectCard("",Color.PINK,Type.FIRST));
            library.insertCardsInLibrary(3,new ObjectCard("",Color.PINK,Type.FIRST));

            library.insertCardsInLibrary(4,new ObjectCard("",Color.GREEN,Type.FIRST));
            library.insertCardsInLibrary(4,new ObjectCard("",Color.GREEN,Type.FIRST));
            library.insertCardsInLibrary(4,new ObjectCard("",Color.WHITE,Type.FIRST));
            library.insertCardsInLibrary(4,new ObjectCard("",Color.WHITE,Type.FIRST));
            assertEquals(library.countAdjacentPoints(),21);
        });
    }
    @Test
    public void countAdjacentPoints_correctLibrary_correctScore1() {
        assertDoesNotThrow(() -> {

            library.insertCardsInLibrary(0,new ObjectCard("",Color.LIGHTBLUE,Type.FIRST));
            library.insertCardsInLibrary(1,new ObjectCard("",Color.LIGHTBLUE,Type.FIRST));
            library.insertCardsInLibrary(3,new ObjectCard("",Color.GREEN,Type.FIRST));
            library.insertCardsInLibrary(4,new ObjectCard("",Color.GREEN,Type.FIRST));
            assertSame(library.countAdjacentPoints(),0);
        });
    }
    @Test
    public void countAdjacentPoints_correctLibrary_correctScore2() {
        assertDoesNotThrow(() -> {

            library.insertCardsInLibrary(0,new ObjectCard("",Color.PINK,Type.FIRST));
            library.insertCardsInLibrary(0,new ObjectCard("",Color.PINK,Type.FIRST));
            library.insertCardsInLibrary(0,new ObjectCard("",Color.BLUE,Type.FIRST));
            library.insertCardsInLibrary(1,new ObjectCard("",Color.PINK,Type.FIRST));
            library.insertCardsInLibrary(1,new ObjectCard("",Color.PINK,Type.FIRST));
            library.insertCardsInLibrary(1,new ObjectCard("",Color.YELLOW,Type.FIRST));
            library.insertCardsInLibrary(1,new ObjectCard("",Color.YELLOW,Type.FIRST));
            library.insertCardsInLibrary(1,new ObjectCard("",Color.YELLOW,Type.FIRST));
            library.insertCardsInLibrary(2,new ObjectCard("",Color.BLUE,Type.FIRST));
            library.insertCardsInLibrary(2,new ObjectCard("",Color.BLUE,Type.FIRST));
            library.insertCardsInLibrary(2,new ObjectCard("",Color.BLUE,Type.FIRST));
            library.insertCardsInLibrary(2,new ObjectCard("",Color.BLUE,Type.FIRST));
            library.insertCardsInLibrary(2,new ObjectCard("",Color.YELLOW,Type.FIRST));
            library.insertCardsInLibrary(3,new ObjectCard("",Color.WHITE,Type.FIRST));
            library.insertCardsInLibrary(3,new ObjectCard("",Color.YELLOW,Type.FIRST));
            library.insertCardsInLibrary(3,new ObjectCard("",Color.YELLOW,Type.FIRST));
            library.insertCardsInLibrary(3,new ObjectCard("",Color.YELLOW,Type.FIRST));
            library.insertCardsInLibrary(3,new ObjectCard("",Color.YELLOW,Type.FIRST));
            library.insertCardsInLibrary(4,new ObjectCard("",Color.WHITE,Type.FIRST));
            library.insertCardsInLibrary(4,new ObjectCard("",Color.WHITE,Type.FIRST));
            assertSame(library.countAdjacentPoints(),16);
        });
    }
    @Test
    public void countAdjacentPoints_noInput_correctOutput()
    {
        assertSame(library.countAdjacentPoints(),0);
    }


}