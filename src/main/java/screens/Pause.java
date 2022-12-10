package screens;

import constants.Constants;
import core.Game;
import utils.Tickable;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class Pause extends Screen implements Tickable {
    public Pause(Game game) {
        super(game);
    }

    @Override
    public void render(Graphics g) throws IOException {
        g.setColor(Constants.BACKGROUND_DARKENED);
        g.fillRect(0, 0, game.getWidth(), game.getHeight());
        g.setColor(Constants.TEXTBOX_COLOUR);
        g.setFont(Constants.SMALL_BOLD_FONT);
        g.drawString("PAUSED", 500, 400);
        g.setColor(Constants.TEXTBOX_COLOUR);
        g.fillRect(450, 475, 275, 70);
        g.setColor(Constants.FONT_COLOUR);
        g.drawString("Resume", 510, 525);
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
