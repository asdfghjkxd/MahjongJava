package constants;

import net.coobird.thumbnailator.Thumbnails;
import pieces.Tile;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

public enum TILE_VECTOR_VALUE_INDEX {
    BACK(-1, "assets/back.png"),
    BAMBOO_ONE(0, "assets/bamboo_1.png"),
    BAMBOO_TWO(1, "assets/bamboo_2.png"),
    BAMBOO_THREE(2, "assets/bamboo_3.png"),
    BAMBOO_FOUR(3, "assets/bamboo_4.png"),
    BAMBOO_FIVE(4, "assets/bamboo_5.png"),
    BAMBOO_SIX(5, "assets/bamboo_6.png"),
    BAMBOO_SEVEN(6, "assets/bamboo_7.png"),
    BAMBOO_EIGHT(7, "assets/bamboo_8.png"),
    BAMBOO_NINE(8, "assets/bamboo_9.png"),
    CIRCLE_ONE(9, "assets/circle_1.png"),
    CIRCLE_TWO(10, "assets/circle_2.png"),
    CIRCLE_THREE(11, "assets/circle_3.png"),
    CIRCLE_FOUR(12, "assets/circle_4.png"),
    CIRCLE_FIVE(13, "assets/circle_5.png"),
    CIRCLE_SIX(14, "assets/circle_6.png"),
    CIRCLE_SEVEN(15, "assets/circle_7.png"),
    CIRCLE_EIGHT(16, "assets/circle_8.png"),
    CIRCLE_NINE(17, "assets/circle_9.png"),
    NUMBER_ONE(18, "assets/numbers_1.png"),
    NUMBER_TWO(19, "assets/numbers_2.png"),
    NUMBER_THREE(20, "assets/numbers_3.png"),
    NUMBER_FOUR(21, "assets/numbers_4.png"),
    NUMBER_FIVE(22, "assets/numbers_5.png"),
    NUMBER_SIX(23, "assets/numbers_6.png"),
    NUMBER_SEVEN(24, "assets/numbers_7.png"),
    NUMBER_EIGHT(25, "assets/numbers_8.png"),
    NUMBER_NINE(26, "assets/numbers_9.png"),
    HONOUR_GREEN(27, "assets/dragon_green.png"),
    HONOUR_RED(28, "assets/dragon_red.png"),
    HONOUR_WHITE(29, "assets/dragon_white.png"),
    HONOUR_EAST(30, "assets/east.png"),
    HONOUR_NORTH(31, "assets/north.png"),
    HONOUR_SOUTH(32, "assets/south.png"),
    HONOUR_WEST(33, "assets/west.png"),
    BONUS_CAT(34, "assets/cat.png"),
    BONUS_CENTIPEDE(35, "assets/centipede.png"),
    BONUS_RAT(36, "assets/rat.png"),
    BONUS_ROOSTER(37, "assets/rooster.png"),
    BONUS_BLACK_ONE(38, "assets/black_flower_1.png"),
    BONUS_BLACK_TWO(39, "assets/black_flower_2.png"),
    BONUS_BLACK_THREE(40, "assets/black_flower_3.png"),
    BONUS_BLACK_FOUR(41, "assets/black_flower_4.png"),
    BONUS_RED_ONE(42, "assets/red_flower_1.png"),
    BONUS_RED_TWO(43, "assets/red_flower_2.png"),
    BONUS_RED_THREE(44, "assets/red_flower_3.png"),
    BONUS_RED_FOUR(45, "assets/black_flower_4.png");

    public final int tileValue;
    public BufferedImage tileImage;

    TILE_VECTOR_VALUE_INDEX(int x, String filePath) {
        tileValue = x;
        try {
            this.tileImage = Thumbnails.of(ImageIO.read(new FileInputStream(filePath)))
                    .size(Tile.maxWidth, Tile.maxHeight)
                    .asBufferedImage();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error in loading asset at " + filePath + ", terminating...",
                    "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }

    public static TILE_VECTOR_VALUE_INDEX fromValue(int value) {
        for (TILE_VECTOR_VALUE_INDEX tv: TILE_VECTOR_VALUE_INDEX.values()) {
            if (tv.tileValue == value) {
                return tv;
            }
        }

        return null;
    }

    public final BufferedImage toImage() {
        return tileImage;
    }
}
