package io;

import board.Board;
import constants.Constants;
import core.Game;
import pieces.Tile;
import screens.HUD;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {
    private final Game game;
    private final HUD hud;
    private final Board board;

    public KeyInput(Game game, HUD hud, Board board) {
        this.game = game;
        this.hud = hud;
        this.board = board;
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
                            "Cannot pause when not in game!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            case KeyEvent.VK_Q -> {
                final JFrame p = new JFrame();
                p.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

                switch (game.getGameState()) {
                    case MAIN_MENU -> {
                        int quit = JOptionPane.showConfirmDialog(p, "Quit the Game?", "Quit",
                                JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

                        if (quit == 0) {
                            game.setGameState(Game.GAME_STATE.END);
                            System.exit(0);
                        }
                    }
                    case IN_GAME, PAUSED, SETTINGS -> {
                        int quit = JOptionPane.showConfirmDialog(p, "Quit to the Main Menu?", "Quit",
                                JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

                        if (quit == 0) {
                            game.setGameState(Game.GAME_STATE.MAIN_MENU);
                        }
                    }
                }

                p.setVisible(false);
            }
            case KeyEvent.VK_W, KeyEvent.VK_UP -> {
                // shift to public tiles
            }
            case KeyEvent.VK_D, KeyEvent.VK_RIGHT -> {
                hud.shiftCursor(HUD.tileCounter + 1);
            }
            case KeyEvent.VK_S, KeyEvent.VK_DOWN -> {
                // shift to private tiles
            }
            case KeyEvent.VK_A, KeyEvent.VK_LEFT -> {
                hud.shiftCursor(HUD.tileCounter - 1);
            }
            case KeyEvent.VK_ENTER -> {
                if (board.getCurrentPlayer().equals(board.getHumanPlayer())) {
                    board.enforcePlayerAction(HUD.tileCounter);
                } else {
                    JOptionPane.showMessageDialog(null, "Cannot perform actions when " +
                            "it is not your turn!", "Wait your turn!", JOptionPane.WARNING_MESSAGE);
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // do nothing
    }
}
