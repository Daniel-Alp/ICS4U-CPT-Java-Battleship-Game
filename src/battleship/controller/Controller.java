/*
 * Main.java
 *
 * Daniel Alp
 * ICS4U1
 * 2022-06-08
 */

package battleship.controller;

import battleship.ai.BattleshipAI;
import battleship.model.*;
import battleship.view.BoardGraphics;
import battleship.view.Frame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;

public class Controller {
    private BoardData userBoardData = new BoardData();
    private BoardData computerBoardData = new BoardData();
    private StatTracker statTracker = new StatTracker();
    private Frame frame = new Frame(userBoardData, computerBoardData, statTracker.getStats());
    private Orientation userCurShipOrientation = null;
    private Type userCurShipType = null;
    private BattleshipAI battleshipAI = new BattleshipAI();

    /**
     *
     */
    public Controller() {
        frame.getSetupPanel().getUserBoardGraphics().addMouseListener(new MouseAdapter() {
            /**
             *
             * @param mouseEvent
             */
            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                if (!SwingUtilities.isLeftMouseButton(mouseEvent)) {
                    return;
                }
                Coordinate coordinate = BoardGraphics.pointToBoardCoordinates(mouseEvent.getPoint());
                if (userCurShipType == null || userCurShipOrientation == null) {
                    return;
                }
                if (!userBoardData.isValidPlacement(userCurShipType.getLength(), userCurShipOrientation, coordinate)) {
                    return;
                }
                userBoardData.getShips().add(new Ship(userCurShipType, userCurShipOrientation, coordinate));
                userCurShipType = null;
                userCurShipOrientation = null;
                frame.repaint();
                frame.getSetupPanel().updateTypeOptions();
            }
        });
        for (JRadioButton typeOption : frame.getSetupPanel().getTypeOptions()) {
            typeOption.addActionListener(new ActionListener() {
                /**
                 *
                 * @param actionEvent
                 */
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    userCurShipType = Type.valueOf(typeOption.getName());
                }
            });
        }
        for (JRadioButton orientationOption : frame.getSetupPanel().getOrientationOptions()) {
            orientationOption.addActionListener(new ActionListener() {
                /**
                 *
                 * @param actionEvent
                 */
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    userCurShipOrientation = Orientation.valueOf(orientationOption.getName());
                }
            });
        }
        frame.getSetupPanel().getResetButton().addActionListener(new ActionListener() {
            /**
             *
             * @param actionEvent
             */
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                userBoardData.reset();
                frame.repaint();
            }
        });
        frame.getMatchPanel().getComputerBoardGraphics().addMouseListener(new MouseAdapter() {
            /**
             *
             * @param mouseEvent
             */
            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                if (!SwingUtilities.isLeftMouseButton(mouseEvent)) {
                    return;
                }
                Coordinate userTargetCoordinates = BoardGraphics.pointToBoardCoordinates(mouseEvent.getPoint());
                if (computerBoardData.getEnemyShots()[userTargetCoordinates.getRow()][userTargetCoordinates.getColumn()]) {
                    return;
                }
                if (GameState.getState() == GameState.MATCH) {
                    nextMove(computerBoardData, userTargetCoordinates);
                }
                if (GameState.getState() == GameState.MATCH) {
                    nextMove(userBoardData, battleshipAI.getShot());
                }
                frame.repaint();
            }
        });
        frame.getMenuPanel().getMatchSetupButton().addActionListener(new ActionListener() {
            /**
             *
             * @param actionEvent
             */
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                reset();
                frame.getSetupPanel().resetTypeOptions();
                frame.showPanel("setup");
            }
        });
        frame.getInstructionPanel().getMatchSetupButton().addActionListener(new ActionListener() {
            /**
             *
             * @param actionEvent
             */
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                reset();
                frame.getSetupPanel().resetTypeOptions();
                frame.showPanel("setup");
            }
        });
        frame.getSetupPanel().getMatchStartButton().addActionListener(new ActionListener() {
            /**
             *
             * @param actionEvent
             */
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (userBoardData.isReady()) {
                    frame.showPanel("match");
                    GameState.setState(GameState.MATCH);
                }
            }
        });
    }

    /**
     *
     */
    private void reset() {
        userBoardData.reset();
        computerBoardData.reset();
        battleshipAI.reset();
        for (Type type : Type.values()) {
            placeComputerShip(type, Orientation.getRandomOrientation());
        }
    }

    /**
     *
     * @param type
     * @param orientation
     */
    private void placeComputerShip(Type type, Orientation orientation) {
        ArrayList<Coordinate> validCoordinates = new ArrayList<>();
        for (int row = 0; row < BoardData.BOARD_SIZE; row++) {
            for (int column = 0; column < BoardData.BOARD_SIZE; column++) {
                Coordinate coordinates = new Coordinate(row, column);
                if (computerBoardData.isValidPlacement(type.getLength(), orientation, coordinates)) {
                    validCoordinates.add(coordinates);
                }
            }
        }
        Collections.shuffle(validCoordinates);
        computerBoardData.getShips().add(new Ship(type, orientation, validCoordinates.get(0)));
    }

    /**
     *
     * @param targetBoardData
     * @param targetCoordinates
     */
    private void nextMove(BoardData targetBoardData, Coordinate targetCoordinates) {
        if (targetBoardData == userBoardData) {
            battleshipAI.update(targetBoardData.getFiredAt(targetCoordinates), targetBoardData.getTypeSunk());
        } else {
            targetBoardData.getFiredAt(targetCoordinates);
        }
        if (targetBoardData.fleetSunk()) {
            GameState.setState(GameState.RESULT);
            if (targetBoardData == userBoardData) {
                statTracker.getStats().increaseComputerWins();
            } else {
                statTracker.getStats().increaseUserWins();
            }
            statTracker.updateStatsFile();
            frame.showPanel("result");
        }
    }
}
