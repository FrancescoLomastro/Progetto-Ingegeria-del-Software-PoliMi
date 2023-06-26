package it.polimi.ingsw.model.Cards.ConcreteCommonCards;

import it.polimi.ingsw.model.Cards.CommonGoalCard;
import it.polimi.ingsw.model.Cards.ObjectCard;
import it.polimi.ingsw.enums.Color;
import it.polimi.ingsw.model.Player.Library;

import java.util.HashSet;
/**
 * This class contains the algorithm to verify if the following common goal is satisfied.
 * The goal is: Four lines each formed by 5 tiles of maximum three different types.
 *              One line can show the same or a different combination of another line
 *
 * @author: Alberto Aniballi
 * */
public class CommonGoalCard3 extends CommonGoalCard {
    public CommonGoalCard3(){
        num=3;
        setDescription("Four lines each formed by 5 tiles of " +
                "maximum three different types. One " +
                "line can show the same or a different " +
                "combination of another line.");
    }
    /**
     * It verifies if the library satisfies the goal of this specific common goal card
     *
     * @param library   the library on which we will verify if the goal is satisfied or not
     * @return: boolean that is true if the goal is satisfied, false otherwise
     * */
    @Override
    public boolean isSatisfied(Library library) {
        int count_row = 0;
        for(int row=0;row<library.getNumberOfRows();row++) {
            if(rowIsFull(library.getMatrix()[row])) {
                HashSet<Color> s = new HashSet<>();
                for (int col = 0; col < library.getNumberOfColumns(); col++) {
                    if (library.getMatrix()[row][col] != null)
                        s.add(library.getMatrix()[row][col].getColor());
                }
                count_row = (s.size()<=3) ? count_row+1 : count_row;
            }
        }

        return count_row >= 4;
    }
    /**This method check is a row is full
     * @author: Riccardo Figini
     * @return {@code boolean} true if a row is full
     * */
    private boolean rowIsFull(ObjectCard[] objectCards){
        for (ObjectCard objectCard : objectCards)
            if (objectCard == null)
                return false;
        return true;
    }

}
