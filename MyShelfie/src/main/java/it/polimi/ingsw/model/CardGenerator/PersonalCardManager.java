package it.polimi.ingsw.model.CardGenerator;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import it.polimi.ingsw.model.Cards.*;
import it.polimi.ingsw.model.Enums.*;
import it.polimi.ingsw.model.Utility.*;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class PersonalCardManager {
    private int numCards;
    private boolean[] usedCards;
    private final String filePath= "src/main/resources/json/PersonalCards.json";

    private JsonArray jsonArrayOfCards;

    public PersonalCardManager() throws IOException {
        jsonArrayOfCards=readCardsInFile();
        this.numCards=jsonArrayOfCards.size();
        this.usedCards= new boolean[this.numCards];
        for(int i=0; i<this.numCards;i++)
        {
            usedCards[i]=false;
        }
    }
    private JsonArray readCardsInFile() throws IOException {
        Gson gson = new Gson();
        Random rndNumberGenerator = new Random();
        FileReader fileReader = new FileReader(filePath);
        JsonObject jsonObject = gson.fromJson(fileReader, JsonObject.class);
        JsonArray cardsArray = jsonObject.getAsJsonArray("generalArray");
        fileReader.close();
        return cardsArray;
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
    public PersonalGoalCard draw(int personalGoalCard)
    {
        JsonObject json_Card;
        JsonArray json_colorArray, json_xCoordinate, json_yCoordinate;
        String description;
        ArrayList<Couple> couples;
        Couple <Position, Color> couple;
        if(isCardDrawable(personalGoalCard))
        {
            usedCards[personalGoalCard]=true;
            json_Card= jsonArrayOfCards.get(personalGoalCard).getAsJsonObject();
            json_colorArray = json_Card.getAsJsonArray("color");
            json_xCoordinate =  json_Card.getAsJsonArray("x");
            json_yCoordinate =  json_Card.getAsJsonArray("y");
            description=  json_Card.get("description").getAsString();
            couples = new ArrayList<>();
            for(int i=0; i<json_colorArray.size(); i++){
                couple = new Couple(
                        new Position(json_xCoordinate.get(i).getAsInt(), json_yCoordinate.get(i).getAsInt()),
                        Color.valueOf(json_colorArray.get(i).getAsString())
                );
                couples.add(couple);
            }
            return new PersonalGoalCard(description, couples);
        }
        return null;
    }
    public boolean isCardDrawable(int personalGoalCardId)
    {
        return !usedCards[personalGoalCardId];
    }
}
