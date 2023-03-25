package it.polimi.ingsw.model;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

//TODO devo ancora testarla per vedere se riesce a prendere tutti i dati e a scriverli

/**IDEA DEI FILE: ho a disposizione 3 file di base in cui sono salvate tutte le carte e i vettori
 * con i valori che devono avere inizialemente. Quando faccio partire la partita inizializzo questa
 * classe passandogli un numero identificativo. Allora questa classe crea 3 file in cui sono
 * presenti solo i 3 vettori che gestiscono le carte prelevate e ancora da prelevare
 * Per semplicità i vettori all'interno dei 3 file di base e dei tre file riferiti alla singola
 * partita hanno lo stesso nome*/
public class CardGenerator {
    private final String pathCommonGoal;
    private final String gamePathCommon;
    private final String pathPersonalGoal;
    private final String gamePathPersonal;
    private final String pathObjectCard;
    private final String gamePathObject;
    private final String generalArray;
    private final String controllerArray;

    private final CustomizedFunction<CommonGoalCard>[] factoryMethodArray =
            new CustomizedFunction[]{CommonGoalCard0::new, CommonGoalCard1::new, CommonGoalCard2::new, CommonGoalCard3::new, CommonGoalCard4::new,
                    CommonGoalCard5::new, CommonGoalCard6::new, CommonGoalCard7::new, CommonGoalCard8::new, CommonGoalCard9::new,
                    CommonGoalCard10::new, CommonGoalCard11::new};

    /**Constructor: create path adding gameNumber. Simultaneously could
     * exsist differnt game, so exsist differt file which could provide information about card */
    public CardGenerator(int gameNumber){
        //TODO i percorsi sono ancora da sistemare
        this.pathCommonGoal=System.getProperty("user.dir")+"/Resources/json/CommonCards.json";
        this.pathPersonalGoal=System.getProperty("user.dir")+"/Resources/json/ObjectCards.json";
        this.pathObjectCard=System.getProperty("user.dir")+"/Resources/json/PersonalCards.json";
        this.generalArray = "generalArray";
        this.controllerArray = "controllerArray";
        this.gamePathCommon=System.getProperty("user.dir")+"/Resources/json/CommonCards"+gameNumber+".json";
        this.gamePathObject=System.getProperty("user.dir")+"/Resources/json/ObjectCards"+gameNumber+".json";
        this.gamePathPersonal=System.getProperty("user.dir")+"/Resources/json/PersonalCards"+gameNumber+".json";
        initArray(pathCommonGoal, gamePathCommon);
        initArray(pathPersonalGoal, gamePathPersonal);
        initArray(pathObjectCard, gamePathObject);
    }
    /**Casual generation of Common goal card*/
    public CommonGoalCard generateCommonGoalCard() {
        int number;
        number=casualGenerationOfNumber(gamePathCommon);
        CommonGoalCard commonGoalCard = factoryMethodArray[number].apply();
        commonGoalCard.setDescription(getDescriptionFromFile(pathCommonGoal, number));
        return commonGoalCard;
    }
    /**Casual generation of personal goal card*/
    public PersonalGoalCard generatePersonalGoalCard() {
        int number;
        String descrption;
        number = casualGenerationOfNumber(gamePathPersonal);
        descrption = getDescriptionFromFile(pathPersonalGoal, number);
        ArrayList<Couple> couples = getCouplesFromFile(number);
        return new PersonalGoalCard(descrption, couples);
    }
    /**Casual generation of object card (for grid)*/
    public ObjectCard generateObjectCard() {
        int number;
        String description;
        number=casualGenerationOfNumberCounted(gamePathObject);
        JsonObject jsonObject = getJsonObjectInArray(pathObjectCard, number);
        description = getDescriptionFromFile(pathObjectCard, number);
        try {
            return new ObjectCard(description, Color.valueOf( jsonObject.get("Color").toString()),
                    Type.valueOf( jsonObject.get("Type").toString()));
        } catch (Exception e) {
            throw new RuntimeException("Error in enum",e);
        }
    }
    /**Casual generation of number with limit. This method and casualGenerationOfNumber are diffent because
     * in this case it's possible select a specific card numerous time */
    private int casualGenerationOfNumberCounted(String path)  {
        int number;
        Random random = new Random();
        Gson gson = new Gson();
        Reader reader;

        try {
            reader = new FileReader(path);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Error in opening json file" +
                    ", info path: " + path,e);
        }
        JsonObject obj = gson.fromJson(reader, JsonObject.class);
        JsonArray jsonArray = obj.getAsJsonArray(controllerArray);

        do{
            number = random.nextInt(jsonArray.size());
        }while( jsonArray.get(number).getAsInt() <= 0);

        JsonObject input = new JsonObject();
        input.addProperty("numero", jsonArray.get(number).getAsInt() -1 );
        jsonArray.set(number, input.get("numero"));

        FileWriter file;
        try {
            file = new FileWriter(path);
            file.write(gson.toJson(obj));
            file.flush();
            file.close();
        } catch (IOException e) {
            throw new RuntimeException("Error in effectively write in file json, info path: " +path,e);
        }

        return number;
    }
    /**Casual generation number. This number is associated with a card, so this method
     * provides to a casual generation of card usable and available */
    private int casualGenerationOfNumber(String path) {
        int number;
        Random random = new Random();
        Gson gson = new Gson();
        JsonObject jsonObject;

        try {
            jsonObject = gson.fromJson(new FileReader(path), JsonObject.class);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Error in opening json file" +
                    ", info path: " + path,e);
        }

        JsonArray jsonArray = jsonObject.getAsJsonArray(controllerArray);
        do{
            number = random.nextInt(jsonArray.size());
        }while( jsonArray.get(number).getAsBoolean());

        JsonObject input = new JsonObject();
        input.addProperty("this", true);
        jsonArray.set(number, input.get("this"));

        FileWriter file;
        try {
            file = new FileWriter(path);
            file.write(gson.toJson(jsonObject));
            file.flush();
            file.close();
        } catch (IOException e) {
            throw new RuntimeException("Error in effectively write in file json, info path: " +path,e);
        }

        return number;
    }
    /**Read Personal goal card from json file and write value in couple*/
    private ArrayList<Couple> getCouplesFromFile(int number){
        JsonObject obj4 = getJsonObjectInArray(pathPersonalGoal, number);
        JsonArray colorArray = obj4.getAsJsonArray("Color");
        JsonArray xCoordinate =  obj4.getAsJsonArray("x");
        JsonArray yCoordinate =  obj4.getAsJsonArray("y");
        ArrayList<Couple> couples = new ArrayList<>();
        for(int i=0; i<colorArray.size(); i++){
            Couple couple = new Couple(
                    new Position(xCoordinate.get(i).getAsInt(), yCoordinate.get(i).getAsInt()),
                    Color.valueOf(colorArray.get(i).getAsString())
            );
            couples.add(couple);
        }
        return couples;
    }

    /**SPIEGAZIONE: this method return jsonObject conteined in jsonarray.
     * jsonarray is a container of cards (Can be any card type)*/
    private JsonObject getJsonObjectInArray(String path, int number){
        Gson gson = new Gson();
        FileReader fileReader;
        try {
            fileReader = new FileReader(path);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found in getJsonObject (file with card information)" +
                    "Info path: " + path + ", info number: " + number,e);
        }
        JsonObject obj2 = gson.fromJson(fileReader, JsonObject.class);
        JsonArray obj3 = obj2.getAsJsonArray(generalArray);
        return obj3.get(number).getAsJsonObject();
    }

    /**return description of specific card*/
    private String getDescriptionFromFile(String path, int number) {
        Gson gson = new Gson();
        JsonObject jsonObject;
        Reader reader;
        try {
            reader = new FileReader(path);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Error in getting descrption from file, info path:"
                    + path + ", info number " + number, e);
        }
        jsonObject = gson.fromJson(reader, JsonObject.class);
        JsonArray jsonArray = jsonObject.getAsJsonArray(generalArray);
        jsonObject = jsonArray.get(number).getAsJsonObject();
        return jsonObject.get("descriprion").getAsString();
    }

    private void initArray(String from, String into) {
        Gson gson = new Gson();
        FileWriter writer0;
        Reader reader0;
        try{
            writer0 = new FileWriter(into);
            reader0 = new FileReader(from);
        } catch (IOException e){
            throw new RuntimeException(e);
        }
        JsonObject object0 = gson.fromJson(reader0, JsonObject.class);
        JsonArray jsonArray0 = object0.getAsJsonArray(controllerArray);
        try {
            writer0.write(gson.toJson(jsonArray0));
        }
        catch (IOException e){
            throw new RuntimeException("Error in getting descrption from file, info path:"
                    + from, e);
        }
    }
}
