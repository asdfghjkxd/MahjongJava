package screens;

import core.Game;
import io.MouseInput;
import utils.Renderable;
import utils.Tickable;

import java.awt.*;
import java.io.IOException;

public abstract class Screen extends MouseInput implements Tickable, Renderable {
    protected int startingX = 0;
    protected int startingY = 0;
    protected int movingX = 0;
    protected int movingY = 0;

    public Screen(Game game) {
        super(game);
    }


    @Override
    public void render(Graphics g) throws IOException {}

    @Override
    public int getStartingX() {
        return startingX;
    }

    @Override
    public int getStartingY() {
        return startingY;
    }

    @Override
    public int getMovingX() {
        return movingX;
    }

    @Override
    public int getMovingY() {
        return movingY;
    }

    @Override
    public void setStartingX(int x) {
        // the dimensions of the frame should not change
    }

    @Override
    public void setStartingY(int y) {
        // the dimensions of the frame should not change
    }

    @Override
    public void setMovingX(int x) {
        // the dimensions of the frame should not change
    }

    @Override
    public void setMovingY(int y) {
        // the dimensions of the frame should not change
    }

    @Override
    public void tick() {
        // the dimensions of the frame should not change
    }
}
