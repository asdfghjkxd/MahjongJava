package core;

import base.TickRender;
import objects.GameObject;
import objects.Player;
import objects.Tile;
import core.Board.WindDirection;

import java.awt.*;
import java.io.IOException;
import java.util.LinkedList;

public class Handler implements TickRender {
    private final LinkedList<GameObject> objects = new LinkedList<GameObject>();
    private final Game game;
    private final Board board;
    private int startX;
    private int startY;

    // adding the tile to board counters
    private enum IncrementalDirection {
        RIGHT,
        LEFT,
        UP,
        DOWN
    }
    private IncrementalDirection incrementalDirection = IncrementalDirection.RIGHT;
    private int counter = 1;
    private int localCounter = 0;

    public Handler(Game game) {
        this.game = game;
        this.board = new Board(this);
        this.startX = game.getWidth() + 300;
        this.startY = game.getHeight() + 130;
    }

    @Override
    public final void tick() {
        for (GameObject go: objects) {
            go.tick();
        }
    }

    @Override
    public final void render(Graphics g) throws IOException {
        for (GameObject go: objects) {
            go.render(g);
        }
    }

    /**
     * Utility functions to add and remove object from running game instance
     */
    public final void addObject(GameObject go) {
        objects.add(go);
    }

    /**
     * Board-related functions
     *
     * This functions as the interface between the game loop and the tiles and players
     */
    public final void instantiateBoardAndPlayers() {

    }

    public final void resetGame() {
        board.resetBoard();
        this.game.setGameState(Game.GAME_STATE.MENU);
    }

    public final void acceptAndDiscardTile(Player player, Tile tile) {
        this.board.discardToBoard(player, tile);
    }

    public void nextIncrementalPosition(Tile t) {
        switch (incrementalDirection) {
            case RIGHT -> {
                if (counter % 19 != 0) {
                    if (localCounter == 0) {
                        t.setTilePosition(startX, startY);
                        startY += 10;
                        localCounter++;
                    } else if (localCounter == 1) {
                        t.setTilePosition(startX, startY);
                        startY -= 10;
                        startX += 35;
                        localCounter--;
                        counter++;
                    }
                } else {
                    counter = 1;
                    localCounter = 0;
                    startX += 30;
                    this.incrementalDirection = IncrementalDirection.DOWN;
                    this.nextIncrementalPosition(t);
                }
            }
            case DOWN -> {
                if (counter % 18 != 0) {
                    if (localCounter == 0) {
                        t.rotateTile(90);
                        t.setTilePosition(startX, startY);
                        startX -= 10;
                        localCounter++;
                    } else if (localCounter == 1) {
                        t.rotateTile(90);
                        t.setTilePosition(startX, startY);
                        startX += 10;
                        startY += 35;
                        localCounter--;
                        counter++;
                    }
                } else {
                    counter = 1;
                    localCounter = 0;
                    this.incrementalDirection = IncrementalDirection.LEFT;
                    startY -= 60;
                    startX -= 65;
                    this.nextIncrementalPosition(t);
                }
            }
            case LEFT -> {
                if (counter % 19 != 0) {
                    if (localCounter == 0) {
                        t.setTilePosition(startX, startY);
                        startY += 10;
                        localCounter++;
                    } else if (localCounter == 1) {
                        t.setTilePosition(startX, startY);
                        startY -= 10;
                        startX -= 37;
                        localCounter--;
                        counter ++;
                    }
                } else {
                    counter = 1;
                    localCounter = 0;
                    this.incrementalDirection = IncrementalDirection.UP;
                    startX -= 35;
                    this.nextIncrementalPosition(t);
                }
            }
            case UP -> {
                if (counter % 18 != 0) {
                    if (localCounter == 0) {
                        t.rotateTile(90);
                        t.setTilePosition(startX, startY);
                        startX += 10;
                        localCounter++;
                    } else if (localCounter == 1) {
                        t.rotateTile(90);
                        t.setTilePosition(startX, startY);
                        startX -= 10;
                        startY -= 35;
                        localCounter--;
                        counter++;
                    }
                } else {
                    counter = 1;
                    localCounter = 0;
                    this.incrementalDirection = IncrementalDirection.RIGHT;
                    this.nextIncrementalPosition(t);
                }
            }
        }
    }

    public int getBoardScore() {
        return this.board.getScore();
    }

    /**
     * Getters and Setters
     */
    public LinkedList<GameObject> getObjects() {
        return objects;
    }

    public int getGamePortWidth() {
        return this.game.getWidth();
    }

    public int getGamePortHeight() {
        return this.game.getHeight();
    }

    public Player pollPlayer() {
        return this.board.pollPlayer();
    }

    public Game.GAME_STATE getGameState() {
        return game.getGameState();
    }

    public void setGameState(Game.GAME_STATE gameState) {
        game.setGameState(gameState);
    }

    public WindDirection getWindDirection() {
        return this.board.getWindDirection();
    }
}
