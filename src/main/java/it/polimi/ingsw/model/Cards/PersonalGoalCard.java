package it.polimi.ingsw.model.Cards;

import it.polimi.ingsw.enums.Color;
import it.polimi.ingsw.model.Player.Library;
import it.polimi.ingsw.utility.Couple;
import it.polimi.ingsw.utility.Position;

import java.util.ArrayList;

/**
 * This class is the concrete "Personal goal card" class that represent cards which are separately given to each
 * specific player. Each card has an objective that a player can try to reach to gain points.
 *
 * @author Riccardo Figini
 * */
public class PersonalGoalCard extends Card {
    private final int cardId;
    private final ArrayList<Couple> goalVector;

    /**Constructor
     * @author Riccardo Figini
     * @param goalVector: vector containing objectives
     * @param cardId: the id of the card
     * */
    public PersonalGoalCard(ArrayList<Couple> goalVector, int cardId){
        super();
        this.goalVector=goalVector;
        this.cardId = cardId;
    }
    /**This method counts and returns point of personal goal card. It will be called at the end of the game.
     * @author Riccardo Figini
     * @author Francesco Lo Mastro
     * @author Alberto Aniballi
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
     * @author Riccardo Figini
     * @return {@code ArrayList<Couple>} it returns arraylist containing information about cells (position and color)
     * */
    public ArrayList<Couple> getGoalVector() {
        return goalVector;
    }

    /**Returns the ID of card
     * @author Francesco Lo Mastro
     * @return the id of the card
     */
    public int getCardId() {
        return cardId;
    }

    /**It returns the description, it's static because the original game does not have it
     * @author Francesco Lo Mastro
     * @return the description of the card
     */
    @Override
    public String getDescription() {
        return "Persona Goal Card";
    }
}