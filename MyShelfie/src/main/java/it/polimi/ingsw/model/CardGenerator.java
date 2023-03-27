package it.polimi.ingsw.model;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

//TODO devo ancora testarla per vedere se riesce a prendere tutti i dati e a scriverli

//TODO IMPORTANTE: NEGLI OGGETTI DI RITORNO FAI SEMPRE UNA COPIA, NON PASSARE RIFERIMETNO

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

    /**Constructor: create path, adding gameNumber in those associated with specific game.
     * Simultaneously can exist different game, so exist different file which can provide
     * information about card
     * @author Riccardo Figini
     * @param gameNumber ID number of game*/
    public CardGenerator(int gameNumber){
        this.pathCommonGoal=System.getProperty("user.dir")+"/risorse/CommonCards.json";
        this.pathPersonalGoal=System.getProperty("user.dir")+"/risorse/PersonalCards.json";
        this.pathObjectCard=System.getProperty("user.dir")+"/risorse/ObjectCards.json";
        this.generalArray = "generalArray";
        this.controllerArray = "controllerArray";
        this.gamePathCommon=System.getProperty("user.dir")+"/risorse/CommonCards"+gameNumber+".json";
        this.gamePathObject=System.getProperty("user.dir")+"/risorse/ObjectCards"+gameNumber+".json";
        this.gamePathPersonal=System.getProperty("user.dir")+"/risorse/PersonalCards"+gameNumber+".json";
        initArray(pathCommonGoal, gamePathCommon);
        initArray(pathPersonalGoal, gamePathPersonal);
        initArray(pathObjectCard, gamePathObject);
    }
    /**Casual generation of Common goal card. Attribute number is associated with free
     * commond goal card, that it's not already in the game. Allocate with factoryMethod
     * and return the CommondGoalCard chosen.
     * @author Riccardo Figini
     * @return commonGoalCard*/
    public CommonGoalCard generateCommonGoalCard() {
        int number;
        number=casualGenerationOfNumber(gamePathCommon);
        CommonGoalCard commonGoalCard = factoryMethodArray[number].apply();
        commonGoalCard.setDescription(getDescriptionFromFile(pathCommonGoal, number));
        return commonGoalCard;
    }
    /**Casual generation of personal goal card. Attribute number is associated with
     * free personal goal card (like generateCommonGoalCard). Get description
     * and couple from PersonalGoalCards.json. Return personalGoalCard
     * @author Riccardo Figini
     * @return PersonalGoalCard*/
    public PersonalGoalCard generatePersonalGoalCard() {
        int number;
        String descrption;
        number = casualGenerationOfNumber(gamePathPersonal);
        descrption = getDescriptionFromFile(pathPersonalGoal, number);
        ArrayList<Couple> couples = getCouplesFromFile(number);
        return new PersonalGoalCard(descrption, couples);
    }
    /**Casual generation of object card, in "actual game exsist 132 card, 22 for color.
     * Every color have 3 different type (8 cards type 1, 7 cards type 2, 7 cards type 3).
     * return object card
     * @uthor Riccardo Figini
     * @return ObjectCard
     * @exception RuntimeException*/
    public ObjectCard generateObjectCard() {
        int number;
        String description;
        number=casualGenerationOfNumberCounted(gamePathObject);
        JsonObject jsonObject = getJsonObjectInArray(pathObjectCard, number);
        description = getDescriptionFromFile(pathObjectCard, number);
        try {
            String color = jsonObject.get("Color").getAsString();
            String type = jsonObject.get("Type").getAsString();
            return new ObjectCard(description, Color.valueOf(color) , Type.valueOf(type));
        } catch (Exception e) {
            throw new RuntimeException("Error in enum",e);
        }
    }
    /**Casual generation of number with limit. This method read a jsonArray from json game file
     *  and generate available number (number isn't available if in the position "number" of jsonarray
     *  is 0)
     *  @author Riccardo Figini
     *  @param path string contained path of game's file
     *  @return number integer
     *  @exception RuntimeException*/
    private int casualGenerationOfNumberCounted(String path)  {
        int number;
        Random random = new Random();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Reader reader;

        try {
            reader = new FileReader(path);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Error in opening json file" +
                    ", info path: " + path,e);
        }

        JsonArray jsonArray = gson.fromJson(reader, JsonArray.class);

        do{
            number = random.nextInt(jsonArray.size());
        }while( jsonArray.get(number).getAsInt() <= 0);

        JsonObject input = new JsonObject();
        input.addProperty("numero", jsonArray.get(number).getAsInt() -1 );
        jsonArray.set(number, input.get("numero"));

        FileWriter file;
        try {
            file = new FileWriter(path);
            file.write(gson.toJson(jsonArray));
            file.flush();
            file.close();
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException("Error in effectively write in file json, " +
                    "info path: " +path,e);
        }
        return number;
    }
    /**Casual generation number. This number is associated with a card, so this method
     * provides to a casual generation of card usable and available
     * @author Riccardo Figini
     * @param path string contained path of game's file
     * @return number integer
     * @exception RuntimeException*/
    private int casualGenerationOfNumber(String path) {
        int number;
        Random random = new Random();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Reader reader;
        JsonArray jsonArray;
        try {
            reader = new FileReader(path);
            jsonArray= gson.fromJson(reader, JsonArray.class);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Error in opening json file" +
                    ", info path: " + path,e);
        }

        do{
            number = random.nextInt(jsonArray.size());
        }while( jsonArray.get(number).getAsBoolean());

        JsonObject input = new JsonObject();
        input.addProperty("this", true);
        jsonArray.set(number, input.get("this"));

        FileWriter file;
        try {
            file = new FileWriter(path);
            file.write(gson.toJson(jsonArray));
            file.flush();
            file.close();
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException("Error in effectively write in file json, info path: " +path,e);
        }
        return number;
    }
    /**Read Personal goal card from json file and write value in couple
     * @author Riccardo Figini
     * @param number integer associated with card
     * @return couples
     * @exception RuntimeException*/
    private ArrayList<Couple> getCouplesFromFile(int number){
        JsonObject obj4 = getJsonObjectInArray(pathPersonalGoal, number);
        JsonArray colorArray = obj4.getAsJsonArray("color");
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

    /**This method return jsonObject conteined in jsonarray.
     * jsonarray is a container of cards (Can be any card type)
     * @author Riccardo Figini
     * @param path path of json file with information
     * @param number integer used to get card's object from file
     * @throws  RuntimeException*/
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
        try{
            fileReader.close();
        }catch (IOException e){
            throw new RuntimeException("Error in closing", e);
        }
        return obj3.get(number).getAsJsonObject();
    }

    /**return description of specific card
     * @author Riccardo figini
     * @param path path of json file with information
     * @param number integer used to get card's description from file
     * @exception RuntimeException*/
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
        try {
            reader.close();
        }
        catch (IOException e){
            throw new RuntimeException("Error in closing", e);

        }
        return jsonObject.get("description").getAsString();
    }
    /**Create game file with initial array.
     * This method reads controllerArray from a file and writes it into another.
     * @author Riccardo Figini
     * @param from file's path which contain initial controllerArray
     * @param into destination file which will contain controllerArray
     * @exception RuntimeException*/
    private void initArray(String from, String into) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        FileWriter writer0;
        Reader reader0;
        try{
            writer0 = new FileWriter(into);
            reader0 = new FileReader(from);
        } catch (IOException e){
            throw new RuntimeException(e);
        }
        JsonObject object0 = gson.fromJson(reader0, JsonObject.class);
        JsonArray jsonArray = object0.getAsJsonArray(controllerArray);
        try {
            writer0.write(gson.toJson(jsonArray));
        }
        catch (IOException e){
            throw new RuntimeException("Error in getting descrption from file, info path:"
                    + from, e);
        }
        try {
            writer0.close();
            reader0.close();
        }
        catch (IOException e){
            throw new RuntimeException("Error in closing", e);
        }
    }
    /**Delete game file
     * @author Riccardo Figini
     * */
    public void deleteFileGame(){
        if(!(deleteFile(gamePathPersonal) && deleteFile(gamePathCommon) && deleteFile(gamePathObject)))
            System.err.println("Delete doesn't work");
    }
    /**Delete specific file and return if it works
     * @param path
     * @return boolean */
    private boolean deleteFile(String path){
        File file = new File(path);
        return file.delete();
    }
}
