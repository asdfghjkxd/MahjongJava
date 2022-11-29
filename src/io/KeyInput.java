package io;

import core.Game;
import core.Handler;
import core.ID;
import objects.GameObject;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {
    private final Handler handler;

    public KeyInput(Handler handler) {
        this.handler = handler;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        } else if (key == KeyEvent.VK_P) {
            if (handler.getGameState() == Game.GAME_STATE.GAME || handler.getGameState() == Game.GAME_STATE.PAUSE) {
                handler.setGameState(handler.getGameState() == Game.GAME_STATE.PAUSE
                        ? Game.GAME_STATE.GAME
                        : Game.GAME_STATE.PAUSE);
            }
        } else if (key == KeyEvent.VK_Q) {
            final JFrame p = new JFrame();

            int thing = JOptionPane.showConfirmDialog(p, "Are you sure you want to quit?");
            if (thing == 0) {
                if (handler.getGameState() == Game.GAME_STATE.GAME) {
                    handler.setGameState(Game.GAME_STATE.MENU);
                    synchronized (handler) {
                        handler.resetGame();
                    }
                }
                p.setVisible(false);
            } else {
                p.setVisible(false);
            }
        }

//        for (GameObject h: handler.getObjects()) {
//            if (h.getId() == ID.Player) {
//                // key events for player, VK represents the keyboard keys
//                if (key == KeyEvent.VK_W) {
//
//                }
//            }
//        }


    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        // should correspond to the actions above
//        for (GameObject h: handler.getObjects()) {
//            if (h.getId() == ID.Player) {
//                // key events for player, VK represents the keyboard keys
//                if (key == KeyEvent.VK_W) {
//                }
//            }
//        }
    }
}
