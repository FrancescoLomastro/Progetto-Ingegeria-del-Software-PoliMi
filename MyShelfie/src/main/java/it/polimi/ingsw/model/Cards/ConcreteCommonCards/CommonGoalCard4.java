package it.polimi.ingsw.model.Cards.ConcreteCommonCards;

import it.polimi.ingsw.model.Cards.CommonGoalCard;
import it.polimi.ingsw.model.Enums.Color;
import it.polimi.ingsw.model.Player.Library;

/**
 * This class contains the algorithm to verify if the following common goal is satisfied.
 * The goal is: Four tiles of the same type in the four corners of the bookshelf.
 *
 * @author: Alberto Aniballi
 * */
public class CommonGoalCard4 extends CommonGoalCard {
    public CommonGoalCard4(){
        setDescription("Four tiles of the same type in the four " +
                "corners of the bookshelf.");
    }
    /**
     * It verifies if the library satisfies the goal of this specific common goal card
     *
     * @param library   the library on which we will verify if the goal is satisfied or not
     * @return: boolean that is true if the goal is satisfied, false otherwise
     * */
    @Override
    public boolean isSatisfied(Library library) {
        if(library.getMatrix()[0][0]!=null) {
            Color color = library.getMatrix()[0][0].getColor();
            return library.getMatrix()[0][0] != null
                    && library.getMatrix()[0][4] != null
                    && library.getMatrix()[5][0] != null
                    && library.getMatrix()[5][4] != null
                    && color.equals(library.getMatrix()[0][4].getColor())
                    && color.equals(library.getMatrix()[5][4].getColor())
                    && color.equals(library.getMatrix()[5][0].getColor());
        }
        return false;
    }
}