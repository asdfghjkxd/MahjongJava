package board;

import entities.Player;
import pieces.Tile;

import java.util.HashMap;

public class Backtracker {
    private final HashMap<Tile, Boolean> backtracker = new HashMap<>();

    public Backtracker(Player player) {
        addAllTilesToBacktracker(player);

    }

    public void addAllTilesToBacktracker(Player player) {
        backtracker.clear();
        for (Tile t: player.getTiles()) {
            backtracker.put(t, false);
        }
    }

    private boolean checkIfAllWinning() {
        return backtracker
                .values()
                .stream()
                .reduce(true, (aBoolean, aBoolean2) -> aBoolean && aBoolean2);

    }

    public void backtrack() {
        
    }


}
