package it.polimi.ingsw;

import it.polimi.ingsw.model.Cards.CommonGoalCard;
import it.polimi.ingsw.model.Cards.ConcreteCommonCards.CommonGoalCard5;
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

public class CommonGoalCard5Test {
    CommonGoalCard commonGoalCard;
    //Type never affects this algorithm
    Type type = Type.FIRST;

    Library library;
    /**Constructor
     * @author: Riccardo Figini
     * @throws IOException throws an exception*/
    public CommonGoalCard5Test() throws IOException {
        commonGoalCard = new CommonGoalCard5();
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
    @After
    public void tearDown() {
        for(int i=0; i<library.getNumberOfRows(); i++){
            for(int j=0; j<library.getNumberOfColumns(); j++){
                library.insertCardInObjectCards(null, i,j);
            }
        }
    }

    @Test
    public void isSatisfied_correctInputCheckFisrtLastColumn_trueInOutput(){
        for(int i=0; i<library.getNumberOfRows(); i++)
            insertElement(i, 0, Color.getEnumFromRelativeInt(i));
        for(int i=0; i<library.getNumberOfRows(); i++)
            insertElement(i, 4, Color.getEnumFromRelativeInt(i));
    }
    @Test
    public void isSatisfied_correctInputCheckSecondFourthColumn_trueInOutput(){
        for(int i=0; i<library.getNumberOfRows(); i++)
            insertElement(i, 1, Color.getEnumFromRelativeInt(i));
        for(int i=0; i<library.getNumberOfRows(); i++)
            insertElement(i, 3, Color.getEnumFromRelativeInt(i));
    }
    @Test
    public void isSatisfied_correctInputCheckthirdFourthColumn_trueInOutput(){
        for(int i=0; i<library.getNumberOfRows(); i++)
            insertElement(i, 2, Color.getEnumFromRelativeInt(i));
        for(int i=0; i<library.getNumberOfRows(); i++)
            insertElement(i, 3, Color.getEnumFromRelativeInt(i));
    }
    @Test
    public void isSatisfied_correctInputEmptyMatrix_falseInOutput(){
        Assert.assertFalse(commonGoalCard.isSatisfied(library));
    }
    @Test
    public void isSatisfied_correctInputFullMatrix_falseInOutput(){
        for(int i=0; i<library.getNumberOfRows()-1; i++)
            insertElement(i, 3, Color.getEnumFromRelativeInt(i));
        insertElement(library.getNumberOfRows()-1, 3, Color.getEnumFromRelativeInt(0));
        fillEmptyPart();
        Assert.assertFalse(commonGoalCard.isSatisfied(library));
    }

}
