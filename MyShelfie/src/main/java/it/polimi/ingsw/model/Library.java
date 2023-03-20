package main.java.it.polimi.ingsw.model;

import java.util.HashSet;

public class Library {
    private final int numberOfRows;
    private final int numberOfColumns;
    private ObjectCard[][] library;
    private HashSet<String> checkedCells;

    public Library(int dimensionX,int dimensionY) {
        this.numberOfColumns = dimensionX;
        this.numberOfRows = dimensionY;
        library = new ObjectCard[numberOfRows][numberOfColumns];
        checkedCells = new HashSet<String>();
    }

    public int getNumberOfColumns() {
        return numberOfColumns;
    }

    public int getNumberOfRows() {
        return numberOfRows;
    }

    public ObjectCard[][] getLibrary() {
        return library;
    }

    public HashSet<String> getCheckedCells() {
        return checkedCells;
    }

    private boolean isCellEmpty(int row, int column) {
        if (library[row][column] != null) {
            return false;
        } else {
            return true;
        }
    }

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

    private void insertCardsInLibrary(ObjectCard[] cards,int firstAvailableRow,int chosenColumn) { // TO BE DISCUSSED
        int insertionRow = firstAvailableRow;

        for(ObjectCard card : cards) {
            library[insertionRow][chosenColumn] = card;
            insertionRow++;
        }
    }

    public boolean checkColumn(ObjectCard[] cards,int chosenColumn) {
        // Assuming that cards contain the chosen cards from the grid, inserted in the order of insertion into the selected column
        boolean columnIsAvailable = false;
        int numberOfChosenCards = cards.length(); //da decidere insieme quando controlliamo che il numero di carte sia minore o uguale a 3, in caso contrario lancio eccezione o mossa non valida
        int numberOfAvailableCellsOnColumn = findNumberOfFreeCells(column);

        if (numberOfAvailableCellsOnColumn >= numberOfChosenCards) {
            columnIsAvailable = true;
            int firstAvailableRow = getNumberOfRows()-numberOfAvailableCellsOnColumn;
            insertCardsInLibrary(cards,firstAvailableRow,chosenColumn);
        }

        return  columnIsAvailable;
    }

    public int findLastRowWithSameColorInSameColumn(int startRow, int startColumn) {

        String color = library[startRow][startColumn].getColor();
        int finalSameColorRow = startRow;
        int column = startColumn;

        //Check if neighbour cell on the same column have same color, up to the last cell with same color
        for(int row=startRow+1;row<getNumberOfRows();row++) {
            if(library[row][startColumn].getColor().equals(color)) {
                finalSameColorRow = row;
            } else {
                break;
            }
        }

        return finalSameColorRow;
    }

    private int findLastColumnWithSameColorInSameRow(int row, int startColumn) {

        getCheckedCells().add(row+"_"+startColumn);

        for(int column=startColumn;column<getNumberOfColumns();column++) {
            if (getLibrary()[lastCellSameColorInSameColumn_row])
        }
    }

    private int countSameColorNeighbours(int startRow, int startColumn) {

        int numberOfSameColorNeighbours = 0;
        int lastCellSameColorInSameColumn_row = findLastRowWithSameColorInSameColumn(startRow,startColumn);

        for(int row=lastCellSameColorInSameColumn_row;row>=startRow;row--) {
            getCheckedCells().add(lastCellSameColorInSameColumn_row+"_"+startColumn)
            int finalSameColorRow = findLastColumnWithSameColorInSameRow(row,startColumn);

        }
    }

    public int countAdjacentPoints() {
        // La stringa "row_col" identifica univocamente una cella per questo uso checked_cells
        int row = 0;
        int column = 0;
        int numberOfAdjacentPoints = 0;

        while (row<getNumberOfRows()) {
            while ((column <getNumberOfColumns()) && (!checkedCells.contains(row+"_"+ column))) {
                int sameColorNeighbours = countSameColorNeighbours(row, column);
                /* da implementare la verifica dei punti da aggiungere in base al numero di sameColorNeighbours*/
                column++;
            }
            column=0;
            row++;
        }

        return numberOfAdjacentPoints;
    }

    public boolean isCommonGoalCardSatisfied(CommonGoalCard commonGoalCard) {
        /*
        To be implemented
         */
    }

}

