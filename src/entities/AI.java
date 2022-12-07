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
        this.movingXPosition = x;
        this.movingYPosition = y;
        this.rotationDegrees = rotationDegrees;
        this.name = new NameGenerator().generateName().toString().split(" ")[0];
        this.strategy = new AIStrategy();
        this.score = 0;
        this.board = board;
        this.playerPrivateHand = new LinkedList<>();
        this.playerPublicHand = new LinkedList<>();
    }

    @Override
    public void acceptItem(Tile tile) {
        if (!(tile.getOwner() instanceof AI || tile.getOwner() instanceof Player)) {
            tile.setOwner(this);
            tile.setRotationDegrees(getRotationDegrees());

            if (!tile.getTileClass().equals("bonus")) {
                this.setNextAvailableTilePosition(tile);
                playerPublicHand.add(tile);
            } else {
                score++;
                // remove it from screen
                tile.setTilePosition(-100, -100);
                playerPrivateHand.add(tile);
            }

            sortHand();
            strategy.onTileReceive(playerPublicHand);
        }
    }

    @Override
    public void setNextAvailableTilePosition(Tile tile) {
        if (rotationDegrees > 0) {
            tile.setTilePosition(getStartingX(), getMovingY());
            setMovingY(getMovingY() + TILE_Y_SPACING);
        } else {
            super.setNextAvailableTilePosition(tile);
        }
    }
}
