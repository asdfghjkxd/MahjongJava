package entities;

import board.Board;
import core.Test;
import pieces.Tile;
import strategy.Strategy;
import utils.Container;
import utils.Observable;
import utils.Renderable;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;

public abstract class Player implements Container, Observable, Renderable {
    protected int startingXPosition;
    protected int movingXPosition;
    protected int startingYPosition;
    protected int movingYPosition;
    protected int rotationDegrees;
    protected String name;
    protected Strategy strategy;
    protected int score;
    protected LinkedList<Tile> playerPublicHand;
    protected LinkedList<Tile> playerPrivateHand;
    protected Board board;
    protected final int TILE_X_SPACING = 50;
    protected final int TILE_Y_SPACING = 30;

    // Interface methods
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
            tile.setRotationDegrees(getRotationDegrees());
            this.setNextAvailableTilePosition(tile);

            if (!tile.getTileClass().equals("bonus")) {
                playerPublicHand.add(tile);
            } else {
                score++;
                playerPrivateHand.add(tile);
            }

            sortHand();
            strategy.onTileReceive(playerPublicHand);
        }
    }

    @Override
    public Tile discardItem() {
        Tile removed = strategy.pollDiscardTile(playerPublicHand);
        board.discardToDiscardPile(removed);
        strategy.onTileDiscard(playerPublicHand);

        return removed;
    }

    @Override
    public Tile discardItem(Tile t) {
        if (t.getOwner() == this) {
            board.discardToDiscardPile(t);

            // remove tile from the hands
            playerPrivateHand.remove(t);
            playerPublicHand.remove(t);
        } else {
            JOptionPane.showMessageDialog(null, "Cannot discard tile that does not belong to" +
                    " player " + getName());
        }

        // return the immediate tile for interface consistency
        return t;
    }

    @Override
    public void render(Graphics g) throws IOException {
        for (Tile t: playerPrivateHand) {
            t.render(g);
        }

        for (Tile t: playerPublicHand) {
            t.render(g);
        }
    }

    @Override
    public int getStartingX() {
        return startingXPosition;
    }

    @Override
    public int getStartingY() {
        return startingYPosition;
    }

    @Override
    public int getMovingX() {
        return movingXPosition;
    }

    @Override
    public int getMovingY() {
        return movingYPosition;
    }

    @Override
    public void setStartingX(int x) {
        startingXPosition = x;
    }

    @Override
    public void setStartingY(int y) {
        startingYPosition = y;
    }

    @Override
    public void setMovingX(int x) {
        movingXPosition = x;
    }

    @Override
    public void setMovingY(int y) {
        movingYPosition = y;
    }

    // Utility functions to keep hand sorted and to derive the next positions for the tile to be placed into
    public void sortHand() {
        Collections.sort(playerPrivateHand);
        rotateAllTiles(getRotationDegrees());

        int tempX = getStartingX();
        for (Tile t: playerPrivateHand) {
            setNextAvailableTilePosition(t);
            tempX += TILE_X_SPACING;
        }

        setMovingX(tempX);
    }

    public void rotateAllTiles(int rotationDegrees) {
        for (Tile t: playerPrivateHand) {
            t.setRotationDegrees(rotationDegrees % 360);
        }

        for (Tile t: playerPublicHand) {
            t.setRotationDegrees(rotationDegrees % 360);
        }
    }

    public void setNextAvailableTilePosition(Tile tile) {
        tile.setStartingX(getMovingX());
        tile.setStartingY(getStartingY());
        setMovingX(getMovingX() + TILE_X_SPACING);
    }

    // Getter and Setters
    public void setPlayerPosition(int x, int y) {
        setStartingX(x);
        setStartingY(y);
    }

    public int getRotationDegrees() {
        return rotationDegrees;
    }

    public void setRotationDegrees(int rotationDegrees) {
        this.rotationDegrees = rotationDegrees;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    // Utility functions
    @Override
    public String toString() {
        if (Test.test_state.equals(Test.TEST_STATE.TESTING)) {
            return "Player: " + getName() + "\n" +
                    playerPublicHand.toString() + "\n" +
                    playerPrivateHand.toString() + "\n" +
                    playerPrivateHand.size() + "\n" +
                    playerPublicHand.size() + "\n";
        } else {
            return "Player: " + getName() + "\n" +
                    "Score: " + getScore() + "\n" +
                    playerPublicHand.toString() + "\n";
        }
    }
}
