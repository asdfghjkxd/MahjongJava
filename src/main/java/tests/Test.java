package tests;

import algorithms.TileAlgorithm;
import constants.TILE_VECTOR_VALUE_INDEX;
import pieces.Tile;

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

        testNewTileSystem();

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
        TileAlgorithm tp = new TileAlgorithm(new LinkedList<>(
                Arrays.asList(
                        new Tile(0, 0, TILE_VECTOR_VALUE_INDEX.BAMBOO_ONE, null, 0),
                        new Tile(0, 0, TILE_VECTOR_VALUE_INDEX.BAMBOO_TWO, null, 0),
                        new Tile(0, 0, TILE_VECTOR_VALUE_INDEX.BAMBOO_THREE, null, 0)
                )
        ));
        tp.getHandSignature();
    }
}
