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

    /**
     * @return the row of the position
     */
    public int getRow() {
        return row;
    }

    /**
     * @return the column of the position
     */
    public int getColumn() {
        return column;
    }

    /**
     * Puts the parameter as row index
     * @param row the integer to be put as a row index
     */
    public void setRow(int row) {
        this.row = row;
    }

    /**
     * Puts the parameter as column index
     * @param column the integer to be put as a column index
     */
    public void setColumn(int column) {
        this.column = column;
    }

    /**
     * Puts the first parameter as row index, and the second parameter as column index.
     * @param row the integer to be put as a row index
     * @param column the integer to be put as a column index
     */
    public void setRowColumn(int row,int column) {
        this.row = row;
        this.column = column;
    }

    /**
     * @param obj the object to be compared with "this"
     * @return true is obj equals to "this"
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

    /**
     * @return an unique hashcode calculated from row and column
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
