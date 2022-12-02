package strategy;

import pieces.Tile;

/**
 * Manages functions necessary for a player to implement in order to manage how they are to deal with tiles they
 * receive
 */
public interface Strategy {
    void onTileReceive();
    Tile pollDiscardTile();
    void onTileDiscard();
}
