package it.polimi.ingsw.model.LivingRoom;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import it.polimi.ingsw.model.Cards.*;
import it.polimi.ingsw.model.Utility.*;
import it.polimi.ingsw.model.CardGenerator.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

//import java.util.Random;

/**
 * This class represents the grid of the living room.
 * In this grid will be positioned the object card that will be drawn by the players on the different turns.
 * In the grid will be managed all the logic that involves the draws and the refills.
 */
public class Grid {
    private final int numColumns = 9;
    private final int numRows = 9;
    private int numPlayers;
    private ObjectCard[][] matrix;
    private Set<Position> notAvailablePositions;
    private CardGenerator cardGenerator;
    private final String filePath= "src/main/resources/json/Grid.json";

    /**
     * The constructor of the grid. After the call of this constructor, the matrix will contain the right number of
     * object cards in the right position and the set notAvailablePosition will contain all the unavailable positions.
     * @param numPlayers the grid need the number of players that will play with it, in order to prepare the game field.
     * @throws IOException propagates to the caller the exception thrown by retrieveUnavailablePositionsSet.
     */
    public Grid(int numPlayers, CardGenerator cardGenerator) throws RuntimeException{
        this.cardGenerator=cardGenerator;
        this.numPlayers=numPlayers;
        this.matrix = new ObjectCard[numRows][numColumns];
        this.notAvailablePositions= retrieveUnavailablePositionsSet(); //lancia una eccezione IOException se non trova il file Grid.json
        refill();
    }

    /**
     * The method refill simply refills the grid with object cards.
     * It checks all the empty positions that are also available in the grid and creates an object card in those positions
     * In case the grid is not completely empty when the method is called, it will maintain the already present cards.
     */
    public void refill(){
        Position position = new Position(0,0);
        for(int r = 0; r< numRows; r++)
        {
            for(int c = 0; c< numColumns; c++)
            {
                position.setXY(r,c);
                if(matrix[r][c]==null && isAvailable(position))
                {
                    //genera la carta
                }
            }
        }
    }

    /**
     * This method check if the grid needs to be refilled
     * @return {@code true} if the player that will draw in this turn is forced to draw a single card.
     * This case realizes when the grid is completely empty or each of the remaining card are surrounded by empty spaces.
     */
    public boolean needRefill(){
        Position position = new Position(0,0);
        for(int r = 0; r< numRows; r++)
        {
            for(int c = 0; c< numColumns; c++)
            {
                position.setXY(r,c);
                if(matrix[r][c]!=null && hasNeighbours(position))
                {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Method that checks if a drawn is correctly formed and performable.
     * @param drawn is a vector of positions
     * @return {@code true} if the vector is not null, has the correct length, has not empty cells in it, is a straight
     * drawn, is formed by connected positions and finally if all the object cards have a free side before the drawn.
     */
    public boolean isDrawAvailable(Position[] drawn){
        int columns[]= new int[drawn.length];
        int rows[]= new int[drawn.length];
        int vector[];

        //Mossa non nulla ed interna
        if((drawn==null)||(drawn.length<=0)||(drawn.length>3))
        {
            return false;
        }
        //Mossa senza buchi
        for(int i=0; i< drawn.length;i++)
        {
            if((drawn[i]==null)||(matrix[drawn[i].getX()][drawn[i].getY()]==null))
            {
                return false;
            }
            columns[i]=drawn[i].getX();
            rows[i]=drawn[i].getY();
        }
        //Mossa allineata e contigua (tutte le mosse distanti 1)
        if(drawn.length>1)
        {
            if (hasSameInt(columns))
            {
                vector = columns;
            }
            else if (hasSameInt(rows))
            {
                vector = rows;
            }
            else
                return false;

            Arrays.sort(vector);
            for (int i = 1; i < vector.length; i++) {
                if (vector[i] != vector[i - 1] -1)
                    return false;
            }
        }
        //almeno un lato libero
        for(int i=1; i<drawn.length;i++)
        {
            if((drawn[i].getX()!=0)&&(drawn[i].getX()!=numColumns)&&(drawn[i].getY()!=0)&&(drawn[i].getY()!=numRows))
            {
                if((matrix[drawn[i].getX()+1][drawn[i].getY()]!=null)&&
                        (matrix[drawn[i].getX()-1][drawn[i].getY()]!=null)&&
                        (matrix[drawn[i].getX()][drawn[i].getY()+1]!=null)&&
                        (matrix[drawn[i].getX()][drawn[i].getY()-1]!=null))
                    return false;
            }
        }
        return true;
    }

    public ObjectCard[][] getMatrix(){
        return matrix;
    }
    public ObjectCard[] draw(Position[] move){
        return null;
    }

    /**
     * The method opens a Json.
     * Inside the file are stored some vectors containing some positions.
     * {@code Default_Invalid_Positions} contains all the position that are not utilizable by default.
     * {@code i_Player_New_Positions} contains all the the position utilizable when the {@code i} players are playing.
     * @return a set of position containing {@code Default_Invalid_Positions} and {@code i_Player_New_Positions}, when
     * {@code i} goes from numPlayers+1 to 4. If the file is missing this set is empty.
     */
    private Set<Position> retrieveUnavailablePositionsSet()
    {
        Set<Position> setOfPositions = new HashSet<>();
        Gson gson = new Gson();
        Reader reader = null;
        try {
            reader = new FileReader(filePath);
        } catch (FileNotFoundException e) {
            System.out.println("Non ho trovato alcun file per la configurazione della griglia");
            System.out.println("Dunque si giocherà su una griglia dritta di dimensione"+this.numColumns+"x"+numPlayers);
            return setOfPositions;
        }
        JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);

        JsonArray arrayOfJsonCells = jsonObject.getAsJsonArray("Default_Invalid_Positions");
        JsonArray jsonCell;

        for (JsonElement jsonCellElement : arrayOfJsonCells) {
            jsonCell = jsonCellElement.getAsJsonArray();
            setOfPositions.add(new Position(jsonCell.get(0).getAsInt(),jsonCell.get(1).getAsInt()));
        }

        for(int i=numPlayers; i<4; i++)
        {
            for (JsonElement jsonCellElement : arrayOfJsonCells) {
                arrayOfJsonCells = jsonObject.getAsJsonArray((i+1)+"_Player_New_Positions");
                jsonCell = jsonCellElement.getAsJsonArray();
                setOfPositions.add(new Position(jsonCell.get(0).getAsInt(),jsonCell.get(1).getAsInt()));
            }
        }
        try {
            reader.close();
        } catch (IOException e) {
            System.out.println("Non sono riuscito a chiudere il file "+filePath);
        }

        return setOfPositions;
    }

    /**
     * @param position is the position to be checked
     * @return {@code true} if the position is inside the numRows x numColumns matrix
     */
    private boolean isInside(Position position)
    {
        return position.getX()>=0 && position.getX()< numColumns &&
                position.getY()>=0 && position.getY()< numRows ;
    }

    /**
     * @param position is the position that will be checked
     * @return {@code true} if {@code position} is a not available position
     */
    private boolean isAvailable(Position position)
    {
        return !notAvailablePositions.contains(position);
    }

    /**
     * Check if {@code position} has cards near it.
     * @param position is the position to be checked
     * @return {@code true} if the position is in the matrix and around the {@code position} are there only empty spaces.
     * Note: The method ignores card positioned in diagonal, that's because they can't be picked in the same drawn.
     */
    private boolean hasNeighbours(Position position)
    {
        int x = position.getX();
        int y = position.getY();

        if(!isInside(position))
        {
            return false;
        }

        if((x>0 && matrix[x-1][y]!=null) ||
                (x< numColumns -1 && matrix[x+1][y]!=null) ||
                (y>0 && matrix[x][y-1]!=null) ||
                (y< numRows -1 && matrix[x][y+1]!=null)) {
            return false;
        }
        return true;
    }

    /**
     * Method created to check if a vector contains the same value in all of its cells.
     * @param vector Is the vector to check
     * @return {@code true} if all the cells of the vector contains the same value.
     */
    private boolean hasSameInt(int[] vector) {
        for(int i=1; i<vector.length;i++)
        {
            if(vector[i]!=vector[i-1])
                return false;
        }
        return true;
    }
}
