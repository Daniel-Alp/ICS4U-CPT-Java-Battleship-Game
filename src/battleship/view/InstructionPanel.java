/*
 *
 *
 *
 *
 */

package battleship.view;

import javax.swing.*;
import java.awt.*;

public class InstructionPanel extends JPanel {
    private JButton matchSetupButton = new JButton("PLAY GAME");
    private ImageIcon backgroundIcon = new ImageIcon("src\\res\\background.png");
    private JLabel instructionsLabel = new JLabel(
            "<html>RULES:<br/><br/>" +
                    "Game objective:<br/>" +
                    "Be the first to sink all 5 of your opponent's ships<br/><br/>" +
                    "Preparation:<br/>" +
                    "Place each ship onto the board. Ships can only be placed horizontally or vertically. Ships cannot overlap. To place a ship, select the type and orientation, then click on the board. Ships are spawned starting from the top left segment of the ship.<br/><br/>" +
                    "Battle:<br/>" +
                    "Players take turns firing at the opponent's board, and it is revealed whether the shot was a hit or miss. If a ship is sunk, the opponent reveals which ship was sunk.<html>"
    );

    /**
     *
     */
    public InstructionPanel() {
        setBounds(0, 0, 1280, 720);
        setLayout(null);

        matchSetupButton.setBounds(550, 480, 180, 60);
        add(matchSetupButton);

        instructionsLabel.setBounds(240, 0, 800, 540);
        instructionsLabel.setForeground(Color.WHITE);
        instructionsLabel.setFont(new Font("", Font.BOLD, 20));
        add(instructionsLabel);
    }

    /**
     *
     * @param graphics
     */
    @Override
    public void paintComponent(Graphics graphics) {
        backgroundIcon.paintIcon(this, graphics, 0, 0);
    }

    /**
     *
     * @return
     */
    public JButton getMatchSetupButton() {
        return matchSetupButton;
    }
}
