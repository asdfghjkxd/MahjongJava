package board;

import entities.Player;
import pieces.Tile;
import utils.Commandable;
import utils.Container;
import utils.Observable;

import java.io.IOException;
import java.util.*;

public class Board implements Container, Commandable, Observable {
    // Container is represented by a Stack of Tiles
    // Possible since all tiles are unique (even if they are from the same tile class/subclass or has the same value
    private final Stack<Tile> boardTiles = new Stack<>();

    // Board is responsible for handling the discards
    private final Discard discardedTiles = new Discard();
    private final LinkedHashSet<Player> boardPlayers = new LinkedHashSet<>();
    private int currentPlayerIndex = 0;

    public Board() {
        // resets the container
        resetContainer();

        // instantiate the board and players
        instantiateBoard();
        instantiatePlayers();
        initialDistribution();

    }
    @Override
    public void resetContainer() {
        this.boardTiles.clear();;
        this.discardedTiles.resetContainer();
        for (Player p: boardPlayers) {
            p.resetContainer();
        }
        boardPlayers.clear();
    }

    public void resetGame() {
        resetContainer();
        instantiateBoard();
        instantiatePlayers();
        initialDistribution();
    }

    // Instantiation of the Board and Players
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
            // create new players here
        }
    }

    private Tile distributeBoardTile() {
        return boardTiles.pop();
    }

    private void initialDistribution() {
        for (Player p: boardPlayers) {
            for (int i = 0; i < 14; i++) {
                Tile takenTile = distributeBoardTile();
                // player accept the tiles here
            }
        }
    }

    // Discard pile methods abstracted out to the board class to be managed
    public void discardToDiscardPile(Tile t) {
        discardedTiles.acceptDiscard(t);
    }

    public Tile takeFromDiscardPile(Tile t) {
        return discardedTiles.distributeDiscard();
    }

    // Player control
    public void advancePlayer() {

    }

    public void enforcePlayerAction() {

    }



    // Interface methods
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
