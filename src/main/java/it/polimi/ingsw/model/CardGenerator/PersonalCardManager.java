package it.polimi.ingsw.model.CardGenerator;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import it.polimi.ingsw.utility.Couple;
import it.polimi.ingsw.utility.Position;
import it.polimi.ingsw.enums.Color;
import it.polimi.ingsw.model.Cards.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * This class is a PersonalCard deck manager.
 * All the PersonalCard models are taken from a JSON file.
 * It keeps tracks of the card to be generated and the already generated cards
 * @author: Francesco Lo Mastro
 */
public class PersonalCardManager implements Serializable {
    private static final long serialVersionUID = 1L;
    private int numCards;
    private boolean[] usedCards;
    private final String filePath= "src/main/resources/json/PersonalCards.json";
    private transient JsonArray jsonArrayOfCards;




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
     * This method reads the file in the class attribute {@code filePath} and prepares to manage the number of card it read.
     * NOTE: In case the file is unreachable it will throw a RuntimeException
     * @return a JsonArray representing all the personal cards that will be managed
     * @throws RuntimeException if the file is missing from the file path
     * @author: Francesco Lo Mastro
     */
    private JsonArray readCardsInFile() throws RuntimeException{
        Gson gson = new Gson();
        FileReader fileReader;

        try {
            fileReader = new FileReader(filePath);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Error in opening "+filePath);
        }

        JsonObject jsonObject = gson.fromJson(fileReader, JsonObject.class);
        JsonArray cardsArray = jsonObject.getAsJsonArray("generalArray");
        if(cardsArray==null)
            throw new RuntimeException("No any list of card found in file "+filePath);

        try {
            fileReader.close();
        } catch (IOException e) {
            throw new RuntimeException("Error while closing file "+filePath);
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
     * @return the initial number of available cards got from file
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
            json_columnCoordinate =  json_Card.getAsJsonArray("column");

            if(json_colorArray==null || json_rowCoordinate == null || json_columnCoordinate == null)
                throw new RuntimeException("The correct parameters of color/matrix for personal card "+personalGoalCardId+" were not found in "+filePath);

            couples = new ArrayList<>();
            for(int i=0; i<json_colorArray.size(); i++){
                couple = new Couple(
                        new Position(json_rowCoordinate.get(i).getAsInt(), json_columnCoordinate.get(i).getAsInt()),
                        Color.valueOf(json_colorArray.get(i).getAsString())
                );
                couples.add(couple);
            }
            return new PersonalGoalCard(couples, personalGoalCardId+1);
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
