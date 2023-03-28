package it.polimi.ingsw.model;

import java.util.HashMap;
import java.util.HashSet;

public class CommonGoalCard6 extends CommonGoalCard {
    @Override
    public boolean isSatisfied(Library lib) {
        /*
        due gruppi separati di 4 tessere dello stesso tipo.
        Idea:
        - Va verificata per tutti i 6 colori, al primo che viene trovato mi fermo. Uso un vettore di contatori di 6 elementi che partono da zero.
        - Parto con una DFS,come per countAdjiacent, e cerco le sequenze con stesso colore (presente,su,destra,giù) che inglobino celle non già incluse in altre sequenze
        - Una volta che ho trovato una sequenza, incremento il contatore di 1 per tale tipo/colore. Se uno raggiunge 2, mi fermo (anche prima di giungere al termine).
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
                    checkedCells.add((row)+"_"+(col));
                    String colorToCheck = library[row][col].getColor();
                    if ((library[row+1][col] != null) && (library[row+1][col].getColor().equals(colorToCheck))) {
                        if ((library[row+1][col+1] != null) && (library[row+1][col+1].getColor().equals(colorToCheck))) {
                            if ((library[row][col+1] != null) && (library[row][col+1].getColor().equals(colorToCheck))) {
                                checkedCells.add((row+1)+"_"+(col));
                                checkedCells.add((row+1)+"_"+(col+1));
                                checkedCells.add((row)+"_"+(col+1));
                                propertySatisfiedCounter.put(colorToCheck,propertySatisfiedCounter.get(colorToCheck)+1);

                                if (propertySatisfiedCounter.get(colorToCheck)==2) {
                                    return true;
                                }
                            }
                        }
                    }

                }
            }
        }
        return false;
    }
}
