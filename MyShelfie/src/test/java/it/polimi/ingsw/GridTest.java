package it.polimi.ingsw;

import it.polimi.ingsw.model.CardGenerator.CardGenerator;
import it.polimi.ingsw.model.Cards.ObjectCard;
import it.polimi.ingsw.model.LivingRoom.Grid;
import it.polimi.ingsw.model.Utility.Position;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

public class GridTest {
    Grid grid;

    public GridTest(){
        grid = new Grid(4, new CardGenerator());
    }

    @Test
    public void refill_EmptyGrid_filledGridInOutput(){
        grid.refill();
        for(int i=0; i<grid.getMatrix().length; i++){
            for(int j=0; j<grid.getMatrix().length; j++)
            {
                if (!(((i==0) && (j==0||j==1||j==2||j==5||j==6||j==7||j==8))
                ||((i==1) && (j==0||j==1||j==2||j==7||j==8||j==6))
                ||((i==2) && (j==0||j==1||j==8||j==7))
                ||((i==3) && (j==0))
                ||((i==5) && (j==8))
                ||((i==6) && (j==0||j==1||j==7||j==8))
                ||((i==7) && (j==0||j==1||j==2||j==6||j==7||j==8))
                ||((i==8) && (j==0||j==1||j==2||j==3||j==6||j==7||j==8)))) {
                    Assert.assertNotEquals(null, grid.getMatrix()[i][j]);
                }
            }
        }
    }
    @Test
    public void refill_PartiallyEmptyGrid_filledGridInOutput(){
        grid.refill();
        grid.draw(new Position[]{new Position(0, 3), new Position(0, 4)});
        grid.draw(new Position[]{new Position(1, 3), new Position(1, 4)});
        grid.draw(new Position[]{new Position(2, 3), new Position(2, 4)});
        grid.draw(new Position[]{new Position(3, 3), new Position(3, 4)});
        grid.draw(new Position[]{new Position(4, 3), new Position(4, 4)});
        grid.draw(new Position[]{new Position(5, 3), new Position(5, 4)});
        grid.draw(new Position[]{new Position(6, 3), new Position(6, 4)});
        grid.draw(new Position[]{new Position(7, 3), new Position(7, 4)});
        grid.draw(new Position[]{new Position(1, 5)});
        grid.draw(new Position[]{new Position(2, 5)});
        grid.draw(new Position[]{new Position(3, 5)});
        grid.draw(new Position[]{new Position(4, 5)});
        grid.draw(new Position[]{new Position(5, 5)});
        grid.draw(new Position[]{new Position(6, 5)});
        grid.draw(new Position[]{new Position(7, 5)});
        grid.draw(new Position[]{new Position(8, 5)});
        grid.draw(new Position[]{new Position(2, 6)});
        grid.draw(new Position[]{new Position(3, 6)});
        grid.draw(new Position[]{new Position(4, 6)});
        grid.draw(new Position[]{new Position(5, 6)});
        grid.draw(new Position[]{new Position(6, 6)});
        grid.draw(new Position[]{new Position(3, 7), new Position(3, 8)});
        grid.draw(new Position[]{new Position(4, 7), new Position(4, 8)});
        grid.draw(new Position[]{new Position(2, 2)});
        grid.draw(new Position[]{new Position(3, 2)});
        grid.draw(new Position[]{new Position(4, 2)});
        grid.draw(new Position[]{new Position(5, 2)});
        grid.draw(new Position[]{new Position(6, 2)});
        grid.draw(new Position[]{new Position(3, 1)});
        grid.draw(new Position[]{new Position(4, 1)});
        grid.draw(new Position[]{new Position(5, 1)});
        grid.draw(new Position[]{new Position(5, 0)});
        ObjectCard[][] objectCard1 = new ObjectCard[9][9];
        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                objectCard1[i][j]=grid.getMatrix()[i][j];
            }
        }
        grid.refill();
        for(int i=0; i<grid.getMatrix().length; i++){
            for(int j=0; j<grid.getMatrix().length; j++){
                if (!(((i==0) && (j==0||j==1||j==2||j==5||j==6||j==7||j==8))
                        ||((i==1) && (j==0||j==1||j==2||j==7||j==8||j==6))
                        ||((i==2) && (j==0||j==1||j==8||j==7))
                        ||((i==3) && (j==0))
                        ||((i==5) && (j==8))
                        ||((i==6) && (j==0||j==1||j==7||j==8))
                        ||((i==7) && (j==0||j==1||j==2||j==6||j==7||j==8))
                        ||((i==8) && (j==0||j==1||j==2||j==3||j==6||j==7||j==8)))) {
                    Assert.assertNotEquals(null, grid.getMatrix()[i][j]);
                }
                if(objectCard1[i][j]!=null){
                    Assert.assertEquals(objectCard1[i][j], grid.getMatrix()[i][j]);
                }
            }
        }
    }
    @Test
    public void needRefill__correctOutputMatrixNeedsRefill(){
        grid.refill();
        Assert.assertNotNull(grid.draw(new Position[]{new Position(0, 3), new Position(0, 4)}));
        Assert.assertNotNull(grid.draw(new Position[]{new Position(1, 3), new Position(1, 4)}));
        Assert.assertNotNull(grid.draw(new Position[]{new Position(2, 3), new Position(2, 4)}));
        Assert.assertNotNull(grid.draw(new Position[]{new Position(3, 3), new Position(3, 4)}));
        Assert.assertNotNull(grid.draw(new Position[]{new Position(4, 3), new Position(4, 4)}));
        Assert.assertNotNull(grid.draw(new Position[]{new Position(5, 3), new Position(5, 4)}));
        Assert.assertNotNull(grid.draw(new Position[]{new Position(6, 3), new Position(6, 4)}));
        Assert.assertNotNull(grid.draw(new Position[]{new Position(7, 3), new Position(7, 4)}));
        Assert.assertNotNull(grid.draw(new Position[]{new Position(1, 5)}));
        Assert.assertNotNull(grid.draw(new Position[]{new Position(2, 5)}));
        Assert.assertNotNull(grid.draw(new Position[]{new Position(3, 5)}));
        Assert.assertNotNull(grid.draw(new Position[]{new Position(4, 5)}));
        Assert.assertNotNull(grid.draw(new Position[]{new Position(5, 5)}));
        Assert.assertNotNull(grid.draw(new Position[]{new Position(6, 5)}));
        Assert.assertNotNull(grid.draw(new Position[]{new Position(7, 5)}));
        Assert.assertNotNull(grid.draw(new Position[]{new Position(8, 5)}));
        Assert.assertNotNull(grid.draw(new Position[]{new Position(2, 6)}));
        Assert.assertNotNull(grid.draw(new Position[]{new Position(3, 6)}));
        Assert.assertNotNull(grid.draw(new Position[]{new Position(4, 6)}));
        Assert.assertNotNull(grid.draw(new Position[]{new Position(5, 6)}));
        Assert.assertNotNull(grid.draw(new Position[]{new Position(6, 6)}));
        Assert.assertNotNull(grid.draw(new Position[]{new Position(3, 7), new Position(3, 8)}));
        Assert.assertNotNull(grid.draw(new Position[]{new Position(4, 7), new Position(4, 8)}));
        Assert.assertNotNull(grid.draw(new Position[]{new Position(2, 2)}));
        Assert.assertNotNull(grid.draw(new Position[]{new Position(3, 2)}));
        Assert.assertNotNull(grid.draw(new Position[]{new Position(4, 2)}));
        Assert.assertNotNull(grid.draw(new Position[]{new Position(5, 2)}));
        Assert.assertNotNull(grid.draw(new Position[]{new Position(6, 2)}));
        Assert.assertNotNull(grid.draw(new Position[]{new Position(3, 1)}));
        Assert.assertNotNull(grid.draw(new Position[]{new Position(4, 1)}));
        Assert.assertNotNull(grid.draw(new Position[]{new Position(5, 1)}));
        Assert.assertNotNull(grid.draw(new Position[]{new Position(5, 0)}));
        /*for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                if ((((i==0) && (j==0||j==1||j==2||j==5||j==6||j==7||j==8))
                        ||((i==1) && (j==0||j==1||j==2||j==7||j==8||j==6))
                        ||((i==2) && (j==0||j==1||j==8||j==7))
                        ||((i==3) && (j==0))
                        ||((i==5) && (j==8))
                        ||((i==6) && (j==0||j==1||j==7||j==8))
                        ||((i==7) && (j==0||j==1||j==2||j==6||j==7||j==8))
                        ||((i==8) && (j==0||j==1||j==2||j==3||j==6||j==7||j==8)))) {
                    System.out.print("| ");
                }
                else if (grid.getMatrix()[i][j]==null)
                    System.out.print("|@");
                else
                    System.out.print("|"+grid.getMatrix()[i][j]);
            }
            System.out.println("");
        }*/
        Assert.assertTrue(grid.needRefill());
    }
    @Test
    public void needRefill__correctOutputMatrixDoNotNeed(){
        grid.refill();
        Assert.assertFalse(grid.needRefill());
    }
    @Test
    public void isDrawAvailable_incorrectInputnullInInput_correctOutput(){
        Assert.assertFalse(grid.isDrawAvailable(null));
    }
    @Test
    public void isDrawAvailable_incorrectInputTooShortAndTooLongVector_correctOutput(){
        Position[] positionLong= new Position[4];
        Position[] positionShort=new Position[0];
        for(int i=0; i<4;i++)
            positionLong[i] = new Position(i, i);
        Assert.assertFalse(grid.isDrawAvailable(positionShort));
        Assert.assertFalse(grid.isDrawAvailable(positionLong));
    }
    @Test
    public void isDrawAvailable_incorrectInputVectorContainsNull_correctOutput(){
        Position[] positions = new Position[3];
        positions[0] = new Position(0,0);
        positions[1] = null;
        positions[2] = new Position(1,1);
        Assert.assertFalse(grid.isDrawAvailable(positions));
    }
    @Test
    public void isDrawAvailable_incorrectInputPositionNotAligned_correctOutput(){
        Position[] positions = new Position[3];
        positions[0] = new Position(0,3);
        positions[1] = new Position(1,4);
        positions[2] = new Position(2,5);
        Assert.assertFalse(grid.isDrawAvailable(positions));
        positions[0] = new Position(0,3);
        positions[1] = new Position(2,3);
        positions[2] = new Position(3,3);
        Assert.assertFalse(grid.isDrawAvailable(positions));
    }
    @Test
    public void isDrawAvailable_incorrectInputPositionWithoutFreeBorder_correctOutput(){
        grid.refill();
        Position[] positions = new Position[3];
        positions[0] = new Position(4,4);
        positions[1] = new Position(4,5);
        positions[2] = new Position(4,6);
        Assert.assertFalse(grid.isDrawAvailable(positions));
    }
    @Test
    public void isDrawAvailable_correctInput_correctOutput(){
        grid.refill();
        Position[] positions = new Position[2];
        positions[0] = new Position(0,3);
        positions[1] = new Position(0,4);
        Assert.assertTrue(grid.isDrawAvailable(positions));
    }
    @Test
    public void draw_correctInput_correctOutput(){
        grid.refill();
        ObjectCard[][] objectCardsMatric = new ObjectCard[9][9];
        for(int i=0; i<9; i++)
            for(int j=0; j<9; j++)
                objectCardsMatric[i][j]=grid.getMatrix()[i][j];
        Position[] positions = new Position[2];
        positions[0] = new Position(0,3);
        positions[1] = new Position(0,4);
        ObjectCard[] objectCards = grid.draw(positions);
        for(int i=3; i<5; i++){
            Assert.assertNull(grid.getMatrix()[0][i]);
            Assert.assertEquals(objectCardsMatric[0][i],objectCards[i-3]);
        }
    }


    @After
    public void tearDown(){
        grid = new Grid(4, new CardGenerator());
    }
}
