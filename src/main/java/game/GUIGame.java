package game;

import board.Board;
import constants.GAME_STATE;
import core.Window;
import io.KeyInput;
import io.MouseInput;
import screens.*;
import utils.Commandable;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.IOException;

/**
 * Manages the overall game environment, and acts as the interface between the game and the game logic
 */
public final class GUIGame extends Game implements Runnable, Commandable {
    private GAME_STATE gameState = GAME_STATE.MAIN_MENU;

    private final MainMenu mainMenu;
     private final HUD hud;
    private final Pause pause;
    private final GameScreen gameScreen;
    private final Settings settings;
    private final End end;
    private final Board board;
    public Graphics graphics;

    public static void main(String[] args) {
        new GUIGame();
    }

    public GUIGame() {
        synchronized (this) {
            new Window(WIDTH, HEIGHT, GAMENAME, this);
            mainMenu = new MainMenu(this);
             board = new Board(this);
             hud = new HUD(this, board);
            pause = new Pause(this);
            gameScreen = new GameScreen(this);
            settings = new Settings(this);
             end = new End(this, board);

             this.addKeyListener(new KeyInput(this, hud, board));
             this.addMouseListener(new MouseInput(this, hud));
        }
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
            System.exit(0);
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
                    synchronise_renders(null);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
            }
        }

        stop();
    }

    public void resetGame() {
//        board.resetGame();
    }

    @Override
    public synchronized void synchronise_ticks() {
//        switch (this.gameState) {
//            case IN_GAME -> {
//                board.synchronise_ticks();
//            }
//        }
    }

    @Override
    public synchronized void synchronise_renders(Graphics g) throws IOException {
        BufferStrategy bs = this.getBufferStrategy();

        // creating 3 buffering stream
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics graph = bs.getDrawGraphics();
        this.graphics = graph;

        switch (gameState) {
            case IN_GAME -> {
//                hud.render(graph);
                gameScreen.render(graph);
//                board.synchronise_renders(graph);
            }
            case MAIN_MENU -> mainMenu.render(graph);
            case SETTINGS -> settings.render(graph);
            case PAUSED -> pause.render(graph);
//            case END -> end.render(graph);
        }

        graph.dispose();
        bs.show();
    }

    // Getters and Setters
    public GAME_STATE getGameState() {
        return gameState;
    }

    public void setGameState(GAME_STATE gameState) {
        this.gameState = gameState;
    }
}
