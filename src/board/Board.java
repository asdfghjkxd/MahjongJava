package board;

import core.Game;
import core.Test;
import entities.AI;
import entities.Human;
import entities.Player;
import pieces.Tile;
import utils.Commandable;
import utils.Container;
import utils.Observable;

import java.awt.*;
import java.io.IOException;
import java.util.*;
import java.util.List;

public final class Board implements Container, Commandable, Observable {
    // Container is represented by a Stack of Tiles
    // Possible since all tiles are unique (even if they are from the same tile class/subclass or has the same value
    private final Stack<Tile> boardTiles = new Stack<>();

    // Board is responsible for handling the discards
    private final Discard discardedTiles = new Discard();
    private final LinkedList<Player> boardPlayers = new LinkedList<>();
    private Player currentPlayer = null;
    private int currentPlayerIndex = 0;
    private WIND_DIRECTION windDirection = WIND_DIRECTION.NORTH;
    private final LinkedList<List<Integer>> POSITIONS =
            new LinkedList<>(Arrays.asList(
                    new LinkedList<>(Arrays.asList((int) (Game.WIDTH / 6 * 1.5), (int) (Game.HEIGHT / 13 * 11.5))),
                    new LinkedList<>(Arrays.asList((Game.WIDTH / 25), (Game.HEIGHT / 5))),
                    new LinkedList<>(Arrays.asList((int) (Game.WIDTH / 6 * 1.5), (Game.HEIGHT / 30))),
                    new LinkedList<>(Arrays.asList((int) (Game.WIDTH / 20 * 18.5), (Game.HEIGHT / 5)))
            ));
    private BOARD_PLACEMENT_DIRECTION direction = BOARD_PLACEMENT_DIRECTION.RIGHT;
    private int counter = 1;
    private int localCounter = 0;
    private int startX = 300;
    private int startY = 130;
    public enum WIND_DIRECTION {
        NORTH,
        SOUTH,
        EAST,
        WEST
    }
    private enum BOARD_PLACEMENT_DIRECTION {
        UP,
        DOWN,
        LEFT,
        RIGHT
    }

    public Board() {
        // resetGame();
    }


    // Instantiation of the Board and Players
    public void resetGame() {
        // reset board tile placement parameters
        startX = 300;
        startY = 130;
        direction = BOARD_PLACEMENT_DIRECTION.RIGHT;
        counter = 1;
        localCounter = 0;

        // reset the board items
        resetContainer();
        instantiateBoard();
        instantiatePlayers();
        currentPlayer = boardPlayers.get(currentPlayerIndex);
    }

    private void instantiateBoard() {
        for (int i = 1; i <= 4; i++) {
            for (int j = 1; j < 10; j++) {
                // suits
                boardTiles.add(new Tile(0, 0, "suit", "number", String.valueOf(j), this, 0));
                boardTiles.add(new Tile(0, 0, "suit", "circle", String.valueOf(j), this, 0));
                boardTiles.add(new Tile(0, 0, "suit", "bamboo", String.valueOf(j), this, 0));
            }

            // honours
            boardTiles.add(new Tile(0, 0, "honour", "wind", "north", this, 0));
            boardTiles.add(new Tile(0, 0, "honour", "wind", "south", this, 0));
            boardTiles.add(new Tile(0, 0, "honour", "wind", "east", this, 0));
            boardTiles.add(new Tile(0, 0, "honour", "wind", "west", this, 0));
            boardTiles.add(new Tile(0, 0, "honour", "dragon", "white", this, 0));
            boardTiles.add(new Tile(0, 0, "honour", "dragon", "green", this, 0));
            boardTiles.add(new Tile(0, 0, "honour", "dragon", "red", this, 0));

            // bonus
            boardTiles.add(new Tile(0, 0, "bonus", "flower", "bf" + i, this, 0));
            boardTiles.add(new Tile(0, 0, "bonus", "flower", "rf" + i, this, 0));
        }

        boardTiles.add(new Tile(0, 0, "bonus", "animal", "cat", this, 0));
        boardTiles.add(new Tile(0, 0, "bonus", "animal", "rat", this, 0));
        boardTiles.add(new Tile(0, 0, "bonus", "animal", "rooster", this, 0));
        boardTiles.add(new Tile(0, 0, "bonus", "animal", "centipede", this, 0));

        // shuffle the board randomly
        Collections.shuffle(boardTiles, new Random());

        for (Tile t: boardTiles) {
            arrangeBoardTiles(t);
        }
    }

    private void instantiatePlayers() {
        if (boardPlayers.isEmpty()) {
            Human human = new Human(0, 0, this);
            AI ai1 = new AI(0, 0, 0, this);
            AI ai2 = new AI(0, 0, 0, this);
            AI ai3 = new AI(0, 0, 0, this);

            this.boardPlayers.add(human);
            this.boardPlayers.add(ai1);
            this.boardPlayers.add(ai2);
            this.boardPlayers.add(ai3);

            // Shuffle the players
            Collections.shuffle(boardPlayers, new Random());

            List<Player> playerList = boardPlayers.stream().filter(x -> x instanceof Human).toList();
            List<Player> AIList = boardPlayers.stream().filter(x -> x instanceof AI).toList();

            for (Player p: playerList) {
                List<Integer> pos = POSITIONS.get(0);
                p.setRotationDegrees(0);
                p.setPlayerPosition(pos.get(0), pos.get(1));
            }

            for (int i = 1; i <= AIList.size(); i++) {
                Player curr = AIList.get(i - 1);
                List<Integer> pos = POSITIONS.get(i);

                if (i % 2 == 1) {
                    curr.setRotationDegrees(90);
                    curr.setPlayerPosition(pos.get(0), pos.get(1));
                } else {
                    curr.setRotationDegrees(0);
                    curr.setPlayerPosition(pos.get(0), pos.get(1));
                }
            }

            // TODO: decide if initial distribution should be done during player setup or after
            initialDistribution();
        } else {
            // if it is not empty for any reason, clear the players then reset it
            boardPlayers.clear();
            instantiatePlayers();
        }
    }

    private Tile distributeBoardTile() {
        return boardTiles.pop();
    }

    private void initialDistribution() {
        for (Player p: boardPlayers) {
            for (int i = 0; i < 13; i++) {
                distributeToPlayer(p);
            }

            p.sortHand();
        }

    }

    private void arrangeBoardTiles(Tile t) {
        switch (direction) {
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
                        counter += 1;
                    }
                } else {
                    counter = 1;
                    localCounter = 0;
                    direction = BOARD_PLACEMENT_DIRECTION.DOWN;
                    startX += 30;
                    this.arrangeBoardTiles(t);
                }
            }
            case DOWN -> {
                if (counter % 18 != 0) {
                    if (localCounter == 0) {
                        t.setRotationDegrees(90);
                        t.setTilePosition(startX, startY);
                        startX -= 10;
                        localCounter++;
                    } else if (localCounter == 1) {
                        t.setRotationDegrees(90);
                        t.setTilePosition(startX, startY);
                        startX += 10;
                        startY += 35;
                        localCounter--;
                        counter++;
                    }
                } else {
                    counter = 1;
                    localCounter = 0;
                    direction = BOARD_PLACEMENT_DIRECTION.LEFT;
                    startY -= 60;
                    startX -= 65;
                    this.arrangeBoardTiles(t);
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
                    startX -= 35;
                    this.direction = BOARD_PLACEMENT_DIRECTION.UP;
                    this.arrangeBoardTiles(t);
                }
            }
            case UP -> {
                if (counter % 18 != 0) {
                    if (localCounter == 0) {
                        t.setRotationDegrees(90);
                        t.setTilePosition(startX, startY);
                        startX += 10;
                        localCounter++;
                    } else if (localCounter == 1) {
                        t.setRotationDegrees(90);
                        t.setTilePosition(startX, startY);
                        startX -= 10;
                        startY -= 35;
                        localCounter--;
                        counter++;
                    }
                } else {
                    counter = 1;
                    localCounter = 0;
                    direction = BOARD_PLACEMENT_DIRECTION.RIGHT;
                    this.arrangeBoardTiles(t);
                }
            }
        }
    }

    private void distributeToPlayer(Player p) {
        Tile t = distributeBoardTile();
        if (t.getTileClass().equals("bonus")) {
            p.acceptItem(t);
            distributeToPlayer(p);
        } else {
            p.acceptItem(t);
        }
    }

    // Discard pile methods abstracted out to the board class to be managed
    public void discardToDiscardPile(Tile t) {
        discardedTiles.acceptItem(t);
    }

    public Tile takeFromDiscardPile() {
        return discardedTiles.discardItem();
    }

    // Player control
    public void advancePlayer() {
        this.currentPlayerIndex = (this.currentPlayerIndex + 1) % 4;
    }

    public void enforcePlayerAction() {

    }

    // Interface methods
    @Override
    public void resetContainer() {
        this.boardTiles.clear();;
        this.discardedTiles.resetContainer();
        for (Player p: boardPlayers) {
            p.resetContainer();
        }
        boardPlayers.clear();
    }

    @Override
    public void acceptItem(Tile tile) {
        // board does not accept items
    }

    @Override
    public Tile discardItem() {
        // board cannot discard items away
        return null;
    }

    @Override
    public Tile discardItem(Tile t) {
        // board cannot discard items away
        return null;
    }

    @Override
    public void synchronise_ticks() {

    }

    @Override
    public void synchronise_renders(Graphics g) throws IOException {
        for (Tile t: boardTiles) {
            t.render(g);
        }

        for (Player p: boardPlayers) {
            p.render(g);
        }

    }

    @Override
    public String toString() {
        return "Board Instance";
    }

    // Test functions
    public void testBoard() {
        if (Test.test_state.equals(Test.TEST_STATE.TESTING)) {
            System.out.println("Board size: " + boardTiles.size());
            for (Player p : boardPlayers) {
                System.out.println(p.toString());
            }

            System.out.println(boardTiles);
        }
    }

    // Getters and Setters
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public WIND_DIRECTION getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(WIND_DIRECTION windDirection) {
        this.windDirection = windDirection;
    }
}
