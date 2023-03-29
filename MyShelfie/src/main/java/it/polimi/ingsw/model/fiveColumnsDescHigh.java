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

    private int checkOneOrTwoResult;


    private int isOne = 1;
    private int isTwo = 1;


    public fiveColumnsDescHigh(Library library){

        this.library = library;

        this.lib = this.library.getLibrary();
    }

    public boolean checkFiveColumnsDescHigh() {

        //  BASTA

        checkOneOrTwoResult = checkOneOrTwo(lib);

        if(checkOneOrTwoResult == 1){

            // 1-5 o 5-1

            // caso da sinistra (5-1)
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

            // caso da destra (1-5)
            else if(!(lib[1][library.getNumberOfColumns() - 1]).equals(null)){


                for (i = 1; i < library.getNumberOfRows(); i++) {
                    for (j = 0; j < library.getNumberOfColumns(); j++) {

                        if (library.getNumberOfColumns() - 1 - j < i) {  // le posizioni in cui deve esserci una tessera

                            if (lib[i][j].equals(null)) {  // se non c'è una tessera ritorno false
                                return false;
                            }
                        }
                        else{ // le posizioni in cui non deve esserci una tessera

                            if (!(lib[i][j].equals(null))) {  // se c'è una tessera ritorno false
                                return false;
                            }
                        }
                    }
                }
            }
            else return false;

            return true;
        }
        else if(checkOneOrTwoResult == 2){

            // 2-6 o 6-2


            // caso da sinistra (6-2)
            if(!(lib[0][0].equals(null))){

                for (i = 0; i < library.getNumberOfRows(); i++) {
                    for (j = 0; j < library.getNumberOfColumns(); j++) {

                        if (j <= i) {  // le posizioni in cui deve esserci una tessera

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
            //else if

            // caso da destra (1-5)
            else if(!(lib[1][library.getNumberOfColumns() - 1]).equals(null)){


                for (i = 0; i < library.getNumberOfRows(); i++) {
                    for (j = 0; j < library.getNumberOfColumns(); j++) {

                        if (library.getNumberOfColumns() - 1 - j <= i) {  // le posizioni in cui deve esserci una tessera

                            if (lib[i][j].equals(null)) {  // se non c'è una tessera ritorno false
                                return false;
                            }
                        }
                        else{ // le posizioni in cui non deve esserci una tessera

                            if (!(lib[i][j].equals(null))) {  // se c'è una tessera ritorno false
                                return false;
                            }
                        }
                    }
                }
            }
            else return false;

            return true;
        }
        else return false;
    }




    // per vedere se è una serie 1-5 o 2-6
    // return 1: controllo se è 1-5 o 5-1
    // return 2: controllo se è 2-6 o 6-2
    // return 0: ritorno direttamente falso nel metodo principale
    public int checkOneOrTwo(ObjectCard[][] lib){


        for (c = 0; c < library.getNumberOfColumns(); c++) { // controllo la prima riga, così se non è vuota evito il controllo

            if (!(lib[0][c].equals(null))) { // se la prima riga non è vuota

                isOne = 0;
            }
            if(lib[library.getNumberOfRows() - 1][c].equals(null) || lib[library.getNumberOfRows() - 2][c].equals(null)){ // se le due righe più in basso non sono piene

                isTwo = 0;
            }
        }
        if(isOne == 1){

            return 1;
        }
        else if(isTwo == 1){

            return 2;
        }
        else return 0;
    }

}
