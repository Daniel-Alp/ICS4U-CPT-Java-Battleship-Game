package Battleship.model;

import java.util.Random;

public enum Orientation {
    HORIZONTAL, VERTICAL;

    public static Orientation getRandomOrientation() {
        Random random = new Random();
        return values()[random.nextInt(2)];
    }
}
