package battleship.ai;

public enum Direction {
    UP(1, 0), RIGHT(0, 1), DOWN(-1, 0), LEFT(0, -1);

    private int rowChange, columnChange;

    Direction (int rowChange, int columnChange) {
        this.rowChange = rowChange;
        this.columnChange = columnChange;
    }

    public int getRowChange() {
        return rowChange;
    }

    public int getColumnChange() {
        return columnChange;
    }
}
