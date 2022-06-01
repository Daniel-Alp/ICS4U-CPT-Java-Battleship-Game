/*
 * Orientation.java
 *
 * Daniel Alp
 * ICS4U1
 * 2022-06-08
 */
package battleship.model;

import java.util.Random;

public enum Orientation {
    HORIZONTAL, VERTICAL;

    /**
     *
     * @return
     */
    public static Orientation getRandomOrientation() {
        Random random = new Random();
        return values()[random.nextInt(2)];
    }
}
