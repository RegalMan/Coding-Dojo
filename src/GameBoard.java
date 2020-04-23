public class GameBoard implements IBoardGame {
    // Public constant
    public static final int ROWS = 3;
    public static final int COLS = 3;

    // Private property
    private Slot[][] slots;
    private GameState currentGameState;

    public GameBoard() {
        init();
    }

    private void init() {
        this.slots = new Slot[ROWS][COLS];
        this.currentGameState = GameState.O_TURN;
    }

    /**
     * @param currentGameState the currentGameState to set
     */
    public void setCurrentGameState(GameState currentGameState) {
        this.currentGameState = currentGameState;
    }

    /**
     * @return the currentGameState
     */
    public GameState getCurrentGameState() {
        return currentGameState;
    }

    /**
     * @return the slots
     */
    public Slot[][] getSlots() {
        return slots;
    }

    public void resetGame() {
        init();
    }

    @Override
    public GameState getWinner() {
        Mark allMark[] = { Mark.O, Mark.X };
        int countMark;
        for (Mark mark : allMark) {
            for (int i = 0; i < ROWS; i++) {
                countMark = 0;
                for (int j = 0; j < COLS; j++) {
                    Slot slot = slots[i][j];
                    if (slot != null) {
                        if (slot.getMark() == mark) {
                            countMark++;
                        }
                    }
                }
                if (countMark == 3) {
                    return getWinnerFromMark(mark);
                }
            }
            for (int j = 0; j < COLS; j++) {
                countMark = 0;
                for (int i = 0; i < ROWS; i++) {
                    Slot slot = slots[i][j];
                    if (slot != null) {
                        if (slot.getMark() == mark) {
                            countMark++;
                        }
                    }
                }
                if (countMark == 3) {
                    return getWinnerFromMark(mark);
                }
            }

        }
        int countUseSlot = 0;
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {

                Slot slot = slots[i][j];
                if (slot != null) {
                    countUseSlot++;
                }

            }
        }

        if (countUseSlot == ROWS * COLS) {
            return GameState.DRAW;
        }

        return getCurrentGameState();
    }

    @Override
    public boolean takeSlot(boolean isX, int row, int col) {
        if (slots[row][col] == null) {
            Mark mark = Mark.O;
            if (isX) {
                mark = Mark.X;
            }

            slots[row][col] = new Slot(row, col, mark);
            return true;
        }

        return false;
    }

    private GameState getWinnerFromMark(Mark mark) {
        if (mark == Mark.X) {
            return GameState.X_WIN;
        }
        return GameState.O_WIN;
    }

}