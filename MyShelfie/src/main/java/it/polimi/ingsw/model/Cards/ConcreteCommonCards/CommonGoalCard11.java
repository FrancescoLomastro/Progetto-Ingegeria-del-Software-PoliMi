package it.polimi.ingsw.model.Cards.ConcreteCommonCards;
// sarebbe la mia classe fiveColumnsDescHigh

import it.polimi.ingsw.model.Cards.CommonGoalCard;
import it.polimi.ingsw.model.Player.Library;

/**
 * This class contains the algorithm to verify if the following common goal is satisfied.
 * The goal is: Five columns of increasing or decreasing height. Starting from the first column on
 *              the left or on the right, each next column must be made of exactly one more tile.
 *              Tiles can be of any type.
 * @author Alberto Aniballi
 */
public class CommonGoalCard11 extends CommonGoalCard {

    /*
    private int c, i, j;

    private Library library;

    private ObjectCard[][] lib;

    private int checkOneOrTwoResult;


    private int isOne = 1;

    private int isTwo = 1;
    */

    /**
     * It verifies if the library satisfies the goal of this specific common goal card
     *
     * @param library   the library on which we will verify if the goal is satisfied or not
     * @return: boolean that is true if the goal is satisfied, false otherwise
     * */
    @Override
    public boolean isSatisfied(Library library) {

        int current = 0;
        int previous = 0;

        for (int i = 0; i < library.getNumberOfColumns(); i++) {
            if (i == 0) {
                previous = library.findNumberOfFreeCells(i);
                current = previous;
                if(current != library.getNumberOfRows()-1)
                    break;
            } else {
                previous = current;
                current = library.findNumberOfFreeCells(i);
                if (previous != current + 1)
                    break;
                else {
                    if (i == library.getNumberOfColumns() - 1) {
                        return true;
                    }
                }
            }
        }

        for (int i = 0; i < library.getNumberOfColumns(); i++) {
            if (i == 0) {
                previous = library.findNumberOfFreeCells(i);
                current = previous;
                if(current != 1)
                    return false;
            } else {
                previous = current;
                current = library.findNumberOfFreeCells(i);
                if (previous != current - 1)
                    break;
                else {
                    if (i == library.getNumberOfColumns() - 1) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
