package battleship.model;

public class Ship {
    private final Type type;
    private final int length;
    private final Orientation orientation;
    private final int row;
    private final int column;
    private int hitCounter = 0;

    public Ship(Type type, Orientation orientation, Coordinate coordinates) {
        this.type = type;
        length = type.getLength();
        this.orientation = orientation;
        row = coordinates.getRow();
        column = coordinates.getColumn();
    }

    public boolean containsCoordinates(Coordinate target) {
        int targetRow = target.getRow(), targetColumn = target.getColumn();
        if (orientation == Orientation.HORIZONTAL) {
            return targetRow == row && targetColumn >= column && targetColumn < column + length;
        } else {
            return targetColumn == column && targetRow >= row && targetRow < row + length;
        }
    }

    public void hit() {
        hitCounter++;
    }

    public boolean isSunk() {
        return hitCounter == length;
    }

    public Type getType() {
        return type;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
}
