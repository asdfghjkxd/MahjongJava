package screens;

import core.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class MainMenu extends Screen {
    public MainMenu(Game g) {
        super(g);
    }

    @Override
    public void render(Graphics g) throws IOException {
        g.setColor(new Color(0, 100, 0));
        g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
        g.setFont(new Font("Arial", Font.BOLD, 60));
        g.setColor(Color.WHITE);
        g.fillRect(423, 190, 320, 80);
        g.setColor(Color.BLACK);
        g.drawString("Mahjong", 458, 250);

        g.setFont(new Font("Arial", Font.BOLD, 40));
        g.setColor(Color.WHITE);
        g.fillRect(480, 400, 200, 70);
        g.fillRect(480, 515, 200, 70);
        g.fillRect(480, 630, 200, 70);

        g.setColor(Color.BLACK);
        g.drawRect(480, 400, 200, 70);
        g.drawRect(480, 515, 200, 70);
        g.drawRect(480, 630, 200, 70);
        g.drawString("Play", 540, 450);
        g.drawString("Settings", 505, 565);
        g.drawString("Quit", 540, 680);
    }

    @Override
    public void tick() {
        // main menu does not need to be ticked
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
