package pieces;

/**
 * This is a class used by the algorithm to tag tiles as winners
 * or visited
 */
public class WrappedTile extends Tile {
    public boolean visited = false;
    public boolean winning = false;
    public Tile tile;

    public WrappedTile(Tile tile) {
        super();
        this.tile = tile;
    }

    public void markVisited() {
        visited = true;
    }

    public void markWinning() {
        winning = true;
    }

    public void markUnvisited() {
        visited = false;
    }

    public void markUnwinning() {
        winning = false;
    }

    public void resetStatus() {
        winning = false;
        visited = false;
    }

    public Tile getTile() {
        return tile;
    }

    // Wrapped tiles are treated the same as the tile it wraps around
    @Override
    public int hashCode() {
        return tile.hashCode();
    }
}
