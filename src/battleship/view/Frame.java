package battleship.view;

import battleship.model.BoardData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Frame extends JFrame {
    private MenuPanel menuPanel;
    private SetupPanel setupPanel;
    private MatchPanel matchPanel;
    private JPanel cardPanel = new JPanel();
    private CardLayout cardLayout = new CardLayout();

    public Frame (BoardData userBoardData, BoardData computerBoardData) {
        setTitle("ICS4U1 CPT - Java Battleship");
        setSize(1280,720);
        setResizable(false);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        cardPanel.setBounds(0, 0, 1280, 720);
        cardPanel.setLayout(cardLayout);
        add(cardPanel);

        menuPanel = new MenuPanel();
        cardLayout.addLayoutComponent(menuPanel, "menu");
        cardPanel.add(menuPanel);

        setupPanel = new SetupPanel(userBoardData);
        setupPanel.getMenuButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmMenuReturn();
            }
        });
        cardLayout.addLayoutComponent(setupPanel, "setup");
        cardPanel.add(setupPanel);

        matchPanel = new MatchPanel(userBoardData, computerBoardData);
        matchPanel.getMenuButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmMenuReturn();
            }
        });
        cardLayout.addLayoutComponent(matchPanel, "match");
        cardPanel.add(matchPanel);

        cardLayout.show(cardPanel, "menu");
        setVisible(true);
    }

    public MenuPanel getMenuPanel () {
        return menuPanel;
    }

    public SetupPanel getSetupPanel() {
        return setupPanel;
    }

    public MatchPanel getMatchPanel() {
        return matchPanel;
    }

    public void showPanel(String name) {
        cardLayout.show(cardPanel, name);
    }

    private void confirmMenuReturn() {
        int response = JOptionPane.showConfirmDialog(
                Frame.this,
                "Are you sure you want to return to menu? Progress will not be saved.",
                "Return to menu",
                JOptionPane.YES_NO_OPTION);
        if (response == JOptionPane.YES_OPTION) {
            showPanel("menu");
        }
    }
}
