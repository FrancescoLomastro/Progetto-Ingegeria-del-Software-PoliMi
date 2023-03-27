package it.polimi.ingsw.model;

import java.util.HashSet;

/**
 * Library class
 * @author Alberto Aniballi
 */
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
        int numberOfChosenCards = cards.length; //da decidere insieme quando controlliamo che il numero di carte sia minore o uguale a 3, in caso contrario lancio eccezione o mossa non valida
        int numberOfAvailableCellsOnColumn = findNumberOfFreeCells(column);

        if (numberOfAvailableCellsOnColumn >= numberOfChosenCards) {
            columnIsAvailable = true;
            int firstAvailableRow = getNumberOfRows()-numberOfAvailableCellsOnColumn;
            insertCardsInLibrary(cards,firstAvailableRow,chosenColumn);
        }

        return  columnIsAvailable;
    }

    private boolean compareColor(ObjectCard objectCard1,ObjectCard objectCard2) {
        if (objectCard1.getColor().equals(objectCard2.getColor())) {
            return true;
        } else {
            return false;
        }
    }

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

    private int countSameColorNeighbours(int startRow, int startColumn) {

        int numberOfSameColorNeighbours = 0;
        //int lastCellSameColorInSameColumn_row = findLastRowWithSameColorInSameColumn(startRow,startColumn);
        if(checkIfColorIsTheSame(startRow,startColumn,"Up")) {
            if (!checkedCells.contains((startRow+1)+"_"+startColumn)) {
                checkedCells.add((startRow+1)+"_"+startColumn);
                numberOfSameColorNeighbours+=1 + countSameColorNeighbours(startRow+1,startColumn,"Up");
            } else {
                numberOfSameColorNeighbours+=1;
            }
        } else if (checkIfColorIsTheSame(startRow,startColumn,"Right")) {
            if (!checkedCells.contains(startRow+"_"+(startColumn+1))) {
                checkedCells.add(startRow+"_"+(startColumn+1));
                numberOfSameColorNeighbours+=1 + countSameColorNeighbours(startRow,startColumn+1,"Right");
            } else {
                numberOfSameColorNeighbours+=1;
            }
        } else if (checkIfColorIsTheSame(startRow,startColumn,"Left")) {
            if (!checkedCells.contains(startRow + "_" + (startColumn - 1))) {
                checkedCells.add(startRow+"_"+(startColumn-1));
                numberOfSameColorNeighbours+=1 + countSameColorNeighbours(startRow, startColumn - 1, "Left");
            } else {
                numberOfSameColorNeighbours+=1;
            }
        } else if (checkIfColorIsTheSame(startRow,startColumn,"Down")) {
            if (!checkedCells.contains((startRow-1) + "_" + startColumn)) {
                checkedCells.add((startRow-1)+"_"+(startColumn));
                numberOfSameColorNeighbours+=1 + countSameColorNeighbours(startRow-1, startColumn, "Down");
            } else {
                numberOfSameColorNeighbours+=1;
            }
        } else {
            numberOfSameColorNeighbours+=1;
        }
        return numberOfSameColorNeighbours;
    }

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
    public int countAdjacentPoints() {
        // La stringa "row_col" identifica univocamente una cella per questo uso checked_cells
        int row = 0;
        int column = 0;
        int numberOfAdjacentPoints = 0;

        while (row<getNumberOfRows()) {
            while ((column <getNumberOfColumns()) && (!checkedCells.contains(row+"_"+ column))) {
                checkedCells.add(row+"_"+column);
                int sameColorNeighbours = countSameColorNeighbours(row, column);

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

