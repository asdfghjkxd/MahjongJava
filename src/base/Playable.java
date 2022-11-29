package base;

import objects.Tile;

public interface Playable {

    void draw(Tile tile);

    void discard(Tile tile);
}
