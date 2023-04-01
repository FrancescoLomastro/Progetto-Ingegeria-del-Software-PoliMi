package it.polimi.ingsw;

import it.polimi.ingsw.model.CardGenerator.CardGenerator;
import it.polimi.ingsw.model.Cards.ObjectCard;
import it.polimi.ingsw.model.Cards.PersonalGoalCard;
import it.polimi.ingsw.model.Enums.Color;
import it.polimi.ingsw.model.Enums.Type;
import it.polimi.ingsw.model.Player.Library;
import it.polimi.ingsw.model.Utility.Couple;
import it.polimi.ingsw.model.Utility.Position;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
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

    @Before
    public void setUp(){
        for(int i=0; i<library.getNumberOfRows(); i++){
            for(int j=0; j<library.getNumberOfColumns(); j++){
                library.insertCardInObjectCards(null, i,j);
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
        //printLibrary(library.getLibrary(), personalGoalCard.getGoalVector(), "Tutte giuste");
        Assert.assertEquals(12,personalGoalCard.countPersonalGoalCardPoints(library));
    }
    @Test
    public void countPersonalGoalCardPoints_correctInput5match_correctOutput(){
        Position position;
        for(int i=0; i<personalGoalCard.getGoalVector().size(); i++) {
            if(i<5) {
                position = (Position) personalGoalCard.getGoalVector().get(i).getFirst();
                library.insertCardInObjectCards(
                        new ObjectCard("", (Color) personalGoalCard.getGoalVector().get(i).getSecond(), type),
                        position.getRow(),
                        position.getColumn()
                );
            }
            else {
                position = (Position) personalGoalCard.getGoalVector().get(i).getFirst();
                library.insertCardInObjectCards(
                        new ObjectCard("", (Color) personalGoalCard.getGoalVector().get(0).getSecond(), type),
                        position.getRow(),
                        position.getColumn()
                );
            }
        }
        fillEmptyPart();
        //printLibrary(library.getLibrary(), personalGoalCard.getGoalVector(), "5");
        Assert.assertEquals(9,personalGoalCard.countPersonalGoalCardPoints(library));
    }
    @Test
    public void countPersonalGoalCardPoints_correctInput4match_correctOutput(){
        Position position;
        Color color;
        for(int i=0; i<personalGoalCard.getGoalVector().size(); i++) {
            if(i<4) {
                position = (Position) personalGoalCard.getGoalVector().get(i).getFirst();
                library.insertCardInObjectCards(
                        new ObjectCard("", (Color) personalGoalCard.getGoalVector().get(i).getSecond(), type),
                        position.getRow(),
                        position.getColumn()
                );
            }
            else {
                position = (Position) personalGoalCard.getGoalVector().get(i).getFirst();
                color = ((Color) personalGoalCard.getGoalVector().get(0).getSecond());
                library.insertCardInObjectCards(
                        new ObjectCard("", color, type),
                        position.getRow(),
                        position.getColumn()
                );
            }
        }
        fillEmptyPart();
        //printLibrary(library.getLibrary(), personalGoalCard.getGoalVector(), "4");
        Assert.assertEquals(6,personalGoalCard.countPersonalGoalCardPoints(library));
    }
    @Test
    public void countPersonalGoalCardPoints_correctInput3match_correctOutput(){
        Position position;
        for(int i=0; i<personalGoalCard.getGoalVector().size(); i++) {
            if(i<3) {
                position = (Position) personalGoalCard.getGoalVector().get(i).getFirst();
                library.insertCardInObjectCards(
                        new ObjectCard("", (Color) personalGoalCard.getGoalVector().get(i).getSecond(), type),
                        position.getRow(),
                        position.getColumn()
                );
            }
            else {
                position = (Position) personalGoalCard.getGoalVector().get(i).getFirst();
                library.insertCardInObjectCards(
                        new ObjectCard("", (Color) personalGoalCard.getGoalVector().get(0).getSecond(), type),
                        position.getRow(),
                        position.getColumn()
                );
            }
        }
        fillEmptyPart();
        //printLibrary(library.getLibrary(), personalGoalCard.getGoalVector(), "3");
        Assert.assertEquals(4, personalGoalCard.countPersonalGoalCardPoints(library));
    }
    @Test
    public void countPersonalGoalCardPoints_correctInput2match_correctOutput(){
        Position position;
        for(int i=0; i<personalGoalCard.getGoalVector().size(); i++) {
            if(i<2) {
                position = (Position) personalGoalCard.getGoalVector().get(i).getFirst();
                library.insertCardInObjectCards(
                        new ObjectCard("", (Color) personalGoalCard.getGoalVector().get(i).getSecond(), type),
                        position.getRow(),
                        position.getColumn()
                );
            }
            else {
                position = (Position) personalGoalCard.getGoalVector().get(i).getFirst();
                library.insertCardInObjectCards(
                        new ObjectCard("", (Color) personalGoalCard.getGoalVector().get(0).getSecond(), type),
                        position.getRow(),
                        position.getColumn()
                );
            }
        }
        fillEmptyPart();
        //printLibrary(library.getLibrary(), personalGoalCard.getGoalVector(), "2");
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
            else {
                position = (Position) personalGoalCard.getGoalVector().get(i).getFirst();
                library.insertCardInObjectCards(
                        new ObjectCard("", (Color) personalGoalCard.getGoalVector().get(0).getSecond(), type),
                        position.getRow(),
                        position.getColumn()
                );
            }
        }
        fillEmptyPart();
        //printLibrary(library.getLibrary(), personalGoalCard.getGoalVector(), "1");
        Assert.assertEquals(personalGoalCard.countPersonalGoalCardPoints(library), 1);
    }
    @Test
    public void countPersonalGoalCardPoints_correctInput0match_correctOutput(){
        Assert.assertEquals(personalGoalCard.countPersonalGoalCardPoints(library), 0);
    }

    @After
    public void tearDown() {
        for(int i=0; i<library.getNumberOfRows(); i++){
            for(int j=0; j<library.getNumberOfColumns(); j++){
                library.insertCardInObjectCards(null, i,j);
            }
        }
    }

    /*public void printLibrary(ObjectCard[][] objectCards, ArrayList<Couple> couple, String from){
        System.out.println(from + ", Obbiettivi: ");
        Position position;
        Color color;
        for(int i=0; i< 6; i++){
            position = (Position) couple.get(i).getFirst();
            color = (Color) couple.get(i).getSecond();
            System.out.println("Row: "+ position.getRow() + ", Column: "+ position.getColumn() + ", Color: "+ color);
        }
        System.out.println("");
        System.out.println("");
        for(int i=0; i<6; i++){
            for(int j=0; j<5; j++){
                color = objectCards[i][j].getColor();
                System.out.print("|row:"+i+",col:"+j+"  " + color +"   ");
            }
            System.out.println("");
        }
    }*/
}
