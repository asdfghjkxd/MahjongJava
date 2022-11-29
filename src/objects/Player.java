package objects;

import base.Playable;
import core.Game;
import core.Handler;
import core.ID;

import javax.swing.*;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Player extends GameObject implements Playable {
    private final List<Tile> publicHand = new LinkedList<Tile>();
    private final List<Tile> privateHand = new LinkedList<Tile>();
    private int StartingX = x;
    private final Handler handler;
    private final String name;
    private int bonusScore;
    private int score;
    private PLAYER_STATE playerState = PLAYER_STATE.WAITING;
    public enum PLAYER_STATE {
        WAITING,
        POLLING
    }


    public Player(int x, int y, Handler handler, String name, ID id) {
        super(x, y, id);
        this.handler = handler;
        this.name = name;
    }

    @Override
    public void draw(Tile tile) {
        if (tile.setCurrentOwner(this)) {
            tile.rotateTile(0);

            if (tile.getTileClass().equalsIgnoreCase("bonus")) {
                tile.acquireTile(this);
                this.publicHand.add(tile);
                // TODO: force the drawing player to draw again via handler
            } else {
                tile.acquireTile(this);
                this.setTilePosition(tile);
                this.privateHand.add(tile);
            }

            keepHandSorted();
        } else {
            JOptionPane.showMessageDialog(null, "Cannot add tile that belongs to someone else");
        }
    }

    @Override
    public void discard(Tile tile) {
        if (privateHand.contains(tile)) {
            JOptionPane.showMessageDialog(null, "Cannot discard bonus tiles");
        } else {
            if (tile.discardTile()) {
                this.handler.acceptAndDiscardTile(this, tile);
                this.publicHand.remove(tile);
            }
        }
    }

    public void forceDiscard(Tile tile) {
        this.handler.acceptAndDiscardTile(this, tile);
    }

    public final void resetPlayer() {
        for (Tile t: this.privateHand) {
            this.discard(t);
        }

        for (Tile t: this.publicHand) {
            this.forceDiscard(t);
        }
    }

    protected void setTilePosition(Tile tile) {
        tile.setTilePosition(this.StartingX, this.y);
        this.StartingX += 50;
    }

    protected void setPlayerPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    protected void keepHandSorted() {
        Collections.sort(privateHand);

        // reset all tiles in the inventory
        this.StartingX = this.x;
        for (Tile t: privateHand) {
            t.setTilePosition(StartingX, y);
            this.StartingX += 50;
        }
    }

    public final String getName() {
        return this.name;
    }
}
