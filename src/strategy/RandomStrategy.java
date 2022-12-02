package strategy;

import pieces.Tile;

/**
 * Random Strategy: Choosing a random tile and discarding it, without checking if the tile is needed or not needed
 * to form winning patterns
 */
public class RandomStrategy implements Strategy {
    public RandomStrategy() {
    }

    @Override
    public void onTileReceive() {
    }

    @Override
    public Tile pollDiscardTile() {
        return null;
    }

    @Override
    public void onTileDiscard() {

    }
}
