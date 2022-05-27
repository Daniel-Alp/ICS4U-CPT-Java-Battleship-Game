package battleship.view;

import battleship.model.BoardData;

import javax.swing.*;
import java.awt.*;

public class MatchPanel extends JPanel {
    private final UserBoardGraphics userBoardGraphics;
    private final ComputerBoardGraphics computerBoardGraphics;
    private final JLabel userBoardLabel = new JLabel("User Board");
    private final JLabel computerBoardLabel = new JLabel("Computer Board");
    private JButton quitButton = new JButton("Quit Button");

    public MatchPanel(BoardData userBoardData, BoardData computerBoardData) {
        setBounds(0, 0, 1280, 720);
        setLayout(null);
        setBackground(new Color(194, 195, 199));

        userBoardLabel.setBounds(895, 25, 100, 60);
        add(userBoardLabel);

        computerBoardLabel.setBounds(285, 25, 100, 60);
        add(computerBoardLabel);

        quitButton.setBounds(0, 0, 100, 40);
        add(quitButton);

        userBoardGraphics = new UserBoardGraphics(userBoardData, 670, 85);
        add(userBoardGraphics);

        computerBoardGraphics = new ComputerBoardGraphics(computerBoardData, 60, 85);
        add(computerBoardGraphics);
    }

    public ComputerBoardGraphics getComputerBoardGraphics() {
        return computerBoardGraphics;
    }

    public JButton getQuitButton() {return quitButton;}
}

