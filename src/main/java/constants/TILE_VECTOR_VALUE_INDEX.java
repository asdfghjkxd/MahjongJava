package constants;

import net.coobird.thumbnailator.Thumbnails;
import pieces.Tile;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

public enum TILE_VECTOR_VALUE_INDEX {
    BACK(-1, "assets/back.png", "\uD83C\uDC2B"),
    BAMBOO_ONE(0, "assets/bamboo_1.png", "\uD83C\uDC10"),
    BAMBOO_TWO(1, "assets/bamboo_2.png", "\uD83C\uDC11"),
    BAMBOO_THREE(2, "assets/bamboo_3.png", "\uD83C\uDC12"),
    BAMBOO_FOUR(3, "assets/bamboo_4.png", "\uD83C\uDC13"),
    BAMBOO_FIVE(4, "assets/bamboo_5.png", "\uD83C\uDC14"),
    BAMBOO_SIX(5, "assets/bamboo_6.png", "\uD83C\uDC15"),
    BAMBOO_SEVEN(6, "assets/bamboo_7.png", "\uD83C\uDC16"),
    BAMBOO_EIGHT(7, "assets/bamboo_8.png", "\uD83C\uDC17"),
    BAMBOO_NINE(8, "assets/bamboo_9.png", "\uD83C\uDC18"),
    CIRCLE_ONE(9, "assets/circle_1.png", "\uD83C\uDC19"),
    CIRCLE_TWO(10, "assets/circle_2.png", "\uD83C\uDC1A"),
    CIRCLE_THREE(11, "assets/circle_3.png", "\uD83C\uDC1B"),
    CIRCLE_FOUR(12, "assets/circle_4.png", "\uD83C\uDC1C"),
    CIRCLE_FIVE(13, "assets/circle_5.png", "\uD83C\uDC1D"),
    CIRCLE_SIX(14, "assets/circle_6.png", "\uD83C\uDC1E"),
    CIRCLE_SEVEN(15, "assets/circle_7.png", "\uD83C\uDC1F"),
    CIRCLE_EIGHT(16, "assets/circle_8.png", "\uD83C\uDC20"),
    CIRCLE_NINE(17, "assets/circle_9.png", "\uD83C\uDC21"),
    NUMBER_ONE(18, "assets/numbers_1.png", "\uD83C\uDC07"),
    NUMBER_TWO(19, "assets/numbers_2.png", "\uD83C\uDC08"),
    NUMBER_THREE(20, "assets/numbers_3.png", "\uD83C\uDC09"),
    NUMBER_FOUR(21, "assets/numbers_4.png", "\uD83C\uDC0A"),
    NUMBER_FIVE(22, "assets/numbers_5.png", "\uD83C\uDC0B"),
    NUMBER_SIX(23, "assets/numbers_6.png", "\uD83C\uDC0C"),
    NUMBER_SEVEN(24, "assets/numbers_7.png", "\uD83C\uDC0D"),
    NUMBER_EIGHT(25, "assets/numbers_8.png", "\uD83C\uDC0E"),
    NUMBER_NINE(26, "assets/numbers_9.png", "\uD83C\uDC0F"),
    HONOUR_GREEN(27, "assets/dragon_green.png", "\uD83C\uDC05"),
    HONOUR_RED(28, "assets/dragon_red.png", "\uD83C\uDC04"),
    HONOUR_WHITE(29, "assets/dragon_white.png", "\uD83C\uDC06"),
    HONOUR_EAST(30, "assets/east.png", "\uD83C\uDC00"),
    HONOUR_NORTH(31, "assets/north.png", "\uD83C\uDC03"),
    HONOUR_SOUTH(32, "assets/south.png", "\uD83C\uDC01"),
    HONOUR_WEST(33, "assets/west.png", "\uD83C\uDC02"),
    BONUS_CAT(34, "assets/cat.png", "Cat"),
    BONUS_CENTIPEDE(35, "assets/centipede.png", "Centipede"),
    BONUS_RAT(36, "assets/rat.png", "Rat"),
    BONUS_ROOSTER(37, "assets/rooster.png", "Rooster"),
    BONUS_BLACK_ONE(38, "assets/black_flower_1.png", "\uD83C\uDC22"),
    BONUS_BLACK_TWO(39, "assets/black_flower_2.png", "\uD83C\uDC23"),
    BONUS_BLACK_THREE(40, "assets/black_flower_3.png", "\uD83C\uDC24"),
    BONUS_BLACK_FOUR(41, "assets/black_flower_4.png", "\uD83C\uDC25"),
    BONUS_RED_ONE(42, "assets/red_flower_1.png", "\uD83C\uDC26"),
    BONUS_RED_TWO(43, "assets/red_flower_2.png", "\uD83C\uDC27"),
    BONUS_RED_THREE(44, "assets/red_flower_3.png", "\uD83C\uDC28"),
    BONUS_RED_FOUR(45, "assets/black_flower_4.png", "\uD83C\uDC29");

    public final int tileValue;
    public BufferedImage tileImage;
    private final String unicodeRepresentation;

    TILE_VECTOR_VALUE_INDEX(int x, String filePath, String unicodeRepresentation) {
        tileValue = x;
        this.unicodeRepresentation = unicodeRepresentation;

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

    public final String getUnicodeRepresentation() {
        return unicodeRepresentation;
    }
}
