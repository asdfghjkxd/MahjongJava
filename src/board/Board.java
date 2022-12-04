package board;

import core.Game;
import entities.AI;
import entities.Human;
import entities.Player;
import pieces.Tile;
import utils.Commandable;
import utils.Container;
import utils.Observable;

import java.io.IOException;
import java.util.*;

public final class Board implements Container, Commandable, Observable {
    // Container is represented by a Stack of Tiles
    // Possible since all tiles are unique (even if they are from the same tile class/subclass or has the same value
    private final Stack<Tile> boardTiles = new Stack<>();

    // Board is responsible for handling the discards
    private final Discard discardedTiles = new Discard();
    private final LinkedList<Player> boardPlayers = new LinkedList<>();
    private Player currentPlayer = null;
    private int currentPlayerIndex = 0;
    private final LinkedList<List<Integer>> POSITIONS =
            new LinkedList<>(Arrays.asList(
                    new LinkedList<>(Arrays.asList((int) (Game.WIDTH / 6 * 1.5), (int) (Game.HEIGHT / 13 * 11.5))),
                    new LinkedList<>(Arrays.asList((Game.WIDTH / 25), (Game.HEIGHT / 5))),
                    new LinkedList<>(Arrays.asList((int) (Game.WIDTH / 6 * 1.5), (Game.HEIGHT / 30))),
                    new LinkedList<>(Arrays.asList((int) (Game.WIDTH / 20 * 18.5), (Game.HEIGHT / 5)))
            ));

    public Board() {
        resetGame();
    }


    // Instantiation of the Board and Players
    public void resetGame() {
        resetContainer();
        instantiateBoard();
        instantiatePlayers();
        initialDistribution();
        currentPlayer = boardPlayers.get(currentPlayerIndex);
    }

    private void instantiateBoard() {
        for (int i = 1; i <= 4; i++) {
            for (int j = 1; j < 10; j++) {
                // suits
                boardTiles.add(new Tile(0, 0, "suit", "number", String.valueOf(j), this, 0));
                boardTiles.add(new Tile(0, 0, "suit", "circle", String.valueOf(j), this, 0));
                boardTiles.add(new Tile(0, 0, "suit", "bamboo", String.valueOf(j), this, 0));
            }

            // honours
            boardTiles.add(new Tile(0, 0, "honour", "wind", "north", this, 0));
            boardTiles.add(new Tile(0, 0, "honour", "wind", "south", this, 0));
            boardTiles.add(new Tile(0, 0, "honour", "wind", "east", this, 0));
            boardTiles.add(new Tile(0, 0, "honour", "wind", "west", this, 0));
            boardTiles.add(new Tile(0, 0, "honour", "dragon", "white", this, 0));
            boardTiles.add(new Tile(0, 0, "honour", "dragon", "green", this, 0));
            boardTiles.add(new Tile(0, 0, "honour", "dragon", "red", this, 0));

            // bonus
            boardTiles.add(new Tile(0, 0, "bonus", "flower", "bf" + i, this, 0));
            boardTiles.add(new Tile(0, 0, "bonus", "flower", "rf" + i, this, 0));
        }

        boardTiles.add(new Tile(0, 0, "bonus", "animal", "cat", this, 0));
        boardTiles.add(new Tile(0, 0, "bonus", "animal", "rat", this, 0));
        boardTiles.add(new Tile(0, 0, "bonus", "animal", "rooster", this, 0));
        boardTiles.add(new Tile(0, 0, "bonus", "animal", "centipede", this, 0));

        // shuffle the board randomly
        Collections.shuffle(boardTiles, new Random());
    }

    private void instantiatePlayers() {
        if (boardPlayers.isEmpty()) {
            Human human = new Human(0, 0, this);
            AI ai1 = new AI(0, 0, 0, this);
            AI ai2 = new AI(0, 0, 0, this);
            AI ai3 = new AI(0, 0, 0, this);

            this.boardPlayers.add(human);
            this.boardPlayers.add(ai1);
            this.boardPlayers.add(ai2);
            this.boardPlayers.add(ai3);

            // Shuffle the players
            Collections.shuffle(boardPlayers, new Random());

            List<Player> playerList = boardPlayers.stream().filter(x -> x instanceof Human).toList();
            List<Player> AIList = boardPlayers.stream().filter(x -> x instanceof AI).toList();

            for (Player p: playerList) {
                List<Integer> pos = POSITIONS.get(0);
                p.setRotationDegrees(0);
                p.setPlayerPosition(pos.get(0), pos.get(1));
            }

            for (int i = 1; i < AIList.size(); i++) {
                Player curr = AIList.get(i);
                List<Integer> pos = POSITIONS.get(i);

                if (i % 2 == 0) {
                    curr.setRotationDegrees(90);
                    curr.setPlayerPosition(pos.get(0), pos.get(1));
                } else {
                    curr.setRotationDegrees(0);
                    curr.setPlayerPosition(pos.get(0), pos.get(1));
                }
            }

            initialDistribution();
        }
    }

    private Tile distributeBoardTile() {
        return boardTiles.pop();
    }

    private void initialDistribution() {
        for (Player p: boardPlayers) {
            for (int i = 0; i < 12; i++) {
                p.acceptItem(distributeBoardTile());
            }
        }
    }

    // Discard pile methods abstracted out to the board class to be managed
    public void discardToDiscardPile(Tile t) {
        discardedTiles.acceptItem(t);
    }

    public Tile takeFromDiscardPile() {
        return discardedTiles.discardItem();
    }

    // Player control
    public void advancePlayer() {
        this.currentPlayerIndex = (this.currentPlayerIndex + 1) % 4;
    }

    public void enforcePlayerAction() {

    }

    // Interface methods
    @Override
    public void resetContainer() {
        this.boardTiles.clear();;
        this.discardedTiles.resetContainer();
        for (Player p: boardPlayers) {
            p.resetContainer();
        }
        boardPlayers.clear();
    }

    @Override
    public void acceptItem(Tile tile) {
        // board does not accept items
    }

    @Override
    public Tile discardItem() {
        // board cannot discard items away
        return null;
    }

    @Override
    public Tile discardItem(Tile t) {
        // board cannot discard items away
        return null;
    }

    @Override
    public void synchronise_ticks() {

    }

    @Override
    public void synchronise_renders() throws IOException {

    }

    @Override
    public String toString() {
        return "Board Instance";
    }
}
