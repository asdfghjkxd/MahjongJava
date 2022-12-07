package io;

import core.Game;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseInput extends MouseAdapter {
    public Game game;

    public MouseInput(Game game) {
        this.game = game;
    }

    @Override
    public final void mousePressed(MouseEvent e) {
        int mouseX = e.getX();
        int mouseY = e.getY();

        switch (game.getGameState()) {
            case MAIN_MENU -> {
                if (mouseOver(mouseX, mouseY, 480, 400, 200, 70)) {
                    game.resetGame();
                    game.setGameState(Game.GAME_STATE.IN_GAME);
                } else if (mouseOver(mouseX, mouseY, 480, 515, 200, 70)) {
                    game.setGameState(Game.GAME_STATE.SETTINGS);
                } else if (mouseOver(mouseX, mouseY, 480, 630, 200, 70)) {
                    if (JOptionPane.showConfirmDialog(null, "Quit the game?") == 0) {
                        game.setGameState(Game.GAME_STATE.END);
                        System.exit(0);
                    }
                }
            }
            case PAUSED -> {
                if (mouseOver(mouseX, mouseY, 450, 475, 275, 70)) {
                    game.setGameState(Game.GAME_STATE.IN_GAME);
                }
            }
            case SETTINGS -> {
                if (mouseOver(mouseX, mouseY, Game.WIDTH - 215, Game.HEIGHT - 175, 170, 80)) {
                    game.setGameState(Game.GAME_STATE.MAIN_MENU);
                }
            }
            case IN_GAME -> {
                if (mouseOver(mouseX, mouseY, Game.WIDTH - 75, 25, 50, 50)) {
                    game.setGameState(Game.GAME_STATE.PAUSED);
                }
            }
        }
    }

    @Override
    public final void mouseReleased(MouseEvent e) {

    }

    public final boolean mouseOver(int mouseX, int mouseY, int x, int y, int width, int height) {
        return (mouseX > x && mouseX < x + width)
                ? (mouseY > y && mouseY < y + height)
                : false;
    }
}
