package it.polimi.ingsw.model;

// AVVERTENZE: LEGGERE AVVERTENZE NELLA CLASSE fiveColumnsDescHigh

import it.polimi.ingsw.model.Library;
import it.polimi.ingsw.model.ObjectCard;
import it.polimi.ingsw.model.Player;

import java.util.ArrayList;

public class eightCellsSameType {

    private int i, j;

    private int iterations = 0; // conto le celle controllate, così dopo 8 iterazioni inizio il controllo periodico per vedere se ho finito
    private Library library;
    private ObjectCard[][] lib;

    private int[] types; // lungo 6, per contare simultaneamente le occorrenze per ogni tipo

    public eightCellsSameType(Library library){

        this.library = library;

        this.lib = library.getLibrary();
    }

    public boolean checkEightCellsSameType(){

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

    // serve per evitare di controllare ancora dopo aver già trovato 8 celle dello stesso tipo
    public boolean checkFinishEightType(int[] types){

        for(int i : types){
            if(i >= 8){
                return true;
            }
        }
        return false;
    }
}
