package it.polimi.ingsw.model.Cards.ConcreteCommonCards;

import it.polimi.ingsw.model.Cards.CommonGoalCard;
import it.polimi.ingsw.enums.Color;
import it.polimi.ingsw.model.Player.Library;

import java.util.HashSet;
/**
 * This class contains the algorithm to verify if the following common goal is satisfied.
 * The goal is: Three columns each formed by 6 tiles of maximum three different types.
 *              One column can show the same or a different combination of another column.
 *
 * @author Alberto Aniballi
 * */
public class CommonGoalCard8 extends CommonGoalCard {
    public CommonGoalCard8(){
        num=8;
        setDescription("Three columns each formed by 6 tiles " +
                "of maximum three different types. One " +
                "column can show the same or a different " +
                "combination of another column");
    }
    /**
     * It verifies if the library satisfies the goal of this specific common goal card
     * @author Alberto Aniballi
     * @author Francesco Lo Mastro
     * @param library   the library on which we will verify if the goal is satisfied or not
     * @return boolean that is true if the goal is satisfied, false otherwise
     * */
    @Override
    public boolean isSatisfied(Library library) {
        int count_col = 0;
        for(int col=0;col<5;col++) {
            HashSet<Color> s = new HashSet<>();
            if (library.getMatrix()[0][col]!=null) {
                for(int row=0;row<6;row++) {
                    if (library.getMatrix()[row][col] != null) {
                        s.add(library.getMatrix()[row][col].getColor());
                    }
                }
                count_col = (s.size()>=1 && s.size()<=3) ? count_col+1 : count_col;
            }
        }

        if (count_col>=3) {
            return true;
        } else {
            return false;
        }
    }
}
