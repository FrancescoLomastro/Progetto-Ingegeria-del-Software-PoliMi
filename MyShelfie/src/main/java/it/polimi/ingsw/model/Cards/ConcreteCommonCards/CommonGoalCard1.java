package it.polimi.ingsw.model.Cards.ConcreteCommonCards;

import it.polimi.ingsw.model.Cards.*;
import it.polimi.ingsw.model.Enums.*;
import it.polimi.ingsw.model.Player.*;

/**
 * This class contains the algorithm to verify if the second common goal algorithm is satisfied.
 * The goal is the following: Five tiles of the same type forming a diagonal.
 *
 * @author: Alberto Aniballi
 * */
public class CommonGoalCard1 extends CommonGoalCard {
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
                    if(library.getLibrary()[0][0]!=null) {
                            color = library.getLibrary()[0][0].getColor();
                        for(int diag=0;diag<5;diag++) {
                            if (!(library.getLibrary()[diag][diag].getColor().equals(color))) {
                                partial_ans = false;
                                break;
                            }
                        }
                        ans = ans || partial_ans;
                    }
                } else if (i==1) {
                    boolean partial_ans = true;
                    Color color;
                    if(library.getLibrary()[1][0]!=null) {
                        color = library.getLibrary()[1][0].getColor();
                        for (int diag = 0; diag < 5; diag++) {
                            if (!(library.getLibrary()[1 + diag][diag].getColor().equals(color))) {
                                break;
                            }
                        }
                        ans = ans || partial_ans;
                    }
                } else if (i==2) {
                    boolean partial_ans = true;
                    Color color;
                    if(library.getLibrary()[5][0]!=null) {
                        color = library.getLibrary()[5][0].getColor();
                        for (int diag = 0; diag < 5; diag++) {
                            if (!(library.getLibrary()[5 - diag][diag].getColor().equals(color))) {
                                break;
                            }
                        }
                        ans = ans || partial_ans;
                    }
                } else if (i==3) {
                    boolean partial_ans = true;
                    Color color;
                    if(library.getLibrary()[4][0]!=null) {
                        color = library.getLibrary()[4][0].getColor();
                        for (int diag = 0; diag < 5; diag++) {
                            if (!(library.getLibrary()[4 - diag][diag].getColor().equals(color))) {
                                break;
                            }
                        }
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
