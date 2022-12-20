package entities;

import board.Board;
import org.ajbrown.namemachine.NameGenerator;
import pieces.Tile;
import strategy.AIStrategy;

import java.util.LinkedList;

import static com.diogonunes.jcolor.Ansi.colorize;
import static game.ConsoleGame.consoleBlueText;

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
        this.publicHand = new LinkedList<>();
        this.privateHand = new LinkedList<>();
    }

    @Override
    public synchronized void setNextAvailableTilePosition(Tile tile) {
        if (rotationDegrees > 0) {
            tile.setTilePosition(getStartingX(), getMovingY());
            setMovingY(getMovingY() + TILE_Y_SPACING);
        } else {
            super.setNextAvailableTilePosition(tile);
        }
    }

    @Override
    public void strategyAction() throws InterruptedException {
        // act first
        discardItem();
        Thread.sleep(2000);

        // after the thread has run to completion call the next player
        board.advancePlayer();
        board.getCurrentPlayer().strategyAction();
    }

    public void consoleStrategyAction() throws InterruptedException {
        discardItem();

        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println(colorize("Current Player: " + board.getCurrentPlayer().getName(), consoleBlueText));
        System.out.println(colorize("Current Score: " + board.getCurrentPlayer().getScore(), consoleBlueText));
        System.out.println(colorize("Public Tiles: " + board.getCurrentPlayer().publicHand, consoleBlueText));

        Thread.sleep(3000);
        board.advancePlayer();
        board.getCurrentPlayer().consoleStrategyAction();
    }
}
