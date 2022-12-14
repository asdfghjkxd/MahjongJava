package strategy;

import io.BlockingIntegerInput;
import pieces.Tile;

import javax.swing.*;
import java.util.Collections;
import java.util.List;

public class HumanStrategy implements Strategy{
    @Override
    public void onTileReceive(List<Tile> inputTiles) {
        Collections.sort(inputTiles);
    }

    @Override
    public Tile pollDiscardTile(List<Tile> inputTiles) {
        int pos = pollHelper(inputTiles);
        Tile removed = inputTiles.get(pos);
        inputTiles.remove(removed);
        return removed;
    }

    private int pollHelper(List<Tile> inputTiles) {
        BlockingIntegerInput.getInput("Discard Tile", "Select a tile to remove", JOptionPane.QUESTION_MESSAGE);
        return BlockingIntegerInput.retrieveInput();
    }

    @Override
    public void onTileDiscard(List<Tile> inputTiles) {
        Collections.sort(inputTiles);
    }
}
