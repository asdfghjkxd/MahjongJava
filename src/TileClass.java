import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

import net.coobird.thumbnailator.Thumbnails;

public enum TileClass {
    CLASS();

    private static final Map<String, List<String>> classToSubClassMap;
    private static final HashMap<String, String> subClassToClass;
    private static final HashMap<String, List<?>> subClassToValue;
    private static final HashMap<String, HashMap<?, BufferedImage>> subClassToImage;
    public static final int maxHeight = 50;
    public static final int maxWidth = 40;
    public static final BufferedImage backOfTile;

    static {
        classToSubClassMap = new HashMap<String, List<String>>();
        classToSubClassMap.put("suit", new LinkedList<String>(List.of("circle", "bamboo", "number")));
        classToSubClassMap.put("honour", new LinkedList<String>(List.of("wind", "dragon")));
        classToSubClassMap.put("bonus", new LinkedList<String>(List.of("animal", "flower")));

        subClassToClass = new HashMap<String, String>();
        subClassToClass.put("circle", "suit");
        subClassToClass.put("bamboo", "suit");
        subClassToClass.put("number", "suit");
        subClassToClass.put("wind", "honour");
        subClassToClass.put("dragon", "honour");
        subClassToClass.put("animal", "bonus");
        subClassToClass.put("flower", "bonus");

        subClassToValue = new HashMap<String, List<?>>();
        subClassToValue.put("circle", new LinkedList<Integer>(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9)));
        subClassToValue.put("bamboo", new LinkedList<Integer>(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9)));
        subClassToValue.put("number", new LinkedList<Integer>(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9)));
        subClassToValue.put("wind", new LinkedList<String>(List.of("north", "south", "east", "west")));
        subClassToValue.put("dragon", new LinkedList<String>(List.of("white", "green", "red")));
        subClassToValue.put("animal", new LinkedList<String>(List.of("cat", "rat", "rooster", "centipede")));
        subClassToValue.put("flower", new LinkedList<String>(List.of("black_flower_1", "black_flower_2",
                "black_flower_3", "black_flower_4", "red_flower_1", "red_flower_2", "red_flower_3",
                "red_flower_4")));

        subClassToImage = new HashMap<String, HashMap<?, BufferedImage>>();
        final HashMap<Integer, BufferedImage> BAMBOO_IMAGES = new HashMap<>();
        final HashMap<Integer, BufferedImage> CIRCLE_IMAGES = new HashMap<>();
        final HashMap<Integer, BufferedImage> NUMBERS_IMAGES = new HashMap<>();
        final HashMap<String, BufferedImage> DRAGON_IMAGES = new HashMap<>();
        final HashMap<String, BufferedImage> WIND_IMAGES = new HashMap<>();
        final HashMap<String, BufferedImage> FLOWER_IMAGES = new HashMap<>();
        final HashMap<String, BufferedImage> ANIMAL_IMAGES = new HashMap<>();

        for (int i = 1; i < 10; i++) {
            try {
                BufferedImage im1 = ImageIO.read(new FileInputStream("assets/bamboo_" + i + ".png"));
                BufferedImage im2 = ImageIO.read(new FileInputStream("assets/circle_" + i + ".png"));
                BufferedImage im3 = ImageIO.read(new FileInputStream("assets/numbers_" + i + ".png"));
                BAMBOO_IMAGES.put(i, Thumbnails.of(im1).size(maxWidth, maxHeight).asBufferedImage());
                CIRCLE_IMAGES.put(i, Thumbnails.of(im2).size(maxWidth, maxHeight).asBufferedImage());
                NUMBERS_IMAGES.put(i, Thumbnails.of(im3).size(maxWidth, maxHeight).asBufferedImage());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        try {
            BufferedImage green = ImageIO.read(new FileInputStream("assets/dragon_green.png"));
            BufferedImage red = ImageIO.read(new FileInputStream("assets/dragon_red.png"));
            BufferedImage white = ImageIO.read(new FileInputStream("assets/dragon_white.png"));
            DRAGON_IMAGES.put("green", Thumbnails.of(green).size(maxWidth, maxHeight).asBufferedImage());
            DRAGON_IMAGES.put("red", Thumbnails.of(red).size(maxWidth, maxHeight).asBufferedImage());
            DRAGON_IMAGES.put("white", Thumbnails.of(white).size(maxWidth, maxHeight).asBufferedImage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        try {
            BufferedImage north = ImageIO.read(new FileInputStream("assets/north.png"));
            BufferedImage south = ImageIO.read(new FileInputStream("assets/south.png"));
            BufferedImage east = ImageIO.read(new FileInputStream("assets/east.png"));
            BufferedImage west = ImageIO.read(new FileInputStream("assets/west.png"));
            WIND_IMAGES.put("north", Thumbnails.of(north).size(maxWidth, maxHeight).asBufferedImage());
            WIND_IMAGES.put("south", Thumbnails.of(south).size(maxWidth, maxHeight).asBufferedImage());
            WIND_IMAGES.put("east", Thumbnails.of(east).size(maxWidth, maxHeight).asBufferedImage());
            WIND_IMAGES.put("west", Thumbnails.of(west).size(maxWidth, maxHeight).asBufferedImage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            BufferedImage bf1 = ImageIO.read(new FileInputStream("assets/black_flower_1.png"));
            BufferedImage bf2 = ImageIO.read(new FileInputStream("assets/black_flower_2.png"));
            BufferedImage bf3 = ImageIO.read(new FileInputStream("assets/black_flower_3.png"));
            BufferedImage bf4 = ImageIO.read(new FileInputStream("assets/black_flower_4.png"));
            BufferedImage rf1 = ImageIO.read(new FileInputStream("assets/red_flower_1.png"));
            BufferedImage rf2 = ImageIO.read(new FileInputStream("assets/red_flower_2.png"));
            BufferedImage rf3 = ImageIO.read(new FileInputStream("assets/red_flower_3.png"));
            BufferedImage rf4 = ImageIO.read(new FileInputStream("assets/red_flower_4.png"));


            FLOWER_IMAGES.put("BF1", Thumbnails.of(bf1).size(maxWidth, maxHeight).asBufferedImage());
            FLOWER_IMAGES.put("BF2", Thumbnails.of(bf2).size(maxWidth, maxHeight).asBufferedImage());
            FLOWER_IMAGES.put("BF3", Thumbnails.of(bf3).size(maxWidth, maxHeight).asBufferedImage());
            FLOWER_IMAGES.put("BF4", Thumbnails.of(bf4).size(maxWidth, maxHeight).asBufferedImage());
            FLOWER_IMAGES.put("RF1", Thumbnails.of(rf1).size(maxWidth, maxHeight).asBufferedImage());
            FLOWER_IMAGES.put("RF2", Thumbnails.of(rf2).size(maxWidth, maxHeight).asBufferedImage());
            FLOWER_IMAGES.put("RF3", Thumbnails.of(rf3).size(maxWidth, maxHeight).asBufferedImage());
            FLOWER_IMAGES.put("RF4", Thumbnails.of(rf4).size(maxWidth, maxHeight).asBufferedImage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            BufferedImage cat = ImageIO.read(new FileInputStream("assets/cat.png"));
            BufferedImage rat = ImageIO.read(new FileInputStream("assets/rat.png"));
            BufferedImage roo = ImageIO.read(new FileInputStream("assets/rooster.png"));
            BufferedImage cen = ImageIO.read(new FileInputStream("assets/centipede.png"));

            ANIMAL_IMAGES.put("cat", Thumbnails.of(cat).size(maxWidth, maxHeight).asBufferedImage());
            ANIMAL_IMAGES.put("rat", Thumbnails.of(rat).size(maxWidth, maxHeight).asBufferedImage());
            ANIMAL_IMAGES.put("rooster", Thumbnails.of(roo).size(maxWidth, maxHeight).asBufferedImage());
            ANIMAL_IMAGES.put("centipede", Thumbnails.of(cen).size(maxWidth, maxHeight).asBufferedImage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        subClassToImage.put("bamboo", BAMBOO_IMAGES);
        subClassToImage.put("circle", CIRCLE_IMAGES);
        subClassToImage.put("number", NUMBERS_IMAGES);
        subClassToImage.put("dragon", DRAGON_IMAGES);
        subClassToImage.put("wind", WIND_IMAGES);
        subClassToImage.put("flower", FLOWER_IMAGES);
        subClassToImage.put("animal", ANIMAL_IMAGES);

        try {
            BufferedImage temp = ImageIO.read(new FileInputStream("assets/back.png"));
            backOfTile = Thumbnails.of(temp).size(maxWidth, maxHeight).asBufferedImage();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public static List<String> querySubclassFromClass(String q) {
        return classToSubClassMap.get(q.toLowerCase()) == null
                ? null
                : Collections.unmodifiableList(classToSubClassMap.get(q.toLowerCase()));
    }

    public static String queryClassFromSubClass(String q) {
        return subClassToClass.get(q.toLowerCase()) == null
                ? null
                : subClassToClass.get(q.toLowerCase());
    }

    public static List<?> queryValue(String q) {
        return subClassToValue.get(q) == null
                ? null
                : Collections.unmodifiableList(subClassToValue.get(q.toLowerCase()));
    }

    public static BufferedImage queryImage(String subClass, Object value) throws IllegalArgumentException{
        try {
            return subClassToImage.get(subClass).get(value);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid Subclass or Value");
        }
    }
}
