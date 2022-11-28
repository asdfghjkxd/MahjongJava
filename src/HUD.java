import java.awt.*;

public class HUD{
    private final Game game;
    private int score;
    public enum WindDirection {
        NORTH(),
        SOUTH(),
        EAST(),
        WEST();


        @Override
        public String toString() {
            return super.toString();
        }
    }
    private WindDirection windDirection = WindDirection.NORTH;

    public HUD(Game g) {
        super();
        this.game = g;
    }

    public final void tick() {
    }

    public final void render(Graphics g) {
        g.setColor(new Color(0, 100, 0));
        g.fillRect(0, 0, game.getWidth(), game.getHeight());
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 16));
        g.setColor(Color.WHITE);
        g.fillRect(35, 20, 200,70);
        g.setColor(Color.BLACK);
        g.drawString("Player: " +
                (game.getCurrentPlayer() == null
                 ? "NULL"
                 : game.getCurrentPlayer().getCanonicalName()), 40, 60);
        g.drawString("Direction: ⥁", 40, 80);
        g.drawString("Wind Direction: " + windDirection, 40, 40);

        g.setColor(Color.WHITE);
        g.fillRect(game.getWidth() - 185, 20, 160,30);
        g.setColor(Color.BLACK);
        g.drawString("Current Score: " + score + " 台", game.getWidth() - 180, 40);

        g.drawImage(TileClass.queryImage("wind", windDirection.toString().toLowerCase()),
                game.getWidth() / 2 - 30, game.getHeight() / 2 - 35, null);
    }

    public final int getScore() {
        return score;
    }

    private void setScore(int score) {
        this.score = score;
    }

    public WindDirection getWindDirection() {
        return windDirection;
    }

    private void setWindDirection(WindDirection windDirection) {
        this.windDirection = windDirection;
    }
}
