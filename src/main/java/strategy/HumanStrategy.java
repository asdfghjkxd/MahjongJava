package strategy;

import io.BlockingIntegerInput;
import pieces.Tile;

import javax.swing.*;
import java.awt.*;
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

    private synchronized int pollHelper(List<Tile> inputTiles) {
        int value = -Integer.MAX_VALUE;

        String toRetrieve = JOptionPane.showInputDialog(null, "Enter in a valid number " +
                "corresponding to the index of the tile to discard", "Discard Tile", JOptionPane.QUESTION_MESSAGE);


        try {
            value = Integer.parseInt(toRetrieve);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Invalid number!", "Error", JOptionPane.ERROR_MESSAGE);
            pollHelper(inputTiles);
        }

        System.out.println(value);
        return value;
    }

    @Override
    public void onTileDiscard(List<Tile> inputTiles) {
        Collections.sort(inputTiles);
    }
}
