package it.polimi.ingsw.model;

import java.util.HashSet;

public class CommonGoalCard5 extends CommonGoalCard {
    @Override
    public boolean isSatisfied(Library lib) {

        int count_col = 0;
        for(int col=0;col<5;col++) {
            HashSet<String> s = new HashSet<>();
            for(int row=0;row<6;row++) {
                s.add(library[row][col].getColor());
            }
            count_col = (s.size()==3) ? count_col+1 : count_col;
        }

        if (count_col>=2) {
            return true;
        } else {
            return false;
        }
    }
}
