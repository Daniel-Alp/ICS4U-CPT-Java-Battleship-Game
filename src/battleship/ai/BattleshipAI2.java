package battleship.ai;

import battleship.model.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class BattleshipAI2 {
    private ArrayList<Coordinate> validShots = new ArrayList<>();
    private ArrayList<Coordinate> suggestedShots = new ArrayList<>();
    private HashSet<Type> typesRemaining = new HashSet<>();
    private Coordinate prevShot;

    public void reset() {
        validShots.clear();
        for (int row = 0; row < 10; row++) {
            for (int column = 0; column < 10; column++) {
                validShots.add(new Coordinate(row, column));
            }
        }
        suggestedShots.clear();
        prevShot = null;
        typesRemaining.addAll(Arrays.asList(Type.values()));
    }

    public void update(boolean shotHit, Type lastTypeSunk) {
        if (shotHit) {
            for (Direction dir : Direction.values()) {
                Coordinate adjCoordinate = new Coordinate(prevShot.getRow() + dir.getRowOffset(), prevShot.getColumn() + dir.getColumnOffset());
                if (isValidShot(adjCoordinate)) {
                    suggestedShots.add(0, adjCoordinate);
                }
            }
        }
        if (typesRemaining.contains(lastTypeSunk)) {
            suggestedShots.clear();
        }
        typesRemaining.remove(lastTypeSunk);
    }

    public Coordinate getShot() {
        if (suggestedShots.size() > 0) {
            Coordinate suggestedShot = suggestedShots.remove(0);
            for (Coordinate shot : validShots) {
                if (shot.getRow() == suggestedShot.getRow() && shot.getColumn() == suggestedShot.getColumn()) {
                    validShots.remove(shot);
                    return prevShot = shot;
                }
            }
        } else {
            Coordinate middlemostShot = null;
            int maxScore = 0;
            for (Coordinate shot : validShots) {
                if (!isValidShot(shot)) {
                    continue;
                }
                int score = distanceToInvalidTile(shot, Direction.UP) * distanceToInvalidTile(shot, Direction.DOWN) + distanceToInvalidTile(shot, Direction.LEFT) * distanceToInvalidTile(shot, Direction.RIGHT);
                if (score > maxScore) {
                    middlemostShot = shot;
                    maxScore = score;
                }
            }
            validShots.remove(middlemostShot);
            return prevShot = middlemostShot;
        }
        return prevShot = validShots.remove(0);
    }

    private boolean isValidShot(Coordinate shot) {
        if (shot.getRow() < 0 || shot.getRow() >= 10 || shot.getColumn() < 0 || shot.getColumn() >= 10) {
            return false;
        }
        for (Coordinate validShot : validShots) {
            if (shot.getRow() == validShot.getRow() && shot.getColumn() == validShot.getColumn()) {
                return true;
            }
        }
        return false;
    }

    private int distanceToInvalidTile(Coordinate shot, Direction dir) {
        int row = shot.getRow();
        int column = shot.getColumn();
        int distance = 0;
        while (isValidShot(new Coordinate(row, column))) {
            row += dir.getRowOffset();
            column += dir.getColumnOffset();
            distance++;
        }
        return distance;
    }
}