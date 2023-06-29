package it.polimi.ingsw.model.Cards.ConcreteCommonCards;

import it.polimi.ingsw.model.Cards.CommonGoalCard;
import it.polimi.ingsw.enums.Color;
import it.polimi.ingsw.model.Player.Library;

import java.util.HashSet;

/**
 * This class contains the algorithm to verify if the following common goal is satisfied.
 * The goal is: Two groups each containing 4 tiles of the same type in a 2x2 square.
 *              The tiles of one square can be different from those of the other square.
 *
 * @author: Alberto Aniballi
 * */
public class CommonGoalCard6 extends CommonGoalCard {
    public CommonGoalCard6(){
        num=6;
        setDescription("Two groups each containing 4 tiles of " +
                "the same type in a 2x2 square. The tiles " +
                "of one square can be different from " +
                "those of the other square.");
    }
    /**
     * It verifies if the library satisfies the goal of this specific common goal card
     * @author: Riccardo Figini
     * @author: Albero Aniballi
     * @author: Francesco Lo Mastro
     * @param library   the library on which we will verify if the goal is satisfied or not
     * @return: boolean that is true if the goal is satisfied, false otherwise
     * */
    @Override
    public boolean isSatisfied(Library library) {

        HashSet<String> checkedCells = new HashSet<>();

        int counter=0;

        for(int row=0;row<library.getNumberOfRows()-1;row++) {
            for(int col=0;col<library.getNumberOfColumns()-1;col++) {
                if (library.getMatrix()[row][col]!=null && (library.getMatrix()[row][col]!=null) && !checkedCells.contains((row)+"_"+(col))) {
                    checkedCells.add((row)+"_"+(col));
                    Color colorToCheck = library.getMatrix()[row][col].getColor();
                    if ((library.getMatrix()[row+1][col] != null) && (library.getMatrix()[row+1][col].getColor().equals(colorToCheck))) {
                        if ((library.getMatrix()[row+1][col+1] != null) && (library.getMatrix()[row+1][col+1].getColor().equals(colorToCheck))) {
                            if ((library.getMatrix()[row][col+1] != null) && (library.getMatrix()[row][col+1].getColor().equals(colorToCheck))) {
                                checkedCells.add((row+1)+"_"+(col));
                                checkedCells.add((row+1)+"_"+(col+1));
                                checkedCells.add((row)+"_"+(col+1));
                                //propertySatisfiedCounter.put(colorToCheck,propertySatisfiedCounter.get(colorToCheck)+1);
                                counter++;
                                if (counter==2) {
                                    return true;
                                }
                            }
                        }
                    }
                }
                else
                    checkedCells.add((row)+"_"+(col));
            }
        }
        return false;
    }
}
