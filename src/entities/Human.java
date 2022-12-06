package entities;

import board.Board;
import pieces.Tile;
import strategy.AIStrategy;

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
        this.strategy = new AIStrategy();
        this.score = 0;
        this.board = board;
        this.playerPrivateHand = new LinkedList<>();
        this.playerPublicHand = new LinkedList<>();
    }

    public String pollForPlayerName() {
        String name = "";

        JOptionPane.showMessageDialog(null, "Hello! Before you proceed, please tell us what we " +
                "should call you!");

        while (name.equals("")) {
            name = JOptionPane.showInputDialog("Enter in your name below:");

            if (name.equals("")) {
                JOptionPane.showMessageDialog(null, "Your name cannot be empty!");
            }
        }

        JOptionPane.showMessageDialog(null, "Hello, " + name + "!");
        return name;
    }
}
