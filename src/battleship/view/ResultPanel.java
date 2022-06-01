/*
 *
 *
 *
 *
 */

package battleship.view;

import battleship.model.BoardData;
import battleship.model.Stats;

import javax.swing.*;
import java.awt.*;

public class ResultPanel extends JPanel {
    private BoardData userBoardData;
    private Stats stats;
    private JLabel winStats = new JLabel();
    private JLabel winner = new JLabel();
    private JButton menuButton = new JButton("To Menu");

    /**
     *
     * @param userBoardData
     * @param stats
     */
    public ResultPanel(BoardData userBoardData, Stats stats) {
        setBounds(0, 0, 1280, 720);
        setBackground(new Color(194, 195, 199));
        setLayout(null);

        winner.setBounds(0, 0, 1280, 100);
        winner.setHorizontalAlignment(SwingConstants.CENTER);
        winner.setFont(new Font("", Font.BOLD, 50));
        add(winner);

        winStats.setBounds(400, 380, 480, 40);
        add(winStats);

        menuButton.setBounds(550, 420, 200, 60);
        add(menuButton);

        this.userBoardData = userBoardData;
        this.stats = stats;
    }

    /**
     *
     */
    public void showWinner() {
        if (userBoardData.fleetSunk()) {
            winner.setText("COMPUTER WON");
        } else {
            winner.setText("USER WON");
        }
        winStats.setText(String.format("<html>USER WINS: %d<br/>COMPUTER WINS: %d<html>", stats.getUserWins(), stats.getComputerWins()));
    }

    /**
     *
     * @return
     */
    public JButton getMenuButton() {
        return menuButton;
    }
}
