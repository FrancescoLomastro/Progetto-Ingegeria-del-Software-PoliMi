package it.polimi.ingsw.model.Cards.ConcreteCommonCards;
// sarebbe la mia classe eightCellsSameType

import it.polimi.ingsw.model.Cards.CommonGoalCard;
import it.polimi.ingsw.model.Cards.ObjectCard;
import it.polimi.ingsw.model.Enums.Color;
import it.polimi.ingsw.model.Player.Library;

import java.util.HashMap;

/**
 * This class contains the algorithm to verify if the following common goal is satisfied.
 * The goal is: Eight tiles of the same type. There’s no restriction about the position of these tiles.
 *
 * @author: Andrea Ferrini
 * */
public class CommonGoalCard10 extends CommonGoalCard {

    public CommonGoalCard10(){
        setDescription("Eight tiles of the same type. There’s no " +
                "restriction about the position of these " +
                "tiles");
    }
    /**
     * the main algorithm that checks this common goal
     * @param library the turn player's library
     * @return boolean : true if satisfied, false if not satisfied
     */
    @Override
    public boolean isSatisfied(Library library) {
        ObjectCard[][] lib = library.getMatrix();
        // istanzio e inizializzo l'array delle occorrenze
        HashMap<Color,Integer> occurencesTypeCounter = new HashMap<>();

        for(int row = 0; row<library.getNumberOfRows(); row++) {
            for (int col = 0; col<library.getNumberOfColumns(); col++) {
                if (lib[row][col]!=null) {
                    Color color = lib[row][col].getColor();

                    if(occurencesTypeCounter.get(color) == null) {
                        occurencesTypeCounter.put(color,1);
                    } else {
                        occurencesTypeCounter.put(color,occurencesTypeCounter.get(color)+1);
                        if (occurencesTypeCounter.get(color) >= 8) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}
