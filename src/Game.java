import javax.swing.plaf.nimbus.State;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.image.BufferStrategy;
import java.io.IOException;
import java.io.Serial;
import java.lang.reflect.Array;
import java.util.*;
import java.util.List;


public class Game extends Canvas implements Runnable {

    @Serial
    private static final long serialVersionUID = -6112428091888191314L;
    public static final int WIDTH = 1200;
    public static final int HEIGHT = WIDTH / 12 * 9;
    private Thread thread;
    private boolean running = false;
    private boolean paused = false;
    private final Handler handler;
    private final Board board;
    private final HUD hud;
    private final Menu menu;
    private int gamePlayerIndex = 0;
    private LinkedList<Player> gamePlayerRoster = null;
    private final LinkedList<List<Integer>> POSITIONS = new LinkedList<List<Integer>>();

    public enum State {
        Menu,
        Game,
        Pause,
        End
    }

    private State gameState = State.Menu;

    public Game() {
        handler = new Handler(this);

        // set up the menu
        menu = new Menu(this, handler);

        // set up the HUD
        hud = new HUD(this);

        // set up the board
        board = new Board(handler, this);

        // add game objects here
        new Window(WIDTH, HEIGHT, "Mahjong", this);

        // add listeners
        this.addKeyListener(new KeyInput(handler, this));
        this.addMouseListener(menu);

        // set up the right player locations
        POSITIONS.add(new LinkedList<>(Arrays.asList((int) (WIDTH / 6 * 1.5), (int) (HEIGHT / 13 * 11.5))));
        POSITIONS.add(new LinkedList<>(Arrays.asList((int) (WIDTH / 25), (int) (HEIGHT / 5))));
        POSITIONS.add(new LinkedList<>(Arrays.asList((int) (WIDTH / 6 * 1.5), (int) (HEIGHT / 30))));
        POSITIONS.add(new LinkedList<>(Arrays.asList((int) (WIDTH / 20 * 18.5), (int) (HEIGHT / 5))));

        // add the players and AI
        this.instantiatePlayers();
    }

    // main method
    public static void main(String[] args) {
        new Game();
    }

    // abstract methods to implement
    public synchronized void start() {
        // starting the thread
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop() {
        // starting the thread
        try {
            // kills the thread
            thread.join();
            running = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        // game loop
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountTicks = 60.0;
        double ns = 1E9 / amountTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;

        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;

            while (delta >= 1) {
                tick();
                delta--;
            }

            if (running) {
                try {
                    render();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                // System.out.println("FPS: " + frames);
                frames = 0;
            }
        }

        stop();
    }

    private void tick() {
        if (gameState.equals(State.Game)) {
            hud.tick();
            handler.tick();

        } else if (gameState.equals(State.Menu)) {
            menu.tick();
        }
    }

    private void render() throws IOException {
        BufferStrategy bs = this.getBufferStrategy();

        // creating 3 buffering stream
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        // g.drawImage(TileClass.queryImage("number", 1), 720, 40, null);

        if (gameState.equals(State.Game)) {
            hud.render(g);
            handler.render(g);
        } else if (gameState.equals(State.Menu)){
            menu.render(g);
        }

        g.dispose();
        bs.show();
    }

    // triggers AI creation and player creation
    private final void instantiatePlayers() {
        // handle player rotation
        Player p = new Player(0, 0, this, "You", ID.Player);
        AI ai1 = new AI(0, 0, this, "AI_1", ID.AI);
        AI ai2 = new AI(0, 0, this, "AI_2", ID.AI);
        AI ai3 = new AI(0, 0, this, "AI_3", ID.AI);

        gamePlayerRoster = new LinkedList<Player>();
        gamePlayerRoster.add(p);
        gamePlayerRoster.add(ai1);
        gamePlayerRoster.add(ai2);
        gamePlayerRoster.add(ai3);

        for (int i = 0; i < new Random().nextInt(1, 100); i++) {
            Collections.shuffle(gamePlayerRoster);
        }

        List<Player> onlyPlayer = gamePlayerRoster.stream().filter(x -> x.getId() == ID.Player).toList();
        List<Player> onlyAI = gamePlayerRoster.stream().filter(x -> x.getId() == ID.AI).toList();

        for (Player player: onlyPlayer) {
            List<Integer> pos = POSITIONS.get(0);
            player.setPlayerPosition(pos.get(0), pos.get(1));
        }

        for (int i = 1; i <= onlyAI.size(); i++) {
            Player curr = onlyAI.get(i - 1);
            List<Integer> curr_pos = POSITIONS.get(i);

            if (i % 2 == 1) {
                curr.rotateAIPlayer();
                curr.setPlayerPosition(curr_pos.get(0), curr_pos.get(1));
            } else {
                curr.setPlayerPosition(curr_pos.get(0), curr_pos.get(1));
            }
        }

        synchronized (board) {
            // add game objects first
            for (Player player: gamePlayerRoster) {
                for (int j = 0; j < 12; j++) {
                    board.drawFromBoard(player);
                    handler.addObject(p);
                }
            }
        }
    }

    public final void pauseGame() {
        this.paused = !this.paused;
    }

    public final void resetGame() {
        for (Player p: gamePlayerRoster) {
            p.reset();
        }

        gamePlayerRoster.clear();
        instantiatePlayers();
    }

    // getters and setters
    public final Board getBoard() {
        return this.board;
    }

    public final Player getCurrentPlayer() {
        return this.gamePlayerRoster.get(gamePlayerIndex);
    }

    public State getGameState() {
        return gameState;
    }

    public final void setGameState(State s) {
        this.gameState = s;
    }
}
