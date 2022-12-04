package strategy;

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
        int item = -1;

        while (item < 0) {
            try {
                item = Integer.parseInt(JOptionPane.showInputDialog("Enter in the position of tile to remove: "));
            } catch (NumberFormatException e) {
                pollHelper(inputTiles);
            }
        }

        return item;
    }

    @Override
    public void onTileDiscard(List<Tile> inputTiles) {
        Collections.sort(inputTiles);
    }
}
