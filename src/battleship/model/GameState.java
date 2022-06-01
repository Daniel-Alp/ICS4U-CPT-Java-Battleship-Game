/*
 * GameState.java
 *
 * Daniel Alp
 * ICS4U1
 * 2022-06-08
 */

package battleship.model;

public enum GameState {
    SETUP, MATCH, RESULT;
    private static GameState state;

    /**
     *
     * @return
     */
    public static GameState getState() {
        return state;
    }

    /**
     *
     * @param state
     */
    public static void setState(GameState state) {
        GameState.state = state;
    }
}
