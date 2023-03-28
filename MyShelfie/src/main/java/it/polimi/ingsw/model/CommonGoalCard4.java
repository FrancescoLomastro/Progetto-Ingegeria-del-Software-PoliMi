package it.polimi.ingsw.model;

import java.util.HashMap;
import java.util.HashSet;

public class CommonGoalCard4 extends CommonGoalCard {
    @Override
    public boolean isSatisfied(Library lib) {
        /*
        Vedi Library countAdjacentPoints() ma ti fermi a 4 ed incrementi contatore:
        riflettere sul concetto di separati: cosa succede se abbiamo due strade possibili? cosa succede se ne ho 8 vicine?
         */
        HashSet<String> checkedCells = new HashSet<>();
        HashMap<String,Integer> propertySatisfiedCounter = new HashMap<>();
        propertySatisfiedCounter.put("Red",0);
        propertySatisfiedCounter.put("Blue",0);
        propertySatisfiedCounter.put("lightBlue",0);
        propertySatisfiedCounter.put("Green",0);
        propertySatisfiedCounter.put("White",0);
        propertySatisfiedCounter.put("Yellow",0);

        int row = 0;
        int column = 0;
        while (row<getNumberOfRows()) {
            while ((column <getNumberOfColumns()) && (!checkedCells.contains(row+"_"+ column))) {
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