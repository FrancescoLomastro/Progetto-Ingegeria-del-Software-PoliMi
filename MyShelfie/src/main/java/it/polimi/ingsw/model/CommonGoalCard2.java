package it.polimi.ingsw.model;

import java.util.HashMap;
import java.util.HashSet;

/**
 * This class contains the algorithm to verify if the second common goal algorithm is satisfied.
 * The goal is the following: Four groups each containing at least 4 tiles of the same type (not necessarily
 * in the depicted shape). The tiles of one group can be different from those of another group.
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
        HashMap<Color,Integer> propertySatisfiedCounter = new HashMap<>();
        propertySatisfiedCounter.put(Color.PINK,0);
        propertySatisfiedCounter.put(Color.BLUE,0);
        propertySatisfiedCounter.put(Color.BEIGE,0);
        propertySatisfiedCounter.put(Color.GREEN,0);
        propertySatisfiedCounter.put(Color.LIGHTBLUE,0);
        propertySatisfiedCounter.put(Color.YELLOW,0);

        int row = 0;
        int column = 0;
        while (row<library.getNumberOfRows()) {
            while ((column <library.getNumberOfColumns()) && (!checkedCells.contains(row+"_"+ column))) {
                checkedCells.add(row+"_"+column);
                int sameColorNeighboursUp = countSameColorNeighbours(row, column,"Up");
                String colorToCheck = library[row][col].getColor();
                if (sameColorNeighboursUp >= 4) {
                    propertySatisfiedCounter.put(colorToCheck,propertySatisfiedCounter.get(colorToCheck)+1);
                    if (propertySatisfiedCounter.get(colorToCheck==4)) {
                        return true;
                    }
                } else {
                    int sameColorNeighboursRight = countSameColorNeighbours(row, column,"Right");
                    if ((sameColorNeighboursUp+sameColorNeighboursRight)>=4) {
                        propertySatisfiedCounter.put(colorToCheck,propertySatisfiedCounter.get(colorToCheck)+1);
                        if (propertySatisfiedCounter.get(colorToCheck)==4) {
                            return true;
                        }
                    }
                }

                /* da implementare la verifica dei punti da aggiungere in base al numero di sameColorNeighbours*/
                numberOfAdjacentPoints += addAdjacentPoints(sameColorNeighbours);
                column++;
            }
            column=0;
            row++;
        }
        return false;
    }
}
