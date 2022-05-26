package battleship.view;

import battleship.model.BoardData;

import javax.swing.*;
import java.awt.*;

public class MatchPanel extends JPanel {
    private final UserBoardGraphics userBoardGraphics;
    private final ComputerBoardGraphics computerBoardGraphics;
    private final JLabel userBoardLabel = new JLabel("User Board");
    private final JLabel computerBoardLabel = new JLabel("Computer Board");

    public MatchPanel(BoardData userBoardData, BoardData computerBoardData) {
        setBounds(0, 0, 1280, 720);
        setLayout(null);
        setBackground(new Color(194, 195, 199));

        userBoardLabel.setBounds(895, 25, 100, 60);
        add(userBoardLabel);

        computerBoardLabel.setBounds(285, 25, 100, 60);
        add(computerBoardLabel);

        userBoardGraphics = new UserBoardGraphics(userBoardData, 670, 85);
        add(userBoardGraphics);

        computerBoardGraphics = new ComputerBoardGraphics(computerBoardData, 60, 85);
        add(computerBoardGraphics);
    }

    public ComputerBoardGraphics getComputerBoardGraphics() {
        return computerBoardGraphics;
    }
}
