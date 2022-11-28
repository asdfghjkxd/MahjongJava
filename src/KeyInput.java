import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {
    private final Handler handler;
    private final Game game;

    // bind handler to keyinput
    public KeyInput(Handler handler, Game game) {
        this.handler = handler;
        this.game = game;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        } else if (key == KeyEvent.VK_P) {
            if (game.getGameState() == Game.State.Game || game.getGameState() == Game.State.Pause) {
                game.setGameState(game.getGameState() == Game.State.Pause ? Game.State.Game : Game.State.Pause);
            }

            game.pauseGame();
        } else if (key == KeyEvent.VK_Q) {
            final JFrame p = new JFrame();

            int thing = JOptionPane.showConfirmDialog(p, "Are you sure you want to quit?");
            if (thing == 0) {
                if (game.getGameState() == Game.State.Game) {
                    game.setGameState(Game.State.Menu);
                    synchronized (game) {
                        game.resetGame();
                    }
                }
                p.setVisible(false);
            } else {
                p.setVisible(false);
            }
        }

        for (GameObject h: handler.objects) {
            if (h.getId() == ID.Player) {
                // key events for player, VK represents the keyboard keys
                if (key == KeyEvent.VK_W) {
                }
            }
        }


    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        // should correspond to the actions above
        for (GameObject h: handler.objects) {
            if (h.getId() == ID.Player) {
                // key events for player, VK represents the keyboard keys
                if (key == KeyEvent.VK_W) {
                }
            }
        }
    }
}
