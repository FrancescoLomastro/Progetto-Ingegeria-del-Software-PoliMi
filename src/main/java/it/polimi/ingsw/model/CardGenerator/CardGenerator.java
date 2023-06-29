package it.polimi.ingsw.model.CardGenerator;


import it.polimi.ingsw.enums.Color;
import it.polimi.ingsw.enums.Type;
import it.polimi.ingsw.model.Cards.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Random;

/**
 * The class CardGenerator is a complete manager for all game card in a single Game.
 * This class manages the generation of each type of card and avoid generating already generated cards.
 * CardGenerator has 3 sub-managers that are dedicated to each type of card
 * @author Francesco Lo Mastro
 */
public class CardGenerator implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private final ObjectCardManager objectCardManager;
    private final CommonCardManager commonCardManager;
    private final PersonalCardManager personalCardManager;




    /**
     * Constructor: instantiate the 3 sub-managers
     * @author Francesco Lo Mastro
     */
    public CardGenerator(int numOfPlayer){
        objectCardManager= new ObjectCardManager();
        commonCardManager = new CommonCardManager(numOfPlayer);
        personalCardManager = new PersonalCardManager();
    }




    /**
     * This method generates an ObjectCard keeping trace of the already generated Cards.
     * @return null if all card have been already generated, otherwise it generates a random new ObjectCard
     * @author Francesco Lo Mastro
     */
    public ObjectCard generateObjectCard()
    {
        Random rnd= new Random();
        int generatedColorCode;
        int generatedTypeCode;
        do {
            if(objectCardManager.isEmpty())
                return null;
            generatedColorCode=rnd.nextInt(objectCardManager.getNumColors());
            generatedTypeCode=rnd.nextInt(objectCardManager.getNumTypes());
        }while(!objectCardManager.isCardDrawable(Color.getEnumFromRelativeInt(generatedColorCode), Type.getEnumFromRelativeInt(generatedTypeCode)));
        return objectCardManager.draw(Color.getEnumFromRelativeInt(generatedColorCode),Type.getEnumFromRelativeInt(generatedTypeCode));
    }




    /**
     * This method generates a CommonGoalCard keeping trace of the already generated Cards.
     * @return null if all card have been already generated, otherwise it generates a random new CommonGoalCard
     * @author Francesco Lo Mastro
     */
    public CommonGoalCard generateCommonGoalCard()
    {
        Random rndNumber= new Random();
        int generatedCardCode;
        do {
            if(commonCardManager.isEmpty())
                return null;
            generatedCardCode=rndNumber.nextInt(commonCardManager.getNumCards());
        }while(!commonCardManager.isCardDrawable(generatedCardCode));
        return commonCardManager.draw(generatedCardCode);
    }




    /**
     * This method generates a PersonalGoalCard keeping trace of the already generated Cards.
     * @return null if all card have been already generated, otherwise it generates a random new PersonalGoalCard
     * @author Francesco Lo Mastro
     */
    public PersonalGoalCard generatePersonalGoalCard() {
        Random rndNumber= new Random();
        int generatedCardCode;
        do {
            if(personalCardManager.isEmpty())
                return null;
            generatedCardCode=rndNumber.nextInt(personalCardManager.getNumCards());
        }while(!personalCardManager.isCardDrawable(generatedCardCode));
        return personalCardManager.draw(generatedCardCode);
    }
}
