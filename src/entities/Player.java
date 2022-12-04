package entities;

import board.Board;
import pieces.Tile;
import strategy.Strategy;
import utils.Container;
import utils.Observable;

import javax.swing.*;
import java.util.Collections;
import java.util.LinkedList;

public abstract class Player implements Container, Observable {
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

            if (tile.getTileClass().equals("bonus")) {
                playerPublicHand.add(tile);
            } else {
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

    // Utility functions to keep hand sorted and to derive the next positions for the tile to be placed into
    public void sortHand() {
        Collections.sort(playerPrivateHand);
        rotateAllTiles(getRotationDegrees());

        int tempX = getStartingXPosition();
        for (Tile t: playerPrivateHand) {
            setNextAvailableTilePosition(t);
            tempX += TILE_X_SPACING;
        }

        setMovingXPosition(tempX);
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
        tile.setStartingX(getMovingXPosition());
        tile.setStartingY(getStartingYPosition());
        setMovingXPosition(getMovingXPosition() + TILE_X_SPACING);
    }

    // Getter and Setters
    public int getStartingXPosition() {
        return startingXPosition;
    }

    public void setStartingXPosition(int startingXPosition) {
        this.startingXPosition = startingXPosition;
    }

    public int getMovingXPosition() {
        return movingXPosition;
    }

    public void setMovingXPosition(int movingXPosition) {
        this.movingXPosition = movingXPosition;
    }

    public int getStartingYPosition() {
        return startingYPosition;
    }

    public void setStartingYPosition(int startingYPosition) {
        this.startingYPosition = startingYPosition;
    }

    public int getMovingYPosition() {
        return movingYPosition;
    }

    public void setMovingYPosition(int movingYPosition) {
        this.movingYPosition = movingYPosition;
    }

    public void setPlayerPosition(int x, int y) {
        setStartingXPosition(x);
        setStartingYPosition(y);
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
}
