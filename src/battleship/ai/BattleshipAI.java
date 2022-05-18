package battleship.ai;

import battleship.model.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class BattleshipAI {
    private boolean[][] validShots = new boolean[10][10];
    private ArrayList<Coordinate> potentialShots = new ArrayList<>();
    private HashSet<Type> typesSunk = new HashSet<>();
    private ArrayList<Coordinate> suggestedShots = new ArrayList<>();
    private int shortestShipLength;
    private Coordinate previousShot;

    public void reset() {
        for (int row = 0; row < 10; row++) {
            for (int column = 0; column < 10; column++) {
                validShots[row][column] = true;
                potentialShots.add(new Coordinate(row, column));
            }
        }
        suggestedShots.clear();
        previousShot = null;
    }

    public void update(boolean shotHit, Type lastTypeSunk) {
        if (shotHit) {
            for (Direction direction : Direction.values()) {
                Coordinate adjacentCoordinate = new Coordinate(previousShot.getRow() + direction.getRowOffset(), previousShot.getColumn() + direction.getColumnOffset());
                if (adjacentCoordinate.getRow() >= 0 && adjacentCoordinate.getRow() < 10
                        && adjacentCoordinate.getColumn() >= 0 && adjacentCoordinate.getColumn() < 10
                        && validShots[adjacentCoordinate.getRow()][adjacentCoordinate.getColumn()]) {
                    suggestedShots.add(adjacentCoordinate);
                }
            }
        }
        if (!typesSunk.contains(lastTypeSunk)) {
            suggestedShots.clear();
            typesSunk.add(lastTypeSunk);
        }
    }

    public Coordinate getShot() {
        if (suggestedShots.size() > 0) {
            for (Coordinate suggestedShot : suggestedShots) {
                if (validShots[suggestedShot.getRow()][suggestedShot.getColumn()]) {
                    validShots[suggestedShot.getRow()][suggestedShot.getColumn()] = false;
                    return previousShot = suggestedShot;
                }
            }
        } else {
            Collections.shuffle(potentialShots);
            shortestShipLength = 5;
            for (Type type : Type.values()) {
                if (!typesSunk.contains(type) && type.getLength() < shortestShipLength) {
                    shortestShipLength = type.getLength();
                }
            }
            for (Coordinate coordinate : potentialShots) {
                if (validShots[coordinate.getRow()][coordinate.getColumn()] && (coordinate.getRow() + coordinate.getColumn()) % shortestShipLength == 0) {
                    validShots[coordinate.getRow()][coordinate.getColumn()] = false;
                    return previousShot = coordinate;
                }
            }
        }
        return null;
    }
}