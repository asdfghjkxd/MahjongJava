package algorithms;

import pieces.Tile;

import java.util.*;

final class BooleanCompressor {
    public static boolean reduceBooleanListAND(List<Boolean> inputList) {
        return inputList.stream().reduce(true, (x, y) -> x && y);
    }

    public static boolean reduceBooleanListOR(List<Boolean> inputList) {
        return inputList.stream().reduce(false, (x, y) -> x || y);
    }

    public static <T> boolean reduceBooleanMapAND(Map<T, Boolean> inputMap) {
        return inputMap.values().stream().reduce(true, (x, y) -> x && y);
    }

    public static <T> boolean reduceBooleanMapOR(Map<T, Boolean> inputMap) {
        return inputMap.values().stream().reduce(false, (x, y) -> x || y);
    }

    public static boolean reduceBooleanListMapAND(HashMap<Tile, LinkedList<Boolean>> inputListMap, int id) {
        try {
            return inputListMap.values().stream().map(x -> x.get(id)).reduce(true, (x, y) -> x && y);
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    public static boolean reduceBooleanListMapOR(HashMap<Tile, LinkedList<Boolean>> inputListMap, int id) {
        try {
            return inputListMap.values().stream().map(x -> x.get(id)).reduce(false, (x, y) -> x || y);
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }
}
