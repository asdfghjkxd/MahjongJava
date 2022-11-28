import net.coobird.thumbnailator.Thumbnails;

import java.awt.*;
import java.io.IOException;

public class Tile extends GameObject implements Comparable<Tile> {
    private Player owner = null;
    private int rotationDegree = 0;

    public Tile(int x, int y, String tileClass, String tileSubclass, Object value, ID id) {
        super(x, y, tileClass, tileSubclass, value, id);
    }

    @Override
    public void tick() {
        // tiles are static and do not need to be ticked;
    }

    @Override
    public void render(Graphics g) throws IOException{
        if (rotationDegree != 0) {
            if (this.owner != null && this.owner.getId() == ID.Player) {
                g.drawImage(Thumbnails.of(TileClass.queryImage(getCanonicalTileSubclass(), getCanonicalValue()))
                                .size(TileClass.maxWidth, TileClass.maxHeight)
                                .rotate(rotationDegree).asBufferedImage(), x_coord, y_coord, null);
            } else {
                g.drawImage(Thumbnails.of(TileClass.backOfTile).rotate(rotationDegree)
                                .size(TileClass.maxWidth, TileClass.maxHeight)
                                .asBufferedImage(),
                        x_coord, y_coord, null);
            }
        } else {
            if (this.owner != null && this.owner.getId() == ID.Player) {
                g.drawImage(TileClass.queryImage(getCanonicalTileSubclass(),
                        getCanonicalValue()), x_coord, y_coord, null);
            } else {
                g.drawImage(TileClass.backOfTile, x_coord, y_coord, null);
            }
        }
    }

    public final Player getCurrentOwner() {
        return owner;
    }

    public final String getCurrentOwnerName() {
        return owner == null ? "No owner" : owner.getCanonicalName();
    }

    public final void setCurrentOwner(Player currentOwner) {
        if (currentOwner.getId() == ID.Player || currentOwner.getId() == ID.AI) {
            if (owner == null) {
                owner = currentOwner;
            } else {
                throw new IllegalArgumentException("Cannot get a tile that belongs to another player");
            }
        } else {
            throw new IllegalArgumentException("Cannot set tile owner to a non-player class");
        }

    }

    public final void setTilePosition(int x, int y) {
        this.x_coord = x;
        this.y_coord = y;
    }

    public final void rotateTile(int rotationDegree) {
        this.rotationDegree = rotationDegree % 360;
    }

    /**
     * Sets the partial order of tiles in the set
     * @param o the object to be compared.
     * @return One of [-1, 0, 1]
     */
    @Override
    public int compareTo(Tile o) {
        if (this.getCanonicalTileClass().compareTo(o.getCanonicalTileClass()) != 0) {
            return (- this.getCanonicalTileClass().compareTo(o.getCanonicalTileClass()));
        } else if (this.getCanonicalTileSubclass().compareTo(o.getCanonicalTileSubclass()) != 0){
            return this.getCanonicalTileSubclass().compareTo(o.getCanonicalTileSubclass());
        } else {
            if (this.getCanonicalValue() instanceof Integer && o.getCanonicalValue() instanceof Integer) {
                return ((Integer) this.getCanonicalValue()).compareTo((Integer) o.getCanonicalValue());
            } else {
                return ((String) this.getCanonicalValue()).compareTo((String) o.getCanonicalValue());
            }
        }

    }

    @Override
    public String toString() {
        return String.valueOf(getCanonicalTileClass().toUpperCase().charAt(0)) +
                getCanonicalTileSubclass().toUpperCase().charAt(0) + "_" +
                (getCanonicalValue() instanceof String
                        ? getCanonicalValue().toString().toUpperCase()
                        : getCanonicalValue().toString());
    }
}
