import java.awt.*;
import java.io.IOException;
import java.util.List;

/**
 * Abstract Base Class that all objects the game instantiates must extend
 */
public abstract class GameObject {
    // declare properties all objects in the game must have
    protected int x_coord;
    protected int y_coord;

    protected ID id;

    // Player
    protected String canonicalName;
    protected List<Tile> inventory;

    // Tile
    protected Object canonicalValue;
    protected String canonicalTileClass;
    protected String canonicalTileSubclass;

    public GameObject(int x, int y, String name, List<Tile> inventory, ID id) {
        this.x_coord = x;
        this.y_coord = y;
        this.canonicalName = name;
        this.inventory = inventory;
        this.id = id;
    }

    public GameObject(int x, int y, String tileClass, String tileSubclass, Object value, ID id) {
        if (TileClass.querySubclassFromClass(tileClass.toLowerCase()) == null ||
                TileClass.queryClassFromSubClass(tileSubclass.toLowerCase()) == null) {
            throw new IllegalArgumentException("Invalid tile class or subclass");
        } else {
            this.canonicalTileClass = tileClass.toLowerCase();
            this.canonicalTileSubclass = tileSubclass.toLowerCase();
            this.canonicalValue = value;
            this.x_coord = x;
            this.y_coord = y;
            this.id = id;
        }
    }

    public abstract void tick();
    public abstract void render(Graphics g) throws IOException;

    public ID getId() {
        return id;
    }

    public Object getCanonicalValue() {
        return canonicalValue;
    }

    public String getCanonicalName() {
        return canonicalName;
    }

    public String getCanonicalTileClass() {
        return canonicalTileClass;
    }

    public String getCanonicalTileSubclass() {
        return canonicalTileSubclass;
    }

    public List<Tile> getInventory() {
        return this.inventory;
    }
}
