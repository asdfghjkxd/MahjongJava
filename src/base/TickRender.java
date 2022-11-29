package base;

import java.awt.*;
import java.io.IOException;

public interface TickRender {
    void tick();
    void render(Graphics g) throws IOException;
}
