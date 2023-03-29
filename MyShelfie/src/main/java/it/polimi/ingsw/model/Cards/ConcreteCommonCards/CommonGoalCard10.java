package it.polimi.ingsw.model.Cards.ConcreteCommonCards;
// sarebbe la mia classe eightCellsSameType

import it.polimi.ingsw.model.Cards.*;
import it.polimi.ingsw.model.Enums.*;
import it.polimi.ingsw.model.Player.*;

/**
 * class for the 11^th of 12 algorithms to check common goal cards
 * @author Andrea Ferrini
 */
public class CommonGoalCard10 extends CommonGoalCard {

    private int i, j;

    private int iterations = 0; // conto le celle controllate, così dopo 8 iterazioni inizio il controllo periodico per vedere se ho finito


    private int[] types; // lungo 6, per contare simultaneamente le occorrenze per ogni tipo

    /**
     * Constructor
     *
     * @param description description of common goal file (get from json file)
     * @param game        current game
     * @author Andrea Ferrini
     */


    /**
     * the main algorithm that checks this common goal
     * @param library the turn player's library
     * @return boolean : true if satisfied, false if not satisfied
     */
    @Override
    public boolean isSatisfied(Library library) {
        ObjectCard[][] lib = library.getLibrary();
        // istanzio e inizializzo l'array delle occorrenze
        types = new int[6];
        for(i = 0; i < 6; i++){
            types[i] = 0;
        }

        // associo ogni cella a un tipo
        // verde -> 0
        // blu -> 1
        // azzurro -> 2
        // bianco -> 3
        // rosa -> 4
        // giallo -> 5

        for(i = 0;i < library.getNumberOfRows(); i++){
            for(j = 0; j < library.getNumberOfColumns(); j++){

                if(lib[i][j].getColor() == Color.GREEN){

                    // verde
                    types[0]++;
                }else if(lib[i][j].getColor() == Color.BLUE){

                    //blu
                    types[1]++;
                }else if(lib[i][j].getColor() == Color.LIGHTBLUE){

                    //azzurro
                    types[2]++;
                }else if(lib[i][j].getColor() == Color.BEIGE){

                    //beige
                    types[3]++;
                }else if(lib[i][j].getColor() == Color.PINK){

                    //rosa
                    types[4]++;
                }else if(lib[i][j].getColor() == Color.YELLOW){

                    //giallo
                    types[5]++;
                }
            }

            iterations ++; // ad ogni cella controllata aumento questo contatore

            if(iterations > 7){

                if(checkFinishEightType(types)){
                    return true;
                }
            }
        }
        return  false;
    }

    @Override
    public String getDescription() {
        return "Cinque tessere dello stesso tipo che formano una X";
    }

    /**
     * controls if the check is finished, so i can avoid useless iterations
     * @param types an array of int, one cell for each type of object card
     * @return boolean: true if the check is finished, false if it's not finished
     */
    public boolean checkFinishEightType(int[] types){

        for(int i : types){
            if(i >= 8){
                return true;
            }
        }
        return false;
    }
}