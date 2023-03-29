package it.polimi.ingsw.model;

import java.util.HashSet;
/**
 * This class contains the algorithm to verify if the second common goal algorithm is satisfied.
 * The goal is the following: Three columns each formed by 6 tiles Five tiles of the same type forming an X.
*                             of maximum three different types. One column can show the same or a different combination of another column.
 *
 * @author: Alberto Aniballi
 * */
public class CommonGoalCard8 extends CommonGoalCard {
    /**
     * It verifies if the library satisfies the goal of this specific common goal card
     *
     * @param library   the library on which we will verify if the goal is satisfied or not
     * @return: boolean that is true if the goal is satisfied, false otherwise
     * */
    @Override
    public boolean isSatisfied(Library library) {
        int count_col = 0;
        for(int col=0;col<5;col++) {
            HashSet<Color> s = new HashSet<>();
            for(int row=0;row<6;row++) {
                s.add(library.getLibrary()[row][col].getColor());
            }
            count_col = (s.size()==3) ? count_col+1 : count_col;
        }

        if (count_col==3) {
            return true;
        } else {
            return false;
        }
    }
}
