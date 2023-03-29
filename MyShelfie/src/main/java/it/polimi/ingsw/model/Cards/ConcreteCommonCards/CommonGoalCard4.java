package it.polimi.ingsw.model.Cards.ConcreteCommonCards;

import it.polimi.ingsw.model.Cards.*;
import it.polimi.ingsw.model.Enums.*;
import it.polimi.ingsw.model.Player.*;

/**
 * This class contains the algorithm to verify if the second common goal algorithm is satisfied.
 * The goal is the following: Four tiles of the same type in the four corners of the bookshelf.
 *
 * @author: Alberto Aniballi
 * */
public class CommonGoalCard4 extends CommonGoalCard {
    /**
     * It verifies if the library satisfies the goal of this specific common goal card
     *
     * @param library   the library on which we will verify if the goal is satisfied or not
     * @return: boolean that is true if the goal is satisfied, false otherwise
     * */
    @Override
    public boolean isSatisfied(Library library) {
        if(library.getLibrary()[0][0]!=null) {
            Color color = library.getLibrary()[0][0].getColor();
            return library.getLibrary()[0][0] != null
                    && library.getLibrary()[0][4] != null
                    && library.getLibrary()[5][0] != null
                    && library.getLibrary()[5][4] != null
                    && color.equals(library.getLibrary()[0][4].getColor())
                    && color.equals(library.getLibrary()[5][4].getColor())
                    && color.equals(library.getLibrary()[5][0].getColor());
        }
        return false;
    }
}