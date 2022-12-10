package strategy;

import pieces.Tile;

import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Random Strategy: Choosing a random tile and discarding it, without checking if the tile is needed or not needed
 * to form winning patterns
 */
public class AIStrategy implements Strategy {
    Random randomizer = new Random();

    public AIStrategy() {
    }

    @Override
    public void onTileReceive(List<Tile> inputTiles) {
        Collections.sort(inputTiles);
    }

    @Override
    public Tile pollDiscardTile(List<Tile> inputTiles) {
        int random = randomizer.nextInt(0, inputTiles.size() - 1);
        Tile remove = inputTiles.get(random);
        inputTiles.remove(remove);
        return remove;
    }

    @Override
    public void onTileDiscard(List<Tile> inputTiles) {
        Collections.sort(inputTiles);
    }
}
