package it.polimi.ingsw.utility;


import java.io.Serializable;

/**
 * Class used to get a representation of a matrix position, with row and column
 */
public class Position implements Serializable {
    private static final long serialVersionUID = 1L;

    private int row;
    private int column;

    public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }

    /**It returns the row number
     * @return the row of the position
     * @author: RIccardo Figini
     */
    public int getRow() {
        return row;
    }

    /**It returns the column number
     * @return the column of the position
     * @author: Riccardo Figini
     */
    public int getColumn() {
        return column;
    }

    /**
     * Puts the parameter as row index
     * @param row the integer to be put as a row index
     * @author: Figini Riccardo
     */
    public void setRow(int row) {
        this.row = row;
    }

    /**
     * Puts the parameter as column index
     * @param column the integer to be put as a column index
     * @author: RIccardo Figini
     */
    public void setColumn(int column) {
        this.column = column;
    }

    /**
     * Puts the first parameter as row index, and the second parameter as column index.
     * @param row the integer to be put as a row index
     * @param column the integer to be put as a column index
     * @author: RIccardo FIgini
     */
    public void setRowColumn(int row,int column) {
        this.row = row;
        this.column = column;
    }

    /**
     * @param obj the object to be compared with "this"
     * @return true is obj equals to "this"
     * @author: Riccardo Figini
     * @author: Francesco Lo Mastro
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Position)) {
            return false;
        }
        Position other = (Position) obj;
        return this.row == other.row && this.column == other.column;
    }

    /**It returns hash value of combined row and column.
     * @return a unique hashcode calculated from row and column
     * @author: Francesco Lo Mastro
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + row;
        result = prime * result + column;
        return result;
    }
}
