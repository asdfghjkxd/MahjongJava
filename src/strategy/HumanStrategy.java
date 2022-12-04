package strategy;

import pieces.Tile;

import java.util.List;

public class HumanStrategy implements Strategy{
    @Override
    public void onTileReceive(List<Tile> inputTiles) {

    }

    @Override
    public Tile pollDiscardTile(List<Tile> inputTiles) {
        return null;
    }

    @Override
    public void onTileDiscard(List<Tile> inputTiles) {

    }
}
