package it.polimi.ingsw.model.LivingRoom;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.exceptions.InvalidMoveException;
import it.polimi.ingsw.model.CardGenerator.CardGenerator;
import it.polimi.ingsw.model.Cards.ObjectCard;
import it.polimi.ingsw.utility.Position;

import java.io.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static it.polimi.ingsw.controller.GameController.ANSI_RESET;


/**
 * This class represents the grid of the living room.
 * In this grid will be positioned the object card that will be drawn by the players on the different turns.
 * In the grid will be managed all the logic that involves the draws and the refills.
 */
public class Grid implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private final int numColumns = 9;
    private final int numRows = 9;
    private final int numPlayers;
    private final ObjectCard[][] matrix;
    private final Set<Position> notAvailablePositions;
    private final CardGenerator cardGenerator;
    private final String filePath= "/json/Grid.json";

    /**
     * The constructor of the grid. After the call of this constructor, the matrix will contain the right number of
     * object cards in the right position and the set notAvailablePosition will contain all the unavailable positions.
     * @param numPlayers the grid need the number of players that will play with it, in order to prepare the game field.
     * @param cardGenerator the generator that the game uses to generate cards
     * @throws RuntimeException propagates to the caller the exception thrown by retrieveUnavailablePositionsSet.
     */
    public Grid(int numPlayers, CardGenerator cardGenerator) throws RuntimeException{
        this.cardGenerator=cardGenerator;
        this.numPlayers=numPlayers;
        this.matrix = new ObjectCard[numRows][numColumns];
        this.notAvailablePositions= retrieveUnavailablePositionsSet();
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
                position.setRowColumn(r,c);
                if(matrix[r][c]==null && isAvailable(position))
                {
                    matrix[r][c]=cardGenerator.generateObjectCard();
                }
            }
        }
    }

    /**
     * This method checks if the grid needs to be refilled
     * @return {@code true} if the player that will draw in this turn is forced to draw a single card.
     * This case realizes when the grid is completely empty or each of the remaining card are surrounded by empty spaces.
     */
    public boolean needRefill(){
        Position position = new Position(0,0);
        for(int r = 0; r< numRows; r++)
        {
            for(int c = 0; c< numColumns; c++)
            {
                position.setRowColumn(r,c);
                if(matrix[r][c]!=null && hasNeighbours(position))
                {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Method that checks if a draw is correctly formed and performable.
     * @param drawn is a vector of positions
     * @return {@code true} if the vector is not null, has the correct length, has not empty cells in it, is a straight
     * drawn, is formed by connected positions and finally if all the object cards have a free side before the drawn.
     */
    public void isDrawAvailable(Position[] drawn) throws InvalidMoveException{
        int[] columns;
        int[] rows;
        int[] vector;

        // drawn needs to be not null and with the right length
        if((drawn==null)||(drawn.length<=0)||(drawn.length>3))
        {
            throw new InvalidMoveException("Invalid number of card drawn");
        }

        columns= new int[drawn.length];
        rows= new int[drawn.length];
        //Drawn without blank positions and internal to the grid
        for(int i=0; i< drawn.length;i++)
        {
            if(drawn[i]==null)
            {
                throw new InvalidMoveException("Some position extracted was null");
            }
            else if((drawn[i].getRow()>=numRows)||(drawn[i].getColumn()>=numColumns) || (drawn[i].getRow()<0) || (drawn[i].getColumn()<0))
            {
                throw new InvalidMoveException("Some position extracted was not internal");
            }
            columns[i]=drawn[i].getRow();
            rows[i]=drawn[i].getColumn();
        }
        //Drawn needs to be inline and contiguous positions
        if(drawn.length>1)
        {
            if (hasSameInt(columns))
            {
                vector = rows;
            }
            else if (hasSameInt(rows))
            {
                vector = columns;
            }
            else
                throw new InvalidMoveException("Drawn do not extract contiguous cards");

            Arrays.sort(vector);
            for (int i = 1; i < vector.length; i++) {
                if (vector[i] != vector[i - 1] + 1)
                    throw new InvalidMoveException("Drawn do not extract contiguous cards");
            }
        }
        //Drawn must contain unavailable positions and should not try to draw null positions in the matrix
        for (Position position : drawn)
            if (!isAvailable(position) || matrix[position.getRow()][position.getColumn()]==null)
                throw new InvalidMoveException("Positions are not available");
        //Each card in the drawn need to have at least a free side
        for(int i=0; i<drawn.length;i++)
        {
            if((drawn[i].getRow()!=0)&&(drawn[i].getRow()!=numRows-1)&&
                    (drawn[i].getColumn()!=0)&&(drawn[i].getColumn()!=numColumns-1))
            {
                if((matrix[drawn[i].getRow()+1][drawn[i].getColumn()]!=null)&&
                        (matrix[drawn[i].getRow()-1][drawn[i].getColumn()]!=null)&&
                        (matrix[drawn[i].getRow()][drawn[i].getColumn()+1]!=null)&&
                        (matrix[drawn[i].getRow()][drawn[i].getColumn()-1]!=null))
                    throw new InvalidMoveException("Not all cards have a free side");
            }
        }
    }

    /**
     * Returns a representation of the grid in the form of a matrix
     * @return a matrix of ObjectCard representing the grid and its cards
     */
    public ObjectCard[][] getMatrix(){
        ObjectCard[][] answer= new ObjectCard[numRows][numColumns];
        for(int row=0; row<numRows;row++)
        {
            for(int col=0;col<numColumns;col++)
            {
                answer[row][col]=matrix[row][col];
            }
        }
        return answer;
    }

    /**This method remove cards from matrix and return an ObjectCard's array with the extracted cards
     * @param move {@code Position[]} Vector with position to remove
     * @return {@code ObjectCard[]} return removed cards
     * @author: Riccardo Figini
     */
    public ObjectCard[] draw(Position[] move) {
        if (move == null)
            return null;
        ObjectCard[] objectCards = new ObjectCard[move.length];
        for (int i = 0; i < move.length; i++) {
            objectCards[i] = matrix[move[i].getRow()][move[i].getColumn()];
            matrix[move[i].getRow()][move[i].getColumn()] = null;
        }
        return objectCards;
    }

    /**
     * The method opens a Json file identified by the class attribute {@code filePath}.
     * Inside the file are stored some vectors containing some positions.
     * {@code Default_Invalid_Positions} contains all the position that are not utilizable by default.
     * {@code i_Player_New_Positions} contains all the new positions utilizable when {@code i} players are playing.
     * @return a set of positions representing all the unavailable positions for the current number of players in the class.
     */
    private Set<Position> retrieveUnavailablePositionsSet()
    {
        Set<Position> setOfPositions = new HashSet<>();

        InputStream is = Grid.class.getResourceAsStream(filePath);
        if (is == null) {
            throw new RuntimeException("Problem reading file "+filePath);
        }

        Reader reader = new BufferedReader(new InputStreamReader(is));
        Gson gson = new Gson();

        JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);

        JsonArray arrayOfJsonCells = jsonObject.getAsJsonArray("Default_Invalid_Positions");
        JsonArray jsonCell;

        for (JsonElement jsonCellElement : arrayOfJsonCells) {
            jsonCell = jsonCellElement.getAsJsonArray();
            setOfPositions.add(new Position(jsonCell.get(0).getAsInt(),jsonCell.get(1).getAsInt()));
        }

        if(numPlayers==2){
            arrayOfJsonCells = jsonObject.getAsJsonArray("3_Player_New_Positions");
            for (JsonElement jsonCellElement : arrayOfJsonCells) {
                jsonCell = jsonCellElement.getAsJsonArray();
                setOfPositions.add(new Position(jsonCell.get(0).getAsInt(),jsonCell.get(1).getAsInt()));
            }
        }
        if(numPlayers==2 || numPlayers==3){
            arrayOfJsonCells = jsonObject.getAsJsonArray("4_Player_New_Positions");
            for (JsonElement jsonCellElement : arrayOfJsonCells) {
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
     * @return {@code true} if the position is in the matrix around the {@code position} are only empty ({@code null}) spaces.
     *  Note: The method ignores card positioned in diagonal, that's because they can't be picked in the same drawn.
     */
    private boolean hasNeighbours(Position position)
    {
        int row = position.getRow();
        int column = position.getColumn();

        return (
                    row > 0 && matrix[row - 1][column] != null)
                || (row < numColumns - 1 && matrix[row + 1][column] != null)
                || (column > 0 && matrix[row][column - 1] != null)
                || (column < numRows - 1 && matrix[row][column + 1] != null
        );
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
