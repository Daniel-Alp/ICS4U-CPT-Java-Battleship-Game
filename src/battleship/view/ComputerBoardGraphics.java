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

    /**
     *
     * @param boardData
     * @param x
     * @param y
     */
    public ComputerBoardGraphics(BoardData boardData, int x, int y) {
        super(boardData, x, y);

        addMouseMotionListener(new MouseAdapter() {
            /**
             *
             * @param mouseEvent
             */
            @Override
            public void mouseMoved(MouseEvent mouseEvent) {
                crosshairPoint = mouseEvent.getPoint();
                repaint();
            }
        });

        addMouseListener(new MouseAdapter() {
            /**
             *
             * @param mouseEvent
             */
            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                crosshairPoint = null;
                repaint();
            }
        });
    }

    /**
     *
     * @param graphics2D
     */
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

    /**
     *
     * @param graphics
     */
    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
        if (crosshairPoint != null) {
            crosshairIcon.paintIcon(this, graphics, crosshairPoint.x - 24, crosshairPoint.y - 24);
        }
    }
}
