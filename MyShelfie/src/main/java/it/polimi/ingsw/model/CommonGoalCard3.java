package it.polimi.ingsw.model;

import java.util.HashSet;
/**
 * This class contains the algorithm to verify if the second common goal algorithm is satisfied.
 * The goal is the following: Four lines each formed by 5 tiles of maximum three different types.
 *                            One line can show the same or a different combination of another line
 *
 * @author: Alberto Aniballi
 * */
public class CommonGoalCard3 extends CommonGoalCard {
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
                s.add(library.getLibrary()[row][col].getColor());
            }
            count_row = (s.size()<=3) ? count_row+1 : count_row;
        }

        if (count_row>=4) {
            return true;
        } else {
            return false;
        }
    }
}
