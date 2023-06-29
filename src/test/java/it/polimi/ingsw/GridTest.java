package it.polimi.ingsw;

        import it.polimi.ingsw.exceptions.InvalidMoveException;
        import it.polimi.ingsw.model.CardGenerator.CardGenerator;
        import it.polimi.ingsw.model.Cards.ObjectCard;
        import it.polimi.ingsw.model.LivingRoom.Grid;
        import it.polimi.ingsw.utility.Position;
        import org.junit.After;
        import org.junit.Assert;
        import org.junit.Test;

        import java.util.Set;

        import static org.junit.Assert.*;
        import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class GridTest {
    Grid grid4,grid3,grid2;

    public GridTest(){
        grid4 = new Grid(4, new CardGenerator(4));
        grid3 = new Grid(3, new CardGenerator(3));
        grid2 = new Grid(2, new CardGenerator(2));
    }

    /**
     * Method used only in the test, to consume a new grid
     * @param grid
     */
    private void consumeGrid(Grid grid) {
        Position[] pos= new Position[1];
        pos[0]=new Position(0,0);
        for(int i=0; i<grid.getNumRows();i++)
        {
            for(int j=0; j<grid.getNumColumns();j++)
            {
                pos[0].setRowColumn(i,j);
                grid.draw(pos);
            }
        }
    }

    /**
     * Assert that, after a refill, unavailablePositions and availablePositions are respected
     */
    private void checkUnavailablePositions(Grid grid) {
        Set<Position> unavailablePositions = grid.getNotAvailablePositions();
        ObjectCard[][] matrix = grid.getMatrix();
        Position position=new Position(0,0);
        for(int i = 0; i< grid.getNumRows(); i++)
        {
            for(int j = 0; j< grid.getNumColumns(); j++)
            {
                position.setRowColumn(i,j);
                if(!unavailablePositions.contains(position))
                {
                    assertNotNull(matrix[i][j]);
                } else
                {
                    assertNull(matrix[i][j]);
                }
            }
        }
    }

    /**
     * Consume a new grid and then refill it
     */
    @Test
    public void refill_emptyGrid_filledGridInOutput_grid4(){
        assertFalse(grid4.needRefill());
        consumeGrid(grid4);
        assertTrue(grid4.needRefill());
        grid4.refill();
        assertFalse(grid4.needRefill());
        checkUnavailablePositions(grid4);
    }
    @Test
    public void refill_emptyGrid_filledGridInOutput_grid3(){
        assertFalse(grid3.needRefill());
        consumeGrid(grid3);
        assertTrue(grid3.needRefill());
        grid3.refill();
        assertFalse(grid3.needRefill());
        checkUnavailablePositions(grid3);
    }
    @Test
    public void refill_emptyGrid_filledGridInOutput_grid2(){
        assertFalse(grid2.needRefill());
        consumeGrid(grid2);
        assertTrue(grid2.needRefill());
        grid2.refill();
        assertFalse(grid2.needRefill());
        checkUnavailablePositions(grid2);
    }

    /**
     * Partially consume a new grid and then refill it
     */
    @Test
    public void refill_notCompletelyEmptyGrid_filledGridInOutput() {
        assertDoesNotThrow(()->{
            assertFalse(grid4.needRefill());
            grid4.draw(new Position[]{new Position(0, 3), new Position(0, 4)});
            grid4.draw(new Position[]{new Position(1, 3), new Position(1, 4)});
            grid4.draw(new Position[]{new Position(2, 3), new Position(2, 4)});
            grid4.draw(new Position[]{new Position(3, 3), new Position(3, 4)});
            grid4.draw(new Position[]{new Position(4, 3), new Position(4, 4)});
            grid4.draw(new Position[]{new Position(5, 3), new Position(5, 4)});
            grid4.draw(new Position[]{new Position(6, 3), new Position(6, 4)});
            grid4.draw(new Position[]{new Position(7, 3), new Position(7, 4)});
            grid4.draw(new Position[]{new Position(1, 5)});
            grid4.draw(new Position[]{new Position(2, 5)});
            grid4.draw(new Position[]{new Position(3, 5)});
            grid4.draw(new Position[]{new Position(4, 5)});
            grid4.draw(new Position[]{new Position(5, 5)});
            grid4.draw(new Position[]{new Position(6, 5)});
            grid4.draw(new Position[]{new Position(7, 5)});
            grid4.draw(new Position[]{new Position(8, 5)});
            grid4.draw(new Position[]{new Position(2, 6)});
            grid4.draw(new Position[]{new Position(3, 6)});
            grid4.draw(new Position[]{new Position(4, 6)});
            grid4.draw(new Position[]{new Position(5, 6)});
            grid4.draw(new Position[]{new Position(6, 6)});
            grid4.draw(new Position[]{new Position(3, 7), new Position(3, 8)});
            grid4.draw(new Position[]{new Position(4, 7), new Position(4, 8)});
            grid4.draw(new Position[]{new Position(2, 2)});
            grid4.draw(new Position[]{new Position(3, 2)});
            grid4.draw(new Position[]{new Position(4, 2)});
            grid4.draw(new Position[]{new Position(5, 2)});
            grid4.draw(new Position[]{new Position(6, 2)});
            grid4.draw(new Position[]{new Position(3, 1)});
            grid4.draw(new Position[]{new Position(4, 1)});
            grid4.draw(new Position[]{new Position(5, 1)});
            grid4.draw(new Position[]{new Position(5, 0)});
            assertTrue(grid4.needRefill());
            grid4.refill();
            assertFalse(grid4.needRefill());
            checkUnavailablePositions(grid4);
        });

    }

    /**
     * Gradually draw cards from a filled library till a refill is needed
     */
    @Test
    public void needRefill__notCompletelyEmptyGrid_TrueOutput(){
        assertDoesNotThrow(()->{
            Assert.assertFalse(grid4.needRefill());
            grid4.draw(new Position[]{new Position(0, 3), new Position(0, 4)});
            grid4.draw(new Position[]{new Position(1, 3), new Position(1, 4)});
            grid4.draw(new Position[]{new Position(2, 3), new Position(2, 4)});
            grid4.draw(new Position[]{new Position(3, 3), new Position(3, 4)});
            grid4.draw(new Position[]{new Position(4, 3), new Position(4, 4)});
            grid4.draw(new Position[]{new Position(5, 3), new Position(5, 4)});
            grid4.draw(new Position[]{new Position(6, 3), new Position(6, 4)});
            grid4.draw(new Position[]{new Position(7, 3), new Position(7, 4)});
            grid4.draw(new Position[]{new Position(1, 5)});
            grid4.draw(new Position[]{new Position(2, 5)});
            grid4.draw(new Position[]{new Position(3, 5)});
            grid4.draw(new Position[]{new Position(4, 5)});
            grid4.draw(new Position[]{new Position(5, 5)});
            grid4.draw(new Position[]{new Position(6, 5)});
            grid4.draw(new Position[]{new Position(7, 5)});
            grid4.draw(new Position[]{new Position(8, 5)});
            Assert.assertFalse(grid4.needRefill());
            grid4.draw(new Position[]{new Position(2, 6)});
            grid4.draw(new Position[]{new Position(3, 6)});
            grid4.draw(new Position[]{new Position(4, 6)});
            Assert.assertFalse(grid4.needRefill());
            grid4.draw(new Position[]{new Position(5, 6)});
            grid4.draw(new Position[]{new Position(6, 6)});
            grid4.draw(new Position[]{new Position(3, 7), new Position(3, 8)});
            grid4.draw(new Position[]{new Position(4, 7), new Position(4, 8)});
            grid4.draw(new Position[]{new Position(2, 2)});
            grid4.draw(new Position[]{new Position(3, 2)});
            grid4.draw(new Position[]{new Position(4, 2)});
            grid4.draw(new Position[]{new Position(5, 2)});
            grid4.draw(new Position[]{new Position(6, 2)});
            grid4.draw(new Position[]{new Position(3, 1)});
            grid4.draw(new Position[]{new Position(4, 1)});
            grid4.draw(new Position[]{new Position(5, 1)});
            grid4.draw(new Position[]{new Position(5, 0)});
            Assert.assertTrue(grid4.needRefill());
        });

    }

    /**
     * Consume a new grid and then checks if it needs refill
     */
    @Test
    public void needRefill__emptyGrid_TrueOutput(){
        assertFalse(grid4.needRefill());
        consumeGrid(grid4);
        assertTrue(grid4.needRefill());
    }

    /**
     * Checks if invalidMoveException is thrown when a null drawn is performed
     */
    @Test
    public void isDrawAvailable_nullInInput_exceptionThrown(){
        assertThrows(InvalidMoveException.class, ()->{
            grid4.isDrawAvailable(null);
        });
    }

    /**
     * Checks if invalidMoveException is thrown when a 0 length vector drawn is performed
     */
    @Test
    public void isDrawAvailable_tooShortPositionVector_exceptionThrown(){
        assertThrows(InvalidMoveException.class, ()->{
            Position[] positionShort=new Position[0];
            grid4.isDrawAvailable(positionShort);
        });
    }

    /**
     * Checks if invalidMoveException is thrown when a too long vector drawn is performed
     */
    @Test
    public void isDrawAvailable_tooLongPositionVector_exceptionThrown(){
        assertThrows(InvalidMoveException.class, ()->{
            Position[] positionLong= new Position[4];
            for(int i=0; i<4;i++)
                positionLong[i] = new Position(i, i);
            grid4.isDrawAvailable(positionLong);
        });

    }

    /**
     * Checks if invalidMoveException is thrown when a position vector contains Null
     */
    @Test
    public void isDrawAvailable_vectorContainsNull_exceptionThrown(){
        assertThrows(InvalidMoveException.class, ()->{
            Position[] positions = new Position[3];
            positions[0] = new Position(0,0);
            positions[1] = null;
            positions[2] = new Position(1,1);
            grid4.isDrawAvailable(positions);
        });
    }

    /**
     * Checks if invalidMoveException is thrown when a not aligned drawn is performed
     */
    @Test
    public void isDrawAvailable_notAlignedPositionVector1_exceptionThrown(){
        assertThrows(InvalidMoveException.class, ()->{
            Position[] positions = new Position[3];
            positions[0] = new Position(0,3);
            positions[1] = new Position(1,4);
            positions[2] = new Position(2,5);
            grid4.isDrawAvailable(positions);
        });
    }

    /**
     * Checks if invalidMoveException is thrown when a not aligned drawn is performed
     */
    @Test
    public void isDrawAvailable_notAlignedPositionVector2_exceptionThrown(){
        assertThrows(InvalidMoveException.class, ()->{
            Position[] positions = new Position[3];
            positions[0] = new Position(0,3);
            positions[1] = new Position(2,3);
            positions[2] = new Position(3,3);
            grid4.isDrawAvailable(positions);
        });
    }

    /**
     * Checks if invalidMoveException is thrown when a drawn whose cards have not free sides is performed
     */
    @Test
    public void isDrawAvailable_noFreeSidesPositionVector_exceptionThrown(){
        assertThrows(InvalidMoveException.class, ()->{
            grid4.refill();
            Position[] positions = new Position[3];
            positions[0] = new Position(4,4);
            positions[1] = new Position(4,5);
            positions[2] = new Position(4,6);
            grid4.isDrawAvailable(positions);
        });
    }

    /**
     * Checks that no exception are thrown if a correct input is performed
     */
    @Test
    public void isDrawAvailable_correctInput_exceptionNotThrown() throws InvalidMoveException {
        assertDoesNotThrow(() -> {
            grid4.refill();
            Position[] positions = new Position[2];
            positions[0] = new Position(0,3);
            positions[1] = new Position(0,4);
            grid4.isDrawAvailable(positions);
        });
    }

    /**
     * Checks if a drawn in a already used position is performable
     */
    @Test
    public void isDrawAvailable_drawnOnAUsedCell_exceptionThrown() throws InvalidMoveException {
        assertThrows(InvalidMoveException.class, () -> {
            Position[] positions = new Position[1];
            positions[0] = new Position(5,1);
            grid4.draw(positions);
            grid4.isDrawAvailable(positions);
        });
    }

    /**
     * Performs a correct drawn and then check that cells are null
     */
    @Test
    public void draw_correctInput_correctOutput_grid4(){
        assertDoesNotThrow(() -> {
            ObjectCard[][] matrix = grid4.getMatrix();
            Assert.assertNotNull(matrix[0][3]);
            Assert.assertNotNull(matrix[0][4]);

            Position[] positions = new Position[2];
            positions[0] = new Position(0,3);
            positions[1] = new Position(0,4);
            ObjectCard[] objectCards = grid4.draw(positions);

            ObjectCard[][] matrix2 = grid4.getMatrix();
            Assert.assertNotNull(objectCards[0]);
            Assert.assertNotNull(objectCards[1]);
            Assert.assertNull(matrix2[0][3]);
            Assert.assertNull(matrix2[0][4]);
        });
    }
    @Test
    public void draw_correctInput_correctOutput_grid3(){
        assertDoesNotThrow(() -> {
            ObjectCard[][] matrix = grid3.getMatrix();
            Assert.assertNotNull(matrix[0][3]);

            Position[] positions = new Position[1];
            positions[0] = new Position(0,3);
            ObjectCard[] objectCards = grid3.draw(positions);

            ObjectCard[][] matrix2 = grid3.getMatrix();
            Assert.assertNotNull(objectCards[0]);
            Assert.assertNull(matrix2[0][3]);
        });
    }
    @Test
    public void draw_correctInput_correctOutput_grid2(){
        assertDoesNotThrow(() -> {
            ObjectCard[][] matrix = grid2.getMatrix();
            Assert.assertNotNull(matrix[1][3]);
            Assert.assertNotNull(matrix[1][4]);

            Position[] positions = new Position[2];
            positions[0] = new Position(1,3);
            positions[1] = new Position(1,4);
            ObjectCard[] objectCards = grid2.draw(positions);

            ObjectCard[][] matrix2 = grid2.getMatrix();
            Assert.assertNotNull(objectCards[0]);
            Assert.assertNotNull(objectCards[1]);
            Assert.assertNull(matrix2[1][3]);
            Assert.assertNull(matrix2[1][4]);
        });
    }

    @After
    public void tearDown(){
    }
}
