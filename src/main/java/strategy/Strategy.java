package strategy;

import pieces.Tile;

import java.util.List;

/**
 * Manages functions necessary for a player to implement in order to manage how they are to deal with tiles they
 * receive
 * <p>
 * All functions are required to be done inplace; the logic of evaluating hand score can be done
 * with the use of other functions, but the main interface methods should be calling those evaluative methods and
 * gathering the results of the computations
 * </p>
 */
public interface Strategy {
    void onTileReceive(List<Tile> inputTiles);
    Tile pollDiscardTile(List<Tile> inputTiles);
    void onTileDiscard(List<Tile> inputTiles);
}
