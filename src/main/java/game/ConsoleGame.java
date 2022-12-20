package game;

import board.Board;
import constants.GAME_STATE;
import com.diogonunes.jcolor.*;
import constants.TILE_VECTOR_VALUE_INDEX;
import org.checkerframework.checker.units.qual.A;

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
    public static final AnsiFormat consoleOrangeText = new AnsiFormat(
            TEXT_COLOR(255, 165, 0), BOLD()
    );
    public static final AnsiFormat consoleBlueText = new AnsiFormat(
            TEXT_COLOR(0, 180, 255), BOLD()
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
        System.out.println(colorize(TILE_VECTOR_VALUE_INDEX.HONOUR_GREEN.getUnicodeRepresentation() +
                " Mahjong Game " + TILE_VECTOR_VALUE_INDEX.HONOUR_GREEN.getUnicodeRepresentation(), consoleGreenText));
        board.resetConsoleGame();
    }

    @Override
    public void setGameState(GAME_STATE gameState) {
        // game state does nothing to a console game
        throw new UnsupportedOperationException("Game State is not supported by Console Game");
    }
}
