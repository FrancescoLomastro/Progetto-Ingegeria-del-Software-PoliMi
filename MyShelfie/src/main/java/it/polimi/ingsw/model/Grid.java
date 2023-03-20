package main.java.it.polimi.ingsw.model;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;
import java.util.Set;

public class Grid {

    private final int dimensionX;
    private final int dimensionY;
    private int numPlayers;
    private int[] objectCardVector; //3*6
    private ObjectCard[][] matrix;
    private Set<Position> notAvailablePositions;

    public Grid(int numPlayers){
        //legge il file playGround.json da cui ricava i parametri
        //NOTPosition       celle che non fanno parte del playGround a prescindere dai giocatori
        //3_NOTPosition     celle da abilitare se i gioatori sono 3
        //4_NOTposition     celle da abilitare se i giocatori sono 4, bisogna abilitare anche 3_NOTPosition
        //con questo si inizializza matrix e notAvailablePositions (contenente l'insieme di tutte le NOTPosition)
        Arrays.fill(objectCardVector,22);
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
                if(matrix[i][j]!=null && isAvailable(position) && anySurrounding(position))
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
    private boolean anySurrounding(Position position)
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


    public boolean isDrawAvailable(Position[] move){
        //move non nulla
        //move lunga max 3 e min 1
        //move in colonna oppure move in riga
        //move piena di carte
        //move
    }

    public ObjectCard[] draw(Couple[] move){

    }

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


}
