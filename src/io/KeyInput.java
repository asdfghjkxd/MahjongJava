package io;

import core.Game;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {
    private final Game game;

    public KeyInput(Game game) {
        this.game = game;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        switch (key) {
            case KeyEvent.VK_ESCAPE -> {
                game.setGameState(Game.GAME_STATE.END);
                System.exit(0);
            }
            case KeyEvent.VK_P -> {
                switch (game.getGameState()) {
                    case IN_GAME -> game.setGameState(Game.GAME_STATE.PAUSED);
                    case PAUSED -> game.setGameState(Game.GAME_STATE.IN_GAME);
                    case MAIN_MENU, SETTINGS -> JOptionPane.showMessageDialog(null,
                            "Cannot pause when not in game!");
                }
            }
            case KeyEvent.VK_Q -> {
                final JFrame p = new JFrame();
                p.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

                int quit = JOptionPane.showConfirmDialog(p, "Are you sure you want to quit?");
                if (quit == 0) {
                    switch (game.getGameState()) {
                        case MAIN_MENU -> {
                            game.setGameState(Game.GAME_STATE.END);
                            System.exit(0);
                        }
                        case IN_GAME, PAUSED, SETTINGS -> {
                            game.setGameState(Game.GAME_STATE.MAIN_MENU);
                            // game.resetGame();
                        }
                    }

                    p.setVisible(false);
                } else {
                    p.setVisible(false);
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // do nothing
    }
}
