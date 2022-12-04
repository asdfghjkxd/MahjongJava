package entities;

import board.Board;
import strategy.RandomStrategy;

import javax.swing.*;
import java.util.LinkedList;

public final class Human extends Player {
    public Human(int x, int y, Board board) {
        this.startingXPosition = x;
        this.startingYPosition = y;
        this.rotationDegrees = 0;
        this.name = JOptionPane.showInputDialog("Enter in your name below: ");
        this.strategy = new RandomStrategy();
        this.score = 0;
        this.board = board;
        this.playerPrivateHand = new LinkedList<>();
        this.playerPublicHand = new LinkedList<>();
    }
}
