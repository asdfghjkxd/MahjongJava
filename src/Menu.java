import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Menu extends MouseAdapter {
    private Game game;
    private Handler handler;


    public Menu(Game g, Handler h) {
        super();
        this.game = g;
        this.handler = h;
    }
    @Override
    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();

        if (this.mouseOver(mx, my, 480, 400, 200, 70)) {
            game.setGameState(Game.State.Game);
        } else if (this.mouseOver(mx, my, 480, 515, 200, 70)) {
            System.exit(0);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e);
    }

    private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
        if (mx > x && mx < x + width) {
            return my > y && my < y + height;
        } else {
            return false;
        }
    }

    public void tick() {

    }

    public void render(Graphics g) {
        g.setColor(new Color(0, 100, 0));
        g.fillRect(0, 0, this.game.getWidth(), this.game.getHeight());
        g.setFont(new Font("Arial", Font.BOLD, 60));
        g.setColor(Color.WHITE);
        g.fillRect(423, 190, 320, 80);
        g.setColor(Color.BLACK);
        g.drawString("Mahjong", 458, 250);

        g.setFont(new Font("Arial", Font.BOLD, 40));
        g.setColor(Color.WHITE);
        g.fillRect(480, 400, 200, 70);
        g.fillRect(480, 515, 200, 70);

        g.setColor(Color.BLACK);
        g.drawRect(480, 400, 200, 70);
        g.drawRect(480, 515, 200, 70);
        g.drawString("Play", 540, 450);
        g.drawString("Quit", 540, 565);
    }
}
