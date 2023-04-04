package it.polimi.ingsw.model.Cards.ConcreteCommonCards;

import it.polimi.ingsw.model.Cards.CommonGoalCard;
import it.polimi.ingsw.model.Enums.Color;
import it.polimi.ingsw.model.Player.Library;

import java.util.HashSet;
/**
 * This class contains the algorithm to verify if the following common goal is satisfied.
 * The goal is: Two lines each formed by 5 different types of tiles. One line can show the
 *              same or a different combination of the other line.
 *
 * @author: Alberto Aniballi
 * */
public class CommonGoalCard7 extends CommonGoalCard {
    /**
     * It verifies if the library satisfies the goal of this specific common goal card
     *
     * @param library   the library on which we will verify if the goal is satisfied or not
     * @return: boolean that is true if the goal is satisfied, false otherwise
     * */
    @Override
    public boolean isSatisfied(Library library) {
        int count_row = 0;
        for(int row=0;row<6;row++) {
            HashSet<Color> s = new HashSet<>();
            for(int col=0;col<5;col++) {
                if (library.getMatrix()[row][col] != null) {
                    s.add(library.getMatrix()[row][col].getColor());
                }
            }
            count_row = (s.size()==5) ? count_row+1 : count_row;
        }

        if (count_row>=2) {
            return true;
        } else {
            return false;
        }
    }
}
