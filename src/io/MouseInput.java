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
        switch (game.getGameState()) {
            case MAIN_MENU -> {
                int mouseX = e.getX();
                int mouseY = e.getY();

                if (mouseOver(mouseX, mouseY, 480, 400, 200, 70)) {

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
            case PAUSED -> {}
            case SETTINGS -> {}
            case IN_GAME -> {}
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
