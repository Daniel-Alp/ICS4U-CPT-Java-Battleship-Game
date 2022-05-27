package battleship.view;

import battleship.model.BoardData;
import battleship.model.Stats;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Frame extends JFrame {
    private MenuPanel menuPanel;
    private InstructionPanel instructionPanel;
    private SetupPanel setupPanel;
    private MatchPanel matchPanel;
    private ResultPanel resultPanel;
    private JPanel cardPanel = new JPanel();
    private CardLayout cardLayout = new CardLayout();

    public Frame(BoardData userBoardData, BoardData computerBoardData, Stats stats) {
        setTitle("ICS4U1 CPT - Java Battleship");
        setSize(1280, 720);
        setResizable(false);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        cardPanel.setBounds(0, 0, 1280, 720);
        cardPanel.setLayout(cardLayout);
        add(cardPanel);

        SplashScreen splashScreen = new SplashScreen();
        cardPanel.add(splashScreen);
        cardLayout.addLayoutComponent(splashScreen, "splashscreen");

        menuPanel = new MenuPanel();
        menuPanel.getInstructionButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showPanel("instruction");
            }
        });
        cardLayout.addLayoutComponent(menuPanel, "menu");
        cardPanel.add(menuPanel);

        instructionPanel = new InstructionPanel();
        instructionPanel.getMatchSetupButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showPanel("setup");
            }
        });
        cardPanel.add(instructionPanel);
        cardLayout.addLayoutComponent(instructionPanel, "instruction");

        setupPanel = new SetupPanel(userBoardData);
        setupPanel.getInstructionButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int response = JOptionPane.showConfirmDialog(
                        Frame.this,
                        "Are you sure you want to exit setup? Progress will not be saved.",
                        "Exit setup",
                        JOptionPane.YES_NO_OPTION);
                if (response == JOptionPane.YES_OPTION) {
                    showPanel("instruction");
                }
            }
        });
        cardLayout.addLayoutComponent(setupPanel, "setup");
        cardPanel.add(setupPanel);

        matchPanel = new MatchPanel(userBoardData, computerBoardData);
        cardLayout.addLayoutComponent(matchPanel, "match");
        cardPanel.add(matchPanel);

        resultPanel = new ResultPanel(userBoardData, stats);
        resultPanel.getMenuButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showPanel("menu");
            }
        });
        cardPanel.add(resultPanel);
        cardLayout.addLayoutComponent(resultPanel, "result");

        setVisible(true);

        cardLayout.show(cardPanel, "splashscreen");
        splashScreen.fakeLoading();
        cardLayout.show(cardPanel, "menu");
    }

    public void showPanel(String panelName) {
        if (panelName.equals("result")) {
            Timer delayTimer = new Timer(2000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    cardLayout.show(cardPanel, "result");
                    resultPanel.showWinner();
                }
            });
            delayTimer.start();
            delayTimer.setRepeats(false);
        } else {
            cardLayout.show(cardPanel, panelName);
        }
    }

    public MenuPanel getMenuPanel() {
        return menuPanel;
    }

    public InstructionPanel getInstructionPanel() {
        return instructionPanel;
    }

    public SetupPanel getSetupPanel() {
        return setupPanel;
    }

    public MatchPanel getMatchPanel() {
        return matchPanel;
    }
}
