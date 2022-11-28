import java.awt.*;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Player extends GameObject{
    private final List<Tile> publicHand = new LinkedList<Tile>();
    private int StartingX = x_coord;
    private final Game game;

    public Player(int startingX, int startingY, Game g, String name, ID id) {
        super(startingX, startingY, name, new LinkedList<Tile>(), id);
        game = g;
    }

    @Override
    public void tick() {
        synchronized (this.game) {

        }
    }

    @Override
    public void render(Graphics g) {
        ;
    }


    // Pattern matching
    public final boolean hasPong() {
        return true;
    }

    public final boolean hasKang() {
        return true;
    }

    public final boolean hasEyes() {
        for (int i = 0; i < inventory.size() - 1; i++) {
            if (inventory.get(i).equals(inventory.get(i + 1))) {
                return true;
            }
        }

        return false;
    }

    public final boolean canIntervene(Tile t) {
        return true;
    }

    public final void handleTileAddition(Tile t) {
        if (t.getCurrentOwner() == null) {
            t.setCurrentOwner(this);
            t.rotateTile(0);
            this.setTilePosition(t);
            inventory.add(t);
            keepHandSorted();
        } else {
            throw new IllegalArgumentException("Cannot add a tile that belongs to another player!");
        }
    }

    public final void handleTileDisposal(Tile t) {
        if (t.getCurrentOwner() == this) {
            game.getBoard().discardToBoard(this, t);
        } else {
            throw new IllegalArgumentException("Tile does not belong to player!");
        }
    }

    protected void keepHandSorted() {
        Collections.sort(inventory);

        // reset all tiles in the inventory
        this.StartingX = this.x_coord;
        for (Tile t: inventory) {
            t.setTilePosition(StartingX, y_coord);
            this.StartingX += 50;
        }
    }

    public void rotateAIPlayer() {
        ; // does nothing
    }

    protected void setTilePosition(Tile t) {
        t.setTilePosition(this.StartingX, this.y_coord);
        this.StartingX += 50;
    }

    protected void setPlayerPosition(int x, int y) {
        this.x_coord = x;
        this.y_coord = y;
    }

    public final void reset() {
        for (Tile t: inventory) {
            t.resetCurrentOwner();
        }

        for (Tile t: publicHand) {
            t.resetCurrentOwner();
        }

        this.inventory.clear();
        this.publicHand.clear();
    }
}
