package screens;

import utils.Renderable;
import utils.Tickable;

import java.awt.*;
import java.io.IOException;

public abstract class Screen implements Tickable, Renderable {
    @Override
    public void render(Graphics g) throws IOException {

    }

    @Override
    public int getStartingX() {
        return 0;
    }

    @Override
    public int getStartingY() {
        return 0;
    }

    @Override
    public int getMovingX() {
        return 0;
    }

    @Override
    public int getMovingY() {
        return 0;
    }

    @Override
    public void setStartingX(int x) {

    }

    @Override
    public void setStartingY(int y) {

    }

    @Override
    public void setMovingX(int x) {

    }

    @Override
    public void setMovingY(int y) {

    }

    @Override
    public void tick() {

    }
}
