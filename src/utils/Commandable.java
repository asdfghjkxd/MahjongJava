package utils;

import java.awt.*;
import java.io.IOException;

public interface Commandable {
    void synchronise_ticks();
    void synchronise_renders(Graphics g) throws IOException;
}
