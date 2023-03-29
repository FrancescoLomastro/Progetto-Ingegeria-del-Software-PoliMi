package it.polimi.ingsw.model.Cards.ConcreteCommonCards;

import it.polimi.ingsw.model.Cards.*;
import it.polimi.ingsw.model.Enums.*;
import it.polimi.ingsw.model.Player.*;

import java.util.HashMap;
import java.util.HashSet;

/**
 * This class contains the algorithm to verify if the first common goal algorithm is satisfied.
 * The goal is the following: Six groups each containing at least 2 tiles of the same type.
 *                            The tiles of one group can be different from those of another group.
 *
 * @author: Alberto Aniballi
 * */
public class CommonGoalCard0 extends CommonGoalCard {
    /**
     * It verifies if the library satisfies the goal of this specific common goal card
     *
     * @param library   the library on which we will verify if the goal is satisfied or not
     * @return: boolean that is true if the goal is satisfied, false otherwise
     * */
    @Override
    public boolean isSatisfied(Library library) {
        /*
        riflettere sul concetto di separati
         */
        HashSet<String> checkedCells = new HashSet<>();
        HashMap<Color,Integer> propertySatisfiedCounter = new HashMap<>();
        propertySatisfiedCounter.put(Color.PINK,0);
        propertySatisfiedCounter.put(Color.BLUE,0);
        propertySatisfiedCounter.put(Color.BEIGE,0);
        propertySatisfiedCounter.put(Color.GREEN,0);
        propertySatisfiedCounter.put(Color.LIGHTBLUE,0);
        propertySatisfiedCounter.put(Color.YELLOW,0);

        for(int row=0;row<6;row++) {
            for(int col=0;col<5;col++) {
                if ((library.getLibrary()[row][col]!=null) && !checkedCells.contains((row)+"_"+(col))) {
                    checkedCells.add((row)+"_"+(col));
                    Color colorToCheck = (library.getLibrary())[row][col].getColor();

                    if (row!=5 && (library.getLibrary()[row+1][col]!=null) && library.getLibrary()[row+1][col].getColor().equals(colorToCheck)) {
                        checkedCells.add((row+1)+"_"+(col));
                        propertySatisfiedCounter.put(colorToCheck,propertySatisfiedCounter.get(colorToCheck)+1);
                        if (propertySatisfiedCounter.get(colorToCheck)==6) {
                            return true;
                        }
                        continue;
                    }

                    if (col!=4&&(library.getLibrary()[row][col+1]!=null) && library.getLibrary()[row][col+1].getColor().equals(colorToCheck)) {
                        checkedCells.add((row)+"_"+(col+1));
                        propertySatisfiedCounter.put(colorToCheck,propertySatisfiedCounter.get(colorToCheck)+1);
                        if (propertySatisfiedCounter.get(colorToCheck)==6) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}