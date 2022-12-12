package screens;

import board.Board;
import constants.CONSTANTS;
import core.Game;
import pieces.Tile;

import java.awt.*;
import java.io.IOException;

public class HUD extends Screen {
    private final Board board;
    public static int tileCounter = 0;



    public HUD(Game game, Board board) {
        super(game);
        this.board = board;
    }

    @Override
    public void render(Graphics g) throws IOException {
        g.setColor(CONSTANTS.BACKGROUND_COLOUR);
        g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
        g.setColor(CONSTANTS.FONT_COLOUR);
        g.setFont(new Font("Arial", Font.BOLD, 16));
        g.setColor(CONSTANTS.TEXTBOX_COLOUR);
        g.fillRect(35, 20, 225,90);
        g.setColor(CONSTANTS.FONT_COLOUR);
        g.drawString("Wind Direction: " + board.getWindDirection(), 40, 40);
        g.drawString("Current Player: " +
                (board.getCurrentPlayer() == null
                        ? "NULL"
                        : board.getCurrentPlayer().getName()), 40, 60);
        g.drawString("Direction: ⥁", 40, 80);
        g.drawString("Current Score: " + board.getCurrentPlayer().getScore() + " 台", 40, 100);

        g.setColor(CONSTANTS.TEXTBOX_COLOUR);
        g.fillRect(Game.WIDTH - 75, 25, 50, 50);
        g.setColor(CONSTANTS.ACCENT_COLOUR);
        g.fillRect(Game.WIDTH - 70, 30, 15, 40);
        g.fillRect(Game.WIDTH - 45, 30, 15, 40);

        if (board.getCurrentPlayer() != null) {
            int[] tilePos = board.getHumanPlayer().getTilePosition(HUD.tileCounter);
            g.setColor(CONSTANTS.CURSOR_COLOUR);
            for (int i = 0; i < CONSTANTS.CURSOR_WIDTH; i++) {
                g.drawRect(tilePos[0] - CONSTANTS.CURSOR_WIDTH + i,
                        tilePos[1] - CONSTANTS.CURSOR_WIDTH + i,
                        Tile.maxWidth + 2 * CONSTANTS.CURSOR_WIDTH - (2 * i),
                        Tile.maxHeight + 2 * CONSTANTS.CURSOR_WIDTH - (2 * i));
            }
        }
    }

    @Override
    public void tick() {
        super.tick();
    }

    @Override
    public int getStartingX() {
        return super.getStartingX();
    }

    @Override
    public int getStartingY() {
        return super.getStartingY();
    }

    @Override
    public int getMovingX() {
        return super.getMovingX();
    }

    @Override
    public int getMovingY() {
        return super.getMovingY();
    }

    @Override
    public void setStartingX(int x) {
        super.setStartingX(x);
    }

    @Override
    public void setStartingY(int y) {
        super.setStartingY(y);
    }

    @Override
    public void setMovingX(int x) {
        super.setMovingX(x);
    }

    @Override
    public void setMovingY(int y) {
        super.setMovingY(y);
    }

    // Function to move cursor around
    public void shiftCursor(int pos) {
        HUD.tileCounter = Math.min(Math.max(pos, 0), board.getHumanPlayer().getNumberOfPrivateTiles() - 1);
    }
}
