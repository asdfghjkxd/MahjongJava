package strategy;

import pieces.Tile;

/**
 * Random Strategy: Choosing a random tile and discarding it, without checking if the tile is needed or not needed
 * to form winning patterns
 */
class RandomStrategy implements Strategy {
    RandomStrategy() {

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
