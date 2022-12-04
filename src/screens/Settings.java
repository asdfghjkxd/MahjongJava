package screens;

import core.Game;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Settings extends Screen{
    public Settings(Game game) {
        super(game);
    }

    @Override
    public void render(Graphics g) throws IOException {
        JFrame p = new JFrame();
        p.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        // exiting action
        p.setVisible(false);
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
