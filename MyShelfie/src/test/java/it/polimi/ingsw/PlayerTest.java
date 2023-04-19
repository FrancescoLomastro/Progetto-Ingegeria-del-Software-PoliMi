package it.polimi.ingsw;

import it.polimi.ingsw.model.CardGenerator.CardGenerator;
import it.polimi.ingsw.model.Cards.ObjectCard;
import it.polimi.ingsw.model.Cards.PersonalGoalCard;
import it.polimi.ingsw.model.Enums.Color;
import it.polimi.ingsw.model.Enums.Type;
import it.polimi.ingsw.model.Player.Library;
import it.polimi.ingsw.model.Player.Player;
import it.polimi.ingsw.model.Utility.Position;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

/**
 * Class used to test Player class.
 * @author: Alberto Aniballi
 * */
public class PlayerTest {
    private CardGenerator cardGenerator;
    private Player player;

    public PlayerTest() {
        cardGenerator = new CardGenerator();
        player = new Player("Frank", cardGenerator);
    }

    @Before
    public void setUp() {
        for(int row=0;row<player.getLibrary().getNumberOfRows();row++) {
            for(int col=0;col<player.getLibrary().getNumberOfColumns();col++) {
                player.getLibrary().getMatrix()[row][col]=null;
            }
        }
    }

    /**
     * First test: final points have to be 20 because personalGoalCard is satisfied in all 6 positions and
     *              we have a unique block of adjacent object cards of type BLUE of more than 8 object cards.
     */
    @Test
    public void countFinalPoints_correctInputAllMatchPersonalGoalCard_correctOutput() {
        assertDoesNotThrow(()-> {
            HashMap<Position, Color> objectCardsGoal = new HashMap<>();
            for (int objectCard_i = 0; objectCard_i < player.getPersonalGoalCard().getGoalVector().size(); objectCard_i++) {
                objectCardsGoal.put(
                        (Position) player.getPersonalGoalCard().getGoalVector().get(objectCard_i).getFirst(),
                        (Color) player.getPersonalGoalCard().getGoalVector().get(objectCard_i).getSecond());
            }

            for (int row = 5; row >= 0; row--) {
                for (int col = 0; col < player.getLibrary().getNumberOfColumns(); col++) {
                    Position position = new Position(row, col);
                    if (objectCardsGoal.get(position) == null) {
                        player.getLibrary().insertCardsInLibrary(col, new ObjectCard("", Color.BLUE, Type.FIRST));
                    } else {
                        player.getLibrary().insertCardsInLibrary(col, new ObjectCard("", objectCardsGoal.get(position), Type.FIRST));
                    }
                }
            }
            Assert.assertEquals(20, player.countFinalPoints());
        });
    }

    /**
     * Second test: final points have to be 17 because personalGoalCard is satisfied in just 5 positions and
     *              we have a unique block of adjacent object cards of type BLUE of more than 8 object cards.
     */
    @Test
    public void countFinalPoints_correctInput5MatchPersonalGoalCard_correctOutput() {
        assertDoesNotThrow(()-> {
            HashMap<Position, Color> objectCardsGoal = new HashMap<>();
            for (int objectCard_i = 0; objectCard_i < player.getPersonalGoalCard().getGoalVector().size() - 1; objectCard_i++) {
                objectCardsGoal.put(
                        (Position) player.getPersonalGoalCard().getGoalVector().get(objectCard_i).getFirst(),
                        (Color) player.getPersonalGoalCard().getGoalVector().get(objectCard_i).getSecond());
            }


            for (int row = 5; row >= 0; row--) {
                for (int col = 0; col < player.getLibrary().getNumberOfColumns(); col++) {
                    Position position = new Position(row, col);
                    if (objectCardsGoal.get(position) == null) {
                        player.getLibrary().insertCardsInLibrary(col, new ObjectCard("", Color.BLUE, Type.FIRST));
                    } else {
                        player.getLibrary().insertCardsInLibrary(col, new ObjectCard("", objectCardsGoal.get(position), Type.FIRST));
                    }
                }
            }


            Assert.assertEquals(17, player.countFinalPoints());
        });
    }



    /**
     * Third test: final points have to be 17 because personalGoalCard is satisfied in just 4 positions and
     *              we have a unique block of adjacent object cards of type BLUE of more than 8 object cards.
     */
    @Test
    public void countFinalPoints_correctInput4MatchPersonalGoalCard_correctOutput() {
        assertDoesNotThrow(()-> {
            HashMap<Position, Color> objectCardsGoal = new HashMap<>();
            for (int objectCard_i = 0; objectCard_i < player.getPersonalGoalCard().getGoalVector().size() - 2; objectCard_i++) {
                objectCardsGoal.put(
                        (Position) player.getPersonalGoalCard().getGoalVector().get(objectCard_i).getFirst(),
                        (Color) player.getPersonalGoalCard().getGoalVector().get(objectCard_i).getSecond());
            }


            for (int row = 5; row >= 0; row--) {
                for (int col = 0; col < player.getLibrary().getNumberOfColumns(); col++) {
                    Position position = new Position(row, col);
                    if (objectCardsGoal.get(position) == null) {
                        player.getLibrary().insertCardsInLibrary(col, new ObjectCard("", Color.BLUE, Type.FIRST));
                    } else {
                        player.getLibrary().insertCardsInLibrary(col, new ObjectCard("", (Color) objectCardsGoal.get(position), Type.FIRST));
                    }
                }
            }
            Assert.assertEquals(14, player.countFinalPoints());


        });
    }

    /**
     * Fourth test: final points have to be 28 because personalGoalCard is satisfied in all 6 positions,
     *              we have one block of adjacent object cards of type BLUE of more than 8 object cards
     *              and we have one block of adjacent object cards of type Pink of more than 8 object cards.
     */
    @Test
    public void countFinalPoints_secondCorrectInputAllMatchPersonalGoalCard_correctOutput() {
        assertDoesNotThrow(()-> {
            HashMap<Position, Color> objectCardsGoal = new HashMap<>();
            for (int objectCard_i = 0; objectCard_i < player.getPersonalGoalCard().getGoalVector().size(); objectCard_i++) {
                objectCardsGoal.put(
                        (Position) player.getPersonalGoalCard().getGoalVector().get(objectCard_i).getFirst(),
                        (Color) player.getPersonalGoalCard().getGoalVector().get(objectCard_i).getSecond());
            }


            for (int row = 5; row >= 0; row--) {
                for (int col = 0; col < player.getLibrary().getNumberOfColumns(); col++) {
                    Position position = new Position(row, col);
                    if (objectCardsGoal.get(position) == null) {
                        if (row > 2) {
                            player.getLibrary().insertCardsInLibrary(col, new ObjectCard("", Color.YELLOW, Type.FIRST));
                        } else {
                            player.getLibrary().insertCardsInLibrary(col, new ObjectCard("", Color.BLUE, Type.FIRST));
                        }
                    } else {
                        player.getLibrary().insertCardsInLibrary(col, new ObjectCard("", (Color) objectCardsGoal.get(position), Type.FIRST));
                    }
                }
            }

            Assert.assertEquals(28, player.countFinalPoints());
        });
    }

    /**
     * Fifth test: final points have to be 25 because personalGoalCard is satisfied in only 5 positions,
     *              we have one block of adjacent object cards of type BLUE of more than 8 object cards
     *              and we have one block of adjacent object cards of type Pink of more than 8 object cards.
     */
    @Test
    public void countFinalPoints_secondCorrectInput5MatchPersonalGoalCard_correctOutput() {
        assertDoesNotThrow(()-> {
            HashMap<Position, Color> objectCardsGoal = new HashMap<>();
            for (int objectCard_i = 0; objectCard_i < player.getPersonalGoalCard().getGoalVector().size() - 1; objectCard_i++) {
                objectCardsGoal.put(
                        (Position) player.getPersonalGoalCard().getGoalVector().get(objectCard_i).getFirst(),
                        (Color) player.getPersonalGoalCard().getGoalVector().get(objectCard_i).getSecond());
            }


            for (int row = 5; row >= 0; row--) {
                for (int col = 0; col < player.getLibrary().getNumberOfColumns(); col++) {
                    Position position = new Position(row, col);
                    if (objectCardsGoal.get(position) == null) {
                        if (row > 2) {
                            player.getLibrary().insertCardsInLibrary(col, new ObjectCard("", Color.YELLOW, Type.FIRST));
                        } else {
                            player.getLibrary().insertCardsInLibrary(col, new ObjectCard("", Color.BLUE, Type.FIRST));
                        }
                    } else {
                        player.getLibrary().insertCardsInLibrary(col, new ObjectCard("", objectCardsGoal.get(position), Type.FIRST));
                    }
                }
            }


            Assert.assertEquals(25, player.countFinalPoints());
        });
    }

    /**
     * Sixth test: final points have to be 17 because personalGoalCard is satisfied in only 1 position,
     *              we have one block of adjacent object cards of type BLUE of more than 8 object cards
     *              and we have one block of adjacent object cards of type Pink of more than 8 object cards.
     */
    @Test
    public void countFinalPoints_secondCorrectInput1MatchPersonalGoalCard_correctOutput() {
        assertDoesNotThrow(()-> {
            HashMap<Position, Color> objectCardsGoal = new HashMap<>();
            for (int objectCard_i = 0; objectCard_i < player.getPersonalGoalCard().getGoalVector().size() - 5; objectCard_i++) {
                objectCardsGoal.put(
                        (Position) player.getPersonalGoalCard().getGoalVector().get(objectCard_i).getFirst(),
                        (Color) player.getPersonalGoalCard().getGoalVector().get(objectCard_i).getSecond());
            }

            for (int row = 5; row >= 0; row--) {
                for (int col = 0; col < player.getLibrary().getNumberOfColumns(); col++) {
                    Position position = new Position(row, col);
                    if (objectCardsGoal.get(position) == null) {
                        if (row > 2) {
                            player.getLibrary().insertCardsInLibrary(col, new ObjectCard("", Color.YELLOW, Type.FIRST));
                        } else {
                            player.getLibrary().insertCardsInLibrary(col, new ObjectCard("", Color.BLUE, Type.FIRST));
                        }
                    } else {
                        player.getLibrary().insertCardsInLibrary(col, new ObjectCard("", (Color) objectCardsGoal.get(position), Type.FIRST));
                    }
                }
            }
            Assert.assertEquals(17, player.countFinalPoints());
        });
    }

    /**
     * Seventh test: final points have to be 36 because personalGoalCard is satisfied in all positions,
     *              we have one block of adjacent object cards of type BLUE of more than 8 object cards,
     *              we have one block of adjacent object cards of type Pink of more than 8 object cards
     *              and we have one block of adjacent object cards of type Green of more than 8 object cards.
     */
    @Test
    public void countFinalPoints_secondCorrectInput2MatchPersonalGoalCard_correctOutput() {
        assertDoesNotThrow(()-> {
            HashMap<Position, Color> objectCardsGoal = new HashMap<>();
            for (int objectCard_i = 0; objectCard_i < player.getPersonalGoalCard().getGoalVector().size(); objectCard_i++) {
                objectCardsGoal.put(
                        (Position) player.getPersonalGoalCard().getGoalVector().get(objectCard_i).getFirst(),
                        (Color) player.getPersonalGoalCard().getGoalVector().get(objectCard_i).getSecond());
            }

                for (int row = 5; row >= 0; row--) {
                    for (int col = 0; col < player.getLibrary().getNumberOfColumns(); col++) {
                        Position position = new Position(row, col);
                        if (objectCardsGoal.get(position) == null) {
                            if (row >= 4) {
                                player.getLibrary().insertCardsInLibrary(col, new ObjectCard("", Color.GREEN, Type.FIRST));
                            } else if (row >= 2) {
                                player.getLibrary().insertCardsInLibrary(col, new ObjectCard("", Color.YELLOW, Type.FIRST));
                            } else {
                                player.getLibrary().insertCardsInLibrary(col, new ObjectCard("", Color.BLUE, Type.FIRST));
                            }
                        } else {
                            player.getLibrary().insertCardsInLibrary(col, new ObjectCard("", (Color) objectCardsGoal.get(position), Type.FIRST));
                        }
                    }
                }
                Assert.assertEquals(36, player.countFinalPoints());

        });
    }


}
