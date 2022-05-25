package battleship.model;

import java.io.Serializable;

public class Stats implements Serializable {
    int userWins = 0;
    int computerWins = 0;

    public int getUserWins() {
        return userWins;
    }

    public int getComputerWins() {
        return computerWins;
    }

    public void increaseUserWins() {
        userWins++;
    }

    public void increaseComputerWins() {
        computerWins++;
    }
}