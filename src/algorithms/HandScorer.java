package algorithms;

import pieces.Tile;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Stack;

public class HandScorer {
    private Stack<Tile> pushPopTiles = new Stack<>();
    private HashMap<Tile, LinkedList<Boolean>> tileTraversedWin = new HashMap<>();


    public HandScorer(LinkedList<Tile> inputTiles) {
        for (Tile t: inputTiles) {
            tileTraversedWin.putIfAbsent(t, new LinkedList<>(Arrays.asList(false, false)));
        }
    }

    public boolean backtrack() {
        while (BooleanCompressor.reduceBooleanListMapAND(tileTraversedWin, 0)) {
            break;
        }

        return false;
    }



}
