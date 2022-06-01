/*
 *
 *
 *
 *
 */

package battleship.view;

import battleship.model.BoardData;

import javax.swing.*;
import java.awt.*;

public class MatchPanel extends JPanel {
    private UserBoardGraphics userBoardGraphics;
    private ComputerBoardGraphics computerBoardGraphics;
    private JLabel userBoardLabel = new JLabel("User Board");
    private JLabel computerBoardLabel = new JLabel("Computer Board");
    private JButton quitButton = new JButton("Quit Button");

    /**
     *
     * @param userBoardData
     * @param computerBoardData
     */
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

    /**
     *
     * @return
     */
    public ComputerBoardGraphics getComputerBoardGraphics() {
        return computerBoardGraphics;
    }

    /**
     *
     * @return
     */
    public JButton getQuitButton() {
        return quitButton;
    }
}

