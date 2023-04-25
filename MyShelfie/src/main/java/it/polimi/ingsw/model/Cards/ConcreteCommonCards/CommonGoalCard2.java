package it.polimi.ingsw.model.Cards.ConcreteCommonCards;

import it.polimi.ingsw.model.Cards.CommonGoalCard;
import it.polimi.ingsw.model.Enums.Color;
import it.polimi.ingsw.model.Player.Library;
import it.polimi.ingsw.model.Utility.Position;

import java.util.HashSet;

/**
 * This class contains the algorithm to verify if the following common goal is satisfied.
 * The goal is: Four groups each containing at least 4 tiles of the same type (not necessarily
 *              in the depicted shape). The tiles of one group can be different from those of another group.
 *
 * @author: Alberto Aniballi
 * */
public class CommonGoalCard2 extends CommonGoalCard {
    public CommonGoalCard2(){
        setDescription("Four groups each containing at least " +
                "4 tiles of the same type (not necessarily " +
                "in the depicted shape). " +
                "The tiles of one group can be different " +
                "from those of another group.");
    }
    /**
     * It verifies if the library satisfies the goal of this specific common goal card
     *
     * @param library   the library on which we will verify if the goal is satisfied or not
     * @return: boolean that is true if the goal is satisfied, false otherwise
     * */
    @Override
    public boolean isSatisfied(Library library) {

        int correctGroupCounter = 0;
        Position position;
        HashSet<Position> checkedCells = new HashSet<>();

        for(int row=0;row<6;row++) {
            for(int col=0;col<5;col++) {
                position=new Position(row,col);
                if ((library.getMatrix()[row][col]!=null) && !checkedCells.contains(position)) {
                    if(checkedCells.add(position))
                    {
                        int same_color_neighbours = library.countNeighbours(row, col, checkedCells)+1;

                        Color colorToCheck = library.getMatrix()[row][col].getColor();
                        if (same_color_neighbours >= 4) {
                            correctGroupCounter += 1;
                            if (correctGroupCounter == 4) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

}
