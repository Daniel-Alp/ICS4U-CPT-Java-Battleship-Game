package Battleship.view;

import Battleship.model.BoardData;
import Battleship.model.Coordinate;
import Battleship.model.Ship;

import java.awt.*;

public class ComputerBoardGraphics extends BoardGraphics{
    public ComputerBoardGraphics (BoardData boardData, int x, int y) {
        super(boardData, x, y);
    }

    @Override
    protected void drawShips(Graphics2D graphics2D) {
        for (int row = 0; row < BoardData.BOARD_SIZE; row++) {
            for (int column = 0; column < BoardData.BOARD_SIZE; column++) {
                Coordinate coordinate = new Coordinate(row, column);
                for (Ship ship : boardData.getShips()) {
                    if (ship.containsCoordinates(coordinate) && ship.isSunk()) {
                        graphics2D.fillRect(column * TILE_SIZE, row * TILE_SIZE, TILE_SIZE, TILE_SIZE);
                        break;
                    }
                }
            }
        }
    }
}
