package it.polimi.ingsw.model;

//controllo obiettivo comune delle cinque colonne di altezza decrescente


// AVVERTENZE: QUANDO SCRIVO lib[i][j] devo specificare che accedo al campo del tipo della tessera, roba da aggiungere quando avrò l'implementazione di ObjectCard

import it.polimi.ingsw.model.Library;
import it.polimi.ingsw.model.ObjectCard;
import it.polimi.ingsw.model.Player;

public class fiveColumnsDescHigh {
    private int c, i, j;
    private Library library;
    private ObjectCard[][] lib;


    public fiveColumnsDescHigh(Library library){

        this.library = library;

        this.lib = this.library.getLibrary();
    }

    public boolean checkFiveColumnsDescHigh() {

        for (c = 0; c < library.getNumberOfColumns(); c++) { // controllo la prima riga, così se non è vuota evito il controllo

            if (!(lib[0][c].equals(null))) {

                return false;
            }
        }

        // se è andato tutto bene proseguo il controllo
        // N.B.: d'ora in poi scorrerò a partire da i = 1, perché la riga 0(zero) l'ho appena controllata


        // caso da sinistra
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

}
