import java.awt.*;

public class Pause {
    private Game game;
    private Handler handler;

    public Pause(Game game, Handler handler) {
        this.game = game;
        this.handler = handler;
    }

    public final void tick() {

    }

    public final void render(Graphics g) {
        g.setColor(new Color(0, 80, 0));
        g.fillRect(0, 0, game.getWidth(), game.getHeight());
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 40));
        g.drawString("PAUSED", 500, 400);
    }
}
