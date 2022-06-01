/*
 * Ship.java
 *
 * Daniel Alp
 * ICS4U1
 * 2022-06-08
 */

package battleship.model;

public class Ship {
    private Type type;
    private int length;
    private Orientation orientation;
    private int row;
    private int column;
    private int hitCounter = 0;

    /**
     *
     * @param type
     * @param orientation
     * @param coordinates
     */
    public Ship(Type type, Orientation orientation, Coordinate coordinates) {
        this.type = type;
        length = type.getLength();
        this.orientation = orientation;
        row = coordinates.getRow();
        column = coordinates.getColumn();
    }

    /**
     *
     * @param target
     * @return
     */
    public boolean containsCoordinates(Coordinate target) {
        int targetRow = target.getRow(), targetColumn = target.getColumn();
        if (orientation == Orientation.HORIZONTAL) {
            return targetRow == row && targetColumn >= column && targetColumn < column + length;
        } else {
            return targetColumn == column && targetRow >= row && targetRow < row + length;
        }
    }

    /**
     *
     */
    public void hit() {
        hitCounter++;
    }

    /**
     *
     * @return
     */
    public boolean isSunk() {
        return hitCounter == length;
    }

    /**
     *
     * @return
     */
    public Type getType() {
        return type;
    }

    /**
     *
     * @return
     */
    public Orientation getOrientation() {
        return orientation;
    }

    /**
     *
     * @return
     */
    public int getRow() {
        return row;
    }

    /**
     *
     * @return
     */
    public int getColumn() {
        return column;
    }
}
