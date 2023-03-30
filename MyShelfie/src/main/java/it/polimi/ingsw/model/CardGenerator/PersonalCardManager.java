package it.polimi.ingsw.model.CardGenerator;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import it.polimi.ingsw.model.Cards.*;
import it.polimi.ingsw.model.Enums.*;
import it.polimi.ingsw.model.Utility.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
/**
 * This class is a PersonalCard deck manager.
 * All the PersonalCard models are taken from a JSON file.
 * It keeps tracks of the card to be generated and the already generated cards
 * @author: Francesco Lo Mastro
 */
public class PersonalCardManager {
    private int numCards;
    private boolean[] usedCards;
    private final String filePath= "src/main/resources/json/PersonalCards.json";
    private JsonArray jsonArrayOfCards;

    /**
     * Constructor: Get all the models stored in the class JSON file and creates a PersonalCardManager with 0 already generated cards.
     * NOTE: this method don't create all the PersonalCards it reads from the file, but prepare to manage the amount of card that are written in the file
     * @author: Francesco Lo Mastro
     */
    public PersonalCardManager(){
        jsonArrayOfCards=readCardsInFile();
        this.numCards=jsonArrayOfCards.size();
        this.usedCards= new boolean[this.numCards];
        for(int i=0; i<this.numCards;i++)
        {
            usedCards[i]=false;
        }
    }

    /**
     * This method reads the file in the path {@code filePath} and prepares to manage the number of card it read.
     * NOTE: In case the file is unreachable it will throw a RuntimeException
     * @author: Francesco Lo Mastro
     * @return a JsonArray representing all the personal cards that will be managed
     * @throws FileNotFoundException if the file is missing from the file path
     */
    private JsonArray readCardsInFile() throws RuntimeException{
        Gson gson = new Gson();
        Random rndNumberGenerator = new Random();
        FileReader fileReader;

        try {
            fileReader = new FileReader(filePath);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Errore apertura del file "+filePath);
        }

        JsonObject jsonObject = gson.fromJson(fileReader, JsonObject.class);
        JsonArray cardsArray = jsonObject.getAsJsonArray("generalArray");
        if(cardsArray==null)
            throw new RuntimeException("Non è stato trovato alcun elenco di carte in "+filePath);

        try {
            fileReader.close();
        } catch (IOException e) {
            throw new RuntimeException("Errore chiusura del file "+filePath);
        }

        return cardsArray;
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


    /**
     * This method generates a PersonalGoalCard from the remaining card to be generated, set as unavailable the already generated cards
     * @param personalGoalCardId is the implicit ID of a personal card. {@code ID = 0} refers to the first card stored in the file
     * @return if the requested card can be generated, will return it, otherwise it returns null.
     * @author: Francesco Lo Mastro
     */
    public PersonalGoalCard draw(int personalGoalCardId)
    {
        JsonObject json_Card;
        JsonArray json_colorArray, json_rowCoordinate, json_columnCoordinate;
        ArrayList<Couple> couples;
        Couple <Position, Color> couple;
        if(isCardDrawable(personalGoalCardId))
        {
            usedCards[personalGoalCardId]=true;
            json_Card= jsonArrayOfCards.get(personalGoalCardId).getAsJsonObject();
            json_colorArray = json_Card.getAsJsonArray("color");
            json_rowCoordinate =  json_Card.getAsJsonArray("row");
            json_columnCoordinate =  json_Card.getAsJsonArray("ycolumn");

            if(json_colorArray==null || json_rowCoordinate == null || json_columnCoordinate == null)
                throw new RuntimeException("Non sono stati trovati i parametri corretti per colore/matrice della carta "+personalGoalCardId+" in "+filePath);

            couples = new ArrayList<>();
            for(int i=0; i<json_colorArray.size(); i++){
                couple = new Couple(
                        new Position(json_rowCoordinate.get(i).getAsInt(), json_columnCoordinate.get(i).getAsInt()),
                        Color.valueOf(json_colorArray.get(i).getAsString())
                );
                couples.add(couple);
            }
            return new PersonalGoalCard(couples);
        }
        return null;
    }
    /**
     * This method checks if a PersonalCard with {@code ID = personalGoalCardId} can be generated
     * @param personalGoalCardId is the implicit ID of a personal card. {@code ID = 0} refers to the first card stored in the file
     * @return true if the card can be generated, means that the requested card with {@code ID = personalGoalCardId} is not already been picked
     * @author: Francesco Lo Mastro
     */
    public boolean isCardDrawable(int personalGoalCardId)
    {
        return !usedCards[personalGoalCardId];
    }
}
