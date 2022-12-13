package entities;

import board.Board;
import screens.HUD;
import strategy.HumanStrategy;

import javax.swing.*;
import java.util.LinkedList;

public final class Human extends Player {
    public Human(int x, int y, Board board) {
        this.startingXPosition = x;
        this.startingYPosition = y;
        this.movingXPosition = x;
        this.movingYPosition = y;
        this.rotationDegrees = 0;
        this.name = pollForPlayerName();
        this.strategy = new HumanStrategy();
        this.score = 0;
        this.board = board;
        this.publicHand = new LinkedList<>();
        this.privateHand = new LinkedList<>();
    }

    public synchronized String pollForPlayerName() {
        String name = "";

        JOptionPane.showMessageDialog(null, "Hello! Before you proceed, please tell us what we " +
                "should call you!", "Welcome", JOptionPane.INFORMATION_MESSAGE);

        while (name.equals("")) {
            name = JOptionPane.showInputDialog(null, "Enter in your name: ",
                    "Name Input", JOptionPane.INFORMATION_MESSAGE);

            if (name == null) {
                name = "Player";
            } else if (name.equals("")) {
                JOptionPane.showMessageDialog(null, "Your name cannot be empty!", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }

        JOptionPane.showMessageDialog(null, "Hello, " + name + "!",
                "Hello!", JOptionPane.INFORMATION_MESSAGE);
        return name;
    }

    @Override
    public synchronized boolean strategyAction(int tilePos) {
        if (isWinningHand()) {
            // disrupt game and set winner to this player
            return false;
        }

        // if not winning then he must be forced to discard a tile
        int result = JOptionPane.showConfirmDialog(null, "Discard Tile?", "Confirm",
                JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);

        if (result == 0) {
            discardItem(tilePos);
            HUD.tileCounter = Math.max(0, HUD.tileCounter - 1);
            return true;
        }

        return false;
    }
}
