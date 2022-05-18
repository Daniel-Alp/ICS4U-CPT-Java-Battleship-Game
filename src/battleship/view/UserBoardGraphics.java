package battleship.view;

import battleship.model.BoardData;
import battleship.model.Coordinate;
import battleship.model.Ship;

import java.awt.*;

public class UserBoardGraphics extends BoardGraphics {
    public UserBoardGraphics (BoardData boardData, int x, int y) {
        super(boardData, x, y);
    }


    @Override
    public void drawShips(Graphics2D graphics2D) {
        for (int row = 0; row < BoardData.BOARD_SIZE; row++) {
            for (int column = 0; column < BoardData.BOARD_SIZE; column++) {
                Coordinate coordinate = new Coordinate(row, column);
                for (Ship ship : boardData.getShips()) {
                    if (ship.containsCoordinates(coordinate)) {
                        graphics2D.fillRect(column * TILE_SIZE, row * TILE_SIZE, TILE_SIZE, TILE_SIZE);
                        break;
                    }
                }
            }
        }
    }
}