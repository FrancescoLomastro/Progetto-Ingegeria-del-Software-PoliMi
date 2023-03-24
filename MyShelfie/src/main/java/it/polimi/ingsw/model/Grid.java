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
import java.util.Random;
import java.util.Set;

public class Grid {

    private final int dimensionX=9;
    private final int dimensionY=9;
    private int numPlayers;
    private int[] objectCardVector; //3*6
    private ObjectCard[][] matrix;
    private Set<Position> notAvailablePositions;

    public Grid(int numPlayers) throws IOException{
        this.numPlayers=numPlayers;
        this.matrix = new ObjectCard[dimensionX][dimensionX];
        this.objectCardVector= null; ///DA RIVEDERE //Arrays.fill(objectCardVector,22);

        this.notAvailablePositions= retrieveUnavailablePositionsSet(); //lancia una eccezione IOException se non trova il file Grid.json
    }

    public void refill(){
        Position position = new Position(0,0);
        for(int i=0; i<dimensionX;i++)
        {
            for(int j=0; j<dimensionY;j++)
            {
                position.setXY(i,j);
                if(matrix[i][j]==null && isAvailable(position))
                {
                    //genera la carta
                }
            }
        }
    }
    public boolean needRefill(){
        Position position = new Position(0,0);
        for(int i=0; i<dimensionX;i++)
        {
            for(int j=0; j<dimensionY;j++)
            {
                position.setXY(i,j);
                if(matrix[i][j]!=null && isAvailable(position) && hasNeighbours(position))
                {
                    return false;
                }
            }
        }
        return true;
    }
    private boolean isInside(Position position)
    {
        return position.getX()>=0 && position.getX()<dimensionX && position.getY()>=0 && position.getY()<dimensionY && !notAvailablePositions.contains(position);
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
           (x<dimensionX-1 && matrix[x+1][y]!=null) ||
           (y>0 && matrix[x][y-1]!=null) ||
           (y>0 && matrix[x][y]!=null)) {
            return false;
        }
        return true;
    }

  /*  public boolean isDrawAvailable(Position[] move){
        //move non nulla
        //move lunga max 3 e min 1
        //move in colonna oppure move in riga
        //move piena di carte
        //move
    }

    public ObjectCard[] draw(Couple[] move){

    }*/

    private ObjectCard generateObjectCard()
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
    }
    private boolean cardFinished()
    {
        for(int i : objectCardVector)
        {
            if(i!=0)
                return false;
        }
        return true;
    }


    private Set<Position> retrieveUnavailablePositionsSet() throws IOException
    {
        Set<Position> hashSet = new HashSet<>();
        Gson gson = new Gson();
        Reader reader = new FileReader("Grid.json");

        JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);

        JsonArray arrayOfCells = jsonObject.getAsJsonArray("Default_Invalid_Positions");
        JsonArray cell;

        for (JsonElement cellElement : arrayOfCells) {
            cell = cellElement.getAsJsonArray();
            hashSet.add(new Position(cell.get(0).getAsInt(),cell.get(1).getAsInt()));
        }
        for(int i=this.numPlayers+1; i<=4; i++)
        {
            for (JsonElement vettore : arrayOfCells) {
                arrayOfCells = jsonObject.getAsJsonArray(i+"_Player_New_Positions");
                cell = vettore.getAsJsonArray();
                hashSet.add(new Position(cell.get(0).getAsInt(),cell.get(1).getAsInt()));
            }
        }
        return hashSet;
    }

}
