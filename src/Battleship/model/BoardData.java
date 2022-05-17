package Battleship.model;

import java.util.ArrayList;

public class BoardData {
    public static final int BOARD_SIZE = 10;
    private ArrayList<Ship> ships = new ArrayList<>();
    private boolean[][] enemyShots = new boolean[BOARD_SIZE][BOARD_SIZE];

    public void reset() {
        ships.clear();
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int column = 0; column < BOARD_SIZE; column++) {
                enemyShots[row][column] = false;
            }
        }
    }

    public boolean isValidPlacement(int length, Orientation orientation, Coordinate coordinate) {
        if (orientation == Orientation.HORIZONTAL && coordinate.getColumn() + length > BOARD_SIZE ||
                orientation == Orientation.VERTICAL && coordinate.getRow() + length > BOARD_SIZE) {
            return false;
        }
        for (int segment = 0; segment < length; segment++) {
            Coordinate segmentCoordinates;
            if (orientation == Orientation.HORIZONTAL) {
                segmentCoordinates = new Coordinate(coordinate.getRow(), coordinate.getColumn() + segment);
            } else {
                segmentCoordinates = new Coordinate(coordinate.getRow() + segment, coordinate.getColumn());
            }
            for (Ship ship : ships) {
                if (ship.containsCoordinates(segmentCoordinates)) {
                    return false;
                }
            }
        }
        return true;
    }

    public void placeShip(Ship ship) {
        ships.add(ship);
    }

    public boolean isReady() {
        return ships.size() == 5;
    }

    public boolean getShotAt (Coordinate targetCoordinates) {
        int targetRow = targetCoordinates.getRow(), targetColumn = targetCoordinates.getColumn();
        if (enemyShots[targetRow][targetColumn]) {
            return false;
        }
        for (Ship ship : ships) {
            if (ship.containsCoordinates(targetCoordinates)) {
                ship.hit();
            }
        }
        enemyShots[targetRow][targetColumn] = true;
        return true;
    }

    public boolean fleetSunk() {
        for (Ship ship : ships) {
            if (!ship.isSunk()) {
                return false;
            }
        }
        return true;
    }

    public ArrayList<Ship> getShips() {
        return ships;
    }

    public boolean[][] getEnemyShots () {
        return enemyShots;
    }
}