package it.polimi.ingsw.model;

import java.util.HashMap;
import java.util.HashSet;

public class CommonGoalCard0 extends CommonGoalCard {
    @Override
    public boolean isSatisfied(Library lib) {
        /*
        riflettere sul concetto di separati
         */
        HashSet<String> checkedCells = new HashSet<>();
        HashMap<String,Integer> propertySatisfiedCounter = new HashMap<>();
        propertySatisfiedCounter.put("Red",0);
        propertySatisfiedCounter.put("Blue",0);
        propertySatisfiedCounter.put("lightBlue",0);
        propertySatisfiedCounter.put("Green",0);
        propertySatisfiedCounter.put("White",0);
        propertySatisfiedCounter.put("Yellow",0);

        for(int row=0;row<6;row++) {
            for(int col=0;col<5;col++) {
                if ((library[row][col]!=null) && !checkedCells.contains((row)+"_"+(col))) {
                    checkedCells.add((row)+"_"+(col)));
                    String colorToCheck = library[row][col].getColor();

                    if ((library[row+1][col]!=null) && library[row+1][col].getColor().equals(colorToCheck)) {
                        checkedCells.add((row+1)+"_"+(col));
                        propertySatisfiedCounter.put(colorToCheck,propertySatisfiedCounter.get(colorToCheck)+1);
                        if (propertySatisfiedCounter.get(colorToCheck)==6) {
                            return true;
                        }
                        continue;
                    }

                    if ((library[row+1][col]!=null) && library[row][col+1].getColor().equals(colorToCheck)) {
                        checkedCells.add((row)+"_"+(col+1));
                        propertySatisfiedCounter.put(colorToCheck,propertySatisfiedCounter.get(colorToCheck)+1);
                        if (propertySatisfiedCounter.get(colorToCheck)==6) {
                            return true;
                        }
                        continue;
                    }
                }
            }
        }
        return false;
    }
}