package it.polimi.ingsw.model;

import java.util.HashSet;

/**
 * This class contains the algorithm to verify if the second common goal algorithm is satisfied.
 * The goal is the following: Two columns each formed by 6 different types of tiles.
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
        for(int col=0;col<5;col++) {
            HashSet<Color> s = new HashSet<>();
            for(int row=0;row<6;row++) {
                s.add(library.getLibrary()[row][col].getColor());
            }
            count_col = (s.size()==6) ? count_col+1 : count_col;
        }

        if (count_col==2) {
            return true;
        } else {
            return false;
        }
    }
}
