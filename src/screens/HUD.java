package screens;

import board.Board;
import core.Game;
import pieces.Tile;

import java.awt.*;
import java.io.IOException;

public class HUD extends Screen {
    private final Board board;

    public HUD(Game game, Board board) {
        super(game);
        this.board = board;
    }

    @Override
    public void render(Graphics g) throws IOException {
        g.setColor(new Color(0, 100, 0));
        g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 16));
        g.setColor(Color.WHITE);
        g.fillRect(35, 20, 300,70);
        g.setColor(Color.BLACK);
        g.drawString("Current Player: " +
                (board.getCurrentPlayer() == null
                        ? "NULL"
                        : board.getCurrentPlayer().getName()), 40, 60);
        g.drawString("Direction: ⥁", 40, 80);
        g.drawString("Wind Direction: " + board.getWindDirection(), 40, 40);

        g.setColor(Color.WHITE);
        g.fillRect(Game.WIDTH - 185, 20, 160,30);
        g.setColor(Color.BLACK);
        g.drawString("Current Score: " + board.getCurrentPlayer().getScore() + " 台", game.getWidth() - 180, 40);

        Tile img = new Tile(0, 0, "honour", "wind",
                board.getWindDirection().toString().toLowerCase(), null, 0);

        g.drawImage(img.getTileImage(), game.getWidth() / 2 - 30, game.getHeight() / 2 - 35, null);
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
