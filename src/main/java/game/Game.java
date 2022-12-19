package game;

import constants.GAME_STATE;

import javax.swing.*;
import java.awt.*;

public abstract class Game extends Canvas {
    protected static final String GAMENAME = "Mahjong";
    protected boolean running = false;
    protected Thread gameThread;
    public static final int WIDTH = 1180;
    public static final int HEIGHT = WIDTH / 12 * 9;
    public Graphics graphics;
    public abstract void setGameState(GAME_STATE gameState);
}
