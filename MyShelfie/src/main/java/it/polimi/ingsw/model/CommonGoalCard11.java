package it.polimi.ingsw.model;
// sarebbe la mia classe fiveColumnsDescHigh

/**
 * class for the 12^th of 12 algorithms to check common goal cards
 * @author Andrea Ferrini
 */
public class CommonGoalCard11 extends CommonGoalCard {

    /**
     * constructor of the class CommonGoalCard11
     */
    public CommonGoalCard11(){
        super();
    }

    /**
     * the main algorithm that checks this common goal
     * @param library the turn player's library
     * @return boolean : true if satisfied, false if not satisfied
     */
    @Override
    public boolean isSatisfied(Library library) {
        ObjectCard[][] lib = library.getLibrary();
        int c;
        for (c = 0; c < library.getNumberOfColumns(); c++) { // controllo la prima riga, così se non è vuota evito il controllo

            if (!(lib[0][c].equals(null))) {

                return false;
            }
        }

        // se è andato tutto bene proseguo il controllo
        // N.B.: d'ora in poi scorrerò a partire da i = 1, perché la riga 0(zero) l'ho appena controllata


        // caso da sinistra
        int i;
        int j;
        if(!(lib[1][0].equals(null))){

            for (i = 1; i < library.getNumberOfRows(); i++) {
                for (j = 0; j < library.getNumberOfColumns(); j++) {

                    if (j < i) {  // le posizioni in cui deve esserci una tessera

                        if (lib[i][j].equals(null)) { // se non c'è una tessera ritorno false
                            return false;
                        }
                    } else { // le posizioni in cui non deve esserci una tessera

                        if (!(lib[i][j].equals(null))) {  // se c'è una tessera ritorno false
                            return false;
                        }
                    }
                }
            }
        }

        // caso da destra
        else if(!(lib[1][library.getNumberOfColumns() - 1]).equals(null)){


            for (i = 1; i < library.getNumberOfRows(); i++) {
                for (j = library.getNumberOfColumns() - 1; j >= 0; j--) {

                    for (j = 0; j < library.getNumberOfColumns(); j++) {

                        if (library.getNumberOfColumns() - 1 - j < i) {  // le posizioni in cui deve esserci una tessera

                            if (lib[i][j].equals(null)) {  // se non c'è una tessera ritorno false
                                return false;
                            }
                        } else { // le posizioni in cui non deve esserci una tessera

                            if (!(lib[i][j].equals(null))) {  // se c'è una tessera ritorno false
                                return false;
                            }
                        }
                    }

                }
            }
        }
        else return false;

        return true;
    }

    @Override
    public String getDescription() {
        return "Cinque colonne di altezza crescente o decrescente: a partire dalla prima colonna a sinistra o a destra, ogni colonna successiva deve essere formata da una tessera in più. Le tessere possono essere di qualsiasi tipo";
    }
}
