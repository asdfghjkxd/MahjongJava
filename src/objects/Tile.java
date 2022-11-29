package objects;

import base.TickRender;
import core.Game;
import core.ID;
import net.coobird.thumbnailator.Thumbnails;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public final class Tile extends GameObject implements TickRender, Comparable<Tile> {
    private Player owner = null;
    private int rotationDegrees = 0;
    private String tileClass;
    private String tileSubclass;
    private Object value;
    private TILE_STATE tileState = TILE_STATE.IN_DECK;

    public enum TILE_STATE {
        IN_DECK,
        OWNED_BY_PLAYER,
        OWNED_BY_AI,
        DISCARDED
    }

    public Tile(int x, int y, String tileClass, String tileSubclass, Object value, ID id) {
        super(x, y, id);
        this.tileClass = tileClass;
        this.tileSubclass = tileSubclass;
        this.value = value;
    }

    @Override
    public void tick() {
        
    }

    @Override
    public void render(Graphics g) throws IOException {
        if (tileState.equals(TILE_STATE.OWNED_BY_PLAYER)) {
            g.drawImage(Thumbnails.of(TileClass.queryImage(this.tileSubclass, this.value))
                    .size(TileClass.maxWidth, TileClass.maxHeight)
                    .rotate(this.rotationDegrees).asBufferedImage(), this.x, this.y, null);
        } else {
            g.drawImage(Thumbnails.of(TileClass.backOfTile).rotate(this.rotationDegrees)
                            .size(TileClass.maxWidth, TileClass.maxHeight)
                            .asBufferedImage(), this.x, this.y, null);
        }
    }

    /**
     * Utility Functions
     */
    public void setTilePosition(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public void rotateTile(int rotationDegrees) {
        this.rotationDegrees = Math.abs( rotationDegrees % 360);
    }

    public boolean discardTile() {
        switch (this.tileState) {
            case OWNED_BY_AI, OWNED_BY_PLAYER -> {
                this.owner = null;
                this.tileState = TILE_STATE.DISCARDED;
                return true;
            }
            case DISCARDED, IN_DECK -> {
                JOptionPane.showMessageDialog(null, "Cannot discard tiles not owned by anyone");
                return false;
            }
        }

        return false;
    }

    public void acquireTile(Player player) {
        switch (this.tileState) {
            case OWNED_BY_AI, OWNED_BY_PLAYER -> {
                JOptionPane.showMessageDialog(null, "Cannot acquire tiles owned by someone");
            }
            case DISCARDED, IN_DECK -> {
                this.owner = player;

                if (player.getId() == ID.Player) {
                    this.tileState = TILE_STATE.OWNED_BY_PLAYER;
                } else if (player.getId() == ID.AI) {
                    this.tileState = TILE_STATE.OWNED_BY_AI;
                }
            }
        }
    }

    public void resetTile() {
        this.owner = null;
        this.tileState = TILE_STATE.IN_DECK;
    }

    /**
     * Tile getters and setters
     */
    public Player getCurrentOwner() {
        return owner;
    }

    public String getTileClass() {
        return tileClass;
    }

    public String getTileSubclass() {
        return tileSubclass;
    }

    public Object getValue() {
        return value;
    }

    public boolean setCurrentOwner(Player currentOwner) {
        switch (currentOwner.getId()) {
            case Player -> {
                if (tileState.equals(TILE_STATE.IN_DECK)) {
                    this.owner = currentOwner;
                    this.tileState = TILE_STATE.OWNED_BY_PLAYER;
                    return true;
                }
                return false;
            }
            case AI -> {
                if (tileState.equals(TILE_STATE.IN_DECK)) {
                    this.owner = currentOwner;
                    this.tileState = TILE_STATE.OWNED_BY_AI;
                    return true;
                }
                return false;
            }
            default -> {
                JOptionPane.showMessageDialog(null, "Non-players cannot own tiles");
                return false;
            }
        }
    }

    /**
     * Comparators
     */
    @Override
    public int compareTo(Tile o) {
        if (this.getTileClass().compareTo(o.getTileClass()) != 0) {
            return (- this.getTileClass().compareTo(o.getTileClass()));
        } else if (this.getTileSubclass().compareTo(o.getTileSubclass()) != 0){
            return this.getTileSubclass().compareTo(o.getTileSubclass());
        } else {
            if (this.getValue() instanceof Integer && o.getValue() instanceof Integer) {
                return ((Integer) this.getValue()).compareTo((Integer) o.getValue());
            } else {
                return ((String) this.getValue()).compareTo((String) o.getValue());
            }
        }

    }

    public boolean equals(Object obj) {
        if (obj instanceof Tile) {
            return ((Tile) obj).getTileClass().equals(this.getTileClass()) &&
                    ((Tile) obj).getTileSubclass().equals(this.getTileSubclass()) &&
                    ((Tile) obj).getValue().equals(this.getValue());
        } else {
            return false;
        }
    }

}
