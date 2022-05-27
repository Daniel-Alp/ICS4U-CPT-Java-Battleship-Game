package battleship.view;

import battleship.model.BoardData;
import battleship.model.Type;

import javax.swing.*;
import java.awt.*;

public class MatchPanel extends JPanel {
    private final UserBoardGraphics userBoardGraphics;
    private final ComputerBoardGraphics computerBoardGraphics;
    private final JLabel userBoardLabel = new JLabel("User Board");
    private final JLabel computerBoardLabel = new JLabel("Computer Board");
    private JButton quitButton = new JButton("Quit Button");

    private JLabel infoText = new JLabel();

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

        infoText.setBounds(300, 100, 680, 300);
        add(infoText);
    }

    public void setInfoText(boolean userShotHit, boolean computerShotHit, Type userTypeShip, Type computerTypeShip){

        String userText = "";
        String computerText = "";

        if(userShotHit){
            userText += "You hit the enemy ";
            if(userTypeShip != null){
                userText += "and sunk their "+userTypeShip.toString().toLowerCase();
            }
        }else{
            userText += "You missed the enemy ";
        }

        if(computerShotHit){
            computerText += "Enemy hit your ship ";
            if(computerTypeShip != null){
                computerText += "and sunk your "+userTypeShip.toString().toLowerCase();
            }
        }else{
            computerText += "Enemy hit your ship ";
        }

        infoText.setText(String.format("<html>%s<br/>%s<html>", userText, computerText));
    }

    public ComputerBoardGraphics getComputerBoardGraphics() {
        return computerBoardGraphics;
    }

    public JButton getQuitButton() {return quitButton;}

}

