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
        }

        for (GameObject h: handler.objects) {
            if (h.getId() == ID.Player) {
                // key events for player, VK represents the keyboard keys
                if (key == KeyEvent.VK_W) {
                    System.out.println("played");
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
                    System.out.println("played");
                }
            }
        }
    }
}
