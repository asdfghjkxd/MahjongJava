package objects;

import base.TickRender;
import core.ID;

import java.awt.*;
import java.io.IOException;

public abstract class GameObject implements TickRender {
    protected int x;
    protected int y;
    protected ID id;

    public GameObject(int x, int y, ID id) {
        this.x = x;
        this.y = y;
        this.id = id;
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) throws IOException {

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public ID getId() {
        return id;
    }
}
