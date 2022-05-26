package battleship.view;

import javax.swing.*;
import java.awt.*;

public class InstructionPanel extends JPanel {
    private JButton matchSetupButton = new JButton("PLAY GAME");
    private JLabel instructionsLabel = new JLabel(
            "<html>RULES:<br/><br/>" +
                    "Game objective:<br/>" +
                    "Be the first to sink all 5 of your opponent's ships<br/><br/>" +
                    "Preparation:<br/>" +
                    "Place each ship onto the board. Ships can only be placed horizontally or vertically. Ships cannot overlap<br/><br/>" +
                    "Battle:<br/>" +
                    "Players take turns firing at the opponent's board, and it is revealed whether the shot was a hit or miss. If a ship is sunk, the opponent reveals which ship was sunk.<html>"
    );

    public InstructionPanel() {
        setBounds(0, 0, 1280, 720);
        setLayout(null);
        setBackground(new Color(194, 195, 199));

        matchSetupButton.setBounds(550, 420, 200, 60);
        add(matchSetupButton);

        instructionsLabel.setBounds(300, 100, 680, 300);
        add(instructionsLabel);
    }

    public JButton getMatchSetupButton() {
        return matchSetupButton;
    }
}
