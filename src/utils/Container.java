package utils;

import pieces.Tile;

public interface Container {
     /**
      * Resets the container instance to a fresh state
      */
     void resetContainer();

     /**
      * Accepts an item to be placed into the container
      * @param tile: Tile to be placed into it
      */
     void acceptItem(Tile tile);

     /**
      * Removes an item from the "top" of the container, by insertion order
      * @return Tile that is to be removed
      */
     Tile discardItem();

     /**
      * Removes a specific item from the container, container must contain the item or null will be returned
      * @param t: Tile to be removed from the container
      * @return Tile or null
      */
     Tile discardItem(Tile t);
}
