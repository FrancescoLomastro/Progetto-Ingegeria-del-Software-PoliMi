package it.polimi.ingsw;

import it.polimi.ingsw.model.Cards.*;
import it.polimi.ingsw.model.Enums.*;
import it.polimi.ingsw.model.Player.*;
import it.polimi.ingsw.model.CardGenerator.CardGenerator;
import it.polimi.ingsw.model.Enums.Type;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

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
        CardGenerator cardGenerator= new CardGenerator();
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
        ObjectCard[][] testMatrix= new ObjectCard[library.getNumberOfRows()][library.getNumberOfColumns()];
        ObjectCard[] testVector = new ObjectCard[library.getNumberOfRows()];
        fillVector(testVector);

        for(int column=0;column<library.getNumberOfColumns();column++)
        {
            for(int row= library.getNumberOfRows()-1,i=0; row>=0;row--,i++)
            {
                testMatrix[row][column]= testVector[i];
            }
            assertTrue(library.insertCardsInLibrary(column,testVector));
            assertArrayEquals(testMatrix,library.getMatrix());
        }
    }
    @Test
    public void insertCardsInLibrary_outOfBoundInput_falseOutput1()
    {
        ObjectCard[][] testMatrix= new ObjectCard[library.getNumberOfRows()][library.getNumberOfColumns()];
        ObjectCard[] testVector = new ObjectCard[library.getNumberOfRows()+1];
        fillVector(testVector);

        assertFalse(library.insertCardsInLibrary(0,testVector));
        assertArrayEquals(library.getMatrix(),testMatrix);
    }
    @Test
    public void insertCardsInLibrary_outOfBoundComposedInput_falseOutput2()
    {
        ObjectCard[] testVector = new ObjectCard[library.getNumberOfRows()+1];
        ObjectCard[] testVector_short = new ObjectCard[1];
        fillVector(testVector);
        fillVector(testVector_short);

        assertTrue(library.insertCardsInLibrary(0,testVector_short));
        assertFalse(library.insertCardsInLibrary(0,testVector));
    }

    @Test
    public void isMoveAvailable_correctInput_TrueOutput()
    {
        ObjectCard[] testVector1 = new ObjectCard[library.getNumberOfRows()];
        ObjectCard[] testVector2 = new ObjectCard[1];
        fillVector(testVector1);
        fillVector(testVector2);

        assertTrue(library.isMoveAvailable(0,testVector1));
        assertTrue(library.isMoveAvailable(library.getNumberOfColumns()-1,testVector2));
        for(int i=0; i<library.getNumberOfRows()-1;i++)
        {
            library.insertCardsInLibrary(0,testVector2);
        }
        assertTrue(library.isMoveAvailable(0,testVector2));
    }
    @Test
    public void isMoveAvailable_incorrectInput_FalseOutput()
    {
        ObjectCard[] testVector1 = new ObjectCard[library.getNumberOfRows()+1];
        ObjectCard[] testVector2 = new ObjectCard[1];
        fillVector(testVector1);
        fillVector(testVector2);

        assertFalse(library.isMoveAvailable(0,testVector1));
        assertFalse(library.isMoveAvailable(library.getNumberOfColumns(),testVector2));
        for(int i=0; i<library.getNumberOfRows();i++)
        {
            library.insertCardsInLibrary(0,testVector2);
        }
        assertFalse(library.isMoveAvailable(0,testVector2));
    }
    @Test
    public void isFull__correctOutput()
    {
        ObjectCard[] testVector1 = new ObjectCard[library.getNumberOfRows()];
        fillVector(testVector1);

        assertFalse(library.isFull());
        for(int column=0; column< library.getNumberOfColumns();column++)
        {
            library.insertCardsInLibrary(column,testVector1);
        }
        assertTrue(library.isFull());
    }

    @Test
    public void findNumberOfFreeCells_correctInput_correctOutput()
    {
        ObjectCard[] testVector1 = new ObjectCard[library.getNumberOfRows()];
        fillVector(testVector1);
        for(int column=0; column< library.getNumberOfColumns();column++)
        {
            assertSame(library.findNumberOfFreeCells(column),library.getNumberOfRows());
        }
        library.insertCardsInLibrary(0,testVector1);
        assertSame(library.findNumberOfFreeCells(0),0);
    }
    @Test
    public void findNumberOfFreeCells_incorrectInput_ZeroOutput()
    {
        assertSame(library.findNumberOfFreeCells(library.getNumberOfColumns()),0);
        assertSame(library.findNumberOfFreeCells(library.getNumberOfColumns()+1),0);
        assertSame(library.findNumberOfFreeCells(-1),0);
    }
    @Test
    public void countAdjacentPoints_correctInput_correctOutput()
    {
        library.insertCardsInLibrary(0,new ObjectCard("",Color.LIGHTBLUE,Type.FIRST));
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
    }
    @Test
    public void countAdjacentPoints_correctInput1_correctOutput1()
    {
        library.insertCardsInLibrary(0,new ObjectCard("",Color.LIGHTBLUE,Type.FIRST));

        library.insertCardsInLibrary(1,new ObjectCard("",Color.LIGHTBLUE,Type.FIRST));

        library.insertCardsInLibrary(3,new ObjectCard("",Color.GREEN,Type.FIRST));

        library.insertCardsInLibrary(4,new ObjectCard("",Color.GREEN,Type.FIRST));
        assertSame(library.countAdjacentPoints(),0);
    }
    @Test
    public void countAdjacentPoints_correctInput2_correctOutput2()
    {
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
    }
    @Test
    public void countAdjacentPoints_noInput_correctOutput()
    {
        assertSame(library.countAdjacentPoints(),0);
    }


}
