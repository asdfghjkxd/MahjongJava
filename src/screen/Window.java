package screen;

import core.Game;

import javax.swing.*;
import java.awt.*;
import java.io.Serial;

public class Window extends Canvas {
    @Serial
    private static final long serialVersionUID = -8255319694373975038L;

    /**
     * Main method which creates the game window and starts the game
     * @param width             Integer, describes the width of the window
     * @param height            Integer, describes the height of the window
     * @param title             String, the title of the window
     * @param game              Runnable, game instance
     */
    public Window(int width, int height, String title, Game game) {
        JFrame frame = new JFrame(title);

        // set the frame sizes
        frame.setPreferredSize(new Dimension(width, height));
        frame.setMaximumSize(new Dimension(width, height));
        frame.setMinimumSize(new Dimension(width, height));

        // set the frame behaviour
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null); // starts at the middle
        frame.add(game);

        // display the frame
        frame.setVisible(true);
        game.start();
    }
}
