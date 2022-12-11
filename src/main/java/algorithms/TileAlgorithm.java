package algorithms;

import constants.TILE_VECTOR_VALUE_INDEX;
import pieces.Tile;

import java.util.*;

public final class TileAlgorithm {
    public TreeMap<Tile, Integer> tileIntegerHashMap;

    public TileAlgorithm(List<Tile> inputTiles) {
        tileIntegerHashMap = new TreeMap<>();

        for (int i = 0; i < 46; i++) {
            tileIntegerHashMap.put(
                    new Tile(0, 0, TILE_VECTOR_VALUE_INDEX.fromValue(i), null, 0),
                    0);
        }
        
        inputTiles.forEach(x -> tileIntegerHashMap.put(
                x, tileIntegerHashMap.get(x) + 1
        ));
    }

    public void getHandSignature() {
        System.out.println(tileIntegerHashMap.toString());
    }


}
