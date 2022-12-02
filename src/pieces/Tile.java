package pieces;

import board.Board;
import board.Discard;
import entities.AI;
import entities.Human;
import net.coobird.thumbnailator.Thumbnails;
import utils.Container;
import utils.Renderable;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

public final class Tile implements Comparable<Tile>, Renderable {
    private static final HashMap<String, HashMap<String, HashMap<String, BufferedImage>>> table = new HashMap<>();
    public static final int maxHeight = 50;
    public static final int maxWidth = 40;
    public static BufferedImage backTile;
    private String tileClass;
    private String tileSubclass;
    private String tileValue;
    private Container owner;
    private int rotationDegrees = 0;
    private int x_position;
    private int y_position;

    /*
     * Static block used to instantiate the static attributes
     *
     * > Creates the various HashMaps required to store key-value/image bindings
     */
    static {
        // Create the Suit Tiles
        HashMap<String, BufferedImage> circleImageMap = new HashMap<>();
        HashMap<String, BufferedImage> bambooImageMap = new HashMap<>();
        HashMap<String, BufferedImage> numberImageMap = new HashMap<>();

        for (int i = 1; i < 10; i++) {
            try {
                circleImageMap.put(String.valueOf(i), Thumbnails.of(ImageIO.read(new FileInputStream("assets/circle_" + i + ".png")))
                        .size(maxWidth, maxHeight)
                        .asBufferedImage());
                bambooImageMap.put(String.valueOf(i), Thumbnails.of(ImageIO.read(new FileInputStream("assets/bamboo_" + i + ".png")))
                        .size(maxWidth, maxHeight)
                        .asBufferedImage());
                numberImageMap.put(String.valueOf(i), Thumbnails.of(ImageIO.read(new FileInputStream("assets/numbers_" + i + ".png")))
                        .size(maxWidth, maxHeight)
                        .asBufferedImage());

            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error in loading suit assets, terminating...");
                System.exit(1);
            }
        }

        HashMap<String, HashMap<String, BufferedImage>> suitMap = new HashMap<>();
        suitMap.put("circle", circleImageMap);
        suitMap.put("bamboo", bambooImageMap);
        suitMap.put("number", numberImageMap);
        table.put("suit", suitMap);

        // Create the Honour Tiles
        HashMap<String, BufferedImage> windImageMap = new HashMap<>();
        HashMap<String, BufferedImage> dragonImageMap = new HashMap<>();

        try {
            dragonImageMap.put("green", Thumbnails.of(ImageIO.read(new FileInputStream("assets/dragon_green.png")))
                    .size(maxWidth, maxHeight)
                    .asBufferedImage());
            dragonImageMap.put("red", Thumbnails.of(ImageIO.read(new FileInputStream("assets/dragon_red.png")))
                    .size(maxWidth, maxHeight)
                    .asBufferedImage());
            dragonImageMap.put("white", Thumbnails.of(ImageIO.read(new FileInputStream("assets/dragon_white.png")))
                    .size(maxWidth, maxHeight)
                    .asBufferedImage());
            windImageMap.put("north", Thumbnails.of(ImageIO.read(new FileInputStream("assets/north.png")))
                    .size(maxWidth, maxHeight)
                    .asBufferedImage());
            windImageMap.put("south", Thumbnails.of(ImageIO.read(new FileInputStream("assets/south.png")))
                    .size(maxWidth, maxHeight)
                    .asBufferedImage());
            windImageMap.put("east", Thumbnails.of(ImageIO.read(new FileInputStream("assets/east.png")))
                    .size(maxWidth, maxHeight)
                    .asBufferedImage());
            windImageMap.put("west", Thumbnails.of(ImageIO.read(new FileInputStream("assets/west.png")))
                    .size(maxWidth, maxHeight)
                    .asBufferedImage());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error in loading honour assets, terminating...");
            System.exit(1);
        }

        HashMap<String, HashMap<String, BufferedImage>> honourMap = new HashMap<>();
        honourMap.put("wind", windImageMap);
        honourMap.put("dragon", dragonImageMap);
        table.put("honour", honourMap);

        // Create the Bonus tiles
        HashMap<String, BufferedImage> animalImageMap = new HashMap<>();
        HashMap<String, BufferedImage> flowerImageMap = new HashMap<>();

        try {
            animalImageMap.put("cat", Thumbnails.of(ImageIO.read(new FileInputStream("assets/cat.png")))
                    .size(maxWidth, maxHeight)
                    .asBufferedImage());
            animalImageMap.put("rat", Thumbnails.of(ImageIO.read(new FileInputStream("assets/rat.png")))
                    .size(maxWidth, maxHeight)
                    .asBufferedImage());
            animalImageMap.put("rooster", Thumbnails.of(ImageIO.read(new FileInputStream("assets/rooster.png")))
                    .size(maxWidth, maxHeight)
                    .asBufferedImage());
            animalImageMap.put("centipede", Thumbnails.of(ImageIO.read(new FileInputStream("assets/centipede.png")))
                    .size(maxWidth, maxHeight)
                    .asBufferedImage());
            flowerImageMap.put("bf1", Thumbnails.of(ImageIO.read(new FileInputStream("assets/black_flower_1.png")))
                    .size(maxWidth, maxHeight)
                    .asBufferedImage());
            flowerImageMap.put("bf2", Thumbnails.of(ImageIO.read(new FileInputStream("assets/black_flower_2.png")))
                    .size(maxWidth, maxHeight)
                    .asBufferedImage());
            flowerImageMap.put("bf3", Thumbnails.of(ImageIO.read(new FileInputStream("assets/black_flower_3.png")))
                    .size(maxWidth, maxHeight)
                    .asBufferedImage());
            flowerImageMap.put("bf4", Thumbnails.of(ImageIO.read(new FileInputStream("assets/black_flower_4.png")))
                    .size(maxWidth, maxHeight)
                    .asBufferedImage());
            flowerImageMap.put("rf1", Thumbnails.of(ImageIO.read(new FileInputStream("assets/red_flower_1.png")))
                    .size(maxWidth, maxHeight)
                    .asBufferedImage());
            flowerImageMap.put("rf2", Thumbnails.of(ImageIO.read(new FileInputStream("assets/red_flower_2.png")))
                    .size(maxWidth, maxHeight)
                    .asBufferedImage());
            flowerImageMap.put("rf3", Thumbnails.of(ImageIO.read(new FileInputStream("assets/red_flower_3.png")))
                    .size(maxWidth, maxHeight)
                    .asBufferedImage());
            flowerImageMap.put("rf4", Thumbnails.of(ImageIO.read(new FileInputStream("assets/red_flower_4.png")))
                    .size(maxWidth, maxHeight)
                    .asBufferedImage());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error in loading bonus assets, terminating...");
            System.exit(1);
        }

        HashMap<String, HashMap<String, BufferedImage>> bonusMap = new HashMap<>();
        bonusMap.put("animal", animalImageMap);
        bonusMap.put("flower", flowerImageMap);
        table.put("bonus", bonusMap);


        // Create the Back Tile
        try {
            backTile = Thumbnails.of(ImageIO.read(new FileInputStream("assets/back.png")))
                    .size(maxWidth, maxHeight)
                    .asBufferedImage();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error in loading assets, terminating...");
            System.exit(1);
        }
    }

    /**
     * Main constructor for the tile
     * <p>
     * Tiles are initially bounded to the Board and hence requires board as an input
     * The board should handle all tile instantiation and users should not be able to construct tiles
     * </p>
     * @param x: x coordinates of the tile (upper left corner)
     * @param y: y coordinates of the tile (upper left corner)
     * @param tileClass: the overarching Suit class of the Tile
     * @param tileSubclass: the specific subclass of a particular Suit class
     * @param tileValue: The value of the tile, given by a string
     * @param board: The board the tile is attached to
     * @param rotationDegrees: The degree of rotation (clockwise) the tile is rotated by
     */
    public Tile(int x, int y, String tileClass, String tileSubclass, String tileValue, Board board, int rotationDegrees) {
        if (this.checkValidConfiguration(tileClass, tileSubclass, tileValue)) {
            this.x_position = x;
            this.y_position = y;
            this.tileClass = tileClass;
            this.tileSubclass = tileSubclass;
            this.tileValue = tileValue;
            this.owner = board;
            this.rotationDegrees = Math.abs(rotationDegrees % 360);
        } else {
            JOptionPane.showMessageDialog(null, "Illegal instantiation of Tile");
            System.exit(1);
        }
    }

    // Renderable functions
    @Override
    public void render(Graphics g) throws IOException {
        if (this.owner instanceof Board || this.owner instanceof AI) {
            g.drawImage(Thumbnails.of(backTile).rotate(rotationDegrees)
                            .asBufferedImage(), x_position, y_position, null);
        } else if (this.owner instanceof Human || this.owner instanceof Discard) {
            g.drawImage(Thumbnails.of(
                    this.getTileImage())
                    .rotate(rotationDegrees).asBufferedImage(), x_position, y_position, null);
        } else {
            JOptionPane.showMessageDialog(null, "Invalid Tile render configuration: " + this);
            System.exit(1);
        }
    }

    // Getter and Setters
    public String getTileClass() {
        return tileClass;
    }

    public String getTileSubclass() {
        return tileSubclass;
    }

    public String getTileValue() {
        return tileValue;
    }

    public BufferedImage getTileImage() {
        return table.get(tileClass).get(tileSubclass).get(tileValue);
    }

    public Container getOwner() {
        return owner;
    }

    public int getRotationDegrees() {
        return rotationDegrees;
    }

    public int getX_position() {
        return x_position;
    }

    public int getY_position() {
        return y_position;
    }

    public void setOwner(Container owner) {
        this.owner = owner;
    }

    public void setRotationDegrees(int rotationDegrees) {
        this.rotationDegrees = rotationDegrees;
    }

    public void setX_position(int x_position) {
        this.x_position = x_position;
    }

    public void setY_position(int y_position) {
        this.y_position = y_position;
    }


    // Comparability
    public int compareTo(Tile o) {
        if (this.getTileClass().compareTo(o.getTileClass()) != 0) {
            return (-this.getTileClass().compareTo(o.getTileClass()));
        } else if (this.getTileSubclass().compareTo(o.getTileSubclass()) != 0) {
            return this.getTileSubclass().compareTo(o.getTileSubclass());
        } else {
            if (this.getTileValue() != null && o.getTileValue() != null) {
                return this.getTileValue().compareTo(o.getTileValue());
            } else {
                JOptionPane.showMessageDialog(null, "Invalid Comparator");
            }
        }

        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Tile) {
            return ((Tile) obj).getTileClass().equals(this.getTileClass()) &&
                    ((Tile) obj).getTileSubclass().equals(this.getTileSubclass()) &&
                    ((Tile) obj).getTileValue().equals(this.getTileValue());
        } else {
            return false;
        }
    }


    // Utility
    @Override
    public String toString() {
        return String.valueOf(getTileClass().toUpperCase().charAt(0)) +
                getTileClass().toUpperCase().charAt(0) + "_" +
                getTileValue().toUpperCase();
    }

    /**
     * Checks if a given configuration of Tile Class, Subclass and Value is valid
     *
     * @param tileClass: the overarching Suit class of the Tile
     * @param tileSubclass: the specific subclass of a particular Suit class
     * @param tileValue: The value of the tile, given by a string
     * @return true if valid, false otherwise
     */
    private boolean checkValidConfiguration(String tileClass, String tileSubclass, String tileValue) {
        try {
            return table.get(tileClass).get(tileSubclass).containsKey(tileValue);
        } catch (Exception e) {
            return false;
        }
    }
}
