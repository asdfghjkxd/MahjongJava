package entities;

import algorithms.TileAlgorithm;
import board.Board;
import constants.VALID_TILE_ACTIONS;
import game.ConsoleGame;
import io.NonBlockingIntegerDialog;
import tests.Test;
import pieces.Tile;
import strategy.Strategy;
import utils.Container;
import utils.Observable;
import utils.Renderable;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.*;

import static com.diogonunes.jcolor.Ansi.colorize;

public abstract class Player implements Container, Observable, Renderable {
    protected int startingXPosition;
    protected int movingXPosition;
    protected int startingYPosition;
    protected int movingYPosition;
    protected int rotationDegrees;
    protected String name;
    protected volatile Strategy strategy;
    protected volatile int score;
    protected volatile LinkedList<Tile> privateHand;
    protected volatile LinkedList<Tile> publicHand;
    protected volatile Board board;
    protected final int TILE_X_SPACING = 50;
    protected final int TILE_Y_SPACING = 40;
    protected volatile int order = -1;
    protected Scanner scanner = new Scanner(System.in);

    // Interface methods
    @Override
    public synchronized void resetContainer() {
        for (Tile t: privateHand) {
            t.setOwner(board);
        }
        privateHand.clear();

        for (Tile t: publicHand) {
            t.setOwner(board);
        }
        publicHand.clear();

        score = 0;
    }

    @Override
    public synchronized void acceptItem(Tile tile) {
        if (!(tile.getOwner() instanceof AI || tile.getOwner() instanceof Player)) {
            tile.setOwner(this);
            tile.setRotationDegrees(getRotationDegrees());

            if (!tile.isBonus()) {
                this.setNextAvailableTilePosition(tile);
                privateHand.add(tile);
            } else {
                score++;
                // remove it from screen
                tile.setTilePosition(-100, -100);
                publicHand.add(tile);
            }

            sortHand();
            strategy.onTileReceive(privateHand);
        }
    }

    @Override
    public synchronized Tile discardItem() {
        Tile removed = strategy.pollDiscardTile(privateHand);
        board.discardToDiscardPile(removed);
        strategy.onTileDiscard(privateHand);
        sortHand();

        return removed;
    }

    @Override
    public synchronized Tile discardItem(Tile t) {
        if (t.getOwner() == this) {
            board.discardToDiscardPile(t);

            // remove tile from the hands
            publicHand.remove(t);
            privateHand.remove(t);

            sortHand();
        } else {
            JOptionPane.showMessageDialog(null, "Cannot discard tile that does not belong to" +
                    " player " + getName(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        // return the immediate tile for interface consistency
        return t;
    }

    public synchronized Tile discardItem(int index) {
        if (index < 0 || index > privateHand.size()) {
            return null;
        } else {
            Tile toDiscard = privateHand.get(index);

            if (toDiscard.getOwner().equals(this)) {
                board.discardToDiscardPile(toDiscard);

                // remove tile from hands
                publicHand.remove(toDiscard);
                privateHand.remove(toDiscard);

                sortHand();
            } else {
                JOptionPane.showMessageDialog(null, "Cannot discard tile that does not belong to" +
                        " player " + getName(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        return null;
    }

    @Override
    public synchronized void render(Graphics g) throws IOException {
        // TODO
        // only render the public hand for now, and avoid rendering the private hand
        for (Tile t : privateHand) {
            t.render(g);
        }
    }

    // Checks if the hand is winning
    public boolean isWinningHand() {
        // get all the possible pong/kang/chow-able tiles from public or private hand
        LinkedList<Tile> onlyValidTiles = new LinkedList<>();
        onlyValidTiles.addAll(
                privateHand.stream().filter(x -> x.getTileProperty().tileValue < 34).toList()
        );
        onlyValidTiles.addAll(
                publicHand.stream().filter(x -> x.getTileProperty().tileValue < 34).toList()
        );
        Collections.sort(onlyValidTiles);

        // then convert this list into a hand and check
        // note that bonus tiles do not contribute in any manner to the winning configuratio of the tiles, only scores
        ArrayList<Integer> collect = TileAlgorithm.tileList2IntList(onlyValidTiles);
        return TileAlgorithm.validHand(
                collect,
                VALID_TILE_ACTIONS.ALL_ACTIONS,
                VALID_TILE_ACTIONS.HEAD_ACTIONS,
                new LinkedList<>()
        ).getLeft();
    }

    public void consoleStrategyAction() throws InterruptedException {
        if (isWinningHand()) {
            board.endGame(this);
        } else {
            System.out.println(colorize("Your hand: \n" + privateHand.toString(),
                    ConsoleGame.consoleWhiteText));
            int result = -1;

            while (result < 0) {
                System.out.println(colorize("Key in the tile index to discard: ", ConsoleGame.consoleWhiteText));

                try {
                    result = scanner.nextInt();
                } catch (InputMismatchException | NumberFormatException ex) {
                    System.out.println(colorize("Invalid tile index! Try again.", ConsoleGame.consoleRedText));
                }
            }

            discardItem(privateHand.get(result));
        }
    }

    public void strategyAction() throws InterruptedException {
        if (isWinningHand()) {
            board.endGame(this);
        } else {
            NonBlockingIntegerDialog dialog = new NonBlockingIntegerDialog(
                    "Discard", "Key in the tile to discard", JOptionPane.QUESTION_MESSAGE,
                    x -> x
            );

            while (dialog.getInput() == Integer.MAX_VALUE) {
                // await
            }

            Tile discard = strategy.pollDiscardTile(privateHand);
            discardItem(discard);

            // pause before advancing the player
            Thread.sleep(2000);
            board.advancePlayer();
            board.getCurrentPlayer().strategyAction();
        }
    }

    // Tile utility functions to keep hand sorted and to derive the next positions for the tile to be placed into
    public synchronized void sortHand() {
        Collections.sort(publicHand);

        if (getRotationDegrees() == 0) {
            setMovingX(getStartingX());

            for (Tile t: privateHand) {
                t.setTilePosition(getMovingX(), getStartingY());
                setMovingX(getMovingX() + TILE_X_SPACING);
            }
        } else {
            setMovingY(getStartingY());

            for (Tile t: privateHand) {
                t.setTilePosition(getStartingX(), getMovingY());
                setMovingY(getMovingY() + TILE_Y_SPACING);
            }
        }

    }

    public synchronized void setNextAvailableTilePosition(Tile tile) {
        tile.setTilePosition(getMovingX(), getStartingY());
        setMovingX(getMovingX() + TILE_X_SPACING);
    }

    // Getter and Setters
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

    public synchronized final void setPlayerPosition(int x, int y) {
        this.startingXPosition = x;
        this.startingYPosition = y;
    }

    public int getRotationDegrees() {
        return rotationDegrees;
    }

    public synchronized void setRotationDegrees(int rotationDegrees) {
        this.rotationDegrees = rotationDegrees;
    }

    public String getName() {
        return name;
    }

    public synchronized void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public synchronized void setScore(int score) {
        this.score = score;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getOrder() {
        return order;
    }

    public synchronized int[] getTilePosition(int pos) {
        if (pos < 0 || pos > privateHand.size() - 1) {
            return new int[]{-100, -100};
        } else {
            Tile curr = privateHand.get(pos);
            return new int[]{curr.getMovingX(), curr.getMovingY()};
        }
    }

    public int getNumberOfPublicTiles() {
        return publicHand.size();
    }

    public int getNumberOfPrivateTiles() {
        return privateHand.size();
    }

    // Class utility functions
    @Override
    public String toString() {
        if (Test.test_state.equals(Test.TEST_STATE.TESTING)) {
            return "Player: " + getName() + "\n" +
                    privateHand.toString() + "\n" +
                    publicHand.toString() + "\n" +
                    publicHand.size() + "\n" +
                    privateHand.size() + "\n";
        } else {
            return "Player: " + getName() + "\n" +
                    "Score: " + getScore() + "\n" +
                    privateHand.toString() + "\n";
        }
    }
}
