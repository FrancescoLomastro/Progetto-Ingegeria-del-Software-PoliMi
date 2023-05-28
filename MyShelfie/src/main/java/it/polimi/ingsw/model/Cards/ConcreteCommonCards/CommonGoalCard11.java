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
    public CommonGoalCard11(){
        num=11;
        setDescription("Five columns of increasing or decreasing " +
                "height. Starting from the first column on " +
                "the left or on the right, each next column " +
                "must be made of exactly one more tile." +
                "Tiles can be of any type");
    }
    /**
     * It verifies if the library satisfies the goal of this specific common goal card
     *
     * @param library   the library on which we will verify if the goal is satisfied or not
     * @return: boolean that is true if the goal is satisfied, false otherwise
     * */
    @Override
    public boolean isSatisfied(Library library) {

        int currentColumnFreeCells = 0;
        int previousColumnFreeCells = 0;

        for (int col = 0; col < library.getNumberOfColumns(); col++) {
            if (col == 0) {
                previousColumnFreeCells = library.findNumberOfFreeCells(col);
                currentColumnFreeCells = previousColumnFreeCells;
            } else {
                previousColumnFreeCells = currentColumnFreeCells;
                currentColumnFreeCells = library.findNumberOfFreeCells(col);
                if (previousColumnFreeCells != currentColumnFreeCells + 1) {
                    break;
                } else {
                    if (col == library.getNumberOfColumns() - 1) {
                        return true;
                    }
                }
            }
        }

        for (int col = 0; col < library.getNumberOfColumns(); col++) {
            if (col == 0) {
                previousColumnFreeCells = library.findNumberOfFreeCells(col);
                currentColumnFreeCells = previousColumnFreeCells;
            } else {
                previousColumnFreeCells = currentColumnFreeCells;
                currentColumnFreeCells = library.findNumberOfFreeCells(col);
                if (previousColumnFreeCells != currentColumnFreeCells - 1)
                    break;
                else {
                    if (col == library.getNumberOfColumns() - 1) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
