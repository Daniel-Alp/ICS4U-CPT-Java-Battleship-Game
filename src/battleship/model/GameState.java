package battleship.model;

public enum GameState {
    SETUP, USER_TURN, COMPUTER_TURN, RESULT;
    private static GameState state;

    public static GameState getState() {
        return state;
    }

    public static void setState(GameState state) {
        GameState.state = state;
    }
}
