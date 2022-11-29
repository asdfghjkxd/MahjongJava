package core;

import core.*;
import io.KeyInput;
import objects.*;
import base.*;
import screen.*;
import screen.Window;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.IOException;
import java.io.Serial;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Game extends Canvas implements Runnable {

    @Serial
    private static final long serialVersionUID = -6112428091888191314L;
    public static final int WIDTH = 1180;
    public static final int HEIGHT = WIDTH / 12 * 9;
    private Thread thread;
    private final Handler handler;
    private final HUD hud;
    private final MainMenu menu;
    private final Pause pause;
    private int gamePlayerIndex = 0;
    private LinkedList<Player> gamePlayerRoster = null;
    private final LinkedList<List<Integer>> playerPositions = new LinkedList<List<Integer>>();
    private GAME_STATE gameState = GAME_STATE.MENU;

    public enum GAME_STATE {
        MENU,
        GAME,
        PAUSE,
        END
    }

    public static void main(String[] args) {
        new Game();
    }

    public Game() {
        this.handler = new Handler(this);
        this.menu = new MainMenu(this.handler);
        this.hud = new HUD(this.handler);
        this.pause = new Pause(this.handler);

        this.handler.instantiateBoardAndPlayers();

        new Window(WIDTH, HEIGHT, "Mahjong", this);

        this.addKeyListener(new KeyInput((this.handler)));
        this.addMouseListener(menu);
    }

    public synchronized void start() {
        // starting the thread
        this.gameState = GAME_STATE.MENU;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop() {
        // starting the thread
        try {
            // kills the thread
            this.gameState = GAME_STATE.END;
            thread.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        // game loop
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountTicks = 60.0;
        double ns = 1E9 / amountTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();

        while (!this.gameState.equals(GAME_STATE.END)) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;

            while (delta >= 1) {
                tick();
                delta--;
            }

            if (!this.gameState.equals(GAME_STATE.END)) {
                try {
                    render();
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

    public void tick() {
        switch (this.gameState) {
            case GAME -> {
                hud.tick();
                handler.tick();
            }
            case MENU -> {
                handler.tick();
                menu.tick();
            }
            case END -> {
                this.stop();
            }
        }
    }

    public void render() throws IOException {
        BufferStrategy bs = this.getBufferStrategy();

        // creating 3 buffering stream
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        switch (this.gameState) {
            case GAME -> {
                hud.render(g);
                handler.render(g);
            }
            case MENU -> {
                menu.render(g);
            }
            case PAUSE -> {
                pause.render(g);
            }
        }

        g.dispose();
        bs.show();
    }

    public final void setGameState(GAME_STATE gameState) {
        this.gameState = gameState;
    }

    public final GAME_STATE getGameState() {
        return this.gameState;
    }
}
