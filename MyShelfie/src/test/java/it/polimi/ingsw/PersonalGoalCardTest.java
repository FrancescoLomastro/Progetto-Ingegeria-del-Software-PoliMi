package it.polimi.ingsw;

import it.polimi.ingsw.model.CardGenerator.CardGenerator;
import it.polimi.ingsw.model.Cards.ObjectCard;
import it.polimi.ingsw.model.Cards.PersonalGoalCard;
import it.polimi.ingsw.model.Enums.Color;
import it.polimi.ingsw.model.Enums.Type;
import it.polimi.ingsw.model.Player.Library;
import it.polimi.ingsw.model.Utility.Position;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.util.Random;

public class PersonalGoalCardTest {
    PersonalGoalCard personalGoalCard;
    CardGenerator cardGenerator;
    Type type = Type.THIRD;
    Library library;

    /**Constructor
     * @author: Riccardo Figini*/
    public PersonalGoalCardTest(){
        cardGenerator = new CardGenerator();
        personalGoalCard = cardGenerator.generatePersonalGoalCard();
        library = new Library(5,6);
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
                    insertElement(i, j, Color.getEnumFromRelativeInt(random.nextInt(5)));
            }
        }
    }

    @Test
    public void countPersonalGoalCardPoints_correctInputAllMatch_correctOutput(){
        Position position;
        for(int i=0; i<personalGoalCard.getGoalVector().size(); i++) {
            position = (Position) personalGoalCard.getGoalVector().get(i).getFirst();
            library.insertCardInObjectCards(
                    new ObjectCard("", (Color) personalGoalCard.getGoalVector().get(i).getSecond(), type),
                    position.getRow(),
                    position.getColumn()
            );
        }
        fillEmptyPart();
        Assert.assertEquals(personalGoalCard.countPersonalGoalCardPoints(library), 12);
    }
    @Test
    public void countPersonalGoalCardPoints_correctInput5match_correctOutput(){
        Position position;
        for(int i=0; i<personalGoalCard.getGoalVector().size()-1; i++) {
            if(i<5) {
                position = (Position) personalGoalCard.getGoalVector().get(i).getFirst();
                library.insertCardInObjectCards(
                        new ObjectCard("", (Color) personalGoalCard.getGoalVector().get(i).getSecond(), type),
                        position.getRow(),
                        position.getColumn()
                );
            }
            position = (Position) personalGoalCard.getGoalVector().get(i).getFirst();
            library.insertCardInObjectCards(
                    new ObjectCard("", (Color) personalGoalCard.getGoalVector().get(i).getSecond(), type),
                    position.getRow(),
                    position.getColumn()
            );
        }
        fillEmptyPart();
        Assert.assertEquals(personalGoalCard.countPersonalGoalCardPoints(library), 9);
    }
    @Test
    public void countPersonalGoalCardPoints_correctInput4match_correctOutput(){
        Position position;
        for(int i=0; i<personalGoalCard.getGoalVector().size()-2; i++) {
            if(i<4) {
                position = (Position) personalGoalCard.getGoalVector().get(i).getFirst();
                library.insertCardInObjectCards(
                        new ObjectCard("", (Color) personalGoalCard.getGoalVector().get(i).getSecond(), type),
                        position.getRow(),
                        position.getColumn()
                );
            }
            position = (Position) personalGoalCard.getGoalVector().get(i).getFirst();
            library.insertCardInObjectCards(
                    new ObjectCard("", (Color) personalGoalCard.getGoalVector().get(i-1).getSecond(), type),
                    position.getRow(),
                    position.getColumn()
            );
        }
        fillEmptyPart();
        Assert.assertEquals(personalGoalCard.countPersonalGoalCardPoints(library), 6);
    }
    @Test
    public void countPersonalGoalCardPoints_correctInput3match_correctOutput(){
        Position position;
        for(int i=0; i<personalGoalCard.getGoalVector().size()-3; i++) {
            if(i<3) {
                position = (Position) personalGoalCard.getGoalVector().get(i).getFirst();
                library.insertCardInObjectCards(
                        new ObjectCard("", (Color) personalGoalCard.getGoalVector().get(i).getSecond(), type),
                        position.getRow(),
                        position.getColumn()
                );
            }
            position = (Position) personalGoalCard.getGoalVector().get(i).getFirst();
            library.insertCardInObjectCards(
                    new ObjectCard("", (Color) personalGoalCard.getGoalVector().get(i-1).getSecond(), type),
                    position.getRow(),
                    position.getColumn()
            );
        }
        fillEmptyPart();
        Assert.assertEquals(personalGoalCard.countPersonalGoalCardPoints(library), 4);
    }
    @Test
    public void countPersonalGoalCardPoints_correctInput2match_correctOutput(){
        Position position;
        for(int i=0; i<personalGoalCard.getGoalVector().size()-4; i++) {
            if(i<2) {
                position = (Position) personalGoalCard.getGoalVector().get(i).getFirst();
                library.insertCardInObjectCards(
                        new ObjectCard("", (Color) personalGoalCard.getGoalVector().get(i).getSecond(), type),
                        position.getRow(),
                        position.getColumn()
                );
            }
            position = (Position) personalGoalCard.getGoalVector().get(i).getFirst();
            library.insertCardInObjectCards(
                    new ObjectCard("", (Color) personalGoalCard.getGoalVector().get(i-1).getSecond(), type),
                    position.getRow(),
                    position.getColumn()
            );
        }
        fillEmptyPart();
        Assert.assertEquals(personalGoalCard.countPersonalGoalCardPoints(library), 2);
    }
    @Test
    public void countPersonalGoalCardPoints_correctInput1match_correctOutput(){
        Position position;
        for(int i=0; i<personalGoalCard.getGoalVector().size(); i++) {
            if(i<1) {
                position = (Position) personalGoalCard.getGoalVector().get(i).getFirst();
                library.insertCardInObjectCards(
                        new ObjectCard("", (Color) personalGoalCard.getGoalVector().get(i).getSecond(), type),
                        position.getRow(),
                        position.getColumn()
                );
            }
            position = (Position) personalGoalCard.getGoalVector().get(i).getFirst();
            library.insertCardInObjectCards(
                    new ObjectCard("", (Color) personalGoalCard.getGoalVector().get(i-1).getSecond(), type),
                    position.getRow(),
                    position.getColumn()
            );
        }
        fillEmptyPart();
        Assert.assertEquals(personalGoalCard.countPersonalGoalCardPoints(library), 1);
    }
    @Test
    public void countPersonalGoalCardPoints_correctInput0match_correctOutput(){
        Assert.assertEquals(personalGoalCard.countPersonalGoalCardPoints(library), 12);
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
