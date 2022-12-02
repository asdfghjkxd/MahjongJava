package entities;

import board.Board;
import pieces.Tile;
import strategy.Strategy;
import utils.Container;

import java.util.Collections;
import java.util.LinkedList;

public abstract class Player implements Container {
    protected int startingXPosition;
    protected int movingXPosition;
    protected int startingYPosition;
    protected int movingYPosition;
    protected String name;
    protected Strategy strategy;
    protected int score;
    protected LinkedList<Tile> playerPublicHand;
    protected LinkedList<Tile> playerPrivateHand;
    protected Board board;

    // Interface methods
    @Override
    public abstract void resetContainer();

    @Override
    public abstract void acceptItem(Tile tile);

    @Override
    public abstract Tile discardItem();

    @Override
    public abstract Tile discardItem(Tile t);

    // Utility functions to keep hand sorted and to derive the next positions for the tile to be placed into
    public void sortHand() {
        Collections.sort(playerPrivateHand);

        int tempX = startingXPosition;
        for (Tile t: playerPrivateHand) {
            setNextAvailableTilePosition(t);
        }
        movingXPosition = tempX;
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
        tile.setX_position(movingXPosition);
        tile.setY_position(startingYPosition);
        movingXPosition += 50;
    }

    // Getter and Setters
    public int getStartingXPosition() {
        return startingXPosition;
    }

    public void setStartingXPosition(int startingXPosition) {
        this.startingXPosition = startingXPosition;
    }

    public int getStartingYPosition() {
        return startingYPosition;
    }

    public void setStartingYPosition(int startingYPosition) {
        this.startingYPosition = startingYPosition;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
