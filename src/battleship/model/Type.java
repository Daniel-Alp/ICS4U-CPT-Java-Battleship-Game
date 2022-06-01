/*
 * Type.java
 *
 * Daniel Alp
 * ICS4U1
 * 2022-06-08
 */

package battleship.model;

public enum Type {
    CARRIER(5), BATTLESHIP(4), CRUISER(3), SUBMARINE(3), DESTROYER(2);

    private int length;

    /**
     *
     * @param length
     */
    Type(int length) {
        this.length = length;
    }

    /**
     *
     * @return
     */
    public int getLength() {
        return length;
    }
}
