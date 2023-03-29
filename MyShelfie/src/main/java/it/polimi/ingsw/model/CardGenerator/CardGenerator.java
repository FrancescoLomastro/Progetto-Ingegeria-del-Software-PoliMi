package it.polimi.ingsw.model.CardGenerator;


import it.polimi.ingsw.model.Cards.*;
import it.polimi.ingsw.model.Enums.*;

import java.io.IOException;
import java.util.Random;

//TODO devo ancora testarla per vedere se riesce a prendere tutti i dati e a scriverli

//TODO IMPORTANTE: NEGLI OGGETTI DI RITORNO FAI SEMPRE UNA COPIA, NON PASSARE RIFERIMETNO

/**IDEA DEI FILE: ho a disposizione 3 file di base in cui sono salvate tutte le carte e i vettori
 * con i valori che devono avere inizialemente. Quando faccio partire la partita inizializzo questa
 * classe passandogli un numero identificativo. Allora questa classe crea 3 file in cui sono
 * presenti solo i 3 vettori che gestiscono le carte prelevate e ancora da prelevare
 * Per semplicità i vettori all'interno dei 3 file di base e dei tre file riferiti alla singola
 * partita hanno lo stesso nome*/
public class CardGenerator {

    private ObjectCardManager objectCardManager;
    private CommonCardManager commonCardManager;
    private PersonalCardManager personalCardManager;
    //private PersonalCardManager personalCardManager;

    public CardGenerator() throws IOException {
        objectCardManager= new ObjectCardManager();
        commonCardManager = new CommonCardManager();
        personalCardManager = new PersonalCardManager();
    }

    public ObjectCard generateObjectCard()
    {
        Random rndColor= new Random();
        Random rndType= new Random();
        int generatedColorCode;
        int generatedTypeCode;
        do {
            if(objectCardManager.isEmpty())
                return null;
            generatedColorCode=rndColor.nextInt(Color.numOfValues +1);
            generatedTypeCode=rndType.nextInt(Type.numOfValues);
        }while(!objectCardManager.isCardDrawable(Color.getEnumFromRelativeInt(generatedColorCode),Type.getEnumFromRelativeInt(generatedTypeCode)));
        return objectCardManager.draw(Color.getEnumFromRelativeInt(generatedColorCode),Type.getEnumFromRelativeInt(generatedTypeCode));
    }

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

    public PersonalGoalCard generatePersonalGoalCard()
    {
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
