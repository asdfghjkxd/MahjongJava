package screens;

import constants.CONSTANTS;
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
        g.setColor(CONSTANTS.BACKGROUND_COLOUR);
        g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);

        // draw the words and characters
        g.setColor(CONSTANTS.TEXTBOX_COLOUR);
        g.fillRect((int) (Game.WIDTH / 2.70), (Game.HEIGHT / 18), 300, 80);
        g.fillRect((int) (Game.WIDTH / 2.70), (Game.HEIGHT / 18) + 250, 300, 80);
        g.fillRect(Game.WIDTH - 215, Game.HEIGHT - 175, 170, 80);

        g.setColor(CONSTANTS.FONT_COLOUR);
        g.setFont(CONSTANTS.MEDIUM_BOLD_FONT);
        g.drawString("Settings", (int) (Game.WIDTH / 2.5), Game.HEIGHT / 8);
        g.drawString("Back", Game.WIDTH - 200, Game.HEIGHT - 115);

        g.setFont(CONSTANTS.SMALL_BOLD_FONT);
        g.drawString("Set Colours", (int) (Game.WIDTH / 2.70) + 40, (Game.HEIGHT / 18) + 305);
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
