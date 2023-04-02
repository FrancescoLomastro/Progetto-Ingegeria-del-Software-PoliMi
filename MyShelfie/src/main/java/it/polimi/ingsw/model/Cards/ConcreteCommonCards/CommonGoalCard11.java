package it.polimi.ingsw.model.Cards.ConcreteCommonCards;
// sarebbe la mia classe fiveColumnsDescHigh

import it.polimi.ingsw.model.Cards.*;
import it.polimi.ingsw.model.Player.*;

/**
 * This class contains the algorithm to verify if the following common goal is satisfied.
 * The goal is: Five columns of increasing or decreasing height. Starting from the first column on
 *              the left or on the right, each next column must be made of exactly one more tile.
 *              Tiles can be of any type.
 * @author Alberto Aniballi
 */
public class CommonGoalCard11 extends CommonGoalCard {

    /*
    private int c, i, j;

    private Library library;

    private ObjectCard[][] lib;

    private int checkOneOrTwoResult;


    private int isOne = 1;

    private int isTwo = 1;
    */

    /**
     * It verifies if the library satisfies the goal of this specific common goal card
     *
     * @param library   the library on which we will verify if the goal is satisfied or not
     * @return: boolean that is true if the goal is satisfied, false otherwise
     * */
    @Override
    public boolean isSatisfied(Library library) {

        /*
        * Increasing from left to right
        */
        boolean answerIncreasing = true;
        int previousColumnFirstAvailableCell = 0;
        for(int col = 0; col<library.getNumberOfColumns(); col++) {
            int currentColumnFirstAvailableCell;
            if (col==0) {
                currentColumnFirstAvailableCell = (library.getNumberOfRows()) - library.findNumberOfFreeCells(col);
                previousColumnFirstAvailableCell = currentColumnFirstAvailableCell;
                continue;
            } else {
                currentColumnFirstAvailableCell = (library.getNumberOfRows()) - library.findNumberOfFreeCells(col);
                if (currentColumnFirstAvailableCell != (previousColumnFirstAvailableCell+1)) {
                    answerIncreasing = false;
                    break;
                } else {
                    previousColumnFirstAvailableCell = currentColumnFirstAvailableCell;
                }
            }
        }

        if (answerIncreasing) {
            return true;
        }

        /*
         * Decreasing from left to right
         */

        boolean answerDecreasing = true;
        previousColumnFirstAvailableCell = 0;
        for(int col = 0; col<library.getNumberOfColumns(); col++) {
            int currentColumnFirstAvailableCell;
            if (col==0) {
                currentColumnFirstAvailableCell = (library.getNumberOfRows()) - library.findNumberOfFreeCells(col);
                previousColumnFirstAvailableCell = currentColumnFirstAvailableCell;
                continue;
            } else {
                currentColumnFirstAvailableCell = (library.getNumberOfRows()) - library.findNumberOfFreeCells(col);
                if (currentColumnFirstAvailableCell != (previousColumnFirstAvailableCell-1)) {
                    answerDecreasing = false;
                    break;
                } else {
                    previousColumnFirstAvailableCell = currentColumnFirstAvailableCell;
                }
            }
        }

        if (answerDecreasing) {
            return true;
        } else {
            return false;
        }

        /*
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

         */
    }


    /**
     * this method idenyifies the two possible main cases: column from 1-5/5-1 or column from 2-6/6-2
     * it returns 1 if we are in the first case
     * it returns 2 if we are in the second case
     * it also returns 0 if it's impossible to get the goal, so the isSatisfied method can return false
     * @param lib the player's bookshelf
     * @return an int, that identifies the previous 2(3) cases
     */
    /*
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

    @Override
    public String getDescription() {
        return "Cinque colonne di altezza crescente o decrescente: a partire dalla prima colonna a sinistra o a destra, ogni colonna successiva deve essere formata da una tessera in più. Le tessere possono essere di qualsiasi tipo";
    }
     */
}
