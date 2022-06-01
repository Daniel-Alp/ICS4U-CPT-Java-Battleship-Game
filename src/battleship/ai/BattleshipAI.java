/*
 * Main.java
 *
 * Daniel Alp
 * ICS4U1
 * 2022-06-08
 */

package battleship.ai;

import battleship.model.BoardData;
import battleship.model.Coordinate;
import battleship.model.Type;

import java.util.*;

public class BattleshipAI {
    private boolean[][] validShots = new boolean[BoardData.BOARD_SIZE][BoardData.BOARD_SIZE];
    private int[][] adjacencyArray = new int[][]{{0,1}, {1,0}, {0,-1}, {-1,0}};
    private Stack<Coordinate> targetingShots = new Stack<>();
    private HashSet<Type> typesRemaining = new HashSet<>();
    private Type shortestTypeRemaining;
    private Coordinate prevShot;
    private Random random = new Random();


    /**
     *
     */
    public void reset() {
        for (int row = 0; row < BoardData.BOARD_SIZE; row++) {
            for (int column = 0; column < BoardData.BOARD_SIZE; column++) {
                validShots[row][column] = true;
            }
        }
        targetingShots.clear();
        prevShot = null;
        typesRemaining.addAll(Arrays.asList(Type.values()));
        shortestTypeRemaining = Type.DESTROYER;
    }

    /**
     *
     * @param shotHit
     * @param typeSunk
     */
    public void update(boolean shotHit, Type typeSunk) {
        if (shotHit) {
            for (int[] adjacent : adjacencyArray) {
                Coordinate adjCoordinate = new Coordinate(prevShot.getRow() + adjacent[0], prevShot.getColumn() + adjacent[1]);
                if (adjCoordinate.getRow() < 0 || adjCoordinate.getRow() >= BoardData.BOARD_SIZE || adjCoordinate.getColumn() < 0 || adjCoordinate.getColumn() >= BoardData.BOARD_SIZE) {
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

    /**
     *
     * @return
     */
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
            for (int row = 0; row < BoardData.BOARD_SIZE; row++) {
                for (int column = 0; column < BoardData.BOARD_SIZE; column++) {
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