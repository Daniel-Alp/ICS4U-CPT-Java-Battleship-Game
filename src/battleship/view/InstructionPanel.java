package battleship.view;

import javax.swing.*;
import java.awt.*;

public class InstructionPanel extends JPanel {
    private JButton menuButton = new JButton("To Menu");
    private JLabel instructionsLabel = new JLabel(
            "<html>RULES:<br/><html>"
    );

    public InstructionPanel () {
        setBounds(0, 0, 1280, 720);
        setBackground(new Color(194, 195, 199));
        setLayout(null);
        menuButton.setBounds(550, 420, 200, 60);
        add(menuButton);
        instructionsLabel.setBounds(300, 100, 680, 100);
        add(instructionsLabel);
    }

    public JButton getMenuButton() {
        return menuButton;
    }
}
