package utils;

import java.awt.*;
import java.io.IOException;

public interface Renderable {

    void render(Graphics g) throws IOException;
    int getStartingX();
    int getStartingY();
    int getMovingX();
    int getMovingY();

    void setStartingX(int x);

    void setStartingY(int y);
    void setMovingX(int x);
    void setMovingY(int y);
}
