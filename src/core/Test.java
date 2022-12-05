package core;

import board.Board;
import entities.Human;
import pieces.Tile;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

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
        Tile t1 = new Tile(0, 0, "honour", "wind", "north", null, 0);
        testTiles(t1);

        Board b = new Board();
        System.out.println(b);
        b.testBoard();
    }

    @Override
    public void run() {

    }

    private static void testTiles(Tile tile) {
        assert tile.getTileClass().equals("honour");
        assert tile.getTileSubclass().equals("wind");
        assert tile.getTileValue().equals("north");
        assert tile.getRotationDegrees() == 0;
        System.out.println("success!");
    }
}
