package screen;

import base.TickRender;
import core.Game;
import core.Handler;
import objects.TileClass;

import java.awt.*;
import java.io.IOException;

public class HUD implements TickRender {
    private Handler handler;

    public HUD(Handler handler) {
        this.handler = handler;
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) throws IOException {
        g.setColor(new Color(0, 100, 0));
        g.fillRect(0, 0, this.handler.getGamePortWidth(), this.handler.getGamePortHeight());
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 16));
        g.setColor(Color.WHITE);
        g.fillRect(35, 20, 200,70);
        g.setColor(Color.BLACK);
        g.drawString("Player: " +
                (this.handler.pollPlayer() == null
                        ? "NULL"
                        : this.handler.pollPlayer().getName()), 40, 60);
        g.drawString("Direction: ⥁", 40, 80);
        g.drawString("Wind Direction: " + this.handler.getWindDirection(), 40, 40);

        g.setColor(Color.WHITE);
        g.fillRect(this.handler.getGamePortWidth() - 185, 20, 160,30);
        g.setColor(Color.BLACK);
        g.drawString("Current Score: " + this.handler.getBoardScore() + " 台",
                this.handler.getGamePortWidth() - 180, 40);

        g.drawImage(TileClass.queryImage("wind", this.handler.getWindDirection().toString().toLowerCase()),
                this.handler.getGamePortWidth() / 2 - 30, this.handler.getGamePortHeight() / 2 - 35,
                null);
    }
}
