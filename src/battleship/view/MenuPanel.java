package battleship.view;

import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel {
    private JButton matchSetupButton = new JButton("PLAY GAME");
    private JButton instructionButton = new JButton("INSTRUCTIONS");

    public MenuPanel () {
        setBounds(0, 0, 1280, 720);
        setLayout(null);
        setBackground(new Color(194, 195, 199));
        setLayout(null);

        matchSetupButton.setBounds(515, 335, 250, 50);
        add(matchSetupButton);

        instructionButton.setBounds(515, 410, 250, 50);
        add(instructionButton);
    }

    public JButton getMatchSetupButton() {
        return matchSetupButton;
    }

    public JButton getInstructionButton() {
        return instructionButton;
    }
}
