package it.polimi.ingsw.model.Cards.ConcreteCommonCards;

import it.polimi.ingsw.model.Cards.CommonGoalCard;
import it.polimi.ingsw.model.Cards.ObjectCard;
import it.polimi.ingsw.model.Enums.Color;
import it.polimi.ingsw.model.Player.Library;

import java.util.HashSet;

/**
 * This class contains the algorithm to verify if the following common goal is satisfied.
 * The goal is: Two columns each formed by 6 different types of tiles.
 *
 * @author: Alberto Aniballi
 * */
public class CommonGoalCard5 extends CommonGoalCard {
    /**
     * It verifies if the library satisfies the goal of this specific common goal card
     *
     * @param library   the library on which we will verify if the goal is satisfied or not
     * @return: boolean that is true if the goal is satisfied, false otherwise
     * */
    @Override
    public boolean isSatisfied(Library library) {
        int count_col = 0;
        for(int col=0;col<library.getNumberOfColumns();col++) {
            if (columnIsFull(library.getMatrix(), col)) {
                HashSet<Color> s = new HashSet<>();
                for (int row = 0; row < library.getNumberOfRows(); row++) {
                    s.add(library.getMatrix()[row][col].getColor());
                }
                count_col = (s.size() == 6) ? count_col + 1 : count_col;
            }
        }
        return count_col == 2;
    }
    private boolean columnIsFull(ObjectCard[][] objectCards, int colum){
        for(int i=0; i<objectCards.length; i++)
            if(objectCards[i][colum]==null)
                return false;
        return true;
    }
}
