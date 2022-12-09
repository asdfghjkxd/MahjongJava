package algorithms;

import jdk.jfr.Name;
import pieces.Tile;

import java.util.*;

public final class TilePattern {
    public static boolean isEye(Tile t0, Tile t1) {
        return t0.equals(t1);
    }

    public static boolean isPong(Tile t0, Tile t1, Tile t2) {
        return t0.equals(t1) && t1.equals(t2);
    }

    public static boolean isKang(Tile t0, Tile t1, Tile t2, Tile t3) {
        return t0.equals(t1)
                && t1.equals(t2)
                && t2.equals(t3);
    }

    public static boolean isChow(Tile t0, Tile t1, Tile t2) {
        LinkedList<Tile> tiles = new LinkedList<>(Arrays.asList(t0, t1, t2));
        Collections.sort(tiles);

        if (tiles.stream().map(Tile::getTileClass).distinct().count() <= 1
        && tiles.stream().map(Tile::getTileSubclass).distinct().count() <= 1) {
            if (tiles.stream().map(Tile::getTileValue).distinct().count() <= 1) {
                return false;
            } else {
                boolean flag = true;

                for (int i = 0; i < tiles.size() - 1; i++) {
                    try {
                        int first = Integer.parseInt(tiles.get(i).getTileValue());
                        int second = Integer.parseInt(tiles.get(i + 1).getTileValue());

                        if (second - first > 1) {
                            flag = false;
                        }

                    } catch (NumberFormatException ex) {
                        return false;
                    }
                }

                return flag;
            }
        } else {
            return false;
        }
    }

    /**
     * Variadic function that checks for all patterns within the hand
     * and returns the possible tile combinations for a particular type
     * of pattern
     *
     * @return A list of lists containing list of Tiles matching patterns
     * - First List: Eyes
     * - Second List: Pong
     * - Third List: Kang
     * - Fourth List: Chow
     */
    public static LinkedList<LinkedList<LinkedList<Tile>>> checkForPatterns(Tile ...tiles) {
        LinkedList<LinkedList<Tile>> eyeList = new LinkedList<>();
        LinkedList<LinkedList<Tile>> pongList = new LinkedList<>();
        LinkedList<LinkedList<Tile>> kangList = new LinkedList<>();
        LinkedList<LinkedList<Tile>> chowList = new LinkedList<>();

        for (int i = 0; i < tiles.length - 1; i++) {
            if (isEye(tiles[i], tiles[i + 1])) {
                eyeList.add(new LinkedList<>(Arrays.asList(tiles[i], tiles[i + 1])));
            }
        }

        for (int i = 0; i < tiles.length - 2; i++) {
            if (isChow(tiles[i], tiles[i + 1], tiles[i + 2])) {
                chowList.add(new LinkedList<>(Arrays.asList(tiles[i], tiles[i + 1], tiles[i + 2])));
            }

            if (isPong(tiles[i], tiles[i + 1], tiles[i + 2])) {
                pongList.add(new LinkedList<>(Arrays.asList(tiles[i], tiles[i + 1], tiles[i + 2])));
            }
        }

        for (int i = 0; i < tiles.length - 3; i++) {
            if (isKang(tiles[i], tiles[i + 1], tiles[i + 2], tiles[i + 3])) {
                kangList.add(new LinkedList<>(
                        Arrays.asList(tiles[i], tiles[i + 1], tiles[i + 2], tiles[i + 3])));
            }
        }

        return new LinkedList<>(Arrays.asList(eyeList, pongList, kangList, chowList));
    }


}
