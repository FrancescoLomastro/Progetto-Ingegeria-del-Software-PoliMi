package it.polimi.ingsw.model.Cards.ConcreteCommonCards;

import it.polimi.ingsw.model.Cards.CommonGoalCard;
import it.polimi.ingsw.enums.Color;
import it.polimi.ingsw.model.Player.Library;

/**
 * This class contains the algorithm to verify if the following common goal is satisfied.
 * The goal is: Five tiles of the same type forming an X.
 *
 * @author: Alberto Aniballi
 * */
public class CommonGoalCard9 extends CommonGoalCard {
    public CommonGoalCard9(){
        num=9;
        setDescription("Five tiles of the same type forming an X");
    }
    /**
     * It verifies if the library satisfies the goal of this specific common goal card
     * @author: Alberto Aniballi
     * @param library   the library on which we will verify if the goal is satisfied or not
     * @return: boolean that is true if the goal is satisfied, false otherwise
     * */
    @Override
    public boolean isSatisfied(Library library) {
        /*
        Cinque tessere delle stesso tipo che formano una x.
        Idea:
        - Verifico usando come punto di partenza la cella centrale della stella.
        - sicuramente la ricerca è ristretta al riquadro interno (5x4) per le celle centrali. Poiche quelle nei lati esterni non possono avere la stella completa.
        - Itero su tutte le celle del riquadro interno cercando la formazione a stella, mi fermo una volta trovata oppure quando sono finite le celle.
        Sarà poi da aggiungere la verifica che la carta sia effettivamente presente (nel senso library[row][col] != null
         */
        for(int row=1;row<=4;row++) {
            for (int col=1;col<=3;col++) {
                if (library.getMatrix()[row][col] != null) {
                    Color centralCellColor = library.getMatrix()[row][col].getColor();
                    if ((library.getMatrix()[row + 1][col - 1]!=null) && library.getMatrix()[row + 1][col - 1].getColor().equals(centralCellColor)) {
                        if ((library.getMatrix()[row - 1][col - 1]!=null) && library.getMatrix()[row - 1][col - 1].getColor().equals(centralCellColor)) {
                            if ((library.getMatrix()[row + 1][col + 1]!=null) && library.getMatrix()[row + 1][col + 1].getColor().equals(centralCellColor)) {
                                if ((library.getMatrix()[row - 1][col + 1]!=null) && library.getMatrix()[row - 1][col + 1].getColor().equals(centralCellColor)) {


                                    //GridPane_Controller -> Ho commentato questo controllo perchè non serve e poi non considera celle null
                                    // Checking that the other cells inside the square but not in X have different color
                                    /*if (!(library.getMatrix()[row - 1][col].getColor().equals(centralCellColor)) &&
                                            !(library.getMatrix()[row + 1][col].getColor().equals(centralCellColor)) &&
                                            !(library.getMatrix()[row][col - 1].getColor().equals(centralCellColor)) &&
                                            !(library.getMatrix()[row][col + 1].getColor().equals(centralCellColor))) {
                                        return true;
                                    }*/
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
