package Battleship.model;

public class Ship {
    private int length;
    private Orientation orientation;
    private int row, column;
    private int hitCounter = 0;

    public Ship (Type type, Orientation orientation, Coordinate coordinates) {
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
}
