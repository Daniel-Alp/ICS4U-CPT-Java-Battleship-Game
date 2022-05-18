package battleship.ai;

public enum Direction {
    UP(1, 0), RIGHT(0, 1), DOWN(-1, 0), LEFT(0, -1);

    private int rowOffset, columnOffset;

    Direction (int rowOffset, int columnOffset) {
        this.rowOffset = rowOffset;
        this.columnOffset = columnOffset;
    }

    public int getRowOffset() {
        return rowOffset;
    }

    public int getColumnOffset() {
        return columnOffset;
    }
}
