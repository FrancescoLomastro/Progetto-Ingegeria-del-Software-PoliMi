package it.polimi.ingsw.enums;

/**
 * Enum representation of the possible direction that a depth search algorithm can use
 */
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

    /**
     * @return the row direction value of the enum
     */
    public int getRowDirection() {
        return rowDirection;
    }
    /**
     * @return the column direction value of the enum
     */
    public int getColumnDirection() {
        return columnDirection;
    }
}
