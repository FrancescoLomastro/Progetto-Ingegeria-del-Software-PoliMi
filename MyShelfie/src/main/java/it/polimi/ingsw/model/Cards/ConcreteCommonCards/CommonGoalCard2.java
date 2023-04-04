package it.polimi.ingsw.model.Cards.ConcreteCommonCards;

import it.polimi.ingsw.model.Cards.*;
import it.polimi.ingsw.model.Enums.*;
import it.polimi.ingsw.model.Player.*;

import java.util.HashMap;
import java.util.HashSet;

/**
 * This class contains the algorithm to verify if the following common goal is satisfied.
 * The goal is: Four groups each containing at least 4 tiles of the same type (not necessarily
 *              in the depicted shape). The tiles of one group can be different from those of another group.
 *
 * @author: Alberto Aniballi
 * */
public class CommonGoalCard2 extends CommonGoalCard {
    /**
     * It verifies if the library satisfies the goal of this specific common goal card
     *
     * @param library   the library on which we will verify if the goal is satisfied or not
     * @return: boolean that is true if the goal is satisfied, false otherwise
     * */
    @Override
    public boolean isSatisfied(Library library) {

        HashSet<String> checkedCells = new HashSet<>();
        int correctGroupCounter = 0;

        for(int row=0;row<6;row++) {
            for(int col=0;col<5;col++) {
                if ((library.getLibrary()[row][col]!=null) && !checkedCells.contains((row)+"_"+(col))) {

                    checkedCells.add((row)+"_"+(col));
                    int same_color_neighbours = library.countSameColorNeighbours(row,col,checkedCells);

                    Color colorToCheck = library.getLibrary()[row][col].getColor();
                    if (same_color_neighbours>=4) {
                        correctGroupCounter += 1;
                        if (correctGroupCounter == 4) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

}
