package it.polimi.ingsw.model;
public class CommonGoalCard9 extends CommonGoalCard {
    @Override
    public boolean isSatisfied(Library lib) {
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
                String centralCellColor = library[row][col].getColor();
                if (library[row+1][col-1].getColor().equals(centralCellColor)) {
                    if (library[row-1][col-1].getColor().equals(centralCellColor)) {
                        if (library[row+1][col+1].getColor().equals(centralCellColor)) {
                            if (library[row-1][col+1].getColor().equals(centralCellColor)) {
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
