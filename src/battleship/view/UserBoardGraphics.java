package battleship.view;

import battleship.model.BoardData;
import battleship.model.Ship;

import javax.swing.*;
import java.awt.*;

public class UserBoardGraphics extends BoardGraphics {
    public UserBoardGraphics(BoardData boardData, int x, int y) {
        super(boardData, x, y);
    }

    @Override
    public void drawShips(Graphics2D graphics2D) {
        for (Ship ship : boardData.getShips()) {
            String shipImagePath = String.format("src\\res\\%s_%s.png", ship.getType(), ship.getOrientation()).toLowerCase();
            new ImageIcon(shipImagePath).paintIcon(this, graphics2D, ship.getColumn() * TILE_SIZE, ship.getRow() * TILE_SIZE);
        }
    }   
}