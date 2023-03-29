package it.polimi.ingsw.model.CardGenerator;

import it.polimi.ingsw.model.Cards.*;
import it.polimi.ingsw.model.Cards.ConcreteCommonCards.*;
/**
 * This class is a CommonCard deck manager. It keeps tracks of the card to be generated and the already generated cards
 * @author: Francesco Lo Mastro
 */
public class CommonCardManager {
    private final int numCards;
    private final CustomizedFunction<CommonGoalCard>[] factoryMethodArray =
            new CustomizedFunction[]{CommonGoalCard0::new, CommonGoalCard1::new, CommonGoalCard2::new, CommonGoalCard3::new, CommonGoalCard4::new,
                    CommonGoalCard5::new, CommonGoalCard6::new, CommonGoalCard7::new, CommonGoalCard8::new, CommonGoalCard9::new,
                    CommonGoalCard10::new, CommonGoalCard11::new};
    private boolean usedCards[];

    /**
     * Constructor: creates an CommonCardManager with 0 already generated cards.
     * @author: Francesco Lo Mastro
     */
    public CommonCardManager()
    {
        numCards=factoryMethodArray.length;
        usedCards = new boolean[numCards];
        for(int i=0; i<numCards;i++)
        {
            usedCards[i]=false;
        }
    }

    /**
     * This method generates a CommonGoalCard from the remaining card to be generated, set as unavailable the already generated cards
     * @param commonGoalCardId is the implicit ID of a common card. {@code ID = 0} refers to the first card stored in the file
     * @return if the requested card can be generated, will return it, otherwise it returns null.
     * @author: Francesco Lo Mastro
     */
    public CommonGoalCard draw(int commonGoalCardId)
    {
        if(isCardDrawable(commonGoalCardId))
        {
            usedCards[commonGoalCardId]=true;
            return factoryMethodArray[commonGoalCardId].apply();
        }
        return null;
    }
    /**
     * This method checks if a CommonCard with {@code ID = personalGoalCardId} can be generated
     * @param commonGoalCardId is the implicit ID of a common card. {@code ID = 0} refers to the first card stored in the file
     * @return true if the card can be generated, means that the requested card with {@code ID = personalGoalCardId} is not already been picked
     * @author: Francesco Lo Mastro
     */
    public boolean isCardDrawable(int commonGoalCardId)
    {
        return !usedCards[commonGoalCardId];
    }

    /**
     * Check if the manager can't generate any other card.
     * @return true if the manager is empty.
     * @author: Francesco Lo Mastro
     */
    public boolean isEmpty()
    {
        for(int i = 0; i< usedCards.length; i++)
        {
            if (!usedCards[i])
                return false;
        }
        return true;
    }

    /**
     * @return the initial number of available cards
     * @author: Francesco Lo Mastro
     */
    public int getNumCards() {
        return numCards;
    }
}
