package battleship.view;

import battleship.model.BoardData;
import battleship.model.Stats;

import javax.swing.*;
import java.awt.*;

public class ResultPanel extends JPanel {
    private final BoardData userBoardData;
    private final Stats stats;
    private final JLabel winStats = new JLabel();
    private final JLabel winner = new JLabel();
    private final JButton menuButton = new JButton("To Menu");

    ResultPanel(BoardData userBoardData, Stats stats) {
        setBounds(0, 0, 1280, 720);
        setBackground(new Color(194, 195, 199));
        setLayout(null);

        winner.setBounds(540, 340, 200, 40);
        add(winner);

        winStats.setBounds(400, 380, 480, 40);
        add(winStats);

        menuButton.setBounds(550, 420, 200, 60);
        add(menuButton);

        this.userBoardData = userBoardData;
        this.stats = stats;
    }

    public void showWinner() {
        if (userBoardData.fleetSunk()) {
            winner.setText("COMPUTER WON");
        } else {
            winner.setText("USER WON");
        }
        winStats.setText(String.format("<html>USER WINS: %d<br/>COMPUTER WINS: %d<html>", stats.getUserWins(), stats.getComputerWins()));
    }

    public JButton getMenuButton() {
        return menuButton;
    }
}
