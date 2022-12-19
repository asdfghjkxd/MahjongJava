package screens;

import board.Board;
import constants.CONSTANTS;
import game.GUIGame;

import java.awt.*;
import java.io.IOException;

public class End extends Screen {
    private final Board board;

    public End(GUIGame game, Board board) {
        super(game);
        this.board = board;
    }

    @Override
    public void render(Graphics g) throws IOException {
        g.setColor(CONSTANTS.BACKGROUND_COLOUR);
        g.fillRect(0, 0, GUIGame.WIDTH, GUIGame.HEIGHT);
        g.setColor(CONSTANTS.FONT_COLOUR);
        g.drawString("Game Over! " + board.getCurrentPlayer() + " wins!", 200, 200);
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
}
