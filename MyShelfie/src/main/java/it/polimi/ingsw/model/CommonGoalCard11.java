package it.polimi.ingsw.model;
// sarebbe la mia classe fiveColumnsDescHigh

/**
 * class for the 12^th of 12 algorithms to check common goal cards
 * @author Andrea Ferrini
 */
public class CommonGoalCard11 extends CommonGoalCard {
    private int c, i, j;
    private Library library;
    private ObjectCard[][] lib;

    private int checkOneOrTwoResult;

    private int isOne = 1;
    private int isTwo = 1;


    /**
     * constructor of the class CommonGoalCard11
     * @param library the turn player's library
     */
    public CommonGoalCard11(Library library){
        this.library = library;

        this.lib = this.library.getLibrary();
    }

    /**
     * the main algorithm that checks this common goal
     * @param library the turn player's library
     * @return boolean : true if satisfied, false if not satisfied
     */
    @Override
    public boolean isSatisfied(Library library) {

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

            // caso da destra (2-6)
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


    /**
     * this method selects 3 different cases:
     * 1: I'll check the 1-5 or 5-1 columns
     * 2: I'll check the 2-6 or 6-2 columns
     * 3: I'll return "false"
     * @param lib the player's library
     * @return an int from 0 to 2, that identifies the 3 cases
     *
     */
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
