package algorithms;

import board.Board;
import constants.TILE_VECTOR_VALUE_INDEX;
import constants.VALID_TILE_ACTIONS;
import org.apache.commons.lang3.tuple.MutablePair;
import pieces.Tile;

import javax.swing.*;
import java.util.*;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;


public final class TileAlgorithm {
    /**
     * Converts a LinkedList of Tiles to its vector representation (represented by an ArrayList)
     *
     * @param input The input {@link LinkedList} of {@link Tile} to convert
     * @return {@link ArrayList} of {@link Integer}; vector representation of {@link Tile}
     */
    public static ArrayList<Integer> tileList2IntList(LinkedList<Tile> input) {
        ArrayList<Integer> toReturn = new ArrayList<>(List.of(
                3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
        ));

        for (Tile t: input) {
            toReturn.add(t.getTileProperty().tileValue, toReturn.get(t.getTileProperty().tileValue) + 1);
        }

        return toReturn;
    }

    /**
     * Converts an ArrayList of Integers to the canonical representation of Tiles (represented by a LinkedList)
     *
     * @param input The input {@link ArrayList} of {@link Integer} to convert
     * @return {@link LinkedList} of {@link Tile}; canonical representation
     */
    public static LinkedList<Tile> intList2TileList(ArrayList<Integer> input) {
        LinkedList<Tile> toReturn = new LinkedList<>();

        for (int i: input) {
            toReturn.add(
                    new Tile(0, 0, TILE_VECTOR_VALUE_INDEX.fromValue(i), null, 0)
            );
        }

        return toReturn;
    }

    /**
     * Converts an ArrayList of Integers to the canonical representation of Tiles (represented by a LinkedList)
     * <p>This function allows the user to specify the board to which the tiles belong to
     *
     * @param input The input {@link ArrayList} of {@link Integer} to convert
     * @param board The {@link Board} instance which the {@link Tile} must belong to
     * @return {@link LinkedList} of {@link Tile}; canonical representation
     */
    public static LinkedList<Tile> intList2TileList(ArrayList<Integer> input, Board board) {
        LinkedList<Tile> toReturn = new LinkedList<>();

        for (int i: input) {
            toReturn.add(
                    new Tile(0, 0, TILE_VECTOR_VALUE_INDEX.fromValue(i), board, 0)
            );
        }

        return toReturn;
    }

    /**
     * "Subtracts" one vector from another vector to represent the use of the tiles
     *
     * @param hand Vector representation of the tiles
     * @param action Vector representation of the action to take
     * @return Vector representation of the tiles after the action is applied
     */
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

    /**
     * Applies a binary operator on one vector to another vector
     *
     * @param hand Vector representation of the tiles
     * @param action Vector representation of the action to take
     * @return Vector representation of the tiles after the action is applied
     */
    private static ArrayList<Integer> applyHandAction(ArrayList<Integer> hand, ArrayList<Integer> action,
                                                      BinaryOperator<Integer> operator) {
        if (hand.size() != action.size()) {
            JOptionPane.showMessageDialog(null, "Invalid dimensions of actions and hand",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < hand.size(); i++) {
            result.add(i, operator.apply(hand.get(i), action.get(i)));
        }

        return result;
    }

    /**
     * Returns a {@link List} of actions (represented as {@link ArrayList} of {@link Integer},
     * that are valid (does not result in the "subtraction" of any tiles that does not exist in the input
     * vector)
     *
     * @param hand Input vector to compare actions against
     * @param comparison The {@link List} of all possible vector actions that can be taken by the hand
     * @return {@link List} of valid vectors that the {@code hand} can take
     */
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

    /**
     * Checks if the hand (vector) is empty: Represented by a contiguous sequence of 0s
     *
     * @param input Hand vector
     * @return {@code true} if the hand is empty in accordance to the definition above, else {@code false}
     */
    private static boolean emptyHand(ArrayList<Integer> input) {
        return input.stream().reduce(0, Integer::sum) == 0;
    }

    /**
     *
     * @param hand
     * @param allActions
     * @param headActions
     * @param appliedActions
     * @return
     */
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
