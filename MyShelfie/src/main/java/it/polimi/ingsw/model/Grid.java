package it.polimi.ingsw.model;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

//import java.util.Random;

public class Grid {

    private final int numColumns =9;
    private final int numRows =9;
    private int numPlayers;
    //private int[] objectCardVector; //3*6
    private ObjectCard[][] matrix;
    private Set<Position> notAvailablePositions;

    public Grid(int numPlayers) throws IOException{
        this.numPlayers=numPlayers;
        this.matrix = new ObjectCard[numRows][numColumns];
        //this.objectCardVector= null; ///DA RIVEDERE //Arrays.fill(objectCardVector,22);
        this.notAvailablePositions= retrieveUnavailablePositionsSet(); //lancia una eccezione IOException se non trova il file Grid.json
    }

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
            if(drawn[i]==null)
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
                   (matrix[drawn[i].getX()][drawn[i].getY()-1]!=null)&&
                   (matrix[drawn[i].getX()][drawn[i].getY()]!=null))
                        return false;
            }
        }
        return true;
    }
    public ObjectCard[] draw(Couple[] move){
        return null;
    }



/////////////////////////////////////////////////////////////METTITI DACCORDO CON FIGIO/////////////////////////////////////////////////////
/*


    /*private ObjectCard generateObjectCard()
    {
        Random random = new Random();
        int randomNumber;

        //cardFinished e returna subito
        do {
            randomNumber = random.nextInt(6) + 1;
            if(objectCardVector[randomNumber] == 0)
            {
                if(cardFinished())
                {
                    return null;
                }
            }
        }while(objectCardVector[randomNumber] > 0);
        return new ObjectCard(randomNumber);
    }*/

    /*private boolean cardFinished()
    {
        for(int i : objectCardVector)
        {
            if(i!=0)
                return false;
        }
        return true;
    }*/
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private Set<Position> retrieveUnavailablePositionsSet() throws IOException
    {
        Set<Position> gettedPositions = new HashSet<>();
        Gson gson = new Gson();
        Reader reader = new FileReader("Grid.json");
        JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);

        JsonArray arrayOfJsonCells = jsonObject.getAsJsonArray("Default_Invalid_Positions");
        JsonArray jsonCell;

        for (JsonElement jsonCellElement : arrayOfJsonCells) {
            jsonCell = jsonCellElement.getAsJsonArray();
            gettedPositions.add(new Position(jsonCell.get(0).getAsInt(),jsonCell.get(1).getAsInt()));
        }

        for(int i=numPlayers; i<4; i++)
        {
            for (JsonElement vettore : arrayOfJsonCells) {
                arrayOfJsonCells = jsonObject.getAsJsonArray((i+1)+"_Player_New_Positions");
                jsonCell = vettore.getAsJsonArray();
                gettedPositions.add(new Position(jsonCell.get(0).getAsInt(),jsonCell.get(1).getAsInt()));
            }
        }
        return gettedPositions;
    }

    private boolean isInside(Position position)
    {
        return position.getX()>=0 && position.getX()< numColumns &&
                position.getY()>=0 && position.getY()< numRows &&
                !notAvailablePositions.contains(position);
    }
    private boolean isAvailable(Position position)
    {
        return !notAvailablePositions.contains(position);
    }
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
    private boolean hasSameInt(int[] vector) {
        for(int i=1; i<vector.length;i++)
        {
            if(vector[i]!=vector[i-1])
                return false;
        }
        return true;
    }
}
