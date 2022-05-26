package battleship.view;

import battleship.model.BoardData;
import battleship.model.Ship;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ComputerBoardGraphics extends BoardGraphics {
    private Point crosshairPoint;
    private ImageIcon crosshairIcon = new ImageIcon("src\\res\\crosshair.png");

    public ComputerBoardGraphics(BoardData boardData, int x, int y) {
        super(boardData, x, y);

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
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
        for (Ship ship : boardData.getShips()) {
            if (!ship.isSunk()) {
                continue;
            }
            String shipImagePath = String.format("src\\res\\%s_%s.png", ship.getType(), ship.getOrientation()).toLowerCase();;
            new ImageIcon(shipImagePath).paintIcon(this, graphics2D, ship.getColumn() * TILE_SIZE, ship.getRow() * TILE_SIZE);
        }
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
        if (crosshairPoint != null) {
            crosshairIcon.paintIcon(this, graphics, crosshairPoint.x - 24, crosshairPoint.y - 24);
        }
    }
}
