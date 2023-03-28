package it.polimi.ingsw.model;

public class CommonCardManager {
    private final int numCards;
    private final CustomizedFunction<CommonGoalCard>[] factoryMethodArray =
            new CustomizedFunction[]{CommonGoalCard0::new, CommonGoalCard1::new, CommonGoalCard2::new, CommonGoalCard3::new, CommonGoalCard4::new,
                    CommonGoalCard5::new, CommonGoalCard6::new, CommonGoalCard7::new, CommonGoalCard8::new, CommonGoalCard9::new,
                    CommonGoalCard10::new, CommonGoalCard11::new};
    private boolean usedCards[];
    public CommonCardManager()
    {
        numCards=factoryMethodArray.length;
        usedCards = new boolean[numCards];
        for(int i=0; i<numCards;i++)
        {
            usedCards[i]=false;
        }
    }

    public CommonGoalCard draw(int commonGoalCardId)
    {
        if(isCardDrawable(commonGoalCardId))
        {
            usedCards[commonGoalCardId]=true;
            return factoryMethodArray[commonGoalCardId].apply();
        }
        return null;
    }
    public boolean isCardDrawable(int commonGoalCardId)
    {
        return !usedCards[commonGoalCardId];
    }
    public boolean isEmpty()
    {
        for(int i = 0; i< usedCards.length; i++)
        {
            if (!usedCards[i])
                return false;
        }
        return true;
    }

    public int getNumCards() {
        return numCards;
    }
}
