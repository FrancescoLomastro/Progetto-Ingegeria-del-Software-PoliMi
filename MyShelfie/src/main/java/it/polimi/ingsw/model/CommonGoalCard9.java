package it.polimi.ingsw.model;
/**
 * This class contains the algorithm to verify if the second common goal algorithm is satisfied.
 * The goal is the following: Five tiles of the same type forming an X.
 *
 * @author: Alberto Aniballi
 * */
public class CommonGoalCard9 extends CommonGoalCard {
    /**
     * It verifies if the library satisfies the goal of this specific common goal card
     *
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
        for(int row=1;row<=5;row++) {
            for (int col=1;col<=4;col++) {
                Color centralCellColor = library.getLibrary()[row][col].getColor();
                if (library.getLibrary()[row+1][col-1].getColor().equals(centralCellColor)) {
                    if (library.getLibrary()[row-1][col-1].getColor().equals(centralCellColor)) {
                        if (library.getLibrary()[row+1][col+1].getColor().equals(centralCellColor)) {
                            if (library.getLibrary()[row-1][col+1].getColor().equals(centralCellColor)) {
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
