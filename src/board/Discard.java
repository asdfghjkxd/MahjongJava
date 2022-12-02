package board;

import pieces.Tile;
import utils.Container;

import javax.swing.*;
import java.util.Stack;

public final class Discard implements Container {
    // Discard pile is a stack to maintain recency of Tile discard
    private final Stack<Tile> discardPile = new Stack<>();

    public void acceptDiscard(Tile t) {
        discardPile.add(t);
    }

    public Tile distributeDiscard() {
        if (!discardPile.isEmpty()) {
            return discardPile.pop();
        } else {
            JOptionPane.showMessageDialog(null,"Discard Pile is empty!");
            return null;
        }
    }

    @Override
    public void resetContainer() {
        discardPile.clear();
    }
}
