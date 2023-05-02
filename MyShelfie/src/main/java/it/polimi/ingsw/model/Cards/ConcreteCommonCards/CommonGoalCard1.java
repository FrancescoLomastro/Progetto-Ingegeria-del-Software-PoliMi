package it.polimi.ingsw.model.Cards.ConcreteCommonCards;

import it.polimi.ingsw.model.Cards.CommonGoalCard;
import it.polimi.ingsw.model.Cards.ObjectCard;
import it.polimi.ingsw.model.Enums.Color;
import it.polimi.ingsw.model.Player.Library;

public class CommonGoalCard1 extends CommonGoalCard {

    ObjectCard[][] matrix;
    private boolean result = false;
    private int i, j, k;
    private  int counter = 1;

    public CommonGoalCard1(){
        setDescription("Five tiles of the same type forming a " +
                "diagonal. ");
    }

    @Override
    public boolean isSatisfied(Library lib) {

        matrix = lib.getMatrix();

        for(i = 0; i < 4; i++){

            Color thisColor;

            switch (i){

                // da 0,0 a 4,4
                case 0: {

                    if(matrix[0][0] != null) thisColor = matrix[0][0].getColor();
                    else break;

                    if(matrix[1][1] != null && matrix[2][2] != null && matrix[3][3] != null &&matrix[4][4] != null){

                        if((matrix[1][1].getColor().equals(thisColor) && matrix[2][2].getColor().equals(thisColor) && matrix[3][3].getColor().equals(thisColor) && matrix[4][4].getColor().equals(thisColor)))
                            return true;
                    }
                }
                /* da 1,0 a 5,4*/
                case 1: {

                    if(matrix[1][0] != null) thisColor = matrix[1][0].getColor();
                    else break;

                    if(matrix[2][1] != null && matrix[3][2] != null && matrix[4][3] != null && matrix[5][4] != null){

                        if((matrix[2][1].getColor().equals(thisColor) && matrix[3][2].getColor().equals(thisColor) && matrix[4][3].getColor().equals(thisColor) && matrix[5][4].getColor().equals(thisColor)))
                            return true;
                    }
                }
                // da 0,4 a 4,0
                case 2: {

                    if(matrix[0][4] != null) thisColor = matrix[0][4].getColor();
                    else break;

                    if(matrix[1][3] != null && matrix[2][2] != null && matrix[3][1] != null &&matrix[4][0] != null){

                        if((matrix[1][3].getColor().equals(thisColor) && matrix[2][2].getColor().equals(thisColor) && matrix[3][1].getColor().equals(thisColor) && matrix[4][0].getColor().equals(thisColor)))
                            return true;
                    }
                }
                // da 1,4 a 5,0
                case 3: {

                    if(matrix[1][4] != null) thisColor = matrix[1][4].getColor();
                    else break;

                    if(matrix[2][3] != null && matrix[3][2]!= null && matrix[4][1] != null && matrix[5][0] != null){

                        if((matrix[2][3].getColor().equals(thisColor) && matrix[3][2].getColor().equals(thisColor) && matrix[4][1].getColor().equals(thisColor) && matrix[5][0].getColor().equals(thisColor)))
                            return true;
                    }
                }
            }
        }

        return false;
    }
}
