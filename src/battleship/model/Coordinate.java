/*
 * Coordinate.java
 *
 * Daniel Alp
 * ICS4U1
 * 2022-06-08
 */

package battleship.model;

public class Coordinate {
    private int row;
    private int column;

    /**
     *
     * @param row
     * @param column
     */
    public Coordinate(int row, int column) {
        this.row = row;
        this.column = column;
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
