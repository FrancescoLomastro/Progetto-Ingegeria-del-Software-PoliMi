package it.polimi.ingsw.model.CardGenerator;

import it.polimi.ingsw.enums.Color;
import it.polimi.ingsw.enums.Type;
import it.polimi.ingsw.model.Cards.*;

import java.io.Serializable;


/**
 * This class is an ObjectCard deck manager. It keeps tracks of the card to be generated and the already generated cards
 * @author: Francesco Lo Mastro
 */
public class ObjectCardManager implements Serializable {
    private static final long serialVersionUID = 1L;
    private final int numCardsForTypes[] = {8,7,7};
    private final int numColors=6;
    private final int[][] deck;




    /**
     * @return the number of types that cards can have in the manager
     * @author: Francesco Lo Mastro
     */
    public int getNumTypes()
    {
        return numCardsForTypes.length;
    }




    /**
     * @return the number of colors that cards can have in the manager
     * @author: Francesco Lo Mastro
     */
    public int getNumColors()
    {
        return numColors;
    }




    /**
     * Constructor: creates an ObjectCardManager and build a deck of ObjectCards
     * @author: Francesco Lo Mastro
     */
    public ObjectCardManager()
    {
        deck= new int[numColors][numCardsForTypes.length];
        fillDeck();
    }




    /**
     * This method set the deck of ObjectCard to the construction state.
     * @author: Francesco Lo Mastro
     */
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





    /**
     * This method checks if a ObjectCard with {@code color} and {@code type} can be generated
     * @param color the color that the card will have
     * @param type the type that the card will have
     * @return true if the card can be generated, means that the requested type of card is not out of stock yet.
     * @author: Francesco Lo Mastro
     */
    public boolean isCardDrawable(Color color, Type type)
    {
        return deck[color.getRelativeInt()][type.getRelativeInt()] > 0;
    }




    /**
     * This method generates an ObjectCard from the remaining card to be generated and reduce the number of same cards which can be generated
     * @param color the color that the card will have
     * @param type the type that the card will have
     * @return if the requested card can be generated, will return it, otherwise it returns null.
     * @author: Francesco Lo Mastro
     */
    public ObjectCard draw(Color color, Type type)
    {
        if(isCardDrawable(color,type))
        {
            deck[color.getRelativeInt()][type.getRelativeInt()]--;
            return new ObjectCard("Object Card ["+color+"]",color, type);
        }
        return null;
    }




    /**
     * Check if the manager can't generate any other card.
     * @return true if the manager is empty.
     * @author: Francesco Lo Mastro
     */
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

