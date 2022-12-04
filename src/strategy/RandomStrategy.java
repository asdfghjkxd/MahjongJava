package strategy;

import pieces.Tile;

import java.util.List;

/**
 * Random Strategy: Choosing a random tile and discarding it, without checking if the tile is needed or not needed
 * to form winning patterns
 */
public class RandomStrategy implements Strategy {
    public RandomStrategy() {
    }

    @Override
    public void onTileReceive(List<Tile> inputTiles) {
    }

    @Override
    public Tile pollDiscardTile(List<Tile> inputTiles) {
        return null;
    }

    @Override
    public void onTileDiscard(List<Tile> inputTiles) {
    }
}
