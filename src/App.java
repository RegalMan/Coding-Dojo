
class App {
    public static void main(final String[] args) {

        final GameBoard gameBoard = new GameBoard();
        new GameBoardDisplayer(gameBoard);

    }
}