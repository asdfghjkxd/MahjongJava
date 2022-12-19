package screens;

import constants.CONSTANTS;
import game.GUIGame;

import java.awt.*;
import java.io.IOException;

public class Settings extends Screen{
    public Settings(GUIGame game) {
        super(game);
    }

    @Override
    public void render(Graphics g) throws IOException {
        // setting the colour of the background
        g.setColor(CONSTANTS.BACKGROUND_COLOUR);
        g.fillRect(0, 0, GUIGame.WIDTH, GUIGame.HEIGHT);

        // draw the words and characters
        g.setColor(CONSTANTS.TEXTBOX_COLOUR);
        g.fillRect((int) (GUIGame.WIDTH / 2.70), (GUIGame.HEIGHT / 18), 300, 80);
        g.fillRect((int) (GUIGame.WIDTH / 2.70), (GUIGame.HEIGHT / 18) + 250, 300, 80);
        g.fillRect(GUIGame.WIDTH - 215, GUIGame.HEIGHT - 175, 170, 80);

        g.setColor(CONSTANTS.FONT_COLOUR);
        g.setFont(CONSTANTS.MEDIUM_BOLD_FONT);
        g.drawString("Settings", (int) (GUIGame.WIDTH / 2.5), GUIGame.HEIGHT / 8);
        g.drawString("Back", GUIGame.WIDTH - 200, GUIGame.HEIGHT - 115);

        g.setFont(CONSTANTS.SMALL_BOLD_FONT);
        g.drawString("Set Colours", (int) (GUIGame.WIDTH / 2.70) + 40, (GUIGame.HEIGHT / 18) + 305);
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
