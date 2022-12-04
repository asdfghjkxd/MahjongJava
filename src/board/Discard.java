package board;

import pieces.Tile;
import utils.Container;

import javax.swing.*;
import java.util.Stack;

public final class Discard implements Container {
    // Discard pile is a stack to maintain a LIFO order on discarded tiles
    private final Stack<Tile> discardPile = new Stack<>();

    // Interface methods
    @Override
    public void resetContainer() {
        discardPile.clear();
    }

    // Interface methods
    @Override
    public void acceptItem(Tile tile) {
        discardPile.add(tile);
    }

    @Override
    public Tile discardItem() {
        if (!discardPile.isEmpty()) {
            return discardPile.pop();
        } else {
            JOptionPane.showMessageDialog(null,"Discard Pile is empty!");
            return null;
        }
    }

    @Override
    public Tile discardItem(Tile t) {
        // specific tiles cannot be removed from the discard pile
        // only the top of the container can be accessed and removed
        return null;
    }
}
