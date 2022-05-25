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

    public Controller () {
        frame.getSetupPanel().getUserBoardGraphics().addMouseListener(new MouseAdapter() {
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

                userBoardData.placeShip(new Ship(userCurShipType, userCurShipOrientation, coordinate));
                userCurShipType = null;
                userCurShipOrientation = null;
                frame.repaint();
                frame.getSetupPanel().updateTypeOptions();
            }
        });
        for (JRadioButton typeOption : frame.getSetupPanel().getTypeOptions()) {
            typeOption.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    userCurShipType = Type.valueOf(typeOption.getName());
                }
            });
        }
        for (JRadioButton orientationOption : frame.getSetupPanel().getOrientationOptions()) {
            orientationOption.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    userCurShipOrientation = Orientation.valueOf(orientationOption.getName());
                }
            });
        }
        frame.getSetupPanel().getResetButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                userBoardData.reset();
                frame.repaint();
            }
        });
        frame.getMatchPanel().getComputerBoardGraphics().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                //TODO Clean code and separate into individual chunks
                if (!SwingUtilities.isLeftMouseButton(mouseEvent) || GameState.getState() != GameState.MATCH) {
                    return;
                }
                Coordinate userTargetCoordinates = BoardGraphics.pointToBoardCoordinates(mouseEvent.getPoint());
                if (computerBoardData.getEnemyShots()[userTargetCoordinates.getRow()][userTargetCoordinates.getColumn()]) {
                    return;
                }
                computerBoardData.getShotAt(userTargetCoordinates);
                frame.repaint();
                if (computerBoardData.fleetSunk()) {
                    frame.repaint();
                    GameState.setState(GameState.RESULT);
                    statTracker.getStats().increaseUserWins();
                    statTracker.updateStatsFile();
                    frame.showPanel("result");
                    return;
                }
                frame.repaint();
                Coordinate computerTargetCoordinates = battleshipAI.getShot();
                battleshipAI.update(userBoardData.getShotAt(computerTargetCoordinates), userBoardData.getTypeSunk());
                if (userBoardData.fleetSunk()) {
                    GameState.setState(GameState.RESULT);
                    statTracker.getStats().increaseComputerWins();
                    statTracker.updateStatsFile();
                    frame.showPanel("result");
                }
                frame.repaint();
            }
        });
        frame.getMenuPanel().getMatchSetupButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                reset();
                frame.getSetupPanel().resetTypeOptions();
                frame.showPanel("setup");
            }
        });
        frame.getSetupPanel().getMatchStartButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (userBoardData.isReady()) {
                    frame.showPanel("match");
                    GameState.setState(GameState.MATCH);
                }
            }
        });
    }

    public void reset() {
        userBoardData.reset();
        computerBoardData.reset();
        battleshipAI.reset();
        for (Type type : Type.values()) {
            placeComputerShip(type, Orientation.getRandomOrientation());
        }
    }

    private void placeComputerShip (Type type, Orientation orientation) {
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
        computerBoardData.placeShip(new Ship(type, orientation, validCoordinates.get(0)));
    }
}
