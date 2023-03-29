package it.polimi.ingsw.model.Cards.ConcreteCommonCards;

import it.polimi.ingsw.model.Cards.*;
import it.polimi.ingsw.model.Enums.*;
import it.polimi.ingsw.model.Player.*;

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
        /*Alberto, se guardi questa classe l'ho modificata. Non hai escluso il caso in cui
        * alcune righe non fossero completamente riempiete, queste non possono essere considerate
        * quando fai la somma*/
        int count_row = 0;
        for(int row=0;row<library.getNumberOfRows();row++) {
            if(rowIsFull(library.getLibrary()[row])) {
                HashSet<Color> s = new HashSet<>();
                for (int col = 0; col < library.getNumberOfColumns(); col++) {
                    if (library.getLibrary()[row][col] != null)
                        s.add(library.getLibrary()[row][col].getColor());
                }
                count_row = (s.size()<=3) ? count_row+1 : count_row;
            }
        }

        return count_row >= 4;
    }

    private boolean rowIsFull(ObjectCard[] objectCards){
        for (ObjectCard objectCard : objectCards)
            if (objectCard == null)
                return false;
        return true;
    }

}
