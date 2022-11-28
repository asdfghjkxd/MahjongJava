import java.util.*;
import java.util.stream.Collectors;

public class Board {
    private final Stack<Tile> undrawnTiles;
    private final Stack<Tile> discardedTiles;
    private final Handler boardHandler;
    private boolean addedToHandler = false;
    private final Game game;


    public Board(Handler handler, Game game) {
        undrawnTiles = new Stack<Tile>();
        discardedTiles = new Stack<Tile>();
        boardHandler = handler;
        initialise();
        this.game = game;
    }

    private void initialise() {
        int[] coords = new int[] {0, 0};

        for (int i = 0; i < 4; i++) {
            for (int j = 1; j <= 9; j++) {
                undrawnTiles.add(new Tile(coords[0], coords[1], "suit", "number", j, ID.Tile));
                undrawnTiles.add(new Tile(coords[0], coords[1], "suit", "circle", j, ID.Tile));
                undrawnTiles.add(new Tile(coords[0], coords[1], "suit", "bamboo", j, ID.Tile));

            }

            undrawnTiles.add(new Tile(coords[0], coords[1], "honour", "wind", "north", ID.Tile));
            undrawnTiles.add(new Tile(coords[0], coords[1], "honour", "wind", "south", ID.Tile));
            undrawnTiles.add(new Tile(coords[0], coords[1], "honour", "wind", "east", ID.Tile));
            undrawnTiles.add(new Tile(coords[0], coords[1], "honour", "wind", "west", ID.Tile));
            undrawnTiles.add(new Tile(coords[0], coords[1], "honour", "dragon", "white", ID.Tile));
            undrawnTiles.add(new Tile(coords[0], coords[1], "honour", "dragon", "green", ID.Tile));
            undrawnTiles.add(new Tile(coords[0], coords[1], "honour", "dragon", "red", ID.Tile));
        }

        for (int i = 1; i <= 4; i++) {
            undrawnTiles.add(new Tile(coords[0], coords[1], "bonus", "flower", "BF" + i, ID.Tile));
            undrawnTiles.add(new Tile(coords[0], coords[1], "bonus", "flower", "RF" + i, ID.Tile));
        }

        undrawnTiles.add(new Tile(coords[0], coords[1], "bonus", "animal", "cat", ID.Tile));
        undrawnTiles.add(new Tile(coords[0], coords[1], "bonus", "animal", "rat", ID.Tile));
        undrawnTiles.add(new Tile(coords[0], coords[1], "bonus", "animal", "rooster", ID.Tile));
        undrawnTiles.add(new Tile(coords[0], coords[1], "bonus", "animal", "centipede", ID.Tile));

        this.shuffleBoard(new Random().nextInt(100));

        addObjectsToHandler();
    }

    private void addObjectsToHandler() {
        if (!addedToHandler && !undrawnTiles.isEmpty()) {
            for (Tile t: undrawnTiles) {
                if (boardHandler.objects.contains(t)) {
                    t.resetCurrentOwner();
                }

                boardHandler.nextPosition(t);
                boardHandler.addObject(t);
            }

            addedToHandler = true;
        }
    }

    public void drawFromBoard(Player p) {
        synchronized (this.game) {
            if (!undrawnTiles.isEmpty()) {
                p.handleTileAddition(undrawnTiles.pop());
            }
        }
    }

    public void discardToBoard(Player p, Tile t) {
        synchronized (this.game) {
            if (t.getCurrentOwner() == p) {
                t.setCurrentOwner(null);
                p.getInventory().remove(t);
                this.discardedTiles.add(t);
            } else {
                throw new IllegalArgumentException("Unknown player or tile");
            }
        }
    }


    public Tile interveningAction(Player p, Game game) {
        if (discardedTiles.isEmpty()) {
            return null;
        } else if (p.canIntervene(discardedTiles.peek())){
            p.handleTileAddition(undrawnTiles.pop());
        }

        return null;
    }

    private void shuffleBoard(int times) {
        // System.out.println("Shuffling " + times + " times...");
        for (int i = 0; i < times; i++) {
            Collections.shuffle(undrawnTiles);
        }
    }

    public void resetBoard() {
        undrawnTiles.empty();
        discardedTiles.empty();
        initialise();
    }

    public void showBoard() {
        System.out.println(undrawnTiles.toString());
    }
}
