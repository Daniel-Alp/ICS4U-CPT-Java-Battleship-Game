package battleship.ai;

import battleship.model.*;

import java.util.*;

public class BattleshipAI {
    private boolean[][] validShots = new boolean[10][10];
    private Stack<Coordinate> targetingShots = new Stack<>();
    private HashSet<Type> typesRemaining = new HashSet<>();
    private Type shortestTypeRemaining;
    private Coordinate prevShot;
    private Random random = new Random();

    public void reset() {
        for (int row = 0; row < 10; row++) {
            for (int column = 0; column < 10; column++) {
                validShots[row][column] = true;
            }
        }
        targetingShots.clear();
        prevShot = null;
        typesRemaining.addAll(Arrays.asList(Type.values()));
        shortestTypeRemaining = Type.DESTROYER;
    }

    public void update(boolean shotHit, Type typeSunk) {
        if (shotHit) {
            for (Direction dir : Direction.values()) {
                Coordinate adjCoordinate = new Coordinate(prevShot.getRow() + dir.getRowChange(), prevShot.getColumn() + dir.getColumnChange());
                if (adjCoordinate.getRow() < 0 || adjCoordinate.getRow() >= 10 || adjCoordinate.getColumn() < 0 || adjCoordinate.getColumn() >= 10) {
                    continue;
                }
                if (!validShots[adjCoordinate.getRow()][adjCoordinate.getColumn()]) {
                    continue;
                }
                targetingShots.add(adjCoordinate);
            }
        }
        if (typeSunk != null) {
            shortestTypeRemaining = Type.CARRIER;
            for (Type typeRemaining : typesRemaining) {
                if (typeRemaining.getLength() < shortestTypeRemaining.getLength()) {
                    shortestTypeRemaining = typeRemaining;
                }
            }
            typesRemaining.remove(typeSunk);
        }
    }

    public Coordinate getShot() {
        Coordinate shot = null;
        while (targetingShots.size() > 0) {
            shot = targetingShots.pop();
            if (validShots[shot.getRow()][shot.getColumn()]) {
                break;
            }
        }
        if (shot == null) {
            ArrayList<Coordinate> huntingShots = new ArrayList<>();
            for (int row = 0; row < 10; row++) {
                for (int column = 0; column < 10; column++) {
                    if ((row + column) % shortestTypeRemaining.getLength() == 0 && validShots[row][column]) {
                        huntingShots.add(new Coordinate(row, column));
                    }
                }
            }
            shot = huntingShots.get(random.nextInt(huntingShots.size()));
        }
        validShots[shot.getRow()][shot.getColumn()] = false;
        return prevShot = shot;
    }
}