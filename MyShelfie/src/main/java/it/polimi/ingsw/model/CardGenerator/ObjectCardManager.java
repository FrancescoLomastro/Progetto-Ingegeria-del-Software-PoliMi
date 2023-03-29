package it.polimi.ingsw.model.CardGenerator;

import it.polimi.ingsw.model.Cards.*;
import it.polimi.ingsw.model.Enums.*;

public class ObjectCardManager {
    private final int numCardsForTypes[] = {8,7,7};
    private final int numColors=6;
    private final int[][] deck;


    public ObjectCardManager()
    {
        deck= new int[numColors][numCardsForTypes.length];
        fillDeck();
    }
    private void fillDeck()
    {
        for(int i=0; i<deck.length;i++)
        {
            for(int j=0; j<numCardsForTypes.length;j++)
            {
                deck[i][j]=numCardsForTypes[j];
            }
        }
    }

    public boolean isCardDrawable(Color color, Type type)
    {
        return deck[color.getRelativeInt()][type.getRelativeInt()] > 0;
    }

    public ObjectCard draw(Color color, Type type)
    {
        if(isCardDrawable(color,type))
        {
            deck[color.getRelativeInt()][type.getRelativeInt()]--;
            return new ObjectCard("Object Card ["+color+"]",color, type);
        }
        return null;
    }
    public boolean isEmpty()
    {
        for(int i=0; i<deck.length;i++)
        {
            for(int j=0; j<numCardsForTypes.length; j++)
            {
                if (deck[i][j] != 0)
                    return false;
            }
        }
        return true;
    }
}
