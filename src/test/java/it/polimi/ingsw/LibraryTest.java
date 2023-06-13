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

    private void fillVector(ObjectCard... vector)
    {
        CardGenerator cardGenerator= new CardGenerator(4);
        for(int i=0; i<vector.length;i++)
        {
            vector[i]=cardGenerator.generateObjectCard();
        }
    }
    @Test
    public void getMatrix_noInput_nullOutput()
    {
        ObjectCard[][] testMatrix= new ObjectCard[library.getNumberOfRows()][library.getNumberOfColumns()];
        assertArrayEquals(testMatrix,library.getMatrix());
    }

    @Test
    public void insertCardsInLibrary_correctInput_correctOutput()
    {
        assertDoesNotThrow(() -> {

            ObjectCard[][] testMatrix= new ObjectCard[library.getNumberOfRows()][library.getNumberOfColumns()];
            ObjectCard[] testVector = new ObjectCard[library.getNumberOfRows()];
            fillVector(testVector);

            for(int column=0;column<library.getNumberOfColumns();column++) {
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
    @Test
    public void insertCardsInLibrary_outOfBoundInput_falseOutput1()
    {
        assertThrows((InvalidMoveException.class), () -> {

            ObjectCard[][] testMatrix= new ObjectCard[library.getNumberOfRows()][library.getNumberOfColumns()];
            ObjectCard[] testVector = new ObjectCard[library.getNumberOfRows()+1];
            fillVector(testVector);

            library.insertCardsInLibrary(0, testVector);
        });

    }
    @Test
    public void insertCardsInLibrary_outOfBoundComposedInput_falseOutput2()
    {
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
    public void isMoveAvailable_correctInput_TrueOutput()
    {
        assertDoesNotThrow(() -> {

            ObjectCard[] testVector1 = new ObjectCard[library.getNumberOfRows()];
            ObjectCard[] testVector2 = new ObjectCard[1];
            fillVector(testVector1);
            fillVector(testVector2);

            library.isMoveAvailable(0,testVector1);
            library.isMoveAvailable(library.getNumberOfColumns()-1,testVector2);

            for(int i=0; i<library.getNumberOfRows()-1;i++)
            {
                library.insertCardsInLibrary(0,testVector2);
            }

            library.isMoveAvailable(0,testVector2);
        });
    }
    @Test
    public void isMoveAvailable_incorrectInput_FalseOutput()
    {
        assertThrows(InvalidMoveException.class, () -> {

            ObjectCard[] testVector1 = new ObjectCard[library.getNumberOfRows()+1];
            fillVector(testVector1);


            library.isMoveAvailable(0,testVector1);
        });

        assertThrows(InvalidMoveException.class, () -> {

            ObjectCard[] testVector2 = new ObjectCard[1];

            fillVector(testVector2);

            library.isMoveAvailable(library.getNumberOfColumns(),testVector2);
        });


        assertThrows(InvalidMoveException.class, () -> {

            ObjectCard[] testVector2 = new ObjectCard[1];
            fillVector(testVector2);

            for(int i=0; i<library.getNumberOfRows();i++)
            {
                library.insertCardsInLibrary(0,testVector2);
            }
            library.isMoveAvailable(0,testVector2);
        });
    }
    @Test
    public void isFull__correctOutput()
    {
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

    @Test
    public void findNumberOfFreeCells_correctInput_correctOutput()
    {
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
    public void countAdjacentPoints_correctInput_correctOutput()
    {
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
            System.out.println(library.countAdjacentPoints());
        });
    }
    @Test
    public void countAdjacentPoints_correctInput1_correctOutput1()
    {
        assertDoesNotThrow(() -> {

            library.insertCardsInLibrary(0,new ObjectCard("",Color.LIGHTBLUE,Type.FIRST));

            library.insertCardsInLibrary(1,new ObjectCard("",Color.LIGHTBLUE,Type.FIRST));

            library.insertCardsInLibrary(3,new ObjectCard("",Color.GREEN,Type.FIRST));

            library.insertCardsInLibrary(4,new ObjectCard("",Color.GREEN,Type.FIRST));
            assertSame(library.countAdjacentPoints(),0);
        });
    }
    @Test
    public void countAdjacentPoints_correctInput2_correctOutput2()
    {
        assertDoesNotThrow(() -> {

            library.insertCardsInLibrary(0,new ObjectCard("",Color.PINK,Type.FIRST));
            library.insertCardsInLibrary(0,new ObjectCard("",Color.PINK,Type.FIRST));
            library.insertCardsInLibrary(0,new ObjectCard("",Color.BLUE,Type.FIRST));
            library.insertCardsInLibrary(1,new ObjectCard("",Color.PINK,Type.FIRST));
            library.insertCardsInLibrary(1,new ObjectCard("",Color.PINK,Type.FIRST));
            library.insertCardsInLibrary(1,new ObjectCard("",Color.BLUE,Type.FIRST));
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
            assertSame(library.countAdjacentPoints(),21);
        });
    }
    @Test
    public void countAdjacentPoints_noInput_correctOutput()
    {
        assertSame(library.countAdjacentPoints(),0);
    }


}