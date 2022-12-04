package entities;

import board.Board;
import org.ajbrown.namemachine.NameGenerator;
import pieces.Tile;
import strategy.AIStrategy;

import java.util.Collections;
import java.util.LinkedList;

public final class AI extends Player {
    public AI(int x, int y, int rotationDegrees, Board board) {
        this.startingXPosition = x;
        this.startingYPosition = y;
        this.rotationDegrees = rotationDegrees;
        this.name = new NameGenerator().generateNames(1).get(0).toString();
        this.strategy = new AIStrategy();
        this.score = 0;
        this.board = board;
        this.playerPrivateHand = new LinkedList<>();
        this.playerPublicHand = new LinkedList<>();
    }

    @Override
    public void sortHand() {
        Collections.sort(playerPrivateHand);
        rotateAllTiles(getRotationDegrees());

        int tempY = getStartingY();
        for (Tile t: playerPrivateHand) {
            setNextAvailableTilePosition(t);
            tempY += TILE_Y_SPACING;
        }

        setMovingY(tempY);
    }

    @Override
    public void setNextAvailableTilePosition(Tile tile) {
        tile.setStartingY(getMovingY());
        tile.setStartingX(getStartingX());
        setMovingY(getMovingY() + TILE_Y_SPACING);
    }
}
