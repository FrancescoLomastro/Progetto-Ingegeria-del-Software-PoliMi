package it.polimi.ingsw.enums;

public enum Direction {
    UP(-1,0),
    DOWN(+1,0),
    LEFT(0,-1),
    RIGHT(0,1);
    private final int rowDirection;
    private final int columnDirection;
    private Direction(int rowDirection, int columnDirection) {
        this.rowDirection = rowDirection;
        this.columnDirection = columnDirection;
    }

    public int getRowDirection() {
        return rowDirection;
    }
    public int getColumnDirection() {
        return columnDirection;
    }
}
