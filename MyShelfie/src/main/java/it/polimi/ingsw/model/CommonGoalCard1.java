package it.polimi.ingsw.model;

import java.util.ArrayList;

public class CommonGoalCard1 extends CommonGoalCard {
    @Override
    public boolean isSatisfied(Library lib) {
        ArrayList<Position> arr = ArrayList<Position>(new Position(0,0),new Position(1,0),new Position(5,0),new Position(4,0));

        boolean ans = false;
        for(int i=0;i<arr.size();i++) {
            if (!ans) {
                if (i==0) {
                    boolean partial_ans = true;
                    String color = library[0][0].getColor();
                    for(int z=1;z<5;z++) {
                        if (!(library[z][z].getColor().equals(color))) {
                            partial_ans = false;
                            break;
                        }
                    }
                    ans = ans || partial_ans;
                } else if (i==1) {
                    boolean partial_ans = true;
                    String color = library[1][0].getColor();
                    for(int z=1;z<5;z++) {
                        if (!(library[1+z][z].getColor().equals(color))) {
                            break;
                        }
                    }
                    ans = ans || partial_ans;
                } else if (i==2) {
                    boolean partial_ans = true;
                    String color = library[1][0].getColor();
                    for(int z=1;z<5;z++) {
                        if (!(library[1+z][z].getColor().equals(color))) {
                            break;
                        }
                    }
                    ans = ans || partial_ans;
                }
            } else {
                break;
            }
        }
        return ans;
    }
}
