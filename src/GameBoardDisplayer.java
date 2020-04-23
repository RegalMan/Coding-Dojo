import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GameBoardDisplayer extends MouseAdapter {

    public static final int WIDTH = 500;
    public static final int HEIGHT = 500;
    private GameBoard gameBoard;

    private JFrame window;
    private JPanel markTable;
    private JButton[][] oxButtons;

    public GameBoardDisplayer(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
        init();
    }

    public void init() {
        window = new JFrame("Tic Tac Toe");
        window.setSize(WIDTH, HEIGHT);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(null);

        markTable = new JPanel();
        markTable.setSize(WIDTH, (int) ((HEIGHT * 0.9)));
        markTable.setBackground(Color.red);
        markTable.setLayout(new GridLayout(GameBoard.ROWS, GameBoard.COLS));

        oxButtons = new JButton[GameBoard.ROWS][GameBoard.COLS];
        for (int i = 0; i < GameBoard.ROWS; i++) {
            for (int j = 0; j < GameBoard.COLS; j++) {
                oxButtons[i][j] = new JButton();
                oxButtons[i][j].addMouseListener(this);
                markTable.add(oxButtons[i][j]);
            }
        }

        window.add(markTable);

    }

    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);

        for (int i = 0; i < GameBoard.ROWS; i++) {
            for (int j = 0; j < GameBoard.COLS; j++) {
                JButton jB = (JButton) e.getSource();
                if (jB == oxButtons[i][j]) {
                    if (gameBoard.getCurrentGameState() == GameState.O_TURN) {
                        if (gameBoard.takeSlot(false, i, j)) {
                            jB.setText(Mark.O.toString());
                            gameBoard.setCurrentGameState(GameState.X_TURN);
                        }

                    } else if (gameBoard.getCurrentGameState() == GameState.X_TURN) {
                        if (gameBoard.takeSlot(true, i, j)) {
                            jB.setText(Mark.X.toString());
                            gameBoard.setCurrentGameState(GameState.O_TURN);
                        }
                    }

                    switch (gameBoard.getWinner()) {
                        case DRAW:
                            JOptionPane.showMessageDialog(window, "Draw!");
                            clearButton();
                            break;
                        case O_WIN:
                            displayWinner("O");
                            break;
                        case X_WIN:
                            displayWinner("X");
                            break;
                        default:
                            break;
                    }
                }
            }
        }
    }

    private void displayWinner(String message) {
        JOptionPane.showMessageDialog(window, String.format("Player %s is the winner!!", message), "",
                JOptionPane.INFORMATION_MESSAGE);
        clearButton();
    }

    private void clearButton() {
        for (JButton[] jBtnRow : oxButtons) {
            for (JButton jBtn : jBtnRow) {
                jBtn.setText("");
            }
        }
        gameBoard.resetGame();
    }

}