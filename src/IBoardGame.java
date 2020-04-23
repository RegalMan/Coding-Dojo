/**
 * IBoardGame
 */
public interface IBoardGame {

    GameState getWinner();

    boolean takeSlot(boolean isX, int row, int col);
}