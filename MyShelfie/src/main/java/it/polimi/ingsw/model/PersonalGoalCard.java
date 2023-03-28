package it.polimi.ingsw.model;
import java.util.ArrayList;

public class PersonalGoalCard extends Card {
    private final ArrayList<Couple> goalVector;
    public PersonalGoalCard(String descrption, ArrayList<Couple> goalVector){
        super(descrption);
        this.goalVector=goalVector;
    }
    public int countPersonalGoalCardPoints(Library lib){
        int count=0;
        Position tmp;
        for(int i=0; i<Color.values().length; i++){
            tmp = (Position) goalVector.get(i).getFirst();
            if(lib.getLibrary()[tmp.getX()][tmp.getY()].getColor()==
                    goalVector.get(i).getSecond()){
                count++;
            }
        }
        switch (count){
            case 1:
                break;
            case 2:
                break;
            case 3: count=4;break;
            case 4: count=6;break;
            case 5: count=9;break;
            case 6: count=12;break;
            default: count=0;break;
        }
        return count;
    }

    public ArrayList<Couple> getGoalVector() {

        return goalVector;
    }
}