package battleship.view;

import battleship.model.BoardData;
import battleship.model.Coordinate;
import battleship.model.Ship;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ComputerBoardGraphics extends BoardGraphics {
    private Point crosshairPoint;
    private ImageIcon crosshairIcon = new ImageIcon("src\\res\\crosshair.png");
    public ComputerBoardGraphics (BoardData boardData, int x, int y) {
        super(boardData, x, y);
        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                Coordinate crosshairCoordinates = pointToBoardCoordinates(e.getPoint());
                crosshairPoint = e.getPoint();
                repaint();
            }
        });
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                crosshairPoint = null;
                repaint();
            }
        });
    }

    @Override
    protected void drawShips(Graphics2D graphics2D) {
        for (int row = 0; row < BoardData.BOARD_SIZE; row++) {
            for (int column = 0; column < BoardData.BOARD_SIZE; column++) {
                Coordinate coordinate = new Coordinate(row, column);
                for (Ship ship : boardData.getShips()) {
                    if (ship.containsCoordinates(coordinate) && ship.isSunk()) {
                        graphics2D.setColor(new Color(95, 87, 79));
                        graphics2D.fillRect(column * TILE_SIZE, row * TILE_SIZE, TILE_SIZE, TILE_SIZE);
                        break;
                    }
                    //TODO reveal remaining ships
//                    if (GameState.getState() == GameState.RESULT) {
//                        graphics2D.setColor(Color.red);
//                        graphics2D.drawRect(column * TILE_SIZE, row * TILE_SIZE, TILE_SIZE, TILE_SIZE);
//                    }
                }
            }
        }
    }

    @Override
    public void paint(Graphics graphics){
        super.paint(graphics);
        if (crosshairPoint != null) {
            crosshairIcon.paintIcon(this, graphics, crosshairPoint.x - 24, crosshairPoint.y - 24);
        }
    }
}
