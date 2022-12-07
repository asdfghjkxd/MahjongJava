package screens;

import core.Game;

import java.awt.*;
import java.io.IOException;

public class Settings extends Screen{
    public Settings(Game game) {
        super(game);
    }

    @Override
    public void render(Graphics g) throws IOException {
        // setting the colour of the background
        g.setColor(new Color(0, 100, 0));
        g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);

        // draw the words and characters
        g.setFont(new Font("Arial", Font.BOLD, 60));
        g.setColor(Color.WHITE);
        g.fillRect((int) (Game.WIDTH / 2.70), (int) (Game.HEIGHT / 18), 300, 80);
        g.setColor(Color.BLACK);
        g.drawString("Settings", (int) (Game.WIDTH / 2.5), Game.HEIGHT / 8);

        // setting page

        g.setColor(Color.WHITE);
        g.fillRect(Game.WIDTH - 215, Game.HEIGHT - 175, 170, 80);
        g.setColor(Color.BLACK);
        g.drawString("Back", Game.WIDTH - 200, Game.HEIGHT - 115);
    }

    @Override
    public void tick() {
        super.tick();
    }

    @Override
    public int getStartingX() {
        return super.getStartingX();
    }

    @Override
    public int getStartingY() {
        return super.getStartingY();
    }

    @Override
    public int getMovingX() {
        return super.getMovingX();
    }

    @Override
    public int getMovingY() {
        return super.getMovingY();
    }

    @Override
    public void setStartingX(int x) {
        super.setStartingX(x);
    }

    @Override
    public void setStartingY(int y) {
        super.setStartingY(y);
    }

    @Override
    public void setMovingX(int x) {
        super.setMovingX(x);
    }

    @Override
    public void setMovingY(int y) {
        super.setMovingY(y);
    }
}
