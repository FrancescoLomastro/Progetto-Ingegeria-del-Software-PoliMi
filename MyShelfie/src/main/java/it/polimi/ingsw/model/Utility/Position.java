package it.polimi.ingsw.model.Utility;


import java.io.Serializable;

public class Position implements Serializable {
    private static final long serialVersionUID = 1L;

    private int row;
    private int column;

    public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public void setRowColumn(int row,int column) {
        this.row = row;
        this.column = column;
    }
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
    @Override //modo per produrre hashcode univoci da 2 campi row e column
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + row;
        result = prime * result + column;
        return result;
    }
}
