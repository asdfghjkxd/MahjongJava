package core;

import objects.Player;
import objects.Tile;

import java.util.*;

public final class Board {
    private final Handler handler;
    private final Stack<Tile> undrawnTiles;
    private final Stack<Tile> discardedTiles;
    private boolean addedToHandler = false;
    private int gamePlayerIndex;
    private LinkedList<Player> gamePlayerRoster = null;
    private final LinkedList<List<Integer>> playerPositions = new LinkedList<List<Integer>>();
    private int score;
    private WindDirection windDirection = WindDirection.NORTH;
    public enum WindDirection {
        NORTH,
        SOUTH,
        EAST,
        WEST
    }

    public Board(Handler handler) {
        this.handler = handler;
        undrawnTiles = new Stack<Tile>();
        discardedTiles = new Stack<Tile>();

        int WIDTH = this.handler.getGamePortWidth();
        int HEIGHT = this.handler.getGamePortHeight();

        playerPositions.clear();
        playerPositions.add(new LinkedList<>(Arrays.asList((int) (WIDTH / 6 * 1.5), (int) (HEIGHT / 13 * 11.5))));
        playerPositions.add(new LinkedList<>(Arrays.asList((int) (WIDTH / 25), (int) (HEIGHT / 5))));
        playerPositions.add(new LinkedList<>(Arrays.asList((int) (WIDTH / 6 * 1.5), (int) (HEIGHT / 30))));
        playerPositions.add(new LinkedList<>(Arrays.asList((int) (WIDTH / 20 * 18.5), (int) (HEIGHT / 5))));

        this.initialise();
    }

    private void initialise() {
        for (int i = 0; i < 4; i++) {
            for (int j = 1; j <= 9; j++) {
                undrawnTiles.add(new Tile(0, 0, "suit", "number", j, ID.Tile));
                undrawnTiles.add(new Tile(0, 0, "suit", "circle", j, ID.Tile));
                undrawnTiles.add(new Tile(0, 0, "suit", "bamboo", j, ID.Tile));

            }

            undrawnTiles.add(new Tile(0, 0, "honour", "wind", "north", ID.Tile));
            undrawnTiles.add(new Tile(0, 0, "honour", "wind", "south", ID.Tile));
            undrawnTiles.add(new Tile(0, 0, "honour", "wind", "east", ID.Tile));
            undrawnTiles.add(new Tile(0, 0, "honour", "wind", "west", ID.Tile));
            undrawnTiles.add(new Tile(0, 0, "honour", "dragon", "white", ID.Tile));
            undrawnTiles.add(new Tile(0, 0, "honour", "dragon", "green", ID.Tile));
            undrawnTiles.add(new Tile(0, 0, "honour", "dragon", "red", ID.Tile));
        }

        for (int i = 1; i <= 4; i++) {
            undrawnTiles.add(new Tile(0, 0, "bonus", "flower", "BF" + i, ID.Tile));
            undrawnTiles.add(new Tile(0, 0, "bonus", "flower", "RF" + i, ID.Tile));
        }

        undrawnTiles.add(new Tile(0, 0, "bonus", "animal", "cat", ID.Tile));
        undrawnTiles.add(new Tile(0, 0, "bonus", "animal", "rat", ID.Tile));
        undrawnTiles.add(new Tile(0, 0, "bonus", "animal", "rooster", ID.Tile));
        undrawnTiles.add(new Tile(0, 0, "bonus", "animal", "centipede", ID.Tile));

        this.shuffleBoard(new Random().nextInt(100));

        addObjectsToHandler();
    }

    private void addObjectsToHandler() {
        handler.getObjects().clear();

        if (!addedToHandler && !undrawnTiles.isEmpty()) {
            for (Tile t: undrawnTiles) {
                if (handler.getObjects().contains(t)) {
                    t.resetTile();
                    handler.getObjects().remove(t);
                }

                handler.nextIncrementalPosition(t);
                handler.addObject(t);
            }

            addedToHandler = true;
        }
    }

    public void drawFromBoard(Player p) {
        synchronized (this.handler) {
            if (!undrawnTiles.isEmpty()) {
                p.draw(undrawnTiles.pop());
            }
        }
    }

    public void discardToBoard(Player p, Tile t) {
        synchronized (this.handler) {
            if (t.getCurrentOwner() == p) {
                t.discardTile();
                this.discardedTiles.add(t);
            } else {
                throw new IllegalArgumentException("Unknown player or tile");
            }
        }
    }

    private void shuffleBoard(int times) {
        // System.out.println("Shuffling " + times + " times...");
        for (int i = 0; i < times; i++) {
            Collections.shuffle(undrawnTiles);
        }
    }

    public void resetBoard() {
        for (Player p: this.gamePlayerRoster) {
            p.resetPlayer();
        }

        for (Tile t: this.discardedTiles) {
            t.resetTile();
            this.undrawnTiles.add(t);
        }
    }

    public void showBoard() {
        System.out.println(undrawnTiles.toString());
    }

    public Player pollPlayer() {
        return this.gamePlayerRoster.get(this.gamePlayerIndex);
    }

    public int getScore() {
        return this.score;
    }

    public WindDirection getWindDirection() {
        return this.windDirection;
    }

    public void setWindDirection(WindDirection windDirection) {
        this.windDirection = windDirection;
    }
}
