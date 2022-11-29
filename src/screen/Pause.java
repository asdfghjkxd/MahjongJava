package screen;

import base.TickRender;
import core.Handler;

import java.awt.*;
import java.io.IOException;

public class Pause implements TickRender {
    private Handler handler;

    public Pause(Handler handler) {
        this.handler = handler;
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) throws IOException {
        g.setColor(new Color(0, 80, 0));
        g.fillRect(0, 0, this.handler.getGamePortWidth(), this.handler.getGamePortHeight());
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 40));
        g.drawString("PAUSED", 500, 400);
    }
}
