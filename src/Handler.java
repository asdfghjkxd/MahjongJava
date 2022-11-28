import java.awt.*;
import java.io.IOException;
import java.util.LinkedList;

/**
 * Maintain, Update and Maintain Objects on the board
 */
public class Handler {
    LinkedList<GameObject> objects = new LinkedList<GameObject>();
    private int startX;
    private int startY;

    // adding the tile to board counters
    private boolean goRight = true;
    private boolean goLeft = false;
    private boolean goDown = false;
    private boolean goUp = false;
    private int counter = 1;
    private int localCounter = 0;


    public Handler(Game attachedGameInstance) {
        startX = attachedGameInstance.getWidth() + 300;
        startY = attachedGameInstance.getHeight() + 130;
    }

    public final void tick() {
        for (GameObject go: objects) {
            go.tick();
        }
    }

    public final void render(Graphics g) throws IOException {
        for (GameObject go: objects) {
            go.render(g);
        }
    }

    public final void addObject(GameObject go) {
        objects.add(go);
    }

    public final void removeObject(GameObject go) {
        objects.remove(go);
    }

    public final void nextPosition(Tile t) {
        if (goRight && !goDown && !goUp && !goLeft) {
            if (counter % 19 != 0) {
                if (localCounter == 0) {
                    t.setTilePosition(startX, startY);
                    startY += 10;
                    localCounter++;
                } else if (localCounter == 1) {
                    t.setTilePosition(startX, startY);
                    startY -= 10;
                    startX += 35;
                    localCounter = 0;
                    counter += 1;
                }
            } else {
                counter = 1;
                localCounter = 0;
                goRight = false;
                goDown = true;
                startX += 30;
                this.nextPosition(t);
            }
        } else if (!goRight && goDown && !goUp && !goLeft) {
            if (counter % 18 != 0) {
                if (localCounter == 0) {
                    t.rotateTile(90);
                    t.setTilePosition(startX, startY);
                    startX -= 10;
                    localCounter++;
                } else if (localCounter == 1) {
                    t.rotateTile(90);
                    t.setTilePosition(startX, startY);
                    startX += 10;
                    startY += 35;
                    localCounter = 0;
                    counter++;
                }
            } else {
                counter = 1;
                localCounter = 0;
                goDown = false;
                goLeft = true;
                startY -= 60;
                startX -= 65;
                this.nextPosition(t);
            }
        } else if (!goRight && !goUp && !goDown && goLeft) {
            if (counter % 19 != 0) {
                if (localCounter == 0) {
                    t.setTilePosition(startX, startY);
                    startY += 10;
                    localCounter++;
                } else if (localCounter == 1) {
                    t.setTilePosition(startX, startY);
                    startY -= 10;
                    startX -= 37;
                    localCounter = 0;
                    counter ++;
                }
            } else {
                counter = 1;
                localCounter = 0;
                goLeft = false;
                goUp = true;
                startX -= 35;
                this.nextPosition(t);
            }
        } else if (!goRight && !goDown && !goLeft && goUp) {
            if (counter % 18 != 0) {
                if (localCounter == 0) {
                    t.rotateTile(90);
                    t.setTilePosition(startX, startY);
                    startX += 10;
                    localCounter++;
                } else if (localCounter == 1) {
                    t.rotateTile(90);
                    t.setTilePosition(startX, startY);
                    startX -= 10;
                    startY -= 35;
                    localCounter = 0;
                    counter++;
                }
            } else {
                counter = 1;
                localCounter = 0;
                this.nextPosition(t);
            }
        }
    }
}
