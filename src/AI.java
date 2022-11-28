import java.awt.*;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class AI extends Player{
    private final List<Tile> publicHand = new LinkedList<Tile>();
    private int StartingX = x_coord;
    private int StartingY = y_coord;
    private Game game;
    private boolean is_rotated = false;

    public AI(int startingX, int startingY, Game g, String name, ID id) {
        super(startingX, startingY, g, name, id);
    }

    public void rotateAIPlayer() {
        is_rotated = true;
    }

    @Override
    public void tick() {
        ;
    }

    @Override
    public void render(Graphics g) {
        ;
    }

    @Override
    protected void keepHandSorted() {
        if (is_rotated) {
            // reset all tiles in the inventory
            this.StartingY = this.y_coord;
            for (Tile t: inventory) {
                t.setTilePosition(x_coord, StartingY);
                t.rotateTile(90);
                this.StartingY += 40;
            }
        } else {
            Collections.sort(inventory);

            // reset all tiles in the inventory
            this.StartingX = this.x_coord;
            for (Tile t : inventory) {
                t.setTilePosition(StartingX, y_coord);
                this.StartingX += 50;
            }
        }
    }
}
