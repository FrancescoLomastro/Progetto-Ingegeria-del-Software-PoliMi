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

import java.util.*;

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
        library = new Library();
    }

    @Before
    public void setUp(){
        library = new Library();
    }
    @Test
    public void countPersonalGoalCardPoints_correctInputAllMatch_correctOutput() {
        HashMap<Position,Color> objectCardsGoal = new HashMap<>();
        for(int objectCard_i=0;objectCard_i<personalGoalCard.getGoalVector().size(); objectCard_i++) {
            objectCardsGoal.put(
                    (Position) personalGoalCard.getGoalVector().get(objectCard_i).getFirst(),
                    (Color) personalGoalCard.getGoalVector().get(objectCard_i).getSecond());
        }

        for(int row=5;row>=0;row--) {
            for(int col=0;col<library.getNumberOfColumns();col++) {
                Position position = new Position(row,col);
                if(objectCardsGoal.get(position)==null) {
                    library.insertCardsInLibrary(col, new ObjectCard("", Color.BLUE, Type.FIRST));
                } else {
                    library.insertCardsInLibrary(col, new ObjectCard("", (Color) objectCardsGoal.get(position), Type.FIRST ));
                }
            }
        }
        Assert.assertEquals(12,personalGoalCard.countPersonalGoalCardPoints(library));

        /*
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
         */
    }
    @Test
    public void countPersonalGoalCardPoints_correctInput5match_correctOutput(){
        HashMap<Position,Color> objectCardsGoal = new HashMap<>();
        for(int objectCard_i=0;objectCard_i<5; objectCard_i++) {
            objectCardsGoal.put(
                    (Position) personalGoalCard.getGoalVector().get(objectCard_i).getFirst(),
                    (Color) personalGoalCard.getGoalVector().get(objectCard_i).getSecond());
        }

        for(int row=5;row>=0;row--) {
            for(int col=0;col<library.getNumberOfColumns();col++) {
                Position position = new Position(row,col);
                if(objectCardsGoal.get(position)==null) {
                    library.insertCardsInLibrary(col, new ObjectCard("", Color.BLUE, Type.FIRST));
                } else {
                    library.insertCardsInLibrary(col, new ObjectCard("", (Color) objectCardsGoal.get(position), Type.FIRST ));
                }
            }
        }
        Assert.assertEquals(9,personalGoalCard.countPersonalGoalCardPoints(library));
        /*
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
         */
    }
    @Test
    public void countPersonalGoalCardPoints_correctInput4match_correctOutput(){
        HashMap<Position,Color> objectCardsGoal = new HashMap<>();
        for(int objectCard_i=0;objectCard_i<4; objectCard_i++) {
            objectCardsGoal.put(
                    (Position) personalGoalCard.getGoalVector().get(objectCard_i).getFirst(),
                    (Color) personalGoalCard.getGoalVector().get(objectCard_i).getSecond());
        }

        for(int row=5;row>=0;row--) {
            for(int col=0;col<library.getNumberOfColumns();col++) {
                Position position = new Position(row,col);
                if(objectCardsGoal.get(position)==null) {
                    library.insertCardsInLibrary(col, new ObjectCard("", Color.BLUE, Type.FIRST));
                } else {
                    library.insertCardsInLibrary(col, new ObjectCard("", (Color) objectCardsGoal.get(position), Type.FIRST ));
                }
            }
        }
        Assert.assertEquals(6,personalGoalCard.countPersonalGoalCardPoints(library));
        /*
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
         */
    }
    @Test
    public void countPersonalGoalCardPoints_correctInput3match_correctOutput(){
        HashMap<Position,Color> objectCardsGoal = new HashMap<>();
        for(int objectCard_i=0;objectCard_i<3; objectCard_i++) {
            objectCardsGoal.put(
                    (Position) personalGoalCard.getGoalVector().get(objectCard_i).getFirst(),
                    (Color) personalGoalCard.getGoalVector().get(objectCard_i).getSecond());
        }

        for(int row=5;row>=0;row--) {
            for(int col=0;col<library.getNumberOfColumns();col++) {
                Position position = new Position(row,col);
                if(objectCardsGoal.get(position)==null) {
                    library.insertCardsInLibrary(col, new ObjectCard("", Color.BLUE, Type.FIRST));
                } else {
                    library.insertCardsInLibrary(col, new ObjectCard("", (Color) objectCardsGoal.get(position), Type.FIRST ));
                }
            }
        }
        Assert.assertEquals(4,personalGoalCard.countPersonalGoalCardPoints(library));
        /*
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
         */
    }
    @Test
    public void countPersonalGoalCardPoints_correctInput2match_correctOutput(){
        HashMap<Position,Color> objectCardsGoal = new HashMap<>();
        for(int objectCard_i=0;objectCard_i<2; objectCard_i++) {
            objectCardsGoal.put(
                    (Position) personalGoalCard.getGoalVector().get(objectCard_i).getFirst(),
                    (Color) personalGoalCard.getGoalVector().get(objectCard_i).getSecond());
        }

        for(int row=5;row>=0;row--) {
            for(int col=0;col<library.getNumberOfColumns();col++) {
                Position position = new Position(row,col);
                if(objectCardsGoal.get(position)==null) {
                    library.insertCardsInLibrary(col, new ObjectCard("", Color.BLUE, Type.FIRST));
                } else {
                    library.insertCardsInLibrary(col, new ObjectCard("", (Color) objectCardsGoal.get(position), Type.FIRST ));
                }
            }
        }
        Assert.assertEquals(2,personalGoalCard.countPersonalGoalCardPoints(library));
        /*
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
         */
    }
    @Test
    public void countPersonalGoalCardPoints_correctInput1match_correctOutput(){
        HashMap<Position,Color> objectCardsGoal = new HashMap<>();
        for(int objectCard_i=0;objectCard_i<1; objectCard_i++) {
            objectCardsGoal.put(
                    (Position) personalGoalCard.getGoalVector().get(objectCard_i).getFirst(),
                    (Color) personalGoalCard.getGoalVector().get(objectCard_i).getSecond());
        }

        for(int row=5;row>=0;row--) {
            for(int col=0;col<library.getNumberOfColumns();col++) {
                Position position = new Position(row,col);
                if(objectCardsGoal.get(position)==null) {
                    library.insertCardsInLibrary(col, new ObjectCard("", Color.BLUE, Type.FIRST));
                } else {
                    library.insertCardsInLibrary(col, new ObjectCard("", (Color) objectCardsGoal.get(position), Type.FIRST ));
                }
            }
        }
        Assert.assertEquals(1,personalGoalCard.countPersonalGoalCardPoints(library));
        /*
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
         */
    }
    @Test
    public void countPersonalGoalCardPoints_correctInput0match_correctOutput(){
        Assert.assertEquals(personalGoalCard.countPersonalGoalCardPoints(library), 0);
    }

    /*
    @After
    public void tearDown() {
        for(int i=0; i<library.getNumberOfRows(); i++){
            for(int j=0; j<library.getNumberOfColumns(); j++){
                library.insertCardInObjectCards(null, i,j);
            }
        }
    }
    */
}
