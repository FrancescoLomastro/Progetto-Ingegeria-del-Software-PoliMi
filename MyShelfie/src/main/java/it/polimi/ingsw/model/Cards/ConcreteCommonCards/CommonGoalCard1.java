package it.polimi.ingsw.model.Cards.ConcreteCommonCards;

import it.polimi.ingsw.model.Cards.CommonGoalCard;
import it.polimi.ingsw.model.Enums.Color;
import it.polimi.ingsw.model.Player.Library;

/**
 * This class contains the algorithm to verify if the following common goal is satisfied.
 * The goal is: Five tiles of the same type forming a diagonal.
 *
 * @author: Alberto Aniballi
 * */
public class CommonGoalCard1 extends CommonGoalCard {
    public CommonGoalCard1(){
        setDescription("Five tiles of the same type forming a " +
                "diagonal. ");
    }
    /**
     * It verifies if the library satisfies the goal of this specific common goal card
     *
     * @param library   the library on which we will verify if the goal is satisfied or not
     * @return: boolean that is true if the goal is satisfied, false otherwise
     * */
    @Override
    public boolean isSatisfied(Library library) {

        boolean ans = false;
        for(int i=0;i<4;i++) {
            if (!ans) {
                if (i==0) {
                    boolean partial_ans = true;
                    Color color;
                    if(library.getMatrix()[0][0]!=null) {
                            color = library.getMatrix()[0][0].getColor();
                        for(int diag=0;diag<5;diag++) {
                            if (!(library.getMatrix()[diag][diag]==null ||library.getMatrix()[diag][diag].getColor().equals(color))) {
                                partial_ans = false;
                                break;
                            }
                        }
                        ans = ans || partial_ans;
                    } else {
                        partial_ans = false;
                        ans = ans || partial_ans;
                    }
                } else if (i==1) {
                    boolean partial_ans = true;
                    Color color;
                    if(library.getMatrix()[1][0]!=null) {
                        color = library.getMatrix()[1][0].getColor();
                        for (int diag = 0; diag < 5; diag++) {
                            if (!(library.getMatrix()[1+diag][diag]==null || library.getMatrix()[1 + diag][diag].getColor().equals(color)) ) {
                                partial_ans = false;
                                break;
                            }
                        }
                        ans = ans || partial_ans;
                    } else {
                        partial_ans = false;
                        ans = ans || partial_ans;
                    }
                } else if (i==2) {
                    boolean partial_ans = true;
                    Color color;
                    if(library.getMatrix()[5][0]!=null) {
                        color = library.getMatrix()[5][0].getColor();
                        for (int diag = 0; diag < 5; diag++) {
                            if (!( library.getMatrix()[5-diag][diag]==null || library.getMatrix()[5 - diag][diag].getColor().equals(color)) ) {
                                partial_ans = false;
                                break;
                            }
                        }
                        ans = ans || partial_ans;
                    } else {
                        partial_ans = false;
                        ans = ans || partial_ans;
                    }
                } else if (i==3) {
                    boolean partial_ans = true;
                    Color color;
                    if(library.getMatrix()[4][0]!=null) {
                        color = library.getMatrix()[4][0].getColor();
                        for (int diag = 0; diag < 5; diag++) {
                            if (!(library.getMatrix()[4-diag][diag]==null || library.getMatrix()[4 - diag][diag].getColor().equals(color))  ) {
                                partial_ans = false;
                                break;
                            }
                        }
                        ans = ans || partial_ans;
                    } else {
                        partial_ans = false;
                        ans = ans || partial_ans;
                    }
                }
            } else {
                break;
            }
        }
        return ans;
    }
}
