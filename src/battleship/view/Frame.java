package battleship.view;

import battleship.model.BoardData;
import battleship.model.GameState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Frame extends JFrame {
    private SetupPanel setupPanel;
    private MatchPanel matchPanel;
    private CardLayout cardLayout = new CardLayout();

    public Frame (BoardData userBoardData, BoardData computerBoardData) {
        setTitle("ICS4U1 CPT - Java Battleship");
        setSize(1280,720);
        setResizable(false);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JPanel cardPanel = new JPanel();
        cardPanel.setBounds(0, 0, 1280, 720);
        cardPanel.setLayout(cardLayout);
        add(cardPanel);

        setupPanel = new SetupPanel(userBoardData);
        cardLayout.addLayoutComponent(setupPanel, "setup");
        cardPanel.add(setupPanel);

        matchPanel = new MatchPanel(userBoardData, computerBoardData);
        cardLayout.addLayoutComponent(matchPanel, "match");
        cardPanel.add(matchPanel);

        getSetupPanel().getStartMatchButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (userBoardData.isReady()) {
                    GameState.setState(GameState.USER_TURN);
                    cardLayout.show(cardPanel, "match");
                }
            }
        });
        setVisible(true);
    }

    public SetupPanel getSetupPanel() {
        return setupPanel;
    }

    public MatchPanel getMatchPanel() {
        return matchPanel;
    }
}
