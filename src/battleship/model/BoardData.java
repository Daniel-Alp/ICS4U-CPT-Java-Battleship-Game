package battleship.model;

import java.util.ArrayList;

public class BoardData {
    public static final int BOARD_SIZE = 10;
    private final ArrayList<Ship> ships = new ArrayList<>();
    private final boolean[][] enemyShots = new boolean[BOARD_SIZE][BOARD_SIZE];
    private Type typeSunk;

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
        return ships.size() == Type.values().length;
    }

    public boolean isValidShot(Coordinate target) {
        return !enemyShots[target.getRow()][target.getColumn()];
    }

    public boolean getFiredAt(Coordinate targetCoordinates) {
        enemyShots[targetCoordinates.getRow()][targetCoordinates.getColumn()] = true;
        typeSunk = null;
        for (Ship ship : ships) {
            if (ship.containsCoordinates(targetCoordinates)) {
                ship.hit();
                if (ship.isSunk()) {
                    typeSunk = ship.getType();
                }
                return true;
            }
        }
        return false;
    }

    public boolean fleetSunk() {
        for (Ship ship : ships) {
            if (!ship.isSunk()) {
                return false;
            }
        }
        return true;
    }

    public Type getTypeSunk() {
        return typeSunk;
    }

    public ArrayList<Ship> getShips() {
        return ships;
    }

    public boolean[][] getEnemyShots() {
        return enemyShots;
    }
}