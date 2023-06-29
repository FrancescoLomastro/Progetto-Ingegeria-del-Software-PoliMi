package it.polimi.ingsw;

import it.polimi.ingsw.model.CardGenerator.CardGenerator;
import it.polimi.ingsw.model.Cards.ObjectCard;
import it.polimi.ingsw.model.Cards.PersonalGoalCard;
import it.polimi.ingsw.enums.Color;
import it.polimi.ingsw.enums.Type;
import it.polimi.ingsw.model.Player.Library;
import it.polimi.ingsw.utility.Position;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class PersonalGoalCardTest {
    PersonalGoalCard personalGoalCard;
    CardGenerator cardGenerator;
    Type type = Type.THIRD;
    Library library;

    public PersonalGoalCardTest(){
        cardGenerator = new CardGenerator(4);
        personalGoalCard = cardGenerator.generatePersonalGoalCard();
        library = new Library();
    }

    @Before
    public void setUp(){
        library = new Library();
    }

    /**
     * Replicates a random Personal goal cards anc builds a library that matches all of that positions
     */
    @Test
    public void countPersonalGoalCardPoints_allMatchPositions_correctPointsOutput() {
        assertDoesNotThrow(()-> {
            HashMap<Position, Color> objectCardsGoal = new HashMap<>();
            for (int objectCard_i = 0; objectCard_i < personalGoalCard.getGoalVector().size(); objectCard_i++) {
                objectCardsGoal.put(
                        (Position) personalGoalCard.getGoalVector().get(objectCard_i).getFirst(),
                        (Color) personalGoalCard.getGoalVector().get(objectCard_i).getSecond());
            }

            for (int row = 5; row >= 0; row--) {
                for (int col = 0; col < library.getNumberOfColumns(); col++) {
                    Position position = new Position(row, col);
                    if (objectCardsGoal.get(position) == null) {
                        library.insertCardsInLibrary(col, new ObjectCard("", Color.BLUE, Type.FIRST));
                    } else {
                        library.insertCardsInLibrary(col, new ObjectCard("", (Color) objectCardsGoal.get(position), Type.FIRST));
                    }
                }
            }
            Assert.assertEquals(12, personalGoalCard.countPersonalGoalCardPoints(library));
        });
    }
    @Test
    public void countPersonalGoalCardPoints_5MatchPositions_correctPointsOutput(){
        assertDoesNotThrow(()-> {
            HashMap<Position, Color> objectCardsGoal = new HashMap<>();
            for (int objectCard_i = 0; objectCard_i < 5; objectCard_i++) {
                objectCardsGoal.put(
                        (Position) personalGoalCard.getGoalVector().get(objectCard_i).getFirst(),
                        (Color) personalGoalCard.getGoalVector().get(objectCard_i).getSecond());
            }

            for (int row = library.getNumberOfRows()-1; row >= 0; row--) {
                for (int col = 0; col < library.getNumberOfColumns(); col++) {
                    Position position = new Position(row, col);
                    if (objectCardsGoal.get(position) == null) {
                        library.insertCardsInLibrary(col, new ObjectCard("", Color.BLUE, Type.FIRST));
                    } else {
                        library.insertCardsInLibrary(col, new ObjectCard("", objectCardsGoal.get(position), Type.FIRST));
                    }
                }
            }
            Assert.assertEquals(9, personalGoalCard.countPersonalGoalCardPoints(library));
        });
    }
    @Test
    public void countPersonalGoalCardPoints_4MatchPositions_correctPointsOutput(){
        assertDoesNotThrow(()-> {
            HashMap<Position, Color> objectCardsGoal = new HashMap<>();
            for (int objectCard_i = 0; objectCard_i < 4; objectCard_i++) {
                objectCardsGoal.put(
                        (Position) personalGoalCard.getGoalVector().get(objectCard_i).getFirst(),
                        (Color) personalGoalCard.getGoalVector().get(objectCard_i).getSecond());
            }

            for (int row = 5; row >= 0; row--) {
                for (int col = 0; col < library.getNumberOfColumns(); col++) {
                    Position position = new Position(row, col);
                    if (objectCardsGoal.get(position) == null) {
                        library.insertCardsInLibrary(col, new ObjectCard("", Color.BLUE, Type.FIRST));
                    } else {
                        library.insertCardsInLibrary(col, new ObjectCard("", (Color) objectCardsGoal.get(position), Type.FIRST));
                    }
                }
            }
            Assert.assertEquals(6, personalGoalCard.countPersonalGoalCardPoints(library));
        });
    }
    @Test
    public void countPersonalGoalCardPoints_3MatchPositions_correctPointsOutput(){
        assertDoesNotThrow(()-> {
            HashMap<Position, Color> objectCardsGoal = new HashMap<>();
            for (int objectCard_i = 0; objectCard_i < 3; objectCard_i++) {
                objectCardsGoal.put(
                        (Position) personalGoalCard.getGoalVector().get(objectCard_i).getFirst(),
                        (Color) personalGoalCard.getGoalVector().get(objectCard_i).getSecond());
            }

            for (int row = 5; row >= 0; row--) {
                for (int col = 0; col < library.getNumberOfColumns(); col++) {
                    Position position = new Position(row, col);
                    if (objectCardsGoal.get(position) == null) {
                        library.insertCardsInLibrary(col, new ObjectCard("", Color.BLUE, Type.FIRST));
                    } else {
                        library.insertCardsInLibrary(col, new ObjectCard("", (Color) objectCardsGoal.get(position), Type.FIRST));
                    }
                }
            }
            Assert.assertEquals(4, personalGoalCard.countPersonalGoalCardPoints(library));
        });
    }
    @Test
    public void countPersonalGoalCardPoints_2MatchPositions_correctPointsOutput(){
        assertDoesNotThrow(()-> {
            HashMap<Position, Color> objectCardsGoal = new HashMap<>();
            for (int objectCard_i = 0; objectCard_i < 2; objectCard_i++) {
                objectCardsGoal.put(
                        (Position) personalGoalCard.getGoalVector().get(objectCard_i).getFirst(),
                        (Color) personalGoalCard.getGoalVector().get(objectCard_i).getSecond());
            }

            for (int row = 5; row >= 0; row--) {
                for (int col = 0; col < library.getNumberOfColumns(); col++) {
                    Position position = new Position(row, col);
                    if (objectCardsGoal.get(position) == null) {
                        library.insertCardsInLibrary(col, new ObjectCard("", Color.BLUE, Type.FIRST));
                    } else {
                        library.insertCardsInLibrary(col, new ObjectCard("", (Color) objectCardsGoal.get(position), Type.FIRST));
                    }
                }
            }
            Assert.assertEquals(2, personalGoalCard.countPersonalGoalCardPoints(library));
        });
    }
    @Test
    public void countPersonalGoalCardPoints_1MatchPositions_correctPointsOutput(){
        assertDoesNotThrow(()-> {
            HashMap<Position, Color> objectCardsGoal = new HashMap<>();
            for (int objectCard_i = 0; objectCard_i < 1; objectCard_i++) {
                objectCardsGoal.put(
                        (Position) personalGoalCard.getGoalVector().get(objectCard_i).getFirst(),
                        (Color) personalGoalCard.getGoalVector().get(objectCard_i).getSecond());
            }

            for (int row = 5; row >= 0; row--) {
                for (int col = 0; col < library.getNumberOfColumns(); col++) {
                    Position position = new Position(row, col);
                    if (objectCardsGoal.get(position) == null) {
                        library.insertCardsInLibrary(col, new ObjectCard("", Color.BLUE, Type.FIRST));
                    } else {
                        library.insertCardsInLibrary(col, new ObjectCard("", (Color) objectCardsGoal.get(position), Type.FIRST));
                    }
                }
            }
            Assert.assertEquals(1, personalGoalCard.countPersonalGoalCardPoints(library));
        });
    }
    @Test
    public void countPersonalGoalCardPoints_0MatchPositions_correctPointsOutput(){
        Assert.assertEquals(personalGoalCard.countPersonalGoalCardPoints(library), 0);
    }
    @After
    public void tearDown(){
        library= new Library();
        cardGenerator = new CardGenerator(4);
        personalGoalCard = cardGenerator.generatePersonalGoalCard();
    }
}
