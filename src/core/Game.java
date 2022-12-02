package core;

import pieces.Tile;
import utils.Commandable;
import utils.Renderable;
import utils.Tickable;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.IOException;

/**
 * Manages the overall game environment, and acts as the interface between the game and the game logic
 */
public class Game extends Canvas implements Runnable, Commandable {
    public static final int WIDTH = 1180;
    public static final int HEIGHT = WIDTH / 12 * 9;
    public static final String GAMENAME = "Mahjong";
    private boolean running = false;
    private Thread gameThread;
    private GAME_STATE gameState = GAME_STATE.RUNNING;
    public enum GAME_STATE {
        RUNNING,
        PAUSED,
        END
    }

    public static void main(String[] args) {
        new Game();
    }

    public Game() {
        new Window(WIDTH, HEIGHT, GAMENAME, this);
    }

    // Functions necessary for running the game
    public synchronized void start() {
        gameThread = new Thread(this);
        gameThread.start();
        running = true;
    }

    public synchronized void stop() {
        try {
            gameThread.join();
            running = false;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    @Override
    public void run() {
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountTicks = 60.0;
        double ns = 1E9 / amountTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();

        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;

            while (delta >= 1) {
                synchronise_ticks();
                delta--;
            }

            if (running) {
                try {
                    synchronise_renders();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
            }
        }

        stop();
    }

    @Override
    public synchronized void synchronise_ticks() {
//        switch (this.gameState) {
//            case GAME -> {
//                hud.tick();
//                handler.tick();
//            }
//            case MENU, END -> {
//                handler.tick();
//                menu.tick();
//            }
//        }
    }

    @Override
    public synchronized void synchronise_renders() throws IOException {
        BufferStrategy bs = this.getBufferStrategy();

        // creating 3 buffering stream
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        g.dispose();
        bs.show();
    }
}
