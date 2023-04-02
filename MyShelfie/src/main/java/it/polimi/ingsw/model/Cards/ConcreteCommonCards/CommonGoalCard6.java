package it.polimi.ingsw.model.Cards.ConcreteCommonCards;

import it.polimi.ingsw.model.Cards.*;
import it.polimi.ingsw.model.Enums.*;
import it.polimi.ingsw.model.Player.*;

import java.util.HashMap;
import java.util.HashSet;

/**
 * This class contains the algorithm to verify if the following common goal is satisfied.
 * The goal is: Two groups each containing 4 tiles of the same type in a 2x2 square.
 *              The tiles of one square can be different from those of the other square.
 *
 * @author: Alberto Aniballi
 * */
public class CommonGoalCard6 extends CommonGoalCard {
    /**
     * It verifies if the library satisfies the goal of this specific common goal card
     *
     * @param library   the library on which we will verify if the goal is satisfied or not
     * @return: boolean that is true if the goal is satisfied, false otherwise
     * */
    @Override
    public boolean isSatisfied(Library library) {
        /*
        due gruppi separati di 4 tessere dello stesso tipo.
        Idea:
        - Va verificata per tutti i 6 colori, al primo che viene trovato mi fermo. Uso un vettore di contatori di 6 elementi che partono da zero.
        - Parto con una DFS,come per countAdjiacent, e cerco le sequenze con stesso colore (presente,su,destra,giù) che inglobino celle non già incluse in altre sequenze
        - Una volta che ho trovato una sequenza, incremento il contatore di 1 per tale tipo/colore. Se uno raggiunge 2, mi fermo (anche prima di giungere al termine).
         */
        HashSet<String> checkedCells = new HashSet<>();
        /*
        HashMap<Color,Integer> propertySatisfiedCounter = new HashMap<>();
        propertySatisfiedCounter.put(Color.PINK,0);
        propertySatisfiedCounter.put(Color.BLUE,0);
        propertySatisfiedCounter.put(Color.BEIGE,0);
        propertySatisfiedCounter.put(Color.GREEN,0);
        propertySatisfiedCounter.put(Color.LIGHTBLUE,0);
        propertySatisfiedCounter.put(Color.YELLOW,0);
        */
        int counter=0;

        for(int row=0;row<library.getNumberOfRows()-1;row++) {
            for(int col=0;col<library.getNumberOfColumns()-1;col++) {
                if (library.getLibrary()[row][col]!=null && (library.getLibrary()[row][col]!=null) && !checkedCells.contains((row)+"_"+(col))) {
                    checkedCells.add((row)+"_"+(col));
                    Color colorToCheck = library.getLibrary()[row][col].getColor();
                    if ((library.getLibrary()[row+1][col] != null) && (library.getLibrary()[row+1][col].getColor().equals(colorToCheck))) {
                        if ((library.getLibrary()[row+1][col+1] != null) && (library.getLibrary()[row+1][col+1].getColor().equals(colorToCheck))) {
                            if ((library.getLibrary()[row][col+1] != null) && (library.getLibrary()[row][col+1].getColor().equals(colorToCheck))) {
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
