package screen;

import base.TickRender;
import core.Game;
import core.Handler;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class MainMenu extends MouseAdapter implements TickRender {
    private final Handler handler;

    public MainMenu(Handler handler) {
        super();
        this.handler = handler;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();

        if (this.mouseOver(mx, my, 480, 400, 200, 70)) {
            handler.setGameState(Game.GAME_STATE.MENU);
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

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) throws IOException {
        g.setColor(new Color(0, 100, 0));
        g.fillRect(0, 0, this.handler.getGamePortWidth(), this.handler.getGamePortHeight());
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
