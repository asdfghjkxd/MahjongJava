package pieces;

import board.*;
import constants.TILE_VECTOR_VALUE_INDEX;
import entities.*;
import net.coobird.thumbnailator.Thumbnails;
import utils.Container;
import utils.Renderable;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * Base class for how tiles are implemented in this game
 *
 * @author George Tay
 * @see constants.TILE_VECTOR_VALUE_INDEX
 * @see Renderable
 * @see Comparable
 * @apiNote Note that the initialising of this class should be handled by the {@link Board}, and if there are
 * any loose initialisation of the tiles, the owner should be pointed to null to avoid errors
 */
public class Tile implements Comparable<Tile>, Renderable {
    private TILE_VECTOR_VALUE_INDEX tileProperty;

    public static final int maxHeight = 45;
    public static final int maxWidth = 36;
    private Container owner;
    private int rotationDegrees = 0;
    private int startingX;
    private int startingY;
    private int movingX;
    private int movingY;


    /**
     * Default constructor that instantiates nothing
     */
    public Tile() {
        // empty constructor, does nothing
    }

    /**
     * Constructor that initialises the fields for this tile
     * @param x The starting x coordinates of this tile
     * @param y The starting y coordinates of this tile
     * @param value The overall value of the tile, one of {@code TILE_VECTOR_VALUE_INDEX.*}
     * @param board An instance of {@link Board}
     * @param rotationDegrees An {@code int}, describing the degree of rotation of the tile image
     *
     * @see TILE_VECTOR_VALUE_INDEX
     * @see Board
     */
    public Tile(int x, int y, TILE_VECTOR_VALUE_INDEX value, Board board, int rotationDegrees) {
        startingX = movingX = x;
        startingY = movingY = y;
        tileProperty = value;
        owner = board;
        this.rotationDegrees = rotationDegrees;
    }

    /**
     * Renders this tile on the board
     *
     * @param g An instance of {@link Graphics} that handles drawing on the main {@link JFrame}
     * @throws IOException Thrown when there is an error with {@code Thumbnails.asBufferedImage()}
     * @see Thumbnails
     * @see Graphics
     * @see IOException
     */
    @Override
    public void render(Graphics g) throws IOException {
        if (this.owner instanceof Board || this.owner instanceof AI) {
            g.drawImage(Thumbnails.of(TILE_VECTOR_VALUE_INDEX.BACK.tileImage)
                    .rotate(rotationDegrees)
                    .size(maxWidth, maxHeight)
                    .asBufferedImage(), startingX, startingY, null);
        } else if (this.owner instanceof Human || this.owner instanceof Discard) {
            g.drawImage(Thumbnails.of(
                            tileProperty.toImage())
                    .rotate(rotationDegrees)
                    .size(maxWidth, maxHeight)
                    .asBufferedImage(), startingX, startingY, null);
        } else {
            JOptionPane.showMessageDialog(null, "Invalid Tile render configuration: " + this,
                    "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }

    // Getter and Setters
    public Container getOwner() {
        return owner;
    }

    public int getRotationDegrees() {
        return rotationDegrees;
    }

    @Override
    public int getStartingX() {
        return startingX;
    }

    @Override
    public int getStartingY() {
        return startingY;
    }

    @Override
    public int getMovingX() {
        // moving x and starting x are the same value
        return movingX;
    }

    @Override
    public int getMovingY() {
        // moving y and starting y are the same value
        return movingY;
    }

    public void setOwner(Container owner) {
        this.owner = owner;
    }

    public void setRotationDegrees(int rotationDegrees) {
        this.rotationDegrees = rotationDegrees;
    }

    @Override
    public void setStartingX(int startingX) {
        this.startingX = startingX;
        this.movingX = startingX;
    }

    @Override
    public void setStartingY(int startingY) {
        this.startingY = startingY;
        this.movingY = startingY;
    }

    @Override
    public void setMovingX(int movingX) {
        // moving x will shift with starting x
        this.movingX = movingX;
        this.startingX = movingX;
    }

    @Override
    public void setMovingY(int movingY) {
        // moving y will shift with starting y
        this.movingY = movingY;
        this.startingY = movingY;
    }

    /**
     * Allows user to set both the x and y position of the tile at one time
     * @param x new x coordinate of this tile
     * @param y new y coordinate of this tile
     */
    public void setTilePosition(int x, int y) {
        this.startingX = x;
        this.startingY = y;
        this.movingX = x;
        this.movingY = y;
    }

    // Comparators
    /**
     * Compares this tile with another tile to establish order, which is handled
     * by the enum
     *
     * @param o the object to be compared.
     * @return {@code 0} if the tiles are the same, {@code 1} if this tile is greater
     * than the tile of comparison, and {@code -1} if this tile is less than the otehr
     * tile of comparison
     */
    public int compareTo(Tile o) {
        return this.tileProperty.compareTo(o.tileProperty);
    }

    /**
     * Checks if 2 tiles are the same
     *
     * @param obj The object to check equality to
     * @return {@code true} if both tiles are the same, else {@code false}
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Tile) {
            return this.tileProperty.equals(((Tile) obj).tileProperty);
        } else {
            return false;
        }
    }

    /**
     * Describes the behaviour when the tiles are hashed
     *
     * @return hashcode, based on the {@link Tile} tileProperty
     */
    @Override
    public int hashCode() {
        return tileProperty.hashCode();
    }

    // Assertion functions
    public boolean isBamboo() {
        return (tileProperty.tileValue >= 0 && tileProperty.tileValue < 9);
    }

    public boolean isCircle() {
        return (tileProperty.tileValue >= 9 && tileProperty.tileValue < 18);
    }

    public boolean isNumber() {
        return (tileProperty.tileValue >= 18 && tileProperty.tileValue < 27);
    }

    public boolean isHonour() {
        return (tileProperty.tileValue >= 27 && tileProperty.tileValue < 34);
    }

    public boolean isBonus() {
        return (tileProperty.tileValue > 33 && tileProperty.tileValue < 46);
    }

    /**
     * Checks for this tile's equality to the enum {@link TILE_VECTOR_VALUE_INDEX}
     *
     * @param refTile The reference tile value to compare to, found at {@link TILE_VECTOR_VALUE_INDEX}
     * @return {@code true} if this tile has the same tileProperty value compared to the reference tile,
     * {@code false} otherwise
     */
    public boolean equalToStandardTileValue(TILE_VECTOR_VALUE_INDEX refTile) {
        return tileProperty.tileValue == refTile.tileValue;
    }

    /**
     * Gets this tile's actual value based on this tile's tileProperty
     *
     * @return a string containing the actual value of the tile
     */
    public String getTileValue() {
        if (isBamboo() || isCircle() || isNumber()) {
            return String.valueOf(tileProperty.tileValue % 9 + 1);
        } else if (isHonour()) {
            switch (tileProperty.tileValue) {
                case 27:
                    return "green";
                case 28:
                    return "red";
                case 29:
                    return "white";
                case 30:
                    return "east";
                case 31:
                    return "north";
                case 32:
                    return "south";
                case 33:
                    return "west";
            }
        } else if (isBonus()) {
            switch (tileProperty.tileValue) {
                case 34:
                    return "cat";
                case 35:
                    return "centipede";
                case 36:
                    return "rat";
                case 37:
                    return "rooster";
                case 38:
                    return "black_flower_1";
                case 39:
                    return "black_flower_2";
                case 40:
                    return "black_flower_3";
                case 41:
                    return "black_flower_4";
                case 42:
                    return "red_flower_1";
                case 43:
                    return "red_flower_2";
                case 44:
                    return "red_flower_3";
                case 45:
                    return "red_flower_4";

            }
        } else {
            return "invalid";
        }

        return "invalid";
    }

    // Utility
    @Override
    public String toString() {
        return tileProperty.toString();
    }
}
