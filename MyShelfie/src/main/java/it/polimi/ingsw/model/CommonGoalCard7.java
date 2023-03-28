package it.polimi.ingsw.model;

import java.util.HashSet;

public class CommonGoalCard7 extends CommonGoalCard {
    @Override
    public boolean isSatisfied(Library lib) {
        int count_row = 0;
        for(int row=0;row<6;row++) {
            HashSet<String> s = new HashSet<>();
            for(int col=0;col<5;col++) {
                s.add(library[row][col].getColor());
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
