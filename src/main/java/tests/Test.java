package tests;

import algorithms.TileAlgorithm;
import constants.VALID_TILE_ACTIONS;
import io.BlockingIntegerInput;
import io.BlockingStringInput;
import org.apache.commons.lang3.tuple.MutablePair;
import pieces.Tile;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class Test extends Canvas implements Runnable{
    public static TEST_STATE test_state = TEST_STATE.ACTUAL;
    public enum TEST_STATE {
        TESTING,
        ACTUAL
    }
    public static void main(String[] args) {
        new Test();
    }

    public Test() {
//        Tile t1 = new Tile(0, 0, TILE_VECTOR_VALUE_INDEX.HONOUR_NORTH, null, 0);
//        testTiles(t1);
//
//        Board b = new Board(null);
//        System.out.println(b);
//        b.testBoard();

        // testNewTileSystem();
        block();
    }

    @Override
    public void run() {

    }

    private static void testTiles(Tile tile) {
        assert tile.isHonour();
        assert tile.getTileValue().equals("north");
        assert tile.getRotationDegrees() == 0;
        System.out.println("success!");
    }

    private static void testNewTileSystem() {
        for (ArrayList<Integer> arrayList: VALID_TILE_ACTIONS.ALL_ACTIONS) {
            System.out.println(arrayList);
        }

//        List<ArrayList<Integer>> thing = tp.allPossibleActions(
//                new ArrayList<>(Arrays.asList(3, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)),
//                VALID_ACTIONS.ALL_ACTIONS
//        );

        MutablePair<Boolean, LinkedList<ArrayList<Integer>>> testValue= TileAlgorithm.validHand(
                new ArrayList<>(Arrays.asList(2, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1,
                        1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)),
                VALID_TILE_ACTIONS.ALL_ACTIONS,
                VALID_TILE_ACTIONS.HEAD_ACTIONS,
                new LinkedList<>()
        );

        assert testValue.left;
        System.out.println(testValue);
    }

    private static void block() {
        BlockingStringInput.getInput("test", "test", JOptionPane.ERROR_MESSAGE);
        System.out.println(BlockingStringInput.retrieveInput());

        BlockingIntegerInput.getInput("test2", "test2", JOptionPane.INFORMATION_MESSAGE);

        System.out.println(BlockingIntegerInput.retrieveInput());
    }

    public static void print() {
        System.out.println("hello");
    }
}
