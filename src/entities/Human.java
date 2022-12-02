package entities;

import board.Board;
import pieces.Tile;
import strategy.RandomStrategy;

import javax.swing.*;
import java.util.LinkedList;

public final class Human extends Player {
    public Human(String name, Board board) {
        this.name = name;
        this.strategy = new RandomStrategy();
        this.score = 0;
        this.board = board;
        this.playerPrivateHand = new LinkedList<>();
        this.playerPublicHand = new LinkedList<>();
    }

    @Override
    public void resetContainer() {
        for (Tile t: playerPublicHand) {
            t.setOwner(board);
        }
        playerPublicHand.clear();

        for (Tile t: playerPrivateHand) {
            t.setOwner(board);
        }
        playerPrivateHand.clear();

        score = 0;
    }

    @Override
    public void acceptItem(Tile tile) {
        if (!(tile.getOwner() instanceof AI || tile.getOwner() instanceof Player)) {
            tile.setOwner(this);
            tile.setRotationDegrees(0);
            this.setNextAvailableTilePosition(tile);

            if (tile.getTileClass().equals("bonus")) {
                playerPublicHand.add(tile);
            } else {
                playerPrivateHand.add(tile);
            }

            sortHand();
        }
    }

    @Override
    public Tile discardItem() {
        return null;
    }

    @Override
    public Tile discardItem(Tile t) {
        if (t.getOwner() == this) {
            board.discardToDiscardPile(t);

            // remove this thing from the hands
            playerPrivateHand.remove(t);
            playerPublicHand.remove(t);
        } else {
            JOptionPane.showMessageDialog(null, "Cannot discard tile that does not belong to" +
                    " player " + getName());
        }

        // return the immediate tile for interface consistency
        return t;
    }
}
