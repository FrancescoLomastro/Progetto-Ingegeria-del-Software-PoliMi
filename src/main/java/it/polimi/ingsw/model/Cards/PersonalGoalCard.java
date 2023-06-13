package it.polimi.ingsw.model.Cards;

import it.polimi.ingsw.enums.Color;
import it.polimi.ingsw.model.Player.Library;
import it.polimi.ingsw.utility.Couple;
import it.polimi.ingsw.utility.Position;

import java.util.ArrayList;


public class PersonalGoalCard extends Card {
    private final int cardId;
    private final ArrayList<Couple> goalVector;
    public PersonalGoalCard(ArrayList<Couple> goalVector, int cardId){
        super();
        this.goalVector=goalVector;
        this.cardId = cardId;
    }
    /**This method counts and returns point of personal goal card. It will be called at the end of the game.
     * @author: Riccardo Figini
     * @param lib player's library, which will be tested
     * @return {@code int} count score point*/
    public int countPersonalGoalCardPoints(Library lib){
        int count=0;
        Position tmp;
        Color tmpColor;
        for(int i = 0; i< Color.values().length-1; i++){
            tmp = (Position) goalVector.get(i).getFirst();
            tmpColor = (Color) goalVector.get(i).getSecond();
            if(
                    lib.getMatrix()[tmp.getRow()][tmp.getColumn()]!=null &&
                    lib.getMatrix()[tmp.getRow()][tmp.getColumn()].getColor()== tmpColor
            ){
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
    /**Return an arraylist representing the goalVector of the card.
     * @author: Riccardo Figini
     * @return {@code ArrayList<Couple>} it returns arraylist containing information about cells (position and color)
     * */
    public ArrayList<Couple> getGoalVector() {

        return goalVector;
    }

    public int getCardId() {
        return cardId;
    }

    @Override
    public String getDescription() {
        return "Persona Goal Card";
    }
}