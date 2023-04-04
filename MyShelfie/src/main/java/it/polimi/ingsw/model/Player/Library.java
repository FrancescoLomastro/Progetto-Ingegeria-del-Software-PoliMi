package it.polimi.ingsw.model.Player;

import it.polimi.ingsw.model.Cards.ObjectCard;
import it.polimi.ingsw.model.Enums.Direction;
import it.polimi.ingsw.model.Utility.Position;

import java.util.HashSet;

/**
 * Library class contains methods related to all actions which are controlled by the library of each player.
 * The main responsibilities are related to the insertion of new cards, counting adjacency points and checking if the library is full.
 *
 * @author Alberto Aniballi
 */
public class Library {
    private final int numberOfRows=6;
    private final int numberOfColumns=5;
    private ObjectCard[][] matrix;

    /**
     * Constructor of Library instances
     * Creates an empty matrix of ObjectCards
     *  @author Alberto Aniballi
     */
    public Library() {
        matrix = new ObjectCard[numberOfRows][numberOfColumns];
    }

    /**
     * It gets the library number of columns
     *
     * @return: number of library columns
     *  @author Alberto Aniballi
     */
    public int getNumberOfColumns() {
        return numberOfColumns;
    }

    /**
     * It gets the library number of rows
     *
     * @return: number of library rows
     * @author Alberto Aniballi
     */
    public int getNumberOfRows() {
        return numberOfRows;
    }


    /**
     * It gets a matrix of object cards that represent the library
     *
     * @return: matrix of object cards that represent the library
     * @author Lo Mastro Francesco
     */
    public ObjectCard[][] getMatrix() {
        ObjectCard[][] toReturn= new ObjectCard[numberOfRows][numberOfColumns];
        for(int row=0; row<numberOfRows;row++)
        {
            for(int column=0;column<numberOfColumns;column++)
            {
                toReturn[row][column]=matrix[row][column];
            }
        }
        return toReturn;
    }


    /**
     * It checks if a specific cell of the library is free
     *
     * @param row    the specific row to be checked
     * @param column the specific column to be checked
     * @return: boolean that is true if the cell is empty, otherwise false
     */
    private boolean isCellEmpty(int row, int column) {
        if (matrix[row][column] != null) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * It checks if the library is full
     *
     * @return: boolean that is true if the library is full, otherwise false
     *  @author Alberto Aniballi
     */
    public boolean isFull() {
        int highest_row = 0;
        boolean libraryIsFull = true;

        for(int col=0;col<getNumberOfColumns();col++) {
            if (isCellEmpty(highest_row,col)) {
                libraryIsFull = false;
                return libraryIsFull;
            }
        }

        return  libraryIsFull;
    }

    /**
     * It counts the number of available free cells of a specific column
     *
     * @param column the specific column where we have to count the number of free cells
     * @return: an integer that represents the number of free cells in a column
     * @author Alberto Aniballi
     */
    public int findNumberOfFreeCells(int column) {
        int availableCells = 0;
        if(column>=0 && column<getNumberOfColumns())
        {
            for(int row=numberOfRows-1;row>=0;row--) {
                if (isCellEmpty(row,column)) {
                    availableCells = row+1;
                    break;
                }
            }
        }
        return availableCells;
    }

    /**
     * It inserts the cards passed as arguments into a specific column starting from the first available cell

     * @param chosenColumn          the specific column where we insert cards
     * @param cards                 array containing the object cards to be inserted in the order of insertion
     * @return true if the cards array has been succesuflly inserted into the fibrary
     * @author: Alberto Aniballi, Francesco Lo Mastro
     */
    public boolean insertCardsInLibrary(int chosenColumn, ObjectCard... cards) {
        int row;
        if(isMoveAvailable(chosenColumn,cards))
        {
            row=findNumberOfFreeCells(chosenColumn)-1;
            for (int i=0; i < cards.length; row--,i++)
            {
                matrix[row][chosenColumn] = cards[i];
            }
            return true;
        }
        return false;
    }

    /** Checks if an array of cards can be inserted into a specific column.
     *
     * @param chosenColumn the column to be checked
     * @param cards the array of cards to be controlled
     * @return true if the array of cards fits into the column selected
     * @author: Alberto Aniballi
     */
    public boolean isMoveAvailable(int chosenColumn, ObjectCard... cards){
        int row = findNumberOfFreeCells(chosenColumn);
        return row>=cards.length && chosenColumn<numberOfColumns && chosenColumn>=0;
    }



    /**
     * It verifies if two object cards have the same color
     *
     * @param objectCard1 the first object card to be compared
     * @param objectCard2 the second object card to be compared
     * @return: boolean that is true if the two cards have the same color, false otherwise
     */
    private boolean compareColor(ObjectCard objectCard1,ObjectCard objectCard2) {
        if (objectCard1.getColor()==objectCard2.getColor()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * It checks if the current cell, identified by startRow and startColumn, has the same color of the celle identified by calculatedRow and calculatedColumn
     *
     * @param startRow      the row where the current cell is
     * @param startColumn   the column where the current cell is
     * @param calculatedRow      the row where the subsequent cell is
     * @param calculatedColumn   the column where the subsequent cell is
     * @return: boolean that is true if the two cells contains two object cards that have the same color, false otherwise
     * @author: Alberto Aniballi
     */
    private boolean checkIfColorIsTheSame(int startRow, int startColumn, int calculatedRow,int calculatedColumn) {
        return compareColor(matrix[startRow][startColumn], matrix[calculatedRow][calculatedColumn]);
    }

    /**
     * Checks if the cordinate of a cell in the library are valid
     * @param row   the row where the current cell is
     * @param column the column where the current cell is
     * @return true if the cordinate of a cell in the library are valid
     * @author: Francesco Lo Mastro
     */
    private boolean checkPositionIsValid(int row, int column) {
        return !(column < 0 || column >= numberOfColumns || row < 0 || row >= numberOfRows);
    }



    /**
     * It counts the number of cards that are identified as neighbours of the parameter, using a recursive approach
     * NOTE: a neighbour of a X card is a card that has the same color of X, also it needs to be close to subsequent to another neighbour of X, or X itself
     *
     * @param startRow      the row where the current cell is
     * @param startColumn   the column where the current cell is
     * @return:             integer that represents the number of neighbours of the passed cell
     * @author: Alberto Aniballi, Francesco Lo MAstro
     */
    public int countNeighbours(int startRow, int startColumn,HashSet<Position> checkedCells) {
        int numberOfSameColorNeighbours = 0;
        int calculatedRow;
        int calculatedColumn;
        Position position;

        if(matrix[startRow][startColumn]!=null)
        {
            for (Direction direction : Direction.values())
            {
                calculatedRow = startRow + direction.getRowDirection();
                calculatedColumn = startColumn + direction.getColumnDirection();
                if (checkPositionIsValid(calculatedRow, calculatedColumn))
                {
                    position = new Position(calculatedRow, calculatedColumn);
                    if (matrix[calculatedRow][calculatedColumn] == null)
                        checkedCells.add(position);
                    else {
                        if (checkIfColorIsTheSame(startRow, startColumn, calculatedRow, calculatedColumn)) {
                            if (checkedCells.add(position)) {
                                numberOfSameColorNeighbours += 1 + countNeighbours(calculatedRow, calculatedColumn,checkedCells);
                            }
                        }
                    }
                }
            }
        }
        return numberOfSameColorNeighbours;
    }

    /**
     * It selects the correct number of points to be given, based on the size of the group of adjacent card found
     *
     * @param numOfAdjacentCard the number of neighbours that have the same color
     * @return: integer that represents the number of points to be given
     * @author: Alberto Aniballi
     */
    private int addAdjacentPoints(int numOfAdjacentCard) {
        if (numOfAdjacentCard==3) {
            return 2;
        } else if (numOfAdjacentCard==4) {
            return 3;
        } else if (numOfAdjacentCard==5) {
            return 5;
        } else if (numOfAdjacentCard>=6) {
            return 8;
        } else {
            return 0;
        }
    }

    /**
     * It returns the amount of points based on the number of groups of adjacent card found in the library
     *
     * @return: integer that represents the number of adjacency points due to object cards in the library
     * @author: Alberto Aniballi, Francesco Lo Mastro
     */
    public int countAdjacentPoints() {

        int numberOfAdjacentPoints = 0;
        int numberOfNeighbours;
        Position position;
        HashSet <Position> checkedCells = new HashSet<>();

        for(int row=0;row<numberOfRows;row++)
        {
            for(int column = 0;column<numberOfColumns;column++)
            {
                position = new Position(row,column);
                if(checkedCells.add(position))
                {
                    if(matrix[row][column]!=null)
                    {
                        numberOfNeighbours = countNeighbours(row, column,checkedCells);
                        numberOfAdjacentPoints += addAdjacentPoints(numberOfNeighbours+1);
                    }
                }
            }
        }
        return numberOfAdjacentPoints;
    }
    public void stampa()
    {
        for(int row=0;row<numberOfRows;row++)
        {
            for(int column = 0;column<numberOfColumns;column++)
            {
                if(matrix[row][column]==null)
                    System.out.print("| ");
                else
                    System.out.print(matrix[row][column]+" ");
            }
            System.out.println("");
        }
    }

}

