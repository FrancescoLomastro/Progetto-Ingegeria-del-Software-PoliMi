package it.polimi.ingsw.model;

import java.util.HashSet;

/**
 * Library class contains methods related to all actions which are controlled by the library of each player.
 * The main responsibilities are related to the insertion of new cards, counting adjacency points and checking if the library is full.
 *
 * @author Alberto Aniballi
 */
public class Library {
    private final int numberOfRows;
    private final int numberOfColumns;
    private ObjectCard[][] library;

    /**
     * Constructor of Library instances
     *
     * @param horizontalDimension the library horizontal dimension
     * @param verticalDimension the library vertical dimension
     */
    public Library(int horizontalDimension,int verticalDimension) {
        this.numberOfColumns = horizontalDimension;
        this.numberOfRows = verticalDimension;
        library = new ObjectCard[numberOfRows][numberOfColumns];
    }

    /**
     * It gets the library number of columns
     *
     * @return: number of library columns
     */
    public int getNumberOfColumns() {
        return numberOfColumns;
    }

    /**
     * It gets the library number of rows
     *
     * @return: number of library rows
     */
    public int getNumberOfRows() {
        return numberOfRows;
    }

    /**
     * It gets the matrix of object cards that represent the library
     *
     * @return: matrix of object cards that represent the library
     */
    public ObjectCard[][] getLibrary() {
        return library;
    }

    /**
     * It checks if a specific cell of the library is free
     *
     * @param row    the specific row to be checked
     * @param column the specific column to be checked
     * @return: boolean that is true if the cell is empty, otherwise false
     */
    private boolean isCellEmpty(int row, int column) {
        if (library[row][column] != null) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * It checks if the library is full
     *
     * @return: boolean that is true if the library is full, otherwise false
     */
    public boolean isFull() {
        //In order to check if the Library is full it is only necessary to check if the highest rows are full

        int highest_row = getNumberOfRows()-1;
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
     */
    public int findNumberOfFreeCells(int column) {
        int availableCells = 0;
        for(int row=0;row<getNumberOfRows();row++) {
            if (isCellEmpty(row,column)) {
                availableCells = getNumberOfRows() - row;//once we find the first empty cell also the ones on top of it are free, so we can directly calculate the final result
                break;
            }
        }

        return availableCells;
    }

    /**
     * It inserts the cards passed as arguments into a specific column starting from the first available cell
     *
     * @param cards                 array containing the object cards to be inserted in the order of insertion
     * @param firstAvailableRow     the first available row of the specific column
     * @param chosenColumn          the specific column where we insert cards
     */
    private void insertCardsInLibrary(ObjectCard[] cards,int firstAvailableRow,int chosenColumn) { // TO BE DISCUSSED
        int insertionRow = firstAvailableRow;

        for(ObjectCard card : cards) {
            library[insertionRow][chosenColumn] = card;
            insertionRow++;
        }
    }

    /**
     * It verifies if a specific column has enough available cells to insert the cards contained in the array
     *
     * @param cards             array containing the object cards to be inserted in the order of insertion
     * @param chosenColumn      the specific column that we want to verify
     * @return:                 boolean that is true if the chosen column has enough free cells
     */
    public boolean checkColumn(ObjectCard[] cards,int chosenColumn) {
        // Assuming that cards contain the chosen cards from the grid, inserted in the order of insertion into the selected column
        boolean columnIsAvailable = false;
        int numberOfChosenCards = cards.length; //da decidere insieme quando controlliamo che il numero di carte sia minore o uguale a 3, in caso contrario lancio eccezione o mossa non valida
        int numberOfAvailableCellsOnColumn = findNumberOfFreeCells(chosenColumn);

        if (numberOfAvailableCellsOnColumn >= numberOfChosenCards) {
            columnIsAvailable = true;
            int firstAvailableRow = getNumberOfRows()-numberOfAvailableCellsOnColumn;
            insertCardsInLibrary(cards,firstAvailableRow,chosenColumn);
        }

        return  columnIsAvailable;
    }

    /**
     * It verifies if two object cards have the same color
     *
     * @param objectCard1 the first object card to be compared
     * @param objectCard2 the second object card to be compared
     * @return: boolean that is true if the two cards have the same color, false otherwise
     */
    private boolean compareColor(ObjectCard objectCard1,ObjectCard objectCard2) {
        if (objectCard1.getColor().equals(objectCard2.getColor())) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * It checks if the current cell, identified by startRow and startColumn, has the same color of the subsequent cell in the specified direction
     *
     * @param startRow      the row where the current cell is
     * @param startColumn   the column where the current cell is
     * @param direction     the direction where we should select the next cell to be compared
     * @return: boolean that is true if the two cells contains two object cards that have the same color, false otherwise
     */
    private boolean checkIfColorIsTheSame(int startRow, int startColumn, String direction) {

        boolean answer = false;

        switch (direction) {
            case "Up": {
                if(compareColor(library[startRow][startColumn],library[startRow+1][startColumn])) {
                    answer = true;
                }
                break;
            }
            case "Right": {
                if(compareColor(library[startRow][startColumn],library[startRow][startColumn+1])) {
                    answer = true;
                }
                break;
            }
            case "Left": {
                if(compareColor(library[startRow][startColumn],library[startRow][startColumn-1])) {
                    answer = true;
                }
                break;
            }
            case "Down": {
                if(compareColor(library[startRow][startColumn],library[startRow-1][startColumn])) {
                    answer = true;
                }
                break;
            }
        }

        return answer;
    }

    /**
     * It counts the number of neighbours that have the same color of the initial cell passed as argument using a depth-first search approach
     *
     * @param startRow      the row where the current cell is
     * @param startColumn   the column where the current cell is
     * @param checkedCells  the set containing the already checked cells, these cells are not available for branching
     * @return:             integer that represents the number of neighbours cells that store an object card of the same color of the initial object card
     */
    private int countSameColorNeighbours(int startRow, int startColumn, HashSet<String> checkedCells) {

        int numberOfSameColorNeighbours = 0;
        //int lastCellSameColorInSameColumn_row = findLastRowWithSameColorInSameColumn(startRow,startColumn);
        if(checkIfColorIsTheSame(startRow,startColumn,"Up")) {
            if (!checkedCells.contains((startRow+1)+"_"+startColumn)) {
                checkedCells.add((startRow+1)+"_"+startColumn);
                numberOfSameColorNeighbours+=1 + countSameColorNeighbours(startRow+1,startColumn,checkedCells);
            } else {
                numberOfSameColorNeighbours+=1;
            }
        } else if (checkIfColorIsTheSame(startRow,startColumn,"Right")) {
            if (!checkedCells.contains(startRow+"_"+(startColumn+1))) {
                checkedCells.add(startRow+"_"+(startColumn+1));
                numberOfSameColorNeighbours+=1 + countSameColorNeighbours(startRow,startColumn+1,checkedCells);
            } else {
                numberOfSameColorNeighbours+=1;
            }
        } else if (checkIfColorIsTheSame(startRow,startColumn,"Left")) {
            if (!checkedCells.contains(startRow + "_" + (startColumn - 1))) {
                checkedCells.add(startRow+"_"+(startColumn-1));
                numberOfSameColorNeighbours+=1 + countSameColorNeighbours(startRow, startColumn - 1,checkedCells);
            } else {
                numberOfSameColorNeighbours+=1;
            }
        } else if (checkIfColorIsTheSame(startRow,startColumn,"Down")) {
            if (!checkedCells.contains((startRow-1) + "_" + startColumn)) {
                checkedCells.add((startRow-1)+"_"+(startColumn));
                numberOfSameColorNeighbours+=1 + countSameColorNeighbours(startRow-1, startColumn,checkedCells);
            } else {
                numberOfSameColorNeighbours+=1;
            }
        } else {
            numberOfSameColorNeighbours+=1;
        }
        return numberOfSameColorNeighbours;
    }

    /**
     * It selects the correct number of points to be added based on the number of neighbour cells that stores object cards that have the same color of the initial object card
     *
     * @param sameColorNeighbours the number of neighbours that have the same color
     * @return: integer that represents the number of points to be added
     */
    private int addAdjacentPoints(int sameColorNeighbours) {
        if (sameColorNeighbours==3) {
            return 2;
        } else if (sameColorNeighbours==4) {
            return 3;
        } else if (sameColorNeighbours==5) {
            return 5;
        } else if (sameColorNeighbours>=6) {
            return 8;
        } else {
            return 0;
        }
    }

    /*
    To be tested: cosa succede se abbiamo due possibili strade da poter seguire dalla cella iniziale?
     */
    /**
     * It counts the correct number of adjacency points to be added based on the number of neighbour cells that stores object cards that have the same color of the initial object card
     *
     * @return: integer that represents the number of adjacency points due to object cards positioning on the library
     */
    public int countAdjacentPoints() {
        // La stringa "row_col" identifica univocamente una cella per questo uso checked_cells
        int row = 0;
        int column = 0;
        int numberOfAdjacentPoints = 0;
        HashSet<String> checkedCells = new HashSet<String>();


        while (row<getNumberOfRows()) {
            while ((column <getNumberOfColumns()) && (!checkedCells.contains(row+"_"+ column))) {
                checkedCells.add(row+"_"+column);
                int sameColorNeighbours = countSameColorNeighbours(row, column,checkedCells);

                /* da implementare la verifica dei punti da aggiungere in base al numero di sameColorNeighbours*/
                numberOfAdjacentPoints += addAdjacentPoints(sameColorNeighbours);
                column++;
            }
            column=0;
            row++;
        }

        return numberOfAdjacentPoints;
    }

}

