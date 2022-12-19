package game;

import board.Board;
import constants.GAME_STATE;
import com.diogonunes.jcolor.*;
import static com.diogonunes.jcolor.Attribute.*;
import static com.diogonunes.jcolor.Ansi.colorize;

import javax.swing.*;

public class ConsoleGame extends Game implements Runnable {
    private Board board;
    public static final  AnsiFormat consoleGreenText = new AnsiFormat(
        GREEN_TEXT(), BOLD()
    );
    public static final  AnsiFormat consoleWhiteText = new AnsiFormat(
            WHITE_TEXT(), BOLD()
    );
    public static final  AnsiFormat consoleRedText = new AnsiFormat(
            RED_TEXT(), BOLD()
    );

    public static void main(String[] args) {
        new ConsoleGame();
    }

    public ConsoleGame() {
        board = new Board(this);
        start();
    }

    // Functions necessary for running the game
    public synchronized void start() {
        gameThread = new Thread(this);
        gameThread.start();
        running = true;
    }

    public synchronized void stop() {
        try {
            gameThread.join();
            running = false;
            System.exit(0);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    @Override
    public void run() {
        System.out.println(colorize("Mahjong", consoleGreenText));
        board.resetConsoleGame();

        while (true) {
            // Player player = board.getCurrentPlayer();
            break;
        }

        stop();
    }

    public int pollForTileRemoved() {
        return -1;
    }

    @Override
    public void setGameState(GAME_STATE gameState) {
        // game state does nothing to a console game
    }
}
