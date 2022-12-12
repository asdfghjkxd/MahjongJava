package algorithms;

import constants.TILE_VECTOR_VALUE_INDEX;
import org.apache.commons.lang3.tuple.MutablePair;
import pieces.Tile;

import javax.swing.*;
import java.util.*;
import java.util.stream.Collectors;


public final class TileAlgorithm {

    public TileAlgorithm() {
        // does nothing
    }

    public static ArrayList<Integer> map2Vec(TreeMap<Tile, Integer> input) {
        if (input.size() != 47) {
            return new ArrayList<>(input.values());
        } else {
            return new ArrayList<>();
        }
    }

    public static TreeMap<Tile, Integer> vec2Map(ArrayList<Integer> input) {
        if (input.size() != 47) {
            JOptionPane.showMessageDialog(null, "Invalid dimension: " + input.size(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        } else {
            TreeMap<Tile, Integer> newTileMap = new TreeMap<>();
            for (int i = 0; i < 46; i++) {
                newTileMap.put(
                        new Tile(0, 0, TILE_VECTOR_VALUE_INDEX.fromValue(i), null, 0),
                        input.get(i));
            }

            return newTileMap;
        }
    }

    private static ArrayList<Integer> applyHandAction(ArrayList<Integer> hand, ArrayList<Integer> action) {
        if (hand.size() != action.size()) {
            JOptionPane.showMessageDialog(null, "Invalid dimensions of actions and hand",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < hand.size(); i++) {
            result.add(i, hand.get(i) - action.get(i));
        }

        return result;
    }

    public static List<ArrayList<Integer>> allPossibleActions(ArrayList<Integer> hand,
                                                             List<ArrayList<Integer>> comparison) {
        return comparison.stream().filter(
                x -> {
                    ArrayList<Integer> result = TileAlgorithm.applyHandAction(hand, x);
                    if (result == null) {
                        return false;
                    }

                    return result.stream().filter(y -> y < 0).toList().size() == 0;
                }
        ).collect(Collectors.toList());
    }

    private static boolean emptyHand(ArrayList<Integer> input) {
        return input.stream().reduce(0, Integer::sum) == 0;
    }

    public static MutablePair<Boolean, LinkedList<ArrayList<Integer>>> validHand(
            ArrayList<Integer> hand, List<ArrayList<Integer>> allActions,
            List<ArrayList<Integer>> headActions, LinkedList<ArrayList<Integer>> appliedActions) {
        if (TileAlgorithm.emptyHand(hand)) {
            return new MutablePair<>(true, appliedActions);
        }

        List<ArrayList<Integer>> availableAction = allPossibleActions(hand, allActions);
        List<ArrayList<Integer>> availableHeadAction = headActions != null
                ? allPossibleActions(hand, headActions)
                : new LinkedList<>();

        for (ArrayList<Integer> action : availableAction) {
            ArrayList<Integer> appliedHandAction = applyHandAction(hand, action);

            // create the new applied action here
            LinkedList<ArrayList<Integer>> newAppliedAction = new LinkedList<>(appliedActions);
            newAppliedAction.add(action);

            MutablePair<Boolean, LinkedList<ArrayList<Integer>>> recursiveCallValue = validHand(
                    appliedHandAction, allActions, headActions, newAppliedAction
            );

            if (recursiveCallValue.getLeft()) {
                return recursiveCallValue;
            }
        }

        for (ArrayList<Integer> currentHeadAction : availableHeadAction) {
            ArrayList<Integer> applyCurrentHeadAction = applyHandAction(hand, currentHeadAction);

            LinkedList<ArrayList<Integer>> newHeadAction = new LinkedList<>(appliedActions);
            newHeadAction.add(currentHeadAction);

            MutablePair<Boolean, LinkedList<ArrayList<Integer>>> recursiveCallValue = validHand(
                    applyCurrentHeadAction, allActions, null, newHeadAction
            );

            if (recursiveCallValue.getLeft()) {
                return recursiveCallValue;
            }
        }

        return new MutablePair<>(false, appliedActions);
    }
}
